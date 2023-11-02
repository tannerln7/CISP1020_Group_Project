package Helpers;

import java.util.Random;
import java.math.BigInteger;

public class Utilities {

    public static BigInteger generateAccountNumber(String seedString) {
        long seed = seedString.hashCode() & 0xffffffffL;
        Random random = new Random(seed);

        // Use StringBuilder to create the number as a string
        StringBuilder numberBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            // Avoid leading zero
            if (i == 0 && digit == 0) {
                digit = random.nextInt(9) + 1;
            }
            numberBuilder.append(digit);
        }

        // Convert the string representation of the number to a BigInteger
        return new BigInteger(numberBuilder.toString());
    }
}
