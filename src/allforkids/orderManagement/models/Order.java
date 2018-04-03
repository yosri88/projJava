/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.*;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author KHOUBEIB
 */
public class Order extends Model {

    private int OrderId;
    private Date OrderDate;
    private ArrayList<OrderLine> orderLines;

    @Override
    public String getTableName() {
        return "order";
    }

    @Override
    public String getPrimaryKeyName() {
        return "orderid";
    }

}
