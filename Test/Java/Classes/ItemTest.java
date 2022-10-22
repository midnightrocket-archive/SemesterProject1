package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Classes.Appliance;
import worldOfZuul.Main.Java.Classes.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    @DisplayName("Test Item - Constructor")
    public void testConstructor() {
        Activity activity = new Activity(5, 5, 5, false);
        Appliance appliance = new Appliance("Test 1", activity);
        Item item = new Item("Test 1", appliance);

        assertEquals(item.getApplianceReference(), appliance);
        assertEquals(item.getItemName(), "Test 1");
    }
}