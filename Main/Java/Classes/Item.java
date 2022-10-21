package worldOfZuul.Main.Java.Classes;

public class Item {
    private final String name; // Name of the item
    private final Appliance applianceReference; // Reference to the associated appliance

    public Item(String name, Appliance applianceReference) {
        this.name = name;
        this.applianceReference = applianceReference;
    }
    
    public Appliance getApplianceReference() {
        return applianceReference;
    }

    public String getItemName() {
        return name;
    }
}
