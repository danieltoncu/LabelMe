/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apipart;

import com.cybozu.labs.langdetect.*;

import java.util.ArrayList;
import static apipart.MailReader.fetch;

/**
 *
 * @author amogage
 */
public class LanguageDetectionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        DetectorFactory.loadProfile("profiles");
        Detector detector;// = DetectorFactory.create();

        String username = "username@gmail.com";// change accordingly
        String password = "password";// change accordingly
        MailReader mailReader = new MailReader();
        ArrayList<String> messages = fetch(username, password);
        if (messages == null) {
            System.out.println("No messages.");
        } else {
            for (int i = 0; i < messages.size(); ++i) {
                System.out.println("Message : ");
                System.out.println(messages.get(i));
                detector = DetectorFactory.create();
                detector.append(messages.get(i));
                
                String lang = detector.detect();
                ArrayList langlist = detector.getProbabilities();

                System.out.println("--->Language for this message : " + lang + " ( " + langlist.toString() + "%)");
            }
        }
    }
}