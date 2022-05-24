package vn.zalopay.freshers.poscli.domains;

public class Zalo implements Sender {
    private final String phoneNumber;

    public Zalo(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(Message messageBody) {
        System.out.println("************");
        System.out.printf("Sending to Zalo username with %s...", this.phoneNumber);
        System.out.println();
        System.out.printf("Content: %s", messageBody.toString());
        System.out.println();
        System.out.println("Sent to Zalo!");
        System.out.println("************");
    }
}
