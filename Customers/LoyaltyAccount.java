package Customers;
import Products.Discount;

// The LoyaltyAccount class extends the Loyalty class to represent a customer's loyalty account, including an account number.
public class LoyaltyAccount extends Loyalty{

    // The unique account number associated with the customer.
    private final int accountNumber;

    // A counter to generate unique account numbers.
    private static int lastAccountNumber = 100000;

    //Used as a default constructor. Uses default pointsBalance and creates a default rewardsDiscount object
    public LoyaltyAccount () {
        super();
        this.accountNumber = lastAccountNumber++;
        lastAccountNumber++;
    }

    // Constructor that allows customizing the initial points balance and rewards discount.
    public LoyaltyAccount(double initialPointsBalance, Discount rewardsDiscount) {
        super(initialPointsBalance, rewardsDiscount);
        this.accountNumber = lastAccountNumber++;
        lastAccountNumber++;
    }

    // Retrieves the customer's account number.
    public double getAccountNumber() {
        return this.accountNumber;
    }
}
