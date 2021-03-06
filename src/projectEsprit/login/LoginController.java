/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectEsprit.login;


import projectEsprit.userManagement.models.User;
import projectEsprit.userManagement.models.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import helpers.BCrypt;
import helpers.MailingService;
import helpers.NavigationService;
import helpers.TrayNotificationService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projectEsprit/login/Register.fxml"));
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
            TrayNotificationService.failureRedNotification("Login/Password fields required", "Login/Password fields required");
            return;
        } 
        else {
            try {
                User user = User.getByUsername(username);
                if(user == null) {
                    TrayNotificationService.failureRedNotification("Login/Password incorrect", "Login/Password incorrect");
                    return;
                }
                boolean loginResult = BCrypt.checkpw(password, (String)user.getAttr("password"));
                if(loginResult == true) {
                    if((Integer)user.getAttr("active") == 1) {
                           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
                           LocalDateTime now = LocalDateTime.now();  
                        UserSession.setInstance(user);
                        TrayNotificationService.successBlueNotification("Welcome", "Welcome " +  (String)user.getAttr("first_name"));
                        MailingService.send((String) UserSession.getInstance().getAttr("email"),  "Login Alert", 
                    "You've sign in at "+dtf.format(now)+". if you are not expecting this email; contact the application administrator");
                        NavigationService.goTo(event, this, "/projectEsprit/welcome/Welcome.fxml");
                    } 
                } else {
                    TrayNotificationService.failureRedNotification("Login/Password incorrect", "Login/Password incorrect");
                }
            } catch(ModelException | UnsupportedDataTypeException ex) {
                TrayNotificationService.failureRedNotification("Could not login", "Could not login");
            } 
        }
    }
}
