/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.dashboard;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class MainController implements Initializable {

    @FXML
    private FontAwesomeIconView faIcon1;
    @FXML
    private Label label1;
    @FXML
    private FontAwesomeIconView faIcon21;
    @FXML
    private FontAwesomeIconView faIcon22;
    @FXML
    private FontAwesomeIconView faIcon211;
    @FXML
    private FontAwesomeIconView faIcon221;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       faIcon1.setIcon(FontAwesomeIcon.TH_LARGE);
       faIcon1.setStyle("-fx-text-fill: red;");
       label1.setText("Blog");
    }    
    
}
