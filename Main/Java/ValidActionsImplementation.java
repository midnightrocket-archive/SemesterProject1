/**
 * This class holds a HashMap of all the possible actions in the game.
 * Each action enum in Actions corresponds to an action string.
 * It is used to evaluate if a user input is a valid action.
 */

package worldOfZuul.Main.Java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidActionsImplementation implements ValidActions {

    // A mapping between each action string and action enum.
    private HashMap<String, Actions> validActions;

    /*
     * This constructor iterates over the enum constant defined in Actions.
     * Each action string is mapped to an action enum as a key-value.
     * For example, "go" is the key to the value Actions.GO.
     */
    public ValidActionsImplementation() {
        validActions = new HashMap<>();
        for (Actions actions : Actions.values()) {
            if (actions != Actions.UNKNOWN) {
                validActions.put(actions.toString(), actions);
            }
        }
    }

    /* Text-based lookup for specific action.
     * Takes string as input and finds corresponding action enum value in validActions HashMap.
     * Returns matching action enum value, and Actions.UNKNOWN if there is no matching entry.
     * e.g. getAction("go") will return Actions.GO
     */
    @Override
    public Actions getAction(String actionWord) {
        Actions action = validActions.get(actionWord);
        if (action != null) {
            return action;
        } else {
            return Actions.UNKNOWN;
        }
    }

    /*
     * Checks if a given string is a valid action/a key in the HashMap.
     * e.g. isAction("go") will return true. isAction("random") will return false.
     */
    @Override
    public boolean isAction(String aString) {
        return validActions.containsKey(aString);
    }

    /*
     * Getter method for all the valid action strings.
     * Iterates over every key element in validActions and adds it to List<String>.
     */
    @Override
    public List<String> getActionWords() {
        List<String> actionWords = new ArrayList<>();
        for (String actionWord : validActions.keySet()) {
            actionWords.add(actionWord);
        }
        return actionWords;
    }
}
