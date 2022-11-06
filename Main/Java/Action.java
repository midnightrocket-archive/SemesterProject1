/*
 * Represents all the valid actions in the game, defined as enum constants.
 * Each action enum corresponds to an action string.
 */

package worldOfZuul.Main.Java;

public enum Action {
    // Defines a constant for each action alongside its corresponding action string.
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    INVENTORY("inventory"),
    PICKUP("pickup"),
    ACTIVITIES("activities"),
    IMLOST("imlost"),
    POWER("power"),
    USE("use"),
    UNKNOWN("?");

    public static Action parse(String string) {
        string = string.trim().toUpperCase(); //Format string so it is parsable by valueOf
        try {
            return Action.valueOf(string);
        } catch (IllegalArgumentException exception) {
            //If string is not found then, return UNKNOWN.
            return Action.UNKNOWN;
        }
    }

    public static String allValidToString() {
        StringBuilder output = new StringBuilder();
        for (Action action : Action.values()) {
            if (action != Action.UNKNOWN) {
                output.append(String.format(" - %s\n", action));
            }
        }
        return output.toString();
    }

    // The action string.
    private String actionName;

    // The constructor for each enum constant.
    Action(String actionsString) {
        this.actionName = actionsString;
    }

    // Returns action string.
    @Override
    public String toString() {
        return this.actionName;
    }
}