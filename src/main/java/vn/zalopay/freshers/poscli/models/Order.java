package vn.zalopay.freshers.poscli.models;

import vn.zalopay.freshers.poscli.domains.OrderObserver;
import vn.zalopay.freshers.poscli.domains.OrderReceipt;
import vn.zalopay.freshers.poscli.domains.OrderStatusSubscriber;
import vn.zalopay.freshers.poscli.domains.PriceCalculator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    private Date orderedAt;
    private final List<OrderItem> orderItems;
    private HashMap<String, List<OrderObserver>> observers;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    private OrderStatus orderStatus;
    public Order(List<OrderItem> orderItems, PriceCalculator priceCalculator) {
        ID = String.valueOf(currentID++);
        this.init();
        this.orderItems = orderItems;
        this.priceCalculator = priceCalculator;
    }

    public Order(List<OrderItem> orderItems) {
        ID = String.valueOf(currentID++);
        this.init();
        this.orderItems = orderItems;
        this.priceCalculator = new PriceCalculator();
    }

    private void init() {
        this.orderStatus = OrderStatus.DRAFT;
        this.orderedAt = new Date();
        observers = new HashMap<>();
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

    public void addSubscriber(OrderObserver observer) {
        String event = observer.getEventName();
        if (!this.observers.containsKey(event)) {
            this.observers.put(event, new ArrayList<>());
        }
        this.observers.get(event).add(observer);
    }

    public void nextState() {
        switch (this.orderStatus) {
            case DRAFT:
                this.orderStatus = OrderStatus.WAITING;
                break;
            case WAITING:
                this.orderStatus = OrderStatus.PROCESSING;
                break;
            case PROCESSING:
                this.orderStatus = OrderStatus.READY;
                break;
            case READY:
                return;
        }
        this.notifySubscribers(OrderStatusSubscriber.class.toString());
    }

    public void notifySubscribers(String event) {
        for (OrderObserver observer: this.observers.get(event)) {
            observer.notify(this);
        }
    }
}
