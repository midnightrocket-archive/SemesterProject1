package worldOfZuul.Test.Java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Classes.Appliance;
import worldOfZuul.Main.Java.Room;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    Room kitchen;
    Activity turnOff;

    @BeforeEach
    @DisplayName("Test Room - Constructor")
    void testConstructor() {
        kitchen = new Room("kitchen");
        turnOff = new Activity(1, 1, 1, true);
    }
    
    @Test
    @DisplayName("Test Room - CreateAppliance")
    Appliance testCreateAppliance() {
        Appliance fridge = kitchen.createAppliance("fridge", turnOff);
        assertInstanceOf(Appliance.class, fridge);
        return fridge;
    }

    @Test
    @DisplayName("Test Room - GetAppliance")
    void testGetAppliance() {
        testCreateAppliance();
        System.out.println(kitchen.getAppliancesString());
    }

    @Test
    @DisplayName("Test Room - HasAppliance")
    void testHasAppliance() {
        assertEquals(kitchen.hasAppliance("fridge"), false);
        testCreateAppliance();
        assertEquals(kitchen.hasAppliance("fridge"), true);
    }

    @Test
    @DisplayName("Test Room - GetApplianceActivity")
    void testGetApplianceActivity() {
        Appliance fridge = testCreateAppliance();
        assertEquals(kitchen.getApplianceActivity(fridge), turnOff);
    }
}
