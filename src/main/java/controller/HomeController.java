package controller;

import view.HomeView;

public class HomeController {
    private final HomeView view = new HomeView();
    private final CustomerController pembeliController = new CustomerController();
    private final MenuController adminController = new MenuController();

    public void menuView() {
        view.menu(pembeliController, adminController);
    }
}
