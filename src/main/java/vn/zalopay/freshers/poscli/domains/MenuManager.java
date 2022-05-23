package vn.zalopay.freshers.poscli.domains;

import vn.zalopay.freshers.poscli.models.MenuItem;
import vn.zalopay.freshers.poscli.models.ToppingItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MenuManager {
    private MenuManager(){}
    private static final List<MenuItem> listSampleMenuItems;
    static {
        listSampleMenuItems = new ArrayList<>();
        loadMenuItems();
    }

    public static List<MenuItem> getMenuItemSamples() {
        return listSampleMenuItems;
    }
    public static void loadMenuItems() {
        listSampleMenuItems.add(new MenuItem("Coffee", 50000, 2,
                Arrays.asList(
                    new ToppingItem("Cream", 5000),
                    new ToppingItem("Pearl", 5000)
                )));
    }
}
