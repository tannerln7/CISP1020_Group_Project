package Main;
import Customers.*;
import Helpers.*;
import Products.OfferManagement;
import Products.ProductManagement;
import Retail_Operations.*;
import Transactions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


//TODO: Start working on the main menu for the program.
//BODY: This will be a menu that allows users to select between the different menus for the program. It should start with a login screen with an option for staff login or customer login, and then allow the user to select between the different menus.
public class Main {

    public static void main(String[] args) {
        File[] employeeFiles = ObjectJson.listFiles(Employee.class);
        File[] customerFiles = ObjectJson.listFiles(Customer.class);
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        Employee loggedInEmployee = new Employee("", "", "", "", "");
        Customer loggedInCustomer = new Customer("", "", "", "");

        //Load all employee files into an arraylist of employees in order to check logins
        if (employeeFiles != null) {
            for (File file : employeeFiles) {
                Employee employee = ObjectJson.objectFromJson(file.getName(), Employee.class);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        }
        //Load all customer files into an arraylist of customers in order to check logins
        if (customerFiles != null) {
            for (File file : customerFiles) {
                Customer customer = ObjectJson.objectFromJson(file.getName(), Customer.class);
                if (customer != null) {
                    customers.add(customer);
                }
            }
        }

        Scanner in = new Scanner(System.in);
        boolean validLogin = false;
        while (!validLogin) {
            System.out.println("Welcome to the POS system");
            System.out.println("Press 1 to login as a customer");
            System.out.println("Press 2 to login as an employee");
            System.out.println("Press 3 to sign up as a customer");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    //If user selects customer login, prompt for username and password
                    System.out.println("Customer login");
                    System.out.println("Username: ");
                    String username = in.next();
                    System.out.println("Password: ");
                    String pass = in.next();
                    //Check if the username and password match a customer in the system
                    for (Customer customer : customers) {
                        if (customer.getUsername().equals(username) && customer.getPassword().equals(pass)) {
                            validLogin = true;
                            break;
                        }
                    }
                    if (!validLogin) {
                        System.out.println("Invalid login");
                        System.out.println("Press enter to try again");
                        in.nextLine();
                        execute(() -> main(null));
                        break;
                    }
                    //If the login is valid, call the customer menu
                    customerMenu(loggedInCustomer);
                    break;
                case 2:
                    //If user selects employee login, prompt for username and password
                    System.out.println("Employee login");
                    System.out.println("login: ");
                    String employeeUsername = in.next();
                    System.out.println("password: ");
                    String employeePass = in.next();
                    //Check if the username and password match an employee in the system
                    for (Employee employee : employees) {
                        if (employee.getUsername().equals(employeeUsername) && employee.getPassword().equals(employeePass)) {
                            validLogin = true;
                            break;
                        }
                    }
                    if (!validLogin) {
                        System.out.println("Invalid login");
                        System.out.println("Press enter to try again");
                        in.nextLine();
                        execute(() -> main(null));
                        break;
                    }
                    //If the login is valid, call the employee menu
                    employeeMenu(loggedInEmployee);
                    break;
                case 3:
                    //If user selects customer sign up, call the customer sign up method
                    CustomerManagement.customerSignUp();
                default:
                    System.out.println("Invalid choice. Press Enter to try again.");
                    in.nextLine();
                    in.nextLine();
                    Cls.cls();
                    execute(() -> main(null));
            }
        }
    }

    private static void customerMenu(Customer customer) {
        //TODO: Create menu for customers to manager their own accounts. Pass customer file to CustomerManagement.customerManagement
        Cls.cls();
        Scanner in = new Scanner(System.in);
        System.out.println("Login successful");
        System.out.println("Welcome " + customer.getName());
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to manage your account");
        System.out.println("Press 2 to manage your recent transactions");
        System.out.println("Press 3 to manage or signup for a loyalty account");
        int menuChoice = in.nextInt();
        switch (menuChoice) {
            case 1:
                Cls.cls();
                CustomerManagement.customerAccountManager(customer);
                break;
            case 2:
                Cls.cls();
                TransactionManagement.cutomerTransactionManager(customer);
                break;
            case 3:
                //if the customer is a rewards customer, call the loyalty account management menu
                Cls.cls();
                if (customer instanceof RewardsCustomer rewardsCustomer) {
                    LoyaltyAccountManagement.customerLoyaltyAccountManagement(rewardsCustomer);
                }else {
                    //if the customer is not a rewards customer, call the rewards sign up method
                    LoyaltyAccountManagement.rewardsSignUp(customer);
                }
                break;
            default:
                System.out.println("Invalid choice. Press enter to try again.");
                in.nextLine();
                execute(() -> customerMenu(customer));
        }

    }

    private static void employeeMenu(Employee employee) {
        Cls.cls();
        Scanner in = new Scanner(System.in);
        System.out.println("Login successful");
        System.out.println("Welcome " + employee.getName());
        switch(employee.getPosition()){
            case "Manager":
                System.out.println("What would you like to do?");
                System.out.println("Press 1 to open the Customer Receipt Manager");
                System.out.println("Press 2 to open the Offer Manager");
                System.out.println("Press 3 to open the Employee Manager");
                System.out.println("Press 4 to open the Customer Account Manager");
                System.out.println("Press 5 to open the Product Manager");
                int menuChoice = in.nextInt();
                switch (menuChoice) {
                    case 1:
                        Cls.cls();
                        TransactionManagement.transactionManager(employee);
                        break;
                    case 2:
                        Cls.cls();
                        OfferManagement.offerManagment(employee);
                        break;
                    case 3:
                        Cls.cls();
                        EmployeeManagement.employeeManagement(employee);
                        break;
                    case 4:
                        Cls.cls();
                        CustomerManagement.customerManagement(employee);
                        break;
                    case 5:
                        Cls.cls();
                        ProductManagement.productManagement(employee);
                        break;
                    default:
                        System.out.println("Invalid choice. Press enter to try again.");
                        in.nextLine();
                        execute(() -> employeeMenu(employee));
                }
                break;
            case "Employee":
                System.out.println("What would you like to do?");
                System.out.println("Press 1 to open the Customer Receipt Manager");
                System.out.println("Press 2 to open the Offer Manager");
                System.out.println("Press 5 to open the Product Manager");
                int menuChoice2 = in.nextInt();
                switch (menuChoice2) {
                    case 1:
                        TransactionManagement.transactionManager(employee);
                        break;
                    case 2:
                        OfferManagement.offerManagment(employee);
                        break;
                    case 3: ProductManagement.productManagement(employee);
                        break;
                    default:
                        System.out.println("Invalid choice. Press enter to try again.");
                        in.nextLine();
                        execute(() -> employeeMenu(employee));;
                }
        }
    }
    public static void execute(FunctionCaller f) {
        f.apply();
    }


}

