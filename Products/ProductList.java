package Products;
import Helpers.ObjectJson;

import java.io.File;
import java.util.ArrayList;
public class ProductList {

    public static ArrayList<Product> getGroceryList() {
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product("Milk", 2.99, "Whole milk", "P001"));
        products.add(new Product("Eggs", 2.49, "12-count carton of eggs", "P002"));
        products.add(new Product("Bread", 1.99, "Loaf of whole wheat bread", "P003"));
        products.add(new Product("Cereal", 3.49, "Box of breakfast cereal", "P004"));
        products.add(new Product("Coffee", 4.99, "Ground coffee", "P005"));
        products.add(new Product("Tea", 2.99, "Box of tea bags", "P006"));
        products.add(new Product("Sugar", 1.49, "Granulated sugar", "P007"));
        products.add(new Product("Salt", 0.99, "Iodized salt", "P008"));
        products.add(new Product("Apples", 1.99, "Fresh, juicy apples", "P009"));
        products.add(new Product("Bananas", 0.99, "Sweet and ripe bananas", "P010"));
        products.add(new Product("Oranges", 2.49, "California oranges", "P011"));
        products.add(new Product("Grapes", 3.99, "Seedless red grapes", "P012"));
        products.add(new Product("Paper towels", 2.99, "Roll of paper towels", "P013"));
        products.add(new Product("Toilet paper", 3.99, "12-pack of toilet paper", "P014"));
        products.add(new Product("Laundry detergent", 4.99, "Bottle of laundry detergent", "P015"));
        products.add(new Product("Dish soap", 2.99, "Bottle of dish soap", "P016"));

        return products;
    }

    /**
     * Demonstrates the use of the ObjectJson helper class to write and read
     * objects to and from JSON files
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Product> products = getGroceryList();

        //write the product objects to JSON files
        for (Product product : products) {
            ObjectJson.objectToJson(product);
        }

        //Read the product objects from JSON files

        // Use the helper method to get an array of files in the directory associated with the Customer class
        File[] files = ObjectJson.listFiles(Product.class);

        // Check if the array of files is not null (i.e., the directory exists, is a directory, and is not empty)
        if (files != null) {
            // Iterate over each file in the array
            for (File file : files) {
                try {
                    //Create a product object from the JSON file and print it to the console
                    Product product = ObjectJson.objectFromJson(file.getName(), Product.class);
                    System.out.println(product);
                } catch (Exception e) {
                    // Handle exceptions during the reading and deserialization process
                    System.err.println("Error reading JSON file: " + e.getMessage());
                }
            }
        }
    }
}