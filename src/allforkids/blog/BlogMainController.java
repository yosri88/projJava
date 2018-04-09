/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.blog;

import allforkids.blog.models.Post;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class BlogMainController implements Initializable {

    @FXML
    private FlowPane allPostsFlowPane;
    @FXML
    private ScrollPane allPostsScrollPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            allPostsFlowPane.prefWidthProperty().bind(allPostsScrollPane.prefWidthProperty().add(45));
            allPostsScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

            ArrayList<Post> allPosts = Model.fetch(Post.class).all().orderBy("creation_date", "DESC").execute();
            for (Post post : allPosts) {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PostCard.fxml"));
                Pane newLoadedPane = loader.load();
                newLoadedPane.prefWidthProperty().bind(allPostsFlowPane.prefWidthProperty());
                PostCardController controller = loader.<PostCardController>getController();
                controller.setPostData(post);
                allPostsFlowPane.getChildren().add(newLoadedPane);
            }
        } catch (UnsupportedDataTypeException | ModelException ex) {
            Logger.getLogger(BlogMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlogMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToAddPost(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPost.fxml"));
            Parent parent = loader.load();
            Scene HomePageScene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            AddPostController controller = loader.<AddPostController>getController();
                controller.setStage(appStage);
            appStage.setScene(HomePageScene);
            appStage.show();

        } catch (IOException ex) {
            Logger.getLogger(BlogMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class PostPane extends Control {

    }

}
