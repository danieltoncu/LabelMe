/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guipart;

import apipart.XML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainPage extends Application {

    //ArrayList<String> categorii = new ArrayList<>();
    static ArrayList<SetOfCategories> categorii = new ArrayList<>();
    static SetOfCategories categorie = new SetOfCategories("FirstSet");
    static Map<String, ArrayList<String>> mapXml = new HashMap<>();

    Button messageButton;
    Button categoryButton;
    Button setButton;

    Scene mainPageScene;
    Stage mainStage;
    
    

    @Override
    public void start(Stage primaryStage) {

        mainStage = primaryStage;
        //categorie.addText("Sport");
        //categorii.add(categorie);

        XML xml = new XML();
        mapXml = xml.getMessagesForCategory();
        
        Set<String> keys = mapXml.keySet();
        for(String s : keys){
            SetOfCategories categorie = new SetOfCategories(s);
            ArrayList<String> texte = new ArrayList<>();
            texte = mapXml.get(s);
            
            for(int i= 0 ; i< texte.size(); i++){
                categorie.addText(texte.get(i));
            }
            
            
            categorii.add(categorie);     
        }
        
        
        TitledPane[] setsTitledPane = new TitledPane[categorii.size()];

        BorderPane mainPageBorder = new BorderPane();
        mainPageBorder.setStyle("-fx-background-color: #B8EDFF;");

        //Label pentru logo
        Label imageLabel = new Label();
        Image image = new Image(getClass().getResourceAsStream("/resources/gui/labelme_logo.png"));
        imageLabel.setGraphic(new ImageView(image));
        imageLabel.setPrefSize(200, 200);
        HBox imageBox = new HBox();
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(20, 20, 20, 20));
        imageBox.getChildren().add(imageLabel);

        //HBox pentru titled pane cu seturi.
        
        HBox tbox = new HBox();
        tbox.setPadding(new Insets(30, 30, 30, 30));
        tbox.setSpacing(70);
        tbox.setAlignment(Pos.CENTER);
        

        for (int i = 0; i < categorii.size(); i++) {
            setsTitledPane[i] = new TitledPane();
            setsTitledPane[i].setText(categorii.get(i).getName());
            setsTitledPane[i].setContent(categorii.get(i).categorii);
        }
        tbox.getChildren().addAll(setsTitledPane);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(tbox);
        scrollPane.setStyle("-fx-background: #B8EDFF;");

        //HBox pentru butoane;
        HBox box = new HBox();
        box.setPadding(new Insets(20, 20, 20, 20));
        box.setSpacing(40);
        box.setAlignment(Pos.CENTER);
        messageButton = new Button("Add Texts");
        categoryButton = new Button("Add Category");
        setButton = new Button("Set");

        messageButton.setPrefSize(200, 35);
        categoryButton.setPrefSize(200, 35);
        setButton.setPrefSize(200, 35);
        messageButton.setStyle("-fx-background-color: #FF6200;"
                + "-fx-text-fill: white;");
        categoryButton.setStyle("-fx-background-color: #FF6200;"
                + "-fx-text-fill: white;");
        setButton.setStyle("-fx-background-color: #FF6200;"
                + "-fx-text-fill: white;");
        box.getChildren().addAll(messageButton, categoryButton, setButton);
        
        
        messageButton.setOnAction(e -> {
            //primaryStage.setScene(addMessagesScene);
            AddMessageUploadFile addMessage = new AddMessageUploadFile(this);
            mainStage.setScene(addMessage.getScene());
        });
        
        
        categoryButton.setOnAction(e -> {
            CreateCategoryFromGUI newGUI = new CreateCategoryFromGUI(this);
        });
        mainPageScene = new Scene(mainPageBorder, 900, 600);
        mainPageBorder.setTop(imageBox);
        mainPageBorder.setCenter(scrollPane);
        mainPageBorder.setBottom(box);

        mainStage.setTitle("MainPage!");
        mainStage.setScene(mainPageScene);
        mainStage.show();
    }
    
    
    public Stage getStage() {
        return mainStage;
    }

    public MainPage getMainPage() {
        return this;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
