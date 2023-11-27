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

//TODO: Bug-test the program

//We all have customer and manager accounts. Username is your github username and password is password

//customer login ---- username: test ---- password: password
//RewardsCustomer login ---- username: testrewards ---- password: password
//Cashier employee login ---- username: cashier ---- password: password
//Manager employee login ---- username: manager ---- password: password

public class Main {

    public static void main(String[] args) {
        ArrayList<Customer> customers = loadCustomers();
        ArrayList<Employee> employees = loadEmployees();

        Employee loggedInEmployee = null;
        Customer loggedInCustomer = null;

        Scanner in = new Scanner(System.in);
        boolean validLogin = false;
        while (!validLogin) {
            Cls.cls();
            System.out.println("Welcome to the POS system");
            System.out.println("Press 1 to login as a customer");
            System.out.println("Press 2 to login as an employee");
            System.out.println("Press 3 to sign up as a customer");
            System.out.println("Press 4 to exit");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    Cls.cls();
                    //If user selects customer login, prompt for username and password
                    System.out.println("Customer login");
                    System.out.println("Username: ");
                    String username = in.next();
                    System.out.println("Password: ");
                    String pass = in.next();
                    //Check if the username and password match a customer in the system
                    for (Customer cust : customers) {
                        if (cust.getUsername().equals(username) && cust.getPassword().equals(pass)) {
                            validLogin = true;
                            loggedInCustomer = cust;
                            break;
                        }
                    }
                    if (!validLogin) {
                        Cls.cls();
                        System.out.println("Invalid login");
                        System.out.println("Press enter to try again");
                        in.nextLine();
                        in.nextLine();
                        execute(() -> main(null));
                        break;
                    }
                    //If the login is valid, call the customer menu
                    customerMenu(loggedInCustomer);
                    break;
                case 2:
                    Cls.cls();
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
                            loggedInEmployee = employee;
                            break;
                        }
                    }
                    if (!validLogin) {
                        Cls.cls();
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
                    break;
                case 4:
                    //If user selects exit, exit the program
                    System.exit(0);
                    break;
                default:
                    Cls.cls();
                    System.out.println("Invalid choice. Press Enter to try again.");
                    in.nextLine();
                    in.nextLine();
                    Cls.cls();
                    execute(() -> main(null));
                    break;
            }
        }
    }

    public static ArrayList<Customer> loadCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        File[] customerFiles = ObjectJson.listFiles(Customer.class);
        if (customerFiles != null) {
            for (File file : customerFiles) {
                if (file.getName().contains("RewardsCustomer")){
                    RewardsCustomer rewardsCustomer = ObjectJson.objectFromJson(file.getName(), RewardsCustomer.class);
                    if (rewardsCustomer != null) {
                        customers.add(rewardsCustomer);
                    }
                }else {
                    Customer customer = ObjectJson.objectFromJson(file.getName(), Customer.class);
                    if (customer != null) {
                        customers.add(customer);
                    }
                }
            }
        }
        return customers;
    }

    public static ArrayList<Employee> loadEmployees() {
        File[] employeeFiles = ObjectJson.listFiles(Employee.class);
        ArrayList<Employee> employees = new ArrayList<>();
        if (employeeFiles != null) {
            for (File file : employeeFiles) {
                Employee employee = ObjectJson.objectFromJson(file.getName(), Employee.class);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        }
        return employees;
    }

    public static void customerMenu(Customer customer) {
        Cls.cls();
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome " + customer.getName() + "! Login successful!\n");
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to manage your account");
        System.out.println("Press 2 to manage your recent transactions");
        System.out.println("Press 3 to return to the main menu");
        int menuChoice = in.nextInt();
        switch (menuChoice) {
            case 1:
                CustomerManagement.customerAccountManager(customer);
                break;
            case 2:
                TransactionManagement.customerTransactionManager(customer);
                break;
            case 3:
                execute(() -> main(null));
                break;
            default:
                Cls.cls();
                System.out.println("Invalid choice. Press enter to try again.");
                in.nextLine();
                execute(() -> customerMenu(customer));
                break;
        }

    }

    public static void employeeMenu(Employee employee) {
        Cls.cls();
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome " + employee.getName() + "! Login successful\n");
        switch(employee.getPosition()){
            case "Manager":
                System.out.println("What would you like to do?");
                System.out.println("Press 1 to open the Customer Receipt Manager");
                System.out.println("Press 2 to open the Offer Manager");
                System.out.println("Press 3 to open the Employee Manager");
                System.out.println("Press 4 to open the Customer Account Manager");
                System.out.println("Press 5 to open the Product Manager");
                System.out.println("Press 6 to return to the main menu");
                int menuChoice = in.nextInt();
                switch (menuChoice) {
                    case 1:
                        TransactionManagement.transactionManager(employee);
                        break;
                    case 2:
                        OfferManagement.offerManagement(employee);
                        break;
                    case 3:
                        EmployeeManagement.employeeManagement(employee);
                        break;
                    case 4:
                        CustomerManagement.customerManagement(employee);
                        break;
                    case 5:
                        ProductManagement.productManagement(employee);
                        break;
                    case 6:
                        execute(() -> main(null));
                        break;
                    default:
                        Cls.cls();
                        System.out.println("Invalid choice. Press enter to try again.");
                        in.nextLine();
                        execute(() -> employeeMenu(employee));
                        break;
                }
                break;
            case "Employee":
                System.out.println("What would you like to do?");
                System.out.println("Press 1 to open the Customer Receipt Manager");
                System.out.println("Press 2 to open the Offer Manager");
                System.out.println("Press 3 to open the Product Manager");
                System.out.println("Press 4 to return to the main menu");
                int menuChoice2 = in.nextInt();
                switch (menuChoice2) {
                    case 1:
                        TransactionManagement.transactionManager(employee);
                        break;
                    case 2:
                        OfferManagement.offerManagement(employee);
                        break;
                    case 3:
                        ProductManagement.productManagement(employee);
                        break;
                    case 4:
                        execute(() -> main(null));
                        break;
                    default:
                        Cls.cls();
                        System.out.println("Invalid choice. Press enter to try again.");
                        in.nextLine();
                        execute(() -> employeeMenu(employee));
                        break;
                }
        }
    }
    public static void execute(FunctionCaller f) {
        f.apply();
    }


}

