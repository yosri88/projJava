/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Wassim
 */
public class SMSService {
    public static final String ACCOUNT_SID = "AC8d07e2e6bb3fabc949dde4535cf11bf8";
    public static final String AUTH_TOKEN = "c7edbb45015f61f20bfb8dab2128bfa2";

    public static void send(String to, String body) {
      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

      Message message = Message.creator(new PhoneNumber("+216" + to),
          new PhoneNumber("+14085969483"), 
          body).create();

      System.out.println(message.getSid());
    }
}

