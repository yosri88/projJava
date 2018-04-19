/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.login;

import allforkids.blog.BlogMainController;
import allforkids.userManagement.models.User;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import helpers.BCrypt;
import helpers.NotificationController;
import helpers.NotificationType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wattouma
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
            NotificationController.showNotification(event, "Fields should not be empty", NotificationType.DANGER);
        } else {
            User user = new User();
            user.setAttr("first_name", firstName);
            user.setAttr("last_name", lastName);
            user.setAttr("username", username);
            user.setAttr("email", email);
            user.setAttr("password", BCrypt.hashpw(password, BCrypt.gensalt()));
            user.save();
            if(user.getAttr("id") == null) {
                NotificationController.showNotification(event, "Could not register", NotificationType.DANGER);
            } else {
                
            }
        }
    }
    
    private void goToMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allforkids/welcome/Welcome.fxml"));
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        Pane newLoadedPane = loader.load();
        Scene HomePageScene = new Scene(newLoadedPane);
        appStage.setScene(HomePageScene);
        appStage.show();
    }

    @FXML
    private void backToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/allforkids/login/Login.fxml"));
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.hide();
            Pane newLoadedPane = loader.load();
            Scene HomePageScene = new Scene(newLoadedPane);
            appStage.setScene(HomePageScene);
            appStage.show();
        } catch (IOException ex) {
            Logger.getLogger(BlogMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
