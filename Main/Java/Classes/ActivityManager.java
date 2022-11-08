package worldOfZuul.Main.Java.Classes;

import worldOfZuul.Main.Java.Game;

import java.util.ArrayList;

public class ActivityManager {

    private static ActivityManager instance;
    public ArrayList<Activity> listOfActivities;

    private ActivityManager(ArrayList<Activity> listOfActivities) {
        this.listOfActivities = listOfActivities;
    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager(new ArrayList<>());
        }

        return instance;
    }

    public void addActivity(Activity activity) {
        listOfActivities.add(activity);
    }

    public void removeActivity(Activity activity) {
        listOfActivities.remove(activity);
    }

    public void clearActivities() {
        listOfActivities.clear();
    }

    public void addPoints(Activity activity) {
        Game.getInstance().addPoints(activity.getSuccessPoints());
    }

    public void removePoints(Activity activity) {
        Game.getInstance().removePoints(activity.getFailurePoints());
    }

    public void removePower(Activity activity) {
        Game.getInstance().removePower(activity.getPowerCost());
    }

    @Override
    public String toString() {
        String activityManagerString = "";

        if (listOfActivities.isEmpty()) {
            return "There are no more activities.";
        }

        for (Activity activity : listOfActivities) {
            activityManagerString += " - " + activity.getName() + "\n";
        }

        return activityManagerString;
    }
}
