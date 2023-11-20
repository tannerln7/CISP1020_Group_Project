package Products;

//TODO: Incorporate this class as a parent class to the Offers Class
public class Discount {
    private double discountPercent;
    private double discountAmount;

    //Super class for Loyalty and Offer classes
    public Discount(){
    }
    public Discount(double discountPercent) {
        this.discountPercent = discountPercent;
    }
    public Discount(double discountPercent, double discountAmount) {
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
    public double getDiscountAmount() {
        return discountAmount;
    }
    public void changeDiscountPercent(double newDiscountPercent) {
        this.discountPercent = newDiscountPercent;
    }

}
