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
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(delimiter);
                ArrayList<String> lineTokens = new ArrayList<>(Arrays.asList(elements));
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
    @DisplayName("Test Activity - Constructor and getter methods")
    public void testConstructorAndGetter() {
        ArrayList<ArrayList<String>> inputFile = this.loadFile("Test/resources/Classes/ActivityTest.csv");
        ArrayList<ArrayList<String>> expectedOutputFile = this.loadFile("Test/resources/Classes/ActivityTest_expected.csv");

        for (int i = 0; i < inputFile.size(); i++) {
            ArrayList<String> inputArgs = inputFile.get(i);
            ArrayList<String> exptectedOutputArgs = expectedOutputFile.get(i);
            try {
                Activity activity = new Activity(inputArgs.get(0), inputArgs.get(1), Boolean.parseBoolean(inputArgs.get(2)), inputArgs.get(3), Integer.parseInt(inputArgs.get(4)), Integer.parseInt(inputArgs.get(5)), Integer.parseInt(inputArgs.get(6)));

                assertEquals(exptectedOutputArgs.get(0), activity.getId());
                assertEquals(exptectedOutputArgs.get(1), activity.getDisplayName());
                assertEquals(exptectedOutputArgs.get(2), activity.getItemId());
                assertEquals(Boolean.parseBoolean(exptectedOutputArgs.get(3)), activity.isDaily());
                assertEquals(Integer.parseInt(exptectedOutputArgs.get(4)), activity.getSuccessPoints());
                assertEquals(Integer.parseInt(exptectedOutputArgs.get(5)), activity.getFailurePoints());
                assertEquals(Integer.parseInt(exptectedOutputArgs.get(6)), activity.getPowerCost());
                assertFalse(activity.isDone());
                assertEquals(0, activity.getFailedCounter());
                assertEquals(0, activity.getSuccessCounter());
            } catch (IllegalArgumentException exception) {
                //switch statement to check if we get the expected error.
                switch (exception.getMessage()) {
                    case "successPoints cannot be negative":
                        assertEquals(exptectedOutputArgs.get(4), exception.getMessage());
                        break;
                    case "failurePoints cannot be negative":
                        assertEquals(exptectedOutputArgs.get(5), exception.getMessage());
                        break;
                    case "powerCost cannot be negative":
                        assertEquals(exptectedOutputArgs.get(6), exception.getMessage());
                        break;
                    default:
                        throw exception;
                }
            }
        }
    }


    @Test
    @DisplayName("Test Activity - calculateResultingPoints")
    public void testCalculateResultingPoints() {
        Activity activity1 = new Activity("test", "displaytest", false, "test", 5, 10, 15);
        assertEquals(activity1.calculateResultingPoints(), 0);

        activity1.setAsDone();

        assertEquals(activity1.calculateResultingPoints(), activity1.getSuccessPoints());
    }


    @Test
    @DisplayName("Test Activity - Settle")
    public void testSettle() {
        Activity activity1 = new Activity("test", "displaytest", false, "test", 5, 10, 15);
        Activity activity2 = new Activity("test", "displaytest", false, "test", 5, 10, 15);

        activity2.setAsDone();

        assertEquals(activity1.settle(), 1);
        assertEquals(activity2.settle(), 0);
    }


    @Test
    @DisplayName("Test Activity - HasAlias")
    public void testHasAlias() {
        Activity activity1 = new Activity("test", "displaytest", false, "test", 5, 10, 15);
        Activity activity2 = new Activity("test", "displaytest", false, "test", 5, 10, 15);
        Activity activity3 = new Activity("testtest", "displaytesttest", false, "test", 5, 10, 15);

        assertTrue(activity1.hasAlias(activity2.getId()));
        assertFalse(activity1.hasAlias(activity3.getId()));
    }


    @Test
    @DisplayName("Test Activity - ToString")
    public void testToString() {
        Activity activity1 = new Activity("test", "displaytest", false, "test", 5, 10, 15);

        assertEquals(activity1.toString(), "'displaytest' power=15 not done");

        activity1.setAsDone();

        assertEquals(activity1.toString(), "'displaytest' power=15 done");
    }
}