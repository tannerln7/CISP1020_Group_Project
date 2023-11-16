package Transactions;

import Customers.Customer;
import Products.Product;

public class Transaction{
    private Product product;
    private Customer customer;

    public Transaction(){};
    public Transaction(Product product, Customer customer) {
        this.product = product;
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void changeProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void changeCustomer(Customer customer) {
        this.customer = customer;
    }

}