package Main;
import Customers.*;
import Helpers.*;
import Products.OfferManagement;
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

        //Load all employee files into an arraylist of employees
        if (employeeFiles != null) {
            for (File file : employeeFiles) {
                Employee employee = ObjectJson.objectFromJson(file.getName(), Employee.class);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        }
        //Load all customer files into an arraylist of customers
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
                    System.out.println("Customer login");
                    System.out.println("Username: ");
                    String username = in.next();
                    System.out.println("Password: ");
                    String pass = in.next();
                    //Check if the username and password match a customer in the system
                    for (Customer customer : customers) {
                        if (customer.getUsername().equals(username) && customer.getPassword().equals(pass)) {
                            validLogin = true;
                            loggedInCustomer = customer;
                        }
                    }
                    if (!validLogin) {
                        System.out.println("Invalid login");
                    }
                    break;
                case 2:
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
                        }
                    }
                    if (!validLogin) {
                        System.out.println("Invalid login");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            if(validLogin){
                System.out.println("Login successful");
                System.out.println("Welcome " + loggedInEmployee.getName());
                System.out.println("What would you like to do?");
                System.out.println("Press 1 to open the Customer Transaction Manager");
                System.out.println("Press 2 to open the Offer Manager");
                int menuChoice = in.nextInt();
                switch (menuChoice){
                    case 1:
                        TransactionManagement.transactionManager(loggedInEmployee);
                        break;
                    case 2:
                        OfferManagement.offerManagment(loggedInEmployee);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
