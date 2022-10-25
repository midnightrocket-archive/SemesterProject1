package worldOfZuul.Main.Java.Classes;

import java.util.Random;

public class Game {


    // Static attributes
    public static int day;
    public static int points;
    public static int power;

    // Instans attributes
    private int maxDays;
    private int defaultPower;
    public int extraPower;


    //Constructor
    public Game(int extraPower, int defaultPower, int maxDays) {
        this.day = 0;
        this.defaultPower = defaultPower;
        this.power = defaultPower;
        this.maxDays = maxDays;
        this.extraPower = extraPower;
    }


    //Metode for skift af dag
    public void nextDay() {
        day += 1;
        if (notLastDay()) {
            double randomExtraPower = Math.random() * extraPower;
            setPower(defaultPower + (int) randomExtraPower);
            extraPower -= (int) randomExtraPower;
        } else {
            setPower(defaultPower + extraPower);
            extraPower = 0;
        }
    }

    //Metode for tilføje til points værdi.
    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
    }

    //Metode for check day.
    public boolean notLastDay() {
        if (day < maxDays)
            return true;
        else return false;
    }

    //Metode getPower til at hente powerværdi.
    public int getPower() {
        return power;
    }

    //Metode for points værdi.
    public int getPoint() {
        return points;
    }

    //Metode set power værdi på dagen
    public void setPower(int newPower) {
        power = newPower;
    }
}