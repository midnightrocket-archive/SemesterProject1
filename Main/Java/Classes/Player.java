package worldOfZuul.Main.Java.Classes;

public class Player {
    private String name;
    private Inventory inventory = new Inventory();


    public Player(String name) {
        this.name = name;
    }

    public boolean addItem(Item item) {
        return this.inventory.add(item);
    }

    public boolean hasItem(String string) {
        return this.inventory.containsByAlias(string);
    }

    public boolean hasItem(Item item) {
        return this.inventory.contains(item);
    }

    public boolean removeItem(String string) {
        return this.inventory.removeByAlias(string);
    }

    public boolean removeItem(Item item) {
        return this.inventory.remove(item);
    }

    public Item getItem(String alias) {
        return this.inventory.getByAlias(alias);
    }

    public String inventoryToString() {
        return this.inventory.toString();
    }


    public String getName() {
        return this.name;
    }
}