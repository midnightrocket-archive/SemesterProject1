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
    @DisplayName("Test Room - Construction")
    void construction() {
        kitchen = new Room("kitchen");
        turnOff = new Activity(1, 1, 1, true);
    }
    @Test
    @DisplayName("Test Room - createAppliance")
    void createAppliance() {
        Appliance fridge = kitchen.createAppliance("fridge", turnOff);
        assertInstanceOf(Appliance.class, fridge);
    }

    @Test
    @DisplayName("Test Room - getAppliance")
    void getAppliances() {
        createAppliance();
        System.out.println(kitchen.getAppliances());
    }
}