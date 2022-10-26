package worldOfZuul.Main.Java;

public interface Command {

    Commands getCommandName();

    String getCommandValue();

    boolean hasCommandValue();

    boolean isUnknown();
}