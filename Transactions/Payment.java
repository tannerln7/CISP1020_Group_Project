package Transactions;

import Customers.RewardsCustomer;
import Products.Discount;
import Retail_Operations.CashRegister;
public class Payment {
    private double amountDue;
    private String paymentType;
    private Discount discount;
    private double tax;
    private static final double defaultTax = 0.09;
    private double changeDue;
    private double amountPaid;
    private boolean isPaid;

    public Payment(){}

    public Payment(double amountDue, double amountPaid, String paymentType, Discount discount, boolean isPaid) {
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.discount = discount;
        this.isPaid = isPaid;
        this.tax = defaultTax;
    }
    public Payment(double amountDue, double amountPaid, String paymentType, boolean isPaid) {
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.isPaid = isPaid;
        this.tax = defaultTax;
    }
    public Payment(double amountDue, double amountPaid, String paymentType, Discount discount, double tax, boolean isPaid) {
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.discount = discount;
        this.tax = tax;
        this.isPaid = isPaid;
    }
    public Payment(double amountDue, double amountPaid, String paymentType, double tax, boolean isPaid) {
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.tax = tax;
        this.isPaid = isPaid;
    }
    public static double totalWithTax(double amountDue){
        return amountDue + (amountDue * defaultTax);
    }

    public void applyDiscount(double total, Discount discount, RewardsCustomer customer){
        if(customer.getLoyaltyAccount().hasEnoughPointsForDiscount(discount.getDiscountAmount() * 10)){
            this.discount = discount;
            this.amountDue = (total - (total * discount.getDiscountPercent())) - discount.getDiscountAmount();
            customer.getLoyaltyAccount().redeemPoints(discount.getDiscountAmount() * 10);
        }
        else{
            this.discount = new Discount(customer.getLoyaltyAccount().getRewardsDiscountPercent());
            this.amountDue = (total - (total * discount.getDiscountPercent()));
        }
    }


    public String getPaymentType(){
        return paymentType;
    }

    public double getAmountPaid(){
        return amountPaid;
    }
    public double getAmountDue(){
        return amountDue;
    }

    public double getChangeDue(){
        if (amountPaid > amountDue) {
            if (discount != null) {
                changeDue = amountPaid - (amountDue * discount.getDiscountPercent() + discount.getDiscountAmount());
            } else {
                changeDue = amountPaid - totalWithTax(amountDue);
            }
        }
        return changeDue;
    }

    public void changeAmountPaid(double newAmountPaid){
        this.amountPaid = newAmountPaid;
    }

    public boolean getIsPaid(){
        return isPaid;
    }

    public void setIsPaid(boolean newIsPaid){
        this.isPaid = newIsPaid;
    }

    public void setTax(double newTax){
        this.tax = newTax;
    }
    public double getTax(){
        return tax;
    }
    public void completePayment(CashRegister cashRegister, Receipt receipt) {
        this.isPaid = true;
        cashRegister.processTransaction(receipt);
    }
}
