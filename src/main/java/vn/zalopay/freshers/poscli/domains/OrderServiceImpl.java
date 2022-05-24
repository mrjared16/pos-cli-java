package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;

public class OrderServiceImpl implements OrderService {
    private static OrderService instance = null;
    private final OrderRepository repository;
    private OrderServiceImpl() {
        this.repository = new InMemoryOrderRepository();
    }
    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    public void addOrder(Order order) {
        this.repository.add(order);
    }
}
