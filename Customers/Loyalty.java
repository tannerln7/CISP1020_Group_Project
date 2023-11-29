package Customers;

import Products.Discount;
/**
 * The Loyalty class manages a customer's loyalty points and rewards discounts.
 */
public class Loyalty {
    private double points;
    private final double pointRatio = 0.1;
    private Discount rewardsDiscount = new Discount(0.05);

    /**
     * Default Constructor that initializes the loyalty account
     */
    public Loyalty() {
    }

    /**
     * Constructor that allows customizing the initial points and rewards discount.
     *
     * @param initialPoints   The initial number of points.
     * @param rewardsDiscount The initial rewards discount.
     */
    public Loyalty(double initialPoints, Discount rewardsDiscount) {
        this.points = initialPoints;
        this.rewardsDiscount = rewardsDiscount;
    }

    /**
     * Adds points to the customer's account.
     *
     * @param amount The amount of points to add.
     */
    public void addPoints(double amount) {
        this.points += amount;
    }

    /**
     * Retrieves the current number of points in the customer's account.
     *
     * @return The current number of points.
     */
    public double getPoints() {
        return this.points;
    }

    /**
     * Sets the number of points in the customer's account.
     *
     * @param points The new number of points.
     */
    public void setPoints(double points) {
        this.points = points;
    }

    /**
     * Checks if the customer has enough points to redeem the specified discount.
     *
     * @param pointsRedeemed The number of points to redeem.
     * @return True if the customer has enough points, false otherwise.
     */
    public boolean hasEnoughPointsForDiscount(double pointsRedeemed) {
        return this.points >= pointsRedeemed;
    }

    /**
     * Redeems the specified amount of points, deducting them from the customer's balance.
     *
     * @param points The number of points to redeem.
     */
    public void redeemPoints(double points) {
        if (hasEnoughPointsForDiscount(points)) {
            this.points -= points;
        }
    }

    /**
     * Calculates the discount based on the points redeemed and the rewards discount.
     *
     * @param pointsRedeemed The number of points to redeem.
     * @return The discount.
     */
    public Discount calculateDiscount(double pointsRedeemed) {
        if (hasEnoughPointsForDiscount(pointsRedeemed)) {
            return new Discount(this.rewardsDiscount.getDiscountPercent(), pointsRedeemed * pointRatio);
        } else {
            return new Discount(this.rewardsDiscount.getDiscountPercent());
        }
    }

    /**
     * Retrieves the percentage value of the rewards discount.
     *
     * @return The percentage value of the rewards discount.
     */
    public double getRewardsDiscountPercent() {
        return this.rewardsDiscount.getDiscountPercent();
    }

    /**
     * Retrieves the Discount object associated with the customer's loyalty account.
     *
     * @return The Discount object.
     */
    public Discount getDiscountObject() {
        return this.rewardsDiscount;
    }

    /**
     * Returns an ArrayList of the discount tiers available to the customer.
     *
     */
    public int getDiscountTiers(double price) {
        int i;
        for (i = 1; i <= Math.floor(this.points / 500); i++) {
            if(price >= i * 10) {
                System.out.println("Tier: " + i);
                System.out.println("Points Required: " + i * 500);
                System.out.println("Discount: $" + i * 10);
            }
        }
        return i;
    }
}