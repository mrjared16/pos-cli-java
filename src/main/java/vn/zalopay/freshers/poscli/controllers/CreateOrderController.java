package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.shared.*;
import vn.zalopay.freshers.poscli.domains.OrderBuilder;
import vn.zalopay.freshers.poscli.models.*;

import java.util.*;


public class CreateOrderController implements Controller, Validator {
    private final Map<Key, Command> createOrderCommands;
    private OrderBuilder orderBuilder;
    private final Controller predecessor;

    public CreateOrderController(Controller predecessor) {
        this.predecessor = predecessor;
        this.reset();
        List<Command> commands = Arrays.asList(
                new Command(new NumberKey(1), "Add item to order", () -> {
                    Controller addItemToOrder = new AddItemToOrderController(this, orderBuilder);
                    addItemToOrder.run();
                }),
                new Command(new NumberKey(2), "Edit item in order", () -> {

                }),
                new Command(new NumberKey(3), "Confirm order", () -> {

                }),
                new Command(new NumberKey(4), "Void order", () -> {
                    this.reset();
                    this.predecessor.run();
                })
        );

        createOrderCommands = new LinkedHashMap<>();
        commands.forEach(command -> this.createOrderCommands.put(command.getKey(), command));
    }

    @Override
    public void run() {
        showCreateOrderMessage();
        showCreateOrderActions();
        showInputPrompt();
        IntInput input = new IntInput(this);
        this.handleCommand(input);
    }

    @Override
    public void reset() {
        this.orderBuilder = new OrderBuilder();
    }

    private void showCreateOrderMessage() {
        this.showGreetingMessage();
        this.showDraftOrder();
    }

    @Override
    public void showGreetingMessage() {
        System.out.println();
        System.out.println("Current order");
    }

    private void showDraftOrder() {
        System.out.println();
        if (this.orderBuilder.getOrderItems().isEmpty()) {
            System.out.println("Current order doesn't have any item");
            System.out.println();
            return;
        }
        int numberOfColumns = 4;
        Utils.printColumnsFormat(new String[]{"ID", "Name", "Price (VND)", "Quantity"}, numberOfColumns);
        String[] columns = new String[numberOfColumns];
        for (int i = 0; i < this.orderBuilder.getOrderItems().size(); i++) {
            OrderItem current = this.orderBuilder.getOrderItems().get(i);
            columns[0] = (i + 1) + "";
            columns[1] = current.getMenuItem().getName();
            columns[2] = Integer.toString((int) current.getMenuItem().getPrice());
            columns[3] = Integer.toString(current.getQuantity());
            Utils.printColumnsFormat(columns, 4);
            List<ToppingItem> toppings = current.getToppings();
            for (ToppingItem currentToppingItem : toppings) {
                columns[0] = "";
                columns[1] = currentToppingItem.getName();
                columns[2] = Integer.toString((int) currentToppingItem.getPrice());
                columns[3] = "";
                Utils.printColumnsFormat(columns, numberOfColumns);
            }
        }
        columns[0] = "Total (VND)";
        columns[1] = "";
        columns[2] = "";
        columns[3] = Integer.toString(this.orderBuilder.getCurrentTotal());
        Utils.printColumnsFormat(columns, numberOfColumns);
        System.out.println();
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
