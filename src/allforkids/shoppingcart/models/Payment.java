/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.shoppingcart.models;

import dopsie.core.*;
/**
 *
 * @author KHOUBEIB
 */
public class Payment extends Model {

    @Override
    public String getTableName() {
        return "payment";
    }

    @Override
    public String getPrimaryKeyName() {
        return "paymentid";
    }
}
