package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.domains.OrderService;
import vn.zalopay.freshers.poscli.shared.*;

import java.util.*;


public class HomeController implements Controller, Validator {
    private final Map<Key, Command> homeCommands;
    private final OrderService orderService;
    public HomeController(OrderService orderService) {
        this.orderService = orderService;
        this.homeCommands = new LinkedHashMap<>();
        Controller createOrderController = new CreateOrderController(this, this.orderService);
        Controller manageOrderController = new ManageOrderController(this, this.orderService);
        List<Command> commands = Arrays.asList(
            new Command(new NumberKey(1), "Create new order", createOrderController::loading),
            new Command(new NumberKey(2), "Manage orders", manageOrderController::loading),
            new Command(new NumberKey(3), "Quit", () -> {})
        );

        commands.forEach(command -> this.homeCommands.put(command.getKey(), command));
    }

    @Override
    public void loading() {
        showGreetingMessage();
        showHomeMenuActions();
        showInputPrompt();
        IntInput input = new IntInput(this );
        this.handleCommand(input);
    }

    @Override
    public void reset() {
        // There is no state in Home
    }

    @Override
    public void showGreetingMessage() {
        System.out.println("Welcome to POS CLI\n");
    }

    private void showInputPrompt() {
        System.out.print("Input the commands ID: ");
    }

    private void showHomeMenuActions() {
        homeCommands.forEach((key, command) -> System.out.println(key.toString() + ". " + command.getLabel()));
        System.out.println();
    }


    @Override
    public boolean valid(Input input) {
        Key key = new NumberKey(((IntInput)input).getValue());
        return homeCommands.containsKey(key);
    }

    private void handleCommand(Input input) {
        Key key = new NumberKey(((IntInput)input).getValue());
        this.homeCommands.get(key).execute();
    }
}
