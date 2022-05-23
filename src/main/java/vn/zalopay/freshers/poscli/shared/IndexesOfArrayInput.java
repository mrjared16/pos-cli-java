package vn.zalopay.freshers.poscli.shared;


import java.util.*;

public class IndexesOfArrayInput implements Input {
    List<Integer> values;
    boolean emptyAsDefault;

    public List<Integer> getValues() {
        return values;
    }

    private static final String INVALID_MESSAGE = "Some of values is not valid! Please enter again: ";


    public IndexesOfArrayInput(Validator validator, CommandHandler errorHandler, boolean emptyAsDefault) {
        this.emptyAsDefault = emptyAsDefault;
        this.handle(validator, errorHandler);
    }

    // ""
    // "1 3 1 -1"
    // "-1"
    // "1 3 abc a"
    private void handle(Validator validator, CommandHandler errorHandler) {
        // using set to auto remove duplicate
        LinkedHashSet<Integer> tmp = new LinkedHashSet<>();
        Scanner input = new Scanner(System.in);

        // handle default case
        String tmpToCheckBlank = input.nextLine();
        if (tmpToCheckBlank.isBlank() && this.emptyAsDefault) {
            values = new ArrayList<>();
            // default cases
            return;
        }
        input = new Scanner(tmpToCheckBlank);

        boolean switchToSTDIN = false;
        int command = 0;
        boolean didRead = false;
        while (!didRead) {
            // handle first line and switch to stdin
            if (!input.hasNext() && !switchToSTDIN) {
                input = new Scanner(System.in);
                switchToSTDIN = true;
            }
            if (!input.hasNextInt()) {
                System.out.print(INVALID_MESSAGE);
                this.handle(validator, errorHandler);
                return;
            }
            command = input.nextInt();
            if (command == -1) {
                didRead = true;
            } else {
                // index of array
                int value = command - 1;
                tmp.add(value);
            }
        }
        values = new ArrayList<>(tmp);
        if (!validator.valid(this)) {
            errorHandler.execute();
            this.handle(validator, errorHandler);
        }
    }

    public boolean isDefault() {
        return this.values.isEmpty();
    }
}