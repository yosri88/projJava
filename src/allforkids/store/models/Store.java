/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.store.models;

import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import java.util.ArrayList;
/**
 *
 * @author Wassim
 */
public class Store extends Model{
    
    public ArrayList<Address> locations() throws ModelException {
        return this.hasMany(Address.class);
    }
}
