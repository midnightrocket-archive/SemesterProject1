package worldOfZuul.Main.Java.Classes;

import java.util.ArrayList;

public class ActivityManager {
    private ActivityContainer noneDailyActivities;

    private ActivityContainer dailyActivities;


    // Constructor
    public ActivityManager() {
        this.noneDailyActivities = new ActivityContainer();
        this.dailyActivities = new ActivityContainer();
    }

    public int calculatePoints() {
        return this.getAllActivities().calculatePoints();
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


    private void settleAllActivities() {
        this.getAllActivities().settleAll();
    }

    // Settle daily activities. should be done at the end of day.
    public void settleDailyActivities() {
        this.dailyActivities.settleAll();
    }

    public boolean areAllDone() {
        return this.getAllActivities().areAllDone();
    }

    public boolean areAllDailyDone() {
        return this.dailyActivities.areAllDone();
    }

    public ActivityContainer getMissingDailyActivities() {
        return this.dailyActivities.getMissingActivities();
    }

    public ActivityContainer getMissingNoneDailyActivities() {
        return this.noneDailyActivities.getMissingActivities();
    }

    public ActivityContainer getAllMissingActivities() {
        return this.getAllActivities().getMissingActivities();
    }


    public int getDailyPowerRequirement() {
        return this.dailyActivities.calculatePowerRequirements();
    }

    public ArrayList<Integer> getExtraPowerLevels() {
        return this.noneDailyActivities.getPowerLevels();
    }


    public ActivityContainer getAllActivities() {
        ActivityContainer container = new ActivityContainer();
        container.addAll(this.dailyActivities);
        container.addAll(this.noneDailyActivities);
        return container;
    }

    public ActivityContainer getAllDailyActivities() {
        return this.dailyActivities;
    }

    public ActivityContainer getAllNonDailyActivities() {
        return this.noneDailyActivities;
    }

    public String dailyActivitiesToString() {
        if (this.dailyActivities.isEmpty()) {
            return "There are no daily activities.";
        }

        StringBuilder stringBuilder = new StringBuilder("Your daily activities are:\n");

        stringBuilder.append(this.dailyActivities.toString());

        return stringBuilder.toString();
    }

    public String noneDailyActivitiesToString() {
        if (this.noneDailyActivities.isEmpty()) {
            return "There are no additional activities.";
        }

        StringBuilder stringBuilder = new StringBuilder("Your additional activities are:\n");

        stringBuilder.append(this.noneDailyActivities.toString());

        return stringBuilder.toString();
    }

    public String missedDailyActivitiesToString() {
        if (this.areAllDailyDone()) {
            return "You finished all your daily activities.\nBut are keeping up with your other activities?";
        }

        StringBuilder stringBuilder = new StringBuilder("You missed these daily activities:\n");

        stringBuilder.append(this.getMissingDailyActivities().toString());

        return stringBuilder.toString();
    }

    public String generateStatsForDaily() {
        if (this.dailyActivities.isEmpty()) {
            return "There were no daily activities.";
        }

        StringBuilder stringBuilder = new StringBuilder("Your daily activities were:\n");

        stringBuilder.append(this.dailyActivities.generateStats());

        return stringBuilder.toString();
    }

    public String generateStatsForNoneDaily() {
        if (this.noneDailyActivities.isEmpty()) {
            return "There were no additional activities.";
        }

        StringBuilder stringBuilder = new StringBuilder("Your additional activities were:\n");

        stringBuilder.append(this.noneDailyActivities.generateStats());

        return stringBuilder.toString();
    }
    // For generating stats at end of game.
    public String generateStats() {
        this.settleAllActivities(); // Settle all activities before generating stat.
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.generateStatsForDaily());
        stringBuilder.append("\n");
        stringBuilder.append(this.generateStatsForNoneDaily());

        return stringBuilder.toString();
    }
    @Override
    public String toString() {
        if (this.getAllActivities().isEmpty()) {
            return "There are no activities.";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.dailyActivitiesToString());
        stringBuilder.append("\n");
        stringBuilder.append(this.noneDailyActivitiesToString());

        return stringBuilder.toString();
    }
}