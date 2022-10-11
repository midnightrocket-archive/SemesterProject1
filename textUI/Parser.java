package worldOfZuul.textUI;

import java.util.Scanner;
import worldOfZuul.Command;
import worldOfZuul.Game;

public class Parser 
{
    private Scanner reader;
    private final Game game;

    public Parser(Game game) 
    {
        this.game = game;
        this.reader = new Scanner(System.in);
    }

    public Command getCommand() 
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> "); 

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next(); 
            }
        }

        return game.getCommand(word1,word2);
    }
}
