package worldOfZuul.Main.Java;
import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Classes.Appliance;
import worldOfZuul.Main.Java.Classes.Item;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits;

    private HashMap<String, Appliance> roomAppliances = new HashMap<>();
    private HashMap<String, Item> roomItems = new HashMap<>();

    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
    }
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Appliance createAppliance(String name, Activity activityReference) {
        Appliance appliance = new Appliance(name, this, activityReference);
        roomAppliances.put(name, appliance);
        return appliance;
    }

    public boolean hasAppliance(String applianceName) {
        return roomAppliances.containsKey(applianceName);
    }
    public Activity getApplianceActivity(Appliance appliance) {
        return appliance.getActivityReference();
    }

    public Item createItem(String name, Appliance applianceReference) {
        Item item = new Item(name, applianceReference);
        roomItems.put(name, item);
        return item;
    }

    public boolean hasItem(String itemName) {
        return roomItems.containsKey(itemName);
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description +
                ".\n\n" + getAppliancesString() +
                "\n\n" + getItemsString() +
                "\n\n" + getExitString();
    }

    public Appliance getAppliance(String applianceKey) {
        return roomAppliances.get(applianceKey);
    }
    public String getAppliancesString() {
        String appliancesString = new String("In this room you can use: \n");
        if (roomAppliances.isEmpty()) {
            appliancesString += "Nothing. This room has no appliances.";
        } else {
            for (String applianceName : roomAppliances.keySet()) {
                appliancesString += applianceName + "\t";
            }
        }
        return appliancesString;
    }
    public Item getItem(String itemKey) {
        return roomItems.get(itemKey);
    }

    public String getItemsString() {
        String itemsString = new String("In this room you can find: \n");
        if (roomItems.isEmpty()) {
            itemsString += "Nothing. This room has no items.";
        } else {
            for (String itemName : roomItems.keySet()) {
                itemsString += itemName + "\t";
            }
        }
        return itemsString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }
}