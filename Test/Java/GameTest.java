package worldOfZuul.Test.Java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Command;
import worldOfZuul.Main.Java.Game;
import worldOfZuul.Main.Java.Room;
import worldOfZuul.Main.Java.ValidActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;

    @BeforeEach
    @DisplayName("Test Game - Setup")
    public void setup() {
        game = Game.getInstance();
    }

    @Test
    @DisplayName("Test Game - GoRoom")
    public void testGoRoom() {
        // WARNING! This test can be inoperative, if the rooms connections changes.

        // This test also resets the player location to the starting point
        String[] directionsString = {"west", "north", "north", "west", "west", null, "east", "south", "south", "east"};
        Boolean[] directionsBoolean = {true, true, true, true, false, false, true, true, true, true};

        for (int i = 0; i < directionsString.length; i++) {
            Command command = game.getCommand("go", directionsString[i]);
            assertEquals(game.goRoom(command), directionsBoolean[i]);
            System.out.println(game.getRoomDescription());
        }
    }

    @Test
    @DisplayName("Test Game - PickUpItem")
    public void testPickUpItem() {

        // Setup for moving player to correct room
        game.goRoom(game.getCommand("go", "west"));
        game.goRoom(game.getCommand("go", "north"));


        String[] itemString = {"food", "dirty_dishes", null};
        Boolean[] itemBoolean = {false, true, false};


        for (int i = 0; i < itemString.length; i++) {
            Command command = game.getCommand("pickup", itemString[i]);
            System.out.println(itemString[i]);
            assertEquals(game.pickupItem(command), itemBoolean[i]);
        }
    }

    @Test
    @DisplayName("Test Game - Quit")
    public void testQuit() {
        Command command;

        command = game.getCommand("quit", null);
        assertTrue(game.quit(command));

        command = game.getCommand("quit", "test");
        assertFalse(game.quit(command));
    }

    @Test
    @DisplayName("Test Game - Getter Methods")
    public void testGetterMethods() {
        // There are no checks for these functions.
        // They are purely for the testing of the methods being call-able.
        String s1 = game.getRoomDescription();
        String s2 = game.getAppliancesInRoom();
        Room room1 = game.getCurrentRoom();
        ValidActions validActions = game.getCommands();
        List<String> list1 = game.getCommandDescriptions();
        game.getActivity();
    }

    @Test
    @DisplayName("Test Game - SetPower")
    public void testSetPower() {
        game.setPower(0);
        assertEquals(game.getPower(), 0);

        game.setPower(50);
        assertEquals(game.getPower(), 50);
    }

    @Test
    @DisplayName("Test Game - AddPoints")
    public void testAddPoints() {
        game.setPoints(0);
        assertEquals(game.getPoints(), 0);

        game.addPoints(5);
        assertEquals(game.getPoints(), 5);
    }

    @Test
    @DisplayName("Test Game - SetNextDay")
    public void testSetNextDay() {
        assertEquals(game.getDay(), 0);

        game.setNextDay();
        assertEquals(game.getDay(), 1);

        for (int i = 0; i < 7; i++) {
            game.setNextDay();
        }

        assertEquals(game.getDay(), 8);
        assertTrue(game.isLastDay());
    }
}
