package Customers;

import Transactions.DiscountReceipt;
import Products.Discount;

// The RewardsCustomer class extends the Customer class to represent a customer with a loyalty account.
public class RewardsCustomer extends Customer {

    // The customer's email address associated with their rewards account.
    private String customerEmail;

    // The customer's loyalty account that manages their points and rewards
    private final LoyaltyAccount loyaltyAccount;

    // Constructor that creates a RewardsCustomer object with the default discount options from the LoyaltyAccount class.
    public RewardsCustomer(String name, String username, String password, String phoneNumber, String customerEmail) {
        super(name, username, password, phoneNumber);
        this.customerEmail = customerEmail;
        this.loyaltyAccount = new LoyaltyAccount();
    }

    // Constructor that allows customizing the initial points balance and rewards discount for the loyalty account.
    public RewardsCustomer(String name, String username, String password, String phoneNumber, String customerEmail, double initialPointsBalance, Discount discount) {
        super(name, username, password, phoneNumber);
        this.customerEmail = customerEmail;
        this.loyaltyAccount = new LoyaltyAccount(initialPointsBalance, discount);
    }

    // Method to upgrade an existing Customer object to a RewardsCustomer object.
    public static RewardsCustomer upgradeCustomerToRewards(Customer customer, String customerEmail, double initialRewardPoints) {

        // Create a new RewardsCustomer object with the copied details from the existing Customer object.
        return new RewardsCustomer(customer.getName(), customer.getUsername(), customer.getPassword(), customer.getPhoneNumber(), customerEmail);
    }

    // Retrieves the customer's email address.
    public String getCustomerEmail() {
        return this.customerEmail;
    }

    // Allows changing the customer's email address.
    public void changeCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    // Retrieves the customer's loyalty account.
    public LoyaltyAccount getLoyaltyAccount() {
        return loyaltyAccount;
    }

    // Adds a receipt associated with the rewards customer.
    public void addReceipt(DiscountReceipt receipt){
        super.addReceipt(receipt);
    }

    // Overrides the default `toString()` method to provide a human-readable representation of the rewards customer object.
    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n")
                .append("  RewardCustomer Username= ").append(this.getUsername()).append(",\n")
                .append("  Name= ").append(this.getName()).append(",\n")
                .append("  PhoneNumber= ").append(this.getPhoneNumber()).append(",\n")
                .append("  CustomerEmail= ").append(this.getCustomerEmail()).append(",\n")
                .append("  LoyaltyAccount= ").append(Math.round(this.getLoyaltyAccount().getAccountNumber())).append(",\n")
                .append("  PointsBalance= ").append(this.getLoyaltyAccount().getPoints()).append(",\n")
                .append("  DiscountPercent= ").append("%").append(100 * this.getLoyaltyAccount().getRewardsDiscountPercent()).append(",\n")
                .append("}\n");
        return sb.toString();
    }

    // Overrides the `getJsonId()` method to generate a JSON-compatible ID for the rewards customer.
    @Override
    public String getJsonId() {
        return "RewardsCustomer_" + super.getUsername();
    }

}
