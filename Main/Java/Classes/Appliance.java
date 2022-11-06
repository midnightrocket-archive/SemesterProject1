package worldOfZuul.Main.Java.Classes;

import worldOfZuul.Main.Java.Interfaces.Aliasable;

public class Appliance implements Aliasable {
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

    public String getItemId() {
        return this.itemId;
    }
}