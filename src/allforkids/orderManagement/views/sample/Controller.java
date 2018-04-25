/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.views.sample;

//import allforkids.orderManagement.controllers.OrderTableController;
import allforkids.orderManagement.models.Order;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author KHOUBEIB
 */
public class Controller implements Initializable {

    @FXML
    private BorderPane borderpane;

    @FXML
    private JFXTreeTableView<OrderView> treeView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JFXTreeTableColumn<OrderView, String> referenceCol = new JFXTreeTableColumn<>("Reference");
        referenceCol.setPrefWidth(150);
        referenceCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderView, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderView, String> param) {
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                return param.getValue().getValue().reference;
            }
        });
        JFXTreeTableColumn<OrderView, String> customerCol = new JFXTreeTableColumn<>("customer");
        customerCol.setPrefWidth(150);
        customerCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderView, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderView, String> param) {
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                return param.getValue().getValue().customer;
            }
        });

        JFXTreeTableColumn<OrderView, String> statusCol = new JFXTreeTableColumn<>("status");
        statusCol.setPrefWidth(150);
        statusCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderView, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderView, String> param) {
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                return param.getValue().getValue().status;
            }
        });
        JFXTreeTableColumn<OrderView, String> shippingMethodCol = new JFXTreeTableColumn<>("shipping Method");
        shippingMethodCol.setPrefWidth(150);
        shippingMethodCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderView, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderView, String> param) {
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                return param.getValue().getValue().shippingMethod;
            }
        });
        JFXTreeTableColumn<OrderView, String> creationDateCol = new JFXTreeTableColumn<>("creation Date");
        creationDateCol.setPrefWidth(150);
        creationDateCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderView, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderView, String> param) {
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                return param.getValue().getValue().creationDate;
            }
        });
        JFXTreeTableColumn<OrderView, String> amountCol = new JFXTreeTableColumn<>("amount ");
        amountCol.setPrefWidth(150);
        amountCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrderView, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<OrderView, String> param) {
                //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                return param.getValue().getValue().amount;
            }
        });

        ObservableList<OrderView> orders = FXCollections.observableArrayList();

        try {
            ResultSet rs
                    = //                    Order.sqlQuery("SELECT order_reference, "
                    //                    + "order_status, "
                    //                    + "customer_id, "
                    //                    + "payment_status"
                    //                    + "  FROM `order`");
                    //            
                    Order.sqlQuery("SELECT CONCAT(u.firstname,\" \",u.lastname) as \"customer\" , sh.method as \"shippingMethod\","
                            + " sc.total as \"amount\", "
                            + "od.order_reference as \"reference\", os.`status` \n"
                            + ", od.creation_date as \"creationDate\"\n"
                            + "FROM user u , shopping_cart sc, \n"
                            + "shipping_method sh, `order` od , order_status os\n"
                            + "WHERE\n"
                            + "u.id = sc.fk_user_id and od.customer_id = u.id "
                            + "and od.fk_shipping_method_id = sh.id and os.id = od.order_status;");

            while (rs.next()) {

                System.out.println(rs.getString("amount"));

                orders.add(new OrderView(
                        rs.getString("reference"),
                        rs.getString("customer"),
                        rs.getString("status"),
                        rs.getString("shippingMethod"),
                        rs.getString("creationDate"),
                        rs.getString("amount")
                ));
            }

        } catch (UnsupportedDataTypeException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModelException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<OrderView> root = new RecursiveTreeItem<OrderView>(orders, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(referenceCol, customerCol, statusCol, shippingMethodCol, creationDateCol, amountCol);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }

    class OrderView extends RecursiveTreeObject<OrderView> {

        StringProperty reference;
        StringProperty customer;
        StringProperty status;
        StringProperty shippingMethod;
        StringProperty creationDate;
        StringProperty amount;

        public OrderView(String reference, String customer, String status, String shippingMethod, String creationDate, String amount) {
            this.reference = new SimpleStringProperty(reference);
            this.customer = new SimpleStringProperty(customer);
            this.status = new SimpleStringProperty(status);
            this.shippingMethod = new SimpleStringProperty(shippingMethod);
            this.creationDate = new SimpleStringProperty(creationDate);
            this.amount = new SimpleStringProperty(amount);

        }

        public StringProperty getCustomer() {
            return customer;
        }

        public StringProperty getStatus() {
            return status;
        }

        public StringProperty getShippingMethod() {
            return shippingMethod;
        }

        public StringProperty getCreationDate() {
            return creationDate;
        }

        public StringProperty getAmount() {
            return amount;
        }
    }
}
