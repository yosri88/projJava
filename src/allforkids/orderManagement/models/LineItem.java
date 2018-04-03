/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.Model;

/**
 *
 * @author KHOUBEIB
 */
public class LineItem extends Model {

    private int itemId;
    private int productId; // fk
    private String productName; //fk
    private double unitPrice; // fk
    private int qty;
    private float productVAT;//fk
    private float subtotal;
    private float total;
    private float totalVAT;
    private int lineNo;
    
    @Override
    public String getTableName() {
        return "lineitem";
    }

    @Override
    public String getPrimaryKeyName() {
        return "lineitemid";
    }

}
