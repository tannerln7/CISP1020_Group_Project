package Products;

import Helpers.JsonIdentifiable;

/**
 * The Discount class represents a discount applied to a product or purchase.
 * It implements the JsonIdentifiable interface.
 */
public class Discount implements JsonIdentifiable {
     private double discountPercent;
     private double discountAmount;

    /**
     * Default constructor for the Discount class.
     */
    public Discount(){
    }

    /**
     * Constructor that creates a Discount object with a specified percentage discount.
     *
     * @param discountPercent The percentage discount to be applied.
     */
    public Discount(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    /**
     * Constructor that creates a Discount object with a specified percentage discount and dollar amount.
     *
     * @param discountPercent The percentage discount to be applied.
     * @param discountAmount The dollar amount of the discount.
     */
    public Discount(double discountPercent, double discountAmount) {
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
    }

    /**
     * Retrieves the percentage discount value.
     *
     * @return The percentage discount value.
     */
    public double getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Retrieves the dollar discount amount of the discount.
     *
     * @return The dollar amount of the discount.
     */
    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the percentage discount value.
     *
     * @param newDiscountPercent The new percentage discount value.
     */
    public void setDiscountPercent(double newDiscountPercent) {
        this.discountPercent = newDiscountPercent;
    }

    /**
     * Sets the dollar amount of the discount.
     *
     * @param newDiscountAmount The new dollar amount of the discount.
     */
    public void setDiscountAmount(double newDiscountAmount){
        this.discountAmount = newDiscountAmount;
    }


    /**
     * Overrides the `getJsonId()` method to generate a JSON-compatible ID for the Discount object.
     *
     * @return The JSON-compatible ID for the Discount object.
     */
    @Override
    public String getJsonId() {
        return "Discount_$" + discountAmount + "_%" + discountPercent;
    }
}
