package model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Model_Dosen extends Model_User {

    private String nip;
    private ArrayList<Model_Kompetensi> kompetensi;
    private ArrayList<String> hariLibur;

    public Model_Dosen() {
        super();
        this.nip = "";
        this.setRole(2);
        this.kompetensi = new ArrayList<Model_Kompetensi>();
        this.hariLibur = new ArrayList<String>();
    }

    public Model_Dosen(String nama, String nip) {
        super(nama);
        this.nip = nip;
        this.setRole(2);
        this.kompetensi = new ArrayList<Model_Kompetensi>();
        this.hariLibur = new ArrayList<String>();
    }

    public Model_Dosen(String nama, String nip, ArrayList<Model_Kompetensi> kompetensi) {
        super(nama);
        this.nip = nip;
        this.setRole(2);
        this.kompetensi = kompetensi;
        this.hariLibur = new ArrayList<String>();
    }

    public String getNip() {
        return this.nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @JsonGetter("kompetensi")
    public ArrayList<Model_Kompetensi> getKompetensi() {
        return this.kompetensi;
    }

    @JsonSetter("kompetensi")
    public void setKompetensi(ArrayList<Model_Kompetensi> kompetensi) {
        this.kompetensi = kompetensi;
    }

    public ArrayList<String> getHariLibur() {
        return hariLibur;
    }

    public void setHariLibur(ArrayList<String> hariLibur) {
        this.hariLibur = hariLibur;
    }

    public boolean jsonFileSetNip(String nip) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("username").asText().equals(this.getUsername())) {

                        ((ObjectNode) root).put("nip", nip);
                        this.setNip(nip);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public Model_Dosen getDosenFromJson(String username) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("username").asText().equals(username) && root.path("role").asInt() == 2) {
                        Model_Dosen dosen = new Model_Dosen();
                        dosen.setUsername(root.path("username").asText());
                        dosen.setNama(root.path("nama").asText());
                        dosen.setNip(root.path("nip").asText());
                        dosen.setPassword(root.path("password").asText());
                        JsonNode kompetensiNode = root.path("kompetensi");
                        Iterator<JsonNode> elements = kompetensiNode.elements();
                        ArrayList<Model_Kompetensi> listOfKompetensi = new ArrayList<Model_Kompetensi>();
                        while (elements.hasNext()) {
                            JsonNode item = elements.next();
                            Model_Kompetensi k = new Model_Kompetensi(item.asText());
                            k = k.getKompetensiByID();
                            listOfKompetensi.add(k);
                        }
                        dosen.setKompetensi(listOfKompetensi);

                        JsonNode hariLiburNode = root.path("hariLibur");
                        Iterator<JsonNode> hariLiburElements = hariLiburNode.elements();
                        ArrayList<String> listOfHariLibur = new ArrayList<String>();
                        while (hariLiburElements.hasNext()) {
                            JsonNode item = hariLiburElements.next();
                            listOfHariLibur.add(item.asText());
                        }
                        dosen.setHariLibur(listOfHariLibur);
                        return dosen;
                    }
                }
            }
            return null;
        } catch (IOException e) {
            return null;
        }

    }

    public boolean jsonFileSetKompetensiDosen(ArrayList<String> kompetensi) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("username").asText().equals(this.getUsername())) {
                        ArrayNode array = mapper.valueToTree(kompetensi);
                        ((ObjectNode) root).putArray("kompetensi").addAll(array);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);

                        JsonNode kompetensiNode = root.path("kompetensi");
                        Iterator<JsonNode> elements = kompetensiNode.elements();
                        ArrayList<Model_Kompetensi> listOfKompetensi = new ArrayList<Model_Kompetensi>();
                        while (elements.hasNext()) {
                            JsonNode item = elements.next();
                            Model_Kompetensi k = new Model_Kompetensi(item.asText());
                            k = k.getKompetensiByID();
                            listOfKompetensi.add(k);
                        }
                        this.setKompetensi(listOfKompetensi);

                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean jsonFileSetHariLiburDosen(ArrayList<String> hariLibur) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("username").asText().equals(this.getUsername())) {
                        ArrayNode array = mapper.valueToTree(hariLibur);
                        ((ObjectNode) root).putArray("hariLibur").addAll(array);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);
                        this.setHariLibur(hariLibur);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public ArrayList<Model_Kelas> getKelasByUsernameDosen() {
        ArrayList<Model_Kelas> listOfKelas = new ArrayList<Model_Kelas>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/KelasFinal.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("idDosen").asText().equals(this.getUsername())) {
                        Model_Kelas kelas = new Model_Kelas();
                        kelas.setIdKelas(root.path("idKelas").asText());
                        kelas.setIdDosen(root.path("idDosen").asText());
                        kelas.setIdKompetensi(root.path("idKompetensi").asText());
                        kelas.setKuotaKelas(root.path("kuotaKelas").asInt());

                        JsonNode waktuNode = root.path("slotWaktu");
                        Iterator<JsonNode> waktuElements = waktuNode.elements();
                        ArrayList<String> listOfWaktu = new ArrayList<String>();
                        while (waktuElements.hasNext()) {
                            JsonNode itemSlot = waktuElements.next();
                            listOfWaktu.add(itemSlot.asText());
                        }
                        kelas.setSlotWaktu(listOfWaktu);
                        listOfKelas.add(kelas);
                    }

                }
            }
        } catch (IOException e) {
            return null;
        }
        return listOfKelas;
    }
    
    public void gantiJadwal(String tanggal) {
        ArrayList<Model_Kelas> listKelas = this.getKelasByUsernameDosen();
        Model_Waktu waktu = new Model_Waktu();
        ArrayList<Model_Waktu> listAllWaktu = waktu.getAllWaktu();
        int idx = listAllWaktu.size() - 1;
        for (Model_Kelas k : listKelas) {
            ArrayList<String> listWaktu = k.getSlotWaktu();
            for (String w : listWaktu) {
                Model_Waktu waktutemp = new Model_Waktu(w);
                waktutemp.getWaktuByID();
                if (waktutemp.getTanggal().equals(tanggal)) {
                    listWaktu.remove(w);
                    listWaktu.add(listAllWaktu.get(idx).getIdWaktu());
                    k.setWaktuKelasToJson(k.getIdKompetensi(), listWaktu);
                    listAllWaktu.get(idx).setFlagOwner(listWaktu);
                    idx--;
                }
            }
        }
    }
}
