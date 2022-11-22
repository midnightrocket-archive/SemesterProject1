package com.example.semester1.core.Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    @DisplayName("Inventory test - Constructor")
    public void testConstructor() {
        Inventory inventory = new Inventory();
        assertEquals("Nothing", inventory.toString());

        inventory.add(new Item("testID", "testDisplayname"));
        assertEquals(" - testDisplayname\n", inventory.toString());
    }
}