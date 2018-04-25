/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.controllers;

import allforkids.orderManagement.models.LineItem;
import allforkids.orderManagement.models.Product;
import allforkids.orderManagement.models.User;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author KHOUBEIB
 */
public class ShoppingCartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public ArrayList<LineItem> getUserLineItems (User u) throws ModelException, UnsupportedDataTypeException{
           
        ArrayList<LineItem> allLineItem = Model.fetch(LineItem.class).all().execute();
        return allLineItem;
        
    }
}
