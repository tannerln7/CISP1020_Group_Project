package Products;
import Helpers.JsonIdentifiable;
import Helpers.Round;

/**
 * The Product class represents a product in a retail system.
 * It implements the JsonIdentifiable interface.
 */
public class Product implements JsonIdentifiable {

    private String name;
    private double price;
    private double discountPrice = 0;
    private String description;
    private String id;
    private Offers offer;

    /**
     * Constructor that creates a Product object with the specified name, price, description, and ID.
     *
     * @param name The name of the product.
     * @param price The price of the product.
     * @param description The description of the product.
     * @param id The ID of the product.
     */
    public Product(String name, double price, String description, String id) {
        this.name = name;
        this.price = Round.round(price);
        this.description = description;
        this.id = id;
    }

    /**
     * Constructor that creates a Product object with the specified name, price, description, ID, and offer.
     *
     * @param name The name of the product.
     * @param price The price of the product.
     * @param description The description of the product.
     * @param id The ID of the product.
     * @param offer The offer applied to the product.
     */
    public Product(String name, double price, String description, String id, Offers offer){
        this.name = name;
        this.price = Round.round(price);
        this.discountPrice = Round.round(price - ((price * (offer.getDiscountPercent() / 100)) + offer.getSubtractionDiscount()));
        this.description = description;
        this.id = id;
        this.offer = offer;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The new name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the price of the product. If a discount is applied, it retrieves the discounted price.
     *
     * @return The price or discounted price of the product.
     */
    public double getPrice() {
        if (discountPrice != 0){
            return discountPrice;
        }else{
            return price;
        }
    }

    /**
     * Sets the price of the product.
     *
     * @param price The new price of the product.
     */
    public void setPrice(double price) {
        this.price = Round.round(price);
    }

    /**
     * Retrieves the description of the product.
     *
     * @return The description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description The new description of the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the ID of the product.
     *
     * @return The ID of the product.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id The new ID of the product.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves the offer applied to the product.
     *
     * @return The offer applied to the product.
     */
    public Offers getOffer(){
        return offer;
    }

    /**
     * Sets the offer applied to the product and updates the discounted price.
     *
     * @param offer The new offer to be applied to the product.
     */
    public void setOffer(Offers offer){
        this.offer = offer;
        this.discountPrice = Round.round(price - ((price * (offer.getDiscountPercent() / 100)) + offer.getSubtractionDiscount()));
    }

    /**
     * Removes the offer applied to the product and resets the discounted price.
     */
    public void removeOffer(){
        this.offer = new Offers(0,0);
        this.discountPrice = 0;
    }

    /**
     * Overrides the default `toString()` method to provide a human-readable representation of the product.
     *
     * @return A string representation of the product, including the name, price, discount price (if any), offer (if any), description, and ID.
     */
    @Override
    public String toString() {
        if (discountPrice != 0) {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", Normal Price=" + price + '\'' +
                    ", Discount Price=" + discountPrice + '\'' +
                    ", offer='" + offer + '\'' +
                    ", description='" + description + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }else{
            return "Product{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    ", description='" + description + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    /**
     * Overrides the `getJsonId()` method to generate a JSON-compatible ID for the product.
     *
     * @return The JSON-compatible ID for the product, which is the name and ID of the product separated by an underscore.
     */
    @Override
    public String getJsonId() {
        return name + "_" + id;
    }
}