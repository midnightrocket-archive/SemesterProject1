/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * <p>
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 * <p>
 * If the command had only one word, then the second word is <null>.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

package com.example.semester1.core;

public class CommandImplementation implements Command {

    private final Action action; // the first word in the command.
    private final String commandValue; //the second word in the command.

    /*
     * Constructor for a command object.
     * The complete command consists of two parts, the command enum and the second user input string.
     * If the first word was not recognised the command enum is Commands.UNKNOWN.
     * The second word aka the commandValue may be null if none is given.
     */
    public CommandImplementation(Action actionWord, String secondWord) {
        this.action = actionWord;
        this.commandValue = secondWord;
    }

    // Returns the command enum
    // e.g. TAKE in "take map"
    @Override
    public Action getAction() {
        return this.action;
    }

    // Returns the second word of the command.
    // e.g. "map" in "take map"
    // Returns null if there was no second word given.
    @Override
    public String getCommandValue() {
        return this.commandValue;
    }

    // Returns true if a second word is given as input.
    @Override
    public boolean hasCommandValue() {
        return (this.commandValue != null);
    }
}