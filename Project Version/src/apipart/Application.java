package apipart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String args[]) {
        Categorize categorize = new Categorize();
        //categorize.trainModel();
        //System.out.println(categorize.getCategory("what a hot day!"));
        //Category a= new Category();
        //a.define("positive", "positive.txt");
        //a.define("negative", "negative.txt");
        //xml.addCategory("sports");
        //xml.addMessage("sports","blablablabla");
        //XML xml= new XML();
        //xml.createDocument();
        //xml.addMessage("weather", "what a nice weather");
        //Category newCategory= new Category();
        //newCategory.define("weather","file.txt");
        //Categorize categorize=new Categorize();
        System.out.println(categorize.getCategory("Good job, well done baby"));
        //XML xml=new XML();
        //Map<String,ArrayList<String>> map= new HashMap<>();
        //ArrayList<String> mesaje=new ArrayList<>();
        //map=xml.getMessagesForCategory();
        //mesaje=map.get("weather");
        //for(int i=0;i<mesaje.size();i++){
        //	System.out.println(mesaje.get(i));
        //}

    }
}
