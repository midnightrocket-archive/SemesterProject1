package worldOfZuul.Test.Java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Classes.Appliance;
import worldOfZuul.Main.Java.Classes.Item;
import worldOfZuul.Main.Java.Room;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    Room kitchen;
    Room hallway;
    Activity turnOff;

    @BeforeEach
    @DisplayName("Test Room - Setup")
    void setup() {
        kitchen = new Room("kitchen");
        hallway = new Room("hallway");
        turnOff = new Activity("test", 1, 1, 1, true);

    }
    
    @Test
    @DisplayName("Test Room - CreateAppliance")
    Appliance testCreateAppliance() {
        Appliance fridge = kitchen.createAppliance("fridge", turnOff);
        assertInstanceOf(Appliance.class, fridge);
        return fridge;
    }

    @Test
    @DisplayName("Test Room - GetShortDecription")
    public void testGetShortDescription() {
        System.out.println(kitchen.getShortDescription());
    }

    @Test
    @DisplayName("Test Room - GetAppliancesString")
    void testGetAppliancesString() {
        testCreateAppliance();
        System.out.println(kitchen.getAppliancesString());

        // Test without any appliances
        System.out.println(hallway.getAppliancesString());
    }

    @Test
    @DisplayName("Test Room - HasAppliance")
    void testHasAppliance() {
        assertFalse(kitchen.hasAppliance("fridge"));
        testCreateAppliance();
        assertTrue(kitchen.hasAppliance("fridge"));
    }

    @Test
    @DisplayName("Test Room - GetApplianceActivity")
    void testGetApplianceActivity() {
        Appliance fridge = testCreateAppliance();
        assertEquals(kitchen.getApplianceActivity(fridge), turnOff);
    }

    @Test
    @DisplayName("Test Room - CreateItem")
    Item testCreateItem() {
        Item food = kitchen.createItem("food", kitchen.getAppliance("fridge"));
        assertInstanceOf(Item.class, food);
        return food;
    }

    @Test
    @DisplayName("Test Room - HasItem")
    void testHasItem() {
        testCreateItem();
        assertTrue(kitchen.hasItem("food"));
    }

    @Test
    @DisplayName("Test Room - GetItem")
    void testGetItem() {
        testCreateItem();
        assertInstanceOf(Item.class, kitchen.getItem("food"));
    }

    @Test
    @DisplayName("Test Room - RemoveItem")
    void testRemoveItem() {
        testCreateItem();
        assertTrue(kitchen.hasItem("food"));
        kitchen.removeItem("food");
        assertFalse(kitchen.hasItem("food"));
    }
}
