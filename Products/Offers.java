/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CISP1020_Group_Project.Products;
import Customers.LoyaltyAccount;

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
    private double subtractionDiscount;
    private Product slashed;
    private LoyaltyAccount account;
    
    /**
     * the constructor with no parameters
     */
    public Offers() {
    }        

    
    /**
     * the constructor with two parameters
     * @param slashed the product to be given an offer
     * @param discountPercent the discount as a percentage
     */
    public Offers(Product slashed, double discountPercent) {
        super(discountPercent);
        this.slashed = slashed;
        this.subtractionDiscount = 0;
    }

    
    /**
     * the constructor with two parameters
     * @param subtractionDiscount the discount to be subtracted
     * @param slashed the product to be given an offer
     */
    public Offers(double subtractionDiscount, Product slashed) {
        this.subtractionDiscount = subtractionDiscount;
        this.slashed = slashed;
    }

    /**
     * 
     * @param slashed the product to be given an offer
     * @param account the loyalty account to be added to the discount
     * @param discountPercent the discount as a percentage
     */
    public Offers(Product slashed, LoyaltyAccount account, double discountPercent) {
        super(discountPercent);
        this.slashed = slashed;
        this.account = account;
        this.subtractionDiscount = 0;
    }

    /**
     * 
     * @param subtractionDiscount the discount to be subtracted
     * @param slashed the product to be given an offer
     * @param account the loyalty account to be added to the discount
     */
    public Offers(double subtractionDiscount, Product slashed, LoyaltyAccount account) {
        this.subtractionDiscount = subtractionDiscount;
        this.slashed = slashed;
        this.account = account;
    }

    /**
     * returns the discount to be subtracted
     * @return the discount to be subtracted
     */
    public double getsubtractionDiscount() {
        return subtractionDiscount;
    }

    /**
     * returns the product to be given an offer
     * @return the product to be given an offer
     */
    public Product getSlashed() {
        return slashed;
    }

    /**
     * returns the loyalty account to be added to the discount
     * @return the loyalty account to be added to the discount
     */
    public LoyaltyAccount getAccount() {
        return account;
    }

    /**
     * sets the discount to be subtracted
     * @param subtractionDiscount the discount to be subtracted
     */
    public void setsubtractionDiscount(double subtractionDiscount) {
        this.subtractionDiscount = subtractionDiscount;
    }

    /**
     * sets the product to be given an offer
     * @param slashed the product to be given an offer
     */
    public void setSlashed(Product slashed) {
        this.slashed = slashed;
    }

    /**
     * sets the loyalty account to be added to the discount
     * @param account the loyalty account to be added to the discount
     */
    public void setAccount(LoyaltyAccount account) {
        this.account = account;
    }

    /**
     * gets the total price after all discounts
     * @return the total price after all discounts
     */
    public double totalSubtractedPrice() {
        return this.getDiscountPercent()*(slashed.getPrice()-subtractionDiscount);
    }
    
    /**
     * returns the object as a string
     * @return the object as a string
     */
    public String toString() {
        return "Product " + slashed + " discount " + super.getDiscountPercent();
    }
    
    
}

