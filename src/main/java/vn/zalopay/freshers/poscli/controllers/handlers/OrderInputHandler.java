package vn.zalopay.freshers.poscli.controllers.handlers;
import vn.zalopay.freshers.poscli.domains.OrderBuilder;

public abstract class OrderInputHandler extends InputHandler {
    public abstract void handle(OrderBuilder builder);
}
