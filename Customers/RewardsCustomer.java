package Customers;

import Transactions.DiscountReceipt;
import Products.Discount;

/**
 * The RewardsCustomer class extends the Customer class to represent a customer with a loyalty account.
 */public class RewardsCustomer extends Customer {
     private String customerEmail;
    private final LoyaltyAccount loyaltyAccount;

    /**
     * Constructor that creates a RewardsCustomer object with the default discount options from the LoyaltyAccount class.
     *
     * @param name the name of the customer
     * @param username the username of the customer
     * @param password the password of the customer
     * @param phoneNumber the phone number of the customer
     * @param customerEmail the email address of the customer
     */
    public RewardsCustomer(String name, String username, String password, String phoneNumber, String customerEmail) {
        super(name, username, password, phoneNumber);
        this.customerEmail = customerEmail;
        this.loyaltyAccount = new LoyaltyAccount();
    }

    /**
     * Constructor that allows customizing the initial points balance and rewards discount for the loyalty account.
     *
     * @param name the name of the customer
     * @param username the username of the customer
     * @param password the password of the customer
     * @param phoneNumber the phone number of the customer
     * @param customerEmail the email address of the customer
     * @param initialPointsBalance the initial points balance for the account
     * @param discount the initial rewards discount for the account
     */
    public RewardsCustomer(String name, String username, String password, String phoneNumber, String customerEmail, double initialPointsBalance, Discount discount) {
        super(name, username, password, phoneNumber);
        this.customerEmail = customerEmail;
        this.loyaltyAccount = new LoyaltyAccount(initialPointsBalance, discount);
    }


    /**
     * Method to upgrade an existing Customer object to a RewardsCustomer object.
     *
     * @param customer the existing Customer object
     * @param customerEmail the email address of the customer
     * @param initialRewardPoints the initial reward points for the account
     * @return the new RewardsCustomer object
     */
    public static RewardsCustomer upgradeCustomerToRewards(Customer customer, String customerEmail, double initialRewardPoints) {

        // Create a new RewardsCustomer object with the copied details from the existing Customer object.
        return new RewardsCustomer(customer.getName(), customer.getUsername(), customer.getPassword(), customer.getPhoneNumber(), customerEmail);
    }

    /**
     * Retrieves the customer's email address.
     *
     * @return the customer's email address
     */
    public String getCustomerEmail() {
        return this.customerEmail;
    }

    /**
     * Allows changing the customer's email address.
     *
     * @param customerEmail the new email address of the customer
     */
    public void changeCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * Retrieves the customer's loyalty account.
     *
     * @return the customer's loyalty account
     */
    public LoyaltyAccount getLoyaltyAccount() {
        return loyaltyAccount;
    }

    /**
     * Adds a receipt associated with the rewards customer.
     *
     * @param receipt the receipt to add
     */
    public void addReceipt(DiscountReceipt receipt){
        super.addReceipt(receipt);
    }

    /**
     * Overrides the default `toString()` method to provide a human-readable representation of the rewards customer object.
     *
     * @return a string representation of the rewards customer
     */
    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  RewardCustomer Username: ").append(this.getUsername()).append(",\n")
                .append("  Name: ").append(this.getName()).append(",\n")
                .append("  PhoneNumber: ").append(this.getPhoneNumber()).append(",\n")
                .append("  CustomerEmail: ").append(this.getCustomerEmail()).append(",\n")
                .append("  LoyaltyAccount: ").append(Math.round(this.getLoyaltyAccount().getAccountNumber())).append(",\n")
                .append("  PointsBalance: ").append(this.getLoyaltyAccount().getPoints()).append(",\n")
                .append("  DiscountPercent: ").append("%").append(100 * this.getLoyaltyAccount().getRewardsDiscountPercent()).append(",\n");
        return sb.toString();
    }

    /**
     * Overrides the `getJsonId()` method to generate a JSON-compatible ID for the rewards customer.
     *
     * @return the JSON-compatible ID for the rewards customer
     */
    @Override
    public String getJsonId() {
        return "RewardsCustomer_" + super.getUsername();
    }

}
