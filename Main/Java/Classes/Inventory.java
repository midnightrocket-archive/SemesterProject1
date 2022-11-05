package worldOfZuul.Main.Java.Classes;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> listOfItems; // Dynamic array to hold items within an inventory list.

    public Inventory() {
        this.listOfItems = new ArrayList<>();
    }

    public boolean add(Item item) {
        return this.listOfItems.add(item);
    }

    public boolean remove(Item item) {
        return this.listOfItems.remove(item);
    }

    public ArrayList<Item> getInventoryList() {
        return this.listOfItems;
    }

    public boolean isEmpty() {
        // Returns true, if the listOfItems is empty.
        return this.listOfItems.isEmpty();
    }

    @Override
    public String toString() {
        if (this.listOfItems.isEmpty()) {
            return "Your inventory is empty.";
        }

        StringBuilder inventoryString = new StringBuilder();

        for (Item item : this.listOfItems) {
            inventoryString.append(String.format(" - %s\n", item.displayName()));
        }

        return inventoryString.toString();
    }
}