package controller;

import java.util.ArrayList;
import model.Model_Admin;
import model.Model_Kelas;
import model.Model_Kompetensi;
import model.Model_Mahasiswa;
import model.Model_Pekerjaan;
import model.Model_Tagihan;
import view.View_Admin;
import view.View_Mahasiswa;

public class Controller_Mahasiswa {

    private Model_Mahasiswa m_mahasiswa;
    private View_Mahasiswa v_mahasiswa;

    public Controller_Mahasiswa() {
        this.m_mahasiswa = new Model_Mahasiswa();
        this.v_mahasiswa = new View_Mahasiswa();
    }

    public Controller_Mahasiswa(Model_Mahasiswa mahasiswa) {
        this.m_mahasiswa = mahasiswa;
        this.v_mahasiswa = new View_Mahasiswa();
    }

    public void controllMenuUtama() {
         //get status isLocked dari json
        boolean isLocked = m_mahasiswa.getIsLockedJson();

        if(isLocked == false)
        {
            v_mahasiswa.viewMenuMahasiswa(m_mahasiswa.getNama(), m_mahasiswa.getUsername(), m_mahasiswa.getNim(), m_mahasiswa.getKompetensi(), m_mahasiswa.getPekerjaan());
            int pilihan = v_mahasiswa.getPilihan();

            switch (pilihan) {
                case 1:
                    this.controllMenuKelolaAkunMahasiswa();
                    break;
                case 2:
                    this.controllUbahPassword();
                    break;
                case 3:
                    this.controllLihatJadwalKelasMahasiswa();
                    break;
                case 4:
                    this.controllMenuTagihanMahasiswa();
                    break;
                case 5:
                    this.controllMahasiswaLogout();
                    break;
                default:
                    System.out.println("--Pilihan Salah--");
                    this.controllMenuUtama();
                    break;
            }
        }
        else
        {
            v_mahasiswa.viewMenuMahasiswaSetelahGenerateJadwal(m_mahasiswa.getNama(), m_mahasiswa.getUsername(), m_mahasiswa.getNim(), m_mahasiswa.getKompetensi(), m_mahasiswa.getPekerjaan());
            int pilihan = v_mahasiswa.getPilihan();

            switch (pilihan) {
                case 1:
                    this.controllUbahPassword();
                    break;
                case 2:
                    this.controllLihatJadwalKelasMahasiswa();
                    break;
                case 3:
                    this.controllMenuTagihanMahasiswa();
                    break;
                case 4:
                    this.controllMahasiswaLogout();
                    break;
                default:
                    System.out.println("--Pilihan Salah--");
                    this.controllMenuUtama();
                    break;
            }
        }
    }
    
    //Lihat seluruh jadwal kelas per mahasiswa
    public void controllLihatJadwalKelasMahasiswa() {
        ArrayList<Model_Kelas> kelas = m_mahasiswa.getKelasByUsernameMahasiswa();
        v_mahasiswa.viewLihatJadwalKelasMahasiswa(kelas);
        this.controllMenuUtama();
    }

    public void controllMenuKelolaAkunMahasiswa() {
        v_mahasiswa.viewEditMahasiswaMenuUtama();
        int pilihan = v_mahasiswa.getPilihan();

        switch (pilihan) {
            case 1:
                this.controllUbahNama();
                break;
            case 2:
                this.controllUbahNim();
                break;
            case 3:
                this.controllUbahKompetensi();
                break;
            case 4:
                this.controllUbahPekerjaan();
                break;
            case 99:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("--Pilihan Salah--");
                this.controllMenuKelolaAkunMahasiswa();
                break;
        }
    }

    public void controllUbahPassword() {
        v_mahasiswa.viewCheckOldPasswordMahasiswa();
        String oldPassword = v_mahasiswa.getOldPassword();
        if (m_mahasiswa.getPassword().equals(oldPassword)) //jika password lama telah sesuai
        {
            v_mahasiswa.viewUbahPasswordMahasiswa();
            if (m_mahasiswa.jsonFileSetPassword(v_mahasiswa.getPassword())) {
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
            this.controllUbahPassword();
        }
    }

    public void controllUbahNama() {
        v_mahasiswa.viewEditMahasiswaUbahNama();
        if (m_mahasiswa.jsonFileSetNama(v_mahasiswa.getNama())) {
            System.out.println("");
            System.out.println("--Nama berhasil diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunMahasiswa();
        } else {
            System.out.println("");
            System.out.println("--Nama gagal diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunMahasiswa();
        }
    }

    public void controllUbahNim() {
        v_mahasiswa.viewEditMahasiswaUbahNim();
        if (m_mahasiswa.jsonFileSetNim(v_mahasiswa.getNim())) {
            System.out.println("");
            System.out.println("--NIM berhasil diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunMahasiswa();
        } else {
            System.out.println("");
            System.out.println("--NIM gagal diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunMahasiswa();
        }
    }

    public void controllUbahKompetensi() {
        View_Admin v_lihatKompetensi = new View_Admin();
        v_lihatKompetensi.viewLihatKompetensiHeader();
        Model_Admin m_admin = new Model_Admin();
        ArrayList<Model_Kompetensi> listOfKompetensi = m_admin.getAllKompetensi();
        for (Model_Kompetensi d : listOfKompetensi) {
            v_lihatKompetensi.viewLihatKompetensiBody(d.getIdKompetensi(), d.getNamaKompetensi(), d.getIsPraktikum(), d.getDaftarReqKompetensi(), d.getBobotKompetensi());
        }

        v_mahasiswa.viewEditMahasiswaUbahKompetensi();
        if (m_mahasiswa.jsonFileSetKompetensiMahasiswa(v_mahasiswa.getKompetensi())) {
            System.out.println("");
            System.out.println("--Kompetensi berhasil diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunMahasiswa();
        } else {
            System.out.println("");
            System.out.println("--Kompetensi gagal diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunMahasiswa();
        }
    }

    public void controllUbahPekerjaan() {
        View_Admin v_lihatPekerjaan = new View_Admin();
        v_lihatPekerjaan.viewLihatPekerjaanHeader();
        Model_Admin m_admin = new Model_Admin();
        ArrayList<Model_Pekerjaan> listOfPekerjaan = m_admin.getAllPekerjaan();
        for (Model_Pekerjaan p : listOfPekerjaan) {
            v_lihatPekerjaan.viewLihatPekerjaanBody(p.getIdPekerjaan(), p.getNamaPekerjaan(), p.getDaftarKompetensi());
        }

        v_mahasiswa.viewEditMahasiswaUbahPekerjaan();
        if (m_mahasiswa.jsonFileSetPekerjaanMahasiswa(v_mahasiswa.getPekerjaan())) {
            System.out.println("");
            System.out.println("--Pekerjaan berhasil diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunMahasiswa();
        } else {
            System.out.println("");
            System.out.println("--Pekerjaan gagal diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunMahasiswa();
        }
    }
   
    public void controllMenuTagihanMahasiswa() {
        v_mahasiswa.viewMenuTagihanMahasiswa();
        int pilihan = v_mahasiswa.getPilihan();

        switch (pilihan) {
            case 1:
                this.controllLihatTagihan();
                break;
            case 2:
                this.controllBayarTagihan();
                break;
            case 99:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("--Pilihan Salah--");
                this.controllMenuTagihanMahasiswa();
                break;
        }
    }

    public void controllBayarTagihan() {
        View_Mahasiswa v_tagihanmahasiswa = new View_Mahasiswa();
        v_tagihanmahasiswa.viewKonfirmasiPembayaran();
        String kodeTransaksiPembayaran = v_tagihanmahasiswa.getKodeTransaksiPembayaran();
        String tanggalPebayaran = v_tagihanmahasiswa.getTanggalTransaksiPembayaran();
        
        if (m_mahasiswa.jsonFileSetPembayaran(kodeTransaksiPembayaran, tanggalPebayaran)){
                System.out.println("");
                System.out.println("--Pembayaran berhasil dilakukan--");
                System.out.println("");
                this.controllMenuTagihanMahasiswa();
            } else {
                System.out.println("");
                System.out.println("--Pembayaran gagal dilakukan--");
                System.out.println("");
                this.controllMenuTagihanMahasiswa();
            }
    }
    
   
    public void controllLihatTagihan() {
        v_mahasiswa.viewLihatTagihanHeader();
        Model_Tagihan tagihan = m_mahasiswa.getTagihanMahasiswa();

        if (tagihan.getMahasiswa().getNim().equals(m_mahasiswa.getNim())) {
            String isLunas;
            if (tagihan.getIsLunas() == false) {
                isLunas = "Belum Lunas";
            } else {
                isLunas = "Lunas";
            }
            v_mahasiswa.viewLihatTagihanMahasiswaBody(tagihan.getMahasiswa(), tagihan.getTotalTagihan(), isLunas, tagihan.getTanggalBayar());
        }

        this.controllMenuTagihanMahasiswa();
    }
    
    //LOGOUT
    public void controllMahasiswaLogout()
    {
        Controller_Login cl = new Controller_Login();
        cl.controllerLoginUser();
    }
}
