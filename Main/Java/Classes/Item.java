package worldOfZuul.Main.Java.Classes;

/**
 * @param id                 Name of the item
 * @param applianceReference Reference to the associated appliance
 */
/*
 * Using record
 * https://dzone.com/articles/what-are-java-records
 * Records are class like constructs, but with immutable properties.
 * Methods can still be implemented if needed, as long they do not modifier the properties.
 * getter methods for each property is automatically generated.
 * *NOTE* the getter methods are named exactly the same as the property they refer to.
 */
public record Item(String id, String displayName) {
}