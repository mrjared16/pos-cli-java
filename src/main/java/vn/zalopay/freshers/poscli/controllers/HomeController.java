package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.controllers.handlers.Input;
import vn.zalopay.freshers.poscli.controllers.handlers.IntInput;
import vn.zalopay.freshers.poscli.controllers.handlers.validators.Validator;

import java.util.*;


public class HomeController implements Controller, Validator {
    private final Map<Key, MyCommand> homeCommands;

    public HomeController() {
        this.homeCommands = new LinkedHashMap<>();
        Controller createOrderController = new CreateOrderController(this);
        List<MyCommand> commands = Arrays.asList(
            new MyCommand(new NumberKey(1), "Create new order", createOrderController::run),
            new MyCommand(new NumberKey(2), "Manage orders", () -> {
                System.out.println("Manage orders");
                System.out.println("Manage orders 2");}),
            new MyCommand(new NumberKey(3), "Quit", () -> {})
        );

        commands.forEach(command -> this.homeCommands.put(command.getKey(), command));
    }

    @Override
    public void run() {
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
        Key key = new NumberKey(((IntInput)input).getId());
        return homeCommands.containsKey(key);
    }

    private void handleCommand(Input input) {
        Key key = new NumberKey(((IntInput)input).getId());
        this.homeCommands.get(key).execute();
    }
}
