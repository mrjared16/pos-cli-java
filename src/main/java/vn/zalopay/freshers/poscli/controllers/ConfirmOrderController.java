package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.domains.*;
import vn.zalopay.freshers.poscli.models.Order;

public class ConfirmOrderController implements Controller {
    private final OrderBuilder orderBuilder;
    private final Controller predecessor;
    private final OrderManager orderManager;
    private final Printer printer;

    public ConfirmOrderController(Controller predecessor, OrderBuilder orderBuilder, OrderManager orderManager, Printer printer) {
        this.orderBuilder = orderBuilder;
        this.predecessor = predecessor;
        this.orderManager = orderManager;
        this.printer = printer;
    }

    public ConfirmOrderController(Controller predecessor, OrderBuilder orderBuilder, OrderManager orderManager) {
        this.predecessor = predecessor;
        this.orderBuilder = orderBuilder;
        this.orderManager = orderManager;
        this.printer = PrinterFactory.getInstance().getDefaultPrinter();
    }

    public void run() {
        this.reset();
        this.showGreetingMessage();

        Order newOrder = orderBuilder.createOrder();
        this.showReceipt(newOrder);

        // save new order
        this.addOrder(newOrder);

        // back to home screen
        this.predecessor.run();
    }

    private void showReceipt(Order newOrder) {
        this.printOrder(newOrder);
    }

    private void printOrder(Order order) {
        this.printer.send(order.toReceipt());
    }

    private void addOrder(Order order) {
        orderManager.addOrder(order);
        this.showSuccessMessage();
    }

    private void showSuccessMessage() {
        System.out.println("One new order created!");
    }

    @Override
    public void reset() {
        // there is no state
    }

    @Override
    public void showGreetingMessage() {
        System.out.println();
        System.out.println("Order Receipt");
    }
}
