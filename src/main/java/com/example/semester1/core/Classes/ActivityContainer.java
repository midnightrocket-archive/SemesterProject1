package com.example.semester1.core.Classes;


import java.util.ArrayList;

//package private class.
public class ActivityContainer extends ReferenceContainer<Activity> {

    public boolean areAllDone() {
        for (Activity activity : this) {
            if (!activity.isDone()) return false;
        }
        return true;
    }

    public ActivityContainer getMissingActivities() {
        ActivityContainer output = new ActivityContainer();
        for (Activity activity : this) {
            if (!activity.isDone()) {
                output.add(activity);
            }
        }
        return output;
    }

    public int calculatePowerRequirements() {
        int total = 0;
        for (Activity activity : this) {
            total += activity.getPowerCost();
        }
        return total;
    }

    public ArrayList<Integer> getPowerLevels() {
        ArrayList<Integer> powerLevels = new ArrayList<Integer>();
        for (Activity activity : this) {
            powerLevels.add(activity.getPowerCost());
        }
        return powerLevels;
    }

    public int calculatePoints() {
        int points = 0;
        for (Activity activity : this) {
            points += activity.calculateResultingPoints();
        }
        return points;
    }

    public void settleAll() {
        for (Activity activity : this) {
            activity.settle();
        }
    }

    public String generateStats() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Activity activity : this) {
            stringBuilder.append(String.format(" - %s \tYou finished it %d times. You missed it %d times\n", activity.getDisplayName(), activity.getSuccessCounter(), activity.getFailedCounter()));
        }
        return stringBuilder.toString();
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Activity activity : this) {
            stringBuilder.append(String.format(" - %s \t %s\n", activity.getDisplayName(), activity.doneToString()));
        }
        return stringBuilder.toString();
    }
}