package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Classes.Appliance;

import static org.junit.jupiter.api.Assertions.*;

class ApplianceTest {

    @Test
    @DisplayName("Test Appliance - Constructor")
    public void testConstructor() {
        Activity activity = new Activity(5, 5, 5, false);
        Appliance appliance = new Appliance("Test 1", activity);

        assertEquals(appliance.getActivityReference(), activity);
        assertEquals(appliance.getName(), "Test 1");
    }
}