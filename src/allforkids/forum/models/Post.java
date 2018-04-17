/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.forum.models;

import dopsie.core.Model;
import dopsie.exceptions.ModelException;

/**
 *
 * @author Wassim
 */
public class Post extends Model{    
    
    public User author() throws ModelException {
        return this.hasOne(User.class);
    }
    
    public Thread thread() throws ModelException {
        return this.belongsTo(Thread.class);
    }
}
