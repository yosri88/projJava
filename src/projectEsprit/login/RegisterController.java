/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectEsprit.login;

import projectEsprit.userManagement.models.Role;
import projectEsprit.userManagement.models.User;
import projectEsprit.userManagement.models.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import helpers.BCrypt;
import helpers.NavigationService;
import helpers.SMSService;
import helpers.SMSServiceV;
import helpers.TrayNotificationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static java.lang.Math.toIntExact;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

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
    @FXML
    private JFXTextField phoneTF;
    @FXML
    private JFXTextField verifCodeTf;
    @FXML
    private HBox phoneVerifHbox;

    private String smsCode;
    @FXML
    private JFXButton registerBtn;
    @FXML
    private JFXButton sendBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerBtn.setDisable(true);
    }

    @FXML
    private void onRegister(ActionEvent event) {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        String email = mailTF.getText();
        String phone = phoneTF.getText();
        String code = verifCodeTf.getText();

        if (username.isEmpty()
                || password.isEmpty()
                || firstName.isEmpty()
                || lastName.isEmpty()
                || email.isEmpty()
                || phone.isEmpty()) {
            TrayNotificationService.failureRedNotification("All fields required", "All fields required");
        } else if (!code.equals(this.smsCode)) {
            TrayNotificationService.failureRedNotification("Verification Code", "Wrong verification code");
        } else {
            try {
                User user = new User();
                user.setAttr("first_name", firstName);
                user.setAttr("last_name", lastName);
                user.setAttr("username", username);
                user.setAttr("email", email);
                user.setAttr("phone", phone);
                user.setAttr("password", BCrypt.hashpw(password, BCrypt.gensalt()));
                user.setRole(Role.USER);
                user.save();
                if (user.getAttr("id") == null) {
                    TrayNotificationService.failureRedNotification("Could not register", "Could not register");
                } else {
                    User connectedUser = Model.find(User.class, toIntExact((Long) user.getAttr("id")));
                    UserSession.setInstance(connectedUser);
                    SMSServiceV.send(phone, "Welcome to Projet Esprit");
                    NavigationService.goTo(event, this, "/projectEsprit/welcome/Welcome.fxml");
                }
            } catch (ModelException | UnsupportedDataTypeException ex) {
                TrayNotificationService.failureRedNotification("Could not register", "Could not register");
            }
        }
    }

    @FXML
    private void backToLogin(ActionEvent event) {
        NavigationService.goTo(event, this, "/projectEsprit/login/Login.fxml");
    }

    @FXML
    private void phoneTyped(KeyEvent event) {
        String phone = phoneTF.getText();
        if (phone.isEmpty()) {
            phoneVerifHbox.setVisible(false);
        } else {
            phoneVerifHbox.setVisible(true);
        }
    }

    @FXML
    private void sendVerifSMS(ActionEvent event) {
        String phoneNumber = phoneTF.getText();
        if (phoneNumber.length() != 8) {
            TrayNotificationService.failureRedNotification("Phone Number", "Phone number should contain 8 digits");
            return;
        }
        smsCode = Math.round((Math.random() * (9998 - 1000) + 1000)) + "";
        sendBtn.setDisable(true);
        sendBtn.setText("Sending..");
        SMSServiceV.send(phoneNumber, "You registration code is : " + smsCode);
        sendBtn.setText("Sent");

    }
/*
    NexmoClient client = new NexmoClient.Builder
            .apiKey("86d3b9f3")
            .apiSecret("gpA9sebJmSerEDaz")
            .build();

    String messageText = "Hello from Vonage SMS API";
    TextMessage message = new TextMessage("Vonage APIs", "21655110222", messageText);

    SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

    for (SmsSubmissionResponseMessage responseMessage

    : response.getMessages () 
        ) {
    System.out.println(responseMessage);
    }
*/
    @FXML
    private void verifCodeTyped(KeyEvent event) {
        String code = verifCodeTf.getText();
        if (code.length() == 4) {
            registerBtn.setDisable(false);
        } else {
            registerBtn.setDisable(true);
        }
    }

}
