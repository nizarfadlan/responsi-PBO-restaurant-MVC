package view;

import javax.swing.JOptionPane;

import controller.MenuController;
import controller.CustomerController;

public class HomeView {
    public void menu(CustomerController customer, MenuController menu) {
        String[] menuAwal = {"Menu", "Login", "Keluar"};
        int pilihanMenuAwal;

        do {
            pilihanMenuAwal = JOptionPane.showOptionDialog(null, "Fitur restaurant", "Program Restaurant", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuAwal, menuAwal[0]);

            if(pilihanMenuAwal == 0) {
                menu.menuView();
            } else if(pilihanMenuAwal == 1) {
                customer.pembeliMenuView();
            } else if(pilihanMenuAwal == 2) {
                JOptionPane.showMessageDialog(null, "Terima kasih telah menggunakan sistem kami", "Terima kasih", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Menu yang dipilih tidak ada", "Error" , JOptionPane.ERROR_MESSAGE);
            }
        } while (pilihanMenuAwal > 2);
    }
}
