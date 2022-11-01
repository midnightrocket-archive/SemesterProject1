package worldOfZuul.Main.Java;

public interface Command {

    Actions getCommandName();

    String getCommandValue();

    boolean hasCommandValue();

    boolean isUnknown();
}