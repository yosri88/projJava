/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.dashboard;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class MenuController implements Initializable {

    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnPricing;
    @FXML
    private JFXButton btnContacts;
    @FXML
    private JFXButton btnWidgets;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnAlerts;
    @FXML
    private JFXButton btnControls;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchPricing(ActionEvent event) {
    }

    @FXML
    private void switchContacts(ActionEvent event) {
    }

    @FXML
    private void switchWidget(ActionEvent event) {
    }

    @FXML
    private void switchProfile(ActionEvent event) {
    }

    @FXML
    private void switchAlert(ActionEvent event) {
    }

    @FXML
    private void switchControls(ActionEvent event) {
    }
    
}
