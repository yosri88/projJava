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
public class OrderLine extends Model{
    
        @Override
    public String getTableName() {
        return "orderline";
    }

    @Override
    public String getPrimaryKeyName() {
        return "orderlineid";
    }

   

       public LineItem orderLine() throws ModelException{
        return this.hasOne(LineItem.class);
    }
    
    
}
