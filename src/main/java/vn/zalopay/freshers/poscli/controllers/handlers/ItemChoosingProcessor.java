package vn.zalopay.freshers.poscli.controllers.handlers;

import vn.zalopay.freshers.poscli.domains.MenuManager;
import vn.zalopay.freshers.poscli.shared.*;
import vn.zalopay.freshers.poscli.domains.OrderItemBuilder;
import vn.zalopay.freshers.poscli.models.*;

import java.util.ArrayList;

public class ItemChoosingProcessor extends OrderItemInputHandler implements CommandHandler, Validator {
    private ArrayList<MenuItem> menuItems;
    public ItemChoosingProcessor() {
        this.menuItems = MenuManager.getMenuItemSamples();
    }
    @Override
    public void handle(OrderItemBuilder builder) {
        this.showMenu();
        this.showInputPrompt();
        IntInput input = new IndexOfArrayInput(this, this);
        builder.setMenuItem(this.getMenuItem(input));
        if (successor != null) {
            ((OrderItemInputHandler) this.successor).handle(builder);
        }
    }

    private MenuItem getMenuItem(IntInput input) {
        return this.menuItems.get(input.getValue());
    }

    @Override
    protected void showInputPrompt() {
        System.out.print("Enter the item ID: ");
    }

    private void showMenu() {
        System.out.println("Menu:");
        System.out.println();
        int numberOfColumns = 3;
        String[] columns = new String[numberOfColumns];
        columns[0] = "ID";
        columns[1] = "Name";
        columns[2] = "Price (VND)";
        Utils.printColumnsFormat(columns, numberOfColumns);
        for (int i = 0; i < this.menuItems.size(); i++) {
            MenuItem current = this.menuItems.get(i);
            columns[0] = Integer.toString(i + 1);
            columns[1] = current.getName();
            columns[2] = Integer.toString((int) current.getPrice());
            Utils.printColumnsFormat(columns, numberOfColumns);
        }
        System.out.println();
    }

    @Override
    public void execute() {
        System.out.printf("ID is not valid! Please enter in the range of 1 -> %d: ", this.menuItems.size());
    }

    @Override
    public boolean valid(Input input) {
        int index = ((IndexOfArrayInput)input).getValue();
        return (index >= 0 && index < this.menuItems.size());
    }
}
