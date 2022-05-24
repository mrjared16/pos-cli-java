package vn.zalopay.freshers.poscli.controllers;

import vn.zalopay.freshers.poscli.domains.*;
import vn.zalopay.freshers.poscli.models.Order;
import vn.zalopay.freshers.poscli.models.OrderItem;
import vn.zalopay.freshers.poscli.shared.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.util.stream.Collectors.joining;


public class ManageOrderController implements Controller, Validator {
    private final Map<Key, Command> manageOrderCommands;
    private final Controller predecessor;
    private final OrderService orderService;

    public ManageOrderController(Controller predecessor, OrderService orderService) {
        this.reset();
        this.predecessor = predecessor;
        this.orderService = orderService;
        List<Command> commands = Arrays.asList(
                new Command(new NumberKey(1), "Process order", () -> {
                    Controller orderProcessingController = new OrderProcessingController(this, this.orderService);
                    orderProcessingController.run();
                }),
                new Command(new NumberKey(2), "Back", this.predecessor::run)

        );

        manageOrderCommands = new LinkedHashMap<>();
        commands.forEach(command -> this.manageOrderCommands.put(command.getKey(), command));
    }

    @Override
    public void run() {
        showManageOrderMessage();
        showManageOrderActions();
        showInputPrompt();
        IntInput input = new IntInput(this);
        this.handleCommand(input);
    }

    @Override
    public void reset() {
        // there is no state
    }

    private void showManageOrderMessage() {
        this.showGreetingMessage();
        this.showOrderQueues();
    }

    private void showOrderQueues() {
        LinkedHashMap<String, List<Order>> orderQueues = this.orderService.getOrdersGroupByStatus();
        for (Map.Entry<String, List<Order>> entry: orderQueues.entrySet()) {
            System.out.printf("%s ORDERS: ", entry.getKey());
            System.out.println();
            if (entry.getValue().isEmpty()) {
                continue;
            }
            int numberOfColumns = 5;
            int columnsWidth = 10;
            Utils.printColumnsFormat(new String[]{"ID", "Items", "Total", "Quantity", "Time"}, numberOfColumns);
            String[] columns = new String[numberOfColumns];
            for (Order order: entry.getValue()) {
                columns[0] = order.getId();
                columns[1] = order.getOrderItems().stream().map(orderItem -> orderItem.getMenuItem().getName()).collect(joining(","));
                if (columns[1].length() > columnsWidth) {
                    columns[1] = columns[1].substring(0, columnsWidth - 2) + "...";
                }
                columns[2] = Integer.toString(order.getTotal());
                columns[3] = order.getOrderItems().stream().map(OrderItem::getQuantity).reduce(0, Integer::sum).toString();
                LocalTime localTime = LocalDateTime.ofInstant(order.getOrderedAt().toInstant(), ZoneId.systemDefault()).toLocalTime();
                columns[4] = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                Utils.printColumnsFormat(columns, numberOfColumns);
            }
        }
    }

    @Override
    public void showGreetingMessage() {
        System.out.println();
        System.out.println("All Order");
    }

    private void showInputPrompt() {
        System.out.print("Input the commands ID: ");
    }

    private void showManageOrderActions() {
        this.manageOrderCommands.forEach((key, command) -> System.out.println(key.toString() + ". " + command.getLabel()));
        System.out.println();
    }

    @Override
    public boolean valid(Input input) {
        Key key = new NumberKey(((IntInput)input).getValue());
        return this.manageOrderCommands.containsKey(key);
    }

    private void handleCommand(Input input) {
        Key key = new NumberKey(((IntInput)input).getValue());
        this.manageOrderCommands.get(key).execute();
    }
}
