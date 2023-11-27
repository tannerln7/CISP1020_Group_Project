package Transactions.Demos;

import Customers.Customer;
import Customers.RewardsCustomer;
import Helpers.ObjectJson;
import Main.Main;
import Products.Discount;
import Products.Product;
import Transactions.*;

import java.util.ArrayList;

public class ReceiptGenerator {
    public static void main(String[] args) {
        ArrayList<Customer> customers = Main.loadCustomers();
        TransactionList transactionList = new TransactionList();
        transactionList.addTransaction(new Transaction(new Product("Apple", 1.00, "A crisp Apple", "Fruit-App")));
        transactionList.addTransaction(new Transaction(new Product("Banana", 3.00, "A yellow Banana", "Fruit-Ban")));
        transactionList.addTransaction(new Transaction(new Product("Orange", 2.00, "A juicy Orange", "Fruit-Ora")));
        transactionList.addTransaction(new Transaction(new Product("Pear", 5.00, "A green Pear", "Fruit-Pea")));
        transactionList.addTransaction(new Transaction(new Product("Grape", 9.00, "A purple Grape", "Fruit-Gra")));
        transactionList.addTransaction(new Transaction(new Product("Pineapple", 10.00, "A spiky Pineapple", "Fruit-Pin")));

        double subTotal = transactionList.getSubTotal();
        double total = Payment.totalWithTax(subTotal);
        double paymentGiven = 100;
        Payment payment = new Payment(total, paymentGiven, "Cash", true);
        Discount discount = new Discount(0.1,1);
        for (Customer customer : customers) {
            if (customer instanceof RewardsCustomer rewardsCustomer) {
                DiscountReceipt receipt = new DiscountReceipt(rewardsCustomer.getName(), payment, 1, transactionList, discount, (rewardsCustomer.getLoyaltyAccount().getPoints() + (total * 0.1 )));
                rewardsCustomer.addReceipt(receipt);
                ObjectJson.objectToJson(rewardsCustomer);
            } else {
                Receipt receipt = new Receipt(customer.getName(), payment, 1, transactionList.getTransactions());
                customer.addReceipt(receipt);
                ObjectJson.objectToJson(customer);
            }
        }
    }
}
