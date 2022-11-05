package worldOfZuul.Main.Java.Classes;

import worldOfZuul.Main.Java.Command;
import worldOfZuul.Main.Java.Room;
import worldOfZuul.Main.Java.Game;

import java.util.ArrayList;

public class Player {
    private String playerName;


    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return this.playerName;
    }
}