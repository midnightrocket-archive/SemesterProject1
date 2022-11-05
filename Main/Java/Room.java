package worldOfZuul.Main.Java;

import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Classes.Appliance;
import worldOfZuul.Main.Java.Classes.Item;
import worldOfZuul.Main.Java.Classes.Utilities.Direction;

import java.util.Map;
import java.util.HashMap;

public class Room {
    private final String description;

    private final String displayName;

    private final String id;


    private HashMap<Direction, Room> exits = new HashMap<Direction, Room>();
    private HashMap<String, Appliance> roomAppliances = new HashMap<>();
    private HashMap<String, Item> roomItems = new HashMap<>();

    public Room(String id, String displayName, String description) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
    }

    // Adds an appliance to a room
    public void addAppliance(Appliance appliance) {
        this.roomAppliances.put(appliance.getId(), appliance);
    }

    public boolean hasAppliance(String applianceId) {
        return this.roomAppliances.containsKey(applianceId);
    }

    // Adds an item to a room
    public void addItem(Item item) {
        this.roomItems.put(item.id(), item);
    }

    public void removeItem(String itemId) {
        this.roomItems.remove(itemId);
    }

    public boolean hasItem(String itemName) {
        return this.roomItems.containsKey(itemName);
    }

    private String exitsToString() {
        StringBuilder stringBuilder = new StringBuilder("Exits:\n");

        for (Map.Entry<Direction, Room> exit : this.exits.entrySet()) {
            Direction direction = exit.getKey();
            Room nextRoom = exit.getValue();
            stringBuilder.append(String.format(" - %s: '%s'\n", direction, nextRoom.getDisplayName()));
        }

        return stringBuilder.toString();
    }

    // Sets an exit for a room
    public void addExit(Direction direction, Room neighbor) {
        this.exits.put(direction, neighbor);
    }

    public Room getExit(Direction direction) {
        return this.exits.get(direction);
    }

    /*public Activity getApplianceActivity(Appliance appliance) {
        return appliance.getActivityReference();
    }*/

    public String getShortDescription() {
        return this.description;
    }

    public String getLongDescription() {
        return "\nYou are " + this.description +
                ".\n\n" + appliancesToString() +
                "\n" + itemsToString() +
                "\n" + exitsToString();
    }

    public Appliance getAppliance(String applianceId) {
        return this.roomAppliances.get(applianceId);
    }

    public String appliancesToString() {
        //Guard clause
        if (this.roomAppliances.isEmpty()) return "This room has no appliances.\n";


        StringBuilder stringBuilder = new StringBuilder("In this room you can use: \n");

        for (Map.Entry<String, Appliance> applianceEntry : this.roomAppliances.entrySet()) {
            Appliance appliance = applianceEntry.getValue();
            stringBuilder.append(String.format(" - %s\n", appliance.getDisplayName()));
        }

        return stringBuilder.toString();
    }

    public Item getItem(String itemId) {
        return this.roomItems.get(itemId);
    }

    public String itemsToString() {
        //Guard clause
        if (this.roomItems.isEmpty()) return "This room has no items.\n";


        StringBuilder stringBuilder = new StringBuilder("In this room you can find: \n");

        for (Map.Entry<String, Item> itemEntry : this.roomItems.entrySet()) {
            Item item = itemEntry.getValue();
            stringBuilder.append(String.format(" - %s\n", item.displayName()));
        }

        return stringBuilder.toString();
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getId() {
        return id;
    }
}