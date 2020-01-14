package model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Model_Mahasiswa extends Model_User{
    
    private String nim;
    private ArrayList<Model_Kompetensi> kompetensi;
    private Model_Pekerjaan[] pekerjaan;

    public Model_Mahasiswa() {
        super();
        this.nim = "";
        this.kompetensi = new ArrayList<Model_Kompetensi>();
        this.pekerjaan = new Model_Pekerjaan[3];
        this.setRole(3);
    }

    public Model_Mahasiswa(String nama, String nim, ArrayList<Model_Kompetensi> kompetensi) {
        super(nama);
        this.nim = nim;
        this.setRole(3);
        this.kompetensi = kompetensi;
        this.pekerjaan = new Model_Pekerjaan[3];
    }

    public Model_Mahasiswa(String nama, String nim) {
        super(nama);
        this.nim = nim;
        this.setRole(3);
        this.kompetensi = new ArrayList<Model_Kompetensi>();
        this.pekerjaan = new Model_Pekerjaan[3];
    }
    
    public String getNim() {
        return this.nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public ArrayList<Model_Kompetensi> getKompetensi() {
        return kompetensi;
    }

    public void setKompetensi(ArrayList<Model_Kompetensi> kompetensi) {
        this.kompetensi = kompetensi;
    }
    
    public Model_Pekerjaan[] getPekerjaan() {
        return this.pekerjaan;
    }

    public void setPekerjaan(Model_Pekerjaan[] pekerjaan) {
        this.pekerjaan = pekerjaan;
    }
    
    
    public boolean jsonFileSetNim(String nim){
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    /* Jika id yang akan diedit sesuai */
                    if(root.path("username").asText().equals(this.getUsername())){
                        
                        ((ObjectNode) root ).put("nim", nim);
                        this.setNim(nim);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);
                        return true;
                    }                    
                }
            }
            return false;
        }catch(IOException e){
            return false;
        }
    }
    
    public Model_Mahasiswa getMahasiswaFromJson(String username)
    {
            try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    if(root.path("username").asText().equals(username) && root.path("role").asInt() == 3){
                        Model_Mahasiswa mahasiswa = new Model_Mahasiswa();
                        mahasiswa.setUsername(root.path("username").asText());
                        mahasiswa.setNama(root.path("nama").asText());
                        mahasiswa.setNim(root.path("nim").asText());
                        mahasiswa.setPassword(root.path("password").asText());
                        JsonNode kompetensiNode = root.path("kompetensi");
                        Iterator<JsonNode> elements = kompetensiNode.elements();
                        ArrayList<Model_Kompetensi> listOfKompetensi = new ArrayList<Model_Kompetensi>();
                        while(elements.hasNext()){
                                JsonNode item = elements.next();
                                Model_Kompetensi k = new Model_Kompetensi(item.asText());
                                k = k.getKompetensiByID();
                                listOfKompetensi.add(k);
                        }
                        mahasiswa.setKompetensi(listOfKompetensi);
                        
                        JsonNode pekerjaanNode = root.path("pekerjaan");
                        Iterator<JsonNode> pekerjaanElements = pekerjaanNode.elements();
                        Model_Pekerjaan[] listOfPekerjaan = new Model_Pekerjaan[3];
                        int index = 0; //untuk keperluan add pekerjaan ke array
                        while(pekerjaanElements.hasNext()){
                                JsonNode item = pekerjaanElements.next();
                                Model_Pekerjaan p = new Model_Pekerjaan(item.asText());
                                p = p.getPekerjaanByID();
                                listOfPekerjaan[index] = p;
                                index++;
                        }
                        mahasiswa.setPekerjaan(listOfPekerjaan);
                        
                        return mahasiswa;
                    }                    
                }
            }
            return null;
        }catch(IOException e){
            return null;
        }
    }
    
    public boolean jsonFileSetKompetensiMahasiswa(ArrayList<String> kompetensi){
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    /* Jika id yang akan diedit sesuai */
                    if(root.path("username").asText().equals(this.getUsername())){
                        ArrayNode array = mapper.valueToTree(kompetensi);
                        ((ObjectNode) root ).putArray("kompetensi").addAll(array);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);
                        
                        JsonNode kompetensiNode = root.path("kompetensi");
                        Iterator<JsonNode> elements = kompetensiNode.elements();
                        ArrayList<Model_Kompetensi> listOfKompetensi = new ArrayList<Model_Kompetensi>();
                        while(elements.hasNext()){
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
        }catch(IOException e){
            return false;
        }
    }
    
    public boolean jsonFileSetPekerjaanMahasiswa(String[] pekerjaan){
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    /* Jika id yang akan diedit sesuai */
                    if(root.path("username").asText().equals(this.getUsername())){
                        ArrayNode array = mapper.valueToTree(pekerjaan);
                        ((ObjectNode) root ).putArray("pekerjaan").addAll(array);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);
                        
                        JsonNode pekerjaanNode = root.path("pekerjaan");
                        Iterator<JsonNode> pekerjaanElements = pekerjaanNode.elements();
                        Model_Pekerjaan[] listOfPekerjaan = new Model_Pekerjaan[3];
                        int index = 0; //untuk keperluan add pekerjaan ke array
                        while(pekerjaanElements.hasNext()){
                                JsonNode item = pekerjaanElements.next();
                                Model_Pekerjaan p = new Model_Pekerjaan(item.asText());
                                p = p.getPekerjaanByID();
                                listOfPekerjaan[index] = p;
                                index++;
                        }
                        this.setPekerjaan(listOfPekerjaan);
                        
                        return true;
                    }                    
                }
            }
            return false;
        }catch(IOException e){
            return false;
        }
    }
    
    public boolean jsonFileSetPembayaran(String kodeTransaksi, String tanggalBayar) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Tagihan.json"));
            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika nim yang akan diedit sesuai */
                    
                    if(root.path("mahasiswa").asText().equals(this.getUsername())){
                        //root.path("username").asText()
                        ((ObjectNode) root).put("kodeTransaksi", kodeTransaksi);
                        //this.setKodeTransaksi(kodeTransaksi);
                        ((ObjectNode) root).put("tanggalBayar", tanggalBayar);
                        
                        ((ObjectNode) root).put("isLunas", true);
                        //this.setTanggalBayar(tanggalBayar);
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
    
    public ArrayList<Model_Kelas> getKelasByUsernameMahasiswa() {
        ArrayList<Model_Kelas> listOfKelas = new ArrayList<Model_Kelas>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/KelasFinal.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    Model_Kelas kelas = new Model_Kelas();
                    JsonNode pesertaNode = root.path("pesertaKelas");
                    Iterator<JsonNode> pesertaElements = pesertaNode.elements();
                    boolean isFound = false;
                    while (pesertaElements.hasNext() && isFound == false) {
                        JsonNode item = pesertaElements.next();
                        if(item.asText().equals(this.getUsername()))
                        {
                            isFound = true;
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
            }
        } catch (IOException e) {
            return null;
        }
        return listOfKelas;
    }
    
    public Model_Tagihan getTagihanMahasiswa() {
        Model_Tagihan tagihan = new Model_Tagihan();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Tagihan.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if(root.path("mahasiswa").asText().equals(this.getUsername())){
                        Model_Mahasiswa m = new Model_Mahasiswa();
                        m = m.getMahasiswaFromJson(root.path("mahasiswa").asText());
                        tagihan.setMahasiswa(m);
                        JsonNode kelasNode = root.path("kelas");
                        Iterator<JsonNode> kelasElements = kelasNode.elements();
                        ArrayList<Model_Kompetensi> listOfKelas = new ArrayList<>();
                        while (kelasElements.hasNext()) {
                            JsonNode item = kelasElements.next();
                            Model_Kompetensi k = new Model_Kompetensi(item.asText());
                            k = k.getKompetensiByID();
                            listOfKelas.add(k);
                        }
                        tagihan.setKelas(listOfKelas);
                        tagihan.setTotalTagihan(root.path("totalTagihan").asInt());
                        tagihan.setIsLunas(root.path("isLunas").asBoolean());
                        tagihan.setKodeTransaksi(root.path("kodeTransaksi").asText());
                        tagihan.setTanggalBayar(root.path("tanggalBayar").asText());
                    }
                }
                
            }
        } catch (IOException e) {
            return null;
        }
        return tagihan;
    }
}
