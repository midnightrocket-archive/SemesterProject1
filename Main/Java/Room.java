package worldOfZuul.Main.Java;

import worldOfZuul.Main.Java.Classes.Appliance;
import worldOfZuul.Main.Java.Classes.Inventory;
import worldOfZuul.Main.Java.Classes.Item;
import worldOfZuul.Main.Java.Classes.ReferenceContainer;
import worldOfZuul.Main.Java.Utilities.Direction;

import java.util.Map;
import java.util.HashMap;

public class Room {
    private final String description;

    private final String displayName;

    private final String id;


    private HashMap<Direction, Room> exits = new HashMap<Direction, Room>();
    private ReferenceContainer<Appliance> roomAppliances = new ReferenceContainer<Appliance>();
    private Inventory roomItems = new Inventory();

    public Room(String id, String displayName, String description) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
    }

    // Adds an appliance to a room
    public void addAppliance(Appliance appliance) {
        this.roomAppliances.add(appliance);
    }

    public boolean hasAppliance(String applianceId) {
        return this.roomAppliances.containsByAlias(applianceId);
    }

    // Adds an item to a room
    public void addItem(Item item) {
        this.roomItems.add(item);
    }

    public void removeItem(String itemId) {
        this.roomItems.removeByAlias(itemId);
    }

    public boolean hasItem(String itemName) {
        return this.roomItems.containsByAlias(itemName);
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
    public void setExit(Direction direction, Room neighbor) {
        this.exits.put(direction, neighbor);

        //By pass setExit() method, to avoid infinite recursion.
        neighbor.exits.put(direction.opposite(), this);
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
        return this.roomAppliances.getByAlias(applianceId);
    }

    public String appliancesToString() {
        //Guard clause
        if (this.roomAppliances.isEmpty()) return "This room has no appliances.\n";


        StringBuilder stringBuilder = new StringBuilder("In this room you can use: \n");

        for (Appliance appliance : this.roomAppliances) {
            stringBuilder.append(String.format(" - %s\n", appliance.getDisplayName()));
        }

        return stringBuilder.toString();
    }

    public Item getItem(String itemId) {
        return this.roomItems.getByAlias(itemId);
    }

    public String itemsToString() {
        String output = "In this room you can find: \n";

        return output + this.roomItems.toString();
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getId() {
        return id;
    }
}