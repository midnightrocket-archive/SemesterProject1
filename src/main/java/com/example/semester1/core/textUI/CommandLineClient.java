/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.semester1.core.textUI;

import com.example.semester1.core.Action;
import com.example.semester1.core.Command;
import com.example.semester1.core.Game;

import java.io.IOException;

/**
 * @author ancla
 */
public class CommandLineClient {

    private Parser parser;
    private Game game;

    public CommandLineClient() {
        parser = new Parser();
    }

    public void play() throws IOException{

        game = Game.createInstance(this.greetPlayer());
        parser.getRawInput("Press enter to continue> ");

        // Welcome message
        printWelcome();


        boolean finished = false;

        // The main loop. If this loop stops, the game stops.
        while (!finished) {
            // parser takes user input and returns a Command object that contains command enum and command string.
            Command command = parser.getCommand();
            finished = processCommand(command); // finished is true, if "wantToQuit" becomes true.
        }

        System.out.println(game.generateGameStats());

        System.out.println("\nThank you for playing.   Goodbye.");
        // Game ends from here
    }

    private String greetPlayer() {
        String name = parser.getRawInput("Hello what is your name? ");
        System.out.println();
        System.out.printf("Hello %s nice to meet you!\nHope you are ready!\n", name);
        return name;
    }

    private void printWelcome() {
        System.out.println("\nWelcome to the Semesterproject 1 game!");
        System.out.println("In this game, your mission is to use your power efficiently.");
        System.out.println("To get started, use the \"" + Action.HELP + "\" command. Good luck!\n");
        parser.getRawInput("Press enter to continue> ");
        System.out.println(game.getRoomDescription());
    }

    private void printHelp() {
        System.out.println(Action.allValidToString());
    }

    // Controller
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        Action action = command.getAction(); // holds command enum


        switch (action) {
            case HELP:
                System.out.println("\nYou can use the following commands:");
                printHelp();

                break;
            case GO:
                if (game.goRoom(command)) {
                    System.out.println(game.getRoomDescription());
                } else {
                    System.out.println("\nCan't walk in that direction.");
                }

                break;
            case USE:
                game.useCommand(command);
                break;
            case QUIT:
                if (game.quit(command)) {
                    wantToQuit = true;
                } else {
                    System.out.println("\nQuit what?");
                }

                break;
            case INVENTORY:
                System.out.println("\nYour inventory contains: ");
                System.out.println(game.inventoryToString());

                break;
            case PICKUP:
                if (game.pickupItem(command)) {
                    System.out.println("\nThis item has been added to your inventory!");
                } else {
                    System.out.println("\nThat item does not exist in this room...");
                }

                break;
            case ACTIVITIES:
                System.out.println("\nYou are still missing the following activities:");
                System.out.println(game.activitiesToString());

                break;
            case IMLOST:
                System.out.println(game.getRoomDescription());

                break;
            case POWER:
                System.out.println("\nYour power is " + game.getPower());

                break;
            case SLEEP:
                if (game.sleepCommand()) {
                    System.out.println("This was your last day. Well done. Here are some stats for you:");
                    wantToQuit = true;
                } else {
                    System.out.printf("Goodnight %s \n", game.getPlayer().getName());
                    parser.getRawInput("Press enter to wake up> ");
                    System.out.println();
                    System.out.printf("Good morning! Day %d\n", game.getDay());
                    System.out.printf("A new day means new power levels, run '%s' to see how much power you have today\n", Action.POWER);
                }
                break;
            case POINTS:
                System.out.printf("You have %d points\n", game.getPoints());
                break;
            case UNKNOWN:
            default:
                System.out.println("\nI don't know what you mean... \n type 'help', to get help");
        }


        return wantToQuit;
    }
}