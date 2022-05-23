package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.models.MenuItem;
import vn.zalopay.freshers.poscli.models.OrderItem;
import vn.zalopay.freshers.poscli.models.ToppingItem;

import java.util.ArrayList;

public class OrderItemBuilder implements Builder {
    private MenuItem menuItem;
    private int quantity;

    public MenuItem getMenuItem() {
        return menuItem;
    }

    private ArrayList<ToppingItem> toppings = new ArrayList<>();
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setToppings(ArrayList<ToppingItem> toppings) {
        this.toppings = toppings;
    }

    public OrderItem createOrderItem() {
        return new OrderItem(menuItem, quantity, toppings);
    }
}
