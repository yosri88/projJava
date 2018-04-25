/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import java.util.ArrayList;

/**
 *
 * @author KHOUBEIB
 */
public class Order extends Model {

    public enum OrderStatus {
        PENDING("Pending"), AWAITINGPAYMENT("Awaiting Payment"), AWAINTINGSHIPMENT("Awaiting Shipment"),
        COMPLETED("Completed"), SHIPPED("Shipped"), CANCELLED("Cancelled"), DECLINED("Declined"), REFUNDED("Refunded"),
        DISPUTED("Disputed"), VERIFICATIONREQUIRED("Verification Required");

        private String statusName;

        OrderStatus(String statusName) {
            this.statusName = statusName;
        }

        public String statusName() {
            return statusName;
        }
    };
    
    public enum ShippingMethod {
        PICKUPINPLACE("Pick up in place"), CHRONOPOST("Chrono Post");

        private String methodName;

        ShippingMethod(String methodName) {
            this.methodName = methodName;
        }

        public String methodName() {
            return methodName;
        }
    };

    @Override
    public String getTableName() {
        return "`order`";
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    public ArrayList<LineItem> lineItems() throws ModelException {

        return this.hasMany(LineItem.class);
    }

    public ArrayList<Payment> payments() throws ModelException {
        return this.hasMany(Payment.class);
    }
    
    public User customer() throws ModelException{
        return this.hasOne(User.class);
    }

    boolean isShoppingCart() {

        boolean flag = true;
        if (!this.getAttr("shopping_cart").toString().matches("1")) {
            flag = false;
        }

        return flag;
    }

    public ShoppingCart castOrderToShoppingCart() {

        ShoppingCart cart = new ShoppingCart();
        if (this.isShoppingCart()) {

            cart = (ShoppingCart) this;
            return cart;
        }
        return cart;
    }

    public void addItemToShoppingCart(Product p) throws ModelException, UnsupportedDataTypeException {

        boolean newItem = true;
        LineItem thisLine = null;

        for (LineItem lineItem : this.lineItems()) {

            if (lineItem.product().getAttr("id") == p.getAttr("id")) {
                System.out.println("========= It's NOT a new Item =============");
                newItem = false;
                lineItem.incrementQuantity();
                thisLine = lineItem;
            }
        }

        if (newItem) {
            System.out.println("========= It's a new Item =============");
            LineItem lineItem = new LineItem();
            lineItem.setAttr("product_id", p.getAttr("id"));
            lineItem.setAttr("order_id", this.getAttr("id"));
            lineItem.setAttr("quantity", 1);
            thisLine = lineItem;

        }

        thisLine.save();

    }

    public int getNumberOfLineItem() throws ModelException {

        int numberOfLineItem = 0;

        numberOfLineItem = (int) this.lineItems().stream().count();

        return numberOfLineItem;
    }

    public int getNumberOfAllItems() throws ModelException {

        int numberOfItems = 0;

        for (LineItem scItem : this.lineItems()) {

            numberOfItems += scItem.getQuantity();
        }

        return numberOfItems;
    }

    public void confirmOrder() {

        if (this.isShoppingCart()) {
            this.setAttr("shopping_cart", 0);
        }
    }

    public void setOrderStatus(OrderStatus status) {
        this.setAttr("order_status", status.statusName());
    }

    public String getOrderStatus() {
        return (String) this.getAttr("order_status");
    }
    
        public void setShippingMethod(ShippingMethod method) {
        this.setAttr("order_status", method.methodName());
    }

    public String getShippingMethod() {
        return (String) this.getAttr("shipping_method");
    }
    
    public Double getOrderTotalVat() throws ModelException{
        double vat = 0 ;
        
        for (LineItem l : this.lineItems()){            
          double inter = (double) l.getAttr("vat_total") ;
            vat = inter + vat ;
        }
                
        return vat;
    }

    public Double getOrderTotalWithVAT() throws ModelException{
        double tot = 0 ;
        
        for (LineItem l : this.lineItems()){            
          double inter = (double) l.getAttr("total") ;
            tot = inter + tot ;
        }
                
        return tot;
        
    }
    
    public Double getOrderTotalWithoutVAT() throws ModelException{
                double sub  = 0 ;
        
        for (LineItem l : this.lineItems()){            
          double inter = (double) l.getAttr("sub_total") ;
            sub = inter + sub ;
        }
                
        return sub;
    }
}
