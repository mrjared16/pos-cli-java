package vn.zalopay.freshers.poscli.controllers.handlers;


import vn.zalopay.freshers.poscli.controllers.CommandHandler;
import vn.zalopay.freshers.poscli.controllers.handlers.validators.Validator;

import java.util.Scanner;

public class IntInput implements Input {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static final String INVALID_MESSAGE = "Command is not valid! Please enter again: ";

   public IntInput(Validator validator, CommandHandler errorHandler) {
        Scanner input = new Scanner(System.in);
        int command = 0;
        boolean didRead = false;
        while (!didRead) {
            if (!input.hasNextInt()) {
                System.out.print(INVALID_MESSAGE);
                input.next();
                continue;
            }
            command = input.nextInt();
            this.id = command;
            if (!validator.valid(this)) {
                errorHandler.execute();
            } else {
                didRead = true;
            }
        }
    }
    public IntInput(Validator validator) {
        Scanner input = new Scanner(System.in);
        int command = 0;
        boolean didRead = false;
        while (!didRead) {
            if (!input.hasNextInt()) {
                System.out.print(INVALID_MESSAGE);
                input.next();
                continue;
            }
            command = input.nextInt();
            this.id = command;
            if (!validator.valid(this)) {
                System.out.print(INVALID_MESSAGE);
            } else {
                didRead = true;
            }
        }
    }
}
