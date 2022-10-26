package worldOfZuul.Main.Java.Classes;

import worldOfZuul.Main.Java.Game;
import java.util.ArrayList;

public class ActivityManager extends Game {

    public ArrayList<Activity> listOfActivities;

    // Constructor WITH a list as input
    public ActivityManager(ArrayList<Activity> listOfActivities) {
        super();
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
        points += activity.getSuccessPoints();
    }

    public void removePoints(Activity activity) {
        points -= activity.getFailurePoints();
    }

    public void removePower(Activity activity) {
        power -= activity.getPowerCost();
    }
}
