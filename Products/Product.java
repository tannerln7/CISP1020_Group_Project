package Products;
import Helpers.JsonIdentifiable;

//TODO: Incorporate Offers Class to allow for discounts on products
//TODO: Provide method to updated current products to include discounts

//TODO: Start working on the Management menu for Products.
//BODy: This will be a menu that allows staff members to add, remove, and edit products and their information.
public class Product implements JsonIdentifiable {

    private String name;
    private double price;
    private String description;
    private String id;

    public Product(String name, double price, String description, String id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public String getJsonId() {
        return name + "_" + id;
    }
}