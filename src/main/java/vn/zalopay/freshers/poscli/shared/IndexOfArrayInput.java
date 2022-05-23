package vn.zalopay.freshers.poscli.shared;

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
