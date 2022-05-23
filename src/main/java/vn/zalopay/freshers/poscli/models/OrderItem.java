package vn.zalopay.freshers.poscli.models;

import java.util.ArrayList;

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private ArrayList<ToppingItem> toppings;

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }


    public ArrayList<ToppingItem> getToppings() {
        return toppings;
    }

    public OrderItem(MenuItem menuItem, int quantity, ArrayList<ToppingItem> toppings) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.toppings = toppings;
    }
}
