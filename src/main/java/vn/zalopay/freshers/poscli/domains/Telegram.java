package vn.zalopay.freshers.poscli.domains;

public class Telegram implements Sender {
    private final String phoneNumber;

    public Telegram(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(Message messageBody) {
        System.out.println("************");
        System.out.printf("Sending to Telegram user that has phone number %s...", this.phoneNumber);
        System.out.println();
        System.out.printf("Content: %s", messageBody.toString());
        System.out.println();
        System.out.println("Sent to Telegram!");
        System.out.println("************");
    }
}
