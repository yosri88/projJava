/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane topBar;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private AnchorPane dashboard;
    @FXML
    private AnchorPane _topBar;
    @FXML
    private AnchorPane _menuPane;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
            dashboard = FXMLLoader.load(getClass().getResource("/allforkids/dashboard/Dashboard.fxml"));
            _menuPane = FXMLLoader.load(getClass().getResource("/allforkids/dashboard/Menu.fxml"));
            _topBar = FXMLLoader.load(getClass().getResource("/allforkids/dashboard/TopBar.fxml"));
            setNode(dashboard);
            setNode(menuPane,_menuPane);
            setNode(topBar, _topBar);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


      //Set selected node to a content holder
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
          //Set selected node to a content holder
    private void setNode(AnchorPane pane, Node node) {
        pane.getChildren().clear();
        pane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
}
