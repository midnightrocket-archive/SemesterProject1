package worldOfZuul.Main.Java.Classes;

import java.util.Random;

public class Game {

    // Static attributes
    public static int day = 0;
    public static int power;
    public static int points;

    // Instance attributes
    private int maxDays;
    private int defaultPower;
    public int extraPower;

  
    // Constructor
    public Game(int extraPower, int defaultPower, int maxDays) {
        this.defaultPower = defaultPower;
        this.power = defaultPower;
        this.maxDays = maxDays;
        this.extraPower = extraPower;
    }


    // Methods //

    // Method add points
    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
    }
  
  
    // Method for checking last day
    public boolean isLastDay() {
        if (day > maxDays)
            return true;
        else return false;
    }

    // Method go to next day
    public void setNextDay() {
        day += 1;
        if (isLastDay()) {
            double randomExtraPower = Math.random() * extraPower;
            setPower(defaultPower + (int) randomExtraPower);
            extraPower -= (int) randomExtraPower;
        } else {
            setPower(defaultPower + extraPower);
            extraPower = 0;
        }
    }

    // Metode for points værdi.
    public int getPoint() {
        return points;
    }
  
    // Metode getPower til at hente powerværdi.
    public int getPower() {
        return power;
    }

    // Metode set power værdi på dagen
    public void setPower(int newPower) {
        power = newPower;
    }
}