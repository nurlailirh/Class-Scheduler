package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Model_Dosen;
import model.Model_Kelas;
import model.Model_Kompetensi;
import model.Model_Mahasiswa;
import model.Model_Pekerjaan;
import model.Model_Waktu;

public class View_Admin {

    private String password;
    private String oldPassword;
    private String username;
    private Scanner input = new Scanner(System.in);
    private int pilihan;
    private String nama;
    private String nip;
    private String nim;
    private ArrayList<String> kompetensi;
    private String idKompetensi;
    private String namaKompetensi;
    private int bobotKompetensi;
    private String isPraktikum;
    private ArrayList<String> daftarReqKompetensi;
    private String idPekerjaan;
    private String namaPekerjaan;
    private int optimasi;

    public void viewMenuAdmin() {
        System.out.println("=================================");
        System.out.println("==========MENU UTAMA ADMIN=======");
        System.out.println("=================================");
        System.out.println("=  1. Kelola Akun               =");
        System.out.println("=  2. Kelola Kompetensi         =");
        System.out.println("=  3. Kelola Pekerjaan          =");
        System.out.println("=  4. Bangkitkan Jadwal Kelas   =");
        System.out.println("=  5. Lihat Jadwal Kelas        =");
        System.out.println("=  6. Pilih optimasi            =");
        System.out.println("=  7. Lihat Tagihan             =");
        System.out.println("=  8. Pekerjaan Terpenuhi       =");
        System.out.println("=  9. Ubah Pasword              =");
        System.out.println("=  10. Logout                   =");
        System.out.println("=================================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }
    
    public void viewMenuAdminSetelahGenerateJadwal() {
        System.out.println("=================================");
        System.out.println("==========MENU UTAMA ADMIN=======");
        System.out.println("=================================");
        System.out.println("=  1. Kelola Akun               =");
        System.out.println("=  2. Lihat Kompetensi          =");
        System.out.println("=  3. Lihat Pekerjaan           =");
        System.out.println("=  4. Lihat Jadwal Kelas        =");
        System.out.println("=  5. Lihat Tagihan             =");
        System.out.println("=  6. Pekerjaan Terpenuhi       =");
        System.out.println("=  7. Ubah Pasword              =");
        System.out.println("=  8. Logout                    =");
        System.out.println("=================================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewPilihOptimasi() {
        System.out.println("");
        System.out.println("Optimasi 1: Berdasarkan minimum defisit.");
        System.out.println("Optimasi 2: Berdasarkan keuntungan terbesar yang dapat diperoleh.");
        System.out.println("Masukkan optimasi yang diinginkan: <1>/<2>");
        this.setOptimasi(input.nextInt());
    }

    public void viewCheckOldPasswordAdmin() {
        System.out.println("");
        System.out.println("Masukkan password lama : ");
        this.setOldPassword(input.next());
        System.out.println("");
    }

    public void viewAdminUbahPasswordAdmin() {
        System.out.print("Masukkan password baru : ");
        this.setPassword(input.next());
        System.out.println("");
    }

    public void viewAdminUbahPasswordDosen() {
        System.out.print("Masukkan Username Dosen yang passwordnya akan diubah : ");
        this.setUsername(input.next());
    }

    public void viewAdminUbahPasswordMahasiswa() {
        System.out.print("Masukkan Username Mahasiswa yang passwordnya akan diubah : ");
        this.setUsername(input.next());
    }

    public void viewAdminEditDosenMenuUtama() {
        System.out.println("==========================");
        System.out.println("=====KELOLA DATA DIRI=====");
        System.out.println("==========================");
        System.out.println("=   1. Ubah Nama         =");
        System.out.println("=   2. Ubah NIP          =");
        System.out.println("=   3. Ubah Kompetensi   =");
        System.out.println("=   4. Reset Password    =");
        System.out.println("=   99. Kembali          =");
        System.out.println("=   0. Menu Utama        =");
        System.out.println("==========================");
        System.out.println("");
        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewAdminEditDosenGetUsername() {
        System.out.print("Masukkan username Dosen yang datanya akan diperbaharui : ");
        this.setUsername(input.next());
    }

    public void viewAdminEditDosenUbahNama() {
        System.out.println("Masukkan nama dosen yang baru: ");
        input.nextLine();
        this.setNama(input.nextLine());
    }

    public void viewAdminEditDosenUbahNip() {
        System.out.println("Masukkan NIP yang baru: ");
        this.setNip(input.next());
    }

    public void viewAdminEditDosenUbahKompetensi() {
        this.kompetensi = new ArrayList<String>();
        System.out.println("Masukkan jumlah requirement kompetensi: ");
        int jumlah = input.nextInt();
        String tempKompetensi;
        for (int i = 1; i <= jumlah; i++) {
            System.out.println("Masukkan ID Kompetensi ke-" + i + ": ");
            tempKompetensi = input.next();
            this.kompetensi.add(tempKompetensi);
        }
        this.setKompetensi(kompetensi);
    }

    public void viewAdminMenuEditKompetensi() {
        System.out.println("=============================");
        System.out.println("======EDIT KOMPETENSI========");
        System.out.println("=============================");
        System.out.println("=   1. Ubah Nama            =");
        System.out.println("=   2. Ubah Jenis Pertemuan =");
        System.out.println("=   3. Ubah Bobot           =");
        System.out.println("=   4. Ubah Req Kompetensi  =");
        System.out.println("=   99. Kembali             =");
        System.out.println("=   0. Menu Utama           =");
        System.out.println("=============================");
        System.out.println("");
        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewAdminEditKompetensiUbahNama() {
        System.out.println("Masukkan ID Kompetensi yang akan diubah : ");
        this.setIdKompetensi(input.next());
        System.out.println("Masukkan nama kompetensi yang baru: ");
        input.nextLine();
        this.setNamaKompetensi(input.nextLine());
    }

    public void viewAdminEditKompetensiUbahBobot() {
        System.out.println("Masukkan ID Kompetensi yang akan diubah : ");
        this.setIdKompetensi(input.next());
        System.out.println("Masukkan bobot kompetensi yang baru: ");
        this.setBobotKompetensi(input.nextInt());
    }

    public void viewAdminEditKompetensiUbahIsPraktikum() {
        System.out.println("Masukkan ID Kompetensi yang akan diubah : ");
        this.setIdKompetensi(input.next());
        System.out.println("Masukkan P jika Praktikum, M jika hanya tatap muka: ");
        this.setIsPraktikum(input.next());
    }

    public void viewAdminEditKompetensiUbahReqKompetensi() {
        System.out.println("Masukkan ID Kompetensi yang akan diubah : ");
        this.setIdKompetensi(input.next());
        this.daftarReqKompetensi = new ArrayList<String>();
        System.out.println("Masukkan jumlah requirement kompetensi: ");
        int jumlah = input.nextInt();
        String kompetensi;
        for (int i = 1; i <= jumlah; i++) {
            System.out.println("Masukkan ID Kompetensi untuk requirement ke-" + i + ": ");
            kompetensi = input.next();
            daftarReqKompetensi.add(kompetensi);
        }
        this.setDaftarReqKompetensi(daftarReqKompetensi);
    }

    public void viewAdminEditMahasiswaMenuUtama() {
        System.out.println("==========================");
        System.out.println("=====KELOLA DATA DIRI=====");
        System.out.println("==========================");
        System.out.println("=   1. Ubah Nama         =");
        System.out.println("=   2. Ubah NIM          =");
        System.out.println("=   3. Ubah Kompetensi   =");
        System.out.println("=   4. Reset Password    =");
        System.out.println("=   99. Kembali          =");
        System.out.println("=   0. Menu Utama        =");
        System.out.println("==========================");
        System.out.println("");
        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewAdminEditMahasiswaGetUsername() {
        System.out.print("Masukkan username Mahasiswa yang datanya akan diperbaharui : ");
        this.setUsername(input.next());
    }

    public void viewAdminEditMahasiswaUbahNama() {
        System.out.println("Masukkan nama Mahasiswa yang baru: ");
        input.nextLine();
        this.setNama(input.nextLine());
    }

    public void viewAdminEditMahasiswaUbahNim() {
        System.out.println("Masukkan NIM yang baru: ");
        this.setNim(input.next());
    }

    public void viewAdminEditMahasiswaUbahKompetensi() {
        this.kompetensi = new ArrayList<String>();
        System.out.println("Masukkan jumlah requirement kompetensi: ");
        int jumlah = input.nextInt();
        String tempKompetensi;
        for (int i = 1; i <= jumlah; i++) {
            System.out.println("Masukkan ID Kompetensi ke-" + i + ": ");
            tempKompetensi = input.next();
            this.kompetensi.add(tempKompetensi);
        }
        this.setKompetensi(kompetensi);
    }

    public void viewAdminMenuEditPekerjaan() {
        System.out.println("=============================");
        System.out.println("======EDIT PEKERJAAN=========");
        System.out.println("=============================");
        System.out.println("=   1. Ubah Nama            =");
        System.out.println("=   2. Ubah Kompetensi      =");
        System.out.println("=   99. Kembali             =");
        System.out.println("=   0. Menu Utama           =");
        System.out.println("=============================");
        System.out.println("");
        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewAdminEditPekerjaanUbahNama() {
        System.out.println("Masukkan ID Pekerjaan yang akan diubah : ");
        this.setIdPekerjaan(input.next());
        System.out.println("Masukkan nama Pekerjaan yang baru: ");
        input.nextLine();
        this.setNamaPekerjaan(input.nextLine());
    }

    public void viewAdminEditPekerjaanUbahKompetensi() {
        System.out.println("Masukkan ID Pekerjaan yang akan diubah : ");
        this.setIdPekerjaan(input.next());
        this.kompetensi = new ArrayList<String>();
        System.out.println("Masukkan jumlah requirement kompetensi: ");
        int jumlah = input.nextInt();
        String inputKompetensi;
        for (int i = 1; i <= jumlah; i++) {
            System.out.println("Masukkan ID Kompetensi untuk requirement ke-" + i + ": ");
            inputKompetensi = input.next();
            kompetensi.add(inputKompetensi);
        }
        this.setKompetensi(kompetensi);
    }

    public void viewHapusDosen() {
        System.out.println("------Hapus Dosen------");
        System.out.print("Masukan NIP Dosen yang akan dihapus: ");
        this.setNip(input.next());
    }

    public void viewHapusKompetensi() {
        System.out.println("------Hapus Kompetensi------");
        System.out.print("Masukan Kode Kompetensi yang akan dihapus: ");
        this.setIdKompetensi(input.next());
    }

    public void viewHapusMahasiswa() {
        System.out.println("------Hapus Mahasiswa------");
        System.out.print("Masukan NIM Mahasiswa yang akan dihapus: ");
        this.setNim(input.next());
    }

    public void viewHapusPekerjaan() {
        System.out.println("------Hapus Pekerjaan------");
        System.out.print("Masukan Kode Pekerjaan yang akan dihapus: ");
        this.setIdPekerjaan(input.next());
    }

    public void viewKelolaAkunDosen() {
        System.out.println("");
        System.out.println("=====================");
        System.out.println("==KELOLA AKUN DOSEN==");
        System.out.println("=====================");
        System.out.println("=  1. Lihat Dosen   =");
        System.out.println("=  2. Tambah Dosen  =");
        System.out.println("=  3. Edit Dosen    =");
        System.out.println("=  4. Hapus Dosen   =");
        System.out.println("=  99. Kembali      =");
        System.out.println("=  0. Menu Utama    =");
        System.out.println("=====================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }
    
    public void viewKelolaAkunDosenSetelahGenerateJadwal() {
        System.out.println("");
        System.out.println("=====================");
        System.out.println("==KELOLA AKUN DOSEN==");
        System.out.println("=====================");
        System.out.println("=  1. Lihat Dosen   =");
        System.out.println("=  99. Kembali      =");
        System.out.println("=  0. Menu Utama    =");
        System.out.println("=====================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewKelolaAkunMahasiswa() {
        System.out.println("");
        System.out.println("=========================");
        System.out.println("==KELOLA AKUN MAHASISWA==");
        System.out.println("=========================");
        System.out.println("=  1. Lihat Mahasiswa   =");
        System.out.println("=  2. Tambah Mahasiswa  =");
        System.out.println("=  3. Edit Mahasiswa    =");
        System.out.println("=  4. Hapus Mahasiswa   =");
        System.out.println("=  99. Kembali          =");
        System.out.println("=  0. Menu Utama        =");
        System.out.println("=========================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }
    
     public void viewKelolaAkunMahasiswaSetelahGenerateJadwal() {
        System.out.println("");
        System.out.println("=========================");
        System.out.println("==KELOLA AKUN MAHASISWA==");
        System.out.println("=========================");
        System.out.println("=  1. Lihat Mahasiswa   =");
        System.out.println("=  99. Kembali          =");
        System.out.println("=  0. Menu Utama        =");
        System.out.println("=========================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewKelolaKompetensi() {
        System.out.println("");
        System.out.println("==========================");
        System.out.println("=====KELOLA KOMPETENSI====");
        System.out.println("==========================");
        System.out.println("=  1. Lihat Kompetensi   =");
        System.out.println("=  2. Tambah Kompetensi  =");
        System.out.println("=  3. Edit Kompetensi    =");
        System.out.println("=  4. Hapus Kompetensi   =");
        System.out.println("=  99. Kembali           =");
        System.out.println("==========================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewKelolaPekerjaan() {
        System.out.println("==========================");
        System.out.println("==KELOLA AKUN PEKERJAAN==");
        System.out.println("==========================");
        System.out.println("=  1. Lihat Pekerjaan    =");
        System.out.println("=  2. Tambah Pekerjaan   =");
        System.out.println("=  3. Edit Pekerjaan     =");
        System.out.println("=  4. Hapus Pekerjaan    =");
        System.out.println("=  99. Kembali           =");
        System.out.println("==========================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewLihatDosenHeader() {

        System.out.println("==========DAFTAR DOSEN============");
        System.out.println("");
        System.out.println("===================================================================================================================================================================");
        System.out.format("%-4s%-25s%-15s%-15s%-100s%-100s\n", "No.", "Nama", "NIP", "Username", "Kompetensi", "Hari Libur");
        System.out.println("===================================================================================================================================================================");
        System.out.println("");
    }

    public void viewLihatDosenBody(int nomor, String nama, String nip, String username, ArrayList<Model_Kompetensi> kompetensi, ArrayList<String> hariLibur) {
        int i = 0;
        String tempKompetensi = "{";

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

        i = 0;
        String tempHariLibur = "{";

        if (hariLibur != null) {
            for (String libur : hariLibur) {
                if (i == 0) {
                    tempHariLibur += libur;
                } else {
                    tempHariLibur += ", " + libur;
                }
                i = 1;
            }
        }
        tempHariLibur += "}";

        System.out.format("%-4s%-25s%-15s%-15s%-100s%-100s\n", nomor + ".", nama, nip, username, tempKompetensi, tempHariLibur);
    }

    public void viewLihatKompetensiHeader() {

        System.out.println("==========DAFTAR KOMPETENSI============");
        System.out.println("");
        System.out.println("==========================================================================================================================");
        System.out.format("%-5s%-10s%-25s%-10s%-100s\n", "ID", "[P]/[M]", "Kompetensi", "Bobot", "Requirement");
        System.out.println("==========================================================================================================================");
        System.out.println("");
    }

    public void viewLihatKompetensiBody(String idKompetensi, String namaKompetensi, String isPraktikum, ArrayList<Model_Kompetensi> reqKompetensi, int bobotKompetensi) {
        int i = 0;
        String tempPrintReq = "";

        if (reqKompetensi != null) {
            for (Model_Kompetensi p : reqKompetensi) {
                if (i == 0) {
                    tempPrintReq += p.getNamaKompetensi();
                } else {
                    tempPrintReq += ", " + p.getNamaKompetensi();
                }
                i = 1;
            }
        } else {
            tempPrintReq = "";
        }

        System.out.format("%-5s%-10s%-25s%-10s%-100s\n", idKompetensi, "[" + isPraktikum + "]", namaKompetensi, bobotKompetensi, "{" + tempPrintReq + "}");
    }

    public void viewLihatMahasiswaHeader() {

        System.out.println("==========DAFTAR MAHASISWA============");
        System.out.println("");
        System.out.println("===================================================================================================================================================================================");
        System.out.format("%-4s%-25s%-15s%-15s%-70s%-100s\n", "No.", "Nama", "NIM", "Username", "Kompetensi", "Pekerjaan");
        System.out.println("===================================================================================================================================================================================");
        System.out.println("");
    }

    public void viewLihatMahasiswaBody(int nomor, String nama, String nim, String username, ArrayList<Model_Kompetensi> kompetensi, Model_Pekerjaan[] pekerjaan) {
        int i = 0;
        String tempKompetensi = "{";

        if (kompetensi != null) {
            for (Model_Kompetensi k : kompetensi) {
                if (i == 0) {
                    tempKompetensi += k.getNamaKompetensi();
                } else {
                    tempKompetensi += ", " + k.getNamaKompetensi();
                }
                i = 1;
            }
        }
        tempKompetensi += "}";

        i = 0;
        String tempPekerjaan = "{";
        if (pekerjaan[0] == null) {
            tempPekerjaan = "{}";
        } else {
            for (Model_Pekerjaan p : pekerjaan) {
                if (p.getNamaPekerjaan() == null) {
                    tempPekerjaan = tempPekerjaan;
                } else {
                    if (i == 0) {
                        tempPekerjaan += p.getNamaPekerjaan();
                    } else {
                        tempPekerjaan += ", " + p.getNamaPekerjaan();
                    }
                    i = 1;
                }
            }
        }
        tempPekerjaan += "}";

        System.out.format("%-4s%-25s%-15s%-15s%-70s%-100s\n", nomor + ".", nama, nim, username, tempKompetensi, tempPekerjaan);
    }

    public void viewLihatPekerjaanHeader() {

        System.out.println("==========DAFTAR PEKERJAAN============");
        System.out.println("");
        System.out.println("==========================================================================================================================");
        System.out.format("%-5s%-25s%-100s\n", "ID", "Pekerjaan", "Kompetensi");
        System.out.println("==========================================================================================================================");
        System.out.println("");
    }

    public void viewLihatPekerjaanBody(String idPekerjaan, String namaPekerjaan, ArrayList<Model_Kompetensi> kompetensi) {
        int i = 0;
        String tempKompetensi = "{";
        for (Model_Kompetensi p : kompetensi) {
            if (i == 0) {
                tempKompetensi += p.getNamaKompetensi();
            } else {
                tempKompetensi += ", " + p.getNamaKompetensi();
            }
            i = 1;
        }
        tempKompetensi += "}";
        System.out.format("%-5s%-25s%-100s\n", idPekerjaan, namaPekerjaan, tempKompetensi);
    }

    public void viewLihatWaktuHeader() {

        System.out.println("==========DAFTAR WAKTU============");
        System.out.println("");
        System.out.println("==========================================================================================================================");
        System.out.println("ID   | Tanggal   | JAM   | Owner");
        System.out.println("==========================================================================================================================");
        System.out.println("");
    }

    public void viewLihatWaktuBody(int i, String idWaktu, String Tanggal, String Jam, ArrayList<String> listOwner) {

        System.out.println(i + " | " + idWaktu + " | " + Tanggal + " |" + Jam + "|" + listOwner);
    }

    public void viewMenuKelolaAkun() {
        System.out.println("=======================");
        System.out.println("===MENU KELOLA AKUN====");
        System.out.println("=======================");
        System.out.println("=  1. Akun Dosen      =");
        System.out.println("=  2. Akun Mahasiswa  =");
        System.out.println("=  99. Menu Utama     =");
        System.out.println("=======================");
        System.out.println("");

        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }

    public void viewTambahDosen() {
        System.out.println("------Tambah Dosen------");
        System.out.println("Masukan Nama: ");
        input.nextLine();
        String name = input.nextLine();
        this.setNama(name);

        System.out.println("Masukan NIP: ");
        this.setNip(input.next());
        input.nextLine();

        System.out.println("Input Kompetensi: ");
        this.kompetensi = new ArrayList<String>();
        System.out.println("Masukkan jumlah Kompetensi: ");
        int jumlah = input.nextInt();
        input.nextLine();
        String k; //temp kompetensi
        for (int i = 1; i <= jumlah; i++) {
            System.out.println("Masukkan ID Kompetensi ke-" + i + ": ");
            k = input.next();
            input.nextLine();
            kompetensi.add(k);
        }
        this.setKompetensi(kompetensi);
    }

    public void viewTambahKompetensi() {
        System.out.println("\n");
        System.out.println("------Tambah Kompetensi------");
        System.out.println("Masukkan data kompetensi: [<P/M>] <Nama Kompetensi> {<K1, K2, .., Kn>} ~<bobot>");
        String inputData = input.nextLine();

        //break down the input
        //get praktikum atau tatap muka
        String isprak = inputData.substring(1, 2);
        this.setIsPraktikum(isprak);

        inputData = inputData.substring(4, inputData.length());
        String arrInputData[] = inputData.split(" ");
        int i = 0; //keperluan iterasi

        //get nama
        String nama = "";
        while (!arrInputData[i].contains("{")) {
            nama += arrInputData[i] + " ";
            i++;
        }

        nama = nama.substring(0, nama.length() - 1); //menghilangkan spasi di akhir
        this.setNamaKompetensi(nama);

        //get requirement
        String requirement = "";
        this.daftarReqKompetensi = new ArrayList<String>();

        while (!arrInputData[i].contains("~")) {
            arrInputData[i] = arrInputData[i].replace("{", "");
            arrInputData[i] = arrInputData[i].replace("}", "");
            requirement += arrInputData[i] + " ";
            i++;
        }
        requirement = requirement.substring(0, requirement.length() - 1); //menghilangkan spasi di akhir
        String kompetensi[] = requirement.split(", ");
        for (String k : kompetensi) {
            daftarReqKompetensi.add(k);
        }
        this.setDaftarReqKompetensi(daftarReqKompetensi);

        //get bobot
        String bobot = arrInputData[i].replace("~", "");
        this.setBobotKompetensi(Integer.parseInt(bobot));
        System.out.println("");
    }

    public void viewTambahMahasiswa() {
        System.out.println("------Tambah Mahasiswa------");
        System.out.print("Masukkan Nama: ");
        input.nextLine();
        this.setNama(input.nextLine());

        System.out.println("Masukan NIM: ");
        this.setNim(input.next());
        input.nextLine();

        System.out.println("Input Kompetensi: ");
        this.kompetensi = new ArrayList<String>();
        System.out.println("Masukkan jumlah Kompetensi: ");
        int jumlah = input.nextInt();
        input.nextLine();
        String k; //temp kompetensi
        for (int i = 1; i <= jumlah; i++) {
            System.out.println("Masukkan ID Kompetensi ke-" + i + ": ");
            k = input.next();
            input.nextLine();
            kompetensi.add(k);
        }
        this.setKompetensi(kompetensi);
    }

    public void viewTambahPekerjaan() {
        System.out.println("\n");
        System.out.println("------Tambah Pekerjaan------");
        System.out.println("Daftar kompetensi tanpa koma, dan tidak boleh kosong");
        System.out.println("Masukkan data pekerjaan: <Nama Pekerjaan> {<K1,K2,..,Kn>}");
        String inputData = input.nextLine();

        //break down the input
        int i = 0; //keperluan iterasi

        //get nama pekerjaan
        String nama = "";
        String arrInput[] = inputData.split(" ");

        //looping sampai array length - 1, karena index akhir pasti kompetensi
        for (int j = 0; j < arrInput.length - 1; j++) {
            nama += arrInput[j] + " ";
        }

        this.setNamaPekerjaan(nama);

        //get requirement
        this.kompetensi = new ArrayList<String>();
        String requirement = arrInput[arrInput.length - 1];
        requirement = requirement.replace("{", "");
        requirement = requirement.replace("}", "");
        String[] arrRequirement = requirement.split(",");

        for (String r : arrRequirement) {
            this.kompetensi.add(r);
        }
        this.setKompetensi(kompetensi);
        System.out.println("");
    }

    public void viewLihatSeluruhJadwalKelas(ArrayList<Model_Kelas> kelas) {
        if(kelas.isEmpty())
        {
            System.out.println("Jadwal Belum dapat ditampilkan");
        }
        else
        {
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
                //Kuota kelas
                System.out.println(">>>>>Kuota kelas: " + mk.getKuotaKelas());
                //peserta kelas
                System.out.println(">>>>>Peserta kelas: ");
                for (Model_Mahasiswa mahasiswa : mk.getPesertaKelas()) {
                    System.out.print(mahasiswa.getNama() + ", ");
                }
                System.out.println("");
                //Jadwal
                System.out.println("");
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

    public ArrayList<String> getKompetensi() {
        return this.kompetensi;
    }

    public void setKompetensi(ArrayList<String> kompetensi) {
        this.kompetensi = kompetensi;
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

    public String getIsPraktikum() {
        return isPraktikum;
    }

    public void setIsPraktikum(String isPraktikum) {
        this.isPraktikum = isPraktikum;
    }

    public ArrayList<String> getDaftarReqKompetensi() {
        return daftarReqKompetensi;
    }

    public void setDaftarReqKompetensi(ArrayList<String> daftarReqKompetensi) {
        this.daftarReqKompetensi = daftarReqKompetensi;
    }

    public int getBobotKompetensi() {
        return bobotKompetensi;
    }

    public void setBobotKompetensi(int bobotKompetensi) {
        this.bobotKompetensi = bobotKompetensi;
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

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public int getOptimasi() {
        return this.optimasi;
    }

    public void setOptimasi(int optimasi) {
        this.optimasi = optimasi;
    }

    
    
    //admin
    public void viewMenuLihatTagihan(){
        System.out.println("========================================");
        System.out.println("=============LIHAT TAGIHAN==============");
        System.out.println("========================================");
        System.out.println("=  1. Lihat Semua Tagihan Mahasiswa    =");
        System.out.println("=  2. Lihat Mahasiswa Belum Membayar   =");
        System.out.println("=  3. Total Pemasukan & Tunggakan      =");
        System.out.println("=  99. Kembali                         =");
        System.out.println("========================================");
        System.out.println("");
        
        System.out.println("Pilih menu: <nomor_menu>");
        this.setPilihan(input.nextInt());
    }
    
    public void viewLihatAllTagihanHeader() {
        System.out.println("==========DAFTAR TAGIHAN============");
        System.out.println("");
        System.out.println("");
        this.viewLihatTagihanHeader();
    }

    public void viewLihatTagihanHeader() {
        System.out.println("==========================================================================================================================");
        //System.out.println("NIM     | Nama Mahasiswa           | Jumlah Tagihan         | Status  ");
        System.out.format("%-25s%-26s%-24s%-8s\n", "NIM", "Nama Mahasiswa" , "Jumlah Tagihan", "Status");
        System.out.println("==========================================================================================================================");
        System.out.println("");
    }

    public void viewLihatTagihanBody(Model_Mahasiswa mahasiswa, int totalTagihan, String isLunas, String tanggalBayar) {
        System.out.format("%-25s%-26s%-24s%-8s\n", mahasiswa.getNim(), mahasiswa.getNama(), totalTagihan, isLunas);
    }

    public void viewValidasiPembayaran() {
        System.out.println("Masukkan NIM yang akan divalidasi pembayarannya: ");
        this.setNim(input.next());
    }
    
    public void viewLihatPemasukanTagihanHeader() {
        System.out.println("==========================================================================================================================");
        System.out.println("Total Pemasukan      | Total Tagihan Belum Dibayar    ");
        System.out.println("==========================================================================================================================");
        System.out.println("");
    }
    
    public void viewLihatPemasukanTagihanBody(int totalPemasukan, int totalTagihan) {
        System.out.format("%-24s%-24s\n", totalPemasukan, totalTagihan);
    }
    
    public void viewLihatMahasiswaMemenuhiPekerjaanHeader() {
        System.out.println("==========================================================================================================================");
        System.out.println("NIM        | Nama Mahasiswa           | Pekerjaan Terpenuhi          ");
        System.out.println("==========================================================================================================================");
        System.out.println("");
    }

    public void viewLihatMahasiswaMemenuhiPekerjaanBody(String nip, String mahasiswa, ArrayList<String> pekerjaan) {
        System.out.format("%-12s%-26s", nip, mahasiswa);
        int i =0;
        String tempPekerjaan = "{";
        for (String p : pekerjaan) {
            if (i == 0) {
                tempPekerjaan += p;
            } else {
                tempPekerjaan += ", " + p;
            }
            i = 1;
        }
        tempPekerjaan += "}";
        System.out.println(tempPekerjaan);
    }
}
