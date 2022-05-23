package vn.zalopay.freshers.poscli.models;

import java.util.List;

public class MenuItem {
    private final String name;
    private double price;

    public List<ToppingItem> getApplicableToppings() {
        return applicableToppings;
    }

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
    private List<ToppingItem> applicableToppings;

    public MenuItem(String name, double price, int maxTopping, List<ToppingItem> applicableToppings) {
        this.name = name;
        this.price = price;
        this.maxTopping = maxTopping;
        this.applicableToppings = applicableToppings;
    }
}
