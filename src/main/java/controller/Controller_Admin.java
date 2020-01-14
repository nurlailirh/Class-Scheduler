package controller;

import java.io.IOException;
import java.util.ArrayList;
import model.Model_Admin;
import model.Model_Dosen;
import model.Model_Kelas;
import model.Model_Kompetensi;
import model.Model_Mahasiswa;
import model.Model_Pekerjaan;
import model.Model_Tagihan;
import view.View_Admin;

public class Controller_Admin {

    private Model_Admin m_admin;
    private View_Admin v_admin;

    //constructor
    public Controller_Admin() {
        this.m_admin = new Model_Admin();
        this.v_admin = new View_Admin();
    }

    public Controller_Admin(Model_Admin admin) {
        this.m_admin = admin;
        this.v_admin = new View_Admin();
    }

    //menu paling utama untuk admin
    public void controllMenuUtama() {
        //get status isLocked dari json
        boolean isLocked = m_admin.getIsLockedJson();

        if(isLocked == false)
        {
            v_admin.viewMenuAdmin();
            int pilihanNonLocked = v_admin.getPilihan();
            switch (pilihanNonLocked) {
                case 1:
                    this.controllMenuKelolaAkun();
                    break;
                case 2:
                    this.controllKelolaKompetensi();
                    break;
                case 3:
                    this.controllKelolaPekerjaan();
                    break;
                case 4:
                    this.controllBangkitkanJadwalKelas();
                    break;
                case 5:
                    this.controllLihatSeluruhJadwalKelas();
                    break;
                case 6:
                    this.controllPilihOptimasi();
                    break;
                case 7:
                    this.controllMenuLihatTagihan();
                    break;
                case 8:
                    this.controllLihatMahasiswaMemenuhiPekerjaan();
                    break;
                case 9:
                    this.controllAdminUbahPasswordAdmin();
                    break;
                case 10:
                    this.controllAdminLogout();
                    break;
                default:
                    System.out.println("");
                    System.out.println("--Pilihan Salah--");
                    System.out.println("");
                    this.controllMenuUtama();
                    break;
            }
        }
        else
        {
            v_admin.viewMenuAdminSetelahGenerateJadwal();
            int pilihanAfterLocked = v_admin.getPilihan();
            switch (pilihanAfterLocked) {
                 case 1:
                    this.controllMenuKelolaAkun();
                    break;
                case 2:
                    this.controllLihatKompetensi();
                    break;
                case 3:
                    this.controllLihatPekerjaan();
                case 4:
                    this.controllLihatSeluruhJadwalKelas();
                    break;
                case 5:
                    this.controllMenuLihatTagihan();
                    break;
                case 6:
                    this.controllLihatMahasiswaMemenuhiPekerjaan();
                    break;
                case 7:
                    this.controllAdminUbahPasswordAdmin();
                    break;
                case 8:
                    this.controllAdminLogout();
                    break;
                default:
                    System.out.println("");
                    System.out.println("--Pilihan Salah--");
                    System.out.println("");
                    this.controllMenuUtama();
                    break;
            }
        }
    }

    //control lihat tagihan
        public void controllMenuLihatTagihan() {
        v_admin.viewMenuLihatTagihan();
        int pilihan = v_admin.getPilihan();

        switch (pilihan) {
            case 1:
                this.controllAdminLihatAllTagihan();
                break;
            case 2:
                this.controllLihatTagihanBelum();
                break;
            case 3:
                this.controllLihatPemasukan();
                break;
            case 99:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("");
                System.out.println("--Pilihan Salah--");
                System.out.println("");
                this.controllMenuUtama();
                break;
        }
    }
     public void controllAdminLihatAllTagihan(){
        v_admin.viewLihatTagihanHeader();
        ArrayList<Model_Tagihan> listTagihan = m_admin.getAllTagihan();
        for (Model_Tagihan t : listTagihan){
            if (t.getIsLunas() == true){
                v_admin.viewLihatTagihanBody(t.getMahasiswa(),t.getTotalTagihan(), "lunas", t.getTanggalBayar());  
            }
            else{
                v_admin.viewLihatTagihanBody(t.getMahasiswa(),t.getTotalTagihan(), "belum lunas", t.getTanggalBayar());
            }
        }
        
        //tampilkan kembali menu tagihan
        this.controllMenuLihatTagihan();
    }
    
    public void controllLihatTagihanBelum(){
        v_admin.viewLihatTagihanHeader();
        ArrayList<Model_Tagihan> listTagihan = m_admin.getAllTagihan();
        for (Model_Tagihan t : listTagihan){
            if(t.getIsLunas() == false){
                v_admin.viewLihatTagihanBody(t.getMahasiswa(),t.getTotalTagihan(), "belum lunas",t.getTanggalBayar());  
            }
        }
        
        //tampilkan kembali menu tagihan
        this.controllMenuLihatTagihan();
    }

//    public void controllValidasiPembayaran() {
//        View_Admin validasi = new View_Admin();
//        validasi.viewValidasiPembayaran();
//        ArrayList<Model_Mahasiswa> list = m_admin.getAllMahasiswa();
//        for (Model_Mahasiswa m : list){
//            if (validasi.getNim().equals(m.getNim())){
//                m_admin.validasiPembayaran(m.getUsername());
//            }
//        }
//        
//        //tampilkan kembali menu tagihan
//        this.controllMenuLihatTagihan();
//    }

    public void controllLihatPemasukan() {
        int totalPemasukan = 0;
        int totalTagihan = 0;
        
        v_admin.viewLihatPemasukanTagihanHeader();
        ArrayList<Model_Tagihan> listTagihan = m_admin.getAllTagihan();
        for (Model_Tagihan t : listTagihan){
            if(t.getIsLunas() == true){
                totalPemasukan += t.getTotalTagihan();
            }
            else{
                totalTagihan += t.getTotalTagihan();
            }
        }
        v_admin.viewLihatPemasukanTagihanBody(totalPemasukan, totalTagihan);
        
        //tampilkan kembali menu tagihan
        this.controllMenuLihatTagihan();
    }
    
    //Bangkitkan jadwal kelas
    public void controllBangkitkanJadwalKelas() {
        Model_Kelas k = new Model_Kelas();
        k.plotJadwal();
        System.out.println("Jadwal Berhasil dibangkitkan");
        this.controllMenuUtama();
    }

    //Lihat seluruh jadwal kelas
    public void controllLihatSeluruhJadwalKelas() {
        ArrayList<Model_Kelas> kelas = m_admin.getAllKelas();
        v_admin.viewLihatSeluruhJadwalKelas(kelas);
        this.controllMenuUtama();
    }

    //PILIH OPTIMASI
    public void controllPilihOptimasi() {
        v_admin.viewPilihOptimasi();
        int optimasi = v_admin.getOptimasi();
        Model_Kelas k = new Model_Kelas();
        if (optimasi == 1) {
            k.optimasi1();
            System.out.println("Optimasi 1 berhasil dilakukan");
            m_admin.setIsLockedJson(true);
            this.controllMenuUtama();
        } else if (optimasi == 2) {
            k.optimasi2();
            System.out.println("Optimasi 2 berhasil dilakukan");
            m_admin.setIsLockedJson(true);
            this.controllMenuUtama();
        } else {
            System.out.println("Invalid input");
            this.controllPilihOptimasi();
        }
    }

    //UBAH PASSWORDNYA ADMIN
    public void controllAdminUbahPasswordAdmin() {
        v_admin.viewCheckOldPasswordAdmin();
        String oldPassword = v_admin.getOldPassword();
        if (m_admin.getPassword().equals(oldPassword)) //jika password lama telah sesuai
        {
            v_admin.viewAdminUbahPasswordAdmin();
            if (m_admin.jsonFileSetPassword(v_admin.getPassword())) {
                System.out.println("");
                System.out.println("--Password berhasil diubah--");
                System.out.println("");
                this.controllMenuUtama();
            } else {
                System.out.println("");
                System.out.println("--Password gagal diubah--");
                System.out.println("");
                this.controllMenuUtama();
            }
        } else {
            System.out.println("Password lama yang dimasukkan salah. Coba lagi!");
            this.controllAdminUbahPasswordAdmin();
        }
    }

    //kelola akun, pilih mahasiswa atau dosen
    public void controllMenuKelolaAkun() {
        v_admin.viewMenuKelolaAkun();
        int pilihan = v_admin.getPilihan();

        switch (pilihan) {
            case 1:
                this.controllKelolaAkunDosen();
                break;
            case 2:
                this.controllKelolaAkunMahasiswa();
                break;
            case 99:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("");
                System.out.println("--Pilihan Salah--");
                System.out.println("");
                this.controllMenuUtama();
                break;
        }
    }

    //>>>>>>>>kelola akun dosen
    public void controllKelolaAkunDosen() {
        boolean isLocked = m_admin.getIsLockedJson();
        if(isLocked == false)
        {
            v_admin.viewKelolaAkunDosen();
            int pilihan = v_admin.getPilihan();

            switch (pilihan) {
                case 1:
                    this.controllLihatDosen();
                    break;
                case 2:
                    this.controllTambahDosen();
                    break;
                case 3:
                    this.controllEditDosen();
                    break;
                case 4:
                    this.controllHapusDosen();
                    break;
                case 99:
                    this.controllMenuKelolaAkun();
                    break;
                case 0:
                    this.controllMenuUtama();
                    break;
                default:
                    System.out.println("");
                    System.out.println("--Pilihan Salah--");
                    System.out.println("");
                    this.controllMenuKelolaAkun();
                    break;
            }
        }
        else
        {
            v_admin.viewKelolaAkunDosenSetelahGenerateJadwal();
            int pilihan = v_admin.getPilihan();

            switch (pilihan) {
                case 1:
                    this.controllLihatDosen();
                    break;
                case 99:
                    this.controllMenuKelolaAkun();
                    break;
                case 0:
                    this.controllMenuUtama();
                    break;
                default:
                    System.out.println("");
                    System.out.println("--Pilihan Salah--");
                    System.out.println("");
                    this.controllMenuKelolaAkun();
                    break;
            }
        }
    }

    //show all dosen
    public void controllLihatDosen() {
        v_admin.viewLihatDosenHeader();
        ArrayList<Model_Dosen> listOfDosen = m_admin.getAllDosen();
        int i = 1; //untuk number di tampilan
        for (Model_Dosen d : listOfDosen) {
            v_admin.viewLihatDosenBody(i, d.getNama(), d.getNip(), d.getUsername(), d.getKompetensi(), d.getHariLibur());
            i++;
        }
        //setelah ditampilkan listnya, tampilkan kembali menu kelola akunnya
        this.controllKelolaAkunDosen();
    }

    //add new dosen
    public void controllTambahDosen() {
        v_admin.viewTambahDosen();
        String nama = v_admin.getNama();
        String nip = v_admin.getNip();
        ArrayList<String> kompetensi = v_admin.getKompetensi();

        /* Proses Masukkan */
        boolean status = m_admin.tambahDosen(nama, nip, kompetensi);
        if (status) {
            System.out.println("");
            System.out.println("--Akun Dosen Berhasil Ditambahkan--");
            System.out.println("");
            this.controllKelolaAkunDosen();
        } else {
            /* Fail */
            System.out.println("");
            System.out.println("--Akun Dosen Gagal Ditambahkan--");
            System.out.println("");
            this.controllKelolaAkunDosen();
        }
    }

    //edit dosen, ada edit, delete
    public void controllEditDosen() {
        v_admin.viewAdminEditDosenMenuUtama();
        int pilihan = v_admin.getPilihan();

        //boleh ga controller manggil controller lainnya?
        switch (pilihan) {
            case 1:
                this.controllUbahNamaDosen();
                break;
            case 2:
                this.controllUbahNipDosen();
                break;
            case 3:
                this.controllUbahKompetensiDosen();
                break;
            case 4:
                this.controllAdminUbahPasswordDosen();
                break;
            case 99:
                this.controllKelolaAkunDosen();
                break;
            case 0:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("--Pilihan Salah--");
                this.controllEditDosen();
                break;
        }
    }

    //ubah nama dosen
    public void controllUbahNamaDosen() {
        v_admin.viewAdminEditDosenGetUsername();
        String username = v_admin.getUsername();
        Model_Dosen dosen = new Model_Dosen();
        dosen = dosen.getDosenFromJson(username);
        if (dosen != null) {
            v_admin.viewAdminEditDosenUbahNama();
            String namaBaru = v_admin.getNama();
            if (dosen.jsonFileSetNama(namaBaru)) {
                System.out.println("");
                System.out.println("--Nama berhasil diubah--");
                System.out.println("");
                this.controllEditDosen();
            } else {
                System.out.println("");
                System.out.println("--Nama gagal diubah--");
                System.out.println("");
                this.controllEditDosen();
            }
        } else //jika username tidak ditemukan
        {
            System.out.println("");
            System.out.println("--Username tidak ditemukan--");
            System.out.println("");
            this.controllUbahNamaDosen();
        }
    }

    //ubah nip dosen
    public void controllUbahNipDosen() {
        v_admin.viewAdminEditDosenGetUsername();
        String username = v_admin.getUsername();
        Model_Dosen dosen = new Model_Dosen();
        dosen = dosen.getDosenFromJson(username);
        if (dosen != null) {
            v_admin.viewAdminEditDosenUbahNip();
            String nipBaru = v_admin.getNip();
            if (dosen.jsonFileSetNip(nipBaru)) {
                System.out.println("");
                System.out.println("--NIP berhasil diubah--");
                System.out.println("");
                this.controllEditDosen();
            } else {
                System.out.println("");
                System.out.println("--NIP gagal diubah--");
                System.out.println("");
                this.controllEditDosen();
            }
        } else //jika username tidak ditemukan
        {
            System.out.println("");
            System.out.println("--Username tidak ditemukan--");
            System.out.println("");
            this.controllUbahNipDosen();
        }
    }

    //UBAH KOMPETENSI DOSEN
    public void controllUbahKompetensiDosen() {
        v_admin.viewLihatKompetensiHeader();
        ArrayList<Model_Kompetensi> listOfKompetensi = m_admin.getAllKompetensi();
        for (Model_Kompetensi d : listOfKompetensi) {
            v_admin.viewLihatKompetensiBody(d.getIdKompetensi(), d.getNamaKompetensi(), d.getIsPraktikum(), d.getDaftarReqKompetensi(), d.getBobotKompetensi());
        }

        v_admin.viewAdminEditDosenGetUsername();
        String username = v_admin.getUsername();
        Model_Dosen dosen = new Model_Dosen();
        dosen = dosen.getDosenFromJson(username);
        if (dosen != null) {
            v_admin.viewAdminEditDosenUbahKompetensi();
            if (dosen.jsonFileSetKompetensiDosen(v_admin.getKompetensi())) {
                System.out.println("");
                System.out.println("--Kompetensi berhasil diubah--");
                System.out.println("");
                this.controllEditDosen();
            } else {
                System.out.println("");
                System.out.println("--Kompetensi gagal diubah--");
                System.out.println("");
                this.controllEditDosen();
            }
        } else //jika username tidak ditemukan
        {
            System.out.println("");
            System.out.println("--Username tidak ditemukan--");
            System.out.println("");
            this.controllUbahKompetensiDosen();
        }
    }

    //RESET PASSWORD DOSEN
    public void controllAdminUbahPasswordDosen() {
        v_admin.viewAdminUbahPasswordDosen();
        String username = v_admin.getUsername();
        Model_Dosen dosen = new Model_Dosen();
        dosen = dosen.getDosenFromJson(username);
        if (dosen != null) {
            if (dosen.jsonFileSetPassword(username)) {
                System.out.println("");
                System.out.println("--Password berhasil diubah--");
                System.out.println("");
                this.controllEditDosen();
            } else {
                System.out.println("");
                System.out.println("--Password gagal diubah--");
                System.out.println("");
                this.controllEditDosen();
            }
        } else //jika username tidak ditemukan
        {
            System.out.println("");
            System.out.println("--Username tidak ditemukan--");
            System.out.println("");
            this.controllAdminUbahPasswordDosen();
        }
    }

    //hapus dosen
    public void controllHapusDosen() {
        v_admin.viewHapusDosen();
        String nip = v_admin.getNip();

        boolean status = m_admin.hapusDosen(nip);
        if (status) {
            System.out.println("");
            System.out.println("--Akun Dosen Berhasil Dihapus--");
            System.out.println("");
            this.controllKelolaAkunDosen();
        } else {
            /* Fail */
            System.out.println("");
            System.out.println("--Akun Dosen Gagal Dihapus--");
            System.out.println("");
            this.controllKelolaAkunDosen();
        }
    }

    //>>>>>>>>>>kelola akun mahasiswa
    public void controllKelolaAkunMahasiswa() {
        boolean isLocked = m_admin.getIsLockedJson();
        if(isLocked == false)
        {
            v_admin.viewKelolaAkunMahasiswa();
            int pilihan = v_admin.getPilihan();

            switch (pilihan) {
                case 1:
                    this.controllLihatMahasiswa();
                    break;
                case 2:
                    this.controllTambahMahasiswa();
                    break;
                case 3:
                    this.controllEditMahasiswa();
                    break;
                case 4:
                    this.controllHapusMahasiswa();
                    break;
                case 99:
                    this.controllMenuKelolaAkun();
                    break;
                case 0:
                    this.controllMenuUtama();
                    break;
                default:
                    System.out.println("");
                    System.out.println("--Pilihan Salah--");
                    System.out.println("");
                    this.controllMenuKelolaAkun();
                    break;
            }
        }
        else
        {
            v_admin.viewKelolaAkunMahasiswaSetelahGenerateJadwal();
            int pilihan = v_admin.getPilihan();

            switch (pilihan) {
                case 1:
                    this.controllLihatMahasiswa();
                    break;
                case 99:
                    this.controllMenuKelolaAkun();
                    break;
                case 0:
                    this.controllMenuUtama();
                    break;
                default:
                    System.out.println("");
                    System.out.println("--Pilihan Salah--");
                    System.out.println("");
                    this.controllMenuKelolaAkun();
                    break;
            }
        }
    }

    //edit mahasiswa, delete
    public void controllEditMahasiswa() {
        v_admin.viewAdminEditMahasiswaMenuUtama();
        int pilihan = v_admin.getPilihan();

        //boleh ga controller manggil controller lainnya?
        switch (pilihan) {
            case 1:
                this.controllUbahNamaMahasiswa();
                break;
            case 2:
                this.controllUbahNimMahasiswa();
                break;
            case 3:
                this.controllUbahKompetensiMahasiswa();
                break;
            case 4:
                this.controllAdminUbahPasswordMahasiswa();
                break;
            case 99:
                this.controllKelolaAkunMahasiswa();
                break;
            case 0:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("--Pilihan Salah--");
                this.controllEditDosen();
                break;
        }
    }

    //ubah nama mahasiswa
    public void controllUbahNamaMahasiswa() {
        v_admin.viewAdminEditMahasiswaGetUsername();
        String username = v_admin.getUsername();
        Model_Mahasiswa mahasiswa = new Model_Mahasiswa();
        mahasiswa = mahasiswa.getMahasiswaFromJson(username);
        if (mahasiswa != null) {
            v_admin.viewAdminEditMahasiswaUbahNama();
            String namaBaru = v_admin.getNama();
            if (mahasiswa.jsonFileSetNama(namaBaru)) {
                System.out.println("");
                System.out.println("--Nama berhasil diubah--");
                System.out.println("");
                this.controllEditMahasiswa();
            } else {
                System.out.println("");
                System.out.println("--Nama gagal diubah--");
                System.out.println("");
                this.controllEditMahasiswa();
            }
        } else //jika username tidak ditemukan
        {
            System.out.println("");
            System.out.println("--Username tidak ditemukan--");
            System.out.println("");
            this.controllUbahNamaMahasiswa();
        }
    }

    //ubah nim mahasiswa
    public void controllUbahNimMahasiswa() {
        v_admin.viewAdminEditMahasiswaGetUsername();
        String username = v_admin.getUsername();
        Model_Mahasiswa mahasiswa = new Model_Mahasiswa();
        mahasiswa = mahasiswa.getMahasiswaFromJson(username);
        if (mahasiswa != null) {
            v_admin.viewAdminEditMahasiswaUbahNim();
            String nimBaru = v_admin.getNim();
            if (mahasiswa.jsonFileSetNim(nimBaru)) {
                System.out.println("");
                System.out.println("--NIM berhasil diubah--");
                System.out.println("");
                this.controllEditMahasiswa();
            } else {
                System.out.println("");
                System.out.println("--NIM gagal diubah--");
                System.out.println("");
                this.controllEditMahasiswa();
            }
        } else //jika username tidak ditemukan
        {
            System.out.println("");
            System.out.println("--Username tidak ditemukan--");
            System.out.println("");
            this.controllUbahNimMahasiswa();
        }
    }

    //UBAH KOMPETENSI MAHASISWA OLEH ADMIN
    public void controllUbahKompetensiMahasiswa() {
        v_admin.viewLihatKompetensiHeader();
        ArrayList<Model_Kompetensi> listOfKompetensi = m_admin.getAllKompetensi();
        for (Model_Kompetensi d : listOfKompetensi) {
            v_admin.viewLihatKompetensiBody(d.getIdKompetensi(), d.getNamaKompetensi(), d.getIsPraktikum(), d.getDaftarReqKompetensi(), d.getBobotKompetensi());
        }

        v_admin.viewAdminEditMahasiswaGetUsername();
        String username = v_admin.getUsername();
        Model_Mahasiswa mahasiswa = new Model_Mahasiswa();
        mahasiswa = mahasiswa.getMahasiswaFromJson(username);
        if (mahasiswa != null) {
            v_admin.viewAdminEditMahasiswaUbahKompetensi();
            if (mahasiswa.jsonFileSetKompetensiMahasiswa(v_admin.getKompetensi())) {
                System.out.println("");
                System.out.println("--Kompetensi berhasil diubah--");
                System.out.println("");
                this.controllEditMahasiswa();
            } else {
                System.out.println("");
                System.out.println("--Kompetensi gagal diubah--");
                System.out.println("");
                this.controllEditMahasiswa();
            }
        } else //jika username tidak ditemukan
        {
            System.out.println("");
            System.out.println("--Username tidak ditemukan--");
            System.out.println("");
            this.controllUbahKompetensiMahasiswa();
        }
    }

    //RESET PASSWORD MAHASISWA
    public void controllAdminUbahPasswordMahasiswa() {
        v_admin.viewAdminUbahPasswordMahasiswa();
        String username = v_admin.getUsername();
        Model_Mahasiswa mahasiswa = new Model_Mahasiswa();
        mahasiswa = mahasiswa.getMahasiswaFromJson(username);
        if (mahasiswa != null) {
            if (mahasiswa.jsonFileSetPassword(username)) {
                System.out.println("");
                System.out.println("--Password berhasil diubah--");
                System.out.println("");
                this.controllEditMahasiswa();
            } else {
                System.out.println("");
                System.out.println("--Password gagal diubah--");
                System.out.println("");
                this.controllEditMahasiswa();
            }
        } else //jika username tidak ditemukan
        {
            System.out.println("");
            System.out.println("--Username tidak ditemukan--");
            System.out.println("");
            this.controllAdminUbahPasswordMahasiswa();
        }
    }

    //show all mahasiswa
    public void controllLihatMahasiswa() {
        v_admin.viewLihatMahasiswaHeader();
        ArrayList<Model_Mahasiswa> listOfMahasiswa = m_admin.getAllMahasiswa();
        int i = 1; //untuk number di tampilan
        for (Model_Mahasiswa m : listOfMahasiswa) {
            v_admin.viewLihatMahasiswaBody(i, m.getNama(), m.getNim(), m.getUsername(), m.getKompetensi(), m.getPekerjaan());
            i++;
        }
        //setelah ditampilkan listnya, tampilkan kembali menu kelola akunnya
        this.controllKelolaAkunMahasiswa();
    }

    //tambah mahasiswa
    public void controllTambahMahasiswa() {
        v_admin.viewTambahMahasiswa();
        String nama = v_admin.getNama();
        String nim = v_admin.getNim();
        ArrayList<String> kompetensi = v_admin.getKompetensi();

        /* Proses Masukkan */
        boolean status = m_admin.tambahMahasiswa(nama, nim, kompetensi);
        if (status) {
            System.out.println("");
            System.out.println("--Akun Mahasiswa Berhasil Ditambahkan--");
            System.out.println("");
            this.controllKelolaAkunMahasiswa();
        } else {
            /* Fail */
            System.out.println("");
            System.out.println("--Akun Mahasiswa Gagal Ditambahkan--");
            System.out.println("");
            this.controllKelolaAkunMahasiswa();
        }
    }

    //hapus mahasiswas
    public void controllHapusMahasiswa() {
        v_admin.viewHapusMahasiswa();
        String nim = v_admin.getNim();

        boolean status = m_admin.hapusMahasiswa(nim);
        if (status) {
            System.out.println("");
            System.out.println("--Akun Mahasiswa Berhasil Dihapus--");
            System.out.println("");
            this.controllKelolaAkunMahasiswa();
        } else {
            /* Fail */
            System.out.println("");
            System.out.println("--Akun Mahasiswa Gagal Dihapus--");
            System.out.println("");
            this.controllKelolaAkunMahasiswa();
        }
    }

    //================================KOMPETENSI================================
    public void controllKelolaKompetensi() {
        v_admin.viewKelolaKompetensi();
        int pilihan = v_admin.getPilihan();

        switch (pilihan) {
            case 1:
                this.controllLihatKompetensi();
                break;
            case 2:
                this.controllTambahKompetensi();
                break;
            case 3:
                this.controllEditKompetensi();
                break;
            case 4:
                this.controllHapusKompetensi();
                break;
            case 99:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("");
                System.out.println("--Pilihan Salah--");
                System.out.println("");
                this.controllKelolaKompetensi();
                break;
        }
    }

    public void controllLihatKompetensi() {
        v_admin.viewLihatKompetensiHeader();
        ArrayList<Model_Kompetensi> listOfKompetensi = m_admin.getAllKompetensi();
        for (Model_Kompetensi d : listOfKompetensi) {
            v_admin.viewLihatKompetensiBody(d.getIdKompetensi(), d.getNamaKompetensi(), d.getIsPraktikum(), d.getDaftarReqKompetensi(), d.getBobotKompetensi());
        }
        //setelah ditampilkan listnya, tampilkan kembali menu kelola akunnya
        //get status isLocked dari json
        boolean isLocked = m_admin.getIsLockedJson();
        if(isLocked == false)
        {
            this.controllKelolaPekerjaan();
        }
        else
        {
            this.controllMenuUtama();
        }
    }

    public void controllTambahKompetensi() {
        //>>>>>>>>>>>>>>>>>>>>>>Liptia
        //>>>>>>>>>>>>>>>>>>>>>>sebelum add, tampilkan dulu listnya, karna user perlu tau kode kompetensinya

        v_admin.viewLihatKompetensiHeader();
        ArrayList<Model_Kompetensi> listOfKompetensi = m_admin.getAllKompetensi();
        String id = ""; //untuk menentukan id kompetensi yang baru saja ditambahkan
        for (Model_Kompetensi d : listOfKompetensi) {
            v_admin.viewLihatKompetensiBody(d.getIdKompetensi(), d.getNamaKompetensi(), d.getIsPraktikum(), d.getDaftarReqKompetensi(), d.getBobotKompetensi());
            id = d.getIdKompetensi();
        }

        try {
            v_admin.viewTambahKompetensi();
        } catch (Exception e) {
            String ANSI_RESET = "\u001B[0m";
            String ANSI_RED = "\u001B[31m";
            System.out.println(e.toString());
            System.out.println("");
            System.out.println(ANSI_RED + "INPUT SALAH, COBA LAGI!" + ANSI_RESET);
            System.out.println("");
            controllTambahKompetensi();
        }

        //break down id
        id = id.substring(1, id.length());
        id = "K" + (Integer.parseInt(id) + 1);

        String nama = v_admin.getNamaKompetensi();
        int bobot = v_admin.getBobotKompetensi();
        String isPraktikum = v_admin.getIsPraktikum();
        ArrayList<String> kompetensi = v_admin.getDaftarReqKompetensi();

        /* Proses Masukkan */
        boolean status = m_admin.tambahKompetensi(id, nama, isPraktikum, kompetensi, bobot);

        //boolean status = m_admin.tambahKompetensi(id, nama, isPraktikum, kompetensi, bobot);
        if (status) {
            System.out.println("");
            System.out.println("--Kompetensi Berhasil Ditambahkan--");
            System.out.println("");
            this.controllKelolaKompetensi();
        } else {
            /* Fail */
            System.out.println("");
            System.out.println("--Kompetensi Gagal Ditambahkan--");
            System.out.println("");
            this.controllKelolaKompetensi();
        }
    }

    //MENU EDIT KOMPETENSI
    public void controllEditKompetensi() {
        v_admin.viewAdminMenuEditKompetensi();
        int pilihan = v_admin.getPilihan();

        //boleh ga controller manggil controller lainnya?
        switch (pilihan) {
            case 1:
                this.controllUbahNamaKompetensi();
                break;
            case 2:
                this.controllUbahIsPraktikumKompetensi();
                break;
            case 3:
                this.controllUbahBobotKompetensi();
                break;
            case 4:
                this.controllUbahReqKompetensi();
                break;
            case 99:
                this.controllKelolaKompetensi();
                break;
            case 0:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("--Pilihan Salah--");
                this.controllEditKompetensi();
                break;
        }
    }

    public void controllUbahNamaKompetensi() {
        v_admin.viewAdminEditKompetensiUbahNama();

        if (m_admin.ubahNamaKompetensi(v_admin.getIdKompetensi(), v_admin.getNamaKompetensi())) {
            System.out.println("");
            System.out.println("--Nama Kompetensi berhasil diubah--");
            System.out.println("");
            this.controllKelolaKompetensi();
        } else {
            System.out.println("");
            System.out.println("--Nama Kompetensi gagal diubah--");
            System.out.println("");
            this.controllKelolaKompetensi();
        }
    }

    public void controllUbahBobotKompetensi() {
        v_admin.viewAdminEditKompetensiUbahBobot();

        if (m_admin.ubahBobotKompetensi(v_admin.getIdKompetensi(), v_admin.getBobotKompetensi())) {
            System.out.println("");
            System.out.println("--Bobot Kompetensi berhasil diubah--");
            System.out.println("");
            this.controllKelolaKompetensi();
        } else {
            System.out.println("");
            System.out.println("--Bobot Kompetensi gagal diubah--");
            System.out.println("");
            this.controllKelolaKompetensi();
        }
    }

    public void controllUbahIsPraktikumKompetensi() {
        v_admin.viewAdminEditKompetensiUbahIsPraktikum();

        if (m_admin.ubahIsPraktikumKompetensi(v_admin.getIdKompetensi(), v_admin.getIsPraktikum())) {
            System.out.println("");
            System.out.println("--Jenis Kompetensi berhasil diubah--");
            System.out.println("");
            this.controllKelolaKompetensi();
        } else {
            System.out.println("");
            System.out.println("--Jenis Kompetensi gagal diubah--");
            System.out.println("");
            this.controllKelolaKompetensi();
        }
    }

    public void controllUbahReqKompetensi() {
        v_admin.viewAdminEditKompetensiUbahReqKompetensi();
        if (m_admin.ubahReqKompetensi(v_admin.getIdKompetensi(), v_admin.getDaftarReqKompetensi())) {
            System.out.println("");
            System.out.println("--Requirement Kompetensi berhasil diubah--");
            System.out.println("");
            this.controllKelolaKompetensi();
        } else {
            System.out.println("");
            System.out.println("--Requirement Kompetensi gagal diubah--");
            System.out.println("");
            this.controllKelolaKompetensi();
        }
    }

    public void controllHapusKompetensi() {
        v_admin.viewHapusKompetensi();
        String id = v_admin.getIdKompetensi();

        boolean statusPenghapusanKompetensi = m_admin.hapusKompetensi(id);
        boolean statusPenghapusanForeignKeyKompetensi = m_admin.hapusForeignKeyKompetensiDiKompetensi(id);
        boolean statusPenghapusanForeignKeyKompetensiDiPekerjaan = m_admin.hapusForeignKeyKompetensiDiPekerjaan(id);
        boolean statusPenghapusanForeignKeyKompetensiDiUser = m_admin.hapusForeignKeyKompetensiDiUser(id);

        if (statusPenghapusanKompetensi) {
            if (statusPenghapusanForeignKeyKompetensi && statusPenghapusanForeignKeyKompetensiDiPekerjaan
                    && statusPenghapusanForeignKeyKompetensiDiUser) {
                System.out.println("");
                System.out.println("--Data Kompetensi Berhasil Dihapus--");
                System.out.println("");
                this.controllKelolaKompetensi();
            }
        } else {
            /* Fail */
            System.out.println("");
            System.out.println("--Data Kompetensi Gagal Dihapus--");
            System.out.println("");
            this.controllKelolaKompetensi();
        }
    }

    //================================PEKERJAAN=================================
    /*KELOLA PEKERJAAN*/
    public void controllKelolaPekerjaan() {
        v_admin.viewKelolaPekerjaan();
        int pilihan = v_admin.getPilihan();

        switch (pilihan) {
            case 1:
                this.controllLihatPekerjaan();
                break;
            case 2:
                this.controllTambahPekerjaan();
                break;
            case 3:
                this.controllEditPekerjaan();
                break;
            case 4:
                this.controllHapusPekerjaan();
                break;
            case 99:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("");
                System.out.println("--Pilihan Salah--");
                System.out.println("");
                this.controllKelolaPekerjaan();
                break;
        }
    }

    public void controllLihatPekerjaan() {
        v_admin.viewLihatPekerjaanHeader();
        ArrayList<Model_Pekerjaan> listOfPekerjaan = m_admin.getAllPekerjaan();
        for (Model_Pekerjaan p : listOfPekerjaan) {
            v_admin.viewLihatPekerjaanBody(p.getIdPekerjaan(), p.getNamaPekerjaan(), p.getDaftarKompetensi());
        }
        //setelah ditampilkan listnya, tampilkan kembali menu kelola akunnya
        //get status isLocked dari json
        boolean isLocked = m_admin.getIsLockedJson();
        if(isLocked == false)
        {
            this.controllKelolaPekerjaan();
        }
        else
        {
            this.controllMenuUtama();
        }
    }

    public void controllTambahPekerjaan() {
        //TAMPILKAN LIST KOMPETENSI 
        v_admin.viewLihatKompetensiHeader();
        ArrayList<Model_Kompetensi> listOfKompetensi = m_admin.getAllKompetensi();
        for (Model_Kompetensi d : listOfKompetensi) {
            v_admin.viewLihatKompetensiBody(d.getIdKompetensi(), d.getNamaKompetensi(), d.getIsPraktikum(), d.getDaftarReqKompetensi(), d.getBobotKompetensi());
        }

        v_admin.viewLihatPekerjaanHeader();
        ArrayList<Model_Pekerjaan> listOfPekerjaan = m_admin.getAllPekerjaan();
        String id = ""; //untuk menentukan id Pekerjaan yang baru saja ditambahkan
        for (Model_Pekerjaan p : listOfPekerjaan) {
            v_admin.viewLihatPekerjaanBody(p.getIdPekerjaan(), p.getNamaPekerjaan(), p.getDaftarKompetensi());
            id = p.getIdPekerjaan();
        }

        try {
            v_admin.viewTambahPekerjaan();
            System.out.println(v_admin.getKompetensi().toString());
            if (v_admin.getKompetensi().toString().equals("[]")) {
                throw new IllegalArgumentException("Kompetensi tidak boleh kosong");
            }
        } catch (IllegalArgumentException ae) {
            String ANSI_RESET = "\u001B[0m";
            String ANSI_RED = "\u001B[31m";
            System.out.println("");
            System.out.println(ANSI_RED + ae.getMessage() + ANSI_RESET);
            System.out.println("");
            controllTambahPekerjaan();
        } catch (Exception e) {
            String ANSI_RESET = "\u001B[0m";
            String ANSI_RED = "\u001B[31m";
            System.out.println(e.toString());
            System.out.println("");
            System.out.println(ANSI_RED + "INPUT SALAH, COBA LAGI!" + ANSI_RESET);
            System.out.println("");
            controllTambahPekerjaan();
        }

        //break down id
        id = id.substring(1, id.length());
        id = "P" + (Integer.parseInt(id) + 1);

        String nama = v_admin.getNamaPekerjaan();
        ArrayList<String> kompetensi = v_admin.getKompetensi();

        /* Proses Masukkan */
        boolean status = m_admin.tambahPekerjaan(id, nama, kompetensi);
        if (status) {
            System.out.println("");
            System.out.println("--Pekerjaan Berhasil Ditambahkan--");
            System.out.println("");
            this.controllKelolaPekerjaan();
        } else {
            /* Fail */
            System.out.println("");
            System.out.println("--Pekerjaan Gagal Ditambahkan--");
            System.out.println("");
            this.controllKelolaPekerjaan();
        }
    }

    public void controllEditPekerjaan() {
        v_admin.viewAdminMenuEditPekerjaan();
        int pilihan = v_admin.getPilihan();

        switch (pilihan) {
            case 1:
                this.controllUbahNamaPekerjaan();
                break;
            case 2:
                this.controllUbahKompetensiUntukPekerjaan();
                break;
            case 99:
                this.controllKelolaPekerjaan();
                break;
            case 0:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("--Pilihan Salah--");
                this.controllEditPekerjaan();
                break;
        }
    }

    public void controllUbahNamaPekerjaan() {
        v_admin.viewAdminEditPekerjaanUbahNama();

        if (m_admin.ubahNamaPekerjaan(v_admin.getIdPekerjaan(), v_admin.getNamaPekerjaan())) {
            System.out.println("");
            System.out.println("--Nama Pekerjaan berhasil diubah--");
            System.out.println("");
            this.controllKelolaPekerjaan();
        } else {
            System.out.println("");
            System.out.println("--Nama Pekerjaan gagal diubah--");
            System.out.println("");
            this.controllKelolaPekerjaan();
        }
    }

    public void controllUbahKompetensiUntukPekerjaan() {
        v_admin.viewAdminEditPekerjaanUbahKompetensi();

        if (m_admin.ubahReqKompetensiUntukPekerjaan(v_admin.getIdPekerjaan(), v_admin.getKompetensi())) {
            System.out.println("");
            System.out.println("--Requirement Kompetensi berhasil diubah--");
            System.out.println("");
            this.controllKelolaPekerjaan();
        } else {
            System.out.println("");
            System.out.println("--Requirement Kompetensi gagal diubah--");
            System.out.println("");
            this.controllKelolaPekerjaan();
        }
    }

    public void controllHapusPekerjaan() {
        v_admin.viewHapusPekerjaan();
        String id = v_admin.getIdPekerjaan();

        boolean status = m_admin.hapusPekerjaan(id);
        if (status) {
            System.out.println("");
            System.out.println("--Pekerjaan Berhasil Dihapus--");
            System.out.println("");
            this.controllKelolaPekerjaan();
        } else {
            /* Fail */
            System.out.println("");
            System.out.println("--Pekerjaan Gagal Dihapus--");
            System.out.println("");
            this.controllKelolaPekerjaan();
        }
    }
        
    //------CEK MAHASISWA YANG MEMENUHI PEKERJAAN-----
    public void controllLihatMahasiswaMemenuhiPekerjaan(){
        v_admin.viewLihatMahasiswaMemenuhiPekerjaanHeader();
        ArrayList<Model_Mahasiswa> mahasiswa = new ArrayList<Model_Mahasiswa>();
        
        mahasiswa = m_admin.getAllMahasiswa();
            for(Model_Mahasiswa rowMahasiswa : mahasiswa){
                ArrayList<String> pekerjaanTerpenuhiPerMahasiswa = new ArrayList<String>();
                
                for(Model_Pekerjaan pekerjaanPerMahasiswa : rowMahasiswa.getPekerjaan()){
                    ArrayList<Model_Kompetensi> kompetensiPekerjaan = new ArrayList<Model_Kompetensi>();
                    kompetensiPekerjaan = pekerjaanPerMahasiswa.getDaftarKompetensi();
                    boolean isTerpenuhi=true;
                    
                    int indexKom =0;
                    while(indexKom < kompetensiPekerjaan.size() && isTerpenuhi==true){
                        int indexKomPunyaMhs =0;
                        isTerpenuhi=false;
                        while (isTerpenuhi == false && indexKomPunyaMhs < rowMahasiswa.getKompetensi().size()) {
                            if(kompetensiPekerjaan.get(indexKom).getIdKompetensi().equals(rowMahasiswa.getKompetensi().get(indexKomPunyaMhs).getIdKompetensi())){
                                isTerpenuhi=true;
                            }else{
                                isTerpenuhi=false;
                            }
                            indexKomPunyaMhs++;
                        }
                        indexKom++;
                    }
                    if(isTerpenuhi==true){
                        pekerjaanTerpenuhiPerMahasiswa.add( pekerjaanPerMahasiswa.getNamaPekerjaan());
                    }
                }
                if(pekerjaanTerpenuhiPerMahasiswa.size()!=0){
                    v_admin.viewLihatMahasiswaMemenuhiPekerjaanBody(rowMahasiswa.getNim(),rowMahasiswa.getNama(),pekerjaanTerpenuhiPerMahasiswa);
                }
            }
        System.out.println("");
        this.controllMenuUtama();
    }

    //LOGOUT
    public void controllAdminLogout()
    {
        Controller_Login cl = new Controller_Login();
        cl.controllerLoginUser();

    }
}
