package Customers;

import java.math.BigInteger;
import Helpers.Utilities;
import Rewards.Discount;

public class LoyaltyAccount {
    private final BigInteger accountNumber;
    private double pointsBalance;
    private final Discount rewardsDiscount;


    public LoyaltyAccount(String customerName) {
        this.accountNumber = Utilities.generateAccountNumber(customerName);
        this.pointsBalance = 10000;
        this.rewardsDiscount = new Discount(0.05);
    }

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
    public void changeDiscountPercent(double newDiscountPercent) {
        this.rewardsDiscount.changeDiscountPercent(newDiscountPercent);
    }
}