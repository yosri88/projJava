/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.store;

import allforkids.store.models.Product;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpers.CustomImageViewPane;
import helpers.NavigationService;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class ProductDetailsController implements Initializable {

    @FXML
    private AnchorPane avatarContainer;
    @FXML
    private FontAwesomeIconView cartIcon;
    @FXML
    private Label cartLabel;
    @FXML
    private AnchorPane imageContainer;
    @FXML
    private WebView longDescription;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private FontAwesomeIconView checkIcon;
    @FXML
    private Label referenceLabel;
    @FXML
    private Text shortDescription;
    @FXML
    private Spinner<Integer> quantitySpinner;
    
    private Product product;
    @FXML
    private Label totalLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Cart Button
        cartIcon.setIcon(FontAwesomeIcon.SHOPPING_CART);
        cartLabel.setText("Cart (0)");
 
        checkIcon.setIcon(FontAwesomeIcon.CHECK_CIRCLE_ALT);
      
        SpinnerValueFactory<Integer> valueFactory =  new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        quantitySpinner.setValueFactory(valueFactory);
        
        quantitySpinner.valueProperty().addListener(
                (ObservableValue<? extends Integer> observable,Integer oldValue, Integer newValue) -> {
                    this.quantityUpdated(newValue);
            }
        );
    }    

    public void setProduct(Product product) {
        this.product = product;
        Double price = (Double)product.getAttr("unit_price");
        this.priceLabel.setText(price.toString() + " HT");
        this.totalLabel.setText(price.toString() + " HT");
        this.productNameLabel.setText((String)product.getAttr("name"));
        this.referenceLabel.setText((String)product.getAttr("reference"));
        String imagePath = (String) product.getAttr("image");
        if(imagePath != null) {
            System.out.println(imagePath);
            String absolutePath =  Paths.get("").toAbsolutePath().toString();
            imagePath = "file:" + absolutePath + "/" + imagePath;
            double imageWidth = imageContainer.getPrefWidth();
            double imageHeight = imageContainer.getPrefHeight();
            imageContainer.getChildren().add(new CustomImageViewPane(imagePath, imageWidth, imageHeight));
        }
        this.shortDescription.setText((String) product.getAttr("short_description"));
        final WebEngine webEngine = this.longDescription.getEngine();
        webEngine.loadContent((String) product.getAttr("description") + "<style> body{ background-color: #f4f4f4}</style>");
        
    }
    @FXML
    private void goToCart(ActionEvent event) {
        
    }

    @FXML
    private void goToStore(ActionEvent event) {
        NavigationService.goTo(event, this, "/allforkids/store/ProductsList.fxml");
    }

    @FXML
    private void addToCart(ActionEvent event) {
        
    }
    
    
    private void quantityUpdated(Integer quantity) {
        this.totalLabel.setText(((Double)this.product.getAttr("unit_price") * quantity) + " HT");
    }
}
