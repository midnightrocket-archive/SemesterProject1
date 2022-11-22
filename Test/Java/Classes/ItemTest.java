package worldOfZuul.Test.Java.Classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.Classes.Item;

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