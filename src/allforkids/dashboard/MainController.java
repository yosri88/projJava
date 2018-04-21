/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.dashboard;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpers.NavigationService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class MainController implements Initializable {

    private Label label1;
    @FXML
    private FontAwesomeIconView usersIcon;
    @FXML
    private Label usersLabel;
    @FXML
    private FontAwesomeIconView faIcon11;
    @FXML
    private Label label11;
    @FXML
    private FontAwesomeIconView faIcon111;
    @FXML
    private Label label111;
    @FXML
    private FontAwesomeIconView faIcon12;
    @FXML
    private Label label12;
    @FXML
    private FontAwesomeIconView faIcon13;
    @FXML
    private Label label13;
    @FXML
    private FontAwesomeIconView faIcon14;
    @FXML
    private Label label14;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       usersIcon.setIcon(FontAwesomeIcon.USERS);
       usersIcon.setStyle("-fx-text-fill: red;");
       usersLabel.setText("Users");
    }    

    @FXML
    private void userMenuClicked(ActionEvent event) throws IOException {
        NavigationService.goTo(event, this, "/allforkids/dashboard/userManagement/UsersList.fxml");
    }

    @FXML
    private void goToWelcome(ActionEvent event) {
        NavigationService.goTo(event, this, "/allforkids/welcome/Welcome.fxml");
    }
    
}
