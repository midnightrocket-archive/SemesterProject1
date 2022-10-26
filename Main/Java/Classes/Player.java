package worldOfZuul.Main.Java.Classes;

import worldOfZuul.Main.Java.Room;
import worldOfZuul.Main.Java.Game;

import java.util.ArrayList;

public class Player {

    Room currentRoom;
    Inventory inventory;

    public Player(Inventory inventory) {
        this.inventory = inventory;
        currentRoom = this.getCurrentRoom();
    }

    public Room getCurrentRoom() {
        return Game.getCurrentRoom();
    }

    public void addItemToInventory(Item item) {
        inventory.addItem(item);
    }

    public void removeItemFromInventory(Item item) {
        inventory.removeItem(item);
    }

    public ArrayList<Item> getInventory() {
        return inventory.getInventoryList();
    }
}
