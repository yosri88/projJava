/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.controllers;

import allforkids.userManagement.models.User;
import allforkids.orderManagement.models.*;
import com.jfoenix.controls.JFXButton;
import com.sun.prism.impl.Disposer.Record;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import helpers.NavigationService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;
import static javax.management.Query.value;
import sun.rmi.runtime.Log;

/**
 * FXML Controller class
 *
 * @author KHOUBEIB
 */
public class ShoppingCartController implements Initializable {

    @FXML
    public TableView<ShoppingItem> itemsTableView;
    @FXML
    private TableColumn<ShoppingItem, ImageView> imageItemColumn;
    @FXML
    private TableColumn<ShoppingItem, String> descriptionAndPriceColumn;
    @FXML
    private TableColumn<ShoppingItem, Integer> quantityColumn;
    @FXML
    private TableColumn<ShoppingItem, Double> totalItemRowColumn;
//    @FXML
//    private TableColumn<ShoppingItem, Button> deleteButtonColumn;

    ObservableList<ShoppingItem> ShoppingitemList = FXCollections.observableArrayList();
    @FXML
    private JFXButton filterButton;
    @FXML
    private Label nbrItem;
    @FXML
    private JFXButton filterButton1;
    @FXML
    private Label totalHT;
    @FXML
    private Label nbrItem1;

    @FXML
    private JFXButton goToStore;
    @FXML
    private Label shippementLabel;
    @FXML
    private Label shippementFee;

    /**
     * Initializes the controller class.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        itemsTableView.setEditable(true);
        totalItemRowColumn.setEditable(true);

        User u = null;
        Order o = null;
        try {

            u = Model.find(User.class, 1);
            System.out.println(u);

            // TODO
        } catch (ModelException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            o = u.getUserShoppingCart();
            System.out.println("current user shopping cart");
            System.out.println(o);
            System.out.println("---------------------------");
            System.out.println("line item" + o.lineItems());

            System.out.println(ShoppingitemList.stream().count());
            String absolutePath = System.getProperty("uploads_folder");
            for (LineItem i : o.lineItems()) {
                String imagePath = "file:" + absolutePath + i.product().getAttr("image") + ".jpg";
                ImageView image = new ImageView(imagePath);
                System.out.println(imagePath);
                image.setFitHeight(100);
                image.setFitWidth(100);
                image.setPreserveRatio(false);

                ShoppingitemList.add(new ShoppingItem(
                        (int) i.getAttr("id"),
                        image,
                        (String) i.product().getAttr("short_description") + " : " + i.product().getAttr("unit_price"),
                        (Integer) i.getAttr("quantity"),
                        i.getAttr("total").toString()
                )
                );

            }

        } catch (ModelException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedDataTypeException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // product item image column
        imageItemColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        imageItemColumn.setPrefWidth(110);

        // produc (description + unit price) column
        descriptionAndPriceColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionPice"));

        // quantity spinner column
        //   quantity.setCellValueFactory(new PropertyValueFactory<ShoppingItem, Spinner>("quantity"));
        quantityColumn.setEditable(true);


        /* Quantity Spinner Column */
        quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        quantityColumn.setCellFactory(tc -> {
            // available stock not yet implemeted 
            int stock = 1000;
            return new SpinnerTableCell<>(stock, ShoppingItem::setQuantity, 1);
        }
        );

        /* Total  Column */
        totalItemRowColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        /*  Delete button column */
        //Insert Button
        TableColumn deleteColumn = new TableColumn<>("Action");
        itemsTableView.getColumns().add(deleteColumn);

        deleteColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        deleteColumn.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell(ShoppingitemList);

            }

        });

        /* fill the itemsTableView with items */
        // adding the shoppingitemList
        itemsTableView.setItems(ShoppingitemList);
        // setting the table view editable
        itemsTableView.setEditable(true);

        ShoppingitemList.addListener((javafx.collections.ListChangeListener.Change<? extends ShoppingItem> pChange) -> {
            while (pChange.next()) {
                System.out.println(pChange.getList());
                itemsTableView.refresh();
                System.out.println("efreshing . . . . . . . . . . . . . ");
            }
        });
    }

    @FXML
    private void goToStore(ActionEvent event) {
        NavigationService.goTo(event, this, "/allforkids/store/ProductsList.fxml");
    }

    public class ShoppingItem {

        private final int id;
        private final ImageView image;
        private final String descriptionPice;
        private int quantity;
        private StringProperty total;
        private Button deleteItem;

        public ShoppingItem(int id, ImageView image, String descriptionPice, int quantity, String total) {
            this.id = id;
            this.image = image;
            this.descriptionPice = descriptionPice;
            this.quantity = quantity;
            this.total = new SimpleStringProperty(total);
            this.deleteItem = new Button("Delete item");
        }

        public int getId() {
            return id;
        }

        public ImageView getImage() {
            return image;
        }

        public String getDescriptionPice() {
            return descriptionPice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int qty) {

            this.quantity = qty;
            System.out.println(this.toString());
            this.updateItemRow();

        }

        public void updateItemRow() {
            String str = getDescriptionPice();
            System.out.println("=== description ==== : " +str);
            Pattern p = Pattern.compile(" : (.*)");
            Matcher m = p.matcher(str);

            if (m.find()) {
                this.setTotal(this.getQuantity(), Double.parseDouble(m.group(1).trim()));
                itemsTableView.refresh();
            }
        }

        public void updateShoppingItem(int id, int qty) throws ModelException, UnsupportedDataTypeException {
            this.setQuantity(qty);
            LineItem i = Model.find(LineItem.class, id);
            Double p = (Double) i.getAttr("product_price");
            System.out.println(this.toString());

        }

        public String getTotal() {
            return total.getValue();
        }

        public void setTotal(int qty, Double unitPrice) {
            this.total = new SimpleStringProperty(Double.toString(qty * unitPrice));
        }

        @Override
        public String toString() {
            return "ShoppingItem " + id + "=" + " " + descriptionPice + "= Qty : " + quantity + " total " + total.getValue();
        }

    }

}
