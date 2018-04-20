/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.dashboard;

import allforkids.login.RegisterController;
import allforkids.userManagement.models.Role;
import allforkids.userManagement.models.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import helpers.BCrypt;
import helpers.NotificationController;
import helpers.NotificationType;
import helpers.TrayNotificationService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
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
 * @author Wassim
 */
public class AddUserController implements Initializable {

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
    @FXML
    private JFXComboBox<String> roleDropDown;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] roleValues = Stream.of(Role.values()).map(Role::name).toArray(String[]::new);
        ArrayList<Object> options = new ArrayList<>(Arrays.asList(roleValues));
        for(Object option: options) {
            roleDropDown.getItems().add((String)option);
        }
    }    

    @FXML
    private void onRegister(ActionEvent event) throws IOException {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String email = mailTF.getText();
        String role = roleDropDown.getValue();
        
        if(username.isEmpty() 
                || password.isEmpty() 
                || firstName.isEmpty()
                || lastName.isEmpty()
                || email.isEmpty()) {
            NotificationController.showNotification(event, "Fields should not be empty", NotificationType.DANGER);
        } else {
            try {
                User user = new User();
                user.setAttr("first_name", firstName);
                user.setAttr("last_name", lastName);
                user.setAttr("username", username);
                user.setAttr("email", email);
                user.setAttr("password", BCrypt.hashpw(password, BCrypt.gensalt()));
                user.setRole(Role.valueOf(role));
                user.save();
                TrayNotificationService.successBlueNotification("User added!", "User added!");
            } catch (ModelException | UnsupportedDataTypeException ex) {
                TrayNotificationService.failureRedNotification("User not added!", "User not added!");
            } finally {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersList.fxml"));
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.hide();
                Pane newLoadedPane = loader.load();
                Scene HomePageScene = new Scene(newLoadedPane);
                appStage.setScene(HomePageScene);
                appStage.show();
            }
            
        }
    }

    @FXML
    private void goToUsersList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/allforkids/dashboard/UsersList.fxml"));
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        Pane newLoadedPane = loader.load();
        Scene HomePageScene = new Scene(newLoadedPane);
        appStage.setScene(HomePageScene);
        appStage.show();
    }
    
    
}