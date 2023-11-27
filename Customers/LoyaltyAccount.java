package Customers;
import Helpers.IdTracking;
import Helpers.ObjectJson;
import Products.Discount;

/**
 * The LoyaltyAccount class extends the Loyalty class to represent a customer's loyalty account, including an account number.
 */
public class LoyaltyAccount extends Loyalty{
     private final int accountNumber;

    /**
     * Default constructor. Creates a default rewards discount object.
     * Also generates a unique account number for the customer.
     */
     public LoyaltyAccount () {
        super();
        this.accountNumber = generateAccountNumber();
    }

    /**
     * Constructor that allows customizing the initial points balance and rewards discount.
     * Also generates a unique account number for the customer.
     *
     * @param initialPointsBalance The initial points balance for the account.
     * @param rewardsDiscount The rewards discount for the account.
     */
    public LoyaltyAccount(double initialPointsBalance, Discount rewardsDiscount) {
        super(initialPointsBalance, rewardsDiscount);
        this.accountNumber = generateAccountNumber();
    }

    /**
     * Retrieves the customer's account number.
     *
     * @return The customer's account number.
     */
    public double getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Generates a unique account number for the customer.
     * The account number is retrieved from the IdTracking object.
     *
     * @return The generated account number.
     */
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
