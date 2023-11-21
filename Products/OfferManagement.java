/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CISP1020_Group_Project.Products;


import Retail_Operations.Employee;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 *
 * @author WJWhit
 */
// unfinished
//TODO: Integrate saved products and employees
//TODO: Connect with main
public class OfferManagement {
    public static void main(String[] args) {
        Employee test = new Employee("test","2324","manager","tester1","p3n!");
        LinkedList<Product> list = new LinkedList<>();
        list.add(new Product("Milk", 2.99, "Whole milk", "P001"));
        list.add(new Product("Eggs", 2.49, "12-count carton of eggs", "P002"));
        list.add(new Product("Bread", 1.99, "Loaf of whole wheat bread", "P003"));
        list.add(new Product("Cereal", 3.49, "Box of breakfast cereal", "P004"));
        ListIterator<Product> iter = list.listIterator();
        Product t3 = new Product("Bread", 1.99, "Loaf of whole wheat bread", "P003");
        Product t4 = new Product("Cereal", 3.49, "Box of breakfast cereal", "P004");
        LinkedList<Offers> list2 = new LinkedList<>();
        list2.add(new Offers(t3,50));
        list2.add(new Offers(t4,60));
        Offers deltest = new Offers(t4, 50);
        Offers edittest = new Offers(t4, 50);
        ListIterator<Offers> iter2 = list2.listIterator();

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
                        
                        
                        System.out.println("type 1 for subtraction discount, type 2 for percent discount");
                        int case2 = in.nextInt();
                        switch(case2){
                            case 1:
                                System.out.println("type subtraction discount");
                                double sub = in.nextDouble();
                                System.out.println("type number for product you wish to modify");
                                int code = in.nextInt();
                                Product t = new Product("Milk", 2.99, "Whole milk", "P001");
                                for(int i = 0;i<code;i++){
                                    t = iter.next();
                                }
                                Offers o1 = new Offers(sub,t);
                                System.out.println("offer created");
                                System.out.println(o1);
                                break;
                            case 2:
                                System.out.println("type percentage discount");
                                double per = in.nextDouble();
                                Product t2 = new Product("Milk", 2.99, "Whole milk", "P001");
                                System.out.println("type number for product you wish to modify");
                                int codes = in.nextInt();
                                for(int i = 0;i<codes;i++){
                                    t2 = iter.next();
                                }
                                Offers o2 = new Offers(per, t2);
                                System.out.println("offer created");
                                break;
                        }
                    break;
                    case 2:
                        System.out.println("type number of offer to be removed");
                        int num = in.nextInt();
                        Offers rem =  new Offers(t4, 50);
                                for(int i = 0;i<num;i++){
                                    rem = iter2.next();
                                }
                        System.out.println(rem);
                        rem = null;
                        System.out.println("offer removed");
                        break;
                    case 3:
                        System.out.println("choose offer to be edited");
                        int num2 = in.nextInt();  
                        Offers edit =  new Offers(t4, 60);
                                for(int i = 0;i<num2;i++){
                                    edit = iter2.next();
                                }
                        if(edit.getsubtractionDiscount()==0){
                             System.out.println("type percentage discount");
                             double per2 = in.nextDouble();
                             edit.changeDiscountPercent(per2);
                             System.out.println(edit);
                             System.out.println("change complete");
                        }
                        else{
                            System.out.println("type subtraction discount");
                            double sub2 = in.nextDouble();
                            edit.setsubtractionDiscount(sub2);
                            System.out.println(edit);
                            System.out.println("change complete");
                        }
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
