package com.example.semester1.core.Classes;


public class Activity extends AliasableImplementation {
    private final String itemId;
    private final boolean daily; // Determines if the task is daily (Daily=true, non-daily=false)
    private int successPoints; // Positive number for number of points to receive when completing the activity.
    private int failurePoints; // Positive number to subtract from points when not completing or failing the activity.
    private int powerCost; // Amount of power required to complete the activity.
    private boolean done = false; // False if activity is not done, true if activity is done.

    private int failedCounter = 0; // This is used to keep track of how many times an activity has been failed.
    private int successCounter = 0;

    public Activity(String id, String displayName, boolean daily, String itemId, int successPoints, int failurePoints, int powerCost) {
        super(id, displayName);
        this.daily = daily;
        this.itemId = itemId;

        // successPoint, failurePoint and powerCost uses another method, which makes sure that all the values non-negative.
        this.setSuccessPoints(successPoints);
        this.setFailurePoints(failurePoints);
        this.setPowerCost(powerCost);
    }

    public int calculateResultingPoints() {
        int totalSuccessPoints =  this.successCounter * this.successPoints;
        int totalFailurePoints = this.failedCounter * this.failurePoints;
        return totalSuccessPoints - totalFailurePoints;
    }

    public boolean isDaily() {
        return this.daily;
    }

    public boolean isDone() {
        return this.done;
    }

    public int setAsDone() {
        if (!this.done) {
            this.successCounter++;
            this.done = true;
        }
        return this.successCounter;
    }

    // Settle, accounts for missing activities, and resets its done state back to false. Ready for stats or next day
    public int settle() {
        if (!this.done) {
            this.failedCounter++;
        }
        this.done = false;
        return this.failedCounter;
    }

    public String doneToString() {
        if (this.done) {
            return "done";
        } else {
            return "not done";
        }
    }


    public int getSuccessPoints() {
        return this.successPoints;
    }

    private void setSuccessPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("successPoints cannot be negative");
        } else {
            this.successPoints = points;
        }
    }

    public int getFailurePoints() {
        return this.failurePoints;
    }

    private void setFailurePoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("failurePoints cannot be negative");
        } else {
            this.failurePoints = points;
        }
    }

    public int getPowerCost() {
        return this.powerCost;
    }

    private void setPowerCost(int powerCost) {
        if (powerCost < 0) {
            throw new IllegalArgumentException("powerCost cannot be negative");
        } else {
            this.powerCost = powerCost;
        }
    }

    public int getFailedCounter() {
        return this.failedCounter;
    }

    public int getSuccessCounter() {
        return this.successCounter;
    }

    public String getItemId() {
        return this.itemId;
    }

    @Override
    public String toString() {
        return String.format("'%s' power=%d %s", this.getDisplayName(), this.getPowerCost(), this.doneToString());
    }
}