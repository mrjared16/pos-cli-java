package vn.zalopay.freshers.poscli.controllers.handlers;

import vn.zalopay.freshers.poscli.shared.CommandHandler;
import vn.zalopay.freshers.poscli.domains.OrderItemBuilder;
import vn.zalopay.freshers.poscli.shared.Validator;
import vn.zalopay.freshers.poscli.shared.Input;
import vn.zalopay.freshers.poscli.shared.IntInput;

public class QuantityProcessor extends OrderItemInputHandler implements Validator, CommandHandler {
    @Override
    public void handle(OrderItemBuilder builder) {
        this.showInputPrompt();
        IntInput input = new IntInput(this, this);
        builder.setQuantity(input.getValue());
        if (successor != null) {
            ((OrderItemInputHandler) this.successor).handle(builder);
        }
    }

    @Override
    protected void showInputPrompt() {
        System.out.print("Enter quantity: ");
    }

    @Override
    public boolean valid(Input input) {
        int quantity = ((IntInput)input).getValue();
        return quantity > 0;
    }

    @Override
    public void execute() {
        System.out.print("Quantity is not valid! Please enter quantity > 0: ");
    }
}
