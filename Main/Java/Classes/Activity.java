package worldOfZuul.Main.Java.Classes;

public class Activity {
    private String name;
    private int successPoints; // Positive number for number of points to receive when completing the activity.
    private int failurePoints; // Positive number to subtract from points when not completing or failing the activity.
    private int powerCost; // Amount of power required to complete the activity.
    private final boolean daily; // Determines if the task is daily (Daily=true, non-daily=false)
    private boolean activityDone; // False if activity is not done, true if activity is done.

    public Activity(String name, int successPoints, int failurePoints, int powerCost, boolean daily) {

        this.name = name;

        this.setSuccessPoints(successPoints);
        this.setFailurePoints(failurePoints);
        this.setPowerCost(powerCost);

        this.daily = daily;
    }

    public void setActivityDone() {
        activityDone = true;
    }

    public int getSuccessPoints() {
        return successPoints;
    }

    private void setSuccessPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("successPoints cannot be negative");
        } else {
            this.successPoints = points;
        }
    }

    public int getFailurePoints() {
        return failurePoints;
    }

    private void setFailurePoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("failurePoints cannot be negative");
        } else {
            this.failurePoints = points;
        }
    }

    public int getPowerCost() {
        return powerCost;
    }

    private void setPowerCost(int powerCost) {
        if (powerCost < 0) {
            throw new IllegalArgumentException("powerCost cannot be negative");
        } else {
            this.powerCost = powerCost;
        }
    }

    public boolean isDaily() {
        return this.daily;
    }

    public boolean isActivityDone() {
        return this.activityDone;
    }

    public String getName() {
        return name;
    }
}