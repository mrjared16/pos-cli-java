package vn.zalopay.freshers.poscli.controllers.handlers;

import vn.zalopay.freshers.poscli.controllers.MenuManager;
import vn.zalopay.freshers.poscli.controllers.CommandHandler;
import vn.zalopay.freshers.poscli.controllers.OrderItemBuilder;
import vn.zalopay.freshers.poscli.controllers.handlers.validators.Validator;
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
        IntInput input = new ArrayInput(this, this);
        builder.setMenuItem(this.getMenuItem(input));
        ((OrderItemInputHandler)this.successor).handle(builder);
    }

    private MenuItem getMenuItem(IntInput input) {
        return this.menuItems.get(input.getId());
    }

    @Override
    protected void showInputPrompt() {
        System.out.print("Enter the item ID: ");
    }

    private void showMenu() {
        System.out.println("Menu:");
        System.out.println();
        System.out.println("ID\tName\tPrice");
        for (int i = 0; i < this.menuItems.size(); i++) {
            MenuItem current = this.menuItems.get(i);
            System.out.println((i + 1) + ". " + current.getName() + "\t" + current.getPrice());
        }
        System.out.println();
    }

    @Override
    public void execute() {
        System.out.printf("Command is not valid! Please enter in the range of 1 -> %d: ", this.menuItems.size());
    }

    @Override
    public boolean valid(Input input) {
        int index = ((ArrayInput)input).getId();
        return (index >= 0 && index < this.menuItems.size());
    }
}
