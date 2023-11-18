package Customers;
//TODO: Refactor this to write the customer object to a file
//TODO: Create a list of Receipt objects associated with the Customer object and write Customer Object to file

//Waiting for Tuan's Customer class to be completed


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//Add the final CustomerID variable for each Customer. Customer can change Name, Phonenumber.
public class Customer {

    private static int initId = 1;
    private final int customerId;
    private String name;
    private String phoneNumber;

    //Default constructor with id 0, incase to use the method to write an ArrayList of Customer Objects to file.
    //Recommend create 01 default customer.
    public Customer() {
        this.customerId = 0;
    }

    //Customer constructor with id increase by 1 for each object created
    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.customerId = initId++;
    }

    // getId method so another class could get the id value.
    public int getId() {
        return customerId;
    }

    //getCustomerId method to show ID with 8 digits.
    public String getCustomerId() {
        return String.format("%08d", customerId);
    }

    //getName method
    public String getName() {
        return name;
    }

    //Change Name method
    public void changeName(String newName) {
        this.name = newName;
    }

    //getPhoneNumber method
    public String getPhoneNumber() {
        return phoneNumber;
    }

    //Change Phone Number method
    public void changePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    @Override
    public String toString() {
        return "{CustomerId= " + this.getCustomerId() + ", Name= " + name + ", Phone number= " + phoneNumber + '}';
    }

    //method to write a single object to file
    public void writeCustomerToFile() {
        File file = new File(this.getName() + ".txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("ID: " + this.getCustomerId());
            writer.println("Name: " + this.getName());
            writer.println("Phone Number: " + this.getPhoneNumber());

            System.out.println("Customer " + this.getName() + " has been written to the file.");
        } catch (IOException e) {
        }
    }

    //Override method to write an ArrayList of Customers to file (use the default customer object to call this method)
    public void writeCustomerToFile(List<Customer> customers) {
        File file = new File("List_Customers.txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Customer customer : customers) {
                writer.println(customer.toString());
            }
            System.out.println("Customer Objects have been written to List_Customer.txt.");
        } catch (IOException e) {
        }
    }
}
