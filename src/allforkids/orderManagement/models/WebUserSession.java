/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.Model;

/**
 *
 * @author KHOUBEIB
 */
public class WebUserSession extends Model{
    
    private int wusId;

    public WebUserSession(int wusId) {
        this.wusId = wusId;
    }

    public WebUserSession() {
    }

    public int getWusId() {
        return wusId;
    }

    public void setWusId(int wusId) {
        this.wusId = wusId;
    }
    
    
}
