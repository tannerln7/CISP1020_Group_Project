package Transactions;
import Products.Discount;
import Customers.*;
import Retail_Operations.*;
import org.jetbrains.annotations.NotNull;

public class DiscountReceipt extends Receipt{
    private final Discount discount;
    public DiscountReceipt(String customerName, Payment payment, CashRegister cashRegister, TransactionList transactions, double changeGiven, Discount discount){
        super(customerName, payment, cashRegister, transactions.getTransactions(), changeGiven);
        this.discount = discount;
    }

    @Override
    public double getTotal() {
        return getSubtotal() + (getSubtotal() * getTax()) - (getSubtotal() * discount.getDiscountPercent());
    }

    @Override
    public String toString() {

        String baseReceipt = super.toString();

        int taxIndex = baseReceipt.indexOf("Tax (");                                            //Find the tax line in the base receipt from the super class
        if (taxIndex != -1 && discount.getDiscountPercent() > 0) {                                              //If the tax line exists and there's a discount to apply
            StringBuilder receiptWithDiscount = buildDiscountReceipt(baseReceipt, taxIndex);    //Build the receipt with the discount using the helper method
            return receiptWithDiscount.toString();                                              //Return the receipt with the discount
        }

        return baseReceipt;                                                                     // If the tax line doesn't exist or there's
                                                                                                // no discount to apply, return the base receipt
    }

    @NotNull
    private StringBuilder buildDiscountReceipt(String baseReceipt, int taxIndex) {
        double discountAmount = getSubtotal() * discount.getDiscountPercent();                                                      //Calculate the discount amount

        String discountString = String.format("Discount: %.2f%% - $%.2f\n", discount.getDiscountPercent() * 100, discountAmount);   //Create the discount string in the
                                                                                                                    //format "Discount: 10.00% - $1.00"

        StringBuilder receiptWithDiscount = new StringBuilder(baseReceipt);                                         //Create the base receipt
        receiptWithDiscount.insert(taxIndex, discountString);                                                       //Insert the discount string into the receipt
        return receiptWithDiscount;                                                                                 //Return the receipt with the discount
    }
}

