/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.controllers;

import allforkids.orderManagement.models.Order;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author KHOUBEIB
 */
public class OrderTableController implements Initializable {

    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> orderRefColumn;
    @FXML
    private TableColumn<Order, Number> orderSatusColumn;
    @FXML
    private TableColumn<Order, Number> orderCustomerColumn;
    @FXML
    private TableColumn<Order, Number> orderAmountColumn;

    
    ObservableList<Order> orderList = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
              
       ArrayList<Order> orders = null;
 /*       try {
            orders = Model.fetch(Order.class).all().execute();
        } catch (ModelException ex) {
            Logger.getLogger(OrderTableController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedDataTypeException ex) {
            Logger.getLogger(OrderTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
        
        try {
            ResultSet rs = Order.sqlQuery("SELECT order_reference, "
                    + "order_status, "
                    + "customer_id, "
                    + "payment_status"
                    + "  FROM `order`");
            
            while (rs.next()){
                
                System.out.println(rs.getString("order_reference"));
                
                orderList.add(new Order(
                        
                        rs.getString("order_reference"), 
                        rs.getInt("order_status"),
                        rs.getInt("customer_id"),
                        rs.getInt("payment_status")                    
                                              
                ));
            }
            
        //    System.out.println("Lines number :"+orderList.stream().count());
        System.out.println( System.getProperties());  
        
        
        } catch (UnsupportedDataTypeException ex) {
            Logger.getLogger(OrderTableController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
        orderRefColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("order_reference"));
        orderSatusColumn.setCellValueFactory(new PropertyValueFactory<>("order_status"));
        orderCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        orderAmountColumn.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
        
        
                
        orderTable.setItems(orderList);
        
    }    
   
    
    
}
