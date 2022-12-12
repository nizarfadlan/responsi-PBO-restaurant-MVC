package controller;

import model.OrderModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import view.OrderView;

public class OrderController {
    private final OrderView view = new OrderView();
    private final OrderModel model = new OrderModel();
    private final MenuController menuController = new MenuController();

    public void orderView(String id) {
        view.menu(id);
    }

    @SuppressWarnings("unchecked")
    public boolean addOrder(String idCustomer, String idMenu, int jumlah) {
        String id = idCustomer + idMenu;
        if (!model.checkIdCustomerOrder(id)) {
            JSONObject data = new JSONObject();
            data.put("id", id);
            data.put("customer", idCustomer);
            data.put("menu", idMenu);
            data.put("jumlah", jumlah);

            return model.addData(data);
        } else {
            return model.changeAmount(2, id, jumlah);
        }
    }

    public boolean checkIDOrder(String id) {
        return model.checkIdCustomerOrder(id);
    }

    public String getAllOrderCustomer(String id) {
        JSONArray data = model.getAllOrderCustomer(id);
        StringBuilder menu = new StringBuilder();

        if (data.size() > 0) {
            long total = 0;
            for (Object datum : data) {
                JSONObject dataParse = (JSONObject) datum;
                long subtotal = menuController.getPrice((String) dataParse.get("menu")) * (long) dataParse.get("jumlah");
                total += subtotal;
                String namaMenu = menuController.getNama((String) dataParse.get("menu"));

                menu.append("[").append(dataParse.get("id")).append("]  ").append(namaMenu).append(":  ").append(dataParse.get("jumlah")).append("  | ").append(subtotal);
                if (data.size() > 1) menu.append("\n");
            }
            menu.append("-----------------------------\n");
            menu.append("Total:           ").append(total);
        } else {
            menu.append("Orderan kosong");
        }

        return menu.toString();
    }

    public int countData(String id) {
        JSONArray data = model.getAllOrderCustomer(id);
        return data.size();
    }

    public boolean editOrder(String id, int jumlah) {
        return model.changeAmount(1, id, jumlah);
    }

    public boolean deleteOrder(String id) {
        return model.deleteOrder(id);
    }

    public long getTotalOrder(String idCustomer) {
        JSONArray data = model.getAllOrderCustomer(idCustomer);
        long total = 0;
        for (Object datum : data) {
            JSONObject dataParse = (JSONObject) datum;
            long subtotal = menuController.getPrice((String) dataParse.get("menu")) * (long) dataParse.get("jumlah");
            total += subtotal;
        }

        return total;
    }

    public boolean checkOut(String idCustomer, long total, CustomerController customerController) {
        boolean result = model.deleteAllOrderUseCustomer(idCustomer);
        customerController.saldoForBuy(idCustomer, total);

        return result;
    }
}
