package com.example.semester1.core.Classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    @DisplayName("Test Item - HasAlias")
    public void testHasAlias() {
        Item item1 = new Item("testId1", "testDisplayname");
        assertFalse(item1.hasAlias("testId??"));
        assertTrue(item1.hasAlias("testId1"));
        assertTrue(item1.hasAlias("testDisplayname"));
    }
}