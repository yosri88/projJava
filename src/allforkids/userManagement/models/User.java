/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.userManagement.models;

import allforkids.forum.models.Post;
import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
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
        allforkids.userManagement.models.User user = (allforkids.userManagement.models.User) obj;
        return this.getAttr("id").equals(user.getAttr("id"));
    }
    
    
    public static User getByUsername(String username) throws ModelException, UnsupportedDataTypeException {
        try {
            return (User)Model.fetch(User.class).all().where("username", username).execute().get(0);
        } catch(IndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public Role getRole() {
        switch((int) getAttr("role")){
            case 0:
                return Role.USER;
            case 1:
                return Role.ANALYST;
            case 2:
                return Role.COMMUNITY_MANAGER;
            case 3:
                return Role.ADMIN;
                
        }
        return Role.USER;
    }
    
    
    public void setRole(Role role) {
        if(null != role) switch (role) {
            case USER:
                this.setAttr("role", 0);
                break;
            case ANALYST:
                this.setAttr("role", 1);
                break;
            case COMMUNITY_MANAGER:
                this.setAttr("role", 2);
                break;
            case ADMIN:
                this.setAttr("role", 3);
                break;
            default:
                break;
        }
    }
}
