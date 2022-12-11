package model;

import core.FileSystemCore;
import core.Restaurant;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MenuModel {

    private final FileSystemCore file = new FileSystemCore("./menu.json");
    private final Restaurant restaurant = new Restaurant();

    public boolean addData(JSONObject data) {
        try {
            return restaurant.fillData(data, file);
        } catch (IOException | ParseException iop) {
            System.out.println("Error: " + iop.getMessage());
        }

        return false;
    }

    public boolean checkIdMenu(String id) {
        return restaurant.findDataBoolean("id", id, file);
    }

    public JSONArray getAllMenu() {
        JSONArray data = new JSONArray();
        try {
            data = restaurant.getAllData(file);
        } catch (IOException | ParseException iop) {
            System.out.println("Error: " + iop.getMessage());
        }

        return data;
    }

    public boolean changePriceMenu(String id, long harga) {
        try {
            return restaurant.changePrice(id, harga, file);
        } catch (IOException | ParseException iop) {
            System.out.println("Error: " + iop.getMessage());
        }

        return false;
    }

    public boolean deleteMenu(String id) {
        try {
            return restaurant.deleteData(id, file);
        } catch (IOException | ParseException iop) {
            System.out.println("Error: " + iop.getMessage());
        }

        return false;
    }
}
