package Transactions;

import Products.Discount;
//TODO: Implement LoyaltyAccount class in order to utilize rewards points
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
    public static double addTax(double amountDue){
        return amountDue + (amountDue * defaultTax);
    }

    public static double addTax(double amountDue, Discount discount){
        if (discount == null) {
            return amountDue + (amountDue * defaultTax);
        }else return amountDue + (amountDue * defaultTax) - (amountDue * discount.getDiscountPercent());
    }

    public String getPaymentType(){
        return paymentType;
    }

    public double getAmountPaid(){
        return amountPaid;
    }

    public double getChangeDue(){
        if (amountPaid > amountDue) {
            if (discount != null) {
                changeDue = amountPaid - addTax(amountDue, discount);
            } else {
                changeDue = amountPaid - addTax(amountDue);
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
}
