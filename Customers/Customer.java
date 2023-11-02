package Customers;

public class Customer {
    private final String Name;
    private String phoneNumber;


    public Customer(String Name, String PhoneNumber) {
        this.Name = Name;
        this.phoneNumber = PhoneNumber;
    }
    public String getName() {
        return Name;
    }
    public void changeName(String newName) {
        this.phoneNumber = newName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void changePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

}
