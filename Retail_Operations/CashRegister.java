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


    //Creates a new CashRegister instance. This is used to store each Transaction objects and cash register information
    public CashRegister(double cashRegisterNumber, double heldCash, Employee salesAssociate) {
        this.cashRegisterNumber = cashRegisterNumber;
        this.heldCash = heldCash;
        this.salesAssociate = salesAssociate;
        this.registerOpenedDate = ZonedDateTime.now();
    }

    //Returns the ArrayList containing the list of all transactions from this cash register
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

    //Set the available cash in the cash register drawer
    public void setHeldCash(double heldCash) {
        this.heldCash = heldCash;
    }

    //returns the sales associate assigned to the cash register
    public Employee getSalesAssociate() {
        return salesAssociate;
    }

    public void setSalesAssociate(Employee salesAssociate) {
        this.salesAssociate = salesAssociate;
    }

    //returns the date and time the CashRegister object was created
    public ZonedDateTime getDate() {
        return registerOpenedDate;
    }
}
