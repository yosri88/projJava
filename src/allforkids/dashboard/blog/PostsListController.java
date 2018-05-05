/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.dashboard.blog;

import allforkids.blog.models.Post;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import helpers.DopsieCellBuilder;
import helpers.NavigationService;
import helpers.TrayNotificationService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class PostsListController implements Initializable {

    @FXML
    private TableView<?> postsTableView;
    @FXML
    private TableColumn<Post, String> authorCol;
    @FXML
    private TableColumn<Post, String> titleCol;
    @FXML
    private TableColumn<Post, String> thumbnailCol;
    
    private ArrayList<Post> posts;

    ObservableList obs = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        postsTableView.setEditable(true);
        authorCol.setEditable(false);
        titleCol.setEditable(true);
        thumbnailCol.setEditable(true);
        
        try {
            posts = Model.fetch(Post.class).all().where("online", "1").execute();
        } catch (ModelException | UnsupportedDataTypeException ex) {
            TrayNotificationService.failureRedNotification("Posts", "Could not fetch posts");
        }
        
        obs = FXCollections.observableArrayList(posts);

        
        authorCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            try {
                return ((Post)p).author().getFullName();
            } catch (ModelException ex) {
                Logger.getLogger(PostsListController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }));
        
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        titleCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            return ((Post) p).getAttr("title");
        }));
        
        
        
        
        thumbnailCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        thumbnailCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            return ((Post) p).getAttr("image_path");
        }));
        
        postsTableView.setItems(obs);
    }    

    @FXML
    private void goToMainMenu(ActionEvent event) {
        NavigationService.goTo(event, this, "/allforkids/dashboard/Main.fxml");
    }

    @FXML
    private void goToAdd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/allforkids/dashboard/blog/AddPost.fxml"));
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Pane newLoadedPane = loader.load();
            AddPostController controller = loader.<AddPostController>getController();
            controller.setStage(appStage);
            appStage.hide();
            Scene HomePageScene = new Scene(newLoadedPane);
            appStage.setScene(HomePageScene);
            appStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NavigationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("Do you confirm ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                Post selectedPost = (Post) postsTableView.getSelectionModel().getSelectedItem();
                selectedPost.setAttr("online", 0);
                selectedPost.save();
                posts.remove(selectedPost);
                postsTableView.getItems().remove(selectedPost);
                TrayNotificationService.successBlueNotification("Post deleted!", "Post deleted!");
            } catch (ModelException | UnsupportedDataTypeException ex) {
                TrayNotificationService.failureRedNotification("Delete Post", "Could not delete post");
            } 
        }
    }

    @FXML
    private void inputModified(TableColumn.CellEditEvent<Post, String> event) {
        Post post = event.getRowValue();
        String newData = event.getNewValue();
        String colName = event.getTableColumn().getText();
        System.out.println(colName);
        if(colName.equals("Title")) {
            post.setAttr("title", newData);
        }
        try {
            post.save();
            TrayNotificationService.successBlueNotification("Post updated!", "Post updated!");
        } catch (ModelException | UnsupportedDataTypeException ex) {
            TrayNotificationService.failureRedNotification("Post not updated!", "Post not updated!");
        }
    }

    @FXML
    private void thumbnailModified(TableColumn.CellEditEvent<Post, String> event) {
        
    }
    
}
