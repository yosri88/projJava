/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import allforkids.forum.TopicController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class NotificationController implements Initializable {

    @FXML
    private AnchorPane notificationPanel;
    @FXML
    private Label notificationLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void show(String notificationMessage, NotificationType notifType) {
        this.notificationLabel.setText(notificationMessage);
        this.notificationPanel.setVisible(true);
        switch(notifType){
            case DANGER:
                notificationPanel.getStyleClass().add("notif-danger");
                break;
            case WARNING:
                notificationPanel.getStyleClass().add("notif-warning");
                break;
            case INFO:
                notificationPanel.getStyleClass().add("notif-info");
                break;
            case SUCCESS:
                notificationPanel.getStyleClass().add("notif-success");
                break;
        }

        final Timeline hide = new Timeline(
            new KeyFrame( 
                Duration.seconds(5), 
                new KeyValue(notificationPanel.opacityProperty(),0)
            ),
            new KeyFrame( 
                Duration.seconds(4), 
                new KeyValue(notificationPanel.visibleProperty(), false)
            )
        );
        hide.play();
    }
    
    public static void showNotification(ActionEvent event, String notificationString, NotificationType notifType) {
        try {
            FXMLLoader loader = new FXMLLoader(NotificationController.class.getResource("/helpers/Notification.fxml"));
            Pane newLoadedPane = loader.load();
            newLoadedPane.relocate(250, 0);
            NotificationController controller = loader.<NotificationController>getController();
            controller.show(notificationString, notifType);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            AnchorPane node = (AnchorPane)appStage.getScene().getRoot().getChildrenUnmodifiable().get(0);
            node.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(TopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
