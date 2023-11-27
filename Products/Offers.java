/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Products;

/**
 *
 * @author WJWhit
 */
//TODO: Give offer in exchange for a set amount of reward points.
public class Offers extends Discount{
    
    /**
     * the constructor with no parameters
     */
    public Offers() {
    }        

    /**
     * the constructor with two parameters
     * @param discountPercent the discount as a percentage
     */
    public Offers(double discountPercent, double subtractionDiscount) {
        super(discountPercent, subtractionDiscount);

    }

    /**
     * returns the discount to be subtracted
     * @return the discount to be subtracted
     */
    public double getSubtractionDiscount() {
        return super.getDiscountAmount();
    }
    public double getPercentDiscount(){
        return super.getDiscountPercent();
    }

    public void setSubtractionDiscount(double subtractionDiscount){
        super.setDiscountAmount(subtractionDiscount);
    }

    
    /**
     * returns the object as a string
     * @return the object as a string
     */
    public String toString() {
        return  "Subtraction Discount: " + super.getDiscountAmount() + " Percent Discount: %" +  super.getDiscountPercent();
    }
    
}

