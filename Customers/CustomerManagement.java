package Customers;

import Helpers.Cls;
import Helpers.FunctionCaller;
import Helpers.ObjectJson;
import Main.Main;
import Products.Discount;
import Retail_Operations.Employee;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManagement {
    public static void customerManagement(Employee employee) {
        if(!employee.getPosition().equals("Manager")){
            System.out.println("You do not have permission to access this menu");
            System.out.println("Press enter to return to the main menu");
            Scanner in = new Scanner(System.in);
            in.nextLine();
            execute(() -> Main.main(null));
            return;
        }
        Scanner in = new Scanner(System.in);
        ArrayList<Customer> allCustomers = new ArrayList<>();
        File[] customerFiles = ObjectJson.listFiles(Customer.class);
        File[] rewardsCustomerFiles = ObjectJson.listFiles(RewardsCustomer.class);
        if (customerFiles != null) {
            for (File file : customerFiles) {
                Customer customer = ObjectJson.objectFromJson(file.getName(), Customer.class);
                if (customer != null) {
                    allCustomers.add(customer);
                }
            }
        }
        if (rewardsCustomerFiles != null) {
            for (File file : rewardsCustomerFiles) {
                RewardsCustomer rewardsCustomer = ObjectJson.objectFromJson(file.getName(), RewardsCustomer.class);
                if (rewardsCustomer != null) {
                    allCustomers.add(rewardsCustomer);
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
        String username;
        Customer customer = null;
        String email;
        switch (choice) {
            case 1:
                //View all customers
                Cls.cls();
                System.out.println("All customers:");
                allCustomers.forEach(System.out::println);
                System.out.println("Press enter to return to the menu");
                in.nextLine();
                in.nextLine();
                execute(() -> customerManagement(employee));
            case 2:
                //View a specific customer
                Cls.cls();
                System.out.println("Please enter the customer's username: ");
                username = in.nextLine();
                for (Customer cust : allCustomers) {
                    if (cust.getUsername().equals(username)) {
                        System.out.println(cust);
                        customer = cust;
                        break;
                    }
                }
                if (customer == null) {
                    System.out.println("Customer not found");
                }
                System.out.println("Press enter to return to the menu");
                in.nextLine();
                in.nextLine();
                execute(() -> customerManagement(employee));
                break;
            case 3:
                //Add a new customer
                Cls.cls();
                System.out.println("Add a new customer");
                System.out.println("Please enter the customer's username: ");
                username = in.nextLine();
                for (Customer cust : allCustomers) {
                    if (cust.getUsername().equals(username)) {
                        System.out.println("Customer already exists");
                        System.out.println(cust);
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    }
                }
                Cls.cls();
                System.out.println("Creating new customer");
                System.out.println("Enter the customer's first and last name: ");
                String name = in.nextLine();
                System.out.println("Enter the customer's password: ");
                String password = in.nextLine();
                System.out.println("Enter the customer's phone number: (XXX-XXX-XXXX)");
                String phoneNumber = in.nextLine();
                System.out.println("Should this customer be a rewards customer? Press 1 for yes, 2 for no");
                choice = in.nextInt();
                if (choice == 1) {
                    System.out.println("Enter the customer's email address: ");
                    email = in.nextLine();
                    customer = new RewardsCustomer(name, username, password, phoneNumber, email, 250, new Discount());
                    Cls.cls();
                    System.out.println("Rewards customer created");
                } else {
                    customer = new Customer(name, username, password, phoneNumber);
                    Cls.cls();
                    System.out.println("Customer created");
                }
                ObjectJson.objectToJson(customer);
                System.out.println(customer);
                System.out.println("Press enter to return to the menu");
                in.nextLine();
                in.nextLine();
                execute(() -> customerManagement(employee));
                break;
            case 4:
                //Edit a customer
                Cls.cls();
                System.out.println("Please enter the customer's username: ");
                username = in.nextLine();
                for (Customer cust : allCustomers) {
                    if (cust.getUsername().equals(username)) {
                        customer = cust;
                        Cls.cls();
                        System.out.println("Customer found!");
                        System.out.println(cust);
                    }
                }
                if (customer == null) {
                    //If the customer is not found, return to the menu
                    Cls.cls();
                    System.out.println("Customer not found");
                    System.out.println("Press enter to return to the menu");
                    in.nextLine();
                    in.nextLine();
                    execute(() -> customerManagement(employee));
                    break;
                }
                System.out.println("Press 1 to edit the customer's name");
                System.out.println("Press 2 to edit the customer's username");
                System.out.println("Press 3 to edit the customer's password");
                System.out.println("Press 4 to edit the customer's phone number");
                if (customer instanceof RewardsCustomer) {
                    System.out.println("Press 5 to edit the customer's email address");
                    System.out.println("Press 6 to edit the customer's loyalty account");
                    System.out.println("Press 7 to return to the menu");
                } else {
                    System.out.println("Press 5 to return to the menu");
                }
                choice = in.nextInt();
                if (!(customer instanceof RewardsCustomer) && choice == 5) {
                    choice = 7;
                }
                switch (choice) {
                    case 1:
                        Cls.cls();
                        in.nextLine();
                        System.out.println("Enter the customer's new name: ");
                        customer.setName(in.nextLine());
                        ObjectJson.objectToJson(customer);
                        System.out.println("Name updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    case 2:
                        Cls.cls();
                        in.nextLine();
                        System.out.println("Enter the customer's new username: ");
                        customer.setUsername(in.nextLine());
                        ObjectJson.objectToJson(customer);
                        System.out.println("Username updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    case 3:
                        Cls.cls();
                        in.nextLine();
                        System.out.println("Enter the customer's new password: ");
                        String newPassword = in.nextLine();
                        System.out.println("Enter the customer's new password again: ");
                        String passwordVerification = in.nextLine();
                        if (!newPassword.equals(passwordVerification)) {
                            System.out.println("Passwords do not match");
                        } else {
                            customer.setPassword(newPassword);
                            ObjectJson.objectToJson(customer);
                            System.out.println("Password updated");
                        }
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    case 4:
                        Cls.cls();
                        boolean validPhoneNumber = false;
                        while (!validPhoneNumber) {
                            //Prompt the customer to enter their new phone number
                            System.out.println("Enter the customer's new phone number: (XXX-XXX-XXXX)");
                            String phone = in.nextLine();
                            //Check if the phone number is valid
                            if (phone.matches("\\d{3}-\\d{3}-\\d{4}")) {
                                validPhoneNumber = true;
                                customer.changePhoneNumber(phone);
                                ObjectJson.objectToJson(customer);
                                System.out.println("Phone number updated");
                            } else {
                                //If the phone number is not valid, prompt them to try agan
                                System.out.println("Invalid phone number. Press enter to try again");
                                in.nextLine();
                                in.nextLine();
                            }
                        }
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    case 6:
                        Cls.cls();
                        if(!(customer instanceof RewardsCustomer)){
                            System.out.println("Invalid choice. Press enter to try again.");
                            in.nextLine();
                            in.nextLine();
                            execute(() -> customerManagement(employee));
                            break;
                        }
                        in.nextLine();
                        System.out.println("Enter the customer's new email address: ");
                        email = in.nextLine();
                        ((RewardsCustomer) customer).changeCustomerEmail(email);
                        ObjectJson.objectToJson(customer);
                        System.out.println("Email address updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    case 7:
                        Cls.cls();
                        if (!(customer instanceof RewardsCustomer)) {
                            System.out.println("Invalid choice. Press enter to try again.");
                            in.nextLine();
                            in.nextLine();
                            execute(() -> customerManagement(employee));
                            break;
                        } else {
                            System.out.println("What would you like to do?");
                            System.out.println("Press 1 to edit the customer's reward points");
                            System.out.println("Press 2 to edit the customer's reward discount");
                            System.out.println("Press 3 to return to the menu");
                            choice = in.nextInt();
                            switch (choice) {
                                case 1:
                                    Cls.cls();
                                    System.out.println("Enter the customer's new points: ");
                                    int points = in.nextInt();
                                    ((RewardsCustomer) customer).getLoyaltyAccount().setPoints(points);
                                    ObjectJson.objectToJson(customer);
                                    System.out.println("Points updated");
                                    System.out.println("Press enter to return to the menu");
                                    in.nextLine();
                                    in.nextLine();
                                    execute(() -> customerManagement(employee));
                                    break;
                                case 2:
                                    Cls.cls();
                                    System.out.println("Enter the customer's new discount: ");
                                    double discount = in.nextDouble();
                                    ((RewardsCustomer) customer).getLoyaltyAccount().getDiscountObject().setDiscountPercent(discount);
                                    ObjectJson.objectToJson(customer);
                                    System.out.println("Discount updated");
                                    System.out.println("Press enter to return to the menu");
                                    in.nextLine();
                                    in.nextLine();
                                    execute(() -> customerManagement(employee));
                                    break;
                                case 3:
                                    Cls.cls();
                                    execute(() -> customerManagement(employee));
                                    break;
                                default:
                                    Cls.cls();
                                    System.out.println("Invalid choice. Press enter to try again.");
                                    in.nextLine();
                                    in.nextLine();
                                    execute(() -> customerManagement(employee));
                                    break;
                            }
                        }
                    case 5:
                        Cls.cls();
                        execute(() -> customerManagement(employee));
                        break;
                }
        }
    }
    public static void customerSignUp(){
        Scanner in = new Scanner(System.in);
        Cls.cls();
        System.out.println("New Customer Sign Up");
        System.out.println("Please enter your first and last name: ");
        String name = in.nextLine();
        System.out.println("Please enter your username: ");
        String username = in.nextLine();
        System.out.println("Please enter your password: ");
        String password = in.nextLine();
        System.out.println("Please enter your phone number: ");
        String phoneNumber = in.nextLine();
        Cls.cls();
        System.out.println("Thank you for signing up! Your account has been created.");
        System.out.println("Would you like to sign up for our rewards program and receive a 250 point bonus?\n");
        System.out.println("Press 1 for yes");
        System.out.println("Press 2 for no");
        int choice = in.nextInt();
        switch (choice){
            case 1:
                Cls.cls();
                System.out.println("Please enter your email address: ");
                String email = in.nextLine();
                RewardsCustomer rewardsCustomer = new RewardsCustomer(name, username, password, phoneNumber, email, 250, new Discount());
                ObjectJson.objectToJson(rewardsCustomer);
                Cls.cls();
                System.out.println("Thank you for signing up! Your Rewards account has been created.");
                System.out.println(rewardsCustomer);
                System.out.println("Press enter to return and login");
                in.nextLine();
                Cls.cls();
                execute(() -> Main.main(null));
                break;
            case 2:
                Cls.cls();
                Customer customer = new Customer(name, username, password, phoneNumber);
                ObjectJson.objectToJson(customer);
                System.out.println("Thank you for signing up! Your account has been created.");
                System.out.println(customer);
                System.out.println("Press enter to return and login");
                in.nextLine();
                Cls.cls();
                execute(() -> Main.main(null));
                break;
            default:
                Cls.cls();
                System.out.println("Invalid choice. Press enter to try again.");
                in.nextLine();
                in.nextLine();
                execute(CustomerManagement::customerSignUp);
                break;
        }
    }

    public static void customerAccountManager(Customer customer) {
        Cls.cls();
        System.out.println("Welcome to the customer account manager");
        System.out.println("Press 1 to view your account information");
        System.out.println("Press 2 to edit your account information");
        if(customer instanceof RewardsCustomer) {
            System.out.println("Press 3 to manage your loyalty account");
            System.out.println("Press 4 to return to the main menu");
        }else {
            System.out.println("Press 3 to return to the main menu");
        }
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if(!(customer instanceof RewardsCustomer) && choice == 3){
            choice = 4;
        }
        switch (choice) {
            case 1:
                Cls.cls();
                System.out.println(customer);
                System.out.println("Press enter to return to the menu");
                in.nextLine();
                in.nextLine();
                execute(() -> customerAccountManager(customer));
                break;
            case 2:
                Cls.cls();
                System.out.println("Press 1 to edit your name");
                System.out.println("Press 2 to edit your username");
                System.out.println("Press 3 to edit your password");
                System.out.println("Press 4 to edit your phone number");
                if(customer instanceof RewardsCustomer) {
                    System.out.println("Press 5 to edit your email address");
                    System.out.println("Press 6 to return to the menu");
                }else {
                    System.out.println("Press 5 to return to the menu");
                }
                choice = in.nextInt();
                if(!(customer instanceof RewardsCustomer) && choice == 5){
                    choice = 6;
                }
                switch (choice) {
                    case 1:
                        Cls.cls();
                        in.nextLine();
                        System.out.println("Enter your new name: ");
                        customer.setName(in.nextLine());
                        ObjectJson.objectToJson(customer);
                        System.out.println("Name updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                    case 2:
                        Cls.cls();
                        in.nextLine();
                        System.out.println("Enter your new username: ");
                        customer.setUsername(in.nextLine());
                        ObjectJson.objectToJson(customer);
                        System.out.println("Username updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 3:
                        Cls.cls();
                        in.nextLine();
                        int count = 0;
                        //Check if customer knows their current password
                        while(!in.nextLine().equals(customer.getPassword())){
                            System.out.println("Enter your current password: ");
                            String currentPassword = in.nextLine();
                            //If they don't, give them 5 attempts to enter it
                            if (!currentPassword.equals(customer.getPassword())) {
                                count++;
                                System.out.println("Incorrect password");
                                System.out.println("Press enter to try again. You have " + (5 - count) + " attempts remaining");
                                in.nextLine();
                                in.nextLine();
                                if(count == 5){
                                    System.out.println("Too many attempts. Returning to the menu");
                                    execute(() -> customerAccountManager(customer));
                                    break;
                                }
                            }
                        }
                        boolean validPassword = false;
                        //Once they enter their current password, prompt them to enter their new password twice
                        Cls.cls();
                        while(!validPassword) {
                            System.out.println("Enter your new password: ");
                            String newPassword = in.nextLine();
                            System.out.println("Enter your new password again: ");
                            String passwordVerification = in.nextLine();
                            //If the two passwords don't match, prompt them to try again
                            if (!newPassword.equals(passwordVerification)) {
                                System.out.println("Passwords do not match");
                                System.out.println("Press enter to try again");
                                in.nextLine();
                                in.nextLine();
                            }else{
                                //If the two passwords match, update the customer's password and save it to the file
                                customer.setPassword(newPassword);
                                ObjectJson.objectToJson(customer);
                                System.out.println("Password updated");
                                validPassword = true;
                            }
                        }
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 4:
                        Cls.cls();
                        boolean validPhoneNumber = false;
                        while(!validPhoneNumber) {
                            //Prompt the customer to enter their new phone number
                            System.out.println("Enter your new phone number: (XXX-XXX-XXXX)");
                            String phoneNumber = in.nextLine();
                            //Check if the phone number is valid
                            if (phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}")) {
                                validPhoneNumber = true;
                                customer.changePhoneNumber(phoneNumber);
                                ObjectJson.objectToJson(customer);
                                ObjectJson.objectToJson(customer);
                                System.out.println("Phone number updated");
                            } else {
                                Cls.cls();
                                //If the phone number is not valid, prompt them to try agan
                                System.out.println("Invalid phone number. Press enter to try again with the format XXX-XXX-XXXX");
                                in.nextLine();
                                in.nextLine();
                            }
                        }
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 5:
                        boolean validEmail = false;
                        String email = null;
                        while(!validEmail){
                            in.nextLine();
                            Cls.cls();
                            System.out.println("Enter your new email address: ");
                            email = in.nextLine();
                            if(email.matches("^(.+)@(.+)$")){
                                validEmail = true;
                            }else{
                                System.out.println("Invalid email address. Press enter to try again");
                            }
                        }
                        ((RewardsCustomer) customer).changeCustomerEmail(email);
                        ObjectJson.objectToJson(customer);
                        System.out.println("Email address updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 6:
                        Cls.cls();
                        execute(() -> customerAccountManager(customer));
                        break;
                    default:
                        Cls.cls();
                        System.out.println("Invalid choice. Press enter to try again.");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                }
            case 3:
                Cls.cls();
                if(!(customer instanceof RewardsCustomer)){
                    System.out.println("Invalid choice. Press enter to try again.");
                    in.nextLine();
                    in.nextLine();
                    execute(() -> customerAccountManager(customer));
                    break;
                }
                System.out.println("What would you like to do?");
                System.out.println("Press 1 to view your reward points");
                System.out.println("Press 2 to view your reward discount");
                System.out.println("Press 3 to return to the menu");
                choice = in.nextInt();
                switch (choice) {
                    case 1:
                        Cls.cls();
                        System.out.println("Your current reward points are: " + ((RewardsCustomer) customer).getLoyaltyAccount().getPoints());
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 2:
                        Cls.cls();
                        System.out.println("Your current reward discount is: " + ((RewardsCustomer) customer).getLoyaltyAccount().getDiscountObject().getDiscountPercent());
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 3:
                        Cls.cls();
                        execute(() -> customerAccountManager(customer));
                        break;
                    default:
                        Cls.cls();
                        System.out.println("Invalid choice. Press enter to try again.");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                }
                break;
            case 4:
                Cls.cls();
                execute(() -> Main.main(null));
                break;
            default:
                Cls.cls();
                System.out.println("Invalid choice. Press enter to try again.");
                in.nextLine();
                in.nextLine();
                execute(() -> customerAccountManager(customer));
                break;
        }
    }

    private static void execute(FunctionCaller f) {
        f.apply();
    }
}

