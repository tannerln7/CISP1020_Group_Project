package Customers;

import Rewards.Offer;
import java.util.ArrayList;

public class RewardsCustomer extends Customer {
    private String customerEmail;
    private final LoyaltyAccount loyaltyAccount;
    private final ArrayList<Offer>offers = new ArrayList<>();

    public RewardsCustomer(String name, String phoneNumber, String customerEmail) {
        super(name, phoneNumber);
        this.customerEmail = customerEmail;
        this.loyaltyAccount = new LoyaltyAccount(name);
    }

    public static RewardsCustomer upgradeCustomerToRewards(Customer customer, String customerEmail, double initialRewardPoints) {
        // Create a new Customers.RewardsCustomer with the details copied from normal Customers.Customer.
        return new RewardsCustomer(customer.getName(), customer.getPhoneNumber(), customerEmail);
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void changeCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

}
