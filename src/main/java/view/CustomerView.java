package view;

import controller.CustomerController;

import javax.swing.JOptionPane;

public class CustomerView {
    TemplateView templateView = new TemplateView();

    public void menu() {
        String[] menuAwal = {"Tambah Customer", "Login Customer", "Keluar"};
        int pilihanMenuAwal;

        CustomerController controller = new CustomerController();

        do {
            pilihanMenuAwal = JOptionPane.showOptionDialog(null, "Fitur restaurant Admin", "Program Restaurant Admin", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuAwal, menuAwal[0]);

            switch(pilihanMenuAwal) {
                case 0:
                    addCustomer(controller);
                    break;
                case 1:
                    loginCustomer(controller);
                    break;
                case 2:
                    System.out.println("Menu utama");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Menu yang dipilih tidak ada", "Error" , JOptionPane.ERROR_MESSAGE);
            }
        } while (pilihanMenuAwal > 2);
    }

    public void addCustomer(CustomerController customer) {
        String idCustomer;
        boolean checkAndSetID;
        char lanjut = 'y';

        do {
            idCustomer = JOptionPane.showInputDialog(null, "Masukkan id");
            checkAndSetID = customer.setIdCustomer(idCustomer);
            if (!checkAndSetID) {
                JOptionPane.showMessageDialog(null, "ID customer sudah ada", "Error", JOptionPane.ERROR_MESSAGE);
                lanjut = JOptionPane.showInputDialog(null, "Apakah ingin lanjut (y/t) ?").charAt(0);
                if (Character.toLowerCase(lanjut) == 't') break;
            }
        } while (!checkAndSetID);

        if (Character.toLowerCase(lanjut) == 'y') {
            String nama = JOptionPane.showInputDialog(null, "Masukkan nama");
            String password = JOptionPane.showInputDialog(null, "Masukkan password");

            boolean result = customer.addCustomer(idCustomer, nama, password);

            templateView.pesanResult(result, "Pembuatan akun customer");
        }
    }

    public void loginCustomer(CustomerController customer) {
        boolean checkLogin;
        char lanjut = 'y';

        do {
            String idCustomer = JOptionPane.showInputDialog(null, "Masukkan id");
            String password = JOptionPane.showInputDialog(null, "Masukkan password");
            checkLogin = customer.loginCustomer(idCustomer, password);
            if (!checkLogin) {
                JOptionPane.showMessageDialog(null, "Login gagal", "Error", JOptionPane.ERROR_MESSAGE);
                lanjut = JOptionPane.showInputDialog(null, "Apakah ingin lanjut (y/t) ?").charAt(0);
                if (Character.toLowerCase(lanjut) == 't') break;
            }
        } while (!checkLogin);


    }
}
