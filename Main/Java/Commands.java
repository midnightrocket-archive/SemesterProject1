/*
 * Represents all the valid commands in the game, defined as enum constants.
 * Each command enum corresponds to a command string.
 */

package worldOfZuul.Main.Java;

public enum Commands {
    // Defines a constant for each command alongside its corresponding command string.
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    INVENTORY("inventory"),
    PICKUP("pickup"),
    ACTIVITYS("activitys"),
    IMLOST("imlost"),
    UNKNOWN("?");
    
    // The command string.
    private String commandName;
    
    // The constructor for each enum constant.
    Commands(String commandString) {
        this.commandName = commandString;
    }
    
    // Returns command string.
    public String toString() {
        return commandName;
    }
}
