package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.controllers.handlers.ItemChoosingProcessor;
import vn.zalopay.freshers.poscli.controllers.handlers.QuantityProcessor;
import vn.zalopay.freshers.poscli.controllers.handlers.ToppingsChoosingProcessor;
import vn.zalopay.freshers.poscli.domains.OrderBuilder;
import vn.zalopay.freshers.poscli.domains.OrderItemBuilder;
import vn.zalopay.freshers.poscli.models.OrderItem;

public class AddItemToOrderController implements Controller {
    private OrderItemBuilder orderItemBuilder;
    private final OrderBuilder orderBuilder;
    private final Controller predecessor;

    public AddItemToOrderController(Controller predecessor, OrderBuilder orderBuilder) {
        this.predecessor = predecessor;
        this.orderBuilder = orderBuilder;
    }

    public void run() {
        this.reset();
        ItemChoosingProcessor itemChoosingProcessor = new ItemChoosingProcessor();
        QuantityProcessor quantityProcessor = new QuantityProcessor();
        ToppingsChoosingProcessor toppingsChoosingProcessor = new ToppingsChoosingProcessor();

        itemChoosingProcessor.setNext(quantityProcessor);
        quantityProcessor.setNext(toppingsChoosingProcessor);

        itemChoosingProcessor.handle(orderItemBuilder);

        // create order item
        OrderItem newOrderItem = orderItemBuilder.createOrderItem();
        this.addOrderItem(newOrderItem);

        // back to create order screen
        this.predecessor.run();
    }

    private void addOrderItem(OrderItem orderItem) {
        orderBuilder.addOrderItem(orderItem);
        this.showSuccessMessage();
    }

    private void showSuccessMessage() {
        System.out.println("Added " + orderItemBuilder.getMenuItem().getName() + " to order!");
    }

    @Override
    public void reset() {
        this.orderItemBuilder = new OrderItemBuilder();
    }

    @Override
    public void showGreetingMessage() {
        System.out.println();
        System.out.println("Adding item to order...");
    }
}
