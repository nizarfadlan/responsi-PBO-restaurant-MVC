package view;

import controller.MenuController;

import javax.swing.JOptionPane;

public class MenuView {
    TemplateView templateView = new TemplateView();

    public void menu() {
        String[] menuAwal = {"Tambah makanan", "Edit makanan", "Hapus makanan", "Keluar"};
        int pilihanMenuAwal;

        MenuController controller = new MenuController();

        do {
            pilihanMenuAwal = JOptionPane.showOptionDialog(null, "Kelola menu makanan", "Program Restaurant Admin", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuAwal, menuAwal[0]);

            switch(pilihanMenuAwal) {
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
        } while (pilihanMenuAwal > 3);
    }

    @SuppressWarnings("unchecked")
    public void addMenu(MenuController controller) {
        String idMenu;
        boolean checkId;
        char lanjut = 'y';

        do {
            idMenu = JOptionPane.showInputDialog(null, "Masukkan id makanan");
            checkId = controller.checkID(idMenu);
            if (checkId) {
                JOptionPane.showMessageDialog(null, "ID makanan sudah ada", "Error" , JOptionPane.ERROR_MESSAGE);
                lanjut = JOptionPane.showInputDialog(null, "Apakah ingin lanjut (y/t) ?").charAt(0);
                if (Character.toLowerCase(lanjut) == 't') break;
            }
        } while (checkId);

        if (Character.toLowerCase(lanjut) == 'y') {
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
            boolean checkId;
            do {
                idMenu = JOptionPane.showInputDialog(null, "Masukkan id");
                checkId = controller.checkID(idMenu);
                if (!checkId)
                    JOptionPane.showMessageDialog(null, "ID makanan tidak ada", "Error", JOptionPane.ERROR_MESSAGE);
            } while (!checkId);

            long harga = Long.parseLong(JOptionPane.showInputDialog(null, "Masukkan harga"));

            boolean result = controller.editMenu(idMenu, harga);
            templateView.pesanResult(result, "Edit menu " + idMenu);
        }
    }

    public void deleteMenu(MenuController controller) {
        tampilkanMenu();

        int countData = controller.countData();

        if (countData > 0) {
            String idMenu;
            boolean checkId;
            do {
                idMenu = JOptionPane.showInputDialog(null, "Masukkan id");
                checkId = controller.checkID(idMenu);
                if (!checkId)
                    JOptionPane.showMessageDialog(null, "ID makanan tidak ada", "Error", JOptionPane.ERROR_MESSAGE);
            } while (!checkId);

            boolean result = controller.deleteMenu(idMenu);
            templateView.pesanResult(result, "Hapus menu");
        }
    }

    public void tampilkanMenu() {
        MenuController controller = new MenuController();
        String data = controller.getAllData();
        JOptionPane.showMessageDialog(null, data, "Menu Makanan", JOptionPane.INFORMATION_MESSAGE);
    }
}
