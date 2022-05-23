package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.MenuItem;
import vn.zalopay.freshers.poscli.models.OrderItem;
import vn.zalopay.freshers.poscli.models.ToppingItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemBuilder implements Builder {
    private MenuItem menuItem;
    private int quantity;

    public MenuItem getMenuItem() {
        return menuItem;
    }

    private List<ToppingItem> toppings = new ArrayList<>();
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setToppings(List<ToppingItem> toppings) {
        this.toppings = toppings;
    }

    public OrderItem createOrderItem() {
        return new OrderItem(menuItem, quantity, toppings);
    }
}
