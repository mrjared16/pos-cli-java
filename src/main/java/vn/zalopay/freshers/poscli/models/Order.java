package vn.zalopay.freshers.poscli.models;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    public enum OrderStatus {
        DRAFT,
        WAITING,
        PROCESSING,
        READY
    }

    private static int currentID = 0;

    private final String ID;
    private Date orderedAt;
    private ArrayList<OrderItem> orderItems;

    public Order(ArrayList<OrderItem> orderItems) {
        ID = String.valueOf(currentID++);
        orderedAt = new Date();
        this.orderItems = orderItems;
    }
}
