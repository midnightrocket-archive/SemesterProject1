package worldOfZuul.Main.Java;
import worldOfZuul.Main.Java.Classes.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static int day;
    public static int power;
    public static int points;
    private static Room currentRoom;
    private ValidActions commands; // holds all valid commands
    private Inventory inventory;

    private final int maxDays;
    private final int defaultPower; // can maybe be changed into a constant value
    public int extraPower;
    private ArrayList<Integer> extraPowerList = new ArrayList<Integer>();

    private ActivityManager activityManager = new ActivityManager(); // Placeholder object, until "real one" has been created.


    public Game() {
        commands = new ValidActionsImplementation();
        inventory = new Inventory();
        activityManager = new ActivityManager();


        maxDays = 7;

        defaultPower = calcDefaultPower();

        day = 0;

        makeExtraPowerList();
        extraPower = 100; // random placeholder value, should later be replaced by value-generating method
        power = defaultPower;
        points = 0;

        createRooms();
    }

    // Calculates daily minimum power
    private int calcDefaultPower(){
        int tempPower = 0;
        for(int i = 0; i < activityManager.listOfActivities.size(); i++){
            if(activityManager.listOfActivities.get(i).isDaily()){
                tempPower += activityManager.listOfActivities.get(i).getPowerCost();
            }
        }
        System.out.println(tempPower);
        return tempPower;
    }

    // Makes list of "extra" activities' power costs
    private void makeExtraPowerList(){
        // Make list of extra power costs:
        for(int i = 0; i < activityManager.listOfActivities.size(); i++){
            if(!activityManager.listOfActivities.get(i).isDaily()){
                extraPowerList.add(activityManager.listOfActivities.get(i).getPowerCost());
            }
        }
    }

    private void createRooms() {
        Room outside, theatre, pub, lab, office;
        Activity turnOff, makeFood, lockDoor; // Activity creation will be handled with ActivityManager
        Appliance fridge, lights, oven, door;
        Item food, key;

        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;

        // Temporary appliance and activity creation

        turnOff = new Activity("Turn off", 1, 1, 1, true);
        makeFood = new Activity("Make food", 1, 1, 1, true);
        lockDoor = new Activity("Lock door", 1, 1, 1, true);

        door = outside.createAppliance("door", lockDoor);
        fridge = office.createAppliance("fridge", turnOff);
        lights = office.createAppliance("lights", turnOff);
        oven = lab.createAppliance("oven", makeFood);

        activityManager.addActivity(lockDoor);
        activityManager.addActivity(turnOff);
        activityManager.addActivity(turnOff);
        activityManager.addActivity(makeFood);

        key = outside.createItem("key", door);
        food = lab.createItem("food", oven);
    }

    public boolean goRoom(Command command) {

        if (!command.hasCommandValue()) {
            //No direction on command.
            //Can't continue with GO command.
            return false;
        }

        String direction = command.getCommandValue();

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

        if (!currentRoom.hasItem(item)) {
            // item is not in room.
            return false;
        }

        Item itemInRoom = currentRoom.getItem(item);

        currentRoom.removeItem(itemInRoom.getItemName());
        inventory.addItem(itemInRoom);

        return true; // command succeeded
    }

    // Returns false if user input of command has a second word.
    public boolean quit(Command command) {
        // Makes sure that there are no extra argument.
        // If there is another argument, it will return false.
        return !command.hasCommandValue();
    }

    // Returns a random int between 0 and the sum of extraPowerList. If last day, return remaining extra power.
    public int getRandomExtraPower(){
        int dailyExtra = 0;

        if(isLastDay()){
            for(int i = 0; i < extraPowerList.size(); i++){
                dailyExtra += extraPowerList.size();
            }
            extraPowerList.clear();
            return dailyExtra;
        }
        
        for(int i = 0; i < extraPowerList.size(); i++){
            if(Math.random()*7 >= 6){
                dailyExtra += extraPowerList.get(i);
                extraPowerList.remove(i);
                i -= 1; // If the current list-item is removed, the list will get one object "smaller".
            }
        }

        return dailyExtra;
    }

    public String getRoomDescription() {
        return currentRoom.getLongDescription();
    }

    public String getAppliancesInRoom() {
        return currentRoom.getAppliancesString();
    }

    public String getInventory() {
        return inventory.toString();
    }

    public String getActivity() {
        return activityManager.toString();
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    // Returns a CommandWords object which holds all valid commands.
    public ValidActions getCommands() {
        return commands;
    }

    // Returns List<String> of all valid command strings.
    public List<String> getCommandDescriptions() {
        return commands.getActionWords();
    }

    /* This is used in the Parser class and takes the user input as arguments.
     * commands.getCommand(word1) returns the corresponding command enum to the first word in the user input.
     * Then returns a Command object with the evaluated enum and the command string.
     */
    public Command getCommand(String word1, String word2) {
        return new CommandImplementation(commands.getAction(word1), word2);
    }

    // Method add points
    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
    }

    // Method for checking last day
    public boolean isLastDay() {
        // Returns true, if day is higher than max days
        return day > maxDays;
    }

    // Method go to next day
    public void setNextDay() {
        day += 1;
        if (isLastDay()) {
            double randomExtraPower = Math.random() * extraPower;
            setPower(defaultPower + (int) randomExtraPower);
            extraPower -= (int) randomExtraPower;
        } else {
            setPower(defaultPower + extraPower);
            extraPower = 0;
        }
    }

    // Metode getPower til at hente powerværdi.
    public int getPower() {
        return power;
    }

    // Metode set power værdi på dagen
    public void setPower(int newPower) {
        power = newPower;
    }

    // Metode for points værdi.
    public int getPoints() {
        return points;
    }
}