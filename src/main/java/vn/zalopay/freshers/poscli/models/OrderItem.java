package vn.zalopay.freshers.poscli.models;

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private ToppingItem[] toppings;

    public OrderItem(MenuItem menuItem, int quantity, ToppingItem[] toppings) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.toppings = toppings;
    }
}
