package worldOfZuul.Main.Java.Classes;

import java.util.Random;

public class Game {


    //Attributes
    public static int day;
    public static int power;
    public static int points;
    private int maxDays;

    private int defaultPower;
    public int extraPower;



    //Constructors

    public Game(int extraPower, int defaultPower, int maxDays) {
        this.day = 0;
        this.defaultPower = defaultPower;
        this.power = defaultPower;
        this.maxDays = maxDays;
        this.extraPower = extraPower;
    }


    //Methods

    //Method getPower
    public int getPower() {
        return power;
    }

    //Method setPower
    public void setPower(int newPower) {
        power = newPower;
    }

    //Method for checking last day
    public boolean isLastDay() {
        if (day > maxDays)
            return true;
        else return false;
    }

    //Method go to next day
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


    //Method points value
    public int getPoints () {
        return points;
    }

    //Method add points
    public void addPoints (int pointsToAdd){
        points += pointsToAdd;
    }
}









