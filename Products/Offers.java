/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Products;
import Customers.LoyaltyAccount;
import Rewards.Discount;

/**
 *
 * @author WJWhit
 */
//TODO: Extend discount class and incorporate into Payment, Transactions, Receipt to apply discounts to transactions.
//TODO: Implement Offers into Product class to allow for discounts on current products. Will need to updated existing products that are saved to files.

//Pieced of needed code are in other Offers class in Rewards package

public class Offers extends Discount{
    private double subtractionDiscount;
    private Product slashed;
    private LoyaltyAccount account;

    public Offers() {
    }        

    public Offers(Product slashed, double discountPercent) {
        super(discountPercent);
        this.slashed = slashed;
    }

    public Offers(double subtractionDiscount, Product slashed) {
        this.subtractionDiscount = subtractionDiscount;
        this.slashed = slashed;
    }

    public Offers(Product slashed, LoyaltyAccount account, double discountPercent) {
        super(discountPercent);
        this.slashed = slashed;
        this.account = account;
    }

    public Offers(double subtractionDiscount, Product slashed, LoyaltyAccount account) {
        this.subtractionDiscount = subtractionDiscount;
        this.slashed = slashed;
        this.account = account;
    }

    public double getsubtractionDiscount() {
        return subtractionDiscount;
    }

    public Product getSlashed() {
        return slashed;
    }

    public LoyaltyAccount getAccount() {
        return account;
    }

    public void setsubtractionDiscount(double subtractionDiscount) {
        this.subtractionDiscount = subtractionDiscount;
    }

    public void setSlashed(Product slashed) {
        this.slashed = slashed;
    }

    public void setAccount(LoyaltyAccount account) {
        this.account = account;
    }
    
    
    public double totalSubtractedPrice() {
        return account.getDiscountPercent()*(slashed.getPrice()-subtractionDiscount);
    }
    
    
}
