package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;

public class OrderManager {
    private static OrderManager instance = null;
    private final OrderRepository repository;
    private OrderManager() {
        this.repository = new InMemoryOrderRepository();
    }
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrder(Order order) {
        this.repository.add(order);
    }
}
