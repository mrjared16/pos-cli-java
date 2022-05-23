package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.models.Order;
import vn.zalopay.freshers.poscli.models.OrderItem;

import java.util.ArrayList;

public class OrderBuilder implements Builder {
    private final ArrayList<OrderItem> orderItems;
    public OrderBuilder() {
        orderItems = new ArrayList<>();
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Order createOrder() {
        return new Order(this.orderItems);
    }

}
