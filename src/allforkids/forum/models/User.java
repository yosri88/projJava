/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.forum.models;

import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import java.util.ArrayList;

/**
 *
 * @author Wassim
 */
public class User extends Model{
    public ArrayList<Post> posts() throws ModelException {
        return this.hasMany(Post.class);
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return this.getAttr("id").equals(user.getAttr("id"));
    }
}
