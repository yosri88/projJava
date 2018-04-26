/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.login;

import allforkids.userManagement.models.Role;
import allforkids.userManagement.models.User;
import allforkids.userManagement.models.UserSession;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import helpers.BCrypt;
import helpers.NavigationService;
import helpers.TrayNotificationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class RegisterController implements Initializable {

    @FXML
    private JFXTextField usernameTF;
    @FXML
    private JFXPasswordField passwordTF;
    @FXML
    private JFXTextField firstNameTF;
    @FXML
    private JFXTextField lastNameTF;
    @FXML
    private JFXTextField mailTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onRegister(ActionEvent event) {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String email = mailTF.getText();
        
        if(username.isEmpty() 
                || password.isEmpty() 
                || firstName.isEmpty()
                || lastName.isEmpty()
                || email.isEmpty()) {
            TrayNotificationService.failureRedNotification("All fields required", "All fields required");
        } else {
            try {
                User user = new User();
                user.setAttr("first_name", firstName);
                user.setAttr("last_name", lastName);
                user.setAttr("username", username);
                user.setAttr("email", email);
                user.setAttr("password", BCrypt.hashpw(password, BCrypt.gensalt()));
                user.setRole(Role.USER);
                user.save();
                if(user.getAttr("id") == null) {
                    TrayNotificationService.failureRedNotification("Could not register", "Could not register");
                } else {
                    UserSession.setInstance(user);
                    NavigationService.goTo(event, this, "/allforkids/welcome/Welcome.fxml");
                }
            } catch (ModelException | UnsupportedDataTypeException ex) {
                TrayNotificationService.failureRedNotification("Could not register", "Could not register");
            } 
        }
    }
   
    @FXML
    private void backToLogin(ActionEvent event) {
        NavigationService.goTo(event, this, "/allforkids/login/Login.fxml");
    }
    
}
