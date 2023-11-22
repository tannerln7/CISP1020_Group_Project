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
//TODO: Incorporate into Payment, Transactions, Receipt to apply discounts to transactions.
//TODO: Implement Offers into Product class to allow for discounts on current products. Will need to updated existing products that are saved to files.
//BODY: Need some sort of menu to allow for the creation of offers on a per product and/or per customer basis.

//TODO: Start working on the Management menu for Offers.
//BODy: This will be a menu that allows staff members to add, remove, and edit offers on current products. For example, a 25% discount on Milk..etc

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

