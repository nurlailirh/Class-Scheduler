package view;
import java.util.Scanner;

public class View_Login {
    private String username;
    private String password;
    
    public void viewLogin(){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("==============LOGIN==============");
        System.out.print("Username: ");
        this.setUsername(input.nextLine());
        System.out.print("Password: ");
        this.setPassword(input.nextLine());
    }
    
    public void setUsername(String user){
        this.username = user;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public String getPassword(){
        return this.password;
    }
}
