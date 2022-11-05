package worldOfZuul.Main.Java.Classes;

import java.util.ArrayList;

public class Inventory {
    private static Inventory instance;

    private ArrayList<Item> listOfItems; // Dynamic array to hold items within an inventory list.

    private Inventory() {
        listOfItems = new ArrayList<>();
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addItem(Item item) {
        listOfItems.add(item);
    }

    public void removeItem(Item item) {
        listOfItems.remove(item);
    }

    public void clearInventory() {
        listOfItems.clear();
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
        String inventoryString = "";

        if (listOfItems.isEmpty()) {
            return "Your inventory is empty.";
        }

        for (Item item : listOfItems) {
            inventoryString += " - " + item.getName() + "\n";
        }

        return inventoryString;
    }
}