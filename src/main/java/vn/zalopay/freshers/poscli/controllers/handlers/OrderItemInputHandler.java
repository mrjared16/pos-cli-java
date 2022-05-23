package vn.zalopay.freshers.poscli.controllers.handlers;
import vn.zalopay.freshers.poscli.domains.OrderItemBuilder;

public abstract class OrderItemInputHandler extends InputHandler {
    public abstract void handle(OrderItemBuilder builder);
}
