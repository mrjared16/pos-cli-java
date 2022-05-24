package vn.zalopay.freshers.poscli.controllers.handlers;

import vn.zalopay.freshers.poscli.models.Order;
import vn.zalopay.freshers.poscli.shared.CommandHandler;
import vn.zalopay.freshers.poscli.shared.Input;
import vn.zalopay.freshers.poscli.shared.IntInput;
import vn.zalopay.freshers.poscli.shared.Validator;

import java.util.Objects;

public class OrderProcessingConfirmationHandler extends InputHandler implements Validator, CommandHandler {
    public void handle(Order order) {
        if (!order.canProcess()) {
            this.showCannotProcessMessage();
            return;
        }
        this.showConfirmationMessage(order);
        this.showInputPrompt();
        IntInput input = new IntInput(this, this);
        if (!didConfirm(input)) {
            return;
        }
        order.nextState();
    }

    private void showCannotProcessMessage() {
        System.out.println("Cannot process this order!");
    }

    private void showConfirmationMessage(Order order) {
        // show order detail
        System.out.print(order.toString());
        System.out.println();
        Order.OrderStatus currentStatus = order.getOrderStatus();
        System.out.printf("Do you want to move order id %s from %s to %s", order.getId(), currentStatus.name(), Objects.requireNonNull(currentStatus.getNext()).name());
        System.out.println();
    }

    @Override
    protected void showInputPrompt() {
        System.out.print("Enter 1 for confirm, 2 to cancel: ");
    }

    @Override
    public boolean valid(Input input) {
        int command = ((IntInput) input).getValue();
        return (command == 1 || command == 2);
    }

    @Override
    public void execute() {
        System.out.print("Command is not valid. Please enter again: ");
    }

    private boolean didConfirm(Input input) {
        int command = ((IntInput) input).getValue();
        return command == 1;
    }
}
