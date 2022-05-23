package vn.zalopay.freshers.poscli.controllers.handlers;

import vn.zalopay.freshers.poscli.controllers.CommandHandler;
import vn.zalopay.freshers.poscli.controllers.handlers.validators.Validator;

public class ArrayInput extends IntInput {
    public ArrayInput(Validator validator, CommandHandler errorHandler) {
        super(validator, errorHandler);
    }

    public ArrayInput(Validator validator) {
        super(validator);
    }

    @Override
    public int getValue() {
        return super.getValue() - 1;
    }
}
