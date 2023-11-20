package Transactions;

import java.util.ArrayList;

public class TransactionList {
    private final ArrayList<Transaction> transactions;
    public TransactionList() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }
    public double getSubTotal() {
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getProduct().getPrice();
        }
        return total;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
