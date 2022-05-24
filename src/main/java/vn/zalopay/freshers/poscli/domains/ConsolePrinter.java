package vn.zalopay.freshers.poscli.domains;

public class ConsolePrinter implements Printer {
    @Override
    public void send(Message messageBody) {
        System.out.println(messageBody.toString());
    }
}
