package vn.zalopay.freshers.poscli.controllers;

public class MyCommand {
    private final Integer key;
    private String label;
    public MyCommand(int key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public MyCommand(int key, String label) {
        this.key = key;
        this.label = label;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyCommand)) return false;

        MyCommand command = (MyCommand) o;
        return key.equals(command.key);
    }

    public int getKey() {
        return this.key;
    }
}
