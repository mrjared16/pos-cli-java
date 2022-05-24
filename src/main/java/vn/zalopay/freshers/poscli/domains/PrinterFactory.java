package vn.zalopay.freshers.poscli.domains;

import java.util.HashMap;

public class PrinterFactory {
    private final HashMap<PrinterType, Printer> printerSample = new HashMap<>();
    private static PrinterFactory instance = null;
    private PrinterFactory() {
        printerSample.put(PrinterType.CONSOLE, new ConsolePrinter());
    }
    public static PrinterFactory getInstance() {
        if (instance == null)
        {
            instance = new PrinterFactory();
        }
        return instance;
    }
    public Printer getPrinter(PrinterType printerType) {
        if (!printerSample.containsKey(printerType)) {
            return getDefaultPrinter();
        }
        return printerSample.get(printerType);
    }

    public Printer getDefaultPrinter() {
        return printerSample.get(PrinterType.CONSOLE);
    }
}
