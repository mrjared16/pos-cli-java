package vn.zalopay.freshers.poscli.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        ID = String.valueOf(currentID++);
        orderedAt = new Date();
        this.orderItems = orderItems;
    }

    public int getTotal() {
        int total = 0;
        for (OrderItem orderItem: orderItems) {
            total += orderItem.getTotal();
        }
        return total;
    }
}
