package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.models.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class CreateOrderController implements Controller {
    private final Map<MyCommand, MyCommandHandler> createOrderCommands;
    private OrderBuilder orderBuilder;
    private static final int DEFAULT_COMMAND = 4;
    private final Controller predecessor;

    public CreateOrderController(Controller predecessor) {
        this.predecessor = predecessor;
        this.reset();
        createOrderCommands = new LinkedHashMap<>();
        createOrderCommands.put(new MyCommand(1, "Add item to order"), () -> {
            Controller addItemToOrder = new AddItemToOrderController(this, orderBuilder);
            addItemToOrder.run();
        });
        createOrderCommands.put(new MyCommand(2, "Edit item in order"), () -> {

        });

        createOrderCommands.put(new MyCommand(3, "Confirm order"), () -> { });
        createOrderCommands.put(new MyCommand(4, "Void order"), () -> {
            this.reset();
            this.predecessor.run();
        });
    }

    @Override
    public void run() {
        showCreateOrderMessage();
        showCreateOrderActions();
        MyCommand command = getOneCommand();
        createOrderCommands.get(command).execute();
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
        createOrderCommands.forEach((command, handler) -> System.out.println(command.getKey() + ". " + command.getLabel()));
        System.out.println();
    }

    private MyCommand getOneCommand() {
        showInputPrompt();
        Scanner input = new Scanner(System.in);
        int command = DEFAULT_COMMAND;
        boolean didRead = false;
        while (!didRead) {
            if (!input.hasNextInt()) {
                System.out.print("Command is not valid! Please enter again: ");
                input.next();
                continue;
            }
            command = input.nextInt();
            if (!validCommand(command)) {
                System.out.print("Command is not valid! Please enter again: ");
            } else {
                didRead = true;
            }
        }
        return new MyCommand(command);
    }

    private boolean validCommand(int command) {
        return createOrderCommands.containsKey(new MyCommand(command));
    }
}
