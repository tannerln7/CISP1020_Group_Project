package Products;
import Helpers.JsonIdentifiable;
import Helpers.ObjectJson;
import Helpers.Round;

//TODO: Incorporate Offers Class to allow for discounts on products
//TODO: Provide method to updated current products to include discounts

//TODO: Start working on the Management menu for Products.
//BODy: This will be a menu that allows staff members to add, remove, and edit products and their information.
public class Product implements JsonIdentifiable {

    private String name;
    private double price;
    private double discountPrice = 0;
    private String description;
    private String id;
    private Offers offer;

    public Product(String name, double price, String description, String id) {
        this.name = name;
        this.price = Round.round(price);
        this.description = description;
        this.id = id;
    }

    public Product(String name, double price, String description, String id, Offers offer){
        this.name = name;
        this.price = Round.round(price);
        this.discountPrice = Round.round(price - ((price * (offer.getDiscountPercent() / 100)) + offer.getSubtractionDiscount()));
        this.description = description;
        this.id = id;
        this.offer = offer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        if (discountPrice != 0){
            return discountPrice;
        }else{
            return price;
        }
    }

    public void setPrice(double price) {
        this.price = Round.round(price);
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
    public Offers getOffer(){
        return offer;
    }
    public void setOffer(Offers offer){
        this.offer = offer;
        this.discountPrice = Round.round(price - ((price * (offer.getDiscountPercent() / 100)) + offer.getSubtractionDiscount()));
        updateJson();
    }
    public void removeOffer(){
        this.offer = new Offers(0,0);
        this.discountPrice = 0;
    }
    private void updateJson(){
        ObjectJson.objectToJson(this);
    }

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

    @Override
    public String getJsonId() {
        return name + "_" + id;
    }
}