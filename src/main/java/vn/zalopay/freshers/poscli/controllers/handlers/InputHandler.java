package vn.zalopay.freshers.poscli.controllers.handlers;

public abstract class InputHandler {
    protected InputHandler successor;
    public void setNext(InputHandler inputHandler) {
        this.successor = inputHandler;
    }
    protected abstract void showInputPrompt();
}
