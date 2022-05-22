package vn.zalopay.freshers.poscli.models;

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
    private OrderItem[] orderItems;

    public Order() {
        ID = String.valueOf(currentID++);
        orderedAt = new Date();
    }
}
