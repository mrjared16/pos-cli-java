package vn.zalopay.freshers.poscli.controllers.handlers;

import vn.zalopay.freshers.poscli.shared.*;
import vn.zalopay.freshers.poscli.domains.OrderItemBuilder;
import vn.zalopay.freshers.poscli.models.ToppingItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToppingsChoosingProcessor extends OrderItemInputHandler implements Validator, CommandHandler {
    private List<ToppingItem> toppingsOfCurrentMenuItem;
    private final List<ToppingItem> defaultToppings;
    private List<ToppingItem> currentToppings;
    private int maxTopping = Integer.MAX_VALUE;
    public ToppingsChoosingProcessor() {
        this.defaultToppings = new ArrayList<>();
    }

    @Override
    public void handle(OrderItemBuilder builder) {
        this.init(builder);
        this.showToppingsSelection();
        this.showInputPrompt();
        IndexesOfArrayInput input = new IndexesOfArrayInput(this, this, true);
        // handle default
        this.getToppings(input);
        // add toppings
        builder.setToppings(this.currentToppings);
        this.showSuccessMessage();
        if (successor != null) {
            ((OrderItemInputHandler) this.successor).handle(builder);
        }
    }

    private void showToppingsSelection() {
        System.out.println("Topping for current item: ");
        System.out.println();
        int numberOfColumns = 3;
        String[] columns = new String[numberOfColumns];
        columns[0] = "ID";
        columns[1] = "Name";
        columns[2] = "Price (VND)";
        Utils.printColumnsFormat(columns, numberOfColumns);
        for (int i = 0; i < this.toppingsOfCurrentMenuItem.size(); i++) {
            ToppingItem current = this.toppingsOfCurrentMenuItem.get(i);
            columns[0] = Integer.toString(i + 1);
            columns[1] = current.getName();
            columns[2] = Integer.toString((int) current.getPrice());
            Utils.printColumnsFormat(columns, numberOfColumns);
        }
        System.out.println();
    }

    private void getToppings(IndexesOfArrayInput input) {
        if (input.isDefault()) {
            this.currentToppings = this.defaultToppings;
            return;
        }
        this.currentToppings = new ArrayList<>();
        for (int index: input.getValues()) {
            this.currentToppings.add(this.toppingsOfCurrentMenuItem.get(index));
        }
    }

    private void showSuccessMessage() {
        List<String> toppingNames = this.currentToppings.stream()
                .map(ToppingItem::getName).collect(Collectors.toList());
        System.out.println("Added topping " + String.join(", ", toppingNames) + " to order!");
    }

    private void init(OrderItemBuilder builder) {
        this.maxTopping = builder.getMenuItem().getMaxTopping();
        toppingsOfCurrentMenuItem = builder.getMenuItem().getApplicableToppings();
    }

    @Override
    protected void showInputPrompt() {
        System.out.print("Enter topping IDs (Example: \"1 3 -1\", default is no topping): ");
    }

    @Override
    public boolean valid(Input input) {
        List<Integer> indexes = ((IndexesOfArrayInput)input).getValues();
        if (this.maxTopping >= indexes.size()) {
            return indexes.stream().allMatch(index -> index >= 0 && index < this.toppingsOfCurrentMenuItem.size());
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.printf("Some of topping ID are not valid or you exceeded max topping of this item (%d)! Please enter again: ", this.maxTopping);
    }
}