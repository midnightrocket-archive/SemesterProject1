package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Classes.ActivityManager;
import worldOfZuul.Main.Java.Classes.Game;

import static org.junit.jupiter.api.Assertions.*;

class ActivityManagerTest {

    ActivityManager activityManager;

    @BeforeEach
    @DisplayName("Test ActivityManager - setup and addActivity")
    public void setup() {
        activityManager = new ActivityManager();
        Activity activity1 = new Activity(5, 5, 5, false);
        Activity activity2 = new Activity(2, 7, 1, true);
        Activity activity3 = new Activity(0, 32, 48, false);

        activityManager.addActivity(activity1);
        activityManager.addActivity(activity2);
        activityManager.addActivity(activity3);

        assertEquals(activityManager.listOfActivities[1], activity2);
    }

    @Test
    @DisplayName("Test ActivityManager - isActivityDone")
    public void isActivityDone() {
        assertFalse(activityManager.listOfActivities[0].isActivityDone());
        assertFalse(activityManager.listOfActivities[1].isActivityDone());
        assertFalse(activityManager.listOfActivities[2].isActivityDone());

        activityManager.listOfActivities[1].setActivityDone();

        assertFalse(activityManager.listOfActivities[0].isActivityDone());
        assertTrue(activityManager.listOfActivities[1].isActivityDone());
        assertFalse(activityManager.listOfActivities[2].isActivityDone());
    }

    @Test
    @DisplayName("Test ActivityManager - addPoints")
    public void testAddPoints() {
        Game.points = 0;
        assertEquals(Game.points, 0);

        activityManager.addPoints(new Activity(5, 7, 3, false));

        assertEquals(Game.points, 5);
    }

    @Test
    @DisplayName("Test ActivityManager - removePoints")
    public void testRemovePoints() {
        Game.points = 0;
        assertEquals(Game.points, 0);

        activityManager.removePoints(new Activity(5, 7, 3, false));

        assertEquals(Game.points, -7);
    }

    @Test
    @DisplayName("Test ActivityManager - removePower")
    public void testRemovePower() {
        Game.power = 10;
        assertEquals(Game.power, 10);

        activityManager.removePower(new Activity(5, 7, 3, false));

        assertEquals(Game.power, 7);
    }
}