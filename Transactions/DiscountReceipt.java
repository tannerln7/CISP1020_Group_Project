package Transactions;
import Products.Discount;
import Customers.*;
import Retail_Operations.*;
import org.jetbrains.annotations.NotNull;

public class DiscountReceipt extends Receipt{
    private Discount discount;
    public DiscountReceipt(Customer customer, Payment payment, CashRegister cashRegister, TransactionList transactions, double changeGiven, Discount discount){
        super(customer, payment, cashRegister, transactions.getTransactions(), changeGiven);
        this.discount = discount;
    }
    public double getDiscount() {
        return discount.getDiscountPercent();
    }
    public void changeDiscount(double discount) {
        this.discount = new Discount(discount);
    }

    @Override
    public double getTotal() {
        return getSubtotal() + (getSubtotal() * getTax()) - (getSubtotal() * getDiscount());
    }

    @Override
    public String toString() {

        String baseReceipt = super.toString();

        int taxIndex = baseReceipt.indexOf("Tax (");                                            //Find the tax line in the base receipt from the super class
        if (taxIndex != -1 && getDiscount() > 0) {                                              //If the tax line exists and there's a discount to apply
            StringBuilder receiptWithDiscount = buildDiscountReceipt(baseReceipt, taxIndex);    //Build the receipt with the discount using the helper method
            return receiptWithDiscount.toString();                                              //Return the receipt with the discount
        }

        return baseReceipt;                                                                     // If the tax line doesn't exist or there's
                                                                                                // no discount to apply, return the base receipt
    }

    @NotNull
    private StringBuilder buildDiscountReceipt(String baseReceipt, int taxIndex) {
        double discountAmount = getSubtotal() * getDiscount();                                                      //Calculate the discount amount

        String discountString = String.format("Discount: %.2f%% - $%.2f\n", getDiscount() * 100, discountAmount);   //Create the discount string in the
                                                                                                                    //format "Discount: 10.00% - $1.00"

        StringBuilder receiptWithDiscount = new StringBuilder(baseReceipt);                                         //Create the base receipt
        receiptWithDiscount.insert(taxIndex, discountString);                                                       //Insert the discount string into the receipt
        return receiptWithDiscount;                                                                                 //Return the receipt with the discount
    }
}

