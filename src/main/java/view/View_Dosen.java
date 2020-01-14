package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Model_Dosen;
import model.Model_Kelas;
import model.Model_Kompetensi;
import model.Model_Waktu;

/**
 *
 * @author Liptia
 */
public class View_Dosen {
    
    private int pilihan;
    private String nama;
    private String nip;
    private ArrayList<String> kompetensi;
    private ArrayList<String> hariLibur;
    private String password;
    private String username;
    private String oldPassword;
    private Scanner input = new Scanner(System.in);
    private String tanggal;
    
    public void viewMenuDosen(String namaDosen, String username, String nip, ArrayList<Model_Kompetensi> kompetensi, ArrayList<String> hariLibur){
        System.out.println("");
        System.out.println("Nama: " + namaDosen + " | Username: " + username + " | NIP: " + nip);
        //tampilkan kompetensi
        int i =0;
        String tempKompetensi="{";
        
        if(kompetensi != null){
            for(Model_Kompetensi p: kompetensi)
            {
                if(i==0){
                    tempKompetensi += p.getNamaKompetensi();
                }else{
                    tempKompetensi += ", " + p.getNamaKompetensi();
                }
                i=1;
            }
        }
        tempKompetensi += "}";
        System.out.println("Kompetensi: " + tempKompetensi);
        
        //tampilkan hari libur
        i=0;
        String tempHariLibur= "{ ";
        if(hariLibur != null){
            for (String hl : hariLibur) {
               
                    if (i == 0) {
                        tempHariLibur += hl;
                    } else {
                        tempHariLibur += ", " + hl;
                    }
                    i = 1;

            }
        }
        tempHariLibur += "}";
        System.out.println("Hari Libur: " + tempHariLibur);
        System.out.println("");
        
        System.out.println("========================");
        System.out.println("====MENU UTAMA DOSEN====");
        System.out.println("========================");
        System.out.println("=  1. Kelola Data Diri =");       
        System.out.println("=  2. Ubah Password    =");
        System.out.println("=  3. Lihat Jadwal     =");
        System.out.println("=  4. Logout           =");
        System.out.println("========================");
        System.out.println("");
        
        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }
    
    public void viewMenuDosenSetelahGenerateJadwal(String namaDosen, String username, String nip, ArrayList<Model_Kompetensi> kompetensi, ArrayList<String> hariLibur){
        System.out.println("");
        System.out.println("Nama: " + namaDosen + " | Username: " + username + " | NIP: " + nip);
        //tampilkan kompetensi
        int i =0;
        String tempKompetensi="{";
        
        if(kompetensi != null){
            for(Model_Kompetensi p: kompetensi)
            {
                if(i==0){
                    tempKompetensi += p.getNamaKompetensi();
                }else{
                    tempKompetensi += ", " + p.getNamaKompetensi();
                }
                i=1;
            }
        }
        tempKompetensi += "}";
        System.out.println("Kompetensi: " + tempKompetensi);
        
        //tampilkan hari libur
        i=0;
        String tempHariLibur= "{ ";
        if(hariLibur != null){
            for (String hl : hariLibur) {
               
                    if (i == 0) {
                        tempHariLibur += hl;
                    } else {
                        tempHariLibur += ", " + hl;
                    }
                    i = 1;

            }
        }
        tempHariLibur += "}";
        System.out.println("Hari Libur: " + tempHariLibur);
        System.out.println("");
        
        System.out.println("========================");
        System.out.println("====MENU UTAMA DOSEN====");
        System.out.println("========================");      
        System.out.println("=  1. Ubah Password    =");
        System.out.println("=  2. Lihat Jadwal     =");
        System.out.println("=  3. Ganti Jadwal     =");
        System.out.println("=  4. Logout           =");
        System.out.println("========================");
        System.out.println("");
        
        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }
    
    public void viewEditDosenMenuUtama(){
        System.out.println("==========================");
        System.out.println("=====KELOLA DATA DIRI=====");
        System.out.println("==========================");
        System.out.println("=   1. Ubah Nama         =");
        System.out.println("=   2. Ubah NIP          =");
        System.out.println("=   3. Ubah Kompetensi   =");
        System.out.println("=   4. Ubah Hari Libur   =");
        System.out.println("=   99. Kembali          =");
        System.out.println("==========================");
        System.out.println("");
        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }
    
    public void viewCheckOldPasswordDosen()
    {
        System.out.println("");
        System.out.print(">>>>> Masukkan password lama : ");
        this.setOldPassword(input.next());
        System.out.println("");
    }
    
    public void viewUbahPasswordDosen()
    {
        System.out.print(">>>>> Masukkan password baru : ");
        this.setPassword(input.next());
        System.out.println("");
    }
    
    public void viewEditDosenUbahNama()
    {
        System.out.print("Masukkan nama baru : ");
        input.nextLine();
        this.setNama(input.nextLine());
    }
    
    public void viewEditDosenUbahNip()
    {
        System.out.print("Masukkan NIP baru : ");
        this.setNip(input.next());
    }
    
    public void viewEditDosenUbahKompetensi()
    {
        this.kompetensi = new ArrayList<String>();
        System.out.println("");
        System.out.println("Masukkan jumlah kompetensi: ");
        int jumlah = input.nextInt();
        String tempKompetensi;
        for (int i = 1; i <= jumlah; i++) {
           System.out.println("Masukkan ID Kompetensi ke-"+i+": "); 
           tempKompetensi = input.next();
           this.kompetensi.add(tempKompetensi);
        }
        this.setKompetensi(this.kompetensi);
    }
    
    public void viewEditDosenUbahHariLibur()
    {
        this.hariLibur = new ArrayList<String>();
        System.out.println("");
        System.out.println("Masukkan tanggal-tanggal cuti! ");
        System.out.println("Format: <DD/MM/YYYY>,<tDD/MM/YYYY>,<DD/MM/YYYY> (tanpa spasi)");
        String tempTanggal = input.next();
        
        //break down input
        String[] arrTempTanggal = tempTanggal.split(",");
        for (int i = 0; i < arrTempTanggal.length; i++) {
            hariLibur.add(arrTempTanggal[i]);
        }
        this.setHariLibur(this.hariLibur);
    }
    
    public void viewLihatJadwalKelasDosen(ArrayList<Model_Kelas> kelas)
    {
        if(kelas.isEmpty())
        {
            System.out.println("Jadwal Belum Tersedia");
        }
        else{
            for (Model_Kelas mk : kelas) {
                System.out.println("");
                //Kompetensi
                Model_Kompetensi kompetensi = new Model_Kompetensi(mk.getIdKompetensi());
                kompetensi = kompetensi.getKompetensiByID();
                System.out.println("==============================================");
                System.out.println(mk.getIdKelas() + ". Kelas " + kompetensi.getNamaKompetensi() + " (" + kompetensi.getBobotKompetensi() + " sks)");
                System.out.println("==============================================");

                //Jadwal
                System.out.println(">>>>>Waktu: ");
                System.out.format("%-25s%-30s%-20s\n", "Jenis Kelas", "Tanggal", "Jam");
                System.out.println("********************************************************************************************************");

                for (String waktu : mk.getSlotWaktu()) {
                    String tanggal = "";
                    String lamaKelas = "";
                    String[] kontenWaktu = waktu.split(",");
                    String jenisKelas = kontenWaktu[0] + " ";
                    for (int i = 1; i < kontenWaktu.length; i++) {
                        Model_Waktu jam = new Model_Waktu(kontenWaktu[i]);
                        jam = jam.getWaktuByID();
                        if(i == 1)
                        {
                            tanggal = jam.getTanggal();
                            lamaKelas += jam.getJam().split("-")[0] + " - ";
                        }

                        if(i == kontenWaktu.length - 1)
                        {
                            lamaKelas += jam.getJam().split("-")[1];
                        }
                    }
                    System.out.format("%-25s%-30s%-20s\n", jenisKelas , tanggal, lamaKelas);
                }
                System.out.println("********************************************************************************************************");
            }
        }
    }
    
    public void viewGantiJadwal()
    {
        System.out.println("Ganti Jadwal");
        System.out.println("Masukkan tanggal: <dd/mm/yyyy");
        this.setTanggal(input.next());
        System.out.println("");
    }
    
    public int getPilihan() {
        return pilihan;
    }

    public void setPilihan(int pilihan) {
        this.pilihan = pilihan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public ArrayList<String> getKompetensi() {
        return kompetensi;
    }

    public void setKompetensi(ArrayList<String> kompetensi) {
        this.kompetensi = kompetensi;
    }

    public ArrayList<String> getHariLibur() {
        return hariLibur;
    }

    public void setHariLibur(ArrayList<String> hariLibur) {
        this.hariLibur = hariLibur;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    public String getUsername() {
        return username;
    }
    
    public String getOldPassword() {
        return oldPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}