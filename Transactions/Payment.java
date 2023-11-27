package Transactions;

import Customers.RewardsCustomer;
import Products.Discount;
import Retail_Operations.CashRegister;
/**
 * The Payment class represents a payment made by a customer in a retail system.
 * It includes details about the amount due, the payment type, any discounts applied, the tax rate, and the change due.
 */
public class Payment {
    private double amountDue;
    private String paymentType;
    private Discount discount;
    private double tax;
    private static final double defaultTax = 0.09;
    private double changeDue;
    private double amountPaid;
    private boolean isPaid;

    /**
     * Default constructor for the Payment class.
     */
    public Payment(){}

    /**
     * Constructs a new Payment with the specified amount due, amount paid, payment type, discount, and payment status.
     *
     * @param amountDue the amount due for the payment
     * @param amountPaid the amount paid for the payment
     * @param paymentType the type of the payment
     * @param discount the discount applied to the payment
     * @param isPaid the payment status
     */
    public Payment(double amountDue, double amountPaid, String paymentType, Discount discount, boolean isPaid) {
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.discount = discount;
        this.isPaid = isPaid;
        this.tax = defaultTax;
    }

    /**
     * Constructs a new Payment with the specified amount due, amount paid, payment type, and payment status.
     *
     * @param amountDue the amount due for the payment
     * @param amountPaid the amount paid for the payment
     * @param paymentType the type of the payment
     * @param isPaid the payment status
     */
    public Payment(double amountDue, double amountPaid, String paymentType, boolean isPaid) {
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.isPaid = isPaid;
        this.tax = defaultTax;
    }

    /**
     * Constructs a new Payment with the specified amount due, amount paid, payment type, discount, tax, and payment status.
     *
     * @param amountDue the amount due for the payment
     * @param amountPaid the amount paid for the payment
     * @param paymentType the type of the payment
     * @param discount the discount applied to the payment
     * @param tax the tax applied to the payment
     * @param isPaid the payment status
     */
    public Payment(double amountDue, double amountPaid, String paymentType, Discount discount, double tax, boolean isPaid) {
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.discount = discount;
        this.tax = tax;
        this.isPaid = isPaid;
    }

    /**
     * Constructs a new Payment with the specified amount due, amount paid, payment type, tax, and payment status.
     *
     * @param amountDue the amount due for the payment
     * @param amountPaid the amount paid for the payment
     * @param paymentType the type of the payment
     * @param tax the tax applied to the payment
     * @param isPaid the payment status
     */
    public Payment(double amountDue, double amountPaid, String paymentType, double tax, boolean isPaid) {
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.tax = tax;
        this.isPaid = isPaid;
    }

    /**
     * Calculates the total amount due with tax.
     *
     * @param amountDue the amount due for the payment
     * @return the total amount due with tax
     */
    public static double totalWithTax(double amountDue){
        return amountDue + (amountDue * defaultTax);
    }

    /**
     * Applies a discount to the total amount due and updates the loyalty account of the customer.
     *
     * @param total the total amount due for the payment
     * @param discount the discount to be applied
     * @param customer the customer making the payment
     */
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

    /**
     * Retrieves the payment type.
     *
     * @return the payment type
     */
    public String getPaymentType(){
        return paymentType;
    }

    /**
     * Retrieves the amount paid.
     *
     * @return the amount paid
     */
    public double getAmountPaid(){
        return amountPaid;
    }

    /**
     * Retrieves the amount due.
     *
     * @return the amount due
     */
    public double getAmountDue(){
        return amountDue;
    }

    /**
     * Calculates the change due after the payment.
     *
     * @return the change due
     */
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

    /**
     * Changes the amount paid.
     *
     * @param newAmountPaid the new amount paid
     */
    public void changeAmountPaid(double newAmountPaid){
        this.amountPaid = newAmountPaid;
    }

    /**
     * Retrieves the payment status.
     *
     * @return the payment status
     */
    public boolean getIsPaid(){
        return isPaid;
    }

    /**
     * Sets the payment status.
     *
     * @param newIsPaid the new payment status
     */
    public void setIsPaid(boolean newIsPaid){
        this.isPaid = newIsPaid;
    }

    /**
     * Sets the tax applied to the payment.
     *
     * @param newTax the new tax
     */
    public void setTax(double newTax){
        this.tax = newTax;
    }

    /**
     * Retrieves the tax applied to the payment.
     *
     * @return the tax
     */
    public double getTax(){
        return tax;
    }

    /**
     * Completes the payment, updates the payment status, and processes the transaction.
     *
     * @param cashRegister the cash register where the payment is made
     * @param receipt the receipt of the payment
     */
    public void completePayment(CashRegister cashRegister, Receipt receipt) {
        this.isPaid = true;
        cashRegister.processTransaction(receipt);
    }
}
