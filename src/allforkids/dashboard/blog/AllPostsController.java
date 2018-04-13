/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.dashboard.blog;

import helpers.DopsieCellBuilder;
import allforkids.blog.models.Post;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class AllPostsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<?> allPostsTV;
    @FXML
    private TableColumn<?, ?> titleCol;
    @FXML
    private TableColumn<?, ?> authorCol;

    @FXML
    private TableColumn actionsCol;

    ObservableList obs = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<Post> allPosts = Model.fetch(Post.class).all().orderBy("creation_date", "DESC").execute();
            obs = FXCollections.observableArrayList(allPosts);

            titleCol.setCellValueFactory(new DopsieCellBuilder(p -> {
                return ((Post) p).getAttr("title");
            }));

            authorCol.setCellValueFactory(new DopsieCellBuilder(p -> {
                try {
                    return ((Post) p).author().getAttr("first_name");
                } catch (ModelException ex) {
                    System.out.println(ex.getMessage());
                }
                return null;
            }));

            
            //Adding the Button to the cell
            actionsCol.setCellFactory(
                    new Callback<TableColumn<Post, Boolean>, TableCell<Post, Boolean>>() {
                @Override
                public TableCell<Post, Boolean> call(TableColumn<Post, Boolean> p) {
                    return new ButtonCell();
                }

            });
            allPostsTV.setItems(obs);
        } catch (UnsupportedDataTypeException | ModelException ex) {
            Logger.getLogger(AllPostsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editBegan(TableColumn.CellEditEvent<?, ?> event) {
        System.out.println(event);
    }

    private class ButtonCell extends TableCell<Post, Boolean> {

        final Button cellButton = new Button("Delete");

        ButtonCell() {

            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                    Post currentPost = (Post) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    //remove selected item from the table list
                    obs.remove(currentPost);
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }
}
