package worldOfZuul.Main.Java;
import worldOfZuul.Main.Java.Classes.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Game instance = null;

    private int day;
    private int power;
    private int defaultPower; // can maybe be changed into a constant value
    private int points;
    private Room currentRoom;
    private ValidActions commands; // holds all valid commands
    private Inventory inventory;
    private int maxDays;
    private ArrayList<Integer> extraPowerList = new ArrayList<>();
    private ActivityManager activityManager = new ActivityManager(); // Placeholder object, until "real one" has been created.
    private Player player;


    private Game() {
        this.commands = new ValidActionsImplementation();
        this.inventory = new Inventory();
        this.activityManager = new ActivityManager();
        this.player = new Player(inventory);

        this.maxDays = 7;

        this.defaultPower = calcDefaultPower();

        this.day = 0;

        makeExtraPowerList();
        this.power = defaultPower;
        this.points = 0;

        createRooms();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    // Calculates daily minimum power
    private int calcDefaultPower() {
        int tempPower = 0;
        for(int i = 0; i < activityManager.listOfActivities.size(); i++){
            if(activityManager.listOfActivities.get(i).isDaily()){
                tempPower += activityManager.listOfActivities.get(i).getPowerCost();
            }
        }
        return tempPower;
    }

    // Makes list of "extra" activities' power costs
    private void makeExtraPowerList() {
        // Make list of extra power costs:
        for(int i = 0; i < activityManager.listOfActivities.size(); i++){
            if(!activityManager.listOfActivities.get(i).isDaily()){
                extraPowerList.add(activityManager.listOfActivities.get(i).getPowerCost());
            }
        }
    }

    private void createRooms() {
        Room homeOffice, livingRoom, bedroom,
                kitchen, diningRoom, laundryRoom,
                bathroom, hallway, entrance;

        Activity dryHair, washCloths, dryCloths,
                makeFood, washDishes, boilWater,
                watchTV, playConsoleGames, playPCGames,
                doWork;

        Appliance hairdryer, washingMachine, dryer,
                oven, dishwasher, kettel,
                tv, gameConsole, computer, computer_work;

        Item dirtyCloths, wetCloths, ovenFood,
                stoveFood, dirtyDishes;


        // Defining the rooms
        homeOffice = new Room("in the home office");
        livingRoom = new Room("in the living room");
        bedroom = new Room("in your bedroom");
        kitchen = new Room("in the kitchen");
        diningRoom = new Room("in the dining room");
        laundryRoom = new Room("in the laundry room");
        bathroom = new Room("in the bathroom");
        hallway = new Room("in the hallway");
        entrance = new Room("at the entrance");


        // Setting the room exits
        homeOffice.setExit("east", livingRoom);

        livingRoom.setExit("west", homeOffice);
        livingRoom.setExit("east", bedroom);
        livingRoom.setExit("south", diningRoom);

        bedroom.setExit("west", livingRoom);

        kitchen.setExit("east", diningRoom);

        diningRoom.setExit("north", livingRoom);
        diningRoom.setExit("west", kitchen);
        diningRoom.setExit("east", laundryRoom);
        diningRoom.setExit("south", hallway);

        laundryRoom.setExit("west", diningRoom);

        bathroom.setExit("east", hallway);

        hallway.setExit("north", diningRoom);
        hallway.setExit("west", bathroom);
        hallway.setExit("east", entrance);

        entrance.setExit("west", hallway);


        // Adding all activities
        dryHair = new Activity("Dry hair", 1, 1, 1, false);
        washCloths = new Activity("Wash cloths", 1, 1, 1, false);
        dryCloths = new Activity("Dry cloths", 1, 1, 1, false);
        makeFood = new Activity("Make food", 1, 1, 1, false);
        washDishes = new Activity("Wash dishes", 1, 1, 1, false);
        boilWater = new Activity("Boil water", 1, 1, 1, false);
        watchTV = new Activity("Watch TV", 1, 1, 1, false);
        playConsoleGames = new Activity("Play console games", 1, 1, 1, false);
        playPCGames = new Activity("Play PC games", 1, 1, 1, false);
        doWork = new Activity("Do work", 1, 1, 1, false);

        activityManager.addActivity(dryHair);
        activityManager.addActivity(washCloths);
        activityManager.addActivity(dryCloths);
        activityManager.addActivity(makeFood);
        activityManager.addActivity(washDishes);
        activityManager.addActivity(boilWater);
        activityManager.addActivity(watchTV);
        activityManager.addActivity(playConsoleGames);
        activityManager.addActivity(playPCGames);
        activityManager.addActivity(doWork);



        // Adding all appliances to their rooms
        hairdryer = bathroom.createAppliance("hairdryer", dryHair);
        washingMachine = laundryRoom.createAppliance("washing_machine", washCloths);
        dryer = laundryRoom.createAppliance("dryer", dryCloths);
        oven = kitchen.createAppliance("oven", makeFood);
        dishwasher = kitchen.createAppliance("dishwasher", washDishes);
        kettel = kitchen.createAppliance("kettel", boilWater);
        tv = livingRoom.createAppliance("tv", watchTV);
        gameConsole = livingRoom.createAppliance("game_console", playConsoleGames);
        computer = homeOffice.createAppliance("computer", playPCGames);
        computer_work = homeOffice.createAppliance("computer_work", doWork);


        // Adding all items to their rooms
        dirtyCloths = bedroom.createItem("dirty_cloths", washingMachine);
        wetCloths = laundryRoom.createItem("wet_cloths", dryer);
        ovenFood = kitchen.createItem("oven_food", oven);
        dirtyDishes = diningRoom.createItem("dirty_dishes", dishwasher);


        // Also setting the current room to the entrance
        currentRoom = entrance;
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

        currentRoom.removeItem(itemInRoom.getName());
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
    public int getRandomExtraPower() {
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

    public Room getCurrentRoom() {
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
        for (int i = 0; i < activityManager.listOfActivities.size(); i++) {
            if (!activityManager.listOfActivities.get(i).isActivityDone()) {
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