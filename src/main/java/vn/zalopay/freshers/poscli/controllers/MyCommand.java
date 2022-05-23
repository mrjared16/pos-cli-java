package vn.zalopay.freshers.poscli.controllers;

public class MyCommand {
    private final Key key;
    private String label;
    private CommandHandler handler;
    public MyCommand(Key key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public MyCommand(Key key, String label, CommandHandler handler) {
        this.key = key;
        this.label = label;
        this.handler = handler;
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

    public Key getKey() {
        return this.key;
    }
    public void execute() {
        this.handler.execute();
    }
}
