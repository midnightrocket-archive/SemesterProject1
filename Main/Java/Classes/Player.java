package worldOfZuul.Main.Java.Classes;

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
