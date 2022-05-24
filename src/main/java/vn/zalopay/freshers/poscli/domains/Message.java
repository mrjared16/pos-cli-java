package vn.zalopay.freshers.poscli.domains;

public abstract class Message {
    protected String getHeader() {
        return null;
    }
    protected abstract String getBody();
    protected String getFooter() {
        return null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getHeader());
        stringBuilder.append(getBody());
        stringBuilder.append(getFooter());

        return stringBuilder.toString();
    }
}
