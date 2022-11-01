package worldOfZuul.Main.Java.Classes;

import worldOfZuul.Main.Java.Game;
import java.util.ArrayList;

public class ActivityManager {

    public ArrayList<Activity> listOfActivities;

    // Constructor WITH a list as input
    public ActivityManager(ArrayList<Activity> listOfActivities) {
        this.listOfActivities = listOfActivities;
    }

    // Constructor WITHOUT a list as input
    public ActivityManager() {
        this(new ArrayList<>());
    }


    public void addActivity(Activity activity) {
        listOfActivities.add(activity);
    }


    public void addPoints(Activity activity) {
        Game.points += activity.getSuccessPoints();
    }

    public void removePoints(Activity activity) {
        Game.points -= activity.getFailurePoints();
    }

    public void removePower(Activity activity) {
        Game.power -= activity.getPowerCost();
    }

    @Override
    public String toString() {
        String activityManagerString = "";
        if (listOfActivities.isEmpty()) {
            return "There are no more activitys.";
        } else {
            for (Activity activity : listOfActivities) {
                activityManagerString += " - " + activity.getName() + "\n";
            }
            return activityManagerString;
        }
    }
}
