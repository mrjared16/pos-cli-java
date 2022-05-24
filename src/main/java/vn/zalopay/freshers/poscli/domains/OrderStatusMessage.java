package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.Order;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OrderStatusMessage extends Message {
    @Override
    protected String getHeader() {
        return "";
    }

    @Override
    protected String getFooter() {
        return "";
    }

    private final Order order;
    public OrderStatusMessage(Order order) {
        this.order = order;
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
        switch (order.getOrderStatus()) {
            case DRAFT:
            case WAITING:
            case PROCESSING:
            case READY:
                System.out.printf("Your order is %s!", order.getOrderStatus());
                break;
        }

        // Put things back
        String result = byteArrayOutputStream.toString();
        System.out.flush();
        System.setOut(stdOut);
        return result;
    }
}
