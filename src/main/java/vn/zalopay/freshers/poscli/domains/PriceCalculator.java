package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.OrderItem;

import java.util.List;

public class PriceCalculator {
    public int calculate(List<OrderItem> orderItems) {
        int total = 0;
        for (OrderItem orderItem: orderItems) {
            total += orderItem.getTotal();
        }
        return total;
    }
}
