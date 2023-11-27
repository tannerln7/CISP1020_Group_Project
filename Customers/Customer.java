package Customers;
import Helpers.JsonIdentifiable;
import Helpers.ObjectJson;
import Transactions.Receipt;
import java.util.ArrayList;

/**
 * This class represents a customer of the store. It stores the customer's name, phone number,
 * and a list of their receipts.
 */
public class Customer implements JsonIdentifiable {
    private String username;
    private String password;

    private String name;
    private String phoneNumber;
    private final ArrayList<Receipt> receipts = new ArrayList<>();


    //Customer constructor with id increase by 1 for each object created
    public Customer(String name, String username, String password, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    //getName method
    public String getName() {
        return this.name;
    }

    //Change Name method
    public void setName(String newName) {
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
    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
    }

    /**
     * Retrieves the customer's list of receipts.
     *
     * @return The customer's list of receipts.
     */
    public ArrayList<Receipt> getReceipts() {
        return this.receipts;
    }

    /**
     * Updates the customer's save file by converting the customer object to JSON format and writing it to a file.
     */
    public void updateSaveFile() {
        ObjectJson.objectToJson(this);
    }

    /**
     * Generates a unique customer ID by incrementing the `lastCustomerId` variable and returning the new value.
     *
     * @return The newly generated customer ID.
     */


    /**
     * Overrides the default `toString()` method to provide a human-readable representation of the customer object.
     *
     * @return A string representation of the customer, including their customer ID, name, and phone number.
     */
    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n")
                .append("  CustomerId= ").append(this.username).append(",\n")
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
        return this.username;
    }
}
//    // Method to create a menu for managing customer information
//    public static void customerManagementMenu(List<Customer> customers) {
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("Customer Management Menu");
//            System.out.println("1. Add Customer");
//            System.out.println("2. Remove Customer");
//            System.out.println("3. Edit Customer Information");
//            System.out.println("4. View All Customers");
//            System.out.println("5. Exit");
//            System.out.print("Enter your choice: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume the newline character
//
//            switch (choice) {
//                case 1:
//                    addCustomer(customers);
//                    break;
//                case 2:
//                    removeCustomer(customers);
//                    break;
//                case 3:
//                    editCustomerInformation(customers);
//                    break;
//                case 4:
//                    viewAllCustomers(customers);
//                    break;
//                case 5:
//                    System.out.println("Exiting Customer Management Menu");
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }

    // Method to add customer in customerList
//    private static void addCustomer(List<Customer> customers) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter customer name: ");
//        String newName = scanner.nextLine();
//        System.out.print("Enter customer phone number: ");
//        String newPhoneNumber = scanner.nextLine();
//
//        Customer newCustomer = new Customer(newName, newPhoneNumber);
//        customers.add(newCustomer);
//        System.out.println("Customer added successfully: " + newCustomer);
//    }

//    // Method to remove customer in customerList
//    private static void removeCustomer(List<Customer> customers) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter customer ID to remove: ");
//        int customerId = scanner.nextInt();
//
//        for (Customer customer : customers) {
//            if (customer.getCustomerId() == customerId) {
//                customers.remove(customer);
//                System.out.println("Customer removed successfully.");
//                return;
//            }
//        }
//
//        System.out.println("Customer with ID " + customerId + " not found.");
//    }

    // Method to edit customer information in customerList
//    private static void editCustomerInformation(List<Customer> customers) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter customer ID to edit: ");
//        int customerId = scanner.nextInt();
//        scanner.nextLine();
//
//        for (Customer customer : customers) {
//            if (customer.getCustomerId() == customerId) {
//                System.out.println("You are editing customer: " + customer);
//
//                System.out.print("Enter new name: ");
//                String newName = scanner.nextLine();
//
//                customer.changeName(newName);
//
//                System.out.print("Enter new phone number: ");
//                String newPhoneNumber = scanner.nextLine();
//                customer.changePhoneNumber(newPhoneNumber);
//
//                System.out.println("Customer information updated successfully: " + customer);
//                return;
//            }
//        }
//
//        System.out.println("Customer with ID " + customerId + " not found.");
//    }
//
//    // Method to view all customers in customerList
//    private static void viewAllCustomers(List<Customer> customers) {
//        System.out.println("All Customers:");
//        for (Customer customer : customers) {
//            System.out.println(customer);
//        }
//    }
//}


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
