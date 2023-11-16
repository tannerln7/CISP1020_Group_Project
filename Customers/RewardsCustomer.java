package Customers;

import Rewards.Offer;
import java.util.ArrayList;

//TODO: Create a list of Receipt objects associated with the RewardsCustomer and write the RewardsCustomer object to a file

public class RewardsCustomer extends Customer {
    private String customerEmail;
    private final LoyaltyAccount loyaltyAccount;
    private final ArrayList<Offer>offers = new ArrayList<>();

    //Constructor for RewardsCustomer. Creates new loyaltyAccount using the customer names and default discount options from the LoyaltyAccount class.
    public RewardsCustomer(String name, String phoneNumber, String customerEmail) {
        super(name, phoneNumber);
        this.customerEmail = customerEmail;
        this.loyaltyAccount = new LoyaltyAccount(name);
    }

    // Create a new RewardsCustomer with the details copied from an existing normal Customer.
    public static RewardsCustomer upgradeCustomerToRewards(Customer customer, String customerEmail, double initialRewardPoints) {

        return new RewardsCustomer(customer.getName(), customer.getPhoneNumber(), customerEmail);
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void changeCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

}
