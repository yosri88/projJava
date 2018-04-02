/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.Model;
import java.sql.Date;

/**
 *
 * @author KHOUBEIB
 */
public class Event extends Model{

    private int EventId;
    private int OrderId; // foreign key class Order
    private Date date;
    private String comment;
    private String description;

    @Override
    public String getTableName() {
        return "event";
    }

    @Override
    public String getPrimaryKeyName() {
        return "eventid";
    }
}

