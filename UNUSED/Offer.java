package UNUSED;


//Leaving this here for now in case code is needed for updating the other Offers Class


import Products.Discount;

public class Offer extends Discount {
    private String discountName;
    private String discountDescription;

    public Offer(String discountName, double discountPercent, String discountDescription) {
        super(discountPercent);
        this.discountName = discountName;
        this.discountDescription = discountDescription;
    }

    public String getDiscountName() {
        return discountName;
    }
    public void changeDiscountName(String newDiscountName) {
        this.discountName = newDiscountName;
    }

    public double getDiscountPercent() {
        return super.getDiscountPercent();
    }
    public void changeDiscountPercent(double newDiscountPercent) {
        super.changeDiscountPercent(newDiscountPercent);
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void changeDiscountDescription(String newDiscountDescription) {
        this.discountDescription = newDiscountDescription;
    }
}
