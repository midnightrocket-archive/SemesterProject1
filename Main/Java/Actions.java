/*
 * Represents all the valid actions in the game, defined as enum constants.
 * Each action enum corresponds to a action string.
 */

package worldOfZuul.Main.Java;

public enum Actions {
    // Defines a constant for each action alongside its corresponding action string.
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    INVENTORY("inventory"),
    PICKUP("pickup"),
    ACTIVITYS("activities"),
    IMLOST("imlost"),
    UNKNOWN("?");
    
    // The action string.
    private String actionName;
    
    // The constructor for each enum constant.
    Actions(String actionsString) {
        this.actionName = actionsString;
    }
    
    // Returns action string.
    public String toString() {
        return actionName;
    }
}
