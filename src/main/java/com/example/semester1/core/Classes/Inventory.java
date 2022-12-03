package com.example.semester1.core.Classes;


public class Inventory extends ReferenceList<Item> {
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "Nothing";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Item item : this) {
            stringBuilder.append(String.format(" - %s\n", item.getDisplayName()));
        }

        return stringBuilder.toString();
    }
}