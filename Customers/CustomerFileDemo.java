package Customers;

import Helpers.ObjectJson;

import java.io.File;
import java.util.ArrayList;

public class CustomerFileDemo {

    public static void main(String[] args) {
        //Create some customer objects
        Customer customer = new Customer("Tuan Tran", "username","password", "987-654-3210");
        Customer customer1 = new Customer("***REMOVED*** ***REMOVED***", "username","password", "123-456-7890");
        RewardsCustomer rewardsCustomer = new RewardsCustomer("Tuan Tran", "987-654-3210",  "username","password","email@email.com");
        RewardsCustomer rewardsCustomer1 = new RewardsCustomer("***REMOVED*** ***REMOVED***", "username","password", "123-456-7890", "email@email.com");

        //If you want to write a single customer directly to a file, you can use this method
        ObjectJson.objectToJson(customer);
        //or this
        customer.updateSaveFile();
        //They do the same thing

        //This will write the customer object to a file named "Customer_<customerID>.json" and save it to the Customers\JSON Files\ folder
        //The name of the file comes from the getJsonId interface override at the bottom of the Customer class. It can be changed to whatever you want
        //if there is already a file for that customer, it will update it with the new information.

        //It works the same way for a rewards customer
        ObjectJson.objectToJson(rewardsCustomer);
        //The only difference is the file name will be "RewardsCustomer_<customerID>.json"

        //If you want to write multiple customers to files, you can make an array or any other list and loop through it
        Customer[] customers = {customer, customer1};
        for (Customer customer2 : customers) {
            ObjectJson.objectToJson(customer2);
        }
        //This will write both customers to files named "Customer_<customerID>.json" and save them to the Customers\JSON Files\ folder
        //It works the same way with an arraylist
        ArrayList<RewardsCustomer> rewardsCustomers = new ArrayList<>();
        rewardsCustomers.add(rewardsCustomer);
        rewardsCustomers.add(rewardsCustomer1);

        for (RewardsCustomer rewardsCustomer2 : rewardsCustomers) {
            ObjectJson.objectToJson(rewardsCustomer2);
        }


        //Clearing objects from memory to show that they are being read from the files and not just from memory
        customer = null;
        customer1 = null;
        rewardsCustomer = null;
        rewardsCustomer1 = null;
        customers = null;
        rewardsCustomers = null;


        //If you want to read a single customer back from a file there are a few ways to do it depending on what "information" the user has

        //If you have the customerID, you can directly read the file
        //You have to pass the Class of the object so that it knows what type of object to create
        customer = ObjectJson.objectFromJson("Customer_1.json", Customer.class);
        //This will read the file named "Customer_1.json" from the Customers\JSON Files\ folder and create a Customer object from it

        //you can also do this with a rewards customer
        rewardsCustomer = ObjectJson.objectFromJson("RewardsCustomer_1.json", RewardsCustomer.class);

        //If you DONT know the customer ID, You can use the listFiles method to get an array of all files in the Customers\JSON Files\ folder
        //This is useful if you want to search for a customer by name or phone number or any other instance variable

        //You still have to make sure the array is not null before you try to use it
        //get the file array
        File[] customerFiles = ObjectJson.listFiles(Customer.class);
        //check if the array is empty
        if(customerFiles != null) {
            //loop through the array
            for (File file : customerFiles) {
                //Use file.getname() to get the name of the file, and pass it to the objectFromJson method to re-create the customer object
                customer1 = ObjectJson.objectFromJson(file.getName(), Customer.class);
                //You can then check the customer object to see if it matches what you are looking for
                //Again, you have to make sure the customer object is not null before you try to use it
                if (customer1 != null && customer1.getName().equals("Tuan Tran")) {
                    //If the customer object is not null and the name matches what you are looking for
                    System.out.println("Customer found: " + customer1);
                }
            }
        }


        //There's also a searchObject method that will search for a customer by ID. It accomplishes the same thing as ObjectJson.objectFromJson
        //but it requires you to cast the object to the correct type. It's only there for a couple weird cases where I needed to cast the objects
        // to specific supers or interfaces.

        //You can use it like this:
        rewardsCustomer = (RewardsCustomer) ObjectJson.searchObject("RewardsCustomer_1", RewardsCustomer.class);




    }



}
