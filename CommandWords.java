
package worldOfZuul;

import java.util.List;

public interface CommandWords {

    //Text-based lookup for specific command.
    Commands getCommand(String commandWord);

    boolean isCommand(String aString);

    List<String> getCommandWords();
    
}
