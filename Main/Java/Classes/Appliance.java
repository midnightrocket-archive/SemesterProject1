package worldOfZuul.Main.Java.Classes;

import worldOfZuul.Main.Java.Interfaces.Aliasable;

public class Appliance implements Aliasable {
    private String id;
    private String displayName;
    private String activityId;


    public Appliance(String id, String displayName, String activityId) {
        this.id = id;
        this.displayName = displayName;
        this.activityId = activityId;
    }

    public boolean hasAlias(String alias) {
        alias = alias.toLowerCase().trim();
        return alias.equals(this.id.toLowerCase()) || alias.equals(this.displayName.toLowerCase());
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