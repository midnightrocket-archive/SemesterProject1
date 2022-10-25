package worldOfZuul.Main.Java.Classes;

import java.util.Random;

public class Game {


    //Attributter
    public static int day;
    private int maxDays;
    private static int power;
    private int defaultPower;
    public int extraPower;
    public static int points;


    //Constructors

    public Game(int extraPower, int defaultPower, int maxDays) {
        this.day = 0;
        this.defaultPower = defaultPower;
        this.power = defaultPower;
        this.maxDays = maxDays;
        this.extraPower = extraPower;
    }


    //Metoder

    //Metode getPower til at hente powerværdi.
    public int getPower() {
        return power;
    }

    //Metode set power værdi på dagen
    public void setPower(int newPower) {
        power = newPower;
    }

    //Metode for check day.
    public boolean notLastDay() {
        if (day < maxDays)
            return true;
        else return false;
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


    //Metode for points værdi.
    public int getPoints () {
        return points;
    }

    //Metode for tilføje til points værdi.
    public void addPoints (int pointsToAdd){
        points += pointsToAdd;
    }
}









