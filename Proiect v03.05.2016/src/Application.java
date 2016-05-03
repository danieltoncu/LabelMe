
public class Application {
    public static void main(String args[]){
    	//Categorize categorize=new Categorize();
    	//categorize.trainModel();
    	//System.out.println(categorize.getCategory("what a hot day!"));
    	//Category a= new Category();
    	//a.define("weather", "file.txt");
    	//XML xml=new XML();
    	//xml.createDocument();
		//xml.addCategory("sports");
		//xml.addMessage("sports","blablablabla");
		//XML xml= new XML();
		//xml.createDocument();
		//xml.addMessage("weather", "what a nice weather");
		//Category newCategory= new Category();
		//newCategory.define("weather","file.txt");
		Categorize categorize=new Categorize();
		System.out.println(categorize.getCategory("What a nice day!"));



    }
}
