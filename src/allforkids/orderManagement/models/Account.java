/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;



import dopsie.core.*;
import dopsie.exceptions.ModelException;

/**
 *
 * @author KHOUBEIB
 */
public class Account extends Model{

    
    @Override
    public String getTableName() {
        return "account";
    }

    @Override
    public String getPrimaryKeyName() {
        return "accountid";
    }
    
    public  Address address() throws ModelException{
        return this.hasOne(Address.class);
    }
    
    
    
    
}
