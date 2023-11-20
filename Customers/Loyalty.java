package Customers;

import Products.Discount;

/**
 *
 * @author Donnie Young
 */

public class Loyalty {

    private double points;
    private final double pointRatio = 0.1;
    private Discount rewardsDiscount = new Discount(0.05);

    //sets points to sign up bonus starting with 10000 points.
    public Loyalty() {
        this.points = 10000;
    }
    //Used to create a LoyaltyAccount object with custom parameters
    public Loyalty(double initialPoints, Discount rewardsDiscount) {
        this.points = initialPoints;
        this.rewardsDiscount = rewardsDiscount;
    }

    //adds points.
    public void addPoints(double amount) {
        this.points += amount;
    }

    public double getPoints() {
        return this.points;
    }

    public boolean hasEnoughPointsForDiscount(double pointsRedeemed) {
        return this.points >= pointsRedeemed * this.rewardsDiscount.getDiscountPercent();
    }


    public void redeemPoints(double points) {
        if (hasEnoughPointsForDiscount(points)) {
            this.points -= points;
        }
    }
    //1 point per 10 dollars spent. Points are worth 10 cents each. 100 points = $10.00
    public Discount calculateDiscount(double pointsRedeemed) {
        if (hasEnoughPointsForDiscount(pointsRedeemed)) {
            return new Discount(this.rewardsDiscount.getDiscountPercent(), pointsRedeemed * pointRatio);
        } else {
            return new Discount(this.rewardsDiscount.getDiscountPercent());
        }
    }
    public double getRewardsDiscountPercent(){
        return this.rewardsDiscount.getDiscountPercent();
    }
}
//   Following code is meant to give an idea of what I was thinking would call or use loyalty class

//public float calculateTotalPrice(List<Item> items, LoyaltyProgram loyaltyProgram) {
//    float total = 0.0f;
//   for (Item item : items) {
//       total += item.getPrice();
//   }

// Apply the loyalty discount if the customer has enough points
//    if (loyaltyProgram.hasEnoughPointsForDiscount()) {
//        total = loyaltyProgram.applyDiscount(total);
//    }
//   return total;
//}

//LoyaltyProgram loyaltyProgram = new LoyaltyProgram();

// Calculate the total price of the items, applying the loyalty discount if necessary
//float totalPrice = calculateTotalPrice(items, loyaltyProgram);

// Print the receipt
//printReceipt(totalPrice);
//}

