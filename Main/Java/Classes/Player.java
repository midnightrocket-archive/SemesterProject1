package worldOfZuul.Main.Java.Classes;

import worldOfZuul.Main.Java.Command;
import worldOfZuul.Main.Java.Room;
import worldOfZuul.Main.Java.Game;

import java.util.ArrayList;

public class Player {
    Room currentRoom;
    Inventory inventoryReference;

    public Player(Inventory inventory) {
        this.currentRoom = this.getCurrentRoom();
        this.inventoryReference = inventory;
    }

    /*
     * If string matches an appliance in CurrentRoom, which has a complete-able activity "attached", and all criteria
     * for said activity (power amount, items in inv) is matched, the activity connected to the mentioned appliance will
     * be executed.
     */
    public void use(Command command){
        Room currentRoom = Game.getInstance().getCurrentRoom();
        String applianceName;
        Appliance applianceInUse;

        Item item = new Item("PlaceholderItem", new Appliance("PlaceholderAppliance",
                new Room("PlaceholderRoom"), new Activity("PlaceholderActivity",0,
                0,0,
                false))); // Placeholder item that will be replaced in inventory-search loop.

        Activity activity;

        applianceName = command.getCommandValue();
        // Checks whether the argument passed is a valid appliance name, and if that appliance has an active activity.
        if(currentRoom.getAppliance(applianceName).getActivityReference() == null){
            System.out.println("No appliance of that name has an activity to do in this room.");
            return;
        }


        applianceInUse = currentRoom.getAppliance(applianceName);
        activity = applianceInUse.getActivityReference();

        // Checks if the activity has already been done
        if(activity.isActivityDone()){
            if(activity.isDaily()) {
                System.out.println("This activity has already been performed today. Try again tomorrow.");
            }
            else {
                System.out.println("This activity has already been performed this week.");
            }
            return;
        }

        // Checks if the player has enough power
        if(Game.getInstance().getPower() < activity.getPowerCost()){
            String message = String.format("Insufficient power. This activity requires %d power.", activity.getPowerCost());
            System.out.println(message);
            return;
        }

        // Checks whether an Item inside inventory references the activity. (Might be changed later).
        boolean hasRequiredItem = false;
        for(int i = 0; i < getInventory().size(); i++){
            if(getInventory().get(i).getApplianceReference() == applianceInUse){
                item = getInventory().get(i);
                hasRequiredItem = true;
                break;
            }
        }
        if(!hasRequiredItem){
            System.out.println("An item is needed to perform this activity.");
            return;
        }

        // ACTIVITY IS COMPLETED SUCCESSFULLY
        Game.getInstance().addPoints(activity.getSuccessPoints()); // Add success points.
        Game.getInstance().removePower(activity.getPowerCost()); // Remove used power.
        activity.setActivityDone();
        getInventory().remove(item);

        String message = String.format("%d points has been added.", activity.getSuccessPoints());
        System.out.println(message);

        message = String.format("You have %d power left for the day.", Game.getInstance().getPower());
        System.out.println(message);
    }
    public void addItemToInventory(Item item) {
        inventoryReference.addItem(item);
    }

    public void removeItemFromInventory(Item item) {
        inventoryReference.removeItem(item);
    }

    public Room getCurrentRoom() {
        return Game.getInstance().getCurrentRoom();
    }

    public ArrayList<Item> getInventory() {
        return inventoryReference.getInventoryList();
    }
}
