/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import allforkids.dashboard.blog.AddPostController;
import allforkids.blog.BlogMainController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class ChipController implements Initializable {

    

    @FXML
    private Label tagNameLabel;
    @FXML
    private JFXButton closeBtn;
    
    private AddPostController parentController;
    
    private BlogMainController blogMainParentController;
    
    @FXML
    private HBox parentHBox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
   
    public void setTagName(String tagName, boolean removable) {
        this.tagNameLabel.setText(tagName);
        if(removable == false) {
            this.parentHBox.getStylesheets().add("/helpers/Chip.css");
            this.parentHBox.getStyleClass().removeAll();
            
            this.parentHBox.getStyleClass().add("disabled-chip");
            this.tagNameLabel.getStyleClass().removeAll();
            
            this.tagNameLabel.getStyleClass().add("disabled-chip-label");
            this.parentHBox.getChildren().remove(closeBtn);
        }
    }
    
    public void setTagName(String tagName) {
        this.tagNameLabel.setText(tagName);
    }
    
    public void setParentController(AddPostController parentController) {
        this.parentController = parentController;
    }
    
    public void setBlogMainParentController(BlogMainController parentController) {
        this.blogMainParentController = parentController;
    }

    @FXML
    private void remove(ActionEvent event) {
        if(parentController != null ) {
            parentController.removeTag(this.tagNameLabel.getText());
        } else if (blogMainParentController != null) {
            blogMainParentController.removeTag(this.tagNameLabel.getText());
        }
    }
}
