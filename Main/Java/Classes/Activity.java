package worldOfZuul.Main.Java.Classes;

public class Activity {

    private final int successPoints;
    private final int failurePoints;
    private final int powerCost;
    private final boolean isDaily;

    public Activity(int successPoints, int failurePoints, int powerCost, boolean isDaily) {

        // Math.max returns the highest of 2 numbers.
        // In this example it returns the variable if it's higher than 0.
        this.successPoints = Math.max(successPoints, 0);
        this.failurePoints = Math.max(failurePoints, 0);
        this.powerCost = Math.max(powerCost, 0);

        this.isDaily = isDaily;
    }

    public int getSuccessPoints() {
        return successPoints;
    }

    public int getFailurePoints() {
        return failurePoints;
    }

    public int getPowerCost() {
        return powerCost;
    }

    public boolean isDaily() {
        return isDaily;
    }
}