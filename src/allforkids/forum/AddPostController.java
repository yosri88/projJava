/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.forum;

import allforkids.forum.models.Post;
import allforkids.forum.models.Thread;
import allforkids.userManagement.models.User;
import com.jfoenix.controls.JFXTextArea;
import helpers.TrayNotificationService;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author wattouma
 */
public class AddPostController implements Initializable {

    @FXML
    private ImageView userAvatarImView;
    @FXML
    private Label userAvatarNameLabel;
    @FXML
    private JFXTextArea newPostContentTA;

    private Thread thread;
    
    private Consumer addPostCallback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setConnectedUser(User user) {
        
    }
    public void setCallback(Consumer callback) {
        this.addPostCallback = callback;
    }
    public void setThread(Thread thread) {
        this.thread = thread;
    }
    
    
    @FXML
    private void addPost(ActionEvent event) {
        if(newPostContentTA.getText().isEmpty()) {
            TrayNotificationService.failureRedNotification("Adding a post", "Post content should not be empty");
            return;
        }
        try{
            Post post = new Post();
            Timestamp now = new Timestamp(new Date().getTime());
            post.setAttr("content", newPostContentTA.getText());
            post.setAttr("user_id", 1);
            post.setAttr("creation_date", now);
            post.setAttr("thread_id", thread.getAttr("id"));
            post.save();
            TrayNotificationService.faceBlueNotification("Adding a post", "Post added successfully");
            this.addPostCallback.accept(post);
        } catch(Exception e) {
            TrayNotificationService.failureRedNotification("Adding a post", "Failed to add post");
            System.out.println(e.getMessage());
        }
    }

    
}
