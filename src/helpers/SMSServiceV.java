/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.SmsSubmissionResponseMessage;
import com.vonage.client.sms.messages.TextMessage;

/**
 *
 * @author kbach
 */
public class SMSServiceV {

    public static final String API_KEY = "86d3b9f3";
    public static final String API_SECRET = "gpA9sebJmSerEDaz";
    static VonageClient client = new VonageClient.Builder()
            .apiKey(API_KEY)
            .apiSecret(API_SECRET)
            .build();

   public static void send(String to, String body) {
    //String messageText = "Hello from Vonage SMS API";
    TextMessage message = new TextMessage("Validation", "+216" + to, body);
    SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);    
   for(SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
          System.out.println(responseMessage);
     }
   
   }

   
}
