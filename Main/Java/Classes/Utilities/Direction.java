package worldOfZuul.Main.Java.Classes.Utilities;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;


    public static Direction parse(String string) {
        for (Direction direction : Direction.values()) {
            if (direction.toString().equals(string)) {
                return direction;
            }
        }
        throw new IllegalArgumentException(String.format("'%s' is not a valid direction", string));
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}