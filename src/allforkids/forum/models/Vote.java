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
public class Vote extends Model{
    
    public User voter() throws ModelException {
        return this.hasOne(User.class);
    }
    
    public Post post() throws ModelException {
        return this.hasOne(Post.class);
    }
    
    public VoteType getType() {
        return this.getAttr("type").equals(-1) ? VoteType.DOWN : VoteType.UP;
    }
}




