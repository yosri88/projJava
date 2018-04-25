/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import dopsie.core.Model;
import dopsie.exceptions.ModelException;
import dopsie.exceptions.UnsupportedDataTypeException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author KHOUBEIB
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ModelException, UnsupportedDataTypeException {

        System.setProperty("host", "127.0.0.1");
        System.setProperty("port", "3306");
        System.setProperty("database", "from_scratch");
        System.setProperty("user", "root");
        System.setProperty("password", "");
        // TODO code application logic here
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

//        Product p1 = new Product(1, "chaussure", new BigDecimal(50), date);
//
////        p1.setAttr( date);
//        Product p2 = new Product(1, "chaussure", new BigDecimal(100), date);
//        Product p3 = new Product(1, "chaussure", new BigDecimal(20), date);
//        Product p4 = new Product(4, "chaussure", new BigDecimal(10), date);

     //   System.out.println(p1.getPrice());

        Address address = new Address("City", "Region", "Street");

      //  User c1 = new User(0, "khoubeib", "bach", 0, "male", address, "khoubeib", "password");

        //     ShoppingCartItem cartitem = new ShoppingCartItem(p1);
        //     cartitem.incrementQuantity();
        //     cartitem.incrementQuantity();
        //     System.out.println(cartitem.getQuantity());
        ShoppingCart cart = new ShoppingCart();

//        cart.addItem(p4);
//        cart.addItem(p1);


//        cart.calculateTotal("50");
//        System.out.println(cart.getItems());
//        System.out.println(cart.getTotal());

        Order order = Model.find(Order.class, 2);
        ArrayList<Order> ord = Model.fetch(Order.class)
					.all()
					.where("id", "=", "1")
					.execute();
        System.out.println(ord);
        
        LineItem line = Model.find(LineItem.class, 1);
        System.out.println(line);
        
//        order.shoppingCart();

// order.customer();
    }

}
