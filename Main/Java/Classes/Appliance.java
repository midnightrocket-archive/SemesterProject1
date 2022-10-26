package worldOfZuul.Main.Java.Classes;
import worldOfZuul.Main.Java.Room;

public class Appliance {
    private final String name; // Name of the appliance

    private final Room roomReference; // Room the appliance is inside
    private final Activity activityReference; // Reference to associated activity

    public Appliance(String name, Room roomReference, Activity activityReference) {
        this.name = name;
        this.roomReference = roomReference;
        this.activityReference = activityReference;
    }
    public Room getRoomReference() {
        return roomReference;
    }
    public Activity getActivityReference() {
        return activityReference;
    }

    public String getName() {
        return name;
    }
}
