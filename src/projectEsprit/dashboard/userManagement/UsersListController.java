/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectEsprit.dashboard.userManagement;

import com.jfoenix.controls.JFXTextField;
import projectEsprit.userManagement.models.Role;
import projectEsprit.userManagement.models.User;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import helpers.DopsieCellBuilder;
import helpers.NavigationService;
import helpers.TrayNotificationService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

/**
 * FXML Controller class
 *
 * @author wattouma
 */
public class UsersListController implements Initializable {

    @FXML
    private TableColumn<User, String> usernameCol;
    @FXML
    private TableColumn<User, String> firstNameCol;
    @FXML
    private TableColumn<User, String> lastNameCol;
    @FXML
    private TableColumn<User, String> phoneCol;
    @FXML
    private TableColumn<User, String> mailCol;
    @FXML
    private TableColumn<User,String> rolCol;

    ObservableList obs = null;
    @FXML
    private TableColumn<User,String> addressCol;
    @FXML
    private TableView<?> usersTableView;
    
    @FXML
    private JFXTextField searchTF;
    
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<User> filtredUsers = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        usersTableView.setEditable(true);
        usernameCol.setEditable(true);
        firstNameCol.setEditable(true);
        lastNameCol.setEditable(true);
        addressCol.setEditable(true);
        phoneCol.setEditable(true);
        mailCol.setEditable(true);
        rolCol.setEditable(true);
        
        try {
            users = Model.fetch(User.class).all().where("active", "1").execute();
        } catch (ModelException | UnsupportedDataTypeException ex) {
            Logger.getLogger(UsersListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        obs = FXCollections.observableArrayList(users);

        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        usernameCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            return (String)((User) p).getAttr("username");
        }));
        
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        firstNameCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            return (String)((User) p).getAttr("first_name");
        }));
        
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        lastNameCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            return (String)((User) p).getAttr("last_name");
        }));
        
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        addressCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            return (String)((User) p).getAttr("address");
        }));
        
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        phoneCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            return (String)((User) p).getAttr("phone");
        }));
        
        
        mailCol.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        mailCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            return (String)((User) p).getAttr("email");
        }));
        
        String[] roleValues = Stream.of(Role.values()).map(Role::name).toArray(String[]::new);
        ArrayList<Object> options = new ArrayList<>(Arrays.asList(roleValues));
        ObservableList dropOptions = FXCollections.observableArrayList(options);
        
        rolCol.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), dropOptions));
        rolCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            return ((User) p).getRole().name();
        }));
        
        usersTableView.setItems(obs);
        
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
                User selectedUser = (User) usersTableView.getSelectionModel().getSelectedItem();
                selectedUser.setAttr("active", "0");
                selectedUser.save();
                users.remove(selectedUser);
                usersTableView.getItems().remove(selectedUser);
                TrayNotificationService.successBlueNotification("User deleted!", "User deleted!");
            } catch (ModelException ex) {
                Logger.getLogger(UsersListController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedDataTypeException ex) {
                Logger.getLogger(UsersListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML
    private void inputModified(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        String newData = event.getNewValue();
        String colName = event.getTableColumn().getText();
        System.out.println(colName);
        if(colName.equals("Username")) {
            user.setAttr("username", newData);
        } else if(colName.equals("First Name")) {
            user.setAttr("first_name", newData);
        } else if(colName.equals("Last Name")) {
            user.setAttr("last_name", newData);
        } else if(colName.equals("Address")) {
            user.setAttr("address", newData);
        } else if(colName.equals("Phone")) {
            user.setAttr("phone", newData);
        } else if(colName.equals("E-mail")) {
            user.setAttr("email", newData);
        }
        try {
            user.save();
            TrayNotificationService.successBlueNotification("User updated!", "User updated!");
        } catch (ModelException | UnsupportedDataTypeException ex) {
            TrayNotificationService.failureRedNotification("User not updated!", "User not updated!");
        }
    }
    @FXML
    private void roleModified(TableColumn.CellEditEvent<User, String> event) {
        try {
            User user = event.getRowValue();
            String newRole = event.getNewValue();
            user.setRole(Role.valueOf(newRole));
            user.save();
            TrayNotificationService.successBlueNotification("User updated!", "User updated!");
        } catch (ModelException | UnsupportedDataTypeException ex) {
            TrayNotificationService.failureRedNotification("User not updated!", "User not updated!");
        }
            
    }
    
    @FXML
    private void searchClicked()
    {
        String searchString = this.searchTF.getText();
      filtredUsers =  (ArrayList) users
                    .stream()
                    .filter(c -> c.getFullName().contains(searchString))
                    .collect(Collectors.toList()) ;
      obs = FXCollections.observableArrayList(filtredUsers);
      usersTableView.setItems(obs);
        System.out.println("test" + searchString);
    }
    
    

    @FXML
    private void goToAdd(ActionEvent event) throws IOException {
        NavigationService.goTo(event, this, "AddUser.fxml");
    }

    @FXML
    private void goToMainMenu(ActionEvent event) throws IOException{
        NavigationService.goTo(event, this, "/projectEsprit/dashboard/Main.fxml");
    }
}
