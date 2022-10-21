package worldOfZuul.Main.Java.Classes;

public class ActivityManager extends Game {

    public Activity[] listOfActivities;

    // Constructor WITH a list as input
    public ActivityManager(Activity[] listOfActivities) {
        this.listOfActivities = listOfActivities;
    }

    // Constructor WITHOUT a list as input
    public ActivityManager() {
        this(new Activity[0]);
    }

    public void addActivity(Activity activity) {
        Activity[] _listOfActivities = new Activity[listOfActivities.length + 1];
        for (int i = 0; i < (listOfActivities.length + 1); i++) {
            if (i == listOfActivities.length) {
                _listOfActivities[i] = activity;
            } else {
                _listOfActivities[i] = listOfActivities[i];
            }
        }

        listOfActivities = _listOfActivities.clone();
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
