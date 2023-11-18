package Customers;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 *
 * @author beode
 */
public class CustomerDemo {

    public static void main(String[] args) {
        Customer customer = new Customer();
        Customer c1 = new Customer("Tuan Tran", "432-567-8900");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("***REMOVED*** ***REMOVED***", "123-456-7890"));
        customerList.add(new Customer("William Whitmire", "987-654-3210"));
        customerList.add(new Customer("Donnie Young", "555-123-4567"));
        customerList.add(new Customer("R Elijah Brewer", "111-222-3333"));

        System.out.println(c1);
        c1.changeName("Tuan Trang Tran");
        c1.changePhoneNumber("321-654-0978");
        customer.writeCustomerToFile(customerList);

        c1.writeCustomerToFile();

    }
}
