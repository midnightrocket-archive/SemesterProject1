/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package worldOfZuul.Main.Java.textUI;

import java.io.IOException;

/**
 *
 * @author ancla
 */
public class WorldOfZuulApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try {
            CommandLineClient client = new CommandLineClient();
            client.play();
        } catch (IOException exception) {
            System.out.println("Cannot load configs exiting.");
            throw exception;
        }
    }
}