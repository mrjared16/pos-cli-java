package vn.zalopay.freshers.poscli.shared;

public class Utils {
    private Utils() {

    }
    public static void printColumnsFormat(String[] inputs, int numberOfColumns) {
        System.out.println(String.format("%10s ".repeat(Math.max(0, numberOfColumns)), (Object[]) inputs));
    }
}
