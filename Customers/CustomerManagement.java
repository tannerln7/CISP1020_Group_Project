package Customers;

import Helpers.Cls;
import Helpers.ObjectJson;
import Main.Main;
import Products.Discount;
import Retail_Operations.Employee;
import java.util.ArrayList;
import java.util.Scanner;

import static Main.Main.execute;

//TODO: Bug-test customer management, customer account manager, and customer sign up
/**
 * This class provides methods for managing customers in a retail system. It includes methods for viewing all customers,
 * viewing a specific customer, adding a new customer, editing a customer, and deleting a customer.
 */
public abstract class CustomerManagement extends Main {
    /**
     * This method provides a menu for managing customers. It allows an employee to view all customers, view a specific
     * customer, add a new customer, edit a customer, and delete a customer.
     *
     * @param employee the employee who is managing the customers
     */
    public static void customerManagement(Employee employee) {
        Cls.cls();
        if (!employee.getPosition().equals("Manager")) {
            System.out.println("You do not have permission to access this menu");
            System.out.println("Press enter to return to the main menu");
            Scanner in = new Scanner(System.in);
            in.nextLine();
            execute(() -> Main.main(null));
            return;
        }
        Scanner in = new Scanner(System.in);
        ArrayList<Customer> allCustomers = Main.loadCustomers();


        System.out.println("Welcome to the customer management menu");
        System.out.println("Press 1 to view all customers");
        System.out.println("Press 2 to view a specific customer");
        System.out.println("Press 3 to add a new customer");
        System.out.println("Press 4 to edit a customer");
        System.out.println("Press 5 to delete a customer");
        System.out.println("Press 6 to return to the employee menu");
        int choice = in.nextInt();
        in.nextLine();
        String username;
        Customer customer;
        String email;
        switch (choice) {
            case 1:
                //View all customers
                Cls.cls();
                System.out.println("All customers:");
                for (Customer cust : allCustomers) {
                    System.out.println(cust);
                }
                System.out.println("Press enter to return to the menu");
                in.nextLine();
                execute(() -> customerManagement(employee));
                break;
            case 2:
                //View a specific customer
                Cls.cls();
                customer = getCustomer(in);
                Cls.cls();
                System.out.println("Customer found!");
                System.out.println(customer);
                System.out.println();
                System.out.println("Press enter to return to the menu");
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
                        Cls.cls();
                        System.out.println("Username already exists");
                        System.out.println(cust);
                        System.out.println();
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    }
                }
                System.out.println();
                System.out.println("Username " + username + " is available");
                System.out.println();
                System.out.println("Enter the customer's first and last name: ");
                String name = in.nextLine();
                System.out.println("Enter the customer's password: ");
                String password = in.nextLine();
                System.out.println("Enter the customer's phone number: (XXX-XXX-XXXX)");
                String phoneNumber = in.nextLine();
                System.out.println("Should this customer be a rewards customer? Press 1 for yes, 2 for no");
                choice = in.nextInt();
                in.nextLine();
                if (choice == 1) {
                    System.out.println("Enter the customer's email address: ");
                    email = in.nextLine();
                    customer = new RewardsCustomer(name, username, password, phoneNumber, email, 250, new Discount(0.05,0));
                    Cls.cls();
                    System.out.println("Rewards customer created");
                } else {
                    customer = new Customer(name, username, password, phoneNumber);
                    Cls.cls();
                    System.out.println("Customer created");
                }
                ObjectJson.objectToJson(customer);
                System.out.println(customer);
                System.out.println();
                System.out.println("Press enter to return to the menu");
                in.nextLine();
                execute(() -> customerManagement(employee));
                break;
            case 4:
                //Edit a customer
                Cls.cls();
                customer = getCustomer(in);
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
                        System.out.println("Enter the customer's new name: ");
                        customer.setName(in.nextLine());
                        ObjectJson.objectToJson(customer);
                        System.out.println("Name updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    case 2:
                        Cls.cls();
                        System.out.println("Enter the customer's new username: ");
                        customer.setUsername(in.nextLine());
                        ObjectJson.objectToJson(customer);
                        System.out.println("Username updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    case 3:
                        Cls.cls();
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
                            }
                        }
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    case 6:
                        Cls.cls();
                        if (!(customer instanceof RewardsCustomer)) {
                            System.out.println("Invalid choice. Press enter to try again.");
                            in.nextLine();
                            execute(() -> customerManagement(employee));
                            break;
                        }
                        System.out.println("Enter the customer's new email address: ");
                        email = in.nextLine();
                        ((RewardsCustomer) customer).changeCustomerEmail(email);
                        ObjectJson.objectToJson(customer);
                        System.out.println("Email address updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    case 7:
                        Cls.cls();
                        if (!(customer instanceof RewardsCustomer)) {
                            System.out.println("Invalid choice. Press enter to try again.");
                            in.nextLine();
                            execute(() -> customerManagement(employee));
                            break;
                        } else {
                            System.out.println("What would you like to do?");
                            System.out.println("Press 1 to edit the customer's reward points");
                            System.out.println("Press 2 to edit the customer's reward discount");
                            System.out.println("Press 3 to return to the menu");
                            choice = in.nextInt();
                            in.nextLine();
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
                                    execute(() -> customerManagement(employee));
                                    break;
                                case 2:
                                    Cls.cls();
                                    System.out.println("Enter the customer's new discount: ");
                                    double discount = in.nextDouble();
                                    in.nextLine();
                                    ((RewardsCustomer) customer).getLoyaltyAccount().getDiscountObject().setDiscountPercent(discount);
                                    ObjectJson.objectToJson(customer);
                                    System.out.println("Discount updated");
                                    System.out.println("Press enter to return to the menu");
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
                                    execute(() -> customerManagement(employee));
                                    break;
                            }
                        }
                    case 5:
                        Cls.cls();
                        execute(() -> customerManagement(employee));
                        break;
                    default:
                        Cls.cls();
                        System.out.println("Invalid choice. Press enter to try again.");
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                }
            case 5:
                //Delete a customer
                Cls.cls();
                customer = getCustomer(in);
                System.out.println("Are you sure you want to delete this customer?");
                System.out.println("Press 1 for yes");
                System.out.println("Press 2 for no");
                choice = in.nextInt();
                in.nextLine();
                switch (choice) {
                    case 1:
                        Cls.cls();
                        System.out.println("Customer deleted");
                        allCustomers.remove(customer);
                        ObjectJson.deleteObject(customer.getJsonId(), customer.getClass());
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    case 2:
                        Cls.cls();
                        System.out.println("Customer not deleted");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                    default:
                        Cls.cls();
                        System.out.println("Invalid choice. Press enter to try again.");
                        in.nextLine();
                        execute(() -> customerManagement(employee));
                        break;
                }
            case 6:
                Cls.cls();
                execute(() -> Main.employeeMenu(employee));
                break;
            default:
                Cls.cls();
                System.out.println("Invalid choice. Press enter to try again.");
                in.nextLine();
                execute(() -> customerManagement(employee));
                break;
        }
    }

    /**
     * This method retrieves a customer based on the username entered by the user. If the username matches a customer's
     * username, that customer is returned. If no match is found, the method prompts the user to try again.
     *
     * @param in the Scanner object to read the user's input
     * @return the customer with the matching username, or null if no match is found
     */
    private static Customer getCustomer(Scanner in) {
        ArrayList<Customer> allCustomers = Main.loadCustomers();
        String username;
        Customer customer = null;
        do{
            System.out.println("Please enter the customer's username or type -L to list all customers: ");
            username = in.nextLine();
            if (username.equalsIgnoreCase("-L")) {
                Cls.cls();
                System.out.println("All customers:");
                for (Customer cust : allCustomers) {
                    System.out.println("Name: " + cust.getName() + " - Username: " + cust.getUsername());
                }
                System.out.println();
                continue;
            }
            for (Customer cust : allCustomers) {
                if (cust.getUsername().equals(username)) {
                    System.out.println(cust);
                    customer = cust;
                    break;
                }
            }
            if (customer == null) {
                System.out.println("Customer not found");
                System.out.println("Press enter to try again");
                in.nextLine();
                Cls.cls();
            }
        } while (customer == null);
        return customer;
    }

    /**
     * This method allows a new customer to sign up. It prompts the user to enter their name, username, password, and
     * phone number. The user is then asked if they want to sign up for a rewards program. If they choose to do so, they
     * are prompted to enter their email address and a RewardsCustomer object is created. If they choose not to sign up
     * for the rewards program, a Customer object is created. The new customer object is then serialized and saved to a
     * file.
     */
    public static void customerSignUp() {
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
        in.nextLine();
        switch (choice) {
            case 1:
                Cls.cls();
                System.out.println("Please enter your email address: ");
                String email = in.nextLine();
                RewardsCustomer rewardsCustomer = new RewardsCustomer(name, username, password, phoneNumber, email, 250, new Discount(0.05,0));
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
                execute(CustomerManagement::customerSignUp);
                break;
        }
    }

    /**
     * This method allows a customer to manage their account. It provides a menu for the customer to view their account
     * information, edit their account information, or manage their loyalty account (if they are a RewardsCustomer).
     *
     * @param customer the customer who is managing their account
     */
    public static void customerAccountManager(Customer customer) {
        Cls.cls();
        System.out.println("Welcome to the customer account manager\n");
        System.out.println("Press 1 to view your account information");
        System.out.println("Press 2 to edit your account information");
        if (customer instanceof RewardsCustomer) {
            System.out.println("Press 3 to manage your loyalty account");
            System.out.println("Press 4 to return to the main menu");
        } else {
            System.out.println("Press 3 to return to the main menu");
        }
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        in.nextLine();
        if (!(customer instanceof RewardsCustomer) && choice == 3) {
            choice = 4;
        }
        switch (choice) {
            case 1:
                Cls.cls();
                System.out.println(customer);
                System.out.println("Press enter to return to the menu");
                in.nextLine();
                execute(() -> customerAccountManager(customer));
                break;
            case 2:
                Cls.cls();
                System.out.println("Press 1 to edit your name");
                System.out.println("Press 2 to edit your username");
                System.out.println("Press 3 to edit your password");
                System.out.println("Press 4 to edit your phone number");
                if (customer instanceof RewardsCustomer) {
                    System.out.println("Press 5 to edit your email address");
                    System.out.println("Press 6 to return to the menu");
                } else {
                    System.out.println("Press 5 to return to the menu");
                }
                choice = in.nextInt();
                in.nextLine();
                if (!(customer instanceof RewardsCustomer) && choice == 5) {
                    choice = 6;
                }
                switch (choice) {
                    case 1:
                        Cls.cls();
                        System.out.println("Enter your new name: ");
                        customer.setName(in.nextLine());
                        ObjectJson.objectToJson(customer);
                        System.out.println("Name updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                    case 2:
                        Cls.cls();
                        System.out.println("Enter your new username: ");
                        customer.setUsername(in.nextLine());
                        ObjectJson.objectToJson(customer);
                        System.out.println("Username updated");
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 3:
                        Cls.cls();
                        int count = 0;
                        //Check if customer knows their current password
                        boolean passwordCheck = false;
                        while (!passwordCheck) {
                            System.out.println("Enter your current password: ");
                            String currentPassword = in.nextLine();
                            //If they don't, give them 5 attempts to enter it
                            if (currentPassword.equals(customer.getPassword())) {
                                passwordCheck = true;
                            } else {
                                count++;
                                System.out.println("Incorrect password");
                                System.out.println("Press enter to try again. You have " + (5 - count) + " attempts remaining");
                                in.nextLine();
                                if (count == 5) {
                                    System.out.println("Too many attempts. Returning to the menu");
                                    execute(() -> customerAccountManager(customer));
                                    break;
                                }
                            }
                        }
                        boolean validPassword = false;
                        //Once they enter their current password, prompt them to enter their new password twice
                        Cls.cls();
                        while (!validPassword) {
                            System.out.println("Enter your new password: ");
                            String newPassword = in.nextLine();
                            System.out.println("Enter your new password again: ");
                            String passwordVerification = in.nextLine();
                            //If the two passwords don't match, prompt them to try again
                            if (!newPassword.equals(passwordVerification)) {
                                System.out.println("Passwords do not match");
                                System.out.println("Press enter to try again");
                                in.nextLine();
                            } else {
                                //If the two passwords match, update the customer's password and save it to the file
                                customer.setPassword(newPassword);
                                ObjectJson.objectToJson(customer);
                                System.out.println("Password updated");
                                validPassword = true;
                            }
                        }
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 4:
                        Cls.cls();
                        boolean validPhoneNumber = false;
                        while (!validPhoneNumber) {
                            //Prompt the customer to enter their new phone number
                            System.out.println("Enter your new phone number: (XXX-XXX-XXXX)");
                            String phoneNumber = in.nextLine();
                            //Check if the phone number is valid
                            if (phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}")) {
                                validPhoneNumber = true;
                                customer.changePhoneNumber(phoneNumber);
                                ObjectJson.objectToJson(customer);
                                System.out.println("Phone number updated");
                            } else {
                                Cls.cls();
                                //If the phone number is not valid, prompt them to try agan
                                System.out.println("Invalid phone number. Press enter to try again.");
                                in.nextLine();
                            }
                        }
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 5:
                        boolean validEmail = false;
                        String email = null;
                        while (!validEmail) {
                            Cls.cls();
                            System.out.println("Enter your new email address: ");
                            email = in.nextLine();
                            if (email.matches("^(.+)@(.+)$")) {
                                validEmail = true;
                            } else {
                                System.out.println("Invalid email address. Press enter to try again");
                            }
                        }
                        ((RewardsCustomer) customer).changeCustomerEmail(email);
                        ObjectJson.objectToJson(customer);
                        System.out.println("Email address updated");
                        System.out.println("Press enter to return to the menu");
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
                        execute(() -> customerAccountManager(customer));
                        break;
                }
            case 3:
                Cls.cls();
                if (!(customer instanceof RewardsCustomer)) {
                    System.out.println("Invalid choice. Press enter to try again.");
                    in.nextLine();
                    execute(() -> customerAccountManager(customer));
                    break;
                }
                System.out.println("What would you like to do?");
                System.out.println("Press 1 to view your reward points");
                System.out.println("Press 2 to view your reward discount");
                System.out.println("Press 3 to return to the menu");
                choice = in.nextInt();
                in.nextLine();
                switch (choice) {
                    case 1:
                        Cls.cls();
                        System.out.println("Your current reward points are: " + ((RewardsCustomer) customer).getLoyaltyAccount().getPoints());
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 2:
                        Cls.cls();
                        System.out.println("Your current reward discount is: " + ((RewardsCustomer) customer).getLoyaltyAccount().getDiscountObject().getDiscountPercent());
                        System.out.println("Press enter to return to the menu");
                        in.nextLine();
                        execute(() -> customerAccountManager(customer));
                        break;
                    case 3:
                        Cls.cls();
                        execute(() -> customerAccountManager(customer));
                        break;
                    default:
                        Cls.cls();
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
                execute(() -> customerAccountManager(customer));
                break;
        }
    }

}

