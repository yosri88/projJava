/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.store;

import allforkids.store.models.Category;
import allforkids.store.models.Product;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import helpers.DopsieCellBuilder;
import helpers.NavigationService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class ProductsListController implements Initializable {

    @FXML
    private AnchorPane avatarContainer;
    @FXML
    private FontAwesomeIconView cartIcon;
    @FXML
    private Label cartLabel;
    @FXML
    private FlowPane productsFlowPane;
    @FXML
    private VBox categoriesVbox;
    @FXML
    private TableView<Category> categoriesTableView;
    @FXML
    private TableColumn<Category, String> categoriesCol;
    
    private ObservableList obs = null;
    private ArrayList<Product> allProducts;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<Category> categories = new ArrayList<>();
        
        try {
            categories = Model.fetch(Category.class).all().execute();
            allProducts = Model.fetch(Product.class).all().execute();
        } catch (ModelException | UnsupportedDataTypeException ex) {
            Logger.getLogger(ProductsListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        showProducts(allProducts);
        
        obs = FXCollections.observableArrayList(categories);
        categoriesCol.setCellValueFactory(new DopsieCellBuilder(p -> {
            return ((Category)p).getAttr("name");
        }));
        categoriesTableView.setItems(obs);
        
        // Remove the header from the categories tableview
        categoriesTableView.skinProperty().addListener((a, b, newSkin) -> {
            TableHeaderRow headerRow = ((TableViewSkinBase) newSkin).getTableHeaderRow();
            headerRow.setMaxHeight(0);
            headerRow.setPrefHeight(0);
            headerRow.setMinHeight(0);
        });
        
        // Cart Button
        cartIcon.setIcon(FontAwesomeIcon.SHOPPING_CART);
        cartLabel.setText("Cart (0)");
    }    

    private void showProducts(ArrayList<Product> productsToShow) {
        productsFlowPane.getChildren().removeAll(productsFlowPane.getChildren());
        if(productsToShow.isEmpty()) {
            Label newLabel = new Label("No Product in here");
            newLabel.getStyleClass().add("no-product-message");
            productsFlowPane.getChildren().add(newLabel);
            return;
        }
        try {
            for(Product product: productsToShow) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
                Pane newLoadedPane = loader.load();
                ProductCardController controller = loader.<ProductCardController>getController();
                controller.setProduct(product);
                productsFlowPane.getChildren().add(newLoadedPane);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductsListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void goToCart(ActionEvent event) {
        
    }

    @FXML
    private void goToMainMenu(ActionEvent event) {
        NavigationService.goTo(event, this, "/allforkids/welcome/Welcome.fxml");
    }

    @FXML
    private void selectCategory(MouseEvent event) {
        Category cat = categoriesTableView.getSelectionModel().getSelectedItem();
        ArrayList<Product> productsToShow = new ArrayList<>();
        for(Product product: allProducts) {
            if(product.getAttr("category_id").equals(cat.getAttr("id"))) {
                productsToShow.add(product);
            }
        }
        showProducts(productsToShow);
    }
    
}