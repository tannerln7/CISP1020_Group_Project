package Customers;
import Helpers.IdTracking;
import Helpers.ObjectJson;
import Products.Discount;

// The LoyaltyAccount class extends the Loyalty class to represent a customer's loyalty account, including an account number.
public class LoyaltyAccount extends Loyalty{

    // The unique account number associated with the customer.
    private final int accountNumber;

    //Used as a default constructor. Uses default pointsBalance and creates a default rewardsDiscount object
    public LoyaltyAccount () {
        super();
        this.accountNumber = generateAccountNumber();
    }

    // Constructor that allows customizing the initial points balance and rewards discount.
    public LoyaltyAccount(double initialPointsBalance, Discount rewardsDiscount) {
        super(initialPointsBalance, rewardsDiscount);
        this.accountNumber = generateAccountNumber();
    }

    // Retrieves the customer's account number.
    public double getAccountNumber() {
        return this.accountNumber;
    }

    private int generateAccountNumber() {
        int newId = 0;
        IdTracking idtracking = ObjectJson.objectFromJson("IdTracking", IdTracking.class);
        if (!(idtracking == null)) {
            newId = idtracking.getAccountId();
            ObjectJson.objectToJson(idtracking);
        } else {
            System.out.println("IdTracking file not found");
        }
        return newId;
    }
}
