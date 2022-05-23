package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;
import vn.zalopay.freshers.poscli.models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder implements Builder {
    private final List<OrderItem> orderItems;
    public OrderBuilder() {
        orderItems = new ArrayList<>();
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Order createOrder() {
        return new Order(this.orderItems);
    }
    public int getCurrentTotal() {
        int total = 0;
        for (OrderItem orderItem: this.orderItems) {
            total += orderItem.getTotal();
        }
        return total;
    }
}
