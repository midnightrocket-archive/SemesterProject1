package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Classes.Appliance;
import worldOfZuul.Main.Java.Classes.Inventory;
import worldOfZuul.Main.Java.Classes.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    Activity activity;
    Appliance appliance;
    Item item1;
    Item item2;
    Inventory inventory;

    @BeforeEach
    @DisplayName("Test Inventory - Setup")
    public void setup() {
        activity = new Activity(5, 5, 5, false);
        appliance = new Appliance("Test appliance", activity);
        item1 = new Item("Test item1", appliance);
        item2 = new Item("Test item2", appliance);
        inventory = new Inventory();
    }

    @Test
    @DisplayName("Test Inventory - AddItem")
    public void testAddItem() {
        inventory.addItem(item1);
        inventory.addItem(item2);

        assertEquals(inventory.getInventoryList(), new ArrayList<>(Arrays.asList(item1, item2)));
    }

    @Test
    @DisplayName("Test Inventory - RemoveItem")
    public void testRemoveItem() {
        inventory.addItem(item1);
        inventory.addItem(item2);

        inventory.removeItem(item1);

        assertEquals(inventory.getInventoryList(), new ArrayList<>(Collections.singletonList(item2)));
    }

    @Test
    @DisplayName("Test Inventory - ToString")
    public void testToString() {
        System.out.println(inventory.toString());
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item2);
        System.out.println(inventory.toString());
    }

    @Test
    @DisplayName("Test Inventory - IsEmpty")
    public void testIsEmpty() {
        // System.out.println(inventory.isEmpty());
        assertEquals(inventory.isEmpty(), true);
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item2);
        assertEquals(inventory.isEmpty(), false);
    }
}
