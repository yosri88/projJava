/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.forum;

import dopsie.exceptions.ModelException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import allforkids.forum.models.Thread;
import allforkids.forum.models.Topic;
import allforkids.forum.models.Post;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wattouma
 */
public class TopicController implements Initializable {

    @FXML
    private Label mainTitleLabel;
    @FXML
    private ScrollPane allThreadsSP;
    @FXML
    private VBox allThreadsVBox;

    private Topic topic;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setTopic(Topic currentTopic) {
        this.topic = currentTopic;
        ArrayList<Thread> allThreads = new ArrayList<>();
        try {
            allThreads = currentTopic.threads();
        }
        catch(ModelException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(allThreads);
        showThreads(allThreads);
    }

    @FXML
    private void backToForum(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/allforkids/forum/ForumMain.fxml"));
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.hide();
            Pane newLoadedPane = loader.load();
            Scene HomePageScene = new Scene(newLoadedPane);
            appStage.setScene(HomePageScene);
            appStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void showThreads(ArrayList<Thread> threads) {
        try {
            allThreadsVBox.getChildren().removeAll(allThreadsVBox.getChildren());
            if(threads.isEmpty()) {
                Label label = new Label(" No Thread ");
                allThreadsVBox.getChildren().add(label);
            }
            for (Thread thread : threads) {       
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Card.fxml"));
                Pane newLoadedPane = loader.load(); 
                newLoadedPane.prefWidthProperty().bind(allThreadsVBox.prefWidthProperty());
                CardController controller = loader.<CardController>getController();
                controller.setContent((String)thread.getAttr("title"),  thread.posts().size() + " Posts", thread, (a, b) -> goToThread((ActionEvent) a, (Thread)b));
                allThreadsVBox.getChildren().add(newLoadedPane);
            }
        } catch (IOException | ModelException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void goToThread(ActionEvent event ,Thread thread) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/allforkids/forum/Thread.fxml"));
//            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            appStage.hide();
//            Pane newLoadedPane = loader.load();
//            Scene HomePageScene = new Scene(newLoadedPane);
//            TopicController controller = loader.<TopicController>getController();
//            controller.setThread(thread);
//            appStage.setScene(HomePageScene);
//            appStage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
    }
    
}
