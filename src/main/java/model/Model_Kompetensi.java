/**
 *
 * @author Trisna
 */
package model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Model_Kompetensi {
    private String idKompetensi;
    private String namaKompetensi;
    private String isPraktikum;
    private ArrayList<Model_Kompetensi> daftarReqKompetensi;
    private int bobotKompetensi;

    public Model_Kompetensi() {
      
    }
    
    public Model_Kompetensi(String id) {
        this.idKompetensi=id;
        this.daftarReqKompetensi = new ArrayList<Model_Kompetensi>();
        this.bobotKompetensi = 0;
        this.namaKompetensi = "";
        this.isPraktikum = "";
    }
    
    public Model_Kompetensi(String idKompetensi, String namaKompetensi, int bobotKompetensi) {
        this.idKompetensi = idKompetensi;
        this.namaKompetensi = namaKompetensi;
        this.bobotKompetensi = bobotKompetensi;
    }

    public Model_Kompetensi(String idKompetensi, String namaKompetensi, String isPraktikum, int bobotKompetensi) {
        this.idKompetensi = idKompetensi;
        this.namaKompetensi = namaKompetensi;
        this.isPraktikum = isPraktikum;
        this.bobotKompetensi = bobotKompetensi;
    }

    public Model_Kompetensi(String idKompetensi, String namaKompetensi, String isPraktikum, ArrayList<Model_Kompetensi> daftarKompetensi, int bobotKompetensi) {
        this.idKompetensi = idKompetensi;
        this.namaKompetensi = namaKompetensi;
        this.isPraktikum = isPraktikum;
        this.daftarReqKompetensi = daftarKompetensi;
        this.bobotKompetensi = bobotKompetensi;
    }

    public String getIsPraktikum() {
        return isPraktikum;
    }

    public void setIsPraktikum(String isPraktikum) {
        this.isPraktikum = isPraktikum;
    }

    public ArrayList<Model_Kompetensi> getDaftarReqKompetensi() {
        return daftarReqKompetensi;
    }

    public void setDaftarReqKompetensi(ArrayList<Model_Kompetensi> daftarReqKompetensi) {
        this.daftarReqKompetensi = daftarReqKompetensi;
    }
    
    public String getIdKompetensi() {
        return idKompetensi;
    }

    public void setIdKompetensi(String idKompetensi) {
        this.idKompetensi = idKompetensi;
    }

    public String getNamaKompetensi() {
        return namaKompetensi;
    }

    public void setNamaKompetensi(String namaKompetensi) {
        this.namaKompetensi = namaKompetensi;
    }

    public int getBobotKompetensi() {
        return bobotKompetensi;
    }

    public void setBobotKompetensi(int bobotKompetensi) {
        this.bobotKompetensi = bobotKompetensi;
    }
    
    public Model_Kompetensi getKompetensiByID (){
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kompetensi.json"));
            Model_Kompetensi kompetensi = new Model_Kompetensi();

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    if(root.path("idKompetensi").asText().equals(this.getIdKompetensi())){
                        kompetensi.setIdKompetensi(root.path("idKompetensi").asText());
                        kompetensi.setNamaKompetensi(root.path("namaKompetensi").asText());
                        kompetensi.setIsPraktikum(root.path("isPraktikum").asText());
                        kompetensi.setBobotKompetensi(root.path("bobotKompetensi").asInt());
                        
                        JsonNode kompetensiNode = root.path("daftarReqKompetensi");
                        Iterator<JsonNode> elements = kompetensiNode.elements();
                        ArrayList<Model_Kompetensi> listOfReqKompetensi = new ArrayList<>();
                        while(elements.hasNext()){
                                JsonNode item = elements.next();
                                Model_Kompetensi m = new Model_Kompetensi(item.asText());
                                //m = m.getKompetensiByID();
                                listOfReqKompetensi.add(m);
                        }
                        kompetensi.setDaftarReqKompetensi(listOfReqKompetensi);
                    }    
                }
                return kompetensi;
            }
            return null;
        }catch(IOException e){
            return null;
        }
    }
}