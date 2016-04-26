package guipart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Chiparus
 */
public class SetOfCategories {
    public String nume;
    //public ArrayList<String> categorii = new ArrayList<>();
    public ListView<String> categorii = new ListView<>();
    ObservableList<String> items = FXCollections.observableArrayList();
    
    public SetOfCategories(String name){
        nume = name;
        categorii.setItems(items);
    }
    
    //public void addCategory( String category ){
    //    categorii.add("category");
    //}
    
    public String getName(){
        return nume;
    }
    
    public void addCategory(String categorie){
        items.add(categorie);
    }
    
    
}
