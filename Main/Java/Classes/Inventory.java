package worldOfZuul.Main.Java.Classes;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> listOfItems; // Dynamic array to hold items within an inventory list.

    public Inventory() {
        listOfItems = new ArrayList<>();
    }

    public ArrayList<Item> getInventoryList() {
        return listOfItems;
    }

    public void addItem(Item item) {
        listOfItems.add(item);
    }

    public void removeItem(Item item) {
        listOfItems.remove(item);
    }

    public void printItems() {
        if (listOfItems.isEmpty()) {
            System.out.println("Your inventory is currently empty!");
            
        }
        else {
            System.out.println("Your inventory currently contains: ");
            for (Item item : listOfItems) {
                System.out.println(item.getItemName());
            }
        }
    }
}
