/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.userManagement.models;

import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;

/**
 *
 * @author Wassim
 */
public class User extends Model{

    public static User getByUsername(String username) throws ModelException, UnsupportedDataTypeException {
        try {
            return (User)Model.fetch(User.class).all().where("username", username).execute().get(0);
        } catch(IndexOutOfBoundsException ex) {
            return null;
        }
    }
}
