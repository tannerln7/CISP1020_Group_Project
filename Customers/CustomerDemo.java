package Customers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Helpers.ObjectJson;

public class CustomerDemo {

    public static void main(String[] args) {
        //Generate a list of customers
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("Tuan Tran", "432-567-8900"));
        customerList.add(new Customer("***REMOVED*** ***REMOVED***", "123-456-7890"));
        customerList.add(new Customer("William Whitmire", "987-654-3210"));
        customerList.add(new Customer("Donnie Young", "555-123-4567"));
        customerList.add(new Customer("R Elijah Brewer", "111-222-3333"));
        customerList.add(new RewardsCustomer("Tuan Tran", "432-567-8900", "Email@email.com"));

        //print out the list of customers
        for (Customer customer : customerList) {
            System.out.println(customer);
        }

        //Write the customer objects to JSON files
        for (Customer customer : customerList) {
            ObjectJson.objectToJson(customer);
        }

        //Read the customer objects from JSON files

        // Use the helper method to get an array of files in the directory associated with the Customer class
        File[] files = ObjectJson.listFiles(Customer.class);

        // Check if the array of files is not null (i.e., the directory exists, is a directory, and is not empty)
        if (files != null) {
            // Iterate over each file in the array
            for (File file : files) {
                try {
                    // Check file name to determine if the file is a Customer object or a RewardsCustomer object
                    if (file.getName().contains("RewardsCustomer")) {
                        // If the file name contains "RewardsCustomer", deserialize the file to a RewardsCustomer object
                        RewardsCustomer rewardsCustomer = ObjectJson.objectFromJson(file.getName(), RewardsCustomer.class);
                        // Print the RewardsCustomer object to the console
                        System.out.println(rewardsCustomer);
                    } else {
                        Customer customer = ObjectJson.objectFromJson(file.getName(), Customer.class);
                        System.out.println(customer);
                    }
                } catch (Exception e) {
                    // Handle exceptions during the reading and deserialization process
                    System.err.println("Error reading JSON file: " + e.getMessage());
                }
            }
        }
    }
}
