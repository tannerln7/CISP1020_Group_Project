package Transactions;

import Helpers.Round;
import java.util.ArrayList;

/**
 * The TransactionList class represents a list of transactions in a retail system.
 * It includes methods for adding and removing transactions, getting the subtotal of the transactions, and retrieving the list of transactions.
 */
public class TransactionList {
    private final ArrayList<Transaction> transactions;

    /**
     * Constructs a new TransactionList.
     */
    public TransactionList() {
        transactions = new ArrayList<>();
    }

    /**
     * Adds a transaction to the list.
     *
     * @param transaction the transaction to add
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Removes a transaction from the list.
     *
     * @param transaction the transaction to remove
     */
    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    /**
     * Calculates the subtotal of the transactions in the list.
     *
     * @return the subtotal of the transactions
     */
    public double getSubTotal() {
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getProduct().getPrice();
        }
        return Round.round(total);
    }

    /**
     * Retrieves the list of transactions.
     *
     * @return the list of transactions
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Returns a string representation of the TransactionList.
     *
     * @return a string representation of the TransactionList
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Transaction transaction : transactions) {
            output.append(transaction).append("\n");
        }
        output.append("Subtotal: $").append(getSubTotal()).append("\n");
        return output.toString();
    }
}
