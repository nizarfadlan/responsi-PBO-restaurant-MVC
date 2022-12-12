package view;

import controller.CustomerController;
import controller.OrderController;

import javax.swing.JOptionPane;

public class CustomerView {
    TemplateView templateView = new TemplateView();

    public void menu() {
        String[] menuAwal = {"Tambah Customer", "Login Customer", "Keluar"};
        int pilihanMenuAwal;

        CustomerController controller = new CustomerController();

        do {
            pilihanMenuAwal = JOptionPane.showOptionDialog(null, "Fitur restaurant", "Program Restaurant", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuAwal, menuAwal[0]);

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
        int lanjut = 0;

        do {
            idCustomer = JOptionPane.showInputDialog(null, "Masukkan id");
            if (!customer.setIdCustomer(idCustomer)) {
                JOptionPane.showMessageDialog(null, "ID customer sudah ada", "Error", JOptionPane.ERROR_MESSAGE);
                lanjut = JOptionPane.showConfirmDialog(null, "Apakah ingin melanjutkan menambah customer?", "Konfirmasi tambah customer", JOptionPane.YES_NO_OPTION);
                if (lanjut == 1) break;
            } else {
                break;
            }
        } while (true);

        if (lanjut == 0) {
            String nama = JOptionPane.showInputDialog(null, "Masukkan nama");
            String password = JOptionPane.showInputDialog(null, "Masukkan password");

            boolean result = customer.addCustomer(idCustomer, nama, password);

            templateView.pesanResult(result, "Pembuatan akun customer");
        }
    }

    public void loginCustomer(CustomerController customer) {
        int lanjut = 0;
        String idCustomer;

        do {
            idCustomer = JOptionPane.showInputDialog(null, "Masukkan id");
            String password = JOptionPane.showInputDialog(null, "Masukkan password");
            if (!customer.loginCustomer(idCustomer, password)) {
                JOptionPane.showMessageDialog(null, "Login gagal", "Error", JOptionPane.ERROR_MESSAGE);
                lanjut = JOptionPane.showConfirmDialog(null, "Apakah ingin melanjutkan login customer?", "Konfirmasi login customer", JOptionPane.YES_NO_OPTION);
                if (lanjut == 1) break;
            } else {
                break;
            }
        } while (true);

        if (lanjut == 0) {
            customer.pembeliMenuView(idCustomer);
        }
    }

    public void menuAfterLogin(String id, OrderController orderController) {
        String[] menuAwal = {"Tampilkan Saldo", "Tambah Saldo", "Order", "Keluar"};
        int pilihanMenuAwal;

        CustomerController controller = new CustomerController();

        do {
            pilihanMenuAwal = JOptionPane.showOptionDialog(null, "Fitur User", "Program Restaurant", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuAwal, menuAwal[0]);

            switch(pilihanMenuAwal) {
                case 0:
                    templateView.infoSaldo(id, controller.getSaldo(id), "Info Saldo");
                    break;
                case 1:
                    tambahSaldo(id, controller);
                    break;
                case 2:
                    orderController.orderView(id);
                    break;
                case 3:
                    System.out.println("Menu utama");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Menu yang dipilih tidak ada", "Error" , JOptionPane.ERROR_MESSAGE);
            }
        } while (pilihanMenuAwal != 3);
    }

    public void tambahSaldo(String id, CustomerController controller) {
        long saldo = Long.parseLong(JOptionPane.showInputDialog(null, "Masukkan uang deposit"));
        long saldoAkun = controller.depositSaldo(id, saldo);

        templateView.infoSaldo(id, saldoAkun, "Deposit saldo");
    }
}
