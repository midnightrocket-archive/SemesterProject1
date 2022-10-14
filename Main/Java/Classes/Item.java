package worldOfZuul.Main.Java.Classes;

public class Item {
    private final String name; // Name of the item
    private final Appliance appliance; // Reference to the associated appliance

    public Item(String name, Appliance appliance) {
        this.name = name;
        this.appliance = appliance;
    }
    
    public Appliance getItemAppliance() {
        return appliance;
    }

    public String getItemName() {
        return name;
    }
}
