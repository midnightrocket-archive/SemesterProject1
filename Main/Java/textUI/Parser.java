/*
 * The parser reads user input and interprets the user input.
 * It tries to interpret the line as a two-word command.
 * It returns a Command object constructed with the two-word command.
 */

package worldOfZuul.Main.Java.textUI;

import worldOfZuul.Main.Java.Action;
import worldOfZuul.Main.Java.Command;
import worldOfZuul.Main.Java.CommandImplementation;

import java.util.Scanner;

public class Parser {
    private final Scanner reader;

    public Parser() {
        this.reader = new Scanner(System.in);
    }

    public String getRawInput(String prompt) {
        System.out.print(prompt); // print prompt
        return reader.nextLine(); // Returns whole line, when user presses enter.
    }
    public String getRawInput() { // Using default prompt
        return this.getRawInput("> ");
    }

    public Command getCommand() {

        String word1 = null;
        String commandValue = null;


        // Find up to two words on the line. The rest of the input line is ignored.
        Scanner tokenizer = new Scanner(this.getRawInput());
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next().toLowerCase(); // get first word and lower case it for command processing
            if (tokenizer.hasNext()) {
                commandValue = tokenizer.nextLine().strip(); // get the value for the command
            }
        }

        return new CommandImplementation(Action.parse(word1), commandValue);
    }
}