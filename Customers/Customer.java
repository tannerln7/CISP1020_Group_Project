package Customers;
import Helpers.JsonIdentifiable;
import Helpers.ObjectJson;
import Transactions.Receipt;
import java.util.ArrayList;

//Add the final CustomerID variable for each Customer. Customer can change Name, Phone number.
//TODO: Start working on the Management menu for Customers.
//BODy: This will be a menu that allows staff members to add, remove, and edit customer information.

//TODO: Start working on the user menu for Customers.
//BODy: This will be a menu that allows customers to view their information, update their information, view their receipts, and sign up for a rewards account.

//Added comments
/**
 * This class represents a customer of the store. It stores the customer's name, phone number,
 * and a list of their receipts.
 */
public class Customer implements JsonIdentifiable{
    private final int customerId;
    private String name;
    private String phoneNumber;
    private static int lastCustomerId;
    private final ArrayList<Receipt> receipts = new ArrayList<>();


    //Customer constructor with id increase by 1 for each object created
    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.customerId = generateId();
    }

    //getCustomerId method to show ID with 8 digits.
    public int getCustomerId() {
        return this.customerId;
    }

    //getName method
    public String getName() {
        return this.name;
    }

    //Change Name method
    public void changeName(String newName) {
        this.name = newName;
    }

    //getPhoneNumber method
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
    * Changes the customer's phone number.
    *
    * @param newPhoneNumber The new phone number for the customer.
    */
    public void changePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
    
    /**
    * Adds a receipt to the customer's list of receipts.
    *
    * @param receipt The receipt to add.
    */
    public void addReceipt(Receipt receipt){
        receipts.add(receipt);
    }

    /**
    * Retrieves the customer's list of receipts.
    *
    * @return The customer's list of receipts.
    */
    public ArrayList<Receipt> getReceipts(){
        return this.receipts;
    }

    /**
    * Updates the customer's save file by converting the customer object to JSON format and writing it to a file.
    */
    public void updateSaveFile(){
        ObjectJson.objectToJson(this);
    }

    /**
    * Generates a unique customer ID by incrementing the `lastCustomerId` variable and returning the new value.
    *
    * @return The newly generated customer ID.
    */
    private static int generateId() {
        lastCustomerId++;
        return lastCustomerId;
    }


    /**
    * Overrides the default `toString()` method to provide a human-readable representation of the customer object.
    *
    * @return A string representation of the customer, including their customer ID, name, and phone number.
    */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n")
                .append("  CustomerId= ").append(this.getCustomerId()).append(",\n")
                .append("  Name= ").append(name).append(",\n")
                .append("  Phone number= ").append(phoneNumber).append("\n")
                .append("}");
        return sb.toString();
    }

    /**
    * Overrides the `getJsonId()` method to generate a JSON-compatible ID for the customer.
    *
    * @return The JSON-compatible ID for the customer, which is formed by prefixing the customer ID with "Customer_".
    */
    @Override
    public String getJsonId() {
        return "Customer_" + this.customerId;
    }
}


    //I'm sorry for deleting your file write methods... I imagine that probably took a lot of trial and error...
    //I moved the file writes to the CustomerDemo class and changed them to match the rest of the project.


    //method to write a single object to file
//    public void writeCustomerToFile() {
//        File file = new File(this.getName() + ".txt");
//
//        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
//            writer.println("ID: " + this.getCustomerId());
//            writer.println("Name: " + this.getName());
//            writer.println("Phone Number: " + this.getPhoneNumber());
//
//            System.out.println("Customer " + this.getName() + " has been written to the file.");
//        } catch (IOException e) {
//        }
//    }
//
//    //Override method to write an ArrayList of Customers to file (use the default customer object to call this method)
//    public void writeCustomerToFile(List<Customer> customers) {
//        File file = new File("List_Customers.txt");
//
//        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
//            for (Customer customer : customers) {
//                writer.println(customer.toString());
//            }
//            System.out.println("Customer Objects have been written to List_Customer.txt.");
//        } catch (IOException e) {
//        }
//    }
