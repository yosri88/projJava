/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.*;
import dopsie.exceptions.ModelException;
import java.util.*;

/**
 *
 * @author KHOUBEIB
 */
public class ShoppingCart extends Order {

    List<LineItem> items;
    int numberOfItems;
    double total;

    public ShoppingCart() {

        items = new ArrayList<LineItem>();
        Order order = new Order();
        numberOfItems = 0;
        total = 0;
    }



//    public  void addItem(Product p, Order o) throws ModelException {
//
//        
//        boolean newItem = true;
//
//        for (LineItem scItem : o.lineItems()) {
//
//            if (scItem.product().getAttr("id") == p.getAttr("id")) {
//
//                newItem = false;
//                scItem.incrementQuantity();
//            }
//        }
//
//        if (newItem) {
//           
//            LineItem scItem = new LineItem();
//           scItem.setAttr("product_id", p.getAttr("id"));
//           scItem.setAttr("order_id", o.getAttr("id"));
//           scItem.setAttr("quantity", 1);
//            
//         
//            items.add(scItem);
//            scItem.save();
//        }
//        
//    }
//    public  void update(Product product, String quantity) throws ModelException {
//
//        short qty = -1;
//
//        // cast quantity as short
//        qty = Short.parseShort(quantity);
//
//        if (qty >= 0) {
//
//            LineItem item = null;
//
//            for (LineItem scItem : items) {
//
//                if (scItem.product().getId() == product.getId()) {
//
//                    if (qty != 0) {
//                        // set item quantity to new value
//                        scItem.setQuantity(qty);
//                    } else {
//                        // if quantity equals 0, save item and break
//                        item = scItem;
//                        break;
//                    }
//                }
//            }
//
//            if (item != null) {
//                // remove from cart
//                items.remove(item);
//            }
//        }
//    }
//
//    public  List<LineItem> getItems() {
//
//        return items;
//    }
//
//    public  int getNumberOfItems() {
//
//        numberOfItems = 0;
//
//        for (LineItem scItem : items) {
//
//            numberOfItems += scItem.getQuantity();
//        }
//
//        return numberOfItems;
//    }
//
//    public synchronized double getSubtotal() throws ModelException {
//
//        double amount = 0;
//
//        for (LineItem scItem : items) {
//
//            Product product = (Product) scItem.product();
//            amount += (scItem.getQuantity() * product.getPrice().doubleValue());
//        }
//
//        return amount;
//    }
//
//    public  void calculateTotal(String surcharge) throws ModelException {
//
//        double amount = 0;
//
//        // cast surcharge as double
//        double s = Double.parseDouble(surcharge);
//
//        amount = this.getSubtotal();
//        amount += s;
//
//        total = amount;
//    }
//
//    public  double getTotal() {
//
//        return total;
//    }
//
//    public  void clear() {
//        items.clear();
//        numberOfItems = 0;
//        total = 0;
//    }
}
