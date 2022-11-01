package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Classes.ActivityManager;
import worldOfZuul.Main.Java.Game;

import static org.junit.jupiter.api.Assertions.*;

class ActivityManagerTest {

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
        assertFalse(activityManager.listOfActivities.get(0).isActivityDone());
        assertFalse(activityManager.listOfActivities.get(1).isActivityDone());
        assertFalse(activityManager.listOfActivities.get(2).isActivityDone());

        activityManager.listOfActivities.get(1).setActivityDone();

        assertFalse(activityManager.listOfActivities.get(0).isActivityDone());
        assertTrue(activityManager.listOfActivities.get(1).isActivityDone());
        assertFalse(activityManager.listOfActivities.get(2).isActivityDone());
    }

    @Test
    @DisplayName("Test ActivityManager - addPoints")
    public void testAddPoints() {
        Game.points = 0;
        assertEquals(Game.points, 0);

        activityManager.addPoints(new Activity("test", 5, 7, 3, false));

        assertEquals(Game.points, 5);
    }

    @Test
    @DisplayName("Test ActivityManager - removePoints")
    public void testRemovePoints() {
        Game.points = 0;
        assertEquals(Game.points, 0);

        activityManager.removePoints(new Activity("test", 5, 7, 3, false));

        assertEquals(Game.points, -7);
    }

    @Test
    @DisplayName("Test ActivityManager - removePower")
    public void testRemovePower() {
        Game.power = 10;
        assertEquals(Game.power, 10);

        activityManager.removePower(new Activity("test", 5, 7, 3, false));

        assertEquals(Game.power, 7);
    }

    @Test
    @DisplayName("Test ActivityManager - toString")
    public void testToString() {
        System.out.println(activityManager);

        // This code tests the toString method, when there are no activity's.
        ActivityManager testActivityManager = new ActivityManager();
        System.out.println(testActivityManager);
    }
}