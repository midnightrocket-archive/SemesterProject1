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
    public boolean isEmpty() {
        if (listOfItems.isEmpty()) {
            return true;
        }
        else return false;
    }

    public ArrayList<Item> getInventoryList() {
        return listOfItems;
    }
    public String toString() {
        String inventoryString = new String();
        if (listOfItems.isEmpty()) {
            return "Nothing. Your inventory is empty!";
        } else {
            for (Item item : listOfItems) {
                inventoryString += item.getItemName() + "\n";
            }
            return inventoryString;
        }
    }
}
