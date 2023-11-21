/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CISP1020_Group_Project.Products;

import Products.Offers;
import Retail_Operations.Employee;
import java.util.Scanner;

/**
 *
 * @author WJWhit
 */
// unfinished
//TODO: Integrate saved products and employees
//TODO: Connect with main
//TODO: figure out how to call a class and mutate it fom a scanner
public class OfferManagement {
    public static void main(String[] args) {
        Employee test = new Employee("test","2324","manager","tester1","p3n!");
        Product t1 = new Product("Milk", 2.99, "Whole milk", "P001");
        Product t2 = new Product("Eggs", 2.49, "12-count carton of eggs", "P002");
        Product t3 = new Product("Bread", 1.99, "Loaf of whole wheat bread", "P003");
        Product t4 = new Product("Cereal", 3.49, "Box of breakfast cereal", "P004");
        
        Offers deltest = new Offers(t4, 50);

        System.out.println("login: ");
        Scanner in = new Scanner(System.in);
        String usename = in.next();
        if(usename.equals(test.getUsername())){
            System.out.println("password: ");
            String pass = in.next();
            if(pass.equals(test.getPassword())){
                System.out.println("welome " + test.getUsername() + " to the offer manegment system");
                System.out.println("what would you like to do?");
                System.out.println("press 1 to add an offer");
                System.out.println("press 2 to delete an offer");
                System.out.println("press 3 to edit an offer");
                int case1 = in.nextInt();
                switch(case1){
                    case 1:
                        System.out.println("to what product would you like to add an offer?");
                        System.out.println("type t code for product you wish to modify");
                        Product code = in.next();
                        System.out.println("type 1 for subtraction discount, type 2 for percent discount");
                        int case2 = in.nextInt();
                        switch(case2){
                            case 1:
                                System.out.println("type subtraction discount");
                                double sub = in.nextDouble();
                                Offers o1 = new Offers(sub,code);
                                System.out.println("offer created");
                                break;
                            case 2:
                                System.out.println("type percentage discount");
                                double per = in.nextDouble();
                                Offers o2 = new Offers(per, code);
                                System.out.println("offer created");
                                break;
                        }
                    break;
                    case 2:
                        System.out.println("name offer to be removed");
                        
                }
            }
            else{
                System.out.println("login failes");
            }
        }
        else{
            System.out.println("login failed");
        }
    }
}
