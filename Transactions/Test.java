package Transactions;

import Customers.Customer;
import Products.Product;
import Retail_Operations.CashRegister;
import Retail_Operations.Employee;
import Rewards.Discount;


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
 * Helpers/Utilities.java
 * Helpers/objectJson.java
 * ------------------------------------------------------
 */

public class Test {
    public static void main(String[] args) {
        //Create test data
        Customer customer = new Customer("John Doe", "123-456-7890");
        Employee employee = new Employee("John Doe", "12345", "Cashier", "username", "password");
        CashRegister cashRegister = new CashRegister(1, 10000, employee);
        Discount discount = new Discount(0.10);

        //Call default constructor to initialize a blank transaction list
        TransactionList TransactionList = new TransactionList();
        //Add test transactions to the list
        TransactionList.addTransaction(new Transaction(new Product("Apple", 1.00, "A crisp Apple", "Fruit-App"), customer));
        TransactionList.addTransaction(new Transaction(new Product("Banana", 3.00, "A yellow Banana", "Fruit-Ban"), customer));
        TransactionList.addTransaction(new Transaction(new Product("Orange", 2.00, "A juicy Orange", "Fruit-Ora"), customer));
        TransactionList.addTransaction(new Transaction(new Product("Pear", 5.00, "A green Pear", "Fruit-Pea"), customer));
        TransactionList.addTransaction(new Transaction(new Product("Grape", 10.00, "A purple Grape", "Fruit-Gra"), customer));
        TransactionList.addTransaction(new Transaction(new Product("Pineapple", 15.00, "A spiky Pineapple", "Fruit-Pin"), customer));
        //Get the subtotal of the transactions
        double subTotal = TransactionList.getSubTotal();

        //Initialize a blank payment in order to call the addTax method
        //TODO: Move addTax method to a different class to avoid having to create a blank payment object
        Payment payment = new Payment();

        //test non discount receipt
        //add tax to the subtotal
        double total = payment.addTax(subTotal);
        //Simulate a cash payment
        double paymentGiven = 100;
        //Create a payment object without a discount
        payment = new Payment(total, paymentGiven, "Cash", true);
        //Get the change due
        double changeDue = payment.getChangeDue();
        //Create a receipt object
        Receipt normalReceipt = new Receipt(customer, payment, cashRegister, TransactionList.getTransactions(), changeDue);
        //Print the receipt
        System.out.println(normalReceipt);

        //TODO: Test associating receipt with Customer and writing / updating Customer file
        //test discount receipt
        //add tax to the subtotal by passing the discount object to the overloaded addTax method in the Payment class. Need to move method to avoid this
        total = payment.addTax(subTotal, discount);
        //Create a discounted payment using the overloaded Payment constructor
        payment = new Payment(total, paymentGiven, "Cash", discount, true);
        //Get the change due
        changeDue = payment.getChangeDue();
        //Create a discount receipt object

        //TODO: Maybe use an overloaded constructor in Receipt class instead of creating a new child class
        DiscountReceipt discountReceipt = new DiscountReceipt(customer, payment, cashRegister, TransactionList, changeDue, discount);
        System.out.println(discountReceipt);
    }
}
