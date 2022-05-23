package vn.zalopay.freshers.poscli.controllers.handlers;

import vn.zalopay.freshers.poscli.controllers.CommandHandler;
import vn.zalopay.freshers.poscli.controllers.handlers.validators.Validator;

public class IndexOfArrayInput extends IntInput {
    public IndexOfArrayInput(Validator validator, CommandHandler errorHandler) {
        super(validator, errorHandler);
    }

    public IndexOfArrayInput(Validator validator) {
        super(validator);
    }

    @Override
    public int getValue() {
        return super.getValue() - 1;
    }
}
