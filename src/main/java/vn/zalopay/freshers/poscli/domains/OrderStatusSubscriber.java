package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;

public class OrderStatusSubscriber implements OrderObserver{
    private final Sender sender;

    public OrderStatusSubscriber(Sender sender) {
        this.sender = sender;
    }

    @Override
    public void notify(Order order) {
        Message message = new OrderStatusMessage(order);
        sender.send(message);
    }

    @Override
    public String getEventName() {
        return this.getClass().toString();
    }
}
