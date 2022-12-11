package controller;

import model.CustomerModel;
import org.json.simple.JSONObject;
import view.CustomerView;

public class CustomerController {
    private final CustomerModel model = new CustomerModel();
    private final CustomerView view = new CustomerView();
    private boolean login;

    public void pembeliMenuView() {
        view.menu();
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
        login = model.loginCustomer(id, password);
        return login;
    }
}
