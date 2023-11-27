package Products;

import Retail_Operations.Employee;

import java.util.ArrayList;
import Helpers.ObjectJson;
import java.io.File;

//TODO: Finish writing employee product management menu
public class ProductManagement {
    public static void productManagement(Employee employee){

    }

    public static ArrayList<Product> listAllProducts() {
        File[] files = ObjectJson.listFiles(Product.class);
        ArrayList<Product> products = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                try {
                    Product product = ObjectJson.objectFromJson(file.getName(), Product.class);
                    products.add(product);
                } catch (Exception e) {
                    System.err.println("Error reading JSON file: " + e.getMessage());
                }
            }
        }
        return products;
    }
}
