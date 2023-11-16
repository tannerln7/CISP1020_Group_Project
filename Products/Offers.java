/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Products;
import Customers.LoyaltyAccount;

/**
 *
 * @author WJWhit
 */
//TODO: Extend discount class and incorporate into Payment, Transactions, Receipt to apply discounts to transactions.
//TODO: Implement Offers into Product class to allow for discounts on current products. Will need to updated existing products that are saved to files.

//Pieced of needed code are in other Offers class in Rewards package

public class Offers {
    private double discount;
    private Product slashed;
    private LoyaltyAccount account;

    public Offers() {
    }        
    
    public Offers(double discount, Product slashed){
        this.discount = discount;
        this.slashed = slashed;
    }
    
    public Offers(double discount, Product slashed, LoyaltyAccount account){
        this.discount = discount;
        this.slashed = slashed;
        this.account = account;
    }

    public double getDiscount() {
        return discount;
    }

    public Product getSlashed() {
        return slashed;
    }

    public LoyaltyAccount getAccount() {
        return account;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setSlashed(Product slashed) {
        this.slashed = slashed;
    }

    public void setAccount(LoyaltyAccount account) {
        this.account = account;
    }
    
    public double totalPercentagePrice(){
        return account.getDiscountPercent()*((discount/100)*slashed.getPrice());
    }
    
    public double totalSubtractedPrice() {
        return account.getDiscountPercent()*(slashed.getPrice()-discount);
    }
    
    
}
