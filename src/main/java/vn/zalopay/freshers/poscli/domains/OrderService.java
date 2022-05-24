package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;
import vn.zalopay.freshers.poscli.shared.Key;

import java.util.LinkedHashMap;
import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    LinkedHashMap<String, List<Order>> getOrdersGroupByStatus();

    Order getOrder(Key id);
}
