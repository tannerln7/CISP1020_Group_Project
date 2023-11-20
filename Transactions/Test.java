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
 * Helpers/objectJson.java
 * ------------------------------------------------------
 */

public class Test {
    public static void main(String[] args) {
        //Create test data
        Customer customer = new Customer("John Doe", "123-456-7890");
        //TODO: Test RewardsCustomer. Need methods to add and remove rewards points....
        RewardsCustomer rewardsCustomer = new RewardsCustomer("John Doe", "123-456-7890", "email@email.com");
        Employee employee = new Employee("John Doe", "12345", "Cashier", "username", "password");
        CashRegister cashRegister = new CashRegister(1, 10000, employee);
        Gson gson = new Gson();

        //Call default constructor to initialize a blank transaction list
        TransactionList TransactionList = new TransactionList();
        //Add test transactions to the list
        TransactionList.addTransaction(new Transaction(new Product("Apple", 1.00, "A crisp Apple", "Fruit-App")));
        TransactionList.addTransaction(new Transaction(new Product("Banana", 3.00, "A yellow Banana", "Fruit-Ban")));
        TransactionList.addTransaction(new Transaction(new Product("Orange", 2.00, "A juicy Orange", "Fruit-Ora")));
        TransactionList.addTransaction(new Transaction(new Product("Pear", 5.00, "A green Pear", "Fruit-Pea")));
        TransactionList.addTransaction(new Transaction(new Product("Grape", 10.00, "A purple Grape", "Fruit-Gra")));
        TransactionList.addTransaction(new Transaction(new Product("Pineapple", 15.00, "A spiky Pineapple", "Fruit-Pin")));
        //Get the subtotal of the transactions
        double subTotal = TransactionList.getSubTotal();

        //test non-discount receipt
        //add tax to the subtotal
        double total = Payment.totalWithTax(subTotal);
        //Simulate a cash payment
        double paymentGiven = 100;
        //Create a payment object without a discount
        Payment payment = new Payment(total, paymentGiven, "Cash", true);
        //Get the change due
        double changeDue = payment.getChangeDue();
        //Create a receipt object
        Receipt normalReceipt = new Receipt(customer.getName(), payment, cashRegister, TransactionList.getTransactions(), changeDue);
        customer.addReceipt(normalReceipt);
        //Print the receipt
        System.out.println(normalReceipt);
        //write the receipt to a file
        objectJson.objectToJson(normalReceipt);

        //TODO: Test associating receipt with Customer and writing / updating Customer file

        //test discount receipt
        //add tax to the subtotal by passing the discount object to the static addTax method
        total = Payment.totalWithTax(subTotal);
        //apply the discounts to the total
        Discount discount = rewardsCustomer.getLoyaltyAccount().calculateDiscount(total);
        payment = new Payment(total, paymentGiven, "Cash", discount, true);
        total = payment.applyDiscount(total, discount);
        // Create a discounted payment using the overloaded Payment constructor
        payment = new Payment(total, paymentGiven, "Cash", rewardsCustomer.getLoyaltyAccount().calculateDiscount(total), true);
        //Get the change due
        changeDue = payment.getChangeDue();
        //Create a discount receipt object
        DiscountReceipt discountReceipt = new DiscountReceipt(customer.getName(), payment, cashRegister, TransactionList, changeDue, discount);
        //Print the receipt
        System.out.println(discountReceipt);
        //write the receipt to a file
        objectJson.objectToJson(discountReceipt);
        //Delete the receipts from memory
        normalReceipt = null;
        discountReceipt = null;
        //Read the receipts from the file

        try {
            discountReceipt = objectJson.objectFromJson("Receipt_2", DiscountReceipt.class);
            normalReceipt = objectJson.objectFromJson("Receipt_1", Receipt.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Print the receipt
        System.out.println("NORMAL RECEIPT READ FROM FILE" +"\n" + normalReceipt);
        System.out.println("DISCOUNT RECEIPT READ FROM FILE" +"\n" + discountReceipt);

    }
}
