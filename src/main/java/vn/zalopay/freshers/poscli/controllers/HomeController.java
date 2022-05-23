package vn.zalopay.freshers.poscli.controllers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class HomeController implements Controller {
    private final Map<MyCommand, MyCommandHandler> homeCommands;
    private static final int DEFAULT_COMMAND = 3;

    public HomeController() {
        this.homeCommands = new LinkedHashMap<>();
        Controller createOrderController = new CreateOrderController(this);
        this.homeCommands.put(new MyCommand(1, "Create new order"), createOrderController::run);
        this.homeCommands.put(new MyCommand(2, "Manage orders"), () -> {
            System.out.println("Manage orders");
            System.out.println("Manage orders 2");
        });
        this.homeCommands.put(new MyCommand(3, "Quit"), () -> {

        });
    }

    @Override
    public void run() {
        showGreetingMessage();
        showHomeMenuActions();
        MyCommand command = getOneCommand();
        homeCommands.get(command).execute();
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
        homeCommands.forEach((command, handler) -> System.out.println(command.getKey() + ". " + command.getLabel()));
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
        return homeCommands.containsKey(new MyCommand(command));
    }

}
