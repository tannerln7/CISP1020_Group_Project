package Products;
import java.util.ArrayList;

//TODO: Refactor to write and read "Test" products to and from JSON files
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
}