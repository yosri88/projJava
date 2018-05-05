/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.orderManagement.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author KHOUBEIB
 */
public class Helpers {

    public static Double currencyFormatter(Double input) {
        
        

        DecimalFormat formatter = new DecimalFormat("#0.00000");
        //return Double.parseDouble(formatter.format(input));
        String formated = formatter.format(input);
        Double output = Double.parseDouble(formated.replace(",","."));
        
        return output;

    }

}
