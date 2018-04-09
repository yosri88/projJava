/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.controllers;

import allforkids.orderManagement.models.Order;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author KHOUBEIB
 */
public class OrdersTreeController implements Initializable {

    @FXML
    private TreeTableView<Order> treeOrderTable;
    @FXML
    private TreeTableColumn<Order, String> orderRefColumn;
    @FXML
    private TreeTableColumn<Order, Integer> orderSatusColumn;
    @FXML
    private TreeTableColumn<Order, Integer> orderCustomerColumn;
    @FXML
    private TreeTableColumn<Order, Integer> orderAmountColumn;

    ObservableList<Order> orders = FXCollections.observableArrayList();
   

    private ObservableList<Order> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        orderRefColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        orderRefColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
//        orderRefColumn.setCellValueFactory(new PropertyValueFactory<>("order_status"));
//        orderRefColumn.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
    }

    
  
}
