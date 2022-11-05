package worldOfZuul.Main.Java.Classes;


public class Appliance {
    private String id;
    private String displayName;
    private String activityId;
    private String itemId;


    public Appliance(String id, String displayName, String activityId, String itemId) {
        this.id = id;
        this.displayName = displayName;
        this.activityId = activityId;
        this.itemId = itemId;
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

    public String getItemId() {
        return this.getItemId();
    }
}