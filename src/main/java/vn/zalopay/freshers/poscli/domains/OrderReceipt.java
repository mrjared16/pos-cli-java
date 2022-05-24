package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;
import vn.zalopay.freshers.poscli.models.OrderItem;
import vn.zalopay.freshers.poscli.models.ToppingItem;
import vn.zalopay.freshers.poscli.shared.Utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class OrderReceipt extends Message {
    @Override
    protected String getHeader() {
        return "";
    }

    @Override
    protected String getFooter() {
        return "";
    }

    private final List<OrderItem> orderItems;
    private final PriceCalculator priceCalculator;
    public OrderReceipt(Order order) {
        this.orderItems = order.getOrderItems();
        this.priceCalculator = new PriceCalculator();
    }

    public OrderReceipt(List<OrderItem> orderItems, PriceCalculator priceCalculator) {
        this.orderItems = orderItems;
        this.priceCalculator = priceCalculator;
    }

    @Override
    protected String getBody() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        // Save the old System.out!
        PrintStream stdOut = System.out;
        // Tell Java to use custom stream
        System.setOut(printStream);
        // Print some output: goes to your special stream
        System.out.println();
        if (this.orderItems.isEmpty()) {
            System.out.println("Current order doesn't have any item");
        } else {
            int numberOfColumns = 4;
            Utils.printColumnsFormat(new String[]{"ID", "Name", "Price (VND)", "Quantity"}, numberOfColumns);
            String[] columns = new String[numberOfColumns];
            for (int i = 0; i < this.orderItems.size(); i++) {
                OrderItem current = this.orderItems.get(i);
                columns[0] = (i + 1) + "";
                columns[1] = current.getMenuItem().getName();
                columns[2] = Integer.toString((int) current.getMenuItem().getPrice());
                columns[3] = Integer.toString(current.getQuantity());
                Utils.printColumnsFormat(columns, 4);
                List<ToppingItem> toppings = current.getToppings();
                for (ToppingItem currentToppingItem : toppings) {
                    columns[0] = "";
                    columns[1] = currentToppingItem.getName();
                    columns[2] = Integer.toString((int) currentToppingItem.getPrice());
                    columns[3] = "";
                    Utils.printColumnsFormat(columns, numberOfColumns);
                }
            }
            columns[0] = "Total (VND)";
            columns[1] = "";
            columns[2] = "";
            columns[3] = Integer.toString(this.priceCalculator.calculate(this.orderItems));
            Utils.printColumnsFormat(columns, numberOfColumns);
        }

        // Put things back
        String result = byteArrayOutputStream.toString();
        System.out.flush();
        System.setOut(stdOut);
        return result;
    }
}
