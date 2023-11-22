package Customers;

import Products.Discount;

// The Loyalty class manages a customer's loyalty points and rewards discounts.
public class Loyalty {

    // Represents the total accumulated loyalty points for the customer.
    private double points;

    // The conversion rate between spending and points earned.
    private final double pointRatio = 0.1;

    // The discount applied to purchases when redeeming points.
    private Discount rewardsDiscount = new Discount(0.05);

    //Constructor that initializes the loyalty account with a sign-up bonus of 10,000 points.
    public Loyalty() {
        this.points = 10000;
    }

    // Constructor that allows customizing the initial points and rewards discount.
    public Loyalty(double initialPoints, Discount rewardsDiscount) {
        this.points = initialPoints;
        this.rewardsDiscount = rewardsDiscount;
    }

    //adds points.
    public void addPoints(double amount) {
        this.points += amount;
    }

    // Constructor that allows customizing the initial points and rewards discount.
    public double getPoints() {
        return this.points;
    }

    // Checks if the customer has enough points to redeem the specified discount.
    public boolean hasEnoughPointsForDiscount(double pointsRedeemed) {
        return this.points >= pointsRedeemed * this.rewardsDiscount.getDiscountPercent();
    }

    // Redeems the specified amount of points, deducting them from the customer's balance.
    public void redeemPoints(double points) {
        if (hasEnoughPointsForDiscount(points)) {
            this.points -= points;
        }
    }
    
    //1 point per 10 dollars spent. Points are worth 10 cents each. 100 points = $10.00
    // Calculates the discount based on the points redeemed and the rewards discount.
    public Discount calculateDiscount(double pointsRedeemed) {
        if (hasEnoughPointsForDiscount(pointsRedeemed)) {
            return new Discount(this.rewardsDiscount.getDiscountPercent(), pointsRedeemed * pointRatio);
        } else {
            return new Discount(this.rewardsDiscount.getDiscountPercent());
        }
    }

    // Retrieves the percentage value of the rewards discount.
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

