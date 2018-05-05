/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.*;
import dopsie.exceptions.ModelException;
import java.util.ArrayList;
import allforkids.orderManagement.models.Order;
import dopsie.exceptions.UnsupportedDataTypeException;

import java.util.HashMap;
import java.util.logging.Logger;
//import sun.security.mscapi.RSASignature.MD5;

/**
 *
 * @author KHOUBEIB
 */
public class User extends Model {

//    private int customerId;
//    private String firstName;
//    private String lastName;
//    private int age;
//    private String gender;
//    private Address address;
//    private String username;
//    private String password;
//	private String isAdmin;
    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    public ArrayList<Order> orders() throws ModelException {
        return this.hasMany(Order.class);
    }

    public Order getUserShoppingCart() throws ModelException, UnsupportedDataTypeException {
        Order UserShoppingCart = null;
        boolean newCustomer = true;
        for (Order o : this.orders()) {

            if ((int) o.getAttr("order_status") == 0) {
                UserShoppingCart = o;
                newCustomer = false;
            }
        }

        if (newCustomer) {
            UserShoppingCart = new Order();
            UserShoppingCart.setAttr("user_id", this.getAttr("id"));
            UserShoppingCart.setAttr("order_status", 0);
            UserShoppingCart.save();
        }

        return UserShoppingCart;

    }

    public ArrayList<Address> address() throws ModelException {

        return this.hasMany(Address.class);
    }

//    public User(int CustomerId, String firstName, String last_Name, int age, String gender, Address address, String username, String password) {
//        this.customerId = CustomerId;
//        this.firstName = firstName;
//        this.lastName = last_Name;
//        this.age = age;
//        this.gender = gender;
//        this.address = address;
//        this.username = username;
//        this.password = password;
//    }
//
//    public User(String firstName, String last_Name, int age, String gender, Address address, String username, String password) {
//        this.firstName = firstName;
//        this.lastName = last_Name;
//        this.age = age;
//        this.gender = gender;
//        this.address = address;
//        this.username = username;
//        this.password = password;
//    }
//
//    public User() {
//    }
    //   private static final Logger LOG = Logger.getLogger(User.class.getName());
//    public int getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(int CustomerId) {
//        this.customerId = CustomerId;
//    }
    public String getFirstName() {
        return (String) this.getAttr("firstname");
    }

    public void setFirstName(String firstName) {
        this.setAttr("firstname", firstName);
    }

    public String getLastName() {
        return (String) this.getAttr("lastname");
    }

    public void setLastName(String lastName) {
        this.setAttr("firstname", lastName);
    }

    public int getAge() {
        return (int) this.getAttr("age");
    }

    public void setAge(int age) {
        this.setAttr("age", age);
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
////    public static String getTableName() {
////        return tableName;
////    }
//    public static void setTableName(String tableName) {
//        RelationalModel.tableName = tableName;
//    }
//
//    public boolean isIsNew() {
//        return isNew;
//    }
//
//    public void setIsNew(boolean isNew) {
//        this.isNew = isNew;
//    }
//
//    public Boolean getIsModified() {
//        return isModified;
//    }
//
//    public void setIsModified(Boolean isModified) {
//        this.isModified = isModified;
//    }
//
//    public HashMap<String, Object> getAttributes() {
//        return attributes;
//    }
//
//    public void setAttributes(HashMap<String, Object> attributes) {
//        this.attributes = attributes;
//    }

}
