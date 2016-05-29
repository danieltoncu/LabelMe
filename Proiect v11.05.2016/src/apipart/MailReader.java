package apipart;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.*;

public class MailReader {
    
    static ArrayList<String> storedMessages = null;
    static String pop3Host = "pop.gmail.com";// change accordingly
    static String mailStoreType = "pop3";

    public static ArrayList<String> fetch(String user,String password) {
        try {
            // create properties field
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(pop3Host, user, password);

            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                writePart(message);
            }
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storedMessages;
    }

    

    public static void writePart(Part p) throws Exception {
        if (p instanceof Message) //Call methos writeEnvelope
        {
           // writeEnvelope((Message) p);
        }

        if (p.isMimeType("text/plain")) {
            //System.out.println("---------------------------");
           // System.out.println((String) p.getContent());
            if(storedMessages == null){
                storedMessages = new ArrayList<>();
                //System.out.println("da");
                
            }
            
            storedMessages.add(p.getContent().toString());
        } //check if the content has attachment
        else if (p.isMimeType("multipart/*")) {
            //System.out.println("This is a Multipart");
            //System.out.println("---------------------------");
            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++) {
                writePart(mp.getBodyPart(i));
            }
        } //check if the content is a nested message
        else if (p.isMimeType("message/rfc822")) {
            //System.out.println("This is a Nested Message");
            //System.out.println("---------------------------");
            writePart((Part) p.getContent());
        } //check if the content is an inline image
          

    }

    /*
   * This method would print FROM,TO and SUBJECT of the message
     */
    public static void writeEnvelope(Message m) throws Exception {
        System.out.println("This is the message envelope");
        System.out.println("---------------------------");
        Address[] a;

        // FROM
        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++) {
                System.out.println("FROM: " + a[j].toString());
            }
        }

        // TO
        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++) {
                System.out.println("TO: " + a[j].toString());
            }
        }

        // SUBJECT
        if (m.getSubject() != null) {
            System.out.println("SUBJECT: " + m.getSubject());
        }

    }
}
