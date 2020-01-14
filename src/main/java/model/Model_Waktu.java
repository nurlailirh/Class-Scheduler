package model;

/**
 *
 * @author Trisna
 */
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

public class Model_Waktu {

    private String idWaktu;
    private String Tanggal;
    private String Jam;
    private ArrayList<String> flagOwner;

    public Model_Waktu() {
    }

    public Model_Waktu(String idWaktu) {
        this.idWaktu = idWaktu;
    }

    public Model_Waktu(String idWaktu, String Tanggal, String Jam, ArrayList<String> flagOwner) {
        this.idWaktu = idWaktu;
        this.Tanggal = Tanggal;
        this.Jam = Jam;
        this.flagOwner = flagOwner;
    }

    public String getIdWaktu() {
        return idWaktu;
    }

    public void setIdWaktu(String idWaktu) {
        this.idWaktu = idWaktu;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String Tanggal) {
        this.Tanggal = Tanggal;
    }

    public String getJam() {
        return Jam;
    }

    public void setJam(String Jam) {
        this.Jam = Jam;
    }

    public ArrayList<String> getFlagOwner() {
        return flagOwner;
    }

    public void setFlagOwner(ArrayList<String> flagOwner) {
        this.flagOwner = flagOwner;
    }

    //GET SEMUA DATA WAKTU
    public ArrayList<Model_Waktu> getAllWaktu() {
        ArrayList<Model_Waktu> listOfWaktu = new ArrayList<Model_Waktu>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Waktu.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    Model_Waktu waktu = new Model_Waktu();
                    waktu.setIdWaktu(root.path("idWaktu").asText());
                    waktu.setTanggal(root.path("Tanggal").asText());
                    waktu.setJam(root.path("Jam").asText());
                    JsonNode ownerNode = root.path("flagOwner");

                    Iterator<JsonNode> elements = ownerNode.elements();
                    ArrayList<String> listOfOwner = new ArrayList<String>();
                    while (elements.hasNext()) {
                        JsonNode item = elements.next();
                        listOfOwner.add(item.asText());
                    }
                    waktu.setFlagOwner(listOfOwner);
                    listOfWaktu.add(waktu);
                }
            }
        } catch (IOException e) {
            return null;
        }
        return listOfWaktu;
    }

    //GET FLAG BY ID
    public ArrayList<String> jsonFileGetFlagOwner() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Waktu.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("idWaktu").asText().equals(this.getIdWaktu())) {
                        Model_Waktu w = new Model_Waktu();
                        JsonNode flagOwnerNode = root.path("flagOwner");
                        Iterator<JsonNode> elements = flagOwnerNode.elements();
                        ArrayList<String> flagOwner = new ArrayList<String>();
                        while (elements.hasNext()) {
                            JsonNode item = elements.next();
                            flagOwner.add(item.asText());
                        }
                        return flagOwner;
                    }
                }
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    //SET FLAG OWNERNYA
    public boolean jsonFileSetFlagOwner(ArrayList<String> newFlag) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Waktu.json"));
            //get flag owner sebelumnya
            //ArrayList<String> flagOwner = this.jsonFileGetFlagOwner();
            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("idWaktu").asText().equals(this.getIdWaktu())) {
                        ArrayNode array = mapper.valueToTree(flagOwner);
                        ((ObjectNode) root).putArray("flagOwner").addAll(array);
                        mapper.writeValue(new File("dataJSON/Waktu.json"), (ArrayNode) rootArray);
                        this.setFlagOwner(flagOwner);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //GET WAKTU BY ID
    public Model_Waktu getWaktuByID() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Waktu.json"));
            Model_Waktu waktu = new Model_Waktu();

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("idWaktu").asText().equals(this.getIdWaktu())) {
                        waktu.setJam(root.path("Jam").asText());
                        waktu.setTanggal(root.path("Tanggal").asText());
                        
                        JsonNode flagOwnerNode = root.path("flagOwner");
                        Iterator<JsonNode> flagOwnerElements = flagOwnerNode.elements();
                        ArrayList<String> listOfFlagOwner = new ArrayList<String>();
                        while(flagOwnerElements.hasNext()){
                                JsonNode item = flagOwnerElements.next();
                                listOfFlagOwner.add(item.asText());
                        }
                        waktu.setFlagOwner(listOfFlagOwner);
                    }
                }
                return waktu;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

}