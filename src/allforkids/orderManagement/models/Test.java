/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author KHOUBEIB
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        
        Product p1 = new Product(1, "chaussure", new BigDecimal(50), date);
        
//        p1.setAttr( date);
        
        Product p2 = new Product(1, "chaussure", new BigDecimal(100), date);
        Product p3 = new Product(1, "chaussure", new BigDecimal(20), date);
        Product p4 = new Product(4, "chaussure", new BigDecimal(10), date);

        System.out.println(p1.getPrice());
        
        Address address = new Address("City", "Region", "Street");

        User c1 = new User(0, "khoubeib", "bach", 0, "male", address, "khoubeib", "password");
        
   //     ShoppingCartItem cartitem = new ShoppingCartItem(p1);
   //     cartitem.incrementQuantity();
   //     cartitem.incrementQuantity();
   //     System.out.println(cartitem.getQuantity());
        
        
   
   
   
        ShoppingCart cart = new ShoppingCart();
        
               
        cart.addItem(p4);
        cart.addItem(p1);
                
        cart.calculateTotal("50");
        System.out.println(cart.getItems());
        System.out.println(cart.getTotal());
        
    }
    
}
