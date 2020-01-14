package model;

/**
 *
 * @author Trisna tia
 */
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Model_Kelas {

    private String idKelas;
    private String idKompetensi;
    private String idDosen;
    private ArrayList<String> slotWaktu;
    private int kuotaKelas;
    private ArrayList<Model_Mahasiswa> pesertaKelas;

    public Model_Kelas() {
    }

    public Model_Kelas(String id_kelas, String id_kompetensi, String id_dosen, ArrayList<String> slotWaktu) {
        this.idKelas = id_kelas;
        this.idKompetensi = id_kompetensi;
        this.idDosen = id_dosen;
        this.slotWaktu = slotWaktu;
    }

    public Model_Kelas(String idKelas, String idKompetensi, String idDosen, ArrayList<String> slotWaktu, int kuotaKelas) {
        this.idKelas = idKelas;
        this.idKompetensi = idKompetensi;
        this.idDosen = idDosen;
        this.slotWaktu = slotWaktu;
        this.kuotaKelas = kuotaKelas;
    }

    public Model_Kelas(String idKelas, String idKompetensi, String id_dosen, ArrayList<String> slotWaktu, int kuotaKelas, ArrayList<Model_Mahasiswa> pesertaKelas) {
        this.idKelas = idKelas;
        this.idKompetensi = idKompetensi;
        this.idDosen = id_dosen;
        this.slotWaktu = slotWaktu;
        this.kuotaKelas = kuotaKelas;
        this.pesertaKelas = pesertaKelas;
    }

    public String getIdDosen() {
        return idDosen;
    }

    public void setIdDosen(String id_dosen) {
        this.idDosen = id_dosen;
    }

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String id_kelas) {
        this.idKelas = id_kelas;
    }

    public String getIdKompetensi() {
        return idKompetensi;
    }

    public void setIdKompetensi(String id_kompetensi) {
        this.idKompetensi = id_kompetensi;
    }

    public ArrayList<String> getSlotWaktu() {
        return slotWaktu;
    }

    public void setSlotWaktu(ArrayList<String> slotWaktu) {
        this.slotWaktu = slotWaktu;
    }

    public int getKuotaKelas() {
        return kuotaKelas;
    }

    public void setKuotaKelas(int kuotaKelas) {
        this.kuotaKelas = kuotaKelas;
    }

    public ArrayList<Model_Mahasiswa> getPesertaKelas() {
        return pesertaKelas;
    }

    public void setPesertaKelas(ArrayList<Model_Mahasiswa> pesertaKelas) {
        this.pesertaKelas = pesertaKelas;
    }

    public void addKelasToJsonFile(Model_Kelas kelas) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!isKelasExist(kelas.getIdKelas())) {
                try {
                    JsonNode root = mapper.readTree(new File("dataJSON/Kelas.json"));
                    ObjectNode data = mapper.createObjectNode();
                    data.put("idKelas", kelas.getIdKelas());
                    data.put("idKompetensi", kelas.getIdKompetensi());
                    data.put("idDosen", kelas.getIdDosen());
                    
                    ArrayNode arraySlotWaktu = mapper.valueToTree(kelas.getSlotWaktu());
                    data.putArray("slotWaktu").addAll(arraySlotWaktu);
                    
                    data.put("kuotaKelas", kelas.getKuotaKelas());
                    
                    ArrayList<String> arrUsernamePesertaKelas = new ArrayList<String>();
                    for(Model_Mahasiswa m : kelas.getPesertaKelas())
                    {
                        arrUsernamePesertaKelas.add(m.getUsername());
                    }
                    ArrayNode arrayPesertaKelas = mapper.valueToTree(arrUsernamePesertaKelas);
                    data.putArray("pesertaKelas").addAll(arrayPesertaKelas);

                    ((ArrayNode) root).add(data);
                    mapper.writeValue(new File("dataJSON/Kelas.json"), ((ArrayNode) root));

                } catch (IOException IE) {
                    JsonFactory jasonFactory = new JsonFactory();
                    try (JsonGenerator jsonGenerator = jasonFactory.createGenerator(new File("dataJSON/Kelas.json"), JsonEncoding.UTF8)) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("idKelas", kelas.getIdKelas());
                        jsonGenerator.writeStringField("idKompetensi", kelas.getIdKompetensi());
                        jsonGenerator.writeStringField("idDosen", kelas.getIdDosen());
                        jsonGenerator.writeArrayFieldStart("slotWaktu"); //start kompetensi array
                        for (String data : kelas.getSlotWaktu()) {
                            jsonGenerator.writeString(data);
                        }
                        jsonGenerator.writeEndArray(); //closing kompetensi array

                        jsonGenerator.writeNumberField("kuotaKelas", kelas.getKuotaKelas());
                        
                        jsonGenerator.writeArrayFieldStart("pesertaKelas"); //start hari libur array
                        for (Model_Mahasiswa m : kelas.getPesertaKelas()) {
                            jsonGenerator.writeString(m.getUsername());
                        }
                        jsonGenerator.writeEndArray(); //closing hari libur array

                        jsonGenerator.writeEndObject(); //closing properties
                        jsonGenerator.flush();
                        jsonGenerator.close();
                    }
                }
            } else {
                System.out.println("Kelas dengan kompetensi: " + kelas.getIdKompetensi() + " sudah tergenerate.");
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void addKelasFinalToJsonFile(Model_Kelas kelas) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!isKelasFinalExist(kelas.getIdKelas())) {
                try {
                    JsonNode root = mapper.readTree(new File("dataJSON/KelasFinal.json"));
                    ObjectNode data = mapper.createObjectNode();
                    data.put("idKelas", kelas.getIdKelas());
                    data.put("idKompetensi", kelas.getIdKompetensi());
                    data.put("idDosen", kelas.getIdDosen());
                    
                    ArrayNode arraySlotWaktu = mapper.valueToTree(kelas.getSlotWaktu());
                    data.putArray("slotWaktu").addAll(arraySlotWaktu);
                    
                    data.put("kuotaKelas", kelas.getKuotaKelas());
                    
                    ArrayList<String> arrUsernamePesertaKelas = new ArrayList<String>();
                    for(Model_Mahasiswa m : kelas.getPesertaKelas())
                    {
                        arrUsernamePesertaKelas.add(m.getUsername());
                    }
                    ArrayNode arrayPesertaKelas = mapper.valueToTree(arrUsernamePesertaKelas);
                    data.putArray("pesertaKelas").addAll(arrayPesertaKelas);

                    ((ArrayNode) root).add(data);
                    mapper.writeValue(new File("dataJSON/KelasFinal.json"), ((ArrayNode) root));

                } catch (IOException IE) {
                    JsonFactory jasonFactory = new JsonFactory();
                    try (JsonGenerator jsonGenerator = jasonFactory.createGenerator(new File("dataJSON/KelasFinal.json"), JsonEncoding.UTF8)) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("idKelas", kelas.getIdKelas());
                        jsonGenerator.writeStringField("idKompetensi", kelas.getIdKompetensi());
                        jsonGenerator.writeStringField("idDosen", kelas.getIdDosen());
                        jsonGenerator.writeArrayFieldStart("slotWaktu"); //start kompetensi array
                        for (String data : kelas.getSlotWaktu()) {
                            jsonGenerator.writeString(data);
                        }
                        jsonGenerator.writeEndArray(); //closing kompetensi array

                        jsonGenerator.writeNumberField("kuotaKelas", kelas.getKuotaKelas());
                        
                        jsonGenerator.writeArrayFieldStart("pesertaKelas"); //start hari libur array
                        for (Model_Mahasiswa m : kelas.getPesertaKelas()) {
                            jsonGenerator.writeString(m.getUsername());
                        }
                        jsonGenerator.writeEndArray(); //closing hari libur array

                        jsonGenerator.writeEndObject(); //closing properties
                        jsonGenerator.flush();
                        jsonGenerator.close();
                    }
                }
            } else {
                System.out.println("Kelas dengan kompetensi: " + kelas.getIdKompetensi() + " sudah tergenerate.");
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isKelasExist(String idKelas) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kelas.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    // Jika id adalah yang ingin dihapus
                    if (root.path("idKelas").asText().equals(idKelas)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }
    
    public boolean isKelasFinalExist(String idKelas) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/KelasFinal.json"));

            if (rootArray.isArray()) {
                for (JsonNode root : rootArray) {
                    // Jika id adalah yang ingin dihapus
                    if (root.path("idKelas").asText().equals(idKelas)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public ArrayList<ArrayList<String>> hitungKompetensiInDosen() {
        ArrayList<ArrayList<String>> listKompetensiInDosen = new ArrayList<ArrayList<String>>();
        Model_Admin m = new Model_Admin();

        ArrayList<Model_Kompetensi> listOfKompetensi = new ArrayList<Model_Kompetensi>();
        listOfKompetensi = m.getAllKompetensi();

        ArrayList<Model_Dosen> listOfDosen = new ArrayList<Model_Dosen>();
        listOfDosen = m.getAllDosen();

        for (Model_Kompetensi k : listOfKompetensi) {
            ArrayList<String> dosenInKompetensi = new ArrayList<String>();
            dosenInKompetensi.add(k.getIdKompetensi());
            for (Model_Dosen d : listOfDosen) {
                ArrayList<Model_Kompetensi> kompetensiDosen = d.getKompetensi();
                for (Model_Kompetensi kd : kompetensiDosen) {
                    if (k.getIdKompetensi().equals(kd.getIdKompetensi())) {
                        dosenInKompetensi.add(d.getUsername());
                    }
                }
            }
            if (dosenInKompetensi.size() != 1) {
                listKompetensiInDosen.add(dosenInKompetensi);
            }
        }
        listKompetensiInDosen = this.hitungKompetensiInDosenSort(listKompetensiInDosen);
        int indexList = 0;
        for (ArrayList<String> kompetensiDosenSorted : listKompetensiInDosen) {
            int i = 0;
            ArrayList<String> tempHasil = new ArrayList<String>();
            tempHasil.add(kompetensiDosenSorted.get(0));
            ArrayList<String> listDosen = new ArrayList<String>();

            for (String dosen : kompetensiDosenSorted) {
                if (i != 0) {
                    listDosen.add(dosen);
                }
                i++;
            }

            ArrayList<String> selectedDosen = hitungLiburInDosen(listDosen);
            for (String d : selectedDosen) {
                tempHasil.add(d);
            }

            listKompetensiInDosen.set(indexList, tempHasil);
            indexList++;
        }
        return listKompetensiInDosen;

    }

    private ArrayList<ArrayList<String>> hitungKompetensiInDosenSort(ArrayList<ArrayList<String>> data) {
        Collections.sort(data, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> value1, ArrayList<String> value2) {
                if (value1.size() - 1 > value2.size() - 1) {
                    return 1;
                } else if (value1.size() - 1 < value2.size() - 1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return data;

    }

    public ArrayList<String> hitungLiburInDosen(ArrayList<String> dosen) {
        ArrayList<ArrayList<String>> listLiburInDosen = new ArrayList<ArrayList<String>>();

        for (String d : dosen) {
            Model_Dosen md = new Model_Dosen();
            md = md.getDosenFromJson(d);
            ArrayList<String> liburDosen = new ArrayList<String>();
            liburDosen.add(md.getUsername());
            for (String libur : md.getHariLibur()) {
                liburDosen.add(libur);
            }
            listLiburInDosen.add(liburDosen);
        }

        this.hitungLiburInDosenSort(listLiburInDosen);

        //ArrayList dosen hasil yang sudah disort
        ArrayList<String> sortedDosen = new ArrayList<String>();
        for (ArrayList<String> d : listLiburInDosen) {
            sortedDosen.add(d.get(0));
        }
        //return listLiburInDosen.get(0).get(0);
        return sortedDosen;
    }

    private ArrayList<ArrayList<String>> hitungLiburInDosenSort(ArrayList<ArrayList<String>> data) {
        Collections.sort(data, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> value1, ArrayList<String> value2) {
                if (value1.size() - 1 < value2.size() - 1) {
                    return 1;
                } else if (value1.size() - 1 > value2.size() - 1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return data;

    }

    public void plotJadwal() {
        
        ArrayList<ArrayList<String>> dataKompetensiDanDosen = this.hitungKompetensiInDosen();
        Model_Waktu mw = new Model_Waktu();
        ArrayList<Model_Waktu> listOfWaktu = mw.getAllWaktu();
        
        //counter untuk id kelas
        int idKelas = 1;
        
        //tampung id dosen yang berhasil diassign ke jadwal
        String finalDosen = "";

        for (ArrayList<String> kd : dataKompetensiDanDosen) {
            //Temp slot waktu untuk kelas
            ArrayList<String> arrJamKelas = new ArrayList<String>();
            ArrayList<String> arrJamKelasPraktikum = new ArrayList<String>();
            //menyimpan daftar waktu untuk suatu pertemuan
            String jamKelas = "";
                
            //get kompetensinya
            Model_Kompetensi kompetensi = new Model_Kompetensi(kd.get(0));
            kompetensi = kompetensi.getKompetensiByID();

            boolean isDosenFound = false;
            int indexDosen = 1;
            //iterasi dosennya
            while (isDosenFound == false && indexDosen < kd.size()) {
                //get dosennya
                finalDosen = kd.get(indexDosen);
                //get hari libur dosen
                Model_Dosen d = new Model_Dosen();
                d = d.getDosenFromJson(finalDosen);
                ArrayList<String> liburDosen = d.getHariLibur();

                for (int pertemuan = 0; pertemuan < 10; pertemuan++) {

                    //counter untuk iterasi waktu satu per satu
                    int count = 0;
                    count = pertemuan*32+(idKelas*9);

                    while (listOfWaktu.size() - kompetensi.getBobotKompetensi() > count) {
                        boolean isDosenLibur = false;
                        int indexHariLibur = 0;
                        while (listOfWaktu.get(count).getTanggal().equals(liburDosen.get(indexHariLibur))) {
                            if (listOfWaktu.get(count).getTanggal().equals(liburDosen.get(indexHariLibur))) {
                                isDosenLibur = true;
                            }
                            indexHariLibur++;
                        }

                        if (isDosenLibur) {
                            count++;
                        } else {
                            //boolean untuk menentukan jadwalnya tersedia untuk sejumlah bobot atau tidak
                            boolean isTersedia = true;

                            //cek agar tidak ada jadwal 1 kuliah yg terpecah berbeda hari
                            ArrayList<String> listTanggal = new ArrayList<String>();

                            //diiterasi sebanyak jumlah bobot, for sampai ketemu jadwal untuk sejumlah bobot
                            int tempCount = count;
                            jamKelas = "TatapMuka";
                            for (int i = 0; i < kompetensi.getBobotKompetensi(); i++) {
                                ArrayList<String> arrFlagOwner = listOfWaktu.get(tempCount).getFlagOwner();
                                for (String flagOwner : arrFlagOwner) {
                                    //break down isi flag
                                    String[] arrFlag = flagOwner.split(",");

                                    //cocokin kompetensi
                                    if (arrFlag[0].equals(kompetensi.getIdKompetensi()) && arrFlag[1].equals(finalDosen)) {
                                        isTersedia = false;
                                    }
                                }
                                //masukkan tanggal
                                listTanggal.add(listOfWaktu.get(tempCount).getTanggal());
                                tempCount++;
                            }

                            //cek jika ada kelas yg terpisah hari
                            String tempFirstDate = listTanggal.get(0);
                            for (String tanggal : listTanggal) {
                                if (!tempFirstDate.equals(tanggal)) {
                                    isTersedia = false;
                                }
                                tempFirstDate = tanggal;
                            }

                            if (isTersedia == true) { //akan keluar dari while
                                tempCount = count;
                                for (int i = 0; i < kompetensi.getBobotKompetensi(); i++) {
                                    ArrayList<String> arrFlagOwner = listOfWaktu.get(tempCount).getFlagOwner();
                                    arrFlagOwner.add(kompetensi.getIdKompetensi() + "," + finalDosen + "," + "TatapMuka," + (pertemuan + 1));
                                    listOfWaktu.get(tempCount).setFlagOwner(arrFlagOwner);
                                    //listOfWaktu.get(tempCount).jsonFileSetFlagOwner(arrFlagOwner);
                                    jamKelas += ","+ listOfWaktu.get(tempCount).getIdWaktu();
                                    tempCount++;
                                }
                                
                                arrJamKelas.add(jamKelas);
                                jamKelas = "";

                                //perulangan untukpraktukum
                                if(kompetensi.getIsPraktikum().equals("P")){
                                    int countPraktikum = tempCount + 9;
                                    while (listOfWaktu.size() - kompetensi.getBobotKompetensi() > countPraktikum) {
                                        //boolean untuk menentukan jadwalnya tersedia untuk sejumlah bobot atau tidak
                                        boolean isTersediaPraktikum = true;

                                        //cek agar tidak ada jadwal 1 kuliah yg terpecah berbeda hari
                                        ArrayList<String> listTanggalPraktikum = new ArrayList<String>();

                                        //diiterasi sebanyak jumlah bobot, for sampai ketemu jadwal untuk sejumlah bobot
                                        int tempCountPraktikum = countPraktikum;

                                        jamKelas = "Praktikum";
                                        for (int i = 0; i < kompetensi.getBobotKompetensi(); i++) {
                                            ArrayList<String> arrFlagOwnerPraktikum = listOfWaktu.get(tempCountPraktikum).getFlagOwner();
                                            for (String flagOwner : arrFlagOwnerPraktikum) {
                                                //break down isi flag
                                                String[] arrFlag = flagOwner.split(",");
                                                //cocokin kompetensi
                                                if (arrFlag[0].equals(kompetensi.getIdKompetensi()) && arrFlag[1].equals(finalDosen)) {
                                                    isTersedia = false;
                                                }
                                            }
                                            //masukkan tanggal praktikum
                                            listTanggalPraktikum.add(listOfWaktu.get(tempCountPraktikum).getTanggal());
                                            tempCountPraktikum++;
                                        }

                                        //cek jika ada kelas yg terpisah hari praktikumnya
                                        String tempFirstDatePraktikum = listTanggalPraktikum.get(0);
                                        for (String tanggalP : listTanggalPraktikum) {
                                            if (!tempFirstDatePraktikum.equals(tanggalP)) {
                                                isTersediaPraktikum = false;
                                            }
                                            tempFirstDatePraktikum = tanggalP;
                                        }

                                        if (isTersediaPraktikum == true) { //akan keluar dari while
                                            tempCountPraktikum = countPraktikum;
                                            for (int i = 0; i < kompetensi.getBobotKompetensi(); i++) {
                                                ArrayList<String> arrFlagOwnerPraktikum = listOfWaktu.get(tempCountPraktikum).getFlagOwner();
                                                arrFlagOwnerPraktikum.add(kompetensi.getIdKompetensi() + "," + finalDosen + "," + "Praktikum," + (pertemuan+ 1));
                                                listOfWaktu.get(tempCountPraktikum).setFlagOwner(arrFlagOwnerPraktikum);
                                                //listOfWaktu.get(tempCount).jsonFileSetFlagOwner(arrFlagOwnerPraktikum);
                                                jamKelas += "," + listOfWaktu.get(tempCountPraktikum).getIdWaktu();
                                                tempCountPraktikum++;
                                            }

                                            arrJamKelasPraktikum.add(jamKelas);
                                            jamKelas = "";

                                            isDosenFound = true;
                                            countPraktikum = listOfWaktu.size();
                                        } else { //lanjut cari waktu available
                                            countPraktikum++;
                                        }
                                    }//akhir perulangan praktikum
                                }  //akhir penanganan praktikum
                                
                                isDosenFound = true;
                                count = listOfWaktu.size();
                            } else { //lanjut cari waktu available
                                count++;
                            }
                        }//tutup if cek hari libur dosen
                    }
                }
                //isDosenFound = true;
                indexDosen++;

            }

            for(String jam : arrJamKelasPraktikum)
            {
                arrJamKelas.add(jam);
            }
            
            //masukkan hasil plot jadwal ke file Kelas.json
            Model_Kelas kelas = new Model_Kelas();
            kelas.setIdKelas(String.valueOf(idKelas));
            kelas.setIdKompetensi(kompetensi.getIdKompetensi());
            kelas.setIdDosen(finalDosen);
            kelas.setSlotWaktu(arrJamKelas);
            kelas.setKuotaKelas(0);
           
            ArrayList<Model_Mahasiswa> m = new ArrayList<Model_Mahasiswa>();
            kelas.setPesertaKelas(m);
            addKelasToJsonFile(kelas);
            idKelas++;
        }

        int indexPrint = 0;
        for (Model_Waktu m : listOfWaktu) {
           
            //System.out.println(m.getTanggal() + " " + m.getJam() + " " + m.getFlagOwner());
            //System.out.println(m.getFlagOwner());
            listOfWaktu.get(indexPrint).jsonFileSetFlagOwner(m.getFlagOwner());
            indexPrint++;
        }
    }
    
    //=======================OPTIMASI 1=========================================
    public void optimasi1()
    {
        Model_Tagihan tagihan = new Model_Tagihan();
        Model_Admin admin = new Model_Admin();
        int kuotaKelas = 0;

        ArrayList<Model_Mahasiswa> arrAllMahasiswa = admin.getAllMahasiswa();
        ArrayList<Model_Pekerjaan> arrAllPekerjaan = admin.getAllPekerjaan();
        ArrayList<Model_Kompetensi> arrAllKompetensi = admin.getAllKompetensi();
        
        for(Model_Mahasiswa mahasiswa : arrAllMahasiswa)
        {
            ArrayList<String> arrKelasYangBerhasilDiambilMahasiswa = new ArrayList<String>();
            //format isi dari array {P,K1,K2,..,Kn}
            ArrayList<ArrayList<String>> arrKompetensiYangBelumDimilikiMahasiswa = new  ArrayList<ArrayList<String>>();
                        
            //pekerjaannya mahasiswa
            Model_Pekerjaan[] arrPekerjaanMahasiswa = mahasiswa.getPekerjaan();
            for (int i = 0; i < arrPekerjaanMahasiswa.length; i++) {
                //ArrayList untuk nampung  {P,K1,K2,..,Kn} per pekerjaan
                ArrayList<String> data = new  ArrayList<String>();
                
                //get kompetensi milik mahasiswa
                ArrayList<Model_Kompetensi> arrKompetensiMahasiswa = mahasiswa.getKompetensi();
                
                //get kompetensi yang dimiliki suatu pekerjaan
                Model_Pekerjaan mp = new Model_Pekerjaan(arrPekerjaanMahasiswa[i].getIdPekerjaan());
                mp = mp.getPekerjaanByID();
                ArrayList<Model_Kompetensi> arrKompetensiPekerjaan = mp.getDaftarKompetensi();
               
                
                //isi index pertama dengan pekerjaannya sebelum diisi dengan kompetensi yg belum ada
                data.add(arrPekerjaanMahasiswa[i].getIdPekerjaan());
                
                for(Model_Kompetensi kompPekerjaan : arrKompetensiPekerjaan)
                {
                    //jika kompetensi di pekerjaan tidak dimiliki oleh mahasiswa
                    boolean isNotFound = true;
                    for(Model_Kompetensi kompMahasiswa : arrKompetensiMahasiswa)
                    {
                        if(kompPekerjaan.getIdKompetensi().equals(kompMahasiswa.getIdKompetensi()))
                        {
                            isNotFound = false;
                        }
                    }
                    
                    if(isNotFound == true)
                    {
                        data.add(kompPekerjaan.getIdKompetensi());
                    }     
                }
                arrKompetensiYangBelumDimilikiMahasiswa.add(data);
            }
            
            //sorting pekerjaan mahasiswa yang defisit kompetensinya paling sedikit
            arrKompetensiYangBelumDimilikiMahasiswa = sortDefisitKompetensiMahasiswaAsc(arrKompetensiYangBelumDimilikiMahasiswa);
            
            //looping hasil sorting dan cek satu per satu kesesuain kompetensi yang dimiliki dengan prasyaratnya
            int count = 0;
            boolean isPekerjaanSelected = false;
            while(count < arrKompetensiYangBelumDimilikiMahasiswa.size()&&isPekerjaanSelected==false)
            {
                isPekerjaanSelected = false;
                ArrayList<String> data = arrKompetensiYangBelumDimilikiMahasiswa.get(count);
                //looping per kompetensinya
                int indexChecker = 0;
                for(String kompetensi : data)
                {
                    boolean isFound = false;
                    if(indexChecker != 0)
                    {
                        Model_Kompetensi k = new Model_Kompetensi(kompetensi);
                        k = k.getKompetensiByID();
                        ArrayList<Model_Kompetensi> arrPrasyaratKompetensi = k.getDaftarReqKompetensi();
                        
                        //looping per prasyarat
                        for(Model_Kompetensi prasyarat : arrPrasyaratKompetensi)
                        {
                            //ini meriksa dengan prasyaratnya
                            if(prasyarat.getIdKompetensi().equals(kompetensi))
                            {
                                isFound = true;
                            }
                            
                            //ini meriksa dengan kompetensi yang dimiliki mahasiswa
                            for(Model_Kompetensi kompetensiMahasiswa : mahasiswa.getKompetensi())
                            {
                                if(kompetensiMahasiswa.getIdKompetensi().equals(prasyarat.getIdKompetensi()))
                                {
                                    isFound = true;
                                }
                            }
                        }
                    }
                    
                    ///kalo isFound dan kalo tidak bentrok masukkin ke kelas
                    if(isFound == true && indexChecker != 0)
                    {
                        if(isJadwalMahasiswaTidakBentrok(mahasiswa.getUsername(),kompetensi) == true)
                        {
                            if(getKuotaKelasFromJson(kompetensi) < 25){
                                isPekerjaanSelected = true;
                                this.addPesertaKelasToJson(kompetensi, mahasiswa.getUsername());
                                arrKelasYangBerhasilDiambilMahasiswa.add(kompetensi);
                                kuotaKelas++;
                                this.setKuotaKelasToJson(kompetensi, getKuotaKelasFromJson(kompetensi)+1);
                            }
                        }
                    }
                    //terus kalo udah ada setidaknya 1 yang memenuhi, ga perlu
                    indexChecker++;
                }
                count++;
            }
        }
        this.deleteKelasYangTidakMemenuhiSyarat();
        
        //tambahkan data mahasiswa beserta tagihan ke file Tagihan.json
        Model_Admin a = new Model_Admin();
        ArrayList<Model_Mahasiswa> arrMahasiswa = a.getAllMahasiswa();
        for(Model_Mahasiswa m : arrMahasiswa)
        {
            //untuk update kompetensi mahasiswa
            ArrayList<String> arrKompetensiMahasiswa = new ArrayList<String>();
            for(Model_Kompetensi kompetensi : m.getKompetensi())
            {
                arrKompetensiMahasiswa.add(kompetensi.getIdKompetensi());
            }
            
            ArrayList<String> arrKelasMahasiswa = new ArrayList<String>();
            for(Model_Kelas k : m.getKelasByUsernameMahasiswa())
            {
                arrKelasMahasiswa.add(k.getIdKompetensi());
                arrKompetensiMahasiswa.add(k.getIdKompetensi());
            }
            //set tagihan
            tagihan.generateTagihanMahasiswaToJson(arrKelasMahasiswa, m.getUsername());
            //update kompetensi mahasiswa
            m.jsonFileSetKompetensiMahasiswa(arrKompetensiMahasiswa);
        }    
    }
    
    //=======================OPTIMASI 2=========================================
    public void optimasi2()
    {
        Model_Tagihan tagihan = new Model_Tagihan();
        Model_Admin admin = new Model_Admin();
        int kuotaKelas = 0;

        ArrayList<Model_Mahasiswa> arrAllMahasiswa = admin.getAllMahasiswa();
        ArrayList<Model_Pekerjaan> arrAllPekerjaan = admin.getAllPekerjaan();
        ArrayList<Model_Kompetensi> arrAllKompetensi = admin.getAllKompetensi();
        
        for(Model_Mahasiswa mahasiswa : arrAllMahasiswa)
        {
            ArrayList<String> arrKelasYangBerhasilDiambilMahasiswa = new ArrayList<String>();
            //format isi dari array {P,K1,K2,..,Kn}
            ArrayList<ArrayList<String>> arrKompetensiYangBelumDimilikiMahasiswa = new  ArrayList<ArrayList<String>>();
                        
            //pekerjaannya mahasiswa
            Model_Pekerjaan[] arrPekerjaanMahasiswa = mahasiswa.getPekerjaan();
            for (int i = 0; i < arrPekerjaanMahasiswa.length; i++) {
                //ArrayList untuk nampung  {P,K1,K2,..,Kn} per pekerjaan
                ArrayList<String> data = new  ArrayList<String>();
                
                //get kompetensi milik mahasiswa
                ArrayList<Model_Kompetensi> arrKompetensiMahasiswa = mahasiswa.getKompetensi();
                
                //get kompetensi yang dimiliki suatu pekerjaan
                Model_Pekerjaan mp = new Model_Pekerjaan(arrPekerjaanMahasiswa[i].getIdPekerjaan());
                mp = mp.getPekerjaanByID();
                ArrayList<Model_Kompetensi> arrKompetensiPekerjaan = mp.getDaftarKompetensi();
               
                
                //isi index pertama dengan pekerjaannya sebelum diisi dengan kompetensi yg belum ada
                data.add(arrPekerjaanMahasiswa[i].getIdPekerjaan());
                
                for(Model_Kompetensi kompPekerjaan : arrKompetensiPekerjaan)
                {
                    //jika kompetensi di pekerjaan tidak dimiliki oleh mahasiswa
                    boolean isNotFound = true;
                    for(Model_Kompetensi kompMahasiswa : arrKompetensiMahasiswa)
                    {
                        if(kompPekerjaan.getIdKompetensi().equals(kompMahasiswa.getIdKompetensi()))
                        {
                            isNotFound = false;
                        }
                    }
                    
                    if(isNotFound == true)
                    {
                        data.add(kompPekerjaan.getIdKompetensi());
                    }     
                }
                arrKompetensiYangBelumDimilikiMahasiswa.add(data);
            }
            
            //sorting pekerjaan mahasiswa yang defisit kompetensinya paling sedikit
            arrKompetensiYangBelumDimilikiMahasiswa = sortDefisitKompetensiMahasiswaDesc(arrKompetensiYangBelumDimilikiMahasiswa);
            
            //looping hasil sorting dan cek satu per satu kesesuain kompetensi yang dimiliki dengan prasyaratnya
            int count = 0;
            boolean isPekerjaanSelected = false;
            while(count < arrKompetensiYangBelumDimilikiMahasiswa.size()&&isPekerjaanSelected==false)
            {
                isPekerjaanSelected = false;
                ArrayList<String> data = arrKompetensiYangBelumDimilikiMahasiswa.get(count);
                //looping per kompetensinya
                int indexChecker = 0;
                for(String kompetensi : data)
                {
                    boolean isFound = false;
                    if(indexChecker != 0)
                    {
                        Model_Kompetensi k = new Model_Kompetensi(kompetensi);
                        k = k.getKompetensiByID();
                        ArrayList<Model_Kompetensi> arrPrasyaratKompetensi = k.getDaftarReqKompetensi();
                        
                        //looping per prasyarat
                        for(Model_Kompetensi prasyarat : arrPrasyaratKompetensi)
                        {
                            //ini meriksa dengan prasyaratnya
                            if(prasyarat.getIdKompetensi().equals(kompetensi))
                            {
                                isFound = true;
                            }
                            
                            //ini meriksa dengan kompetensi yang dimiliki mahasiswa
                            for(Model_Kompetensi kompetensiMahasiswa : mahasiswa.getKompetensi())
                            {
                                if(kompetensiMahasiswa.getIdKompetensi().equals(prasyarat.getIdKompetensi()))
                                {
                                    isFound = true;
                                }
                            }
                        }
                    }
                    
                    ///kalo isFound dan kalo tidak bentrok masukkin ke kelas
                    if(isFound == true && indexChecker != 0)
                    {
                        if(isJadwalMahasiswaTidakBentrok(mahasiswa.getUsername(),kompetensi) == true)
                        {
                            if(getKuotaKelasFromJson(kompetensi) < 25){
                                isPekerjaanSelected = true;
                                this.addPesertaKelasToJson(kompetensi, mahasiswa.getUsername());
                                arrKelasYangBerhasilDiambilMahasiswa.add(kompetensi);
                                kuotaKelas++;
                                this.setKuotaKelasToJson(kompetensi, getKuotaKelasFromJson(kompetensi)+1);
                            }
                        }
                    }
                    //terus kalo udah ada setidaknya 1 yang memenuhi, ga perlu
                    indexChecker++;
                }
                count++;
            }
        }
        this.deleteKelasYangTidakMemenuhiSyarat();
        
        //tambahkan data mahasiswa beserta tagihan ke file Tagihan.json
        Model_Admin a = new Model_Admin();
        ArrayList<Model_Mahasiswa> arrMahasiswa = a.getAllMahasiswa();
        for(Model_Mahasiswa m : arrMahasiswa)
        {
            //untuk update kompetensi mahasiswa
            ArrayList<String> arrKompetensiMahasiswa = new ArrayList<String>();
            for(Model_Kompetensi kompetensi : m.getKompetensi())
            {
                arrKompetensiMahasiswa.add(kompetensi.getIdKompetensi());
            }
            
            ArrayList<String> arrKelasMahasiswa = new ArrayList<String>();
            for(Model_Kelas k : m.getKelasByUsernameMahasiswa())
            {
                arrKelasMahasiswa.add(k.getIdKompetensi());
                arrKompetensiMahasiswa.add(k.getIdKompetensi());
            }
            //set tagihan
            tagihan.generateTagihanMahasiswaToJson(arrKelasMahasiswa, m.getUsername());
            //update kompetensi mahasiswa
            m.jsonFileSetKompetensiMahasiswa(arrKompetensiMahasiswa);
        }   
    }
    
      
    private ArrayList<ArrayList<String>> sortDefisitKompetensiMahasiswaAsc(ArrayList<ArrayList<String>> data) {
        Collections.sort(data, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> value1, ArrayList<String> value2) {
                if (value1.size() - 1 > value2.size() - 1) {
                    return 1;
                } else if (value1.size() - 1 < value2.size() - 1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return data;

    }
    
    private ArrayList<ArrayList<String>> sortDefisitKompetensiMahasiswaDesc(ArrayList<ArrayList<String>> data) {
        Collections.sort(data, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> value1, ArrayList<String> value2) {
                if (value1.size() - 1 < value2.size() - 1) {
                    return 1;
                } else if (value1.size() - 1 > value2.size() - 1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return data;

    }
    
    private boolean isJadwalMahasiswaTidakBentrok(String usernameMahasiswa, String idKompetensiBaru)
    {
        //kalo tidak bentrok
        boolean isTidakBentrok = true;
        //baca kelas sesuai idKompetensi baru (ambil jam2nya)
        ArrayList<String> arrWaktuKelasKompetensiBaru = this.getSlotWaktuByIdKompetensiFromJson(idKompetensiBaru);
        //get all kelas yang terdapat nama idmahasiswa (ambil jam2nya)
        ArrayList<String> arrWaktuKelasMilikMahasiswa = this.getSlotWaktuKelasMilikMahasiswaFromJson(usernameMahasiswa);
        //kalo ada yang sama langsung return false
        
        int counterWaktuByKompetensi =0;
        while(counterWaktuByKompetensi < arrWaktuKelasKompetensiBaru.size() && isTidakBentrok == true){
            int counterWaktuByMahasiswa = 0;
            while(counterWaktuByMahasiswa < arrWaktuKelasMilikMahasiswa.size() && isTidakBentrok == true)
            {
                if(arrWaktuKelasKompetensiBaru.get(counterWaktuByKompetensi).equals(arrWaktuKelasMilikMahasiswa.get(counterWaktuByMahasiswa)))
                {
                    isTidakBentrok = false;
                }
                counterWaktuByMahasiswa++;
            }
            counterWaktuByKompetensi++;
        }
        return isTidakBentrok;
    }
    
    private void addPesertaKelasToJson(String idKompetensi, String usernameMahasiswa)
    {
        ArrayList<String> arrUsernameMahasiwa = new ArrayList<String>();
        
        ArrayList<Model_Mahasiswa> arrMahasiswaLama = getPesertaKelasFromJson(idKompetensi);
        
        if(!arrMahasiswaLama.isEmpty())
        {
            for(Model_Mahasiswa m : arrMahasiswaLama)
            {
                arrUsernameMahasiwa.add(m.getUsername());
            }
        }
        
        arrUsernameMahasiwa.add(usernameMahasiswa);
        
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kelas.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    if(root.path("idKompetensi").asText().equals(idKompetensi)){
                        
                        ArrayNode array = mapper.valueToTree(arrUsernameMahasiwa);
                        ((ObjectNode) root ).putArray("pesertaKelas").addAll(array);
                        mapper.writeValue(new File("dataJSON/Kelas.json"), (ArrayNode) rootArray);
                        
//                        this.setPesertaKelas(arrUsernameMahasiwa);
                    }                    
                }
            }
        }catch(IOException e){
        }
    }
    
    private ArrayList<Model_Mahasiswa> getPesertaKelasFromJson(String idKompetensi)
    {
        
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kelas.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    if(root.path("idKompetensi").asText().equals(idKompetensi)){
                        JsonNode mahasiswaNode = root.path("pesertaKelas");
                        Iterator<JsonNode> elements = mahasiswaNode.elements();
                        ArrayList<Model_Mahasiswa> arrMahasiswa = new ArrayList<Model_Mahasiswa>();
                        while(elements.hasNext()){
                                JsonNode item = elements.next();
                                Model_Mahasiswa m = new Model_Mahasiswa();
                                m = m.getMahasiswaFromJson(item.asText());
                                arrMahasiswa.add(m);
                        }
                        return arrMahasiswa;
                    }                    
                }
            }
            return null;
        }catch(IOException e){
            return null;
        }
    }
    
    private int getKuotaKelasFromJson(String idKompetensi)
    {
        
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kelas.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    if(root.path("idKompetensi").asText().equals(idKompetensi)){
                        return root.path("kuotaKelas").asInt();
                    }                    
                }
            }
            return 0;
        }catch(IOException e){
            return 0;
        }
    }
    
    private void setKuotaKelasToJson(String idKompetensi, int kuota)
    {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kelas.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    if(root.path("idKompetensi").asText().equals(idKompetensi)){
                        
                        ((ObjectNode) root ).put("kuotaKelas", kuota);
                        mapper.writeValue(new File("dataJSON/Kelas.json"), (ArrayNode) rootArray);
                    }                    
                }
            }
        }catch(IOException e){
        }
    }
    
    private ArrayList<String> getSlotWaktuByIdKompetensiFromJson(String idKompetensi)
    {
        
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kelas.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    if(root.path("idKompetensi").asText().equals(idKompetensi)){
                        JsonNode slotWaktuNode = root.path("slotWaktu");
                        Iterator<JsonNode> elements = slotWaktuNode.elements();
                        //slot waktu yang sudah displit hanya diambil id waktu saja
                        ArrayList<String> arrSlotWaktu = new ArrayList<String>();
                        while (elements.hasNext()) {
                            JsonNode item = elements.next();
                            String[] arrRawSlotWaktu = item.asText().split(",");
                            for (int i = 1; i < arrRawSlotWaktu.length; i++) {
                                arrSlotWaktu.add(arrRawSlotWaktu[i]);
                            }
                        }
                        return arrSlotWaktu;
                    }                    
                }
            }
            return null;
        }catch(IOException e){
            return null;
        }
    }
    
    private ArrayList<String> getSlotWaktuKelasMilikMahasiswaFromJson(String usernameMahasiswa)
    {
        
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kelas.json"));
            //slot waktu yang sudah displit hanya diambil id waktu saja
            ArrayList<String> arrSlotWaktu = new ArrayList<String>();

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    JsonNode pesertaKelasNode = root.path("pesertaKelas");
                    Iterator<JsonNode> elements = pesertaKelasNode.elements();
                    ArrayList<String> arrPesertaKelas = new ArrayList<String>();
                    boolean isFound = false;
                    //looping mahasiswanya
                    while (elements.hasNext() && isFound == false) {
                        JsonNode item = elements.next();
                        if(item.asText().equals(usernameMahasiswa))
                        {
                            isFound = true;
                        }
                    }
                    
                    if(isFound)
                    {
                        JsonNode slotWaktuNode = root.path("slotWaktu");
                        Iterator<JsonNode> slotWaktuElements = slotWaktuNode.elements();
                        while (slotWaktuElements.hasNext()) {
                            JsonNode item = slotWaktuElements.next();
                            String[] arrRawSlotWaktu = item.asText().split(",");
                            for (int i = 1; i < arrRawSlotWaktu.length; i++) {
                                arrSlotWaktu.add(arrRawSlotWaktu[i]);
                            }
                        }
                    }
                }
                return arrSlotWaktu;
            }
            return null;
        }catch(IOException e){
            return null;
        }
    }
    
    //delete kelas yang pesertanya kurang dari 10
    private void deleteKelasYangTidakMemenuhiSyarat()
    {
        ArrayList<Model_Kelas> listOfKelas = new ArrayList<Model_Kelas>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kelas.json"));

            if (rootArray.isArray()) {
                int index = 0;
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
                        //tagihan.generateTagihanMahasiswaToJson(arrKelasYangBerhasilDiambilMahasiswa, mahasiswa.getUsername());
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

                    if(kelas.getKuotaKelas() > 10)
                    {
                        listOfKelas.add(kelas);
                    }
                    index++;
                }
            }
            
            for(Model_Kelas k : listOfKelas)
            {
                this.addKelasFinalToJsonFile(k);
            }
        } catch (IOException e) {
        }
    }
    
    public void setWaktuKelasToJson(String idKompetensi, ArrayList<String> arrWaktu)
    {        
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/Kelas.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    if(root.path("idKompetensi").asText().equals(idKompetensi)){                   
                        ArrayNode array = mapper.valueToTree(arrWaktu);
                        ((ObjectNode) root ).putArray("slotWaktu").addAll(array);
                        mapper.writeValue(new File("dataJSON/Kelas.json"), (ArrayNode) rootArray);                        
                    }                    
                }
            }
        }catch(IOException e){
        }
    }
}