import javax.swing.JOptionPane;

import controller.HomeController;

public class Main {
    public static void main(String[] args) {
        int changeAccount;

        HomeController home = new HomeController();

        do {
            home.menuView();
            changeAccount = JOptionPane.showConfirmDialog(null, "Apakah ingin menggunakan program lagi?", "Konfirmasi program", JOptionPane.YES_NO_OPTION);
        } while(changeAccount == 0);
    }
}
