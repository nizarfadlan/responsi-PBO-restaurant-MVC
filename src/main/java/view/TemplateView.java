package view;

import javax.swing.JOptionPane;

public class TemplateView {
    public void pesanResult(boolean hasil, String keterangan) {
        JOptionPane.showMessageDialog(null, "Status: " + (hasil ? "Berhasil" : "Gagal") + "\nAksi: " + keterangan, "Hasil " + keterangan, JOptionPane.INFORMATION_MESSAGE);
    }

    public void infoSaldo(String id, long uang, String keterangan) {
        JOptionPane.showMessageDialog(null, "ID User: " + id + "\nSaldo: " + uang + "\nAksi: " + keterangan, "Hasil " + keterangan, JOptionPane.INFORMATION_MESSAGE);
    }
}
