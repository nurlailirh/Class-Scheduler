package model;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Liptia
 */
public class Model_Tagihan {

    private Model_Mahasiswa mahasiswa;
    private ArrayList<Model_Kompetensi> arrKelas;
    private int totalTagihan;
    private boolean isLunas;
    private String kodeTransaksi;
    private String tanggalBayar;

    public Model_Tagihan() {
    }

    public Model_Tagihan(Model_Mahasiswa mahasiswa, ArrayList<Model_Kompetensi> arrKelas) {
        this.mahasiswa = mahasiswa;
        this.arrKelas = arrKelas;
    }

    public Model_Tagihan(Model_Mahasiswa mahasiswa, ArrayList<Model_Kompetensi> arrKelas, int totalTagihan) {
        this.mahasiswa = mahasiswa;
        this.arrKelas = arrKelas;
        this.totalTagihan = totalTagihan;
    }

    public Model_Tagihan(Model_Mahasiswa mahasiswa, ArrayList<Model_Kompetensi> arrKelas, int totalTagihan, boolean isLunas, String kodeTransaksi, String tanggalBayar) {
        this.mahasiswa = mahasiswa;
        this.arrKelas = arrKelas;
        this.totalTagihan = totalTagihan;
        this.isLunas = isLunas;
        this.kodeTransaksi = kodeTransaksi;
        this.tanggalBayar = tanggalBayar;
    }

    public void generateTagihanMahasiswaToJson(ArrayList<String> arrKelasMahasiswa, String usernameMahasiswa) {
        Model_Mahasiswa mahasiswa = new Model_Mahasiswa();
        mahasiswa = mahasiswa.getMahasiswaFromJson(usernameMahasiswa);
        //untuk menampung hasil hitung total tagihan untuk mahasiswa
        int besarTagihan = 0;
        
        ArrayList<Model_Kompetensi> arrKelas = new ArrayList<Model_Kompetensi>();
        for (String idKompetensi : arrKelasMahasiswa) {
            Model_Kompetensi kelas = new Model_Kompetensi(idKompetensi);
            kelas = kelas.getKompetensiByID();
            arrKelas.add(kelas);

            //hitung tagihan
            if (kelas.getIsPraktikum().equals("M")) {
                besarTagihan += 500000 * kelas.getBobotKompetensi();
            } else {
                besarTagihan += 750000 * kelas.getBobotKompetensi();
            }
        }

        Model_Tagihan tagihan = new Model_Tagihan(mahasiswa, arrKelas);

       
        tagihan.setKelas(arrKelas);
        tagihan.setMahasiswa(mahasiswa);
        tagihan.setKodeTransaksi("");
        tagihan.setTanggalBayar("");
       
        if(arrKelasMahasiswa.isEmpty())
        {
            tagihan.setIsLunas(true);
            tagihan.setTotalTagihan(0);
        }
        else
        {
            tagihan.setIsLunas(false);
            tagihan.setTotalTagihan(besarTagihan);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!isUserExist(mahasiswa.getUsername())) {
                try {
                    JsonNode root = mapper.readTree(new File("dataJSON/Tagihan.json"));
                    ObjectNode data = mapper.createObjectNode();
                    data.put("mahasiswa", tagihan.getMahasiswa().getUsername());

                    ArrayNode arrayKelas = mapper.valueToTree(arrKelasMahasiswa);
                    data.putArray("kelas").addAll(arrayKelas);

                    data.put("totalTagihan", tagihan.getTotalTagihan());
                    data.put("isLunas", tagihan.getIsLunas());
                    data.put("kodeTransaksi", tagihan.getKodeTransaksi());
                    data.put("tanggalBayar", tagihan.getTanggalBayar());

                    ((ArrayNode) root).add(data);
                    mapper.writeValue(new File("dataJSON/Tagihan.json"), ((ArrayNode) root));
                } catch (IOException IE) {
                    JsonFactory jasonFactory = new JsonFactory();
                    try (JsonGenerator jsonGenerator = jasonFactory.createGenerator(new File("dataJSON/Tagihan.json"), JsonEncoding.UTF8)) {
                        jsonGenerator.writeStringField("mahasiswa", tagihan.getMahasiswa().getUsername());

                        jsonGenerator.writeArrayFieldStart("kelas"); //start kompetensi array
                        for (String data : arrKelasMahasiswa) {
                            jsonGenerator.writeString(data);
                        }
                        jsonGenerator.writeEndArray(); //closing kompetensi array

                        jsonGenerator.writeNumberField("totalTagihan", tagihan.getTotalTagihan());
                        jsonGenerator.writeBooleanField("isLunas", tagihan.getIsLunas());
                        jsonGenerator.writeStringField("kodeTransaksi", tagihan.getKodeTransaksi());
                        jsonGenerator.writeStringField("tanggalBayar", tagihan.getTanggalBayar());

                        jsonGenerator.writeEndObject(); //closing properties
                        jsonGenerator.flush();
                        jsonGenerator.close();
                    }
                }
            } else {
                System.out.println("Mahasiswa dengan username: " + mahasiswa.getUsername() + " sudah ada di data tagihan");
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserExist(String usernameMahasiswa) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Tagihan.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("mahasiswa").asText().equals(usernameMahasiswa)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public Model_Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Model_Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public ArrayList<Model_Kompetensi> getKelas() {
        return arrKelas;
    }

    public void setKelas(ArrayList<Model_Kompetensi> kelas) {
        this.arrKelas = kelas;
    }

    public int getTotalTagihan() {
        return totalTagihan;
    }

    public void setTotalTagihan(int totalTagihan) {
        this.totalTagihan = totalTagihan;
    }

    public boolean getIsLunas() {
        return isLunas;
    }

    public void setIsLunas(boolean isLunas) {
        this.isLunas = isLunas;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(String tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public boolean jsonFileSetIsLunas() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Tagihan.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("mahasiswa").asText().equals(this.getMahasiswa().getNim())) {

                        ((ObjectNode) root).put("isLunas", true);
                        this.setIsLunas(true);
                        mapper.writeValue(new File("dataJSON/Tagihan.json"), (ArrayNode) rootArray);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean jsonFileSetPembayaran(String kodeTransaksi, String tanggalBayar) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Tagihan.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("mahasiswa").asText().equals(this.getMahasiswa().getNim())) {
                        ((ObjectNode) root).put("kodeTransaksi", kodeTransaksi);
                        this.setKodeTransaksi(kodeTransaksi);
                        ((ObjectNode) root).put("tanggalBayar", tanggalBayar);
                        this.setTanggalBayar(tanggalBayar);
                        mapper.writeValue(new File("dataJSON/Tagihan.json"), (ArrayNode) rootArray);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
