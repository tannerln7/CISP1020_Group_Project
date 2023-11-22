/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Products;


import Helpers.ObjectJson;
import Retail_Operations.Employee;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author WJWhit
 */
// unfinished
//TODO: Connect with main


//Updated to store offer objects in product objects instead of vice versa.
//Updated to check usernames and passwords from employee files

public class OfferManagement {
    public static void main(String[] args) {
        //Load employee files
        File[] employeeFiles = ObjectJson.listFiles(Employee.class);
        ArrayList<Employee> employees = new ArrayList<>();
        Employee loggedInEmployee = new Employee("", "", "", "", "");
        if (employeeFiles != null) {
            for (File file : employeeFiles) {
                Employee employee = ObjectJson.objectFromJson(file.getName(), Employee.class);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        }
        Scanner in = new Scanner(System.in);
        boolean validLogin = false;
        while(!validLogin) {
            System.out.println("login: ");
            String username = in.next();
            System.out.println("password: ");
            String pass = in.next();
            for (Employee employee : employees) {
                if (employee.getUsername().equals(username) && employee.getPassword().equals(pass)) {
                    validLogin = true;
                    loggedInEmployee = employee;
                }
            }
            System.out.println("Invalid Login");
        }
        boolean repeat = true;
        while (repeat) {
            System.out.println("Welcome " + loggedInEmployee.getUsername() + " to the offer management system");
            System.out.println("What would you like to do?");
            System.out.println("Press 1 to add an offer");
            System.out.println("Press 2 to delete an offer");
            System.out.println("Press 3 to edit an offer");
            int case1 = in.nextInt();
            switch (case1) {
                case 1:
                    do {
                        System.out.println("To which product would you like to add an offer?");
                        System.out.println("Type 1 for subtraction discount, type 2 for percent discount");
                        int case2 = in.nextInt();
                        in.nextLine();
                        switch (case2) {
                            case 1:
                                System.out.println("Enter the subtraction discount");
                                double sub = in.nextDouble();
                                //in.nextDouble leaves a hanging return character for some reason. This causes the next in.nextLine() to be skipped.
                                //added a blank in.nextLine() to consume the hanging return character.
                                in.nextLine();
                                System.out.println("type ID for product you wish to modify. (eg. Apples_P009)");
                                String id = in.nextLine();
                                //Uses ObjectJson.searchObject to find the json file and load the product object.
                                Product t = (Product) ObjectJson.searchObject(id, Product.class);
                                //If the product was found
                                if (t != null) {
                                    //Create new offer with 0% discount and subtractionDiscount "sub".
                                    Offers o1 = new Offers(sub, 0);
                                    //Uses the setOffer method in Product class to apply the offer to the product object and update the Json file.
                                    t.setOffer(o1);
                                    System.out.println("offer created");
                                    System.out.println(t);
                                    repeat = false;
                                } else {
                                    System.out.println("The product was not found. Please try again.");
                                }
                                break;
                            case 2:
                                System.out.println("type percentage discount");
                                double per = in.nextDouble();
                                //in.nextDouble leaves a hanging return character for some reason. This causes the next in.nextLine() to be skipped.
                                //added a blank in.nextLine() to consume the hanging return character.
                                in.nextLine();
                                System.out.println("type ID for product you wish to modify. (eg. Apples_P009)");
                                String id2 = in.nextLine();
                                //Uses ObjectJson.searchObject to find the json file and load the product object.
                                Product t2 = (Product) ObjectJson.searchObject(id2, Product.class);
                                //Create new offer with percentDiscount "per" and 0 subtractionDiscount.
                                Offers o2 = new Offers(per, 0);
                                //Uses the setOffer method in Product class to apply the offer to the product object and update the Json file.
                                if (t2 != null) {
                                    t2.setOffer(o2);
                                    System.out.println("offer created");
                                    System.out.println(t2);
                                    repeat = false;
                                } else {
                                    System.out.println("The product was not found. Please try again.");
                                }
                                break;
                        }
                    } while (repeat);
                    break;
                case 2:
                    System.out.println("type ID for product you wish to modify. (eg. Apples_P009)");
                    in.nextLine();
                    String id3 = in.nextLine();
                    Product t3 = (Product) ObjectJson.searchObject(id3, Product.class);
                    if (t3 != null) {
                        t3.removeOffer();
                        System.out.println("Offer removed.");
                        System.out.println(t3);
                        repeat = false;
                    }else{
                        System.out.println("Product not found please try again.");
                    }
                    break;
                case 3:
                    System.out.println("type ID for product you wish to modify. (eg. Apples_P009)");
                    in.nextLine();
                    String id4 = in.nextLine();
                    Product t4 = (Product) ObjectJson.searchObject(id4, Product.class);
                    System.out.println("Type percentage discount. Type 0 for no percentage discount.");
                    double per2 = in.nextDouble();
                    in.nextLine();
                    System.out.println("Type a subtraction discount. Type 0 for no subtraction discount.");
                    double sub2 = in.nextDouble();
                    in.nextLine();
                    Offers newOffer = new Offers(per2, sub2);
                    if (t4 != null) {
                        if (t4.getOffer() != null) {
                            System.out.println("Current offer on product:");
                            System.out.println(t4.getOffer());
                            System.out.println("----------------------------------");
                            System.out.println();
                            System.out.println("Would you like to change this to:");
                            System.out.println(newOffer);
                            System.out.println("Type yes to confirm.");
                            if (in.nextLine().equalsIgnoreCase("yes")) {
                                t4.setOffer(newOffer);
                                System.out.println("Offer updated");
                                System.out.println(t4);
                                repeat = false;
                            } else {
                                System.out.println("Update Canceled");
                            }
                        } else {
                            t4.setOffer(newOffer);
                            System.out.println(t4.getOffer());
                            System.out.println("Offer Updated.");
                            System.out.println(t4);
                            repeat = false;
                        }
                    }
            }

        }
    }
}

