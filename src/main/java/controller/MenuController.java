package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.MenuModel;
import view.MenuView;

public class MenuController {
    private final MenuView view = new MenuView();
    private final MenuModel model = new MenuModel();
    public void menuView() {
        view.menu();
    }

    @SuppressWarnings("unchecked")
    public boolean addMenu(String id, String nama, long harga) {
        JSONObject data = new JSONObject();
        data.put("id", id);
        data.put("nama", nama);
        data.put("harga", harga);

        return model.addData(data);
    }

    public boolean checkID(String id) {
        return model.checkIdMenu(id);
    }

    public String getAllData() {
        JSONArray data = model.getAllMenu();
        StringBuilder menu = new StringBuilder();

        if (data.size() > 0) {
            for (Object datum : data) {
                JSONObject dataParse = (JSONObject) datum;
                menu.append("[").append(dataParse.get("id")).append("]     ").append(dataParse.get("nama")).append(":  ").append(dataParse.get("harga"));
                if (data.size() > 1) menu.append("\n");
            }
        } else {
            menu.append("Menu makanan kosong");
        }

        return menu.toString();
    }

    public boolean editMenu(String id, long harga) {
        return model.changePriceMenu(id, harga);
    }

    public boolean deleteMenu(String id) {
        return model.deleteMenu(id);
    }

    public int countData() {
        JSONArray data = model.getAllMenu();
        return data.size();
    }

    public long getPrice(String id) {
        return model.getPriceMenu(id);
    }

    public String getNama(String id) {
        return model.getNamaMenu(id);
    }
}
