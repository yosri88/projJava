/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.controllers;

import allforkids.orderManagement.controllers.ShoppingCartController.ShoppingItem;
import com.jfoenix.controls.JFXButton;
import com.sun.prism.impl.Disposer.Record;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

/**
 *
 * @author KHOUBEIB
 */
//Define the button cell
    public class ButtonCell extends TableCell<Record, Boolean> {
        final JFXButton cellButton = new JFXButton("Delete");
        
        ButtonCell( ObservableList<ShoppingItem> thisList){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	   ShoppingCartController.ShoppingItem currentItem = (ShoppingItem) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	   System.out.println(currentItem);
                           
                           thisList.remove(currentItem);
                    //    thisTableView.refresh();
                        
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }
