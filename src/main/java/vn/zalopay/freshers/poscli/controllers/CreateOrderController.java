package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.controllers.handlers.Input;
import vn.zalopay.freshers.poscli.controllers.handlers.IntInput;
import vn.zalopay.freshers.poscli.controllers.handlers.validators.Validator;
import vn.zalopay.freshers.poscli.models.*;

import java.util.*;


public class CreateOrderController implements Controller, Validator {
    private final Map<Key, MyCommand> createOrderCommands;
    private OrderBuilder orderBuilder;
    private final Controller predecessor;

    public CreateOrderController(Controller predecessor) {
        this.predecessor = predecessor;
        this.reset();
        List<MyCommand> commands = Arrays.asList(
                new MyCommand(new NumberKey(1), "Add item to order", () -> {
                    Controller addItemToOrder = new AddItemToOrderController(this, orderBuilder);
                    addItemToOrder.run();
                }),
                new MyCommand(new NumberKey(2), "Edit item in order", () -> {

                }),
                new MyCommand(new NumberKey(3), "Confirm order", () -> {

                }),
                new MyCommand(new NumberKey(4), "Void order", () -> {
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
        System.out.println("ID\tName\tPrice\tQuantity");
        for (int i = 0; i < this.orderBuilder.getOrderItems().size(); i++) {
            OrderItem current = this.orderBuilder.getOrderItems().get(i);
            System.out.println((i + 1) + ". " +
                    current.getMenuItem().getName() + "\t" +
                    current.getMenuItem().getPrice() + "\t" +
                    current.getQuantity() + "\t"
            );
            ArrayList<ToppingItem> toppings = current.getToppings();
            for (ToppingItem currentToppingItem : toppings) {
                System.out.println("\t\t" +
                        currentToppingItem.getName() + "\t" +
                        currentToppingItem.getPrice() + "\t"
                );
            }
        }
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
        Key key = new NumberKey(((IntInput)input).getId());
        return this.createOrderCommands.containsKey(key);
    }

    private void handleCommand(Input input) {
        Key key = new NumberKey(((IntInput)input).getId());
        this.createOrderCommands.get(key).execute();
    }
}
