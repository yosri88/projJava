/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.blog;

import helpers.ChipController;
import allforkids.blog.models.Post;
import allforkids.blog.models.PostTag;
import allforkids.blog.models.Tag;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import helpers.HtmlEditorWithImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class AddPostController implements Initializable {

    @FXML
    private JFXButton AddPostButton;
    private JFXTextArea contentTextArea;
    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXButton AddTagButton;
    @FXML
    private Pane tagsPane;
    @FXML
    private JFXTextField newTagTF;

    private HashMap<String, Pane> tags;
    @FXML
    private AnchorPane addPostPane;
    @FXML
    private AnchorPane htmlEditorAP;
    
    private HtmlEditorWithImage htmlEditor;
    @FXML
    private ScrollPane htmlEditorSP;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tags = new HashMap<String, Pane>();  
       
    }
    public void setStage(Stage stage) {
        this.htmlEditor = new HtmlEditorWithImage(stage, 515, 1008);
        this.htmlEditorAP.getChildren().add(htmlEditor);
        this.htmlEditor.prefWidthProperty().bind(htmlEditorAP.prefWidthProperty());

    }
    @FXML
    private void addPost(ActionEvent event) {
        try {
            String title = titleTextField.getText();
            String content = this.htmlEditor.getHtmlText();
            ArrayList<Tag> allPostTags = new ArrayList<>();
            ArrayList<Tag> tagFromDB;
            for(String tagName: this.tags.keySet()) {
                try {
                    System.out.println(tagName);
                    tagFromDB = Model.fetch(Tag.class)
                            .all()
                            .where("name", tagName)
                            .execute();
                    if(tagFromDB.isEmpty()) {
                        Tag newTag = new Tag();
                        newTag.setAttr("name", tagName);
                        newTag.save();
                        allPostTags.add(newTag);
                    } else {
                        allPostTags.add(tagFromDB.get(0));
                    }
                } catch(ModelException| UnsupportedDataTypeException ex ) {
                    System.out.println(ex.getMessage());
                }
            }
            System.out.println(allPostTags);
            Post post = new Post();
            Timestamp now = new Timestamp(new Date().getTime());
            post.setAttr("title", title);
            post.setAttr("content", content);
            post.setAttr("user_id", 1);
            post.setAttr("creation_date", now);
            post.save();
            
            for(Tag tag: allPostTags) {
                PostTag postTag = new PostTag();
                postTag.setAttr("post_id", post.getAttr("id"));
                postTag.setAttr("tag_id", tag.getAttr("id"));
                postTag.save();
            }
            
            goBack(event);
        } catch (ModelException ex) {
            Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedDataTypeException ex) {
            Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/allforkids/blog/BlogMain.fxml"));
            Scene HomePageScene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            appStage.hide();
            appStage.setScene(HomePageScene);
            appStage.show();

        } catch (IOException ex) {
            Logger.getLogger(BlogMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addTag(ActionEvent event) {
        newTagTF.getScene().getStylesheets().add("/helpers/Chip.css");
        String tagName = newTagTF.getText();
        if (!tagName.isEmpty() && !this.tags.containsKey(tagName)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/helpers/Chip.fxml"));
                Pane newLoadedPane = loader.load();
                ChipController controller = loader.<ChipController>getController();
                controller.setTagName(tagName);
                controller.setParentController(this);
                tagsPane.getChildren().add(newLoadedPane);
                this.tags.put(tagName, newLoadedPane);
                this.newTagTF.clear();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void removeTag(String tagName) {
        Pane chip = this.tags.get(tagName);
        this.tags.remove(tagName);
        this.tagsPane.getChildren().remove(chip);
    }

    @FXML
    private void TagTfKeyReleased(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            this.addTag(null);
        }
    }
    
}
