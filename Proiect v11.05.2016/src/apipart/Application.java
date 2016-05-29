package apipart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String args[]) {
        Categorize categorize = new Categorize();
        //categorize.trainModel();
//    System.out.println(categorize.getCategory("very good","tr.txt"));
      Category a= new Category();
//a.define("new", "file.txt");
//        ArrayList<String> ai =new ArrayList();
//        ai.add("positix");
//        a.define("netru",ai);
//        a.define("positive1", "positive.txt");
//        a.define("negative", "negative.txt");
//        //xml.addCategory("sports");
//        //xml.addMessage("sports","blablablabla");
        XML xml= new XML();
        xml.createDocument();
        //xml.addCategory("weather");
        //Category newCategory= new Category();
        //newCategory.define("weather","file.txt");
        //Categorize categorize=new Categorize();
        //System.out.println(categorize.getCategory("Good job, well done baby"));
        /*XML xml=new XML();
        Map<String,ArrayList<String>> map= new HashMap<>();
//        ArrayList<String> mesaje=new ArrayList<>();
        map=xml.getMessagesForCategory();
        mesaje=map.get("mancare");
        for(int i=0;i<mesaje.size();i++){
        	System.out.println(mesaje.get(i));
        }*/
        
       DefinitionXML definition= new DefinitionXML();
       definition.createDocument();
//    	Category c=new Category();
//    	ArrayList<String> a= new ArrayList<>();
//    	a.add("ana"); a.add("mere");
//    	c.define("ana", a);


    }
}
