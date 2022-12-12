package view;

import controller.MenuController;

import javax.swing.JOptionPane;

public class MenuView {
    TemplateView templateView = new TemplateView();

    public void menu() {
        String[] menuAwal = {"Tambah makanan", "Edit makanan", "Hapus makanan", "Keluar"};
        int choice;

        MenuController controller = new MenuController();

        do {
            choice = JOptionPane.showOptionDialog(null, "Kelola menu makanan", "Program Restaurant Admin", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuAwal, menuAwal[0]);

            switch(choice) {
                case 0:
                    addMenu(controller);
                    break;
                case 1:
                    editMenu(controller);
                    break;
                case 2:
                    deleteMenu(controller);
                    break;
                case 3:
                    System.out.println("Menu utama");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Menu yang dipilih tidak ada", "Error" , JOptionPane.ERROR_MESSAGE);
            }
        } while (choice != 3);
    }

    public void addMenu(MenuController controller) {
        String idMenu;
        int lanjut = 0;

        do {
            idMenu = JOptionPane.showInputDialog(null, "Masukkan id makanan");
            if (controller.checkID(idMenu)) {
                JOptionPane.showMessageDialog(null, "ID makanan sudah ada", "Error" , JOptionPane.ERROR_MESSAGE);
                lanjut = JOptionPane.showConfirmDialog(null, "Apakah ingin melanjutkan menambah menu?", "Konfirmasi tambah menu", JOptionPane.YES_NO_OPTION);
                if (lanjut == 1) break;
            } else {
                break;
            }
        } while (true);

        if (lanjut == 0) {
            String nama = JOptionPane.showInputDialog(null, "Masukkan nama makanan");
            long harga = Long.parseLong(JOptionPane.showInputDialog(null, "Masukkan harga makanan"));

            boolean result = controller.addMenu(idMenu, nama, harga);

            templateView.pesanResult(result, "Tambah makanan");
        }
    }

    public void editMenu(MenuController controller) {
        tampilkanMenu();

        int countData = controller.countData();

        if (countData > 0) {
            String idMenu;
            int lanjut = 0;

            do {
                idMenu = JOptionPane.showInputDialog(null, "Masukkan id");
                if (!controller.checkID(idMenu)) {
                    JOptionPane.showMessageDialog(null, "ID makanan tidak ada", "Error", JOptionPane.ERROR_MESSAGE);
                    lanjut = JOptionPane.showConfirmDialog(null, "Apakah ingin melanjutkan mengubah menu?", "Konfirmasi mengubah menu", JOptionPane.YES_NO_OPTION);
                    if (lanjut == 1) break;
                } else {
                    break;
                }
            } while (true);

            if (lanjut == 0) {
                long harga = Long.parseLong(JOptionPane.showInputDialog(null, "Masukkan harga"));

                boolean result = controller.editMenu(idMenu, harga);
                templateView.pesanResult(result, "Edit menu " + idMenu);
            }
        }
    }

    public void deleteMenu(MenuController controller) {
        tampilkanMenu();

        int countData = controller.countData();

        if (countData > 0) {
            String idMenu;
            int lanjut = 0;

            do {
                idMenu = JOptionPane.showInputDialog(null, "Masukkan id");
                if (!controller.checkID(idMenu)) {
                    JOptionPane.showMessageDialog(null, "ID makanan tidak ada", "Error", JOptionPane.ERROR_MESSAGE);
                    lanjut = JOptionPane.showConfirmDialog(null, "Apakah ingin melanjutkan menghapus menu?", "Konfirmasi menghapus menu", JOptionPane.YES_NO_OPTION);

                    if (lanjut == 1) break;
                } else {
                    break;
                }
            } while (true);

            if (lanjut == 0) {
                boolean result = controller.deleteMenu(idMenu);
                templateView.pesanResult(result, "Hapus menu");
            }
        }
    }

    public void tampilkanMenu() {
        MenuController controller = new MenuController();
        String data = controller.getAllData();
        JOptionPane.showMessageDialog(null, data, "Menu Makanan", JOptionPane.INFORMATION_MESSAGE);
    }
}
