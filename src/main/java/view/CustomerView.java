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

            if(pilihanMenuAwal == 0) {
                addCustomer(controller);
            } else if(pilihanMenuAwal == 1) {
                loginCustomer(controller);
            } else {
                JOptionPane.showMessageDialog(null, "Menu yang dipilih tidak ada", "Error" , JOptionPane.ERROR_MESSAGE);
            }
        } while (pilihanMenuAwal > 2);
    }

    public void addCustomer(CustomerController customer) {
        String idCustomer;
        boolean checkAndSetID;
        do {
            idCustomer = JOptionPane.showInputDialog(null, "Masukkan id");
            checkAndSetID = customer.setIdCustomer(idCustomer);
            if (!checkAndSetID)
                JOptionPane.showMessageDialog(null, "ID customer sudah ada", "Error", JOptionPane.ERROR_MESSAGE);
        } while (!checkAndSetID);

        String nama = JOptionPane.showInputDialog(null, "Masukkan nama");
        String password = JOptionPane.showInputDialog(null, "Masukkan password");

        boolean result = customer.addCustomer(idCustomer, nama, password);

        templateView.pesanResult(result, "Pembuatan akun customer");
    }

    public void loginCustomer(CustomerController customer) {
        boolean checkLogin;
        do {
            String idCustomer = JOptionPane.showInputDialog(null, "Masukkan id");
            String password = JOptionPane.showInputDialog(null, "Masukkan password");
            checkLogin = customer.loginCustomer(idCustomer, password);
            if (!checkLogin)
                JOptionPane.showMessageDialog(null, "Login gagal", "Error", JOptionPane.ERROR_MESSAGE);
        } while (!checkLogin);
    }
}
