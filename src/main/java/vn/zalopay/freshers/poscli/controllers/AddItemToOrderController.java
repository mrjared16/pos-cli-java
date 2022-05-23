package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.controllers.handlers.ItemChoosingProcessor;

public class AddItemToOrderController implements Controller {
    private OrderItemBuilder orderItemBuilder;
    private final OrderBuilder orderBuilder;
    private final Controller predecessor;

    public AddItemToOrderController(Controller predecessor, OrderBuilder orderBuilder) {
        this.predecessor = predecessor;
        this.orderBuilder = orderBuilder;
        this.reset();
    }

    public void run() {
        ItemChoosingProcessor itemChoosingProcessor = new ItemChoosingProcessor();
//        QuantityProcessor quantityProcessor = new QuantityProcessor();
//        AddToppingProcessor addToppingProcessor = new AddToppingProcessor();
//        itemChoosingProcessor.setNext(quantityProcessor);
//        setNext(addToppingProcessor);
        itemChoosingProcessor.handle(orderItemBuilder);
        // check valid id
//        System.out.print("Enter the quantity: ");
//        orderItemBuilder.setQuantity(input);
//        // check valid quantity
//        System.out.print("Enter the toppings (default is no topping): ");
//        orderItemBuilder.setToppings(new ToppingItem[]{});
        // check valid input
//        System.out.print("Enter the note: ");
        // check valid input
        this.addOrderItem();
        // back to create order screen
        this.predecessor.run();
    }

    private void addOrderItem() {
        orderBuilder.addOrderItem(orderItemBuilder.createOrderItem());
        this.showSuccessMessage();
        this.reset();
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
