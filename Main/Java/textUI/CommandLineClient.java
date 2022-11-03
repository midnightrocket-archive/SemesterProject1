/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package worldOfZuul.Main.Java.textUI;

import worldOfZuul.Main.Java.Command;
import worldOfZuul.Main.Java.Actions;
import worldOfZuul.Main.Java.Game;

/**
 *
 * @author ancla
 */
public class CommandLineClient {

    private Parser parser;
    private Game game;

    public CommandLineClient() {
        game = Game.getInstance();
        parser = new Parser();
    }

    public void play() {
        // Welcome message
        printWelcome();

        boolean finished = false;

        // The main loop. If this loop stops, the game stops.
        while (!finished) {
            // parser takes user input and returns a Command object that constains command enum and command string.
            Command command = parser.getCommand();
            finished = processCommand(command); // finished is true, if "wantToQuit" becomes true.
        }

        System.out.println("\nThank you for playing.   Goodbye.");
        // Game ends from here
    }

    private void printWelcome() {
        System.out.println("\nWelcome to the Semesterproject 1 game!");
        System.out.println("In this game, your mission is to use your power efficiently.");
        System.out.println("To get started, use the \"" + Actions.HELP + "\" command. Good luck!\n");
        System.out.println(game.getRoomDescription());
    }

    private void printHelp() {
        for (String str : game.getCommandDescriptions()) {
            System.out.println(" - " + str);
        }
    }

    // Controller
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        Actions commandWord = command.getCommandName(); // holds command enum

        // Checks if the command is invalid.
        if (commandWord == Actions.UNKNOWN) {
            System.out.println("\nI don't know what you mean...");
            return false;
        }

        if (commandWord == Actions.HELP) { // HELP COMMAND
            System.out.println("\nYou can use the following commands:");
            printHelp();
        } else if (commandWord == Actions.GO) { // GO COMMAND
            if (game.goRoom(command)) {
                System.out.println(game.getRoomDescription());
            } else {
                System.out.println("\nCan't walk in that direction.");
            }
        } else if (commandWord == Actions.QUIT) { // QUIT COMMAND
            if (game.quit(command)) {
                wantToQuit = true;
            } else {
                System.out.println("\nQuit what?");
            }
        } else if (commandWord == Actions.INVENTORY) { // INVENTORY COMMAND
            System.out.println("\nYour inventory contains: ");
            System.out.println(game.getInventory());
        } else if (commandWord == Actions.PICKUP) { // PICKUP COMMAND
            if(game.pickupItem(command)) {
                System.out.println("\nThis item has been added to your inventory!");
            } else {
                System.out.println("\nThat item does not exist in this room...");
            }
        } else if (commandWord == Actions.ACTIVITYS) { // ACTIVITYS COMMAND
            System.out.println("\nYou are still missing the following activitys:");
            System.out.println(game.getActivity());
        } else if (commandWord == Actions.IMLOST) { // IMLOST COMMAND
            System.out.println(game.getRoomDescription());
        } else if (commandWord == Actions.POWER) { // POWER COMMAND
            System.out.println("\nYour power is " +game.getPower());
        } else if (commandWord == Actions.USE) { // USE COMMAND
            game.player.use(command);
        }

        return wantToQuit;
    }
}
