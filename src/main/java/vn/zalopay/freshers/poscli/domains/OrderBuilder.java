package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;
import vn.zalopay.freshers.poscli.models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder implements Builder {
    private final List<OrderItem> orderItems;
    private final PriceCalculator priceCalculator;
    public OrderBuilder() {
        orderItems = new ArrayList<>();
        priceCalculator = new PriceCalculator();
    }

    public OrderBuilder(PriceCalculator priceCalculator) {
        orderItems = new ArrayList<>();
        this.priceCalculator = priceCalculator;
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
    public int getTotal() {
        return priceCalculator.calculate(orderItems);
    }

    public OrderReceipt toReceipt() {
        return new OrderReceipt(this.orderItems, this.priceCalculator);
    }

    public String toString() {
        return this.toReceipt().toString();
    }
}
