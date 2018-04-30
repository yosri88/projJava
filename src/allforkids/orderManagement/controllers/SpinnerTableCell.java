/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.controllers;

import allforkids.orderManagement.controllers.ShoppingCartController.ShoppingItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;

/**
 *
 * @author KHOUBEIB
 */
public class SpinnerTableCell<S, T extends Number> extends TableCell<S, T> {

    private final Spinner<T> spinner;

    public SpinnerTableCell() {
        this(1);
    }

    public SpinnerTableCell(int step) {

        this.spinner = new Spinner<>(0, 100, step);
        this.spinner.valueProperty().addListener((observable, oldValue, newValue) -> commitEdit(newValue));
        this.spinner.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                getTableView().edit(getIndex(), getTableColumn());
            }
        });

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

        setGraphic(spinner);
    }
}
