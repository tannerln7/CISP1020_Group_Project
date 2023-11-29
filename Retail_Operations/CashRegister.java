package Retail_Operations;

import Transactions.Receipt;
import java.util.ArrayList;
import Helpers.JsonIdentifiable;
import Transactions.Transaction;

/**
 * The CashRegister class represents a cash register in a retail system.
 */
public class CashRegister implements JsonIdentifiable{
    private final ArrayList<Transaction> transactionLog = new ArrayList<>();
    private int cashRegisterNumber;
    private int lastCashRegisterNumber;
    private double heldCash;
    private Employee salesAssociate;

    /**
     * Constructs a new CashRegister with the specified amount of cash and associated employee.
     *
     * @param heldCash the initial amount of cash in the cash register
     * @param salesAssociate the employee associated with the cash register
     */
    public CashRegister(double heldCash, Employee salesAssociate) {
        this.cashRegisterNumber = generateId();
        this.heldCash = heldCash;
        this.salesAssociate = salesAssociate;
    }

    /**
     * Returns the log of transactions processed by this cash register.
     *
     * @return the log of transactions
     */
    public ArrayList<Transaction> getTransactionLog(){
     return this.transactionLog;
    }

    /**
     * Returns the number of this cash register.
     *
     * @return the cash register number
     */
    public double getCashRegisterNumber() {
        return cashRegisterNumber;
    }

    /**
     * Sets the number of this cash register.
     *
     * @param cashRegisterNumber the new cash register number
     */
    public void setCashRegisterNumber(int cashRegisterNumber) {
        this.cashRegisterNumber = cashRegisterNumber;
    }

    /**
     * Returns the amount of cash held in this cash register.
     *
     * @return the amount of cash held
     */
    public double getHeldCash() {
        return this.heldCash;
    }

    /**
     * Processes a transaction, adding it to the transaction log and updating the amount of cash held.
     *
     * @param receipt the receipt of the transaction
     */
    public void processTransaction(Receipt receipt){
        this.transactionLog.addAll(receipt.getTransactions());
        if(receipt.getPayment().getPaymentType().equals("Cash")){
            this.heldCash = (this.heldCash + receipt.getPayment().getAmountDue()) - receipt.getPayment().getChangeDue();
        }else{
            this.heldCash -= receipt.getPayment().getChangeDue();
        }

    }

    /**
     * Sets the amount of cash held in this cash register.
     *
     * @param heldCash the new amount of cash held
     */
    public void setHeldCash(double heldCash) {
        this.heldCash = heldCash;
    }

    /**
     * Returns the employee associated with this cash register.
     *
     * @return the associated employee
     */
    public Employee getSalesAssociate() {
        return salesAssociate;
    }

    /**
     * Sets the employee associated with this cash register.
     *
     * @param salesAssociate the new associated employee
     */
    public void setSalesAssociate(Employee salesAssociate) {
        this.salesAssociate = salesAssociate;
    }

    /**
     * Generates a unique ID for this cash register.
     *
     * @return the generated ID
     */
    private  int generateId() {
        return ++lastCashRegisterNumber;
    }

    /**
     * Calculates the total sales processed by this cash register.
     *
     * @return the total sales
     */
    public double getTotalSales(){
        double totalSales = 0;
        for(Transaction transaction : transactionLog){
            totalSales += transaction.getProduct().getPrice();
        }
        return totalSales;
    }
    /**
     * Returns a JSON-compatible ID for this cash register.
     *
     * @return the JSON-compatible ID
     */
    @Override
    public String getJsonId() {
        return "CashRegister_" + this.getCashRegisterNumber();
    }
}
