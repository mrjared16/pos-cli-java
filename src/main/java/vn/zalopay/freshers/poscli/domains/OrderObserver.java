package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;

public interface OrderObserver extends Observer {
    void notify(Order order);
    String getEventName();
}
