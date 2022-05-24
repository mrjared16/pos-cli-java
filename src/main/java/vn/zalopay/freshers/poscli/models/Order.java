package vn.zalopay.freshers.poscli.models;

import vn.zalopay.freshers.poscli.domains.PriceCalculator;

import java.util.Date;
import java.util.List;

public class Order {
    public enum OrderStatus {
        DRAFT,
        WAITING,
        PROCESSING,
        READY
    }

    public PriceCalculator getPriceCalculator() {
        return priceCalculator;
    }

    private static int currentID = 0;
    private final PriceCalculator priceCalculator;
    public String getID() {
        return ID;
    }

    private final String ID;
    private final Date orderedAt;
    private final List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems, PriceCalculator priceCalculator) {
        ID = String.valueOf(currentID++);
        orderedAt = new Date();
        this.orderItems = orderItems;
        this.priceCalculator = priceCalculator;
    }

    public Order(List<OrderItem> orderItems) {
        ID = String.valueOf(currentID++);
        orderedAt = new Date();
        this.orderItems = orderItems;
        this.priceCalculator = new PriceCalculator();
    }

    public int getTotal() {
        return priceCalculator.calculate(this.getOrderItems());
    }

    public OrderReceipt toReceipt() {
        return new OrderReceipt(this);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
