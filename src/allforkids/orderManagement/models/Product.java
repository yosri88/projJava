/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author KHOUBEIB
 */
public class Product extends Model {

//    public Product() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public String getTableName() {
        return "`product`";
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    public LineItem lineItem() throws ModelException {
        return this.belongsTo(LineItem.class);
    }

    private Integer id;
    private String name;
    private BigDecimal price;
    private Date lastUpdate;

    public Integer getId() {
        return id;
    }
//
//    public String getName() {
//        return name;
//    }
//

    public Double getPrice() {
        return (Double) this.getAttr("product_price");
    }

    public Double vatRate() {
        return (Double) this.getAttr("vat_rate");
    }

    public Double vat() {
        return (this.getPrice() * this.vatRate() / 100);
    }

    public Double getVatPrice() {
        return this.getPrice() + this.vat();
    }
//
//    public Date getLastUpdate() {
//        return lastUpdate;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public void setLastUpdate(Date lastUpdate) {
//        this.lastUpdate = lastUpdate;
//    }
//    
//    
//    
//
//    public Product(Integer id, String name, BigDecimal price, Date lastUpdate) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.lastUpdate = lastUpdate;
//    }
//    
//    
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//    
//        @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Product)) {
//            return false;
//        }
//        Product other = (Product) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//    
//        @Override
//    public String toString() {
//        return "entity.Product[id=" + id + "]";
//    }

}
