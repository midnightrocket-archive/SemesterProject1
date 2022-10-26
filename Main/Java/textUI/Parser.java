/*
 * The parser reads user input and interprets the user input.
 * It tries to interpret the line as a two-word command.
 * It returns a Command object constructed with the two-word command.
 */

package worldOfZuul.Main.Java.textUI;

import java.util.Scanner;
import worldOfZuul.Main.Java.Command;
import worldOfZuul.Main.Java.Game;

public class Parser {
    private final Scanner reader;
    private final Game game;

    public Parser(Game game) {
        this.game = game;
        this.reader = new Scanner(System.in);
    }

    public Command getCommand() {
        String inputLine; // holds the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> "); // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line. The rest of the input line is ignored.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next().toLowerCase(); // get first word and lower case it for command processing
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next().toLowerCase();  // get the second word and lower case it for command processing
            }
        }

        return game.getCommand(word1,word2);
    }
}
