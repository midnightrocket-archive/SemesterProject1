package worldOfZuul.Main.Java.Classes;

import worldOfZuul.Main.Java.Command;
import worldOfZuul.Main.Java.Room;
import worldOfZuul.Main.Java.Game;

import java.util.ArrayList;

public class Player {
    Room currentRoom;
    private Inventory inventory;
    private String playerName;


    public Player(String playerName) {
        this.playerName = playerName;
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

    public String getPlayerName() {
        return this.playerName;
    }
}