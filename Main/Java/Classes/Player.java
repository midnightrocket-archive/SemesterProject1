package worldOfZuul.Main.Java.Classes;

import worldOfZuul.Main.Java.Command;
import worldOfZuul.Main.Java.Room;
import worldOfZuul.Main.Java.Game;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private Inventory inventory = new Inventory();


    public Player(String playerName) {
        this.playerName = playerName;
    }

    public boolean addItem(Item item) {
        return this.inventory.add(item);
    }

    public boolean hasItem(String string) {
        return this.inventory.containsByAlias(string);
    }

    public boolean hasItem(Item item) {
        return this.inventory.contains(item);
    }

    public boolean removeItem(String string) {
        return this.inventory.removeByAlias(string);
    }

    public boolean removeItem(Item item) {
        return this.inventory.remove(item);
    }

    public Item getItem(String alias) {
        return this.inventory.getByAlias(alias);
    }

    public String inventoryToString() {
        return this.inventory.toString();
    }


    public String getPlayerName() {
        return this.playerName;
    }
}