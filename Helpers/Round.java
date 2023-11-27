package Helpers;

/**
 * The Round class provides a utility method for rounding a double value to two decimal places.
 */
public class Round {

    /**
     * Rounds a double value to two decimal places. Used for monetary values.
     *
     * @param numberToRound the double value to round
     * @return the rounded double value
     */
    public static double round(double numberToRound){
        return (double) Math.round(numberToRound * 100) / 100;
    }
}
