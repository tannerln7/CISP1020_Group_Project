package Transactions;

import Products.Discount;

/**
 * The DiscountReceipt class extends the Receipt class to represent a receipt with a discount applied.
 * It includes details about the discount and the remaining point balance after the discount has been applied.
 */
public class DiscountReceipt extends Receipt{
    private final Discount discount;
    private final double pointBalance;

    /**
     * Constructs a new DiscountReceipt with the specified name, payment, cash register, transactions, discount, and new point balance.
     *
     * @param name the name on the receipt
     * @param payment the payment for the receipt
     * @param cashRegister the cash register where the receipt was processed
     * @param transactions the transactions on the receipt
     * @param discount the discount applied to the receipt
     * @param newPointBalance the new point balance after the discount has been applied
     */
    public DiscountReceipt(String name, Payment payment, double cashRegister, TransactionList transactions, Discount discount, double newPointBalance){
        super(name, payment, cashRegister, transactions.getTransactions());
        this.discount = discount;
        this.pointBalance = newPointBalance;
    }

    /**
     * Calculates the total amount due after applying the discount.
     *
     * @return the total amount due after discount
     */
    @Override
    public double getTotal() {
        return getSubtotal() + (getSubtotal() * getTax()) - (getSubtotal() * discount.getDiscountPercent());
    }

    /**
     * Provides a string representation of the discount receipt, including the name, payment, cash register, transactions, discount, and new point balance.
     *
     * @return a string representation of the discount receipt
     */
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

    /**
     * Builds a receipt with the discount applied.
     *
     * @param baseReceipt the base receipt before applying the discount
     * @param taxIndex the index of the tax line in the base receipt
     * @return a receipt with the discount applied
     */
    private StringBuilder buildDiscountReceipt(String baseReceipt, int taxIndex) {

        // Create the discount string in the format "Discount: 10.00% off | Point Discount $1.00"
        String discountString = String.format("Discount: %.2f%% off | Point Discount $%.2f\n",
                discount.getDiscountPercent() * 100, discount.getDiscountAmount());

        StringBuilder receiptWithDiscount = new StringBuilder(baseReceipt); // Create the base receipt
        receiptWithDiscount.insert(taxIndex, discountString); // Insert the discount string into the receipt
        return receiptWithDiscount; // Return the receipt with the discount
    }

}

