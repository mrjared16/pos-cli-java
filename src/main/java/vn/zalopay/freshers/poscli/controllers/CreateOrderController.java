package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.domains.*;
import vn.zalopay.freshers.poscli.shared.*;

import java.util.*;


public class CreateOrderController implements Controller, Validator {
    private final Map<Key, Command> createOrderCommands;
    private OrderBuilder orderBuilder;
    private final Controller predecessor;
    private final OrderService orderService;
    private final Printer printer;

    public CreateOrderController(Controller predecessor, OrderService orderService) {
        this.reset();
        this.predecessor = predecessor;
        this.orderService = orderService;
        this.printer = PrinterFactory.getInstance().getDefaultPrinter();
        List<Command> commands = Arrays.asList(
                new Command(new NumberKey(1), "Add item to order", () -> {
                    Controller addItemToOrder = new AddItemToOrderController(this, orderBuilder);
                    addItemToOrder.loading();
                }),
                new Command(new NumberKey(2), "Confirm order", () -> {
                    Controller confirmOrderController = new ConfirmOrderController(this, this.orderBuilder, this.orderService, printer);
                    confirmOrderController.loading();
                }),
                new Command(new NumberKey(3), "Void order", () -> {
                    this.reset();
                    this.loading();
                }),
                new Command(new NumberKey(4), "Back", this.predecessor::loading)

        );

        createOrderCommands = new LinkedHashMap<>();
        commands.forEach(command -> this.createOrderCommands.put(command.getKey(), command));
    }

    @Override
    public void loading() {
        showCreateOrderMessage(this.orderBuilder);
        showCreateOrderActions();
        showInputPrompt();
        IntInput input = new IntInput(this);
        this.handleCommand(input);
    }

    @Override
    public void reset() {
        this.orderBuilder = new OrderBuilder();
    }

    private void showCreateOrderMessage(OrderBuilder orderBuilder) {
        this.showGreetingMessage();
        this.showDraftOrder(orderBuilder);
    }

    @Override
    public void showGreetingMessage() {
        System.out.println();
        System.out.println("Current order");
    }

    private void showDraftOrder(OrderBuilder orderBuilder) {
        System.out.println(orderBuilder.toString());
    }

    private void showInputPrompt() {
        System.out.print("Input the commands ID: ");
    }

    private void showCreateOrderActions() {
        this.createOrderCommands.forEach((key, command) -> System.out.println(key.toString() + ". " + command.getLabel()));
        System.out.println();
    }

    @Override
    public boolean valid(Input input) {
        Key key = new NumberKey(((IntInput)input).getValue());
        return this.createOrderCommands.containsKey(key);
    }

    private void handleCommand(Input input) {
        Key key = new NumberKey(((IntInput)input).getValue());
        this.createOrderCommands.get(key).execute();
    }
}
