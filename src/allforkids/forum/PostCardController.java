/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.forum;

import allforkids.forum.models.Post;
import dopsie.exceptions.ModelException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.ocpsoft.prettytime.PrettyTime;

/**
 * FXML Controller class
 *
 * @author wattouma
 */
public class PostCardController implements Initializable {

    @FXML
    private ImageView userAvatarImView;
    @FXML
    private Label userAvatarNameLabel;
    @FXML
    private Text postContent;
    @FXML
    private Label postMetaLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void setPost(Post post) {
        try {
            Date creationDate = new Date(((Date) post.getAttr("creation_date")).getTime());
            PrettyTime p = new PrettyTime();
            this.postMetaLabel.setText(p.format(creationDate));
            this.userAvatarNameLabel.setText((String) post.author().getAttr("first_name"));
            this.postContent.setText((String) post.getAttr("content"));
        } catch (ModelException ex) {
            Logger.getLogger(PostCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
