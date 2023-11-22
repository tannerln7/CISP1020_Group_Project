package Helpers;

public class Round {
    public static double round(double numberToRound){
        return (double) Math.round(numberToRound * 100) / 100;
    }
}
