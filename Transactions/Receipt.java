package Transactions;

import Helpers.*;
import java.util.ArrayList;


/**
 * The Receipt class represents a receipt in a retail system.
 * It includes details about the customer, payment, cash register, transactions, tax, change given, and receipt ID.
 */
public class Receipt extends ObjectJson implements JsonIdentifiable {
    private final String customerName;
    private final Payment payment;
    private final double cashRegister;
    private final ArrayList<Transaction> transactions;
    private double tax = 0.09;
    private final double changeGiven;
    private final int id;

    /**
     * Constructs a new Receipt with the specified name, payment, cash register, and transactions.
     *
     * @param name the name on the receipt
     * @param payment the payment for the receipt
     * @param cashRegister the cash register where the receipt was processed
     * @param transactions the transactions on the receipt
     */
    public Receipt(String name, Payment payment, double cashRegister, ArrayList<Transaction> transactions) {
        this.customerName = name;
        this.payment = payment;
        this.cashRegister = cashRegister;
        this.transactions = transactions;
        this.changeGiven = payment.getAmountPaid() - payment.getAmountDue();
        this.id = generateId();
    }

    /**
     * Calculates the subtotal of the transactions on the receipt.
     *
     * @return the subtotal of the transactions
     */
    public double getSubtotal() {
        double subtotal = 0;
        for (Transaction transaction : transactions) {
            subtotal += transaction.getProduct().getPrice();
        }
        return subtotal;
    }

    /**
     * Retrieves the tax applied to the receipt.
     *
     * @return the tax applied to the receipt
     */
    public double getTax() {
        return tax;
    }

    /**
     * Sets the tax applied to the receipt.
     *
     * @param tax the new tax to be applied to the receipt
     */
    public void setTax(double tax) {
        this.tax = tax;
    }

    /**
     * Retrieves the change given to the customer.
     *
     * @return the change given to the customer
     */
    public double getChangeGiven() {
        return changeGiven;
    }

    /**
     * Calculates the total amount due on the receipt, including tax.
     *
     * @return the total amount due
     */
    public double getTotal() {
       return getSubtotal() + (getSubtotal() * getTax());
    }

    /**
     * Retrieves the ID of the receipt.
     *
     * @return the ID of the receipt
     */
    public double getId() {
        return id;
    }

    /**
     * Retrieves the name on the receipt.
     *
     * @return the name on the receipt
     */
    public String getName(){
        return customerName;
    }

    /**
     * Retrieves the payment for the receipt.
     *
     * @return the payment for the receipt
     */
    public Payment getPayment(){
        return payment;
    }

    /**
     * Retrieves the transactions on the receipt.
     *
     * @return the transactions on the receipt
     */
    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }

    /**
     * Generates a unique ID for the receipt.
     *
     * @return the generated ID
     */
    private int generateId() {
        int newId = 0;
        IdTracking idtracking = ObjectJson.objectFromJson("IdTracking", IdTracking.class);
        if(!(idtracking == null)){
            newId = idtracking.getId();
            ObjectJson.objectToJson(idtracking);
        }else{
            System.out.println("IdTracking file not found");
        }
        return newId;
    }

    /**
     * Returns a JSON-compatible ID for this receipt.
     *
     * @return the JSON-compatible ID
     */
    @Override
    public String getJsonId() {
        return "Receipt_" + id;
    }

    /**
     * Provides a string representation of the receipt, including the name, payment, cash register, transactions, tax, and change given.
     *
     * @return a string representation of the receipt
     */
    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        // Header
        receipt.append("RECEIPT - ").append(id).append("\n");
        receipt.append("-------------------------------\n");

        // Cash Register and Customer Details
        receipt.append("Cash Register ID: ").append(Math.round(cashRegister)).append("\n");

        // Transactions
        receipt.append("Items Purchased:\n");
        for (Transaction transaction : transactions) {
            receipt.append(transaction.getProduct().getName())
                    .append(" - $")
                    .append(String.format("%.2f", transaction.getProduct().getPrice()))
                    .append("\n");
        }

        // Subtotal
        receipt.append("Subtotal: $").append(String.format("%.2f", getSubtotal())).append("\n");

        // Tax
        receipt.append("Tax (").append(String.format("%.0f", tax * 100)).append("%): $")
                .append(String.format("%.2f", getSubtotal() * tax)).append("\n");

        // Total
        receipt.append("Total: $").append(String.format("%.2f", payment.getAmountDue())).append("\n");

        // Payment and Change
        receipt.append("Paid with: ").append(payment.getPaymentType()).append("\n");
        receipt.append("Amount Paid: $").append(String.format("%.2f", payment.getAmountPaid())).append("\n");
        receipt.append("Change Given: $").append(String.format("%.2f", getChangeGiven())).append("\n");

        // Footer
        receipt.append("-------------------------------\n");
        receipt.append("Thank you for shopping with us!\n");

        return receipt.toString();
    }
}
