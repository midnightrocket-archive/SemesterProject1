package worldOfZuul.Main.Java.Classes;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> listOfItems; // Dynamic array to hold items within an inventory list.

    public Inventory() {
        listOfItems = new ArrayList<>();
    }

    public void addItem(Item item) {
        listOfItems.add(item);
    }

    public void removeItem(Item item) {
        listOfItems.remove(item);
    }

    public ArrayList<Item> getInventoryList() {
        return listOfItems;
    }

    public boolean isEmpty() {
        // Returns true, if the listOfItems is empty.
        return listOfItems.isEmpty();
    }

    @Override
    public String toString() {
        if (listOfItems.isEmpty()) {
            return "Your inventory is empty.";
        }

        StringBuilder inventoryString = new StringBuilder();

        for (Item item : listOfItems) {
            inventoryString.append(String.format(" - %s\n", item.displayName()));
        }

        return inventoryString.toString();
    }
}