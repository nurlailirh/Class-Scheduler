package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Model_Dosen;
import model.Model_Kelas;
import model.Model_Kompetensi;
import model.Model_Mahasiswa;
import model.Model_Pekerjaan;
import model.Model_Waktu;

/**
 *
 * @author Liptia
 */
public class View_Mahasiswa {

    private int pilihan;
    private String nama;
    private String nim;
    private ArrayList<String> kompetensi;
    private String[] pekerjaan;
    private String password;
    private String username;
    private String oldPassword;
    private Scanner input = new Scanner(System.in);
    private String kodeTransaksiPembayaran;
    private String tanggalTransaksiPembayaran;

    public void viewMenuMahasiswa(String namaMahasiswa, String username, String nim, ArrayList<Model_Kompetensi> kompetensi, Model_Pekerjaan[] pekerjaan) {
        System.out.println("");
        System.out.println("Nama: " + namaMahasiswa + " | Username: " + username + " | NIM: " + nim);
        //tampilkan kompetensi
        int i = 0;
        String tempKompetensi = "{ ";

        if (kompetensi != null) {
            for (Model_Kompetensi p : kompetensi) {
                if (i == 0) {
                    tempKompetensi += p.getNamaKompetensi();
                } else {
                    tempKompetensi += ", " + p.getNamaKompetensi();
                }
                i = 1;
            }
        }
        tempKompetensi += "}";
        System.out.println("Kompetensi: " + tempKompetensi);

        //tampilkan pekerjaan
        i = 0;
        String tempPekerjaan = "{ ";
        if (pekerjaan[0] == null) {
            tempPekerjaan = "{}";
        } else {
            for (Model_Pekerjaan p : pekerjaan) {

                if (i == 0) {
                    tempPekerjaan += p.getNamaPekerjaan();
                } else {
                    tempPekerjaan += ", " + p.getNamaPekerjaan();
                }
                i = 1;

            }
        }

        tempPekerjaan += "}";
        System.out.println("Pekerjaan: " + tempPekerjaan);
        System.out.println("");

        System.out.println("============================");
        System.out.println("===MENU UTAMA MAHASISWA=====");
        System.out.println("============================");
        System.out.println("=   1. Kelola Data Diri    =");
        System.out.println("=   2. Ubah Password       =");
        System.out.println("=   3. Lihat Jadwal        =");
        System.out.println("=   4. Tagihan&Pembayaran  =");
        System.out.println("=   5. Logout              =");
        System.out.println("============================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }
    
    public void viewMenuMahasiswaSetelahGenerateJadwal(String namaMahasiswa, String username, String nim, ArrayList<Model_Kompetensi> kompetensi, Model_Pekerjaan[] pekerjaan) {
        System.out.println("");
        System.out.println("Nama: " + namaMahasiswa + " | Username: " + username + " | NIM: " + nim);
        //tampilkan kompetensi
        int i = 0;
        String tempKompetensi = "{ ";

        if (kompetensi != null) {
            for (Model_Kompetensi p : kompetensi) {
                if (i == 0) {
                    tempKompetensi += p.getNamaKompetensi();
                } else {
                    tempKompetensi += ", " + p.getNamaKompetensi();
                }
                i = 1;
            }
        }
        tempKompetensi += "}";
        System.out.println("Kompetensi: " + tempKompetensi);

        //tampilkan pekerjaan
        i = 0;
        String tempPekerjaan = "{ ";
        if (pekerjaan[0] == null) {
            tempPekerjaan = "{}";
        } else {
            for (Model_Pekerjaan p : pekerjaan) {

                if (i == 0) {
                    tempPekerjaan += p.getNamaPekerjaan();
                } else {
                    tempPekerjaan += ", " + p.getNamaPekerjaan();
                }
                i = 1;

            }
        }

        tempPekerjaan += "}";
        System.out.println("Pekerjaan: " + tempPekerjaan);
        System.out.println("");

        System.out.println("============================");
        System.out.println("===MENU UTAMA MAHASISWA=====");
        System.out.println("============================");
        System.out.println("=   1. Ubah Password       =");
        System.out.println("=   2. Lihat Jadwal        =");
        System.out.println("=   3. Tagihan&Pembayaran  =");
        System.out.println("=   4. Logout              =");
        System.out.println("============================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewCheckOldPasswordMahasiswa() {
        System.out.println("");
        System.out.print(">>>>> Masukkan password lama : ");
        this.setOldPassword(input.next());
        System.out.println("");
    }

    public void viewUbahPasswordMahasiswa() {
        System.out.print(">>>>> Masukkan password baru : ");
        this.setPassword(input.next());
        System.out.println("");
    }

    public void viewEditMahasiswaMenuUtama() {
        System.out.println("==========================");
        System.out.println("=====KELOLA DATA DIRI=====");
        System.out.println("==========================");
        System.out.println("=   1. Ubah Nama         =");
        System.out.println("=   2. Ubah NIM          =");
        System.out.println("=   3. Ubah Kompetensi   =");
        System.out.println("=   4. Ubah Pekerjaan    =");
        System.out.println("=   99. Kembali          =");
        System.out.println("==========================");
        System.out.println("");
        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewEditMahasiswaUbahNama() {
        System.out.print("Masukkan nama baru : ");
        input.nextLine();
        this.setNama(input.nextLine());
    }

    public void viewEditMahasiswaUbahNim() {
        System.out.print("Masukkan NIM baru : ");
        this.setNim(input.next());
    }

    public void viewEditMahasiswaUbahKompetensi() {
        this.kompetensi = new ArrayList<String>();
        System.out.println("");
        System.out.println("Masukkan jumlah kompetensi: ");
        int jumlah = input.nextInt();
        String tempKompetensi;
        for (int i = 1; i <= jumlah; i++) {
            System.out.println("Masukkan ID Kompetensi ke-" + i + ": ");
            tempKompetensi = input.next();
            this.kompetensi.add(tempKompetensi);
        }
        this.setKompetensi(this.kompetensi);
    }

    public void viewEditMahasiswaUbahPekerjaan() {
        this.pekerjaan = new String[3];
        System.out.println("");
        System.out.println("Masukkan tepat 3 pekerjaan: ");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Masukkan ID Pekerjaan ke-" + i + ": ");
            this.pekerjaan[i - 1] = input.next();
        }
        this.setPekerjaan(this.pekerjaan);
    }
    
    public void viewLihatJadwalKelasMahasiswa(ArrayList<Model_Kelas> kelas)
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
                //dosen
                Model_Dosen dosen = new Model_Dosen();
                dosen = dosen.getDosenFromJson(mk.getIdDosen());
                System.out.println(">>>>>Dosen : " + dosen.getNama());

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
    
     public void viewMenuTagihanMahasiswa(){
        System.out.println("==============================");
        System.out.println("====TAGIHAN DAN PEMBAYARAN====");
        System.out.println("==============================");
        System.out.println("=  1. Lihat Tagihan Saya     =");
        System.out.println("=  2. Konfirmasi Pembayaran  =");
        System.out.println("=  99. Kembali               =");
        System.out.println("==============================");
        System.out.println("");
        
        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
        
    }
    
    public void viewLihatTagihanHeader() {
        System.out.println("==========================================================================================================================");
        //System.out.println("NIM     | Nama Mahasiswa           | Jumlah Tagihan         | Status  ");
        System.out.format("%-25s%-26s%-24s%-8s\n", "NIM", "Nama Mahasiswa" , "Jumlah Tagihan", "Status");
        System.out.println("==========================================================================================================================");
        System.out.println("");
    }
    
    public void viewLihatTagihanMahasiswaBody(Model_Mahasiswa mahasiswa, int totalTagihan, String isLunas, String tanggalBayar) {
        System.out.format("%-25s%-26s%-24s%-8s\n", mahasiswa.getNim(), mahasiswa.getNama(), totalTagihan, isLunas);
    }
        
    public void viewKonfirmasiPembayaran() {
        System.out.println("==========PEMBAYARAN TAGIHAN============");
        System.out.println("");
        System.out.println("Masukkan Kode Transaksi Pembayaran: ");
        this.setKodeTransaksiPembayaran(input.next());
        System.out.println("Masukkan Tanggal pembayaran (dd/mm/yy): ");
        this.setTanggalTransaksiPembayaran(input.next());
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

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public ArrayList<String> getKompetensi() {
        return kompetensi;
    }

    public void setKompetensi(ArrayList<String> kompetensi) {
        this.kompetensi = kompetensi;
    }

    public String[] getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String[] pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getKodeTransaksiPembayaran() {
        return kodeTransaksiPembayaran;
    }

    public void setKodeTransaksiPembayaran(String kodeTransaksiPembayaran) {
        this.kodeTransaksiPembayaran = kodeTransaksiPembayaran;
    }

    public String getTanggalTransaksiPembayaran() {
        return tanggalTransaksiPembayaran;
    }

    public void setTanggalTransaksiPembayaran(String tanggalTransaksiPembayaran) {
        this.tanggalTransaksiPembayaran = tanggalTransaksiPembayaran;
    }
    
    
}
