/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;
 import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import java.util.ArrayList;

/**
 *
 * @author KHOUBEIB
 */
public class Order extends Model{
    
    private int id;
    private String order_reference;
    private int order_status;
    private int customer_id;
    private int payment_status;

    public Order() {
    }

    public Order(String order_reference, int order_status, int customer_id, int payment_status) {
        this.order_reference = order_reference;
        this.order_status = order_status;
        this.customer_id  = customer_id;
        this.payment_status = payment_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_reference() {
        return order_reference;
    }

    public void setOrder_reference(String order_reference) {
        this.order_reference = order_reference;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }
    
    

    @Override
    public String getTableName() {
        return "`order`";
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

   

       public ArrayList<LineItem> lineItems() throws ModelException{
        return this.hasMany(LineItem.class);
    }
    
      public User customer() throws ModelException{
          return this.belongsTo(User.class);
      }
       
      
      
}
