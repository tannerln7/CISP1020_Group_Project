package Rewards;

public class Discount {
    private double discountPercent;

    public Discount(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
    public void changeDiscountPercent(double newDiscountPercent) {
        this.discountPercent = newDiscountPercent;
    }

}
