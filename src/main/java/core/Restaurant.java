package core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Objects;

public class Restaurant {
    public String findDataString(String keySuper, String dataKey, String key, FileSystemCore file) {
        if (file.checkFile()) {
            JSONArray parseData = (JSONArray) file.getDataFile().get("data");
            for (Object parseDatum : parseData) {
                JSONObject data = (JSONObject) parseDatum;
                if (Objects.equals(data.get(keySuper), dataKey)) {
                    return (String) data.get(key);
                }
            }
        }

        return "";
    }

    public Boolean findDataBoolean(String keySuper, String dataKey, FileSystemCore file) {
        if (file.checkFile()) {
            JSONArray parseData = (JSONArray) file.getDataFile().get("data");
            for (Object parseDatum : parseData) {
                JSONObject data = (JSONObject) parseDatum;
                if (Objects.equals(data.get(keySuper), dataKey)) {
                    return true;
                }
            }
        }

        return false;
    }

    public long findDataLong(String keySuper, String dataKey, String key, FileSystemCore file) {
        if (file.checkFile()) {
            JSONArray parseData = (JSONArray) file.getDataFile().get("data");
            for (Object parseDatum : parseData) {
                JSONObject data = (JSONObject) parseDatum;
                if (Objects.equals(data.get(keySuper), dataKey)) {
                    return (long) data.get(key);
                }
            }
        }

        return 0;
    }

    @SuppressWarnings("unchecked")
    public boolean fillData(JSONObject data, FileSystemCore file) throws IOException, ParseException {
        // Membuat array object untuk menampung data rekening
        JSONArray arrayData = new JSONArray();

        if (file.checkFile()) {
            return file.updateDataFile(1, null, data);
        } else {
            // Menambah data rekening ke array object
            arrayData.add(data);

            return file.addDataFile(arrayData);
        }
    }

    public boolean checkPassword(String id, String password, FileSystemCore file) {
        if (file.checkFile()) {
            JSONArray parseData = (JSONArray) file.getDataFile().get("data");
            for (Object parseDatum : parseData) {
                JSONObject data = (JSONObject) parseDatum;
                if (Objects.equals(data.get("id"), id) && Objects.equals(data.get("password"), password)) {
                    return true;
                }
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public long changeSaldo(int tipe, String id, long uang, FileSystemCore file) throws IOException, ParseException {
        long saldo = 0;
        JSONArray parseData = (JSONArray) file.getDataFile().get("data");
        for (Object parseDatum : parseData) {
            JSONObject data = (JSONObject) parseDatum;
            if (Objects.equals(data.get("id"), id)) {
                if (tipe == 1) {
                    data.put("saldo", (long) data.get("saldo") + uang);
                } else if (tipe == 2) {
                    data.put("saldo", (long) data.get("saldo") - uang);
                }

                saldo = (long) data.get("saldo");
                break;
            }
        }

        file.updateDataFile(2, parseData, null);

        return saldo;
    }

    public JSONArray getAllData(FileSystemCore file) throws IOException, ParseException {
        return (JSONArray) file.getDataFile().get("data");
    }

    @SuppressWarnings("unchecked")
    public boolean changePrice(String id, long harga, FileSystemCore file) throws IOException, ParseException {
        JSONArray parseData = (JSONArray) file.getDataFile().get("data");
        for (Object parseDatum : parseData) {
            JSONObject data = (JSONObject) parseDatum;
            if (Objects.equals(data.get("id"), id)) {
                data.put("harga", harga);
                break;
            }
        }

        return file.updateDataFile(2, parseData, null);
    }

    @SuppressWarnings("unchecked")
    public boolean deleteData(String id, FileSystemCore file) throws IOException, ParseException {
        JSONArray newData = new JSONArray();
        JSONArray parseData = (JSONArray) file.getDataFile().get("data");
        for (Object parseDatum : parseData) {
            JSONObject data = (JSONObject) parseDatum;
            if (!Objects.equals(data.get("id"), id)) {
                newData.add(data);
            }
        }

        return file.updateDataFile(2, newData, null);
    }
}
