package Customers;

import Products.Discount;

/**
 *
 * @author Donnie Young
 */

public class Loyalty {

    private double points;
    private final double pointRatio = 0.01;
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

    public boolean hasEnoughPointsForDiscount(double total) {
        return this.points >= total * this.rewardsDiscount.getDiscountPercent();
    }

    //Redeems points for a $0.01 discount per dollar spent and a discount based on the rewardsDiscount object
    public void redeemPoints(double total) {
        if (hasEnoughPointsForDiscount(total)) {
            this.points -= total;
        }
    }
    public Discount calculateDiscount(double total) {
        if (hasEnoughPointsForDiscount(total)) {
            return new Discount(this.rewardsDiscount.getDiscountPercent(), total * pointRatio);
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

