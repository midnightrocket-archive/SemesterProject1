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

    private ArrayList<Appliance> roomAppliances = new ArrayList<>();

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public Appliance createAppliance(String name, Activity activityReference) {
        Appliance appliance = new Appliance(name, this, activityReference);
        roomAppliances.add(appliance);
        return appliance;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }


    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n\n" + getAppliances() + "\n\n" + getExitString();
    }

    public String getAppliances() {
        String appliancesString = new String("In this room you can find: \n");
        if (roomAppliances.isEmpty()) {
            appliancesString += "Nothing. This room is empty.";
        } else {
            for (Appliance appliance : roomAppliances) {
                appliancesString += appliance.getName() + "\t";
            }

        }
        return appliancesString;
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }
}