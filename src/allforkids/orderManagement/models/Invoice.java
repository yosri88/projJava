/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.*;

/**
 *
 * @author KHOUBEIB
 */
public class Invoice extends Model {

    private int InvoiceId;
    private String InvoiceRef;

    @Override
    public String getTableName() {
        return "invoice";
    }

    @Override
    public String getPrimaryKeyName() {
        return "invoiceid";
    }

}
