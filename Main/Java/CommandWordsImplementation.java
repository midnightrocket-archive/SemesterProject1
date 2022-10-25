/**
 * This class holds a HashMap of all the possible commands in the game.
 * Each command enum in Commands corresponds to a command string.
 * It is used to evaluate if a user input is a valid command.
 */

package worldOfZuul.Main.Java;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandWordsImplementation implements CommandWords {

    // A mapping between each command string and command enum.
    private HashMap<String, Commands> validCommands;

    /*
     * This constructor iterates over the enum constant defined in Commands.
     * Each command string is mapped to a command enum as a key-value.
     * For example, "go" is the key to the value Commands.GO.
     */
    public CommandWordsImplementation() {
        validCommands = new HashMap<String, Commands>();
        for(Commands command : Commands.values()) {
            if(command != Commands.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /* Text-based lookup for specific command.
    * Takes string as input and finds corresponding command enum value in validCommands HashMap.
    * Returns matching command enum value, and Commands.UNKNOWN if there is no matching entry.
    * e.g. getCommand("go") will return Commands.GO
    */
    @Override
    public Commands getCommand(String commandWord) {
        Commands command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return Commands.UNKNOWN;
        }
    }
    
    /*
     * Checks if a given string is a valid command/a key in the HashMap.
     * e.g. isCommand("go") will return true. isCommand("random") will return false.
     */
    @Override
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    /*
     * Getter method for all the valid command strings.
     * Iterates over every key element in validCommands and adds it to List<String>.
     */
    @Override
    public List<String> getCommandWords() {
        List<String> commandWords = new ArrayList<>();
        for(String commandWord : validCommands.keySet()) {
            commandWords.add(commandWord);
        }
        return commandWords;
    }
}
