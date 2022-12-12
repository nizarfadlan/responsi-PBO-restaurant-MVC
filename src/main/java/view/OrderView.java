package view;

import controller.CustomerController;
import controller.MenuController;
import controller.OrderController;

import javax.swing.JOptionPane;

public class OrderView {
    TemplateView templateView = new TemplateView();

    public void menu(String id) {
        String[] menuAwal = {"Tampilkan Pesanan", "Tambah Pesanan", "Edit Pesanan", "Hapus Pesanan", "Selesai Pesanan", "Keluar"};
        int pilihanMenuAwal;

        MenuController menuController = new MenuController();
        OrderController controller = new OrderController();
        CustomerController customerController = new CustomerController();

        do {
            pilihanMenuAwal = JOptionPane.showOptionDialog(null, "Fitur restaurant", "Program Restaurant", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuAwal, menuAwal[0]);

            switch(pilihanMenuAwal) {
                case 0:
                    tampilkanOrderan(id, controller);
                    break;
                case 1:
                    addPesanan(id, controller, menuController);
                    break;
                case 2:
                    editMenu(id, controller);
                    break;
                case 3:
                    deleteOrder(id, controller);
                    break;
                case 4:
                    checkOut(id, controller, customerController);
                    break;
                case 5:
                    System.out.println("Menu customer");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Menu yang dipilih tidak ada", "Error" , JOptionPane.ERROR_MESSAGE);
            }
        } while (pilihanMenuAwal != 5);
    }

    public void addPesanan(String id, OrderController controller, MenuController menuController) {
        String idMenu;
        MenuView menuView = new MenuView();

        int lanjutPesanan;
        do {
            int lanjut = 0;
            do {
                menuView.tampilkanMenu();
                idMenu = JOptionPane.showInputDialog(null, "Masukkan id makanan");
                if (!menuController.checkID(idMenu)) {
                    JOptionPane.showMessageDialog(null, "ID makanan tidak ada", "Error", JOptionPane.ERROR_MESSAGE);
                    lanjut = JOptionPane.showConfirmDialog(null, "Apakah ingin melanjutkan tambah pesanan?", "Konfirmasi tambah pesanan", JOptionPane.YES_NO_OPTION);
                    if (lanjut == 1) break;
                } else {
                    break;
                }
            } while (true);

            if (lanjut == 0) {
                int jumlah = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan jumlah makanan"));

                boolean result = controller.addOrder(id, idMenu, jumlah);

                templateView.pesanResult(result, "Tambah pesanan");
            }
            lanjutPesanan = JOptionPane.showConfirmDialog(null, "Apakah ingin menambah pesanan?", "Konfirmasi tambah pesanan", JOptionPane.YES_NO_OPTION);
            if (lanjutPesanan == 1) break;
        } while(lanjutPesanan == 0);
    }

    public void editMenu(String id, OrderController controller) {
        tampilkanOrderan(id, controller);

        int countData = controller.countData(id);

        if (countData > 0) {
            String idOrder;
            int lanjut = 0;

            do {
                idOrder = JOptionPane.showInputDialog(null, "Masukkan id orderan");
                if (!controller.checkIDOrder(idOrder)) {
                    JOptionPane.showMessageDialog(null, "ID order tidak ada", "Error", JOptionPane.ERROR_MESSAGE);
                    lanjut = JOptionPane.showConfirmDialog(null, "Apakah ingin melanjutkan mengubah orderan?", "Konfirmasi mengubah order", JOptionPane.YES_NO_OPTION);
                    if (lanjut == 1) break;
                } else {
                    break;
                }
            } while (true);

            if (lanjut == 0) {
                int jumlah = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukkan jumlah baru"));

                boolean result = controller.editOrder(idOrder, jumlah);
                templateView.pesanResult(result, "Edit order " + idOrder);
            }
        }
    }

    public void deleteOrder(String id, OrderController controller) {
        tampilkanOrderan(id, controller);

        int countData = controller.countData(id);

        if (countData > 0) {
            String idOrder;
            int lanjut = 0;

            do {
                idOrder = JOptionPane.showInputDialog(null, "Masukkan id");
                if (!controller.checkIDOrder(idOrder)) {
                    JOptionPane.showMessageDialog(null, "ID orderan tidak ada", "Error", JOptionPane.ERROR_MESSAGE);
                    lanjut = JOptionPane.showConfirmDialog(null, "Apakah ingin melanjutkan menghapus orderan?", "Konfirmasi menghapus order", JOptionPane.YES_NO_OPTION);

                    if (lanjut == 1) break;
                } else {
                    break;
                }
            } while (true);

            if (lanjut == 0) {
                boolean result = controller.deleteOrder(idOrder);
                templateView.pesanResult(result, "Hapus orderan");
            }
        }
    }

    public void checkOut(String id, OrderController controller, CustomerController customerController) {
        tampilkanOrderan(id, controller);
        int countData = controller.countData(id);

        if (countData > 0) {
            int lanjut = JOptionPane.showConfirmDialog(null, "Apakah ingin melanjutkan checkout orderan?", "Konfirmasi checkout", JOptionPane.YES_NO_OPTION);
            if (lanjut == 0) {
                long totalOrder = controller.getTotalOrder(id);
                long saldoCustomer = customerController.getSaldo(id);

                if (saldoCustomer >= totalOrder) {
                    boolean result = controller.checkOut(id, totalOrder, customerController);
                    templateView.pesanResult(result, "Checkout orderan");
                    templateView.infoSaldo(id, customerController.getSaldo(id), "Sisa Saldo");
                } else {
                    JOptionPane.showMessageDialog(null, "Saldo kurang, silahkan tambah saldo terlebih dahulu");
                }
            }
        }
    }

    public void tampilkanOrderan(String id, OrderController controller) {
        String data = controller.getAllOrderCustomer(id);
        JOptionPane.showMessageDialog(null, data, "Orderan", JOptionPane.INFORMATION_MESSAGE);
    }
}
