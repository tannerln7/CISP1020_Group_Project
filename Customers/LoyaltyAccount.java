package Customers;
import Products.Discount;

public class LoyaltyAccount extends Loyalty{

    private final int accountNumber;
    private final Discount rewardsDiscount;

    //Used as a default constructor. Uses default pointsBalance and creates a default rewardsDiscount object
    public LoyaltyAccount () {
        //TODO: Replace hardcoded account number with utility recursive method to generate account number
        super();
        this.accountNumber = 100001;
        this.rewardsDiscount = new Discount(0.05);
    }

    //Used to create a LoyaltyAccount object with custom parameters
    public LoyaltyAccount(double initialPointsBalance, double discountPercent) {
        //TODO: Replace hardcoded account number with utility recursive method to generate account number
        this.accountNumber = 100001;
        this.rewardsDiscount = new Discount(discountPercent);
    }

    public double getAccountNumber() {
        return accountNumber;
    }

    public double getDiscountPercent() {
        return this.rewardsDiscount.getDiscountPercent();
    }

}