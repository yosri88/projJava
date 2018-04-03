/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.*;
import dopsie.exceptions.ModelException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author KHOUBEIB
 */
public class Order extends Model {

    private int orderId;
    private Date orderDate;
    private ArrayList<OrderLine> orderLines;

    @Override
    public String getTableName() {
        return "order";
    }

    @Override
    public String getPrimaryKeyName() {
        return "orderid";
    }

   

       public ArrayList<OrderLine> orderLines() throws ModelException{
        return this.hasMany(OrderLine.class);
    }
    
       
       
       
       

    public Order(int orderId, Date orderDate, ArrayList<OrderLine> orderLines) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderLines = orderLines;
    }
    
    
    

    public Order() {
    }

    public Order(int orderId) {
        this.orderId = orderId;
    }

}
