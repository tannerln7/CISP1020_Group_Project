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
public class Customer implements JsonIdentifiable{
    private final int customerId;
    private String name;
    private String phoneNumber;
    private int lastCustomerId;
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

    //Change Phone Number method
    public void changePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public void addReceipt(Receipt receipt){
        receipts.add(receipt);
    }

    public ArrayList<Receipt> getReceipts(){
        return this.receipts;
    }

    public void updateSaveFile(){
        ObjectJson.objectToJson(this);
    }

    private int generateId() {
        this.lastCustomerId++;
        return lastCustomerId;
    }


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
