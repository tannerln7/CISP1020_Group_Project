package Transactions;

import Customers.Customer;
import Helpers.ObjectJson;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import Products.Product;
import Retail_Operations.Employee;

public class TransactionManagement {
    public static void transactionManager(Employee employee) {

        //load customer data
        File[] customerFiles = ObjectJson.listFiles(Customer.class);
        ArrayList<Customer> customers = new ArrayList<>();
        if (customerFiles != null) {
            for (File file : customerFiles) {
                Customer customer = ObjectJson.objectFromJson(file.getName(), Customer.class);
                if (customer != null) {
                    customers.add(customer);
                }
            }
        }

        System.out.println(employee.getUsername() + " - Welcome to the transaction management system");
        System.out.println("Enter the customer's username");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        Customer customer = null;
        for (Customer customer1 : customers) {
            if (customer1.getUsername().equals(username)) {
                System.out.println("Customer found");
                customer = customer1;
                System.out.println(customer);
            }
        }
        if(customer == null || customer.getReceipts().isEmpty()){
            System.out.println("Customer not found or customer has no receipts");
            System.out.println("Press any key to return to the main menu");
            scanner.nextLine();
            return;
        }
        ArrayList<Receipt> receipts = customer.getReceipts();
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to edit a receipt");
        System.out.println("Press 2 to view all transactions");
        System.out.println("Press 3 to exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                editTransaction(employee, customer);
                break;
            case 2:
                viewAllTransactions(employee);
                break;
            case 3:
                System.out.println("Exiting Transaction Management Menu");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }

    private static void editTransaction(Employee employee, Customer customer) {
        System.out.println("Edit Transaction");
        System.out.println("Customer Receipts");
        ArrayList<Receipt> receipts = customer.getReceipts();
        for (Receipt receipt : receipts) {
            System.out.println(receipt);
        }
        System.out.println("Enter the Receipt number you would like to edit");
        Scanner scanner = new Scanner(System.in);
        String receiptId = scanner.nextLine();
        Receipt receipt = null;
        //Find the receipt in the customer's receipt list
        for (Receipt receipt1 : receipts) {
            if (receipt1.getJsonId().equals("Receipt_" + receiptId)) {
                receipt = receipt1;
            }
        }
        //If the receipt is not found or the receipt has no transactions
        if (receipt == null || receipt.getTransactions().isEmpty()) {
            System.out.println("Receipt not found or receipt has no transactions");
            System.out.println("Press any key to return to the main menu");
            scanner.nextLine();
            return;
        }
        //Get the transactions from the receipt
        ArrayList<Transaction> transactionArrayList = receipt.getTransactions();
        System.out.println("Receipt found");
        System.out.println(receipt);
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to add a transaction to the receipt");
        System.out.println("Press 2 to remove a transaction from the receipt");
        System.out.println("Press 3 to replace a transaction in the receipt");
        System.out.println("Press 4 to exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Add a transaction to the receipt");
                System.out.println("Current transaction list");
                for (Transaction transaction : transactionArrayList) {
                    System.out.println(transaction);
                }
                System.out.println("Enter the product ID of the product you would like to add");
                scanner.nextLine();
                String productId = scanner.nextLine();
                Product product = findProduct(productId, employee);
                receipt.getTransactions().add(new Transaction(product));
                customer.addReceipt(receipt);
                ObjectJson.objectToJson(customer);
                System.out.println("Receipt updated successfully");
                System.out.println("New Receipt");
                System.out.println(ObjectJson.objectFromJson(receipt.getJsonId(), Receipt.class));
                System.out.println("Press any key to return to the main menu");
                scanner.nextLine();
                break;
            case 2:
                System.out.println("Remove a transaction from the receipt");
                System.out.println("Current transaction list");
                ArrayList<Transaction> transactions = receipt.getTransactions();
                for (Transaction transaction : transactions) {
                    System.out.println(transaction.getProduct());
                }
                System.out.println("Enter the name of the product you would like to remove");
                scanner.nextLine();
                String productName = scanner.nextLine();
                Product product1 = findProduct(productName, employee);
                for (Transaction transaction : transactions) {
                    if (product1 != null && transaction.getProduct().getName().equals(product1.getName())) {
                        customer.getReceipts().remove(receipt);
                        receipt.getTransactions().remove(transaction);
                        customer.getReceipts().add(receipt);
                        System.out.println("Receipt updated successfully");
                        System.out.println("New Receipt");
                        System.out.println(ObjectJson.objectFromJson(receipt.getJsonId(), Receipt.class));
                    }
                }
                break;
            case 3:
                System.out.println("Edit a transaction in the receipt");
                System.out.println("Current transaction list");
                System.out.println(receipt.getTransactions());
                System.out.println("Enter the product ID of the product you would like to edit");
                String productId3 = scanner.nextLine();
                Product product3 = findProduct(productId3, employee);
                System.out.println("Enter the replacement product ID");
                String productId4 = scanner.nextLine();
                Product product4 = findProduct(productId4, employee);
                if (receipt.getTransactions().contains(new Transaction(product3))) {
                    receipt.getTransactions().remove(new Transaction(product3));
                    receipt.getTransactions().add(new Transaction(product4));
                    ObjectJson.objectToJson(receipt);
                    System.out.println("Receipt updated successfully");
                } else {
                    System.out.println("Product not found in receipt");
                    System.out.println("Press any key to return to the main menu");
                    scanner.nextLine();
                    return;
                }
                return;
            case 4:
                System.out.println("Exiting Transaction Management Menu");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewAllTransactions(Employee employee) {
        System.out.println("View All Transactions");
    }

    private static Receipt getReceipt(String receiptId, ArrayList<Receipt> receipts, Employee employee) {
        boolean found = false;
        for (Receipt receipt : receipts) {
            if (receipt.getJsonId().equals(receiptId)) {
                return receipt;
            }
        }
        if (!found) {
            System.out.println("Receipt not found");
            transactionManager(employee);
        }
        return null;
    }

    private static Product findProduct(String productName, Employee employee) {
        File[] productFiles = ObjectJson.listFiles(Product.class);
        ArrayList<Product> results = new ArrayList<>();
        if (productFiles != null) {
            for (File file : productFiles) {
                Product product = ObjectJson.objectFromJson(file.getName(), Product.class);
                if (product != null) {
                    if (product.getName().equalsIgnoreCase(productName)) {
                        results.add(product);
                    }
                }
            }
            if (results.size() > 1){
                System.out.println("Multiple products found");
                int i= 0;
                for (Product product : results) {
                    System.out.println("Number: " + i + ": " + product);
                    i++;
                }
                System.out.println("Please enter the number of the product you would like to select");
                Scanner scanner = new Scanner(System.in);
                int selection = scanner.nextInt();
                return results.get(selection);
            } else if (results.size() == 1){
                return results.get(0);
            }else {
                System.out.println("No products found");
                transactionManager(employee);
            }
        } else {
            System.out.println("No products found");
            transactionManager(employee);
        }
        return null;
    }
}
