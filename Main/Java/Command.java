package worldOfZuul.Main.Java;

public interface Command {

    Action getCommandName();

    String getCommandValue();

    boolean hasCommandValue();

    boolean isUnknown();
}