package vn.zalopay.freshers.poscli.models;

import java.util.List;

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private List<ToppingItem> toppings;

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }


    public List<ToppingItem> getToppings() {
        return toppings;
    }

    public OrderItem(MenuItem menuItem, int quantity, List<ToppingItem> toppings) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.toppings = toppings;
    }

    public int getTotal() {
        int totalTopping = 0;
        for (ToppingItem toppingItem: this.toppings) {
            totalTopping += toppingItem.getPrice();
        }
        return (int) (this.menuItem.getPrice() * this.quantity + totalTopping);
    }
}
