package Rewards;

/**
 *
 * @author Donnie Young
 */
//TODO: Implement with LoyaltyAccount class in order to utilize rewards points
//TODO: Incorporate with RewardsCustomer, LoyaltyAccount, and Discount classes
public class Loyalty {

    //Integer for points. I didnot see the point in doing double for this
    private int points;

    //sets points to zero. Will need to get the points from customer.
    public Loyalty() {
        this.points = 0;
    }

    //adds points.
    public void addPoints(int amount) {
        this.points += amount;
    }

    //I was thinking that 100 points would equal redeemable. Will have to fix this at some point
    public void redeemPoints(int amount) {
        if (this.points >= amount) {
            this.points -= amount;
        }
    }

    public int getPoints() {
        return this.points;
    }

    public boolean hasEnoughPointsForDiscount() {
        return this.points >= 100;
    }

    //I think this would apply a 25% discount. 
    public float applyDiscount(float total) {
        if (this.hasEnoughPointsForDiscount()) {
            return total * 0.75f;
        } else {
            return total;
        }
    }}
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

