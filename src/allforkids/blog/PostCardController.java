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
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
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
    private WebView contentWebView;
    @FXML
    private FlowPane tagsFlowPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setPostData(Post post) throws ModelException {
        this.post = post;
        this.titleLabel.setText((String) post.getAttr("title"));
        
        this.contentWebView.getChildrenUnmodifiable().addListener(new ListChangeListener<Node>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Node> change) {
                    Set<Node> deadSeaScrolls = contentWebView.lookupAll(".scroll-bar");
                    for (Node scroll : deadSeaScrolls) {
                        scroll.setVisible(false);
                    }
                }
            });
        final WebEngine webEngine = this.contentWebView.getEngine();
        webEngine.loadContent((String) post.getAttr("content"));
        User user = post.author();
        String userFirstName = (String)user.getAttr("first_name");
        this.postedByLabel.setText("Posted by " + userFirstName);
        
        ArrayList<Tag> postTags = post.tags();
        for(Tag tag: postTags) {
            addTag((String)tag.getAttr("name"));
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
