package Products;

//TODO: Incorporate this class as a parent class to the Offers Class
public class Discount {
    private double discountPercent;

    //Super class for Loyalty and Offer classes
    public Discount(){

    }
    public Discount(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
    public void changeDiscountPercent(double newDiscountPercent) {
        this.discountPercent = newDiscountPercent;
    }

}
