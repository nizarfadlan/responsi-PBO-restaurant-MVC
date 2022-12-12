package controller;

import model.CustomerModel;
import org.json.simple.JSONObject;
import view.CustomerView;

public class CustomerController {
    private final CustomerModel model = new CustomerModel();
    private final CustomerView view = new CustomerView();
    private final OrderController orderController = new OrderController();

    public void pembeliMenuView() {
        view.menu();
    }

    public void pembeliMenuView(String id) {
        view.menuAfterLogin(id, orderController);
    }

    @SuppressWarnings("unchecked")
    public boolean addCustomer(String idPengguna, String nama, String password) {
        JSONObject data = new JSONObject();
        data.put("id", idPengguna);
        data.put("nama", nama);
        data.put("password", password);
        data.put("saldo", 0);

        return model.addData(data);
    }

    public boolean setIdCustomer(String id) {
        return model.setID(id);
    }

    public boolean loginCustomer(String id, String password) {
        return model.loginCustomer(id, password);
    }

    public long depositSaldo(String id, long uang) {
        return model.setSaldo(1, id, uang);
    }

    public long saldoForBuy(String id, long uang) {
        return model.setSaldo(2, id, uang);
    }

    public long getSaldo(String id) {
        return model.getSaldo(id);
    }
}
