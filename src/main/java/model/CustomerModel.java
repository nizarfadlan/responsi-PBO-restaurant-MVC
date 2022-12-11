package model;

import core.Restaurant;
import core.FileSystemCore;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CustomerModel {
    private String idCustomer;

    private final FileSystemCore file = new FileSystemCore("./customer.json");
    private final Restaurant restaurant = new Restaurant();

    public boolean addData(JSONObject data) {
        if (!idCustomer.isEmpty()) {
            try {
                return restaurant.fillData(data, file);
            } catch (IOException | ParseException iop) {
                System.out.println("Error: " + iop.getMessage());
            }
        }

        return false;
    }

    private boolean checkId(String idPengguna) {
        return restaurant.findDataBoolean("id", idPengguna, file);
    }

    public boolean setID(String id) {
        if (!checkId(id)) {
            this.idCustomer = id;

            return true;
        }

        return false;
    }

    public long setSaldo(int tipe, String id, long saldo) {
        try {
            return restaurant.changeSaldo(tipe, id, saldo, file);
        } catch (IOException | ParseException iop) {
            System.out.println("Error: " + iop.getMessage());
        }

        return 0;
    }

    public boolean loginCustomer(String id, String password) {
        return restaurant.checkPassword(id, password, file);
    }
}
