package vn.zalopay.freshers.poscli;

import vn.zalopay.freshers.poscli.controllers.HomeController;
import vn.zalopay.freshers.poscli.domains.OrderManager;

public class Main {
    public static void main(String[] args) {
        HomeController homeController = new HomeController(OrderManager.getInstance());
        homeController.run();
    }
}
