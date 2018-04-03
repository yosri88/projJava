/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.blog;

import allforkids.blog.models.Post;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class AddPostController implements Initializable {

    @FXML
    private JFXButton AddPostButton;
    @FXML
    private JFXTextArea contentTextArea;
    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXButton AddTagButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addPost(ActionEvent event) {
        String title = titleTextField.getText();
        String content = contentTextArea.getText();
        Post post = new Post();
        post.setAttr("title", title);
        post.setAttr("content", content);
        post.setAttr("user_id", 1);
        post.save();
    }

    @FXML
    private void Cancel(ActionEvent event) {
        System.out.println("Cancel");
    }
    
}
