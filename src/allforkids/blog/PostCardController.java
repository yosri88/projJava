/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.blog;

import allforkids.blog.models.Post;
import allforkids.blog.models.Tag;
import allforkids.forum.models.User;
import dopsie.exceptions.ModelException;
import helpers.ChipController;
import helpers.CustomImageViewPane;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class PostCardController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
    private Label postedByLabel;

    private Post post;
    
    @FXML
    private FlowPane tagsFlowPane;
    @FXML
    private AnchorPane imageContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageContainer.getChildren().add(new CustomImageViewPane("/img/default-blog.png", 280f, 200f));
    }

    public void setPostData(Post post) throws ModelException {
        this.post = post;
        this.titleLabel.setText((String) post.getAttr("title"));
        String imagePath = (String) post.getAttr("image_path");
        if(imagePath != null) {
            
            String absolutePath =  System.getProperty("uploads_folder");
            imagePath = "file:" + absolutePath + imagePath;
            imageContainer.getChildren().add(new CustomImageViewPane(imagePath, 280f, 200f));
        }
    }
    
    private void addTag(String tagName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/helpers/Chip.fxml"));
            Pane newLoadedPane = loader.load();
            ChipController controller = loader.<ChipController>getController();
            controller.setTagName(tagName, false);
            tagsFlowPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void readMoreBtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/allforkids/blog/PostDetails.fxml"));
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.hide();
            Pane newLoadedPane = loader.load();
            Scene HomePageScene = new Scene(newLoadedPane);
            PostDetailsController controller = loader.<PostDetailsController>getController();
            controller.setPostData(post);
            appStage.setScene(HomePageScene);
            appStage.show();
        } catch (IOException ex) {
            Logger.getLogger(BlogMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
