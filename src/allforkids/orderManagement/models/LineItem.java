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

    private int ItemId;
    private int ProductId; // fk
    private String ProductName; //fk
    private float Price; // fk
    private int qty;
    private float ProductVAT;//fk
    private float subtotal;
    private float total;
    private float totalVAT;

    @Override
    public String getTableName() {
        return "lineitem";
    }

    @Override
    public String getPrimaryKeyName() {
        return "lineitemid";
    }

}
