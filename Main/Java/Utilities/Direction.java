package worldOfZuul.Main.Java.Utilities;

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

    public Direction opposite() {
        // Using the switch expressions construct
        // https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html
        return switch (this) {
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EAST;
        };
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}