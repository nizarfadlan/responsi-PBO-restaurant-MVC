import javax.swing.JOptionPane;

import controller.HomeController;

public class Main {
    public static void main(String[] args) {
        int gantiKartu;

        HomeController home = new HomeController();

        do {
            home.menuView();
            gantiKartu = JOptionPane.showConfirmDialog(null, "Apakah ingin menggunakan program lagi?", "Konfirmasi program", JOptionPane.YES_NO_OPTION);
        } while(gantiKartu == 0);
    }
}
