package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Appliance;
import worldOfZuul.Main.Java.Classes.Inventory;
import worldOfZuul.Main.Java.Classes.Item;
import worldOfZuul.Main.Java.Classes.Player;
import worldOfZuul.Main.Java.Classes.Activity;
import worldOfZuul.Main.Java.Game;
import worldOfZuul.Main.Java.Room;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Activity activity;
    Appliance appliance;
    Room room;
    Item item;
    Inventory inventory;
    Player player;

    @BeforeEach
    @DisplayName("Test Player - setup")
    public void setup() {
        activity = new Activity("test", 5, 5, 5, false);
        room = new Room("TestRoom");
        appliance = new Appliance("TestAppliance", room, activity);
        item = new Item("TestItem", appliance);
        inventory = Inventory.getInstance();
        player = Player.getInstance();
    }

    @Test
    @DisplayName("Test Player - AddItemToInventory and RemoveItemFromInventory")
    public void testAddAndRemoveItemsFromInventory() {
        ArrayList<Item> expected = new ArrayList<>();
        inventory.clearInventory();

        assertEquals(inventory.getInventoryList(), new ArrayList<>());

        player.addItemToInventory(item);
        expected.add(item);

        assertEquals(inventory.getInventoryList(), expected);

        player.removeItemFromInventory(item);
        expected.remove(item);

        assertEquals(inventory.getInventoryList(), new ArrayList<>());
    }

    @Test
    @DisplayName("Test Player - Use")
    public void testUse() {
        // Transports the user to the kitchen
        Game.getInstance().goRoom(Game.getInstance().getCommand("go", "west"));
        Game.getInstance().goRoom(Game.getInstance().getCommand("go", "north"));
        Game.getInstance().goRoom(Game.getInstance().getCommand("go", "west"));

        // Tries to use an appliance that doesn't exist
        player.use(Game.getInstance().getCommand("use", "none_existing"));

        // Tries to use the appliance "dishwasher" with no power
        Game.getInstance().setPower(0);
        player.use(Game.getInstance().getCommand("use", "dishwasher"));

        // Tries to use the appliance "dishwasher" with power but without the item
        Game.getInstance().setPower(100);
        player.use(Game.getInstance().getCommand("use", "dishwasher"));

        // Tries to use the appliance "dishwasher" with power and the item
        Game.getInstance().goRoom(Game.getInstance().getCommand("go", "east"));
        Game.getInstance().pickupItem(Game.getInstance().getCommand("pickup", "dirty_dishes"));
        Game.getInstance().goRoom(Game.getInstance().getCommand("go", "west"));
        player.use(Game.getInstance().getCommand("use", "dishwasher"));

        // Tries to use the appliance "dishwasher" after it has been completed
        player.use(Game.getInstance().getCommand("use", "dishwasher"));

        // Tries to use the appliance "oven" with power and the item
        // Also tries to use it again after
        Game.getInstance().pickupItem(Game.getInstance().getCommand("pickup", "oven_food"));
        player.use(Game.getInstance().getCommand("use", "oven"));
        player.use(Game.getInstance().getCommand("use", "oven"));
    }
}