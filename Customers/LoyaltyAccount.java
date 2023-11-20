package Customers;
import Products.Discount;

public class LoyaltyAccount extends Loyalty{

    private final int accountNumber;
    private static int lastAccountNumber = 100000;

    //Used as a default constructor. Uses default pointsBalance and creates a default rewardsDiscount object
    public LoyaltyAccount () {
        super();
        this.accountNumber = lastAccountNumber++;
        lastAccountNumber++;
    }

    //Used to create a LoyaltyAccount object with custom parameters
    public LoyaltyAccount(double initialPointsBalance, Discount rewardsDiscount) {
        super(initialPointsBalance, rewardsDiscount);
        this.accountNumber = lastAccountNumber++;
        lastAccountNumber++;
    }

    public double getAccountNumber() {
        return this.accountNumber;
    }
}