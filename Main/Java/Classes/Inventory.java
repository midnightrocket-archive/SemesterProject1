package worldOfZuul.Main.Java.Classes;


public class Inventory extends ReferenceContainer<Item> {
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "Nothing";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Item item : this) {
            stringBuilder.append(String.format(" - %s\n", item.displayName()));
        }

        return stringBuilder.toString();
    }
}