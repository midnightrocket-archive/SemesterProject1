package com.example.semester1.core;

import com.example.semester1.core.Classes.*;
import com.example.semester1.core.Utilities.ConfigLoader;
import com.example.semester1.core.Utilities.Direction;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    private static Game instance;

    private int day;
    private int maxDays;

    private int defaultPower; // can maybe be changed into a constant value
    private Room currentRoom;
    private int power;
    private ArrayList<Integer> extraPowerList;

    private int[] randomPowerLevels;

    private ActivityManager activityManager = new ActivityManager(); // Placeholder object, until "real one" has been created.
    private Player player;

    private final ConfigLoader configs;


    private Game(String name) throws IOException {
        configs = new ConfigLoader();

        this.activityManager = configs.getActivityManager();

        this.currentRoom = configs.getDefaultRoom();


        this.player = new Player(name);


        this.maxDays = 7;
        this.day = 1;


        this.defaultPower = activityManager.getDailyPowerRequirement();
        this.extraPowerList = activityManager.getExtraPowerLevels();

        this.generateRandomPowerLevels(); //Random power levels for the whole game is precomputed.

        this.getPowerForToday();
    }

    public static Game createInstance(String name) throws IOException {
        if (Game.instance != null) throw new IllegalStateException("Game is already initialized");

        return instance = new Game(name);
    }

    public static Game getInstance() throws IOException {
        if (Game.instance == null) throw new IllegalStateException("Game is not initialized");

        return instance;
    }


    public boolean goRoom(Command command) {

        if (!command.hasCommandValue()) {
            //No direction on command.
            //Can't continue with GO command.
            return false;
        }

        Direction direction;

        try {
            direction = Direction.parse(command.getCommandValue());
        } catch (IllegalArgumentException exception) {
            return false;
        }

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false;
        } else {
            currentRoom = nextRoom;
            return true;
        }
    }

    public boolean pickupItem(Command command) {

        if (!command.hasCommandValue()) {
            //No item on command.
            //Can't continue with PICKUP command.
            return false;
        }

        String item = command.getCommandValue();

        if (!this.currentRoom.hasItem(item)) {
            // item is not in room.
            return false;
        }

        Item itemInRoom = this.currentRoom.getItem(item);

        this.currentRoom.removeItem(itemInRoom.getId());
        this.player.addItem(itemInRoom);

        return true; // command succeeded
    }

    // Returns false if user input of command has a second word.
    public boolean quit(Command command) {
        // Makes sure that there are no extra argument.
        // If there is another argument, it will return false.
        return !command.hasCommandValue();
    }

    // Returns a random int between 0 and the sum of extraPowerList. If last day, return remaining extra power.
    private void generateRandomPowerLevels() {
        Random rand = new Random();
        this.randomPowerLevels = new int[this.maxDays];
        for (int i : this.extraPowerList) {
            this.randomPowerLevels[rand.nextInt(this.maxDays)] += i;
        }

    }

    public String getRoomDescription() {
        return this.currentRoom.getLongDescription();
    }

    public String getAppliancesInRoom() {
        return this.currentRoom.appliancesToString();
    }

    public String inventoryToString() {
        return this.player.inventoryToString();
    }

    public String activitiesToString() {
        return this.activityManager.toString();
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public boolean useCommand(Command command) {
        Appliance appliance = this.currentRoom.getAppliance(command.getCommandValue());
        if (appliance == null) {
            System.out.printf("You can't use '%s'\n", command.getCommandValue());
            return false;
        }

        String activityId = appliance.getActivityId();
        Activity activity = this.activityManager.getAllActivities().getByAlias(activityId);
        String itemId = activity.getItemId();

        // Using early returns, instead of if else. To avoid big if-else nesting.

        if (activity.isDone()) {
            System.out.println("You have already done that activity :-)");
            return false;
        }


        if (this.power < activity.getPowerCost()) {
            System.out.println("You don't have enough power :-(");
            System.out.printf("This activity requires %d power \n", activity.getPowerCost());
            return false;
        }


        if (!itemId.equals("none")) {
            Item item = this.player.getItem(itemId);
            if (item == null) {

                Item referenceItem = this.configs.getItemsStore().getByAlias(itemId);
                //Load referenceItem, so its getDisplayName can be used to tell the use what item is missing.

                System.out.printf("You don't have '%s' in your inventory\n", referenceItem.getDisplayName());
                return false;
            }

            this.player.removeItem(item);
            System.out.printf("'%s' have been removed from your inventory\n", item.getDisplayName());
        }

        this.removePower(activity.getPowerCost());
        activity.setAsDone();
        System.out.printf("You have finished activity '%s' \n", activity.getDisplayName());
        return true;
    }


    // Method for removing power
    public void removePower(int n) {
        this.power -= n;
    }

    // Method for checking last day
    public boolean isLastDay() {
        // Returns true, if day is higher than max days
        return this.day >= this.maxDays;
    }

    // Method for getting the power value
    public int getPower() {
        return this.power;
    }

    // Method for setting the power value
    public void setPower(int power) {
        this.power = power;
    }

    // Method for getting the points value
    public int getPoints() {
        return this.activityManager.calculatePoints();
    }


    // Method for getting the day value
    public int getDay() {
        return this.day;
    }

    public ActivityManager getActivityManager(){
        return activityManager;
    }


    private void getPowerForToday() {
        // Add defaultPower level with the random powerLevel of the current day
        this.power = this.defaultPower + this.randomPowerLevels[this.getDay() - 1];
    }

    private void advanceDay() {
        System.out.println(this.activityManager.missedDailyActivitiesToString());
        this.activityManager.settleDailyActivities();
        this.day += 1;
        this.configs.repopulateDailyNeededItems();
        this.getPowerForToday();
    }

    public boolean sleepCommand() {
        if (this.isLastDay()) {
            return true;
        }
        this.advanceDay();
        return false;
    }

    public String generateGameStats() {
        StringBuilder output = new StringBuilder();
        output.append("This is an overview of your activities.");
        output.append(this.activityManager.generateStats());
        output.append(String.format("Your total points are %d", this.getPoints()));
        return output.toString();
    }

    public Player getPlayer() {
        return this.player;
    }
}