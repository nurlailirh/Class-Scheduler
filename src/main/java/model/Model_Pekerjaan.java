package model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Nurlaili
 */
public class Model_Pekerjaan {

    private String idPekerjaan;
    private String namaPekerjaan;
    private ArrayList<Model_Kompetensi> daftarKompetensi;

    public Model_Pekerjaan() {
    }
    
    public Model_Pekerjaan(String id) {
        this.idPekerjaan = id;
    }

    public Model_Pekerjaan(String id, String nama, ArrayList<Model_Kompetensi> mK) {
        this.idPekerjaan = id;
        this.namaPekerjaan = nama;
        this.daftarKompetensi = mK;
    }

    public String getIdPekerjaan() {
        return idPekerjaan;
    }

    public void setIdPekerjaan(String idPekerjaan) {
        this.idPekerjaan = idPekerjaan;
    }

    public String getNamaPekerjaan() {
        return namaPekerjaan;
    }

    public void setNamaPekerjaan(String namaPekerjaan) {
        this.namaPekerjaan = namaPekerjaan;
    }

    public ArrayList<Model_Kompetensi> getDaftarKompetensi() {
        return daftarKompetensi;
    }

    public void setDaftarKompetensi(ArrayList<Model_Kompetensi> daftarKompetensi) {
        this.daftarKompetensi = daftarKompetensi;
    }

    public Model_Pekerjaan getPekerjaanByID (){
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Pekerjaan.json"));
            Model_Pekerjaan pekerjaan = new Model_Pekerjaan();

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    if(root.path("idPekerjaan").asText().equals(this.getIdPekerjaan())){
                        pekerjaan.setIdPekerjaan(root.path("idPekerjaan").asText());
                        pekerjaan.setNamaPekerjaan(root.path("namaPekerjaan").asText());
                        JsonNode kompetensiNode = root.path("daftarKompetensi");
                        Iterator<JsonNode> elements = kompetensiNode.elements();
                        ArrayList<Model_Kompetensi> arrKompetensi = new ArrayList<Model_Kompetensi>();
                        while(elements.hasNext()){
                                JsonNode item = elements.next();
                                Model_Kompetensi k = new Model_Kompetensi(item.asText());
                                k = k.getKompetensiByID();
                                arrKompetensi.add(k);
                        }
                        pekerjaan.setDaftarKompetensi(arrKompetensi);
                    }    
                }
                return pekerjaan;
            }
            return null;
        }catch(IOException e){
            return null;
        }
    }
}