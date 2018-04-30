/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.store;

import allforkids.store.models.Product;
import com.jfoenix.controls.JFXButton;
import helpers.CustomImageViewPane;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class ProductCardController implements Initializable {

    @FXML
    private AnchorPane imageContainer;
    @FXML
    private JFXButton productName;
    @FXML
    private JFXButton productPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
    public void setProduct(Product product) {
        String prodName = (String) product.getAttr("name");
        Double prodPrice = (Double) product.getAttr("unit_price");
        this.productName.setText(prodName);
        this.productPrice.setText(prodPrice + " HT");
        String imagePath = (String) product.getAttr("image");
        if(imagePath != null) {
            System.out.println(imagePath);
            String absolutePath =  Paths.get("").toAbsolutePath().toString();
            imagePath = "file:" + absolutePath + "/" + imagePath;
            double imageWidth = imageContainer.getPrefWidth();
            double imageHeight = imageContainer.getPrefHeight();
            imageContainer.getChildren().add(new CustomImageViewPane(imagePath, imageWidth, imageHeight));
        }
    }
    
    @FXML
    private void goToProduct(ActionEvent event) {
        
    }
    
}
