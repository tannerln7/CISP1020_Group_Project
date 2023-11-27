package Retail_Operations;

import Transactions.Receipt;

import java.util.ArrayList;
import Helpers.JsonIdentifiable;
import Transactions.Transaction;

@SuppressWarnings("unused")
public class CashRegister implements JsonIdentifiable{
    private final ArrayList<Transaction> transactionLog = new ArrayList<>();
    private int cashRegisterNumber;
    private int lastCashRegisterNumber;
    private double heldCash;
    private Employee salesAssociate;


    //Creates a new CashRegister instance. This is used to store each Transaction objects and cash register information
    public CashRegister(double heldCash, Employee salesAssociate) {
        this.cashRegisterNumber = generateId();
        this.heldCash = heldCash;
        this.salesAssociate = salesAssociate;
    }

    //Returns the ArrayList containing the list of all transactions from this cash register
    public ArrayList<Transaction> getTransactionLog(){
     return this.transactionLog;
    }


    public double getCashRegisterNumber() {
        return cashRegisterNumber;
    }

    public void setCashRegisterNumber(int cashRegisterNumber) {
        this.cashRegisterNumber = cashRegisterNumber;
    }

    public double getHeldCash() {
        return this.heldCash;
    }

    public void processTransaction(Receipt receipt){
        this.transactionLog.addAll(receipt.getTransactions());
        if(receipt.getPayment().getPaymentType().equals("Cash")){
            this.heldCash = (this.heldCash + receipt.getPayment().getAmountDue()) - receipt.getPayment().getChangeDue();
        }else{
            this.heldCash -= receipt.getPayment().getChangeDue();
        }

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

    private  int generateId() {
        return ++lastCashRegisterNumber;
    }

    public double getTotalSales(){
        double totalSales = 0;
        for(Transaction transaction : transactionLog){
            totalSales += transaction.getProduct().getPrice();
        }
        return totalSales;
    }
@Override
    public String getJsonId() {
        return "CashRegister_" + this.getCashRegisterNumber();
    }
}
