package controller;

import model.Model_User;
import view.View_Login;

/**
 *
 * @author Liptia
 */
public class Controller_Login {
    
    public void controllerLoginUser()
    {
        Model_User user = new Model_User();
        View_Login v_login = new View_Login();
        int role = 0;
        do{
            v_login.viewLogin();
            role = user.jsonFileGetRole(v_login.getUsername(), v_login.getPassword());
            user.login(role, v_login.getUsername());
        }while (role == 0);
    }
}
