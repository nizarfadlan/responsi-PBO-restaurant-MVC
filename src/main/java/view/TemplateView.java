package view;

import javax.swing.JOptionPane;

public class TemplateView {
    public void pesanResult(boolean hasil, String keterangan) {
        JOptionPane.showMessageDialog(null, "Status: " + (hasil ? "Berhasil" : "Gagal") + "\nAksi: " + keterangan, "Hasil " + keterangan, JOptionPane.INFORMATION_MESSAGE);
    }
}
