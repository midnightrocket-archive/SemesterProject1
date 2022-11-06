package worldOfZuul.Main.Java.Classes;

import java.util.ArrayList;

public class ActivityManager {
    private ReferenceContainer<Activity> noneDailyActivities;

    private ReferenceContainer<Activity> dailyActivities;


    private static void prettyFormatActivityList(StringBuilder stringBuilder, ReferenceContainer<Activity> container) {
        for (Activity activity : container) {
            stringBuilder.append(String.format(" - %s \t %s\n", activity.getDisplayName(), activity.doneToString()));
        }
    }


    // Constructor
    public ActivityManager() {
        this.noneDailyActivities = new ReferenceContainer<Activity>();
        this.dailyActivities = new ReferenceContainer<Activity>();
    }


    public void add(Activity activity) {
        if (activity.isDaily()) {
            this.dailyActivities.add(activity);
        } else {
            this.noneDailyActivities.add(activity);
        }
    }

    public void remove(Activity activity) {
        if (activity.isDaily()) {
            this.dailyActivities.remove(activity);
        } else {
            this.noneDailyActivities.remove(activity);
        }
    }

    public void resetAllDailyActivities() {
        for (Activity activity : this.dailyActivities) {
            activity.setDoneState(false);
        }
    }

    public boolean areAllDone() {
        for (Activity activity : this.getAllActivities()) {
            if (!activity.isDone()) return false;
        }
        return true;
    }

    public ReferenceContainer<Activity> getMissingDailyActivities() {
        ReferenceContainer<Activity> output = new ReferenceContainer<Activity>();
        for (Activity activity : this.dailyActivities) {
            if (!activity.isDone()) {
                output.add(activity);
            }
        }
        return output;
    }

    public ReferenceContainer<Activity> getMissingNoneDailyActivities() {
        ReferenceContainer<Activity> output = new ReferenceContainer<Activity>();
        for (Activity activity : this.noneDailyActivities) {
            if (!activity.isDone()) {
                output.add(activity);
            }
        }
        return output;
    }

    public ReferenceContainer<Activity> getAllMissingActivities() {
        ReferenceContainer<Activity> output = new ReferenceContainer<Activity>();
        output.addAll(this.getMissingDailyActivities());
        output.addAll(this.getMissingNoneDailyActivities());
        return output;
    }


    public int getDailyPowerRequirement() {
        int total = 0;
        for (Activity activity : this.dailyActivities) {
            total += activity.getPowerCost();
        }
        return total;
    }

    public ArrayList<Integer> getExtraPowerLevels() {
        ArrayList<Integer> extra = new ArrayList<Integer>();
        for (Activity activity : this.noneDailyActivities) {
            extra.add(activity.getPowerCost());
        }
        return extra;
    }


    public ReferenceContainer<Activity> getAllActivities() {
        ReferenceContainer<Activity> container = new ReferenceContainer<Activity>();
        container.addAll(this.dailyActivities);
        container.addAll(this.noneDailyActivities);
        return container;
    }

    public String dailyActivitiesToString() {
        if (this.dailyActivities.isEmpty()) {
            return "There are no more daily activities.";
        }

        StringBuilder stringBuilder = new StringBuilder("Your daily activities are:\n");

        ActivityManager.prettyFormatActivityList(stringBuilder, this.dailyActivities);

        return stringBuilder.toString();
    }

    public String noneDailyActivitiesToString() {
        if (this.noneDailyActivities.isEmpty()) {
            return "There are no additional activities.";
        }

        StringBuilder stringBuilder = new StringBuilder("Your additional activities are:\n");

        ActivityManager.prettyFormatActivityList(stringBuilder, this.noneDailyActivities);

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        if (this.getAllActivities().isEmpty()) {
            return "There are no more activities.";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.dailyActivitiesToString());
        stringBuilder.append("\n");
        stringBuilder.append(this.noneDailyActivitiesToString());

        return stringBuilder.toString();
    }
}