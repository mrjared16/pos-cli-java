package vn.zalopay.freshers.poscli.models;

public class MenuItem {
    private final String name;
    private double price;

    public double getPrice() {
        return price;
    }

    public int getMaxTopping() {
        return maxTopping;
    }

    public String getName() {
        return name;
    }

    private int maxTopping;
    private String[] applicableToppings;

    public MenuItem(String name, double price, int maxTopping, String[] applicableToppings) {
        this.name = name;
        this.price = price;
        this.maxTopping = maxTopping;
        this.applicableToppings = applicableToppings;
    }
}
