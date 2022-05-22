package vn.zalopay.freshers.poscli.models;

public class ToppingItem {
    private final String name;
    private double price;

    public ToppingItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
