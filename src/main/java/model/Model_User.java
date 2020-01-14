package model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controller.Controller_Admin;
import controller.Controller_Dosen;
import controller.Controller_Mahasiswa;
import java.io.File;
import java.io.IOException;

public class Model_User {
    private String nama;
    private String username;
    private String password;
    private int role;
    
   public Model_User(){
       this.nama = "";
       this.username = "";
       this.password = "";
       this.role = 0;
   }
    
    public Model_User(String nama){
        this.nama = nama;
        this.username = this.nama.trim();
        this.password = this.username;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getNama(){
        return this.nama;
    }
    
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setNama(String nama)
    {
        this.nama = nama;
    }
    
     public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    //RETURN ROLENYA SAAT LOGIN
    public int jsonFileGetRole(String username, String password){
        
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    if(root.path("username").asText().equals(username) && root.path("password").asText().equals(password)){
                        this.setRole(root.path("role").asInt());
                        return root.path("role").asInt();
                    }                    
                }
            }
            return 0;
        }catch(IOException e){
            return 0;
        }
    }
    
    
    public boolean jsonFileSetPassword(String password){
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    /* Jika id yang akan diedit sesuai */
                    if(root.path("username").asText().equals(this.getUsername())){
                        
                        ((ObjectNode) root ).put("password", password);
                        this.setPassword(password);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);
                        return true;
                    }                    
                }
            }
            return false;
        }catch(IOException e){
            return false;
        }
    }
     
    public boolean jsonFileSetNama(String newNama){
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/User.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    /* Jika id yang akan diedit sesuai */
                    if(root.path("username").asText().equals(this.getUsername())){
                        
                        ((ObjectNode) root ).put("nama", newNama);
                        this.setNama(newNama);
                        mapper.writeValue(new File("dataJSON/User.json"), (ArrayNode) rootArray);
                        return true;
                    }                    
                }
            }
            return false;
        }catch(IOException e){
            return false;
        }
    }
    
    public void login(int role, String username) {
        switch (role) {
            case 1:
                Model_Admin admin = new Model_Admin();
                Controller_Admin controllerAdmin = new Controller_Admin(admin.getAdminFromJson(username));
                controllerAdmin.controllMenuUtama();
                break;
            case 2:
                Model_Dosen dosen = new Model_Dosen();
                Controller_Dosen controllerDosen = new Controller_Dosen(dosen.getDosenFromJson(username));
                controllerDosen.controllMenuUtama();
                break;
            case 3:
                Model_Mahasiswa mahasiswa = new Model_Mahasiswa();
                Controller_Mahasiswa controllerMahasiswa = new Controller_Mahasiswa(mahasiswa.getMahasiswaFromJson(username));
                controllerMahasiswa.controllMenuUtama();
                break;
            default:
                System.out.println("Username atau password salah");
                break;
        }
    }
  
    public void setIsLockedJson(boolean isLocked)
    {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/IsLocked.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    ((ObjectNode) root ).put("isLocked", isLocked);
                    mapper.writeValue(new File("dataJSON/IsLocked.json"), (ArrayNode) rootArray);                  
                }
            }
        }catch(IOException e){
        }
    }
    
    
    public boolean getIsLockedJson()
    {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootArray = mapper.readTree(new File("dataJSON/IsLocked.json"));

            if (rootArray.isArray()) {
                for(JsonNode root : rootArray){
                    return root.path("isLocked").asBoolean();                 
                }
            }
            return false;
        }catch(IOException e){
            return false;
        }
    }
}
