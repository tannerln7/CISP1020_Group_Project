/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Products;


import Helpers.Cls;
import Helpers.ObjectJson;
import Main.Main;
import Retail_Operations.Employee;
import Transactions.TransactionManagement;

import java.util.Scanner;

import static Main.Main.execute;

/**
 *
 * @author WJWhit
 */

//TODO: Bug-test offer management.

//TODO: Add user menu to exchange set amount of points for an offer.

public class OfferManagement {
    public static void offerManagement(Employee loggedInEmployee) {
        //Load employee files
        Cls.cls();
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome " + loggedInEmployee.getUsername() + " to the offer management system");
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to add an offer");
        System.out.println("Press 2 to delete an offer");
        System.out.println("Press 3 to edit an offer");
        System.out.println("Press 4 to return to the employee menu");
        int case1 = in.nextInt();
        in.nextLine();
        switch (case1) {
            case 1:
                Cls.cls();
                System.out.println("Enter 1 for a subtraction discount");
                System.out.println("Enter 2 for a percent discount");
                System.out.println("Enter 3 for both");
                System.out.println("Enter 4 to return to the offer management menu");
                int case2 = in.nextInt();
                in.nextLine();
                switch (case2) {
                    case 1:
                        Cls.cls();
                        Product t = getProduct(loggedInEmployee, in);
                        System.out.println("Enter the subtraction discount");
                        double sub = in.nextDouble();
                        in.nextLine();
                        //in.nextDouble leaves a hanging return character for some reason. This causes the next in.nextLine() to be skipped.
                        //added a blank in.nextLine() to consume the hanging return character.
                        //Create new offer with 0% discount and subtractionDiscount "sub".
                        Offers o1 = new Offers(0, sub);
                        //Uses the setOffer method in Product class to apply the offer to the product object and update the Json file.
                        t.setOffer(o1);
                        ObjectJson.objectToJson(t);
                        Cls.cls();
                        System.out.println("offer created\n");
                        System.out.println(t);
                        System.out.println("Press enter to return to the offer management menu");
                        in.nextLine();
                        execute(() -> offerManagement(loggedInEmployee));
                        break;
                    case 2:
                        Cls.cls();
                        Product t2 = getProduct(loggedInEmployee, in);
                        System.out.println("Enter the percentage discount");
                        double per = in.nextDouble();
                        in.nextLine();
                        //in.nextDouble leaves a hanging return character for some reason. This causes the next in.nextLine() to be skipped.
                        //added a blank in.nextLine() to consume the hanging return character.
                        //Create new offer with percentDiscount "per" and 0 subtractionDiscount.
                        Offers o2 = new Offers(per, 0);
                        //Uses the setOffer method in Product class to apply the offer to the product object and update the Json file.
                        t2.setOffer(o2);
                        ObjectJson.objectToJson(t2);
                        Cls.cls();
                        System.out.println("offer created\n");
                        System.out.println(t2);
                        System.out.println("Press enter to return to the offer management menu");
                        in.nextLine();
                        execute(() -> offerManagement(loggedInEmployee));
                        break;
                    case 3:
                        Cls.cls();
                        Product t1 = getProduct(loggedInEmployee, in);
                        System.out.println("Enter the percentage discount");
                        double per1 = in.nextDouble();
                        //in.nextDouble leaves a hanging return character for some reason. This causes the next in.nextLine() to be skipped.
                        //added a blank in.nextLine() to consume the hanging return character.
                        in.nextLine();
                        System.out.println("Enter the subtraction discount");
                        double sub1 = in.nextDouble();
                        in.nextLine();
                        //in.nextDouble leaves a hanging return character for some reason. This causes the next in.nextLine() to be skipped.
                        //added a blank in.nextLine() to consume the hanging return character.
                        //Create new offer with percentDiscount "per1" and subtractionDiscount "sub1".
                        Offers o3 = new Offers(per1, sub1);
                        //Uses the setOffer method in Product class to apply the offer to the product object and update the Json file.
                        t1.setOffer(o3);
                        ObjectJson.objectToJson(t1);
                        Cls.cls();
                        System.out.println("offer created\n");
                        System.out.println(t1);
                        System.out.println("Press enter to return to the offer management menu");
                        in.nextLine();
                        execute(() -> offerManagement(loggedInEmployee));
                        break;
                    case 4:
                        execute(() -> offerManagement(loggedInEmployee));
                        break;
                }
                break;
            case 2:
                Cls.cls();
                Product t3 = getProduct(loggedInEmployee, in);
                t3.removeOffer();
                Cls.cls();
                System.out.println("Offer removed.\n");
                System.out.println(t3);
                System.out.println("Press enter to return to the offer management menu");
                in.nextLine();
                execute(() -> offerManagement(loggedInEmployee));
                break;
            case 3:
                Product t4 = getProduct(loggedInEmployee, in);
                Cls.cls();
                System.out.println("Type percentage discount. Type 0 for no percentage discount.");
                double per2 = in.nextDouble();
                in.nextLine();
                System.out.println("Type a subtraction discount. Type 0 for no subtraction discount.");
                double sub2 = in.nextDouble();
                in.nextLine();
                Offers newOffer = new Offers(per2, sub2);
                if (t4 != null) {
                    if (t4.getOffer() != null) {
                        Cls.cls();
                        System.out.println("Current offer on product:");
                        System.out.println(t4.getOffer());
                        System.out.println("----------------------------------");
                        System.out.println();
                        System.out.println("Would you like to change this to:");
                        System.out.println(newOffer);
                        System.out.println("Type yes to confirm.");
                        if (in.nextLine().equalsIgnoreCase("yes")) {
                            t4.setOffer(newOffer);
                            ObjectJson.objectToJson(t4);
                            Cls.cls();
                            System.out.println("Offer updated\n");
                        } else {
                            Cls.cls();
                            System.out.println("Update Canceled\n");
                        }
                        System.out.println(t4);
                        System.out.println("Press enter to return to the offer management menu");
                        in.nextLine();
                        execute(() -> offerManagement(loggedInEmployee));
                        break;
                    } else {
                        Cls.cls();
                        t4.setOffer(newOffer);
                        ObjectJson.objectToJson(t4);
                        System.out.println("Offer Updated.\n");
                        System.out.println(t4);
                        System.out.println("Press enter to return to the offer management menu");
                        in.nextLine();
                        execute(() -> offerManagement(loggedInEmployee));
                        break;
                    }
                }
                break;
            case 4:
                Cls.cls();
                execute(() -> Main.employeeMenu(loggedInEmployee));
                break;
        }

    }
    private static Product getProduct(Employee loggedInEmployee, Scanner in) {
        Product t = null;
        Cls.cls();
        do {
            System.out.println("Type the name of the product you wish to modify. (eg. Apples). Type -L to list all products.");
            String name = in.nextLine();
            if (name.equalsIgnoreCase("-L")) {
                Cls.cls();
                ProductManagement.listAllProducts().forEach(System.out::println);
                System.out.println("\n");
            } else {
                t = TransactionManagement.findProduct(name, loggedInEmployee);
                if (t == null) {
                    System.out.println("Product not found. Please try again.");
                    System.out.println("Press enter to continue.");
                    in.nextLine();
                    Cls.cls();
                }
            }
        } while (t == null);
        return t;
    }
}

