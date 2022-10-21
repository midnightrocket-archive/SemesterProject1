package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @Test
    @DisplayName("Test Activity - Constructor")
    public void testConstructor() {
        // Trys to open the files ActivityTest.csv and ActivityTest_expected.csv
        try (Scanner scanner_test_input = new Scanner(new File("Test/Java/Classes/ActivityTest.csv")); Scanner scanner_test_expected = new Scanner(new File("Test/Java/Classes/ActivityTest_expected.csv"))) {
            // Runs a loop as long as both files has another line.
            while (scanner_test_input.hasNextLine() && scanner_test_expected.hasNextLine()) {
                // Scans the next line and save it in a variable.
                String test_input_next_line = scanner_test_input.nextLine();
                String test_expected_next_line = scanner_test_expected.nextLine();

                // Splits the variable with the line
                String[] test_input = test_input_next_line.split(",");
                String[] expected_input = test_expected_next_line.split(",");

                // Uses the activity's constructor with the data from the CSV files.
                Activity activityTest = new Activity((int)Long.parseLong(test_input[0]), (int)Long.parseLong(test_input[1]), (int)Long.parseLong(test_input[2]), false);

                // Checks if the values match with the data from the expected data csv file.
                assertEquals(activityTest.getSuccessPoints(), Integer.parseInt(expected_input[0]));
                assertEquals(activityTest.getFailurePoints(), Integer.parseInt(expected_input[1]));
                assertEquals(activityTest.getPowerCost(), Integer.parseInt(expected_input[2]));
            }
        } catch (IOException ignored) {
            // This catch block does not contain anything, because
        }
    }

    @Test
    @DisplayName("Test Activity - isDaily Getter")
    public void testIsDaily() {
        // Creates 2 constructors, one with daily as false and one with daily as true.
        Activity activityTest1 = new Activity(-5, 5, -5, false);
        Activity activityTest2 = new Activity(-5, 5, -5, true);

        // Checks if the 2 instances' getter-functions work.
        assertFalse(activityTest1.isDaily());
        assertTrue(activityTest2.isDaily());
    }

    @Test
    @DisplayName("Test Activity - setActivityDone")
    public void testSetActivityDone() {
        // Creates 2 constructors.
        Activity activityTest1 = new Activity(-5, 5, -5, false);
        Activity activityTest2 = new Activity(-5, 5, -5, true);

        // Checks if both activities are done. Expected is that both are NOT done.
        assertFalse(activityTest1.isActivityDone());
        assertFalse(activityTest2.isActivityDone());

        // Sets activity 1 to done.
        activityTest1.setActivityDone();

        // Checks if both activities are done.
        // Activity 1 is expected to be done. Activity 2 is not.
        assertTrue(activityTest1.isActivityDone());
        assertFalse(activityTest2.isActivityDone());
    }
}