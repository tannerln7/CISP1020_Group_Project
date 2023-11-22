package Transactions;

import Products.Discount;
import Retail_Operations.*;

public class DiscountReceipt extends Receipt{
    private final Discount discount;
    private final double pointBalance;
    public DiscountReceipt(String name, Payment payment, CashRegister cashRegister, TransactionList transactions, Discount discount, double newPointBalance){
        super(name, payment, cashRegister, transactions.getTransactions());
        this.discount = discount;
        this.pointBalance = newPointBalance;
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
            StringBuilder receiptWithDiscount = buildDiscountReceipt(baseReceipt, taxIndex);
            receiptWithDiscount.append("\n").append("Points Redeemed: ").append(discount.getDiscountAmount() * 10).append("\n")
                    .append("Points Balance: ").append(pointBalance);//Build the receipt with the discount using the helper method
            return receiptWithDiscount.toString();                                              //Return the receipt with the discount
        }

        return baseReceipt;                                                                     // If the tax line doesn't exist or there's
                                                                                                // no discount to apply, return the base receipt
    }

    private StringBuilder buildDiscountReceipt(String baseReceipt, int taxIndex) {

        // Create the discount string in the format "Discount: 10.00% off | Point Discount $1.00"
        String discountString = String.format("Discount: %.2f%% off | Point Discount $%.2f\n",
                discount.getDiscountPercent() * 100, discount.getDiscountAmount());

        StringBuilder receiptWithDiscount = new StringBuilder(baseReceipt); // Create the base receipt
        receiptWithDiscount.insert(taxIndex, discountString); // Insert the discount string into the receipt
        return receiptWithDiscount; // Return the receipt with the discount
    }

}

