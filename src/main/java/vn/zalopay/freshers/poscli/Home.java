package vn.zalopay.freshers.poscli;

import vn.zalopay.freshers.poscli.models.MyCommand;
import vn.zalopay.freshers.poscli.models.MyCommandHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class Home {
    private final Map<MyCommand, MyCommandHandler> homeCommands;
    private static final int DEFAULT_COMMAND = 3;
    public Home() {
        homeCommands = new LinkedHashMap<>();
        homeCommands.put(new MyCommand(1, "Create new order"), () -> {
            System.out.println("Create new order");
            System.out.println("Create new order 2");
        });
        homeCommands.put(new MyCommand(2, "Manage orders"), () -> {
            System.out.println("Manage orders");
            System.out.println("Manage orders 2");
        });
        homeCommands.put(new MyCommand(3, "Quit"), ()->{

        });
    }
    private void showGreetingMessage() {
        System.out.println("Welcome to POS CLI");
    }
    private void showHomeMenu() {
        showHomeMenuActions();
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
            }
            didRead = true;
        }
        return new MyCommand(command);
    }

    private boolean validCommand(int command) {
        return homeCommands.containsKey(new MyCommand(command));
    }

    private void showInputPrompt() {
        System.out.print("Input the commands ID: ");
    }

    private void showHomeMenuActions() {
        homeCommands.forEach((command, handler) -> System.out.println(command.getKey() + ". " + command.getLabel()));
    }
    public void run() {
        showGreetingMessage();
        showHomeMenu();
        MyCommand command = getOneCommand();
        homeCommands.get(command).execute();
    }
}
