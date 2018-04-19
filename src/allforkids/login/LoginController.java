/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.login;

import allforkids.userManagement.models.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import helpers.BCrypt;
import helpers.NotificationController;
import helpers.NotificationType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.management.Notification;

/**
 * FXML Controller class
 *
 * @author w
 */
public class LoginController implements Initializable {

    @FXML
    private JFXButton goToRegister;
    @FXML
    private JFXTextField usernameTF;
    @FXML
    private JFXPasswordField passwordTF;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    
    @FXML
    private void goToRegister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allforkids/login/Register.fxml"));
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        Pane newLoadedPane = loader.load();
        Scene HomePageScene = new Scene(newLoadedPane);
        appStage.setScene(HomePageScene);
        appStage.show();
    }

    @FXML
    private void onLogin(ActionEvent event) throws IOException {
        String password = this.passwordTF.getText();
        String username = this.usernameTF.getText();
        if(username.isEmpty() || password.isEmpty()) {
            NotificationController.showNotification(event, "Login/Password fields are required", NotificationType.DANGER);
            return;
        } 
        else {
            try {
                User user = User.getByUsername(username);
                if(user == null) {
                    NotificationController.showNotification(event, "Login/Password incorrect", NotificationType.DANGER);
                    return;
                }
                boolean loginResult = BCrypt.checkpw(password, (String)user.getAttr("password"));
                if(loginResult == true) {
                    goToMainMenu(event);
                } else {
                    NotificationController.showNotification(event, "Login/Password incorrect", NotificationType.DANGER);
                }
            } catch(ModelException | UnsupportedDataTypeException ex) {
                NotificationController.showNotification(event, "Could not login", NotificationType.DANGER);
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
}
