package Transactions;

import Customers.Customer;

import java.util.Date;

public class Transaction{
    private Date date;
    private double transactionNumber;
    private Product product;
    private Customer customer;

    public Transaction(){};
    public Transaction(Date date, double transactionNumber, Product product, Customer customer) {
        this.date = date;
        this.transactionNumber = transactionNumber;
        this.product = product;
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(double transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}