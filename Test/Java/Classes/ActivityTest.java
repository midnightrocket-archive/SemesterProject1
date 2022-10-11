package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @Test
    @DisplayName("Test Activity - Negative numbers in constructor 1")
    public void testConstructor1() {
        Activity activityTest = new Activity(-5, -15, -15, false);

        assertEquals(activityTest.getSuccessPoints(), 0);
        assertEquals(activityTest.getFailurePoints(), 0);
        assertEquals(activityTest.getPowerCost(), 0);

    }

    @Test
    @DisplayName("Test Activity - Negative numbers in constructor 2")
    public void testConstructor2() {
        Activity activityTest = new Activity(12345678, -378532, -12345678, false);

        assertEquals(activityTest.getSuccessPoints(), 12345678);
        assertEquals(activityTest.getFailurePoints(), 0);
        assertEquals(activityTest.getPowerCost(), 0);

    }

    @Test
    @DisplayName("Test Activity - Negative numbers in constructor 3")
    public void testConstructor3() {
        Activity activityTest = new Activity(-0, -2147483648, 2147483647, false);

        assertEquals(activityTest.getSuccessPoints(), 0);
        assertEquals(activityTest.getFailurePoints(), 0);
        assertEquals(activityTest.getPowerCost(), 2147483647);

    }

    @Test
    @DisplayName("Test Activity - Is daily getter")
    public void testIsDaily() {
        Activity activityTest1 = new Activity(-5, 5, -5, false);
        Activity activityTest2 = new Activity(-5, 5, -5, true);

        assertFalse(activityTest1.isDaily());
        assertTrue(activityTest2.isDaily());
    }
}