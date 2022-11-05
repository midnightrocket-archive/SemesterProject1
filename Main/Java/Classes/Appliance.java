package worldOfZuul.Main.Java.Classes;


public class Appliance {
    private String id;
    private String displayName;
    private String activityId;


    public Appliance(String id, String displayName, String activityId) {
        this.id = id;
        this.displayName = displayName;
        this.activityId = activityId;
    }


    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getActivityId() {
        return activityId;
    }
}