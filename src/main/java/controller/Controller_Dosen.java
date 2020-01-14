package controller;

import java.util.ArrayList;
import model.Model_Admin;
import model.Model_Dosen;
import model.Model_Kelas;
import model.Model_Kompetensi;
import view.View_Admin;
import view.View_Dosen;

public class Controller_Dosen {
    private Model_Dosen m_dosen;
    private View_Dosen v_dosen;
    
    public Controller_Dosen()
    {
        this.m_dosen = new Model_Dosen();
        this.v_dosen = new View_Dosen();
    }
    
    public Controller_Dosen(Model_Dosen dosen)
    {
        this.m_dosen = dosen;
        this.v_dosen = new View_Dosen();
    }
    
    public void controllMenuUtama(){
        //get status isLocked dari json
        boolean isLocked = m_dosen.getIsLockedJson();
        if(isLocked == false){
            v_dosen.viewMenuDosen(m_dosen.getNama(), m_dosen.getUsername(), m_dosen.getNip(), m_dosen.getKompetensi(), m_dosen.getHariLibur());
            int pilihanNotLocked = v_dosen.getPilihan();

            switch(pilihanNotLocked){
                case 1:
                    this.controllMenuKelolaAkunDosen();
                    break;
                case 2:
                    this.controllUbahPassword();
                    break;
                case 3:
                    this.controllLihatJadwalKelasDosen();
                    break;
                case 4:
                    this.controllDosenLogout();
                    break;
                default:
                    System.out.println("--Pilihan Salah--");
                    this.controllMenuUtama();
                    break;
            }
        }
        else
        {
             v_dosen.viewMenuDosenSetelahGenerateJadwal(m_dosen.getNama(), m_dosen.getUsername(), m_dosen.getNip(), m_dosen.getKompetensi(), m_dosen.getHariLibur());
            int pilihanLocked = v_dosen.getPilihan();

            switch(pilihanLocked){
                case 1:
                    this.controllUbahPassword();
                    break;
                case 2:
                    this.controllLihatJadwalKelasDosen();
                    break;
                case 3:
                    this.controllGantiJadwal();
                    break;
                case 4:
                    this.controllDosenLogout();
                    break;
                default:
                    System.out.println("--Pilihan Salah--");
                    this.controllMenuUtama();
                    break;
            }
            
        }
    }
    
    //Lihat seluruh jadwal kelas per mahasiswa
    public void controllLihatJadwalKelasDosen() {
        ArrayList<Model_Kelas> kelas = m_dosen.getKelasByUsernameDosen();
        v_dosen.viewLihatJadwalKelasDosen(kelas);
        this.controllMenuUtama();
    }
    
    public void controllMenuKelolaAkunDosen()
    {
        v_dosen.viewEditDosenMenuUtama();
        int pilihan = v_dosen.getPilihan();
        
        switch(pilihan){
            case 1:
                this.controllUbahNama();
                break;
            case 2:
                this.controllUbahNip();
                break;
            case 3:
                this.controllUbahKompetensi();
                break;
            case 4:
                this.controllUbahHariLibur();
                break;
            case 99:
                this.controllMenuUtama();
                break;
            default:
                System.out.println("--Pilihan Salah--");
                this.controllMenuKelolaAkunDosen();
                break;
        }
    }
    
    public void controllUbahPassword()
    {
        v_dosen.viewCheckOldPasswordDosen();
        String oldPassword = v_dosen.getOldPassword();
        if (m_dosen.getPassword().equals(oldPassword)) //jika password lama telah sesuai
        {
            v_dosen.viewUbahPasswordDosen();
            if (m_dosen.jsonFileSetPassword(v_dosen.getPassword())) {
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
        }
        else
        {
            System.out.println("Password lama yang dimasukkan salah. Coba lagi!");
            this.controllUbahPassword();
        }
    }
    
    public void controllUbahNama()
    {
        v_dosen.viewEditDosenUbahNama();
        if(m_dosen.jsonFileSetNama(v_dosen.getNama())) {
            System.out.println("");
            System.out.println("--Nama berhasil diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunDosen();
        }else{
            System.out.println("");
            System.out.println("--Nama gagal diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunDosen();
        }
    }
    
    public void controllUbahNip()
    {
        v_dosen.viewEditDosenUbahNip();
        if(m_dosen.jsonFileSetNip(v_dosen.getNip())) {
            System.out.println("");
            System.out.println("--NIP berhasil diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunDosen();
        }else{
            System.out.println("");
            System.out.println("--NIP gagal diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunDosen();
        }
    }
    
    public void controllUbahKompetensi()
    {
        View_Admin v_lihatKompetensi = new View_Admin();
        v_lihatKompetensi.viewLihatKompetensiHeader();
        Model_Admin m_admin = new Model_Admin();
        ArrayList<Model_Kompetensi> listOfKompetensi = m_admin.getAllKompetensi();
        for (Model_Kompetensi d : listOfKompetensi) {
            v_lihatKompetensi.viewLihatKompetensiBody(d.getIdKompetensi(), d.getNamaKompetensi(), d.getIsPraktikum(), d.getDaftarReqKompetensi(), d.getBobotKompetensi());
        }
        
        v_dosen.viewEditDosenUbahKompetensi();
        if(m_dosen.jsonFileSetKompetensiDosen(v_dosen.getKompetensi())) {
            System.out.println("");
            System.out.println("--Kompetensi berhasil diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunDosen();
        }else{
            System.out.println("");
            System.out.println("--Kompetensi gagal diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunDosen();
        }
    }
     public void controllUbahHariLibur()
    {
        v_dosen.viewEditDosenUbahHariLibur();
        if(m_dosen.jsonFileSetHariLiburDosen(v_dosen.getHariLibur())) {
            System.out.println("");
            System.out.println("--Hari Libur berhasil diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunDosen();
        }else{
            System.out.println("");
            System.out.println("--Hari Libur gagal diubah--");
            System.out.println("");
            this.controllMenuKelolaAkunDosen();
        }
    }
     
    //LOGOUT
    public void controllDosenLogout()
    {
        Controller_Login cl = new Controller_Login();
        cl.controllerLoginUser();
    }
    
    //GANTI JADWAL
    public void controllGantiJadwal()
    {
        v_dosen.viewGantiJadwal();
        m_dosen.gantiJadwal(v_dosen.getTanggal());
        controllMenuUtama();
    }
}