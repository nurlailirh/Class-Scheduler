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
import java.util.Comparator;
import java.util.Iterator;

public class Model_Admin extends Model_User {

    public Model_Admin() {
        super();
        this.setRole(1);
    }

    public Model_Admin(String nama) {
        super(nama);
        this.setRole(1);
    }

    //======================================USER================================
    //CREATE MAHASISWA
    public boolean tambahMahasiswa(String nama, String nim, ArrayList<String> kompetensi) {

        Model_Mahasiswa mahasiswa = new Model_Mahasiswa(nama, nim);
        mahasiswa.setUsername("itb" + mahasiswa.getNim());
        mahasiswa.setPassword(mahasiswa.getUsername());
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!isUserExist(mahasiswa.getNim())) {
                try {
                    JsonNode root = mapper.readTree(new File("dataJSON/User.json"));
                    ObjectNode data = mapper.createObjectNode();
                    data.put("nim", mahasiswa.getNim());
                    data.put("nama", mahasiswa.getNama());
                    data.put("username", mahasiswa.getUsername());
                    data.put("password", mahasiswa.getPassword());
                    ArrayNode arrayKompetensi = mapper.valueToTree(kompetensi);
                    ArrayNode arrayPekerjaan = mapper.valueToTree(mahasiswa.getPekerjaan());
                    data.putArray("kompetensi").addAll(arrayKompetensi);
                    data.putArray("pekerjaan").addAll(arrayPekerjaan);
                    data.put("role", 3);
                    ((ArrayNode) root).add(data);
                    mapper.writeValue(new File("dataJSON/User.json"), ((ArrayNode) root));
                } catch (IOException IE) {
                    JsonFactory jasonFactory = new JsonFactory();
                    try (JsonGenerator jsonGenerator = jasonFactory.createGenerator(new File("dataJSON/User.json"), JsonEncoding.UTF8)) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("nim", mahasiswa.getNim());
                        jsonGenerator.writeStringField("nama", mahasiswa.getNama());
                        jsonGenerator.writeStringField("username", mahasiswa.getUsername());
                        jsonGenerator.writeStringField("password", mahasiswa.getPassword());
                        jsonGenerator.writeArrayFieldStart("kompetensi"); //start kompetensi array
                        for (String data : kompetensi) {
                            jsonGenerator.writeString(data);
                        }
                        jsonGenerator.writeEndArray(); //closing kompetensi array

                        jsonGenerator.writeArrayFieldStart("pekerjaan"); //start pekerjaan array
                        for (Model_Pekerjaan data : mahasiswa.getPekerjaan()) {
                            jsonGenerator.writeString(data.getIdPekerjaan());
                        }
                        jsonGenerator.writeEndArray(); //closing kompetensi array

                        jsonGenerator.writeNumberField("role", 3);
                        jsonGenerator.writeEndObject(); //closing properties
                        jsonGenerator.flush();
                        jsonGenerator.close();
                        return true;
                    }
                }
                return true;
            } else {
                System.out.println("Mahasiswa dengan NIM: " + mahasiswa.getNim() + " sudah memiliki akun.");
                return false;
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return false;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //CREATE DOSEN
    public boolean tambahDosen(String nama, String nip, ArrayList<String> kompetensi) {

        Model_Dosen dosen = new Model_Dosen(nama, nip);
        dosen.setUsername("itb" + dosen.getNip());
        dosen.setPassword(dosen.getUsername());
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!isUserExist(dosen.getNip())) {
                try {
                    JsonNode root = mapper.readTree(new File("dataJSON/User.json"));
                    ObjectNode data = mapper.createObjectNode();
                    data.put("nip", dosen.getNip());
                    data.put("nama", dosen.getNama());
                    data.put("username", dosen.getUsername());
                    data.put("password", dosen.getPassword());
                    ArrayNode array = mapper.valueToTree(kompetensi);
                    ArrayNode arrayHariLibur = mapper.valueToTree(dosen.getHariLibur());
                    data.putArray("kompetensi").addAll(array);
                    data.putArray("hariLibur").addAll(arrayHariLibur);
                    data.put("role", 2);
                    ((ArrayNode) root).add(data);
                    mapper.writeValue(new File("dataJSON/User.json"), ((ArrayNode) root));
                } catch (IOException IE) {
                    JsonFactory jasonFactory = new JsonFactory();
                    try (JsonGenerator jsonGenerator = jasonFactory.createGenerator(new File("dataJSON/User.json"), JsonEncoding.UTF8)) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("nip", dosen.getNip());
                        jsonGenerator.writeStringField("nama", dosen.getNama());
                        jsonGenerator.writeStringField("username", dosen.getUsername());
                        jsonGenerator.writeStringField("password", dosen.getPassword());
                        jsonGenerator.writeArrayFieldStart("kompetensi"); //start kompetensi array
                        for (String data : kompetensi) {
                            jsonGenerator.writeString(data);
                        }
                        jsonGenerator.writeEndArray(); //closing kompetensi array

                        jsonGenerator.writeArrayFieldStart("hariLibur"); //start hari libur array
                        for (String data : dosen.getHariLibur()) {
                            jsonGenerator.writeString(data);
                        }
                        jsonGenerator.writeEndArray(); //closing hari libur array

                        jsonGenerator.writeNumberField("role", 2);
                        jsonGenerator.writeEndObject(); //closing properties
                        jsonGenerator.flush();
                        jsonGenerator.close();
                        return true;
                    }
                }
                return true;
            } else {
                System.out.println("Dosen dengan NIP: " + dosen.getNip() + " sudah memiliki akun.");
                return false;
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return false;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUserExist(String id) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("nim").asText().equals(id) || root.path("nip").asText().equals(id)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //DELETE MAHASISWA
    public boolean hapusMahasiswa(String nim) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                int index = 0;
                for (JsonNode root : rootArray) {
                    /* Jika sesuai dengan nim */
                    if (root.path("nim").asText().equals(nim)) {
                        ((ArrayNode) rootArray).remove(index);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);
                        return true;
                    }
                    index++;
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //DELETE DOSEN
    public boolean hapusDosen(String nip) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                int index = 0;
                for (JsonNode root : rootArray) {
                    /* Jika sesuai dengan nim */
                    if (root.path("nip").asText().equals(nip)) {
                        ((ArrayNode) rootArray).remove(index);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);
                        return true;
                    }
                    index++;
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //GET ALL OF DOSEN
    public ArrayList<Model_Dosen> getAllDosen() {
        ArrayList<Model_Dosen> listOfDosen = new ArrayList<Model_Dosen>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("role").asInt() == 2) {
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

                        listOfDosen.add(dosen);
                    }
                }
            }
        } catch (IOException e) {
            return null;
        }
        return listOfDosen;
    }

    //GET ALL OF MAHASISWA
    public ArrayList<Model_Mahasiswa> getAllMahasiswa() {
        ArrayList<Model_Mahasiswa> listOfMahasiswa = new ArrayList<Model_Mahasiswa>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("role").asInt() == 3) {
                        Model_Mahasiswa mahasiswa = new Model_Mahasiswa();
                        mahasiswa.setUsername(root.path("username").asText());
                        mahasiswa.setNama(root.path("nama").asText());
                        mahasiswa.setNim(root.path("nim").asText());
                        mahasiswa.setPassword(root.path("password").asText());
                        JsonNode kompetensiNode = root.path("kompetensi");
                        Iterator<JsonNode> elements = kompetensiNode.elements();
                        ArrayList<Model_Kompetensi> listOfKompetensi = new ArrayList<Model_Kompetensi>();
                        while (elements.hasNext()) {
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
                        while (pekerjaanElements.hasNext()) {
                            JsonNode item = pekerjaanElements.next();
                            Model_Pekerjaan p = new Model_Pekerjaan(item.asText());
                            p = p.getPekerjaanByID();
                            listOfPekerjaan[index] = p;
                            index++;
                        }
                        mahasiswa.setPekerjaan(listOfPekerjaan);

                        listOfMahasiswa.add(mahasiswa);
                    }
                }
            }
        } catch (IOException e) {
            return null;
        }
        return listOfMahasiswa;
    }

    public Model_Admin getAdminFromJson(String username) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    //System.out.println(root.path("id").asText());
                    if (root.path("username").asText().equals(username)) {
                        Model_Admin admin = new Model_Admin();
                        admin.setUsername(root.path("username").asText());
                        admin.setNama(root.path("nama").asText());
                        admin.setPassword(root.path("password").asText());
                        return admin;
                    }
                }
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    //ADMIN EDIT KOMPETENSI MAHASISWA ATAU DOSEN
    public boolean ubahKompetensiUser(String username, ArrayList<String> kompetensi) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("username").asText().equals(username)) {
                        ArrayNode array = mapper.valueToTree(kompetensi);
                        ((ObjectNode) root).putArray("kompetensi").addAll(array);
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

    //================================KOMPETENSI================================
    public ArrayList<Model_Kompetensi> getAllKompetensi() {
        ArrayList<Model_Kompetensi> listOfKompetensi = new ArrayList<Model_Kompetensi>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kompetensi.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    Model_Kompetensi kompetensi = new Model_Kompetensi();
                    kompetensi.setIdKompetensi(root.path("idKompetensi").asText());
                    kompetensi.setNamaKompetensi(root.path("namaKompetensi").asText());
                    kompetensi.setIsPraktikum(root.path("isPraktikum").asText());
                    kompetensi.setBobotKompetensi(root.path("bobotKompetensi").asInt());

                    JsonNode kompetensiNode = root.path("daftarReqKompetensi");
                    Iterator<JsonNode> elements = kompetensiNode.elements();
                    ArrayList<Model_Kompetensi> listOfReqKompetensi = new ArrayList<Model_Kompetensi>();
                    while (elements.hasNext()) {
                        JsonNode item = elements.next();
                        Model_Kompetensi m = new Model_Kompetensi(item.asText());
                        m = m.getKompetensiByID();
                        listOfReqKompetensi.add(m);
                    }
                    kompetensi.setDaftarReqKompetensi(listOfReqKompetensi);
                    listOfKompetensi.add(kompetensi);
                }
            }
        } catch (IOException e) {
            return null;
        }
        return listOfKompetensi;
    }

    //CREATE KOMPETENSI
    public boolean tambahKompetensi(String idKompetensi, String namaKompetensi, String isPraktikum, ArrayList<String> daftarKompetensi, int bobotKompetensi) {
        Model_Kompetensi kompetensi = new Model_Kompetensi(idKompetensi, namaKompetensi, isPraktikum, bobotKompetensi);
        kompetensi.setIdKompetensi(idKompetensi);
        kompetensi.setNamaKompetensi(namaKompetensi);
        kompetensi.setBobotKompetensi(bobotKompetensi);
        kompetensi.setIsPraktikum(isPraktikum);
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!isKompetensiExist(kompetensi.getIdKompetensi())) {
                try {

                    JsonNode root = mapper.readTree(new File("dataJSON/Kompetensi.json"));
                    ObjectNode data = mapper.createObjectNode();
                    data.put("idKompetensi", kompetensi.getIdKompetensi());
                    data.put("namaKompetensi", kompetensi.getNamaKompetensi());
                    data.put("isPraktikum", kompetensi.getIsPraktikum());
                    ArrayNode array = mapper.valueToTree(daftarKompetensi);
                    data.putArray("daftarReqKompetensi").addAll(array);
                    data.put("bobotKompetensi", kompetensi.getBobotKompetensi());
                    ((ArrayNode) root).add(data);
                    mapper.writeValue(new File("dataJSON/Kompetensi.json"), ((ArrayNode) root));
                } catch (IOException IE) {
                    JsonFactory jasonFactory = new JsonFactory();
                    try (JsonGenerator jsonGenerator = jasonFactory.createGenerator(new File("dataJSON/Kompetensi.json"), JsonEncoding.UTF8)) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("idKompetensi", kompetensi.getIdKompetensi());
                        jsonGenerator.writeStringField("namaKompetensi", kompetensi.getNamaKompetensi());
                        jsonGenerator.writeNumberField("bobotKompetensi", kompetensi.getBobotKompetensi());
                        jsonGenerator.writeArrayFieldStart("daftarReqKompetensi"); //start kompetensi array
                        for (String data : daftarKompetensi) {
                            jsonGenerator.writeString(data);
                        }
                        jsonGenerator.writeEndArray(); //closing kompetensi array
                        jsonGenerator.writeEndObject(); //closing properties
                        jsonGenerator.flush();
                        jsonGenerator.close();
                        return true;
                    }
                }
                return true;
            } else {
                System.out.println("Kompetensi dengan ID: " + kompetensi.getIdKompetensi() + " sudah ada.");
                return false;
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return false;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //CEK KOMPETENSI ADA ATAU TIDAK
    public boolean isKompetensiExist(String id) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kompetensi.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    // Jika id adalah yang ingin dihapus
                    if (root.path("idKompetensi").asText().equals(id)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //UBAH NAMA KOMPETENSI
    public boolean ubahNamaKompetensi(String id, String nama) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kompetensi.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("idKompetensi").asText().equals(id)) {

                        ((ObjectNode) root).put("namaKompetensi", nama);
                        mapper.writeValue(new File("dataJSON/Kompetensi.json"), (ArrayNode) rootArray);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //UBAH BOBOT KOMPETENSI
    public boolean ubahBobotKompetensi(String id, int bobot) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kompetensi.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("idKompetensi").asText().equals(id)) {

                        ((ObjectNode) root).put("bobotKompetensi", bobot);
                        mapper.writeValue(new File("dataJSON/Kompetensi.json"), (ArrayNode) rootArray);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean ubahIsPraktikumKompetensi(String id, String isPraktikum) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kompetensi.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("idKompetensi").asText().equals(id)) {
                        ((ObjectNode) root).put("isPraktikum", isPraktikum);
                        mapper.writeValue(new File("dataJSON/Kompetensi.json"), (ArrayNode) rootArray);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean ubahReqKompetensi(String id, ArrayList<String> reqKompetensi) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kompetensi.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("idKompetensi").asText().equals(id)) {
                        ArrayNode array = mapper.valueToTree(reqKompetensi);
                        ((ObjectNode) root).putArray("daftarReqKompetensi").addAll(array);
                        mapper.writeValue(new File("dataJSON/Kompetensi.json"), (ArrayNode) rootArray);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //HAPUS KOMPETENSI
    public boolean hapusKompetensi(String id) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kompetensi.json"));

            if (rootArray.isArray()) {
                int index = 0;
                for (JsonNode root : rootArray) {
                    /* Jika sesuai dengan id */
                    if (root.path("idKompetensi").asText().equals(id)) {
                        ((ArrayNode) rootArray).remove(index);
                        mapper.writeValue(new File("dataJSON/Kompetensi.json"), (ArrayNode) rootArray);
                        return true;
                    }
                    index++;
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean hapusForeignKeyKompetensiDiKompetensi(String id) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kompetensi.json"));
            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("daftarReqKompetensi").toString().contains(id)) {
                        ArrayList<String> reqKompetensi = new ArrayList<String>();
                        String requirement = root.path("daftarReqKompetensi").toString();
                        requirement = requirement.replace("[", "");
                        requirement = requirement.replace("]", "");
                        requirement = requirement.replace("\"", "");
                        String[] arrRequirement = requirement.split(",");
                        for (int i = 0; i < arrRequirement.length; i++) {
                            if (!arrRequirement[i].equals(id)) {
                                reqKompetensi.add(arrRequirement[i]);
                            }
                        }

                        ArrayNode array = mapper.valueToTree(reqKompetensi);
                        ((ObjectNode) root).set("daftarReqKompetensi", array);
                        mapper.writeValue(new File("dataJSON/Kompetensi.json"), (ArrayNode) rootArray);
                    }
                }
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean hapusForeignKeyKompetensiDiPekerjaan(String id) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Pekerjaan.json"));
            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("daftarKompetensi").toString().contains(id)) {
                        ArrayList<String> reqKompetensi = new ArrayList<String>();
                        String requirement = root.path("daftarKompetensi").toString();
                        requirement = requirement.replace("[", "");
                        requirement = requirement.replace("]", "");
                        requirement = requirement.replace("\"", "");
                        String[] arrRequirement = requirement.split(",");
                        for (int i = 0; i < arrRequirement.length; i++) {
                            if (!arrRequirement[i].equals(id)) {
                                reqKompetensi.add(arrRequirement[i]);
                            }
                        }

                        ArrayNode array = mapper.valueToTree(reqKompetensi);
                        ((ObjectNode) root).set("daftarKompetensi", array);
                        mapper.writeValue(new File("dataJSON/Pekerjaan.json"), (ArrayNode) rootArray);
                    }
                }
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean hapusForeignKeyKompetensiDiUser(String id) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));
            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("kompetensi").toString().contains(id)) {
                        ArrayList<String> reqKompetensi = new ArrayList<String>();
                        String requirement = root.path("kompetensi").toString();
                        requirement = requirement.replace("[", "");
                        requirement = requirement.replace("]", "");
                        requirement = requirement.replace("\"", "");
                        String[] arrRequirement = requirement.split(",");
                        for (int i = 0; i < arrRequirement.length; i++) {
                            if (!arrRequirement[i].equals(id)) {
                                reqKompetensi.add(arrRequirement[i]);
                            }
                        }

                        ArrayNode array = mapper.valueToTree(reqKompetensi);
                        ((ObjectNode) root).set("kompetensi", array);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);
                    }
                }
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //================================PEKERJAAN================================
    //GET ALL PEKERJAAN
    public ArrayList<Model_Pekerjaan> getAllPekerjaan() {
        ArrayList<Model_Pekerjaan> listOfPekerjaan = new ArrayList<Model_Pekerjaan>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Pekerjaan.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    Model_Pekerjaan pekerjaan = new Model_Pekerjaan();
                    pekerjaan.setIdPekerjaan(root.path("idPekerjaan").asText());
                    pekerjaan.setNamaPekerjaan(root.path("namaPekerjaan").asText());
                    JsonNode kompetensiNode = root.path("daftarKompetensi");
                    Iterator<JsonNode> elements = kompetensiNode.elements();
                    ArrayList<Model_Kompetensi> listOfKompetensi = new ArrayList<Model_Kompetensi>();
                    while (elements.hasNext()) {
                        JsonNode item = elements.next();
                        Model_Kompetensi m = new Model_Kompetensi(item.asText());
                        m = m.getKompetensiByID();
                        listOfKompetensi.add(m);
                    }
                    pekerjaan.setDaftarKompetensi(listOfKompetensi);
                    listOfPekerjaan.add(pekerjaan);
                }
            }
        } catch (IOException e) {
            return null;
        }
        return listOfPekerjaan;
    }

    //CREATE PEKERJAAN
    public boolean tambahPekerjaan(String id, String nama, ArrayList<String> kompetensi) {
        Model_Pekerjaan pekerjaan = new Model_Pekerjaan();
        pekerjaan.setIdPekerjaan(id);
        pekerjaan.setNamaPekerjaan(nama);

        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!isPekerjaanExist(pekerjaan.getIdPekerjaan(), pekerjaan.getNamaPekerjaan())) {
                try {
                    JsonNode root = mapper.readTree(new File("dataJSON/Pekerjaan.json"));
                    ObjectNode data = mapper.createObjectNode();
                    data.put("idPekerjaan", pekerjaan.getIdPekerjaan());
                    data.put("namaPekerjaan", pekerjaan.getNamaPekerjaan());
                    ArrayNode array = mapper.valueToTree(kompetensi);
                    data.putArray("daftarKompetensi").addAll(array);
                    ((ArrayNode) root).add(data);
                    mapper.writeValue(new File("dataJSON/Pekerjaan.json"), ((ArrayNode) root));
                } catch (IOException IE) {
                    JsonFactory jasonFactory = new JsonFactory();
                    try (JsonGenerator jsonGenerator = jasonFactory.createGenerator(new File("dataJSON/Pekerjaan.json"), JsonEncoding.UTF8)) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("idPekerjaan", pekerjaan.getIdPekerjaan());
                        jsonGenerator.writeStringField("namaPekerjaan", pekerjaan.getNamaPekerjaan());
                        jsonGenerator.writeArrayFieldStart("daftarKompetensi"); //start kompetensi array
                        for (String data : kompetensi) {
                            jsonGenerator.writeString(data);
                        }
                        jsonGenerator.writeEndArray(); //closing kompetensi array
                        jsonGenerator.writeEndObject(); //closing properties
                        jsonGenerator.flush();
                        jsonGenerator.close();
                        return true;
                    }
                }
                return true;
            } else {
                System.out.println("Pekerjaan dengan ID" + pekerjaan.getIdPekerjaan() + " sudah ada dalam daftar.");
                return false;
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return false;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isPekerjaanExist(String id, String nama) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Pekerjaan.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    if (root.path("idPekerjaan").asText().equals(id) || root.path("namaPekerjaan").asText().equals(nama)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //UBAH PRASYARAT KOMPETENSI
    public boolean ubahReqKompetensiUntukPekerjaan(String id, ArrayList<String> reqKompetensi) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Pekerjaan.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("idPekerjaan").asText().equals(id)) {
                        ArrayNode array = mapper.valueToTree(reqKompetensi);
                        ((ObjectNode) root).putArray("daftarKompetensi").addAll(array);
                        mapper.writeValue(new File("dataJSON/Pekerjaan.json"), (ArrayNode) rootArray);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //UBAH NAMA PEKERJAAN
    public boolean ubahNamaPekerjaan(String id, String nama) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Pekerjaan.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("idPekerjaan").asText().equals(id)) {

                        ((ObjectNode) root).put("namaPekerjaan", nama);
                        mapper.writeValue(new File("dataJSON/Pekerjaan.json"), (ArrayNode) rootArray);
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //HAPUS PEKERJAAN
    public boolean hapusPekerjaan(String id) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Pekerjaan.json"));

            if (rootArray.isArray()) {
                int index = 0;
                for (JsonNode root : rootArray) {
                    /* Jika sesuai dengan nim */
                    if (root.path("idPekerjaan").asText().equals(id)) {
                        ((ArrayNode) rootArray).remove(index);
                        mapper.writeValue(new File("dataJSON/Pekerjaan.json"), (ArrayNode) rootArray);
                        return true;
                    }
                    index++;
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //get all kelas
    public ArrayList<Model_Kelas> getAllKelas() {
        ArrayList<Model_Kelas> listOfKelas = new ArrayList<Model_Kelas>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/KelasFinal.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    Model_Kelas kelas = new Model_Kelas();
                    kelas.setIdKelas(root.path("idKelas").asText());
                    kelas.setIdDosen(root.path("idDosen").asText());
                    kelas.setIdKompetensi(root.path("idKompetensi").asText());
                    kelas.setKuotaKelas(root.path("kuotaKelas").asInt());

                    JsonNode pesertaNode = root.path("pesertaKelas");
                    Iterator<JsonNode> pesertaElements = pesertaNode.elements();
                    ArrayList<Model_Mahasiswa> listOfPeserta = new ArrayList<Model_Mahasiswa>();
                    while (pesertaElements.hasNext()) {
                        JsonNode item = pesertaElements.next();
                        Model_Mahasiswa m = new Model_Mahasiswa();
                        m = m.getMahasiswaFromJson(item.asText());
                        listOfPeserta.add(m);
                    }
                    kelas.setPesertaKelas(listOfPeserta);

                    JsonNode waktuNode = root.path("slotWaktu");
                    Iterator<JsonNode> waktuElements = waktuNode.elements();
                    ArrayList<String> listOfWaktu = new ArrayList<String>();
                    while (waktuElements.hasNext()) {
                        JsonNode item = waktuElements.next();
                        listOfWaktu.add(item.asText());
                    }
                    kelas.setSlotWaktu(listOfWaktu);

                    listOfKelas.add(kelas);
                }
            }
        } catch (IOException e) {
            return null;
        }
        return listOfKelas;
    }

    //====================TAGIHAN==================
    public ArrayList<Model_Tagihan> getAllTagihan() {
        ArrayList<Model_Tagihan> listOfTagihan = new ArrayList<Model_Tagihan>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Tagihan.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    Model_Tagihan tagihan = new Model_Tagihan();
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
                    listOfTagihan.add(tagihan);
                }
                
            }
        } catch (IOException e) {
            return null;
        }
        return listOfTagihan;
    }

    public boolean validasiPembayaran(String username) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Tagihan.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    /* Jika id yang akan diedit sesuai */
                    if (root.path("mahasiswa").asText().equals(username)) {

                        ((ObjectNode) root).put("isLunas", true);
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