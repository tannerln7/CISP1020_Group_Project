package Transactions;

import Customers.Customer;
import Customers.RewardsCustomer;
import Products.Product;
import Retail_Operations.CashRegister;
import Retail_Operations.Employee;
import Products.Discount;
import com.google.gson.Gson;
import java.io.IOException;
import Helpers.*;



/**
 * This class currently tests the function of the following classes:
 * ------------------------------------------------------
 * Transactions/Transaction.java
 * Transactions/TransactionList.java
 * Transactions/Receipt.java
 * Transactions/DiscountReceipt.java
 * Transactions/Payment.java
 * Retail_Operations/CashRegister.java
 * Retail_Operations/Employee.java
 * Rewards/Discount.java
 * Customers/Customer.java
 * Products/Product.java
 *------------------------------------------------------
 * These will be added soon
 * Helpers/JsonIdentifiable.java
 * Helpers/ObjectJson.java
 * ------------------------------------------------------
 */

public class Test {
    public static void main(String[] args) {
        //Create test data
        Customer customer = new Customer("John Doe", "123-456-7890");
        RewardsCustomer rewardsCustomer = new RewardsCustomer("John Doe", "123-456-7890", "email@email.com");
        Employee employee = new Employee("John Doe", "12345", "Cashier", "username", "password");
        CashRegister cashRegister = new CashRegister( 10000, employee);
        Gson gson = new Gson();

        //Call default constructor to initialize a blank transaction list
        TransactionList TransactionList = new TransactionList();
        //Add test transactions to the list
        TransactionList.addTransaction(new Transaction(new Product("Apple", 1.00, "A crisp Apple", "Fruit-App")));
        TransactionList.addTransaction(new Transaction(new Product("Banana", 3.00, "A yellow Banana", "Fruit-Ban")));
        TransactionList.addTransaction(new Transaction(new Product("Orange", 2.00, "A juicy Orange", "Fruit-Ora")));
        TransactionList.addTransaction(new Transaction(new Product("Pear", 5.00, "A green Pear", "Fruit-Pea")));
        TransactionList.addTransaction(new Transaction(new Product("Grape", 9.00, "A purple Grape", "Fruit-Gra")));
        TransactionList.addTransaction(new Transaction(new Product("Pineapple", 10.00, "A spiky Pineapple", "Fruit-Pin")));
        //Get the subtotal of the transactions
        double subTotal = TransactionList.getSubTotal();

        //test non-discount receipt
        //add tax to the subtotal
        double total = Payment.totalWithTax(subTotal);
        //Simulate a cash payment
        double paymentGiven = 100;
        //Create a payment object without a discount
        Payment payment = new Payment(total, paymentGiven, "Cash", false);
        //complete the payment by adding the receipt to the cash register and updating the cash register's held cash
        Receipt normalReceipt = new Receipt(customer.getName(), payment, cashRegister, TransactionList.getTransactions());
        payment.completePayment(cashRegister, normalReceipt);
        //Add the receipt to the customer object
        customer.addReceipt(normalReceipt);
        //Print the receipt
        System.out.println(normalReceipt);
        //Save the customer to a file. This will also save the payment, transaction list, and receipt. They are stored in the customer object.
        customer.updateSaveFile();

        //test discount receipt

        //add tax to the subtotal by passing the discount object to the static addTax method
        total = Payment.totalWithTax(subTotal);
        //apply the discounts to the total
        Discount discount = rewardsCustomer.getLoyaltyAccount().calculateDiscount(25);
        double discountedTotal = total - ((total * discount.getDiscountPercent()) + discount.getDiscountAmount());
        payment = new Payment(discountedTotal , paymentGiven, "Cash", discount, false);
        //apply the discount to the payment and remove the points from the customer's loyalty account
        payment.applyDiscount(total, discount, rewardsCustomer);
        //generate the discounted receipt
        DiscountReceipt discountReceipt = new DiscountReceipt(rewardsCustomer.getName(), payment, cashRegister, TransactionList,
                discount, rewardsCustomer.getLoyaltyAccount().getPoints());
        //complete the payment by adding the receipt to the cash register and updating the cash register's held cash
        payment.completePayment(cashRegister, discountReceipt);



        //Print the receipt
        System.out.println(discountReceipt);
        //add the receipt to the customer object
        rewardsCustomer.addReceipt(discountReceipt);
        //Save the customer to a file. This will also save the payment, transaction list, loyalty account and receipt. They are stored in the customer object.
        rewardsCustomer.updateSaveFile();

        //Delete the customers from volatile memory to simulate the program closing
        customer = null;
        rewardsCustomer = null;

        //Read the customers back from the files

        try {
            customer = ObjectJson.objectFromJson("Customer_1", Customer.class);
            rewardsCustomer = ObjectJson.objectFromJson("RewardsCustomer_1", RewardsCustomer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Print the customer Information
        System.out.println("Customer Information read from file \n");
        System.out.println("NORMAL Customer" +"\n" + customer);
        System.out.println("Rewards Customer" +"\n" + rewardsCustomer);

        //Print the receipts
        System.out.println("Receipts read from file \n");
        System.out.println("NORMAL Customer Receipts" +"\n" + customer.getReceipts().toString());
        System.out.println("Rewards Customer Receipts" +"\n" + rewardsCustomer.getReceipts().toString());

    }
}
