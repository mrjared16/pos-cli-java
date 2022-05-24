package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;
import vn.zalopay.freshers.poscli.shared.Key;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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

    @Override
    public LinkedHashMap<String, List<Order>> getOrdersGroupByStatus() {
        List<Order> allOrders = this.repository.getAll();
        LinkedHashMap<String, List<Order>> result = new LinkedHashMap<>();
        for (String status: Order.OrderStatus.getNames()) {
            result.put(status, new ArrayList<>());
        }
        for (Order order: allOrders) {
            if (!result.containsKey(order.getOrderStatus().name())) {
                continue;
            }
            result.get(order.getOrderStatus().name()).add(order);
        }
        return result;
    }

    @Override
    public Order getOrder(Key id) {
        return this.repository.get(id);
    }
}
