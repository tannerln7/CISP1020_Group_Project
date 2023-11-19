package Transactions;

import Customers.Customer;
import Retail_Operations.CashRegister;
import Helpers.*;
import java.util.ArrayList;



public class Receipt implements JsonIdentifiable {
    private final Customer customer;
    private final Payment payment;
    private final CashRegister cashRegister;
    private final ArrayList<Transaction> transactions;
    private double tax = 0.09;
    private final double changeGiven;
    private final int id;
    private static int lastId;


    public Receipt(Customer customer, Payment payment, CashRegister cashRegister, ArrayList<Transaction> transactions, double changeGiven) {
        this.customer = customer;
        this.payment = payment;
        this.cashRegister = cashRegister;
        this.transactions = transactions;
        this.changeGiven = changeGiven;
        this.id = generateId();
        lastId = id;
        customer.addReceipt(this);
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (Transaction transaction : transactions) {
            subtotal += transaction.getProduct().getPrice();
        }
        return subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getChangeGiven() {
        return changeGiven;
    }

    public double getTotal() {
       return getSubtotal() + (getSubtotal() * getTax());
    }

    public double getId() {
        return id;
    }

    private static int generateId() {
         lastId += 1;
        return lastId;
    }
    @Override
    public String getJsonId() {
        return "Receipt_" + id;
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        // Header
        receipt.append("RECEIPT - ").append(id).append("\n");
        receipt.append("-------------------------------\n");

        // Cash Register and Customer Details
        receipt.append("Cash Register ID: ").append(Math.round(cashRegister.getCashRegisterNumber())).append("\n");
        receipt.append("Customer: ").append(customer.getName()).append("\n");

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
        receipt.append("Total: $").append(String.format("%.2f", getTotal())).append("\n");

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
