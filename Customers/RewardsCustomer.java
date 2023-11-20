package Customers;

import Helpers.ObjectJson;
import Transactions.DiscountReceipt;
import Products.Discount;


import java.util.ArrayList;

//TODO: Add methods to keep track of customer points on a per transaction basis.
public class RewardsCustomer extends Customer {
    private String customerEmail;
    private final LoyaltyAccount loyaltyAccount;



    //Constructor for RewardsCustomer. Creates new loyaltyAccount using the default discount options from the LoyaltyAccount class.
    public RewardsCustomer(String name, String phoneNumber, String customerEmail) {
        super(name, phoneNumber);
        this.customerEmail = customerEmail;
        this.loyaltyAccount = new LoyaltyAccount();
    }
    public RewardsCustomer(String name, String phoneNumber, String customerEmail, double initialPointsBalance, Discount discount) {
        super(name, phoneNumber);
        this.customerEmail = customerEmail;
        this.loyaltyAccount = new LoyaltyAccount(initialPointsBalance, discount);
    }

    // Create a new RewardsCustomer with the details copied from an existing normal Customer.
    public static RewardsCustomer upgradeCustomerToRewards(Customer customer, String customerEmail, double initialRewardPoints) {

        return new RewardsCustomer(customer.getName(), customer.getPhoneNumber(), customerEmail);
    }
    public String getCustomerEmail() {
        return this.customerEmail;
    }
    public void changeCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public LoyaltyAccount getLoyaltyAccount() {
        return loyaltyAccount;
    }
    public void addReceipt(DiscountReceipt receipt){
        super.addReceipt(receipt);
    }
    @Override
    public void updateSaveFile(){
        ObjectJson.objectToJson(this);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n")
                .append("  Reward CustomerId= ").append(this.getCustomerId()).append(",\n")
                .append("  Name= ").append(this.getName()).append(",\n")
                .append("  PhoneNumber= ").append(this.getPhoneNumber()).append(",\n")
                .append("  CustomerEmail= ").append(this.getCustomerEmail()).append(",\n")
                .append("  LoyaltyAccount= ").append(Math.round(this.getLoyaltyAccount().getAccountNumber())).append(",\n")
                .append("  PointsBalance= ").append(this.getLoyaltyAccount().getPoints()).append(",\n")
                .append("  DiscountPercent= ").append("%").append(100 * this.getLoyaltyAccount().getRewardsDiscountPercent()).append(",\n")
                .append("}\n");
        return sb.toString();
    }
    @Override
    public String getJsonId() {
        return "RewardsCustomer_" + this.getId();
    }

}
