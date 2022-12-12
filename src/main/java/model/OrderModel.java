package model;

import core.FileSystemCore;
import core.Restaurant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class OrderModel {
    private final FileSystemCore file = new FileSystemCore("./order.json");
    private final Restaurant restaurant = new Restaurant();

    public boolean addData(JSONObject data) {
        try {
            return restaurant.fillData(data, file);
        } catch (IOException | ParseException iop) {
            System.out.println("Error: " + iop.getMessage());
        }

        return false;
    }

    public boolean checkIdCustomerOrder(String id) {
        return restaurant.findDataBoolean("id", id, file);
    }

    public JSONArray getAllOrderCustomer(String idCustomer) {
        return restaurant.getAllDataID("customer", idCustomer, file);
    }

    public boolean changeAmount(int tipe, String id, int amout) {
        try {
            return restaurant.changeAmount(tipe, id, amout, file);
        } catch (IOException io) {
            System.out.println("Error: " + io.getMessage());
        }

        return false;
    }

    public boolean deleteOrder(String id) {
        try {
            return restaurant.deleteData(id, file);
        } catch (IOException | ParseException iop) {
            System.out.println("Error: " + iop.getMessage());
        }

        return false;
    }

    public boolean deleteAllOrderUseCustomer(String idCustomer) {
        try {
            return restaurant.deleteAllData("customer", idCustomer, file);
        } catch (IOException iop) {
            System.out.println("Error: " + iop.getMessage());
        }

        return false;
    }
}
