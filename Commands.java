package worldOfZuul;

public enum Commands
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?");
    
    private String commandName;
    
    Commands(String commandString)
    {
        this.commandName = commandString;
    }
    
    public String toString()
    {
        return commandName;
    }
}
