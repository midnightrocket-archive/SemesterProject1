package worldOfZuul.Main.Java;

public interface Command {

    Action getAction();

    String getCommandValue();

    boolean hasCommandValue();

    boolean isUnknown();
}