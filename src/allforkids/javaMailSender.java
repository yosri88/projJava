/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids;

/**
 *
 * @author KHOUBEIB
 */
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class javaMailSender {

    // Replace sender@example.com with your "From" address.
    // This address must be verified.
    static final String FROM = "test@allforkids.ml";
    static final String FROMNAME = "All For Kids";

    // Replace recipient@example.com with a "To" address. If your account 
    // is still in the sandbox, this address must be verified.
    static final String TO = "web-m6rsf@mail-tester.com";

    // Replace smtp_username with your Amazon SES SMTP user name.
    static final String SMTP_USERNAME = "test@allforkids.ml";

    // Replace smtp_password with your Amazon SES SMTP password.
    static final String SMTP_PASSWORD = "traxdata+-007";

    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    static final String CONFIGSET = "ConfigSet";

    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    static final String HOST = "mail.allforkids.ml";

    // The port you will connect to on the Amazon SES SMTP endpoint. 
    static final int PORT = 25;

    static final String SUBJECT = "All For Kids (SMTP service test using Java)";

//    static final String BODY = String.join(
//    	    System.getProperty("line.separator"),
//    	    "<h1>All For Kids SMTP Email Test</h1>",
//    	    "<p>This email was sent with Pstfix Service (mail.allforkids.ml) using the ", 
//    	    "<a href='https://github.com/WassimKallel/AllForKids'>AllFoKids JavaMail</a>",
//    	    " for <a href='http://www.allforkids.ml'>all fo kids</a>."
//    	);
    
    
   // ----> KHOUBEIB
    
    static final String plainMsg = "My plaintext";
    static final String htmlMsg = String.join(
            System.getProperty("line.separator"),
            "<h1>All For Kids SMTP Email Test</h1>",
            "<p>This email was sent with Pstfix Service (mail.allforkids.ml) using the ",
            "<a href='https://github.com/WassimKallel/AllForKids'>AllFoKids JavaMail</a>",
            " for <a href='http://www.allforkids.ml'>all fo kids</a>."
    );

    String text = "Hello, World";
    String html = "<h1>" + text + "</h1>";
    
    Session mailSession = Session.getInstance( new Properties() );

    
//    MimeMessage message = new MimeMessage( mailSession );
//    Multipart multipart = new MimeMultipart( "alternative" );
//
//        MimeBodyPart textPart = new MimeBodyPart();
//    textPart.setText( text, "utf-8" );
//
//    MimeBodyPart htmlPart = new MimeBodyPart();
//    htmlPart.setContent( html, "text/html; charset=utf-8" );
//
//    multipart.addBodyPart( textPart );
//    multipart.addBodyPart( htmlPart );
//    message.setContent( multipart );
    
    // -----> KHOUBEB END
    
    
    
    
    static final String BODY = String.join(
            System.getProperty("line.separator"),
            "Simple text",
            "<html>",
            "<h1>Ce mail a un scoe de 100%</h1>",
            "<p>Il ne tombra pas dans les Junk ;) :) ",
            "<a href='https://mxtoolbox.com/SuperTool.aspx?action=dkim%3aallforkids.ml%3a201804&run=toolpage'>Validation</a>",
            "</html>"
    );

    public static void main(String[] args) throws Exception {

        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties. 
        Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY, "text/html");

        // Add a configuration set header. Comment or delete the 
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

        // Create a transport.
        Transport transport = session.getTransport();

        // Send the message.
        try {
            System.out.println("Sending...");

            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }
}
