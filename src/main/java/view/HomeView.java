package view;

import javax.swing.JOptionPane;

import controller.MenuController;
import controller.CustomerController;

public class HomeView {
    public void menu(CustomerController customer, MenuController menu) {
        String[] listMenu = {"Menu", "Login", "Keluar"};
        int choice;

        do {
            choice = JOptionPane.showOptionDialog(null, "Fitur restaurant", "Program Restaurant", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, listMenu, listMenu[0]);

            switch(choice) {
                case 0:
                    menu.menuView();
                    break;
                case 1:
                    customer.pembeliMenuView();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Terima kasih telah menggunakan sistem kami", "Terima kasih", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Menu yang dipilih tidak ada", "Error" , JOptionPane.ERROR_MESSAGE);
            }
        } while (choice > 2);
    }
}
