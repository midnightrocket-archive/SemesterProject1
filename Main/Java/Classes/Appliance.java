package worldOfZuul.Main.Java.Classes;

public class Appliance {
    private final String name; // Name of the appliance
    private final Activity activity; // Reference to associated activity

    public Appliance(String name, Activity activity) {
        this.name = name;
        this.activity = activity;
    }

    public Activity getApplianceActivity() {
        return activity;
    }

    public String getApplianceName() {
        return name;
    }
}
