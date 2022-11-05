package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    /*
     * Helper function, to read the contents of the test files,
     * which contains the test input.
     */
    private ArrayList<ArrayList<String>> loadFile(String path, String delimiter) {
        File file = new File(path);
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(delimiter);
                ArrayList<String> lineTokens = new ArrayList<String>(Arrays.asList(elements));
                output.add(lineTokens);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return output;
    }

    private ArrayList<ArrayList<String>> loadFile(String path) {
        return this.loadFile(path, ",");
    }

    @Test
    @DisplayName("Test Activity - Constructor")
    public void testConstructor() {
        ArrayList<ArrayList<String>> testInput = this.loadFile("Test/Java/Classes/ActivityTest.csv");
        ArrayList<ArrayList<String>> expected = this.loadFile("Test/Java/Classes/ActivityTest_expected.csv");
        for (int i = 0; i < testInput.size(); i++) {
            ArrayList<String> testArgs = testInput.get(i);
            ArrayList<String> expectedArgs = expected.get(i);
            try {
                Activity activityTest = new Activity(testArgs.get(0), Integer.parseInt(testArgs.get(1)), Integer.parseInt(testArgs.get(2)), Integer.parseInt(testArgs.get(3)), Boolean.parseBoolean(testArgs.get(4)));

                assertEquals(expectedArgs.get(0), activityTest.getName());
                assertEquals(Integer.parseInt(expectedArgs.get(1)), activityTest.getSuccessPoints());
                assertEquals(Integer.parseInt(expectedArgs.get(2)), activityTest.getFailurePoints());
                assertEquals(Integer.parseInt(expectedArgs.get(3)), activityTest.getPowerCost());
                assertEquals(Boolean.parseBoolean(expectedArgs.get(4)), activityTest.isDaily());
            } catch (IllegalArgumentException exception) {

                //switch statement to check if we get the expected error.
                switch (exception.getMessage()) {
                    case "successPoints cannot be negative":
                        assertEquals(expectedArgs.get(1), exception.getMessage());
                        break;
                    case "failurePoints cannot be negative":
                        assertEquals(expectedArgs.get(2), exception.getMessage());
                        break;
                    case "powerCost cannot be negative":
                        assertEquals(expectedArgs.get(3), exception.getMessage());
                        break;
                    default:
                        throw exception;
                }
            }
        }
    }

    @Test
    @DisplayName("Test Activity - isDaily Getter")
    public void testIsDaily() {
        // Creates 2 constructors, one with daily as false and one with daily as true.
        Activity activityTest1 = new Activity("test", 1, 5, 5, false);
        Activity activityTest2 = new Activity("test", 1, 5, 5, true);

        // Checks if the 2 instances' getter-functions work.
        assertFalse(activityTest1.isDaily());
        assertTrue(activityTest2.isDaily());
    }

    @Test
    @DisplayName("Test Activity - setActivityDone")
    public void testSetActivityDone() {
        // Creates 2 constructors.
        Activity activityTest1 = new Activity("test", 1, 5, 5, false);
        Activity activityTest2 = new Activity("test", 1, 5, 5, true);

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