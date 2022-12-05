package com.example.semester1.core.Classes;

public class Appliance extends Item {
    private final String activityId;

    public Appliance(String id, String displayName, String activityId) {
        super(id, displayName);
        this.activityId = activityId;
    }

    public String getActivityId() {
        return this.activityId;
    }

}