package Customers;

import Products.Product;
import Retail_Operations.CashRegister;
import Retail_Operations.Employee;
import Transactions.Payment;
import Transactions.Receipt;
import Transactions.Transaction;

import java.util.ArrayList;

//TODO: Add methods to keep track of customer points on a per transaction basis.
public class RewardsCustomer extends Customer {
    private String customerEmail;
    private LoyaltyAccount loyaltyAccount;


    //Constructor for RewardsCustomer. Creates new loyaltyAccount using the default discount options from the LoyaltyAccount class.
    public RewardsCustomer(String name, String phoneNumber, String customerEmail) {
        super(name, phoneNumber);
        this.customerEmail = customerEmail;
        this.loyaltyAccount = new LoyaltyAccount();
    }
    public RewardsCustomer(String name, String phoneNumber, String customerEmail, double initialPointsBalance, double discountPercent) {
        super(name, phoneNumber);
        this.customerEmail = customerEmail;
        this.loyaltyAccount = new LoyaltyAccount(initialPointsBalance, discountPercent);
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
    public LoyaltyAccount getLoyaltyAccount() {
        return loyaltyAccount;
    }

    public static String pointsMenu(Customer customer){
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(new Product("Apple", 32, "apple", "Apple-1"),customer));
        customer.addReceipt(new Receipt(customer, new Payment(), new CashRegister(1,1000, new Employee("name", "123", "Employee", "Username", "Password")),transactions, 100));
        return customer.getReceipts().toString();
    }

    public static void main(String[] args) {
        RewardsCustomer customer = new RewardsCustomer("Name", "123-123-123", "Email@email.com");
        System.out.println(pointsMenu(customer));
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
                .append("  DiscountPercent= ").append("%").append(100 * this.getLoyaltyAccount().getDiscountPercent()).append(",\n")
                .append("}\n");
        return sb.toString();
    }
    @Override
    public String getJsonId() {
        return "RewardsCustomer_" + this.getId();
    }

}
