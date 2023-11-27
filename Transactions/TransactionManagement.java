package Transactions;

import Customers.Customer;
import Helpers.Cls;
import Helpers.ObjectJson;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Main.Main;
import Products.Product;
import Retail_Operations.Employee;

import static Main.Main.execute;

//TODO: Debug the employee transaction management system and the customer receipt management system

/**
 * The TransactionManagement class provides methods for managing transactions in a retail system.
 * It allows an employee to edit a receipt, view all transactions, and find a product.
 * It also allows a customer to view all receipts and view a specific receipt.
 */
public class TransactionManagement {

    /**
     * Displays the transaction management menu for an employee and executes the user's choice.
     *
     * @param employee The logged in employee.
     */
    public static void transactionManager(Employee employee) {
        Cls.cls();
        //load customer data
        ArrayList<Customer> customers = Main.loadCustomers();

        System.out.println(employee.getUsername() + " - Welcome to the transaction management system\n");
        System.out.println("Enter the customer's username or type -X to exit");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        if (username.equalsIgnoreCase("-X")) {
            execute(() -> Main.employeeMenu(employee));
            return;
        }
        Customer customer = null;
        for (Customer customer1 : customers) {
            if (customer1.getUsername().equals(username)) {
                Cls.cls();
                System.out.println("Customer found");
                customer = customer1;
                System.out.println(customer);
            }
        }
        if(customer == null || customer.getReceipts().isEmpty()){
            Cls.cls();
            System.out.println("Customer not found or customer has no receipts");
            System.out.println("Press enter to return to the menu");
            scanner.nextLine();
            execute(() -> transactionManager(employee));
            return;
        }
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
                viewAllTransactions(employee, customer);
                break;
            case 3:
                Cls.cls();
                execute(() -> Main.employeeMenu(employee));
                break;
            default:
                Cls.cls();
                System.out.println("Invalid choice. Please try again.");
                System.out.println("Press enter to return to the menu");
                scanner.nextLine();
                execute(() -> transactionManager(employee));
        }

    }

    /**
     * Allows an employee to edit a transaction in a customer's receipt.
     *
     * @param employee The logged in employee.
     * @param customer The customer whose receipt is being edited.
     */
    private static void editTransaction(Employee employee, Customer customer) {
        Cls.cls();
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
            Cls.cls();
            System.out.println("Receipt not found or receipt has no transactions");
            System.out.println("Press enter to return to the menu");
            scanner.nextLine();
            execute(() -> transactionManager(employee));
            return;
        }
        //Get the transactions from the receipt
        Cls.cls();
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
                Cls.cls();
                System.out.println("Add a transaction to the receipt");
                System.out.println("Current transaction list");
                for (Transaction transaction : transactionArrayList) {
                    System.out.println(transaction);
                }
                System.out.println("Enter the name of the product you would like to add");
                scanner.nextLine();
                String productId = scanner.nextLine();
                Product product = findProduct(productId, employee);
                if (product == null) {
                    Cls.cls();
                    System.out.println("Product not found");
                    System.out.println("Press enter to return to the main menu");
                    scanner.nextLine();
                    execute(() -> transactionManager(employee));
                    break;
                }else{
                    System.out.println("Product found");
                    System.out.println(product);
                }
                receipt.getTransactions().add(new Transaction(product));
                customer.addReceipt(receipt);
                ObjectJson.objectToJson(customer);
                Cls.cls();
                System.out.println("Receipt updated successfully");
                System.out.println("New Receipt");
                System.out.println(receipt);
                System.out.println("Press enter to return to the main menu");
                scanner.nextLine();
                execute(() -> transactionManager(employee));
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
                        ObjectJson.objectToJson(customer);
                        Cls.cls();
                        System.out.println("Receipt updated successfully");
                        System.out.println("New Receipt");
                        System.out.println(receipt);
                        System.out.println("Press enter to return to the main menu");
                        scanner.nextLine();
                        execute(() -> transactionManager(employee));
                        break;
                    }
                }
                Cls.cls();
                System.out.println("Product not found in receipt");
                System.out.println("Press enter to return to the main menu");
                scanner.nextLine();
                execute(() -> transactionManager(employee));
                break;
            case 3:
                Cls.cls();
                System.out.println("Replace a transaction in the receipt");
                System.out.println("Current transaction list");
                System.out.println(receipt.getTransactions());
                System.out.println("Enter the product ID of the product you would like to replace");
                String productId3 = scanner.nextLine();
                Product product3 = findProduct(productId3, employee);
                System.out.println("Enter the replacement product ID");
                String productId4 = scanner.nextLine();
                Product product4 = findProduct(productId4, employee);
                if (receipt.getTransactions().contains(new Transaction(product3))) {
                    customer.getReceipts().get(customer.getReceipts().indexOf(receipt)).getTransactions().remove(new Transaction(product3));
                    customer.getReceipts().get(customer.getReceipts().indexOf(receipt)).getTransactions().add(new Transaction(product4));
                    ObjectJson.objectToJson(customer);
                    Cls.cls();
                    System.out.println("Receipt updated successfully");
                    System.out.println("New Receipt");
                    System.out.println(customer.getReceipts().get(customer.getReceipts().indexOf(receipt)));
                    System.out.println("Press enter to return to the menu");
                } else {
                    Cls.cls();
                    System.out.println("Product not found in receipt");
                    System.out.println("Press enter to return to the main menu");
                }
                scanner.nextLine();
                execute(() -> transactionManager(employee));
                break;
            case 4:
                execute(() -> Main.employeeMenu(employee));
                break;
            default:
                System.out.println("Invalid choice. Press enter to return to the menu");
                scanner.nextLine();
                execute(() -> transactionManager(employee));
                break;
        }
    }

    /**
     * Allows an employee to view all transactions in a customer's receipt.
     *
     * @param employee The logged in employee.
     * @param customer The customer whose transactions are being viewed.
     */
    private static void viewAllTransactions(Employee employee, Customer customer) {
        Cls.cls();
        Scanner scanner = new Scanner(System.in);
        System.out.println("View All Transactions");
        System.out.println("Customer Receipts");
        if(customer == null || customer.getReceipts().isEmpty()){
            System.out.println("Customer not found or customer has no receipts");
            System.out.println("Press enter to return to the main menu");
            scanner.nextLine();
            execute(() -> transactionManager(employee));
            return;
        }
        ArrayList<Receipt> receipts = customer.getReceipts();
        for (Receipt receipt : receipts) {
            System.out.println(receipt);
        }
        System.out.println("Press enter to return to the menu");
        scanner.nextLine();
        execute(() -> transactionManager(employee));
    }


    /**
     * Finds a product based on the product name entered by the user.
     *
     * @param productName The name of the product to find.
     * @param employee The logged in employee.
     * @return The product if found, or null if not found.
     */
    public static Product findProduct(String productName, Employee employee) {
        Cls.cls();
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
                System.out.println("No products found. Press enter to try again");
                return null;
            }
        } else {
            System.out.println("No products found. Press enter to return to try again");
            return null;
        }
    }

    /**
     * Displays the transaction management menu for a customer and executes the user's choice.
     *
     * @param customer The logged in customer.
     */
    public static void customerTransactionManager(Customer customer) {
        Cls.cls();
        System.out.println(customer.getUsername() + " - Welcome to the transaction management system");
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to view all Receipts");
        System.out.println("Press 2 to view a specific Receipt");
        System.out.println("Press 3 to exit");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Receipt> receipts = customer.getReceipts();
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                Cls.cls();
                System.out.println("View all Receipts");
                for (Receipt receipt : receipts) {
                    System.out.println(receipt);
                }
                System.out.println("Press enter to return to the menu");
                scanner.nextLine();
                execute(() -> customerTransactionManager(customer));
                break;
            case 2:
                Cls.cls();
                System.out.println("View a specific Receipt");
                System.out.println("Enter the Receipt number you would like to view");
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
                    Cls.cls();
                    System.out.println("Receipt not found or receipt has no transactions");
                    System.out.println("Press enter to return to the menu");
                    scanner.nextLine();
                    execute(() -> customerTransactionManager(customer));
                    break;
                }
                Cls.cls();
                System.out.println("Receipt found");
                System.out.println(receipt);
                System.out.println("Press enter to return to the menu");
                scanner.nextLine();
                execute(() -> customerTransactionManager(customer));
                break;
            case 3:
                System.out.println("Exiting Transaction Management Menu");
                execute(() -> Main.customerMenu(customer));
                break;
            default:
                System.out.println("Invalid choice. Press enter to try again.");
                execute(() -> customerTransactionManager(customer));
                break;
        }

    }
}
