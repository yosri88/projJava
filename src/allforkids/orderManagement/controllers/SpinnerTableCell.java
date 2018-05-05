/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.controllers;

import allforkids.orderManagement.controllers.ShoppingCartController.ShoppingItem;
import java.util.function.BiConsumer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

/**
 *
 * @author KHOUBEIB
 */
public class SpinnerTableCell<S, T extends Integer> extends TableCell<S, T> {

    private final Spinner<T> spinner;
   

    public SpinnerTableCell(int stock, BiConsumer<S, T> update, int step) {

        
        this.spinner = new Spinner<>(0, stock, step);
        this.spinner.setEditable(true);

        this.spinner.valueProperty().addListener((observable, oldValue, newValue)
                -> update.accept(getTableView().getItems().get(getIndex()), newValue)
        );
        
  
        
        
        
      
    }

    @Override
    protected void updateItem(T c, boolean empty) {
        super.updateItem(c, empty);

        if (empty || c == null) {
            setText(null);
            setGraphic(null);
            return;
        }

        this.spinner.getValueFactory().setValue(c);
        System.out.println(c);

        setGraphic(spinner);
    }

}
