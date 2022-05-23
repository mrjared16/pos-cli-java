package vn.zalopay.freshers.poscli.controllers.handlers;

import vn.zalopay.freshers.poscli.controllers.CommandHandler;
import vn.zalopay.freshers.poscli.controllers.OrderItemBuilder;
import vn.zalopay.freshers.poscli.controllers.handlers.validators.Validator;

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
