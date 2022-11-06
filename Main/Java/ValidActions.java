package worldOfZuul.Main.Java;

import java.util.List;

public interface ValidActions {

    // Text-based lookup for specific action.
    Actions getAction(String actionWord);

    boolean isAction(String aString);

    List<String> getActionWords();

}
