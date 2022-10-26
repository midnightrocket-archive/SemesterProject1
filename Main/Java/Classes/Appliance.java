package worldOfZuul.Main.Java.Classes;

public class Appliance {
    private final String name; // Name of the appliance
    private final Activity activityReference; // Reference to associated activity

    public Appliance(String name, Activity activityReference) {
        this.name = name;
        this.activityReference = activityReference;
    }

    public String getName() {
        return name;
    }

    public Activity getActivityReference() {
        return activityReference;
    }
}
