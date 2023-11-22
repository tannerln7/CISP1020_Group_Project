package Products;

// The Discount class represents a discount applied to a product or purchase.
public class Discount {

    // The percentage discount applied to the original price.
    private double discountPercent;

    // The equivalent dollar amount of the discount.
    private double discountAmount;

    //Super class for Loyalty and Offer classes
    public Discount(){
    }

    // Constructor for a discount with only a percentage value.
    public Discount(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    // Constructor for a discount with both a percentage and dollar amount.
    public Discount(double discountPercent, double discountAmount) {
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
    }

    // Retrieves the percentage discount value.
    public double getDiscountPercent() {
        return discountPercent;
    }

    // Retrieves the equivalent dollar amount of the discount.
    public double getDiscountAmount() {
        return discountAmount;
    }

    // Allows changing the percentage discount value.
    public void changeDiscountPercent(double newDiscountPercent) {
        this.discountPercent = newDiscountPercent;
    }

}
