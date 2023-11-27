package Transactions;

import Products.Product;

public class Transaction{
    private Product product;

    public Transaction(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void changeProduct(Product product) {
        this.product = product;
    }


}