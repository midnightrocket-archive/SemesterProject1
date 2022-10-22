package worldOfZuul.Main.Java.Classes;

public class Activity {
    private final int successPoints; // Positive number for number of points to receive when completing the activity.
    private final int failurePoints; // Positive number to subtract from points when not completing or failing the activity.
    private final int powerCost; // Amount of power required to complete the activity.
    private final boolean daily; // Determines if the task is daily (Daily=true, non-daily=false)
    private boolean activityDone; // False if activity is not done, true if activity is done.


    public Activity(int successPoints, int failurePoints, int powerCost, boolean daily) {

        // Math.max returns the highest of 2 numbers.
        // In this example it returns the variable if it's higher than 0.
        this.successPoints = Math.max(successPoints, 0);
        this.failurePoints = Math.max(failurePoints, 0);
        this.powerCost = Math.max(powerCost, 0);

        this.daily = daily;
    }

    public void setActivityDone() {
        activityDone = true;
    }

    public int getSuccessPoints() {
        return successPoints;
    }

    public int getFailurePoints() {
        return failurePoints;
    }

    public int getPowerCost() {
        return powerCost;
    }

    public boolean isDaily() {
        return daily;
    }

    public boolean isActivityDone() {
        return activityDone;
    }
}