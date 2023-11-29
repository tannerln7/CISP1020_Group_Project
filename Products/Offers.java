/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Products;


/**
 * The Offers class extends the Discount class to represent an offer applied to a product.
 * An offer includes a percentage discount and a subtraction discount.
 */
public class Offers extends Discount{

    /**
     * Default constructor for the Offers class.
     */
    public Offers() {
    }

    /**
     * Constructor that creates an Offers object with a specified percentage discount and subtraction discount.
     *
     * @param discountPercent The percentage discount to be applied.
     * @param subtractionDiscount The subtraction discount to be applied.
     */
    public Offers(double discountPercent, double subtractionDiscount) {
        super(discountPercent, subtractionDiscount);

    }

    /**
     * Retrieves the subtraction discount value.
     *
     * @return The subtraction discount value.
     */
    public double getSubtractionDiscount() {
        return super.getDiscountAmount();
    }

    /**
     * Retrieves the percentage discount value.
     *
     * @return The percentage discount value.
     */
    public double getPercentDiscount(){
        return super.getDiscountPercent();
    }

    /**
     * Sets the subtraction discount value.
     *
     * @param subtractionDiscount The new subtraction discount value.
     */
    public void setSubtractionDiscount(double subtractionDiscount){
        super.setDiscountAmount(subtractionDiscount);
    }


    /**
     * Overrides the default `toString()` method to provide a human-readable representation of the offer.
     *
     * @return A string representation of the offer, including the subtraction discount and percentage discount.
     */
    public String toString() {
        return  "Subtraction Discount: " + super.getDiscountAmount() + " Percent Discount: %" +  super.getDiscountPercent();
    }
    
}

