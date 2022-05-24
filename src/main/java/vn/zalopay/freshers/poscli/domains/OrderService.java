package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;

import java.util.LinkedHashMap;
import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    LinkedHashMap<String, List<Order>> getOrdersGroupByStatus();
}
