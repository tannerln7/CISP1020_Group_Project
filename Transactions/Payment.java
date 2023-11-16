package Transactions;

import Rewards.Discount;
//TODO: Implement LoyaltyAccount class in order to utilize rewards points
public class Payment {
    private double amountDue;
    private String paymentType;
    private Discount discount;
    private final double tax = 0.09;
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
    }
    public Payment(double amountDue, double amountPaid, String paymentType, boolean isPaid) {
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.isPaid = isPaid;
    }
    public double addTax(double amountDue){
        return amountDue + (amountDue * tax);
    }

    public double addTax(double amountDue, Discount discount){
        if (discount == null) {
            return amountDue + (amountDue * tax);
        }else return amountDue + (amountDue * tax) - (amountDue * discount.getDiscountPercent());
    }

    public String getPaymentType(){
        return paymentType;
    }

    public double getAmountPaid(){
        return amountPaid;
    }

    public double getChangeDue(){
        double changeDue = 0;
        if (amountPaid > amountDue) {
            if (discount != null) {
                changeDue = amountPaid - addTax(amountDue, discount);
            } else {
                changeDue = amountPaid - addTax(amountDue);
            }
            changeDue = amountPaid - amountDue;
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
}
