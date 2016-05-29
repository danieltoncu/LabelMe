package apipart;

public class Application {

    public static void main(String args[]) {
        Categorize categorize = new Categorize();
        //categorize.trainModel();
      //System.out.println(categorize.getCategory("The oldest classical Greek and Latin writing had little or no space between words,"," and could be written in boustrophedon (alternating directions). Over time, text direction (left to right) became standardized, and word dividers and terminal punctuation became common. The first way to divide sentences into groups was the original paragraphos, similar to an underscore at the beginning of the new group.[3] The Greek paragraphos evolved into the pilcrow (¶), which in English manuscripts in the Middle Ages can be seen inserted inline between sentences. The hedera leaf (e.g. ☙) has also been used in the same way."));
     Category a= new Category();
////a.define("new", "file.txt");
      //a.define("netru","file.txt");
//        System.out.println("aici");
//       a.define("positive", "positive.txt");
//       a.define("negative", "negative.txt");
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
