package Customers;
//TODO: Add methods for keeping a list of customer receipts.
import Helpers.JsonIdentifiable;

//Add the final CustomerID variable for each Customer. Customer can change Name, Phone number.
public class Customer implements JsonIdentifiable{
    private final int customerId;
    private String name;
    private String phoneNumber;
    private static int lastCustomerId;


    //Customer constructor with id increase by 1 for each object created
    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.customerId = generateId();
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

    private static int generateId() {
        lastCustomerId += 1;
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
        return "Customer_" + customerId;
    }
}


    //I'm sorry for deleting the file writes... I image that probably took a lot of trial and error...
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
