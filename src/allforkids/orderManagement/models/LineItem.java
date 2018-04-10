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
public class LineItem extends Model {

 

    @Override
    public String getTableName() {
        return "`line_item`";
    }

    @Override
    public String getPrimaryKeyName() {
        return "line_item_id";
    }

    public Product product() throws ModelException {
        return this.hasOne(Product.class);
    }
    
    public ShoppingCart cart() throws ModelException{
        return this.belongsTo(ShoppingCart.class);
        
    }
    
    

}
