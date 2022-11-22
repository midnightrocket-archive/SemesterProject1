package com.example.semester1.core.Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;
    Item item;

    @BeforeEach
    @DisplayName("Test Player - Setup")
    public void setup() {
        player = new Player("nameOfPlayer");
        item = new Item("testId", "testDisplayname");
    }


    @Test
    @DisplayName("Test Player - AddItem and RemoveItem")
    public void testAddItemAndRemoveItem() {
        player.addItem(item);

        assertTrue(player.hasItem("testId"));
        assertTrue(player.hasItem("testDisplayname"));
        assertTrue(player.hasItem(item));

        // Test with removing Item as a object
        player.removeItem(item);
        assertFalse(player.hasItem(item));


        // Test with removing Item as a string
        player.addItem(item);
        assertTrue(player.hasItem(item));
        player.removeItem(item.displayName());
        assertFalse(player.hasItem(item));
    }


    @Test
    @DisplayName("Test Player - GetName")
    public void testGetName() {
        assertEquals("nameOfPlayer", player.getName());
    }


    @Test
    @DisplayName("Test Player - GetItem")
    public void testGetItem() {
        player.addItem(item);

        assertEquals(item, player.getItem(item.displayName()));
    }


    @Test
    @DisplayName("Test Player - InventoryToString")
    public void testInventoryToString() {
        assertEquals("Nothing", player.inventoryToString());

        player.addItem(item);

        assertEquals(" - testDisplayname\n", player.inventoryToString());
    }
}