package Transactions;

import Customers.Customer;
import Retail_Operations.CashRegister;
import Rewards.Discount;

import java.util.ArrayList;

//TODO: Incorporate Elijah's Product Class
public class Receipt {
    private final Customer customer;
    private final Payment payment;
    private final CashRegister cashRegister;
    private final ArrayList<Transaction> transactions;
    private Discount discount;
    private double tax = 0.09;
    private double changeGiven;


    public Receipt(Customer customer, Payment payment, CashRegister cashRegister, ArrayList<Transaction> transactions) {
        this.customer = customer;
        this.payment = payment;
        this.cashRegister = cashRegister;
        this.transactions = transactions;
        this.changeGiven = payment.getAmountPaid() - getSubtotal();
    }

    public Receipt(Customer customer, Payment payment, CashRegister cashRegister, ArrayList<Transaction> transactions, Discount discount) {
        this.customer = customer;
        this.payment = payment;
        this.cashRegister = cashRegister;
        this.transactions = transactions;
        this.discount = discount;
    }


    public double getSubtotal() {
        double subtotal = 0;
        for(Transaction transaction : transactions) {
            subtotal += transaction.getProduct().getPrice();
        }return subtotal;
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

    public double getTotal(){
        return getSubtotal() + (getSubtotal() * getTax());
    }
}
