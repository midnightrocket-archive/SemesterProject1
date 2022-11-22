package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Classes.ActivityContainer;
import worldOfZuul.Main.Java.Classes.ActivityManager;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ActivityManagerTest {

    ActivityManager activityManager;
    ActivityContainer activityContainer;
    Activity activity1;
    Activity activity2;

    @BeforeEach
    @DisplayName("Test ActivityManager - Setup")
    public void setup() {
        activityManager = new ActivityManager();
        activityContainer = new ActivityContainer();
        activity1 = new Activity("test", "displaytest", false, "itemtest", 5, 10, 15);
        activity2 = new Activity("test", "displaytest", true, "itemtest", 5, 10, 15);
    }


    @Test
    @DisplayName("Test ActivityManager - GetMissingDailyActivities")
    public void testConstructorAndGetter() {
        activityContainer.add(activity2);

        activityManager.add(activity1);
        activityManager.add(activity2);

        assertEquals(activityManager.getMissingDailyActivities().toString(), activityContainer.toString());
    }


    @Test
    @DisplayName("Test ActivityManager - GetMissingNoneDailyActivities")
    public void testGetMissingNoneDailyActivities() {
        activityContainer.add(activity1);

        activityManager.add(activity1);
        activityManager.add(activity2);

        assertEquals(activityManager.getMissingNoneDailyActivities().toString(), activityContainer.toString());
    }


    @Test
    @DisplayName("Test ActivityManager - GetAllMissingActivities")
    public void testGetAllMissingActivities() {
        activityContainer.add(activity1);
        activityContainer.add(activity2);

        activityManager.add(activity1);
        activityManager.add(activity2);

        assertEquals(activityManager.getAllMissingActivities().toString(), activityContainer.toString());

        // Also test on removing:

        activityContainer.remove(activity1);
        activityContainer.remove(activity2);
        activityManager.remove(activity1);
        activityManager.remove(activity2);

        assertEquals(activityManager.getAllMissingActivities().toString(), activityContainer.toString());
    }


    @Test
    @DisplayName("Test ActivityManager - GetDailyPowerRequirement")
    public void testGetDailyPowerRequirement() {
        assertEquals(activityManager.getDailyPowerRequirement(), 0);

        activityManager.add(activity2);

        assertEquals(activityManager.getDailyPowerRequirement(), 15);
    }


    @Test
    @DisplayName("Test ActivityManager - GetExtraPowerLevels")
    public void testGetExtraPowerLevels() {
        activityManager.add(activity1);
        activityManager.add(activity1);

        assertEquals(activityManager.getExtraPowerLevels().toString(), Arrays.toString(new int[]{15, 15}));
    }



    /*
    ActivityManager activityManager;

    @BeforeEach
    @DisplayName("Test ActivityManager - setup and addActivity")
    public void setup() {
        activityManager = new ActivityManager();
        Activity activity1 = new Activity("test", 5, 5, 5, false);
        Activity activity2 = new Activity("test", 2, 7, 1, true);
        Activity activity3 = new Activity("test", 0, 32, 48, false);

        activityManager.addActivity(activity1);
        activityManager.addActivity(activity2);
        activityManager.addActivity(activity3);

        assertEquals(activityManager.listOfActivities.get(1), activity2);
    }

    @Test
    @DisplayName("Test ActivityManager - isActivityDone")
    public void isActivityDone() {
        assertFalse(activityManager.listOfActivities.get(0).isDone());
        assertFalse(activityManager.listOfActivities.get(1).isDone());
        assertFalse(activityManager.listOfActivities.get(2).isDone());

        activityManager.listOfActivities.get(1).setAsDone();

        assertFalse(activityManager.listOfActivities.get(0).isDone());
        assertTrue(activityManager.listOfActivities.get(1).isDone());
        assertFalse(activityManager.listOfActivities.get(2).isDone());
    }

    @Test
    @DisplayName("Test ActivityManager - addPoints")
    public void testAddPoints() {
        Game.getInstance().setPoints(0);
        assertEquals(Game.getInstance().getPoints(), 0);

        activityManager.addPoints(new Activity("test", 5, 7, 3, false));

        assertEquals(Game.getInstance().getPoints(), 5);
    }

    @Test
    @DisplayName("Test ActivityManager - removePoints")
    public void testRemovePoints() {
        Game.getInstance().setPoints(0);
        assertEquals(Game.getInstance().getPoints(), 0);

        activityManager.removePoints(new Activity("test", 5, 7, 3, false));

        assertEquals(Game.getInstance().getPoints(), -7);
    }

    @Test
    @DisplayName("Test ActivityManager - removePower")
    public void testRemovePower() {
        Game.getInstance().setPower(10);
        assertEquals(Game.getInstance().getPower(), 10);

        activityManager.removePower(new Activity("test", 5, 7, 3, false));

        assertEquals(Game.getInstance().getPower(), 7);
    }

    @Test
    @DisplayName("Test ActivityManager - toString")
    public void testToString() {
        System.out.println(activityManager);

        // This code tests the toString method, when there are no activity's.
        ActivityManager testActivityManager = new ActivityManager();
        System.out.println(testActivityManager);
    }*/
}