package worldOfZuul.Main.Java;

import worldOfZuul.Main.Java.Classes.*;
import worldOfZuul.Main.Java.Utilities.ConfigLoader;
import worldOfZuul.Main.Java.Utilities.Direction;

import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private static boolean isInitialized = false;

    private int day;
    private int power;
    private int defaultPower; // can maybe be changed into a constant value
    private int points;
    private Room currentRoom;

    private int maxDays;
    private ArrayList<Integer> extraPowerList = new ArrayList<>();
    private ActivityManager activityManager = new ActivityManager(); // Placeholder object, until "real one" has been created.
    private Player player;

    private final ConfigLoader configs;


    private Game() throws IOException {
        configs = new ConfigLoader();

        this.activityManager = configs.getActivityManager();

        this.currentRoom = configs.getDefaultRoom();


        this.player = new Player("Tim");
        //this.player.addInventory(inventory);

        this.maxDays = 7;

        this.defaultPower = activityManager.getDailyPowerRequirement();

        this.day = 0;

        this.extraPowerList = activityManager.getExtraPowerLevels();
        this.power = defaultPower;
        this.points = 0;

        //this.createRooms();
    }

    public static Game createInstance() throws IOException {
        if (Game.isInitialized) throw new IllegalStateException("Game is already initialized");

        Game.isInitialized = true;
        return new Game();
    }


    private void createRooms() {
        Room entrance = new Room("entrance", "Entrance", "at the entrance");
        Room hallway = new Room("hallway", "Hallway", "in the hallway");
        entrance.setExit(Direction.NORTH, hallway);
        Item shoes = new Item("shoesTest", "Shoes test");
        hallway.addItem(shoes);

        Appliance washer = new Appliance("washer", "Washing Machine", "washClothes", "dirtyClothes");

        hallway.addAppliance(washer);

        Activity testDaily = new Activity("daily", "Daily", 1, 1, 1, true);
        Activity testNoneDaily = new Activity("none daily", "None Daily", 1, 1, 1, false);
        testNoneDaily.setAsDone();
        this.activityManager.add(testDaily);
        this.activityManager.add(testNoneDaily);

        // Also setting the current room to the entrance
        currentRoom = entrance;
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

        this.currentRoom.removeItem(itemInRoom.id());
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
    public int getRandomExtraPower() {
        int dailyExtra = 0;

        if (isLastDay()) {
            for (int i = 0; i < extraPowerList.size(); i++) {
                dailyExtra += extraPowerList.size();
            }
            extraPowerList.clear();
            return dailyExtra;
        }

        for (int i = 0; i < extraPowerList.size(); i++) {
            if (Math.random() * 7 >= 6) {
                dailyExtra += extraPowerList.get(i);
                extraPowerList.remove(i);
                i -= 1; // If the current list-item is removed, the list will get one object "smaller".
            }
        }

        return dailyExtra;
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
        if (appliance == null){
            System.out.printf("You can't use '%s'\n", command.getCommandValue());
            return false;
        }

        String itemId = appliance.getItemId();
        String activityId = appliance.getActivityId();
        Activity activity = this.activityManager.getAllActivities().getByAlias(activityId);

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
                //Load referenceItem, so its displayName can be used to tell the use what item is missing.

                System.out.printf("You don't have '%s' in your inventory\n", referenceItem.displayName());
                return false;
            }

            this.player.removeItem(item);
            System.out.printf("'%s' have been removed from your inventory\n", item.displayName());
        }

        this.removePower(activity.getPowerCost());
        activity.setAsDone();
        System.out.printf("You have finished activity '%s' \n", activity.getDisplayName());
        return true;
    }

    // Method for adding points
    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
    }

    // Method for removing points
    public void removePoints(int n) {
        this.points -= n;
    }

    // Method for removing power
    public void removePower(int n) {
        this.power -= n;
    }

    // Method for checking last day
    public boolean isLastDay() {
        // Returns true, if day is higher than max days
        return day > maxDays;
    }

    // Method for incrementing the day counter
    public void setNextDay() {
        day += 1;
    }

    // Method for getting the power value
    public int getPower() {
        return power;
    }

    // Method for setting the power value
    public void setPower(int newPower) {
        power = newPower;
    }

    // Method for getting the points value
    public int getPoints() {
        return points;
    }

    // Method for setting the points value
    public void setPoints(int n) {
        this.points = n;
    }

    // Method for getting the day value
    public int getDay() {
        return day;
    }

    // Attempt at making day system
    public boolean daySystem() {
        for (int i = 0; i < activityManager.getAllActivities().size(); i++) {
            if (!activityManager.getAllActivities().get(i).isDone()) {
                return false;
            }
        }
        setNextDay();
        setPower(defaultPower + getRandomExtraPower());
        System.out.println("Your power is " + getPower());
        return true;
    }

    public Player getPlayer() {
        return player;
    }
}