package worldOfZuul.Test.Java.textUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import worldOfZuul.Main.Java.textUI.Parser;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    Parser parser;

    @BeforeEach
    @DisplayName("Test Parser - Constructor")
    public void testConstructor() {
        parser = new Parser();
    }

    // This test does not work, because we can't insert user input into the Scanner
    /*
    @Test
    @DisplayName("Test Parser - GetCommand")
    public void testGetCommand() {
        String input = "go east";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(input, parser.getCommand());

    }
     */
}