package Transactions;

import Products.Product;

/**
 * The Transaction class represents a individual product transaction in a retail system.
 * It includes details about the product involved in the transaction.
 */
public class Transaction extends Payment {
    private Product product;

    /**
     * Constructs a new Transaction with the specified product.
     *
     * @param product the product involved in the transaction
     */
    public Transaction(Product product) {
        this.product = product;
    }

    /**
     * Retrieves the product involved in the transaction.
     *
     * @return the product involved in the transaction
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Changes the product involved in the transaction.
     *
     * @param product the new product involved in the transaction
     */
    public void changeProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns a string representation of the transaction.
     *
     * @return a string representation of the transaction
     */
    @Override
    public String toString() {
        return "Product: " + product.getName() + " | Price: " + product.getPrice();
    }
}
