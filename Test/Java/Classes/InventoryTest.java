package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Inventory;
import worldOfZuul.Main.Java.Classes.Item;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    @DisplayName("Inventory test - Constructor")
    public void testConstructor() {
        Inventory inventory = new Inventory();
        assertEquals("Nothing", inventory.toString());

        inventory.add(new Item("testID", "testDisplayname"));
        assertEquals(" - testDisplayname\n", inventory.toString());
    }
}