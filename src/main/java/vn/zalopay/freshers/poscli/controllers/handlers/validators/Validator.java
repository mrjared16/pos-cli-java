package vn.zalopay.freshers.poscli.controllers.handlers.validators;

import vn.zalopay.freshers.poscli.controllers.handlers.Input;

public interface Validator {
    boolean valid(Input input);
}
