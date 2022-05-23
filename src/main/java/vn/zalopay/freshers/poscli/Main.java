package vn.zalopay.freshers.poscli;

import vn.zalopay.freshers.poscli.controllers.HomeController;

public class Main {
    public static void main(String[] args) {
        HomeController homeController = new HomeController();
        homeController.run();
    }
}
