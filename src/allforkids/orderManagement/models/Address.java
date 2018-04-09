/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;



import dopsie.core.Model;
import dopsie.exceptions.ModelException;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author KHOUBEIB
 */
public class Address extends Model{
    
    private int AddressId;
    private String City;
    private String Region;
    private String Street;
    private String Phone; 
    private String email;

    @Override
    
    public String getTableName() {
        return "address";
    }
    
    @Override
    public String getPrimaryKeyName() {
        return "addressid";
    }

    public ArrayList<Address> address() throws ModelException{
        try {
            return this.hasMany(Address.class);
        } catch (Exception e) {
            throw new ModelException("Could not find relationship");
        }
    }
    
    
    public Address() {
    }

    public Address(String City, String Region, String Street) {
        this.City = City;
        this.Region = Region;
        this.Street = Street;
    }

    public Address(int AddressId, String City, String Region, String Street) {
        this.AddressId = AddressId;
        this.City = City;
        this.Region = Region;
        this.Street = Street;
    }

    public int getAddressId() {
        return AddressId;
    }

    public void setAddressId(int AddressId) {
        this.AddressId = AddressId;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.AddressId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.City, other.City)) {
            return false;
        }
        if (!Objects.equals(this.Region, other.Region)) {
            return false;
        }
        if (!Objects.equals(this.Street, other.Street)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "AddressId=" + AddressId + ", City=" + City + ", Region=" + Region + ", Street=" + Street + ", Phone=" + Phone + ", email=" + email + '}';
    }
    
    
    
}
