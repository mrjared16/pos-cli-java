package vn.zalopay.freshers.poscli.models;

import vn.zalopay.freshers.poscli.domains.OrderObserver;
import vn.zalopay.freshers.poscli.domains.OrderReceipt;
import vn.zalopay.freshers.poscli.domains.OrderStatusSubscriber;
import vn.zalopay.freshers.poscli.domains.PriceCalculator;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Order {
    public enum OrderStatus {
        WAITING,
        PROCESSING,
        READY,
        COMPLETED;

        public static List<String> getNames() {
            return Arrays.stream(values()).map(Enum::name)
                    .collect(Collectors.toList());
        }

        public OrderStatus getNext() {
            List<OrderStatus> enumValues = Arrays.asList(values());
            int index = enumValues.indexOf(this);
            if (index + 1 >= enumValues.size()) {
                return null;
            }
            return enumValues.get(index + 1);
        }
    }

    public PriceCalculator getPriceCalculator() {
        return priceCalculator;
    }

    private static int currentID = 0;
    private final PriceCalculator priceCalculator;
    public String getId() {
        return id;
    }

    private final String id;

    public Date getOrderedAt() {
        return orderedAt;
    }

    private Date orderedAt;
    private final List<OrderItem> orderItems;
    private HashMap<String, List<OrderObserver>> observers;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    private OrderStatus orderStatus;
    public Order(List<OrderItem> orderItems, PriceCalculator priceCalculator) {
        id = String.valueOf(currentID++);
        this.init();
        this.orderItems = orderItems;
        this.priceCalculator = priceCalculator;
    }

    public Order(List<OrderItem> orderItems) {
        id = String.valueOf(currentID++);
        this.init();
        this.orderItems = orderItems;
        this.priceCalculator = new PriceCalculator();
    }

    private void init() {
        this.orderStatus = OrderStatus.WAITING;
        this.orderedAt = new Date();
        observers = new HashMap<>();
    }

    public int getTotal() {
        return priceCalculator.calculate(this.getOrderItems());
    }

    public OrderReceipt toReceipt() {
        return new OrderReceipt(this);
    }
    public String toString() {
        return this.toReceipt().toString();
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
            case WAITING:
                this.orderStatus = OrderStatus.PROCESSING;
                break;
            case PROCESSING:
                this.orderStatus = OrderStatus.READY;
                break;
            case READY:
                this.orderStatus = OrderStatus.COMPLETED;
                break;
            case COMPLETED:
                return;
        }
        this.notifySubscribers(OrderStatusSubscriber.class.toString());
    }

    public boolean canProcess() {
        return this.orderStatus.getNext() != null;
    }

    public void notifySubscribers(String event) {
        for (OrderObserver observer: this.observers.get(event)) {
            observer.notify(this);
        }
    }
}
