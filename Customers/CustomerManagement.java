package Customers;

import Helpers.FunctionCaller;
import Helpers.ObjectJson;
import Retail_Operations.Employee;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManagement {
    //TODO: Write customerManagement menu
    public static void customerManagement(Employee employee) {
        Scanner in = new Scanner(System.in);
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<RewardsCustomer> rewardsCustomers = new ArrayList<>();
        File[] customerFiles = ObjectJson.listFiles(Customer.class);
        File[] rewardsCustomerFiles = ObjectJson.listFiles(RewardsCustomer.class);
        if (customerFiles != null) {
            for (File file : customerFiles) {
                Customer customer = ObjectJson.objectFromJson(file.getName(), Customer.class);
                if (customer != null) {
                    customers.add(customer);
                }
            }
        }
        if (rewardsCustomerFiles != null) {
            for (File file : rewardsCustomerFiles) {
                RewardsCustomer rewardsCustomer = ObjectJson.objectFromJson(file.getName(), RewardsCustomer.class);
                if (rewardsCustomer != null) {
                    rewardsCustomers.add(rewardsCustomer);
                }
            }
        }
        System.out.println("Welcome to the customer management menu");
        System.out.println("Press 1 to view all customers");
        System.out.println("Press 2 to view a specific customer");
        System.out.println("Press 3 to add a new customer");
        System.out.println("Press 4 to edit a customer");
        System.out.println("Press 5 to delete a customer");
        System.out.println("Press 6 to return to the main menu");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                System.out.println("All customers:");
                for (Customer customer : customers) {
                    System.out.println(customer);
                }
                System.out.println("All rewards customers:");
                for (RewardsCustomer rewardsCustomer : rewardsCustomers) {
                    System.out.println(rewardsCustomer);
                }
                System.out.println("Press enter to return to the menu");
                in.nextLine();
                in.nextLine();
                return;
            case 2:
                System.out.println("Please enter the customer's username: ");
                String username = in.nextLine();
                Customer customer = ObjectJson.objectFromJson(username, Customer.class);
                if (customer != null) {
                    System.out.println(customer);
                } else {
                    RewardsCustomer rewardsCustomer = ObjectJson.objectFromJson(username, RewardsCustomer.class);
                    if (rewardsCustomer != null) {
                        System.out.println(rewardsCustomer);
                    } else {
                        System.out.println("Customer not found");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        return;
                    }
                    System.out.println("Press enter to return to the menu");
                    in.nextLine();
                    in.nextLine();
                    return;
                }
            case 3:
                System.out.println("Please enter the customer's username: ");
                username = in.nextLine();
                customer = ObjectJson.objectFromJson(username, Customer.class);
                String email = null;
                if (customer != null) {
                    System.out.println(customer);
                } else {
                    RewardsCustomer rewardsCustomer = ObjectJson.objectFromJson(username, RewardsCustomer.class);
                    if (rewardsCustomer != null) {
                        System.out.println(rewardsCustomer);
                        System.out.println("Enter the customer's email address: ");
                        email = in.nextLine();
                    } else {
                        System.out.println("Customer not found");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        return;
                    }
                    System.out.println("Enter the customer's first and last name: ");
                    String name = in.nextLine();
                    System.out.println("Enter the customer's username: ");
                    username = in.nextLine();
                    System.out.println("Enter the customer's password: ");
                    String password = in.nextLine();
                    System.out.println("Enter the customer's phone number: ");

                    if (email != null) {
                        rewardsCustomer = new RewardsCustomer(name, username, password, in.nextLine(), email);
                        ObjectJson.objectToJson(rewardsCustomer);
                        System.out.println(rewardsCustomer);
                    } else {
                        customer = new Customer(name, username, password, in.nextLine());
                        ObjectJson.objectToJson(customer);
                        System.out.println(customer);
                    }
                    System.out.println("Customer added");
                    System.out.println("Press enter to return to the menu");
                    in.nextLine();
                    in.nextLine();
                    return;
                }
        }
    }
    public static void customerSignUp(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your first and last name: ");
        String name = in.nextLine();
        System.out.println("Please enter your username: ");
        String username = in.nextLine();
        System.out.println("Please enter your password: ");
        String password = in.nextLine();
        System.out.println("Please enter your phone number: ");
        String phoneNumber = in.nextLine();
        Customer customer = new Customer(name, username, password, phoneNumber);
        System.out.println("Thank you for signing up! Your account has been created.");
        System.out.println("Would you like to sign up for our rewards program and receive an 250 point sign up bonus?");
        System.out.println("Press 1 for yes");
        System.out.println("Press 2 for no");
        int choice = in.nextInt();
        switch (choice){
            case 1:
                System.out.println("Please enter your email address: ");
                String email = in.nextLine();
                RewardsCustomer rewardsCustomer = RewardsCustomer.upgradeCustomerToRewards(customer, email, 250);
                ObjectJson.objectToJson(rewardsCustomer);
                System.out.println("Thank you for signing up! Your Rewards account has been created.");
                break;
            case 2:
                ObjectJson.objectToJson(customer);
                System.out.println("Thank you for signing up! Your account has been created.");
                break;
            default:
                System.out.println("Invalid choice. Press enter to try again.");
                in.nextLine();
                in.nextLine();
                execute(CustomerManagement::customerSignUp);
        }
        System.out.println("Press enter to return and login");
        in.nextLine();
        execute(() -> Main.Main.main(null));


    }



    public static void execute(FunctionCaller f) {
        f.apply();
    }
}

