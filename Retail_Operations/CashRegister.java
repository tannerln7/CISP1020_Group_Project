package Retail_Operations;

import Transactions.Transaction;
import java.util.ArrayList;
import Helpers.JsonIdentifiable;
//TODO: Add a method to return the total sales for the day
//TODO: Increment the cash register number by 1 for each new cash register created
//TODO: Update to write CashRegister objects to a file
public class CashRegister implements JsonIdentifiable{
    private final ArrayList<Transaction> transactionLog = new ArrayList<>();
    private double cashRegisterNumber;
    private double heldCash;
    private Employee salesAssociate;


    //Creates a new CashRegister instance. This is used to store each Transaction objects and cash register information
    public CashRegister(double cashRegisterNumber, double heldCash, Employee salesAssociate) {
        this.cashRegisterNumber = cashRegisterNumber;
        this.heldCash = heldCash;
        this.salesAssociate = salesAssociate;
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

@Override
    public String getJsonId() {
        return "CashRegister_" + this.getCashRegisterNumber();
    }
}
