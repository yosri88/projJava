/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.*;

import java.util.HashMap;
import java.util.logging.Logger;
//import sun.security.mscapi.RSASignature.MD5;

/**
 *
 * @author KHOUBEIB
 */
public class Customer extends Model {

    private int CustomerId;
    private String firstName;
    private String last_Name;
    private int age;
    private String gender;
    private Address address;
    private String username;
    private String password;
//	private String isAdmin;

    @Override
    public String getTableName() {
        return "customer";
    }

    @Override
    public String getPrimaryKeyName() {
        return "customerid";
    }

    public Customer(int CustomerId, String firstName, String last_Name, int age, String gender, Address address, String username, String password) {
        this.CustomerId = CustomerId;
        this.firstName = firstName;
        this.last_Name = last_Name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public Customer(String firstName, String last_Name, int age, String gender, Address address, String username, String password) {
        this.firstName = firstName;
        this.last_Name = last_Name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public Customer() {
    }
    private static final Logger LOG = Logger.getLogger(Customer.class.getName());

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public static String getTableName() {
//        return tableName;
//    }
    public static void setTableName(String tableName) {
        RelationalModel.tableName = tableName;
    }

    public boolean isIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsModified() {
        return isModified;
    }

    public void setIsModified(Boolean isModified) {
        this.isModified = isModified;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }

}
