/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.controllers;

import allforkids.orderManagement.models.*;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import java.net.URL;
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
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author KHOUBEIB
 */
public class ShoppingCartController implements Initializable {

    @FXML
    private TableView<ShoppingItem> itemsTable;
    @FXML
    private TableColumn<ShoppingItem, ImageView> imageItem;
    @FXML
    private TableColumn<ShoppingItem, String> descriptionAndPrice;
    @FXML
    private TableColumn<ShoppingItem, Integer> quantity;
    @FXML
    private TableColumn<ShoppingItem, Double> total;

    ObservableList<ShoppingItem> itemList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User u = null;
        try {
            u = Model.find(User.class, 2);

            // TODO
        } catch (ModelException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(u);
        try {
            Order o = u.getUserShoppingCart();
            System.out.println(o);
            System.out.println(itemList.stream().count());
            for (LineItem i : o.lineItems()) {
               ImageView image =  new ImageView("file:D:/Esprit/Projets/Java Web/AllForKids(kbach)/src/allforkids/orderManagement/views/image/" + i.product().getAttr("image_link") + ".jpg");
               image.setFitHeight(100);
               image.setFitWidth(100);
               image.setPreserveRatio(true);

                itemList.add(new ShoppingItem(
                        image,
                        //    (String) i.product().getAttr("image"),
                        (String) i.product().getAttr("product_name") + " " + i.product().getAttr("product_price"),
                        (Integer) i.getAttr("quantity"),
                        (Double) i.getAttr("total"))
                );
                System.out.println(itemList.stream().count());
            }

        } catch (ModelException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageItem.setCellValueFactory(new PropertyValueFactory<>("image"));
        descriptionAndPrice.setCellValueFactory(new PropertyValueFactory<>("descriptionPice"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));

        itemsTable.setItems(itemList);
        System.out.println("hi hello ============================================");
    }

    public class ShoppingItem {

        private final ImageView image;
        private final String descriptionPice;
        private final int quantity;
        private final Double total;

        public ShoppingItem(ImageView image, String descriptionPice, int quantity, Double total) {
            this.image = image;
            this.descriptionPice = descriptionPice;
            this.quantity = quantity;
            this.total = total;
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

        public Double getTotal() {
            return total;
        }

    }
}
