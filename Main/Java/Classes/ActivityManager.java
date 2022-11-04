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

    public void removeActivity(Activity activity) {
        listOfActivities.remove(activity);
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
        if (this.listOfActivities.isEmpty()) {
            return "There are no more activities.";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Activity activity : this.listOfActivities) {
            stringBuilder.append(String.format(" - %s\n", activity.getName()));
        }

        return stringBuilder.toString();
    }
}