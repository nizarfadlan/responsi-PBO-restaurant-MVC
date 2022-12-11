package core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystemCore {
    private final String pathData;

    public FileSystemCore(String path) {
        this.pathData = path;
    }

    public JSONObject getDataFile() {
        JSONParser parserData = new JSONParser();
        // Membaca file json dan parser setelah itu casting map dan ke object dataFile
        JSONObject dataFile;

        try {
            File file = file();
            if (!file.exists()) {
                boolean create = file.createNewFile();
                System.out.println("File " + (create ? "berhasil" : "gagal") + " dibuat");
            }

            dataFile = (JSONObject) parserData.parse(new FileReader(pathData));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return dataFile;
    }

    private File file() {
        return new File(pathData);
    }

    public Boolean checkFile() {
        return file().exists();
    }

    private Boolean fileWriter(JSONObject data) throws IOException {
        // Untuk menulis file jika tidak ada file maka akan dituliskan
        FileWriter file = new FileWriter(pathData);
        try {
            // Menulis data di file
            file.write(data.toJSONString());
            return true;
        } catch (IOException e) { // Jika terjadi error pada Input dan Ouput penulisan file maka akan langsung kesini
            System.out.println("General I/O exception: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally { // Jika selesai maka file tadi akan di flush dan close
            file.flush(); // Menghapus semua simpanan kata dari permintaan penulisan
            file.close(); // Menutup file
        }
    }

    @SuppressWarnings("unchecked") // Hanya untuk menghilangkan warning saja
    public Boolean addDataFile(JSONArray data) throws IOException {
        // throws untuk menunjukkan bahwa jika terjadi kesalahan maka akan bisa dilempar secara tertentu
        // sedangkan ini kan ditambah IOException maka jika ada kesalahan Input dan Output maka akan dilempar

        // Membuat variabel untuk menampung semua array object
        JSONObject dataNew = new JSONObject();
        // Menambah data array object ke kunci utama atau data utama
        dataNew.put("data", data);

        return fileWriter(dataNew);
    }

    @SuppressWarnings("unchecked") // Hanya untuk menghilangkan warning saja
    public Boolean updateDataFile(int mode, JSONArray dataAppend, JSONObject newData) throws IOException {
        // throws untuk menunjukkan bahwa jika terjadi kesalahan maka akan bisa dilempar secara tertentu
        // sedangkan ini kan ditambah IOException maka jika ada kesalahan Input dan Output maka akan dilempar
        // sedangkan ini kan ditambah ParseException maka jika ada kesalahan pada parse maka akan dilempar

        // mode 1 update file untuk menambah 1 data
        // mode 2 update file untuk update object data

        // Membuat variabel untuk menampung semua array object baru
        JSONObject dataArray = new JSONObject();
        if (mode == 1) {
            // Ambil data didalam json key data dan casting ke JSONArray
            JSONArray parseDataOld = (JSONArray) getDataFile().get("data");

            // Buat object dataNew
            JSONArray dataNew = new JSONArray();
            // Tambah semua data old yang udah diparse/casting ke dataNew
            dataNew.addAll(parseDataOld);
            // Tambah data baru ke dataNew
            dataNew.add(newData);

            dataArray.put("data", dataNew);
        } else if (mode == 2) {
            dataArray.put("data", dataAppend);
        }

        return fileWriter(dataArray);
    }
}
