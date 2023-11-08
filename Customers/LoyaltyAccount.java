package Customers;

import java.math.BigInteger;
import Helpers.Utilities;
import Rewards.Discount;

public class LoyaltyAccount{

    //Had to use BigInteger for 16-digit Account number
    private final BigInteger accountNumber;
    private double pointsBalance;
    private final Discount rewardsDiscount;

    //Used as a default constructor. Uses default pointsBalance and creates a default rewardsDiscount object
    public LoyaltyAccount(String customerName) {
        this.accountNumber = Utilities.generateAccountNumber(customerName);
        this.pointsBalance = 10000;
        this.rewardsDiscount = new Discount(0.05);
    }

    //Used to create a LoyaltyAccount object with custom parameters
    public LoyaltyAccount(String customerName, double initialPointsBalance, double discountPercent) {
        this.accountNumber = Utilities.generateAccountNumber(customerName);
        this.pointsBalance = initialPointsBalance;
        this.rewardsDiscount = new Discount(discountPercent);
    }

    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    public void addPoints(double points) {
        this.pointsBalance += points;
    }

    public void usePoints(double points) {
        this.pointsBalance -= points;
    }

    public double getPointsBalance() {
        return this.pointsBalance;
    }
    public double getDiscountPercent() {
        return this.rewardsDiscount.getDiscountPercent();
    }

    //Uses the changeDiscountPercent method in the Discount class to assign a new discount percent to this class's rewardsDiscount instance
    //Not sure if the "this." is needed or not in this case
    public void changeDiscountPercent(double newDiscountPercent) {
        this.rewardsDiscount.changeDiscountPercent(newDiscountPercent);
    }
}