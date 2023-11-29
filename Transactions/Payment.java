package Transactions;

import Customers.Customer;
import Customers.RewardsCustomer;
import Helpers.Round;
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
    private double pointsEarned;
    private double pointsUsed;
    private double savedToday;

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
     * Applies a discount to the total amount due and updates the loyalty account of the customer.
     *
     * @param total the total amount due for the payment
     * @param rewardsDiscount the discount to be applied
     * @param customer the customer making the payment
     */
    public void applyDiscount(double total, Discount rewardsDiscount, RewardsCustomer customer){

        if(rewardsDiscount.getDiscountAmount() > 0 && customer.getLoyaltyAccount().hasEnoughPointsForDiscount(rewardsDiscount.getDiscountAmount() * 50)){
            this.discount = rewardsDiscount;
            double pointDiscountTotal = total - rewardsDiscount.getDiscountAmount();
            this.amountDue = Round.round(pointDiscountTotal - (pointDiscountTotal * this.discount.getDiscountPercent()));
            this.pointsUsed = this.discount.getDiscountAmount() * 50;
            customer.getLoyaltyAccount().redeemPoints(this.pointsUsed);
            this.pointsEarned = (Math.floor((this.amountDue / 20) * 5));
            customer.getLoyaltyAccount().addPoints(this.pointsEarned);
            this.savedToday = Round.round(total - this.amountDue);
        }
        else if (rewardsDiscount.getDiscountAmount() == 0){
            this.amountDue = Round.round (total - (total * discount.getDiscountPercent()));
            this.pointsEarned = (Math.floor((this.amountDue / 20) * 5));
            customer.getLoyaltyAccount().addPoints(this.pointsEarned);
            this.savedToday = Round.round(total - this.amountDue);
        }else{
            System.out.println("You do not have enough points to redeem this discount.");
            this.amountDue = Round.round(total - (total * discount.getDiscountPercent()));
            this.pointsEarned = (Math.floor((this.amountDue / 20) * 5));
            customer.getLoyaltyAccount().addPoints(this.pointsEarned);
            this.savedToday = Round.round(total - this.amountDue);
        }
    }

    /**
     * Applies a discount to the total amount due for a non-rewards customer.
     *
     * @param total the total amount due for the payment
     * @param discount the discount to be applied
     * @param customer the customer making the payment
     */
    public void applyDiscount(double total, Discount discount, Customer customer) {
        this.discount = discount;
        double discountAmountTotal = total - discount.getDiscountAmount();
        this.amountDue = Round.round(discountAmountTotal - (discountAmountTotal * discount.getDiscountPercent()));
        this.savedToday = Round.round(total - this.amountDue);
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
     * Sets the payment type.
     *
     * @param newPaymentType the new payment type
     */
    public void setPaymentType(String newPaymentType) {
        this.paymentType = newPaymentType;
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
     * Sets the amount paid.
     * @param amountPaid the new amount paid
     */
    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
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
        this.changeDue = amountPaid - amountDue;
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

    /**
     * Retrieves the discount applied to the payment.
     *
     * @return the discount
     */
    public Discount getDiscount() {
        if (discount != null) {
            return discount;
        }else{
            return null;
        }
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Retrieves the amount of points earned from the payment.
     *
     * @return the amount of points earned
     */
    public double getPointsEarned() {
        return pointsEarned;
    }

    /**
     * Retrieves the amount of points used from the payment.
     *
     * @return the amount of points used
     */
    public double getPointsUsed() {
        return pointsUsed;
    }

    /**
     * Retrieves the amount saved from the payment.
     *
     * @return the amount saved
     */
    public double getSavedToday() {
        return savedToday;
    }

    /**
     * Static method to calculate a total with tax.
     *
     * @param amountDue the amount due for the payment
     * @return the total amount due with tax
     */
    public static double totalWithTax(double amountDue){
        return amountDue + (amountDue * defaultTax);
    }

    /**
     * Static method to retrieve the default tax rate.
     *
     * @return the default tax rate
     */
    public static double getDefaultTax() {
        return defaultTax;
    }
}
