package Customers;
import Helpers.JsonIdentifiable;
import Helpers.ObjectJson;
import Transactions.Receipt;
import java.util.ArrayList;

/**
 * This class represents a customer of the store. It stores the customer's username, password, name, phone number,
 * and a list of their receipts.
 */
public class Customer implements JsonIdentifiable {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private final ArrayList<Receipt> receipts = new ArrayList<>();

    /**
     * Constructs a new Customer with the specified name and phone number.
     *
     * @param name the name of the customer
     * @param phoneNumber the phone number of the customer
     */
    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructs a new Customer with the specified name, username, password, and phone number.
     *
     * @param name the name of the customer
     * @param username the username of the customer
     * @param password the password of the customer
     * @param phoneNumber the phone number of the customer
     */
    public Customer(String name, String username, String password, String phoneNumber) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the username of the customer.
     *
     * @param username the new username of the customer
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the customer.
     *
     * @return the username of the customer
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the password of the customer.
     *
     * @param password the new password of the customer
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the password of the customer.
     *
     * @return the password of the customer
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the name of the customer.
     *
     * @return the name of the customer
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param newName the new name of the customer
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Returns the phone number of the customer.
     *
     * @return the phone number of the customer
     */
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
     * Overrides the default `toString()` method to provide a human-readable representation of the customer object.
     *
     * @return A string representation of the customer, including their customer ID, name, and phone number.
     */
    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Username: ").append(this.username).append(",\n")
                .append("Name: ").append(name).append(",\n")
                .append("Phone number: ").append(phoneNumber).append("\n");
        return sb.toString();
    }

    /**
     * Overrides the `getJsonId()` method to generate a JSON-compatible ID for the customer.
     *
     * @return The JSON-compatible ID for the customer, which is the username of the customer.
     */
    @Override
    public String getJsonId() {
        return this.username;
    }
}