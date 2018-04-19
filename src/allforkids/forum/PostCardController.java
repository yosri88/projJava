/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.forum;

import allforkids.forum.models.Post;
import allforkids.forum.models.User;
import allforkids.forum.models.Vote;
import allforkids.forum.models.VoteType;
import com.jfoenix.controls.JFXButton;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import helpers.NotificationController;
import helpers.NotificationType;
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
 * @author Wassim
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
    @FXML
    private Label voteCounter;
    
    private Post post;
    @FXML
    private JFXButton upArrowBtn;
    @FXML
    private JFXButton downArrowBtn;
    
    private int voteScore;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void setPost(Post post) {
        try {
            this.post = post;
            Date creationDate = new Date(((Date) post.getAttr("creation_date")).getTime());
            PrettyTime p = new PrettyTime();
            this.postMetaLabel.setText(p.format(creationDate));
            this.userAvatarNameLabel.setText((String) post.author().getAttr("first_name"));
            this.postContent.setText((String) post.getAttr("content"));
            this.voteScore = post.voteScore();
            this.voteCounter.setText(voteScore + "");
            User user = Model.find(User.class, 1);
            if(post.userVoted(user)){
                this.upArrowBtn.setDisable(true);
                this.downArrowBtn.setDisable(true);
            }
        } catch (ModelException ex) {
            Logger.getLogger(PostCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void upVote(ActionEvent event) {
        try {
            vote(VoteType.UP);
            this.voteCounter.setText(++this.voteScore + "");
            NotificationController.showNotification(event, "Upvoted", NotificationType.INFO);
        } catch(Exception e) {
            NotificationController.showNotification(event, "Failed to Upvote", NotificationType.WARNING);
        }
        
    }

    @FXML
    private void downVote(ActionEvent event) {
        try {
            vote(VoteType.DOWN);
            this.voteCounter.setText(--this.voteScore + "");
            NotificationController.showNotification(event, "Downvoted", NotificationType.INFO);
        } catch(Exception e) {
            NotificationController.showNotification(event, "Failed to Downvote", NotificationType.WARNING);
        }
    }
    
    public void vote(VoteType voteType) {
        this.upArrowBtn.setDisable(true);
        this.downArrowBtn.setDisable(true);
        Vote vote = new Vote();
        vote.setAttr("user_id", 1);
        vote.setAttr("post_id", this.post.getAttr("id"));
        vote.setVoteType(voteType);
        vote.save();
    }
}
