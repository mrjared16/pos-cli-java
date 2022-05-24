package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.controllers.handlers.*;
import vn.zalopay.freshers.poscli.domains.OrderService;
import vn.zalopay.freshers.poscli.models.Order;
import vn.zalopay.freshers.poscli.shared.*;

public class OrderProcessingController extends InputHandler implements Controller, CommandHandler, Validator {
    private final OrderService orderService;
    private final Controller predecessor;

    public OrderProcessingController(Controller predecessor, OrderService orderService) {
        this.predecessor = predecessor;
        this.orderService = orderService;
    }

    public void run() {
        this.reset();
        this.showInputPrompt();
        IntInput input = new IntInput(this, this);
        Order order = this.getOrder(input);
        if (order == null) {
            this.showOrderNotFoundMessage();
        } else {
            // process order
            OrderProcessingConfirmationHandler confirmationHandler = new OrderProcessingConfirmationHandler();
            confirmationHandler.handle(order);
        }
        // back to manage screen
        this.predecessor.run();
    }

    private void showOrderNotFoundMessage() {
        System.out.println("Cannot find the order has that ID. Please try another ID!");
    }

    private Order getOrder(IntInput input) {
        String id = Integer.toString(input.getValue());
        return this.orderService.getOrder(new StringKey(id));
    }

    @Override
    public void reset() {
        // there is no state
    }

    @Override
    public void showGreetingMessage() {
        // no necessary
    }

    @Override
    public void execute() {
        this.showOrderNotFoundMessage();
    }

    @Override
    public boolean valid(Input input) {
        return true;
    }

    @Override
    protected void showInputPrompt() {
        System.out.print("Enter the Order ID: ");
    }
}
