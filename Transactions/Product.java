package Transactions;

public class Product {
    private double price;
    private double itemNumber;
    private String name;
    private String description;

    public Product(double price, double itemNumber, String name, String description) {
        this.price = price;
        this.itemNumber = itemNumber;
        this.name = name;
        this.description = description;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(double itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
