/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import static allforkids.orderManagement.models.Helpers.currencyFormatter;
import dopsie.core.Model;
import dopsie.dataTypes.Date;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author KHOUBEIB
 */
public class Order extends Model {

    public enum OrderStatus {
        SHOPPINGCART("Shopping Cart"),
        PENDING("Pending"), 
        AWAITINGPAYMENT("Awaiting Payment"), 
        AWAINTINGSHIPMENT("Awaiting Shipment"),
        COMPLETED("Completed"), 
        SHIPPED("Shipped"), 
        CANCELLED("Cancelled"), 
        DECLINED("Declined"), 
        REFUNDED("Refunded"),
        DISPUTED("Disputed"), 
        VERIFICATIONREQUIRED("Verification Required");

        private String statusName;
   

        OrderStatus(String statusName) {
            this.statusName = statusName;
         
        }

        public String statusName() {
            return statusName;
        }
        
        public int statusNumber(){
          return  this.ordinal();
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

    public User customer() throws ModelException {
        return this.hasOne(User.class);
    }
    
    public ShippingMethod shippingMethod() throws ModelException {
        return this.hasOne(ShippingMethod.class);
    }
    
    public Address deliveryAddress() throws ModelException{
        return this.hasOne(Address.class);
    }
    
    
    

    boolean isShoppingCart() {

        boolean flag = true;
        if (!this.getAttr("shopping_cart").toString().matches("0")) {
            flag = false;
        }

        return flag;
    }

    public ShoppingCart castOrderToShoppingCart() {

        ShoppingCart cart = new ShoppingCart();
        if (this.isShoppingCart()) {

            cart = (ShoppingCart) this;
            
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

    public void setOrderStatusByName(String status) {
        
        int index = OrderStatus.valueOf(status).ordinal();
        this.setAttr("order_status", index);
    }
    
    public void setOrderStatusByIndex(int index) {
        if (index < OrderStatus.values().length){
            this.setAttr("order_status", index);
        }else
        {
            System.out.println("out of range");
        }
        
    }

    public String getOrderStatusName() {
        int index = (int) this.getAttr("order_status");
        return OrderStatus.values()[index].statusName;
    }
    
    public  int getOrderStatusIndex(){
        return (int) this.getAttr("order_status");
    }
    


    public Double getOrderTotalVat() throws ModelException {
        double vat = 0;

        for (LineItem l : this.lineItems()) {
            double inter = (double) l.getAttr("vat_total");
            vat = inter + vat;
        }

        return vat;
    }

    public Double getOrderTotalWithVAT() throws ModelException {
        Double tot = currencyFormatter(0.0);

        for (LineItem l : this.lineItems()) {
            Double inter = (Double) l.getAttr("total");
            tot = inter + tot;
        }

        return Helpers.currencyFormatter(tot);

    }

    public Double getOrderTotalWithoutVAT() throws ModelException {
        double sub = 0;

        for (LineItem l : this.lineItems()) {
            double inter = (double) l.getAttr("sub_total");
            sub = inter + sub;
        }

        return sub;
    }

    // Payment pocess
    public Double paymentAction(Double amount, String method) throws ModelException, UnsupportedDataTypeException {
        Double rest = 0.0;
        rest = Helpers.currencyFormatter(rest);
        amount = Helpers.currencyFormatter(amount);
        Double alreadyPaid = this.getTotalPayment();
        Double orderTotalAmount = this.getOrderTotalWithVAT();

        if ((alreadyPaid < orderTotalAmount) && (amount <= (orderTotalAmount - alreadyPaid))) {

            Payment p = new Payment();
            p.setAttr("order_id", this.getAttr("id"));
            p.setAttr("amount", amount);
            p.setAttr("method", method);
            p.setAttr("date", new Date());
            p.save();
            
            //update alreadyPaid
            alreadyPaid =+ amount;
        }
        else {
            System.out.println("error in payment");
        }

        this.updateOrderStatus();
        rest = orderTotalAmount - alreadyPaid;
       
        return rest;
    }

    public Double getTotalPayment() throws ModelException {

        Double tot = 0.00;
        for (Payment p : this.payments()) {
            //System.out.println(p);
            tot += p.getPaymentAmount();
           
        }
      
        return Helpers.currencyFormatter(tot);
    }
    
    public void updateOrderStatus() throws ModelException, UnsupportedDataTypeException{
        if (this.getTotalPayment() - this.getOrderTotalWithVAT() == 0){
             this.setAttr("order_status", OrderStatus.AWAINTINGSHIPMENT.name());
             
        }else{
            this.setAttr("order_status", OrderStatus.PENDING.name());
            //System.out.println("nothing to update");
        }
        
        this.save();
    }

}
