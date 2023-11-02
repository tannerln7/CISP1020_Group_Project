package Retail_Operations;

import Transactions.Transaction;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class CashRegister {
    private final ArrayList<Transaction> transactionLog = new ArrayList<>();
    private double cashRegisterNumber;
    private double heldCash;
    private Employee salesAssociate;
    private final ZonedDateTime registerOpenedDate;

    public CashRegister(double cashRegisterNumber, double heldCash, Employee salesAssociate) {
        this.cashRegisterNumber = cashRegisterNumber;
        this.heldCash = heldCash;
        this.salesAssociate = salesAssociate;
        this.registerOpenedDate = ZonedDateTime.now();
    }

    public ArrayList<Transaction> getTransactionLog(){
     return transactionLog;
    }
    public void newTransaction(Transaction transaction) {
        transactionLog.add(transaction);
        heldCash =- transaction.getProduct().getPrice();
    }

    public double getCashRegisterNumber() {
        return cashRegisterNumber;
    }

    public void setCashRegisterNumber(double cashRegisterNumber) {
        this.cashRegisterNumber = cashRegisterNumber;
    }

    public double getHeldCash() {
        return heldCash;
    }

    public void setHeldCash(double heldCash) {
        this.heldCash = heldCash;
    }

    public Employee getSalesAssociate() {
        return salesAssociate;
    }

    public void setSalesAssociate(Employee salesAssociate) {
        this.salesAssociate = salesAssociate;
    }

    public ZonedDateTime getDate() {
        return registerOpenedDate;
    }
}
