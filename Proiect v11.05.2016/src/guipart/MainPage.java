/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guipart;

import apipart.XML;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class MainPage extends Application {
    Text text = new Text("aici se afiseaza continutul fisierului text.");
    //ArrayList<String> categorii = new ArrayList<>();
    static ArrayList<SetOfCategories> categorii = new ArrayList<>();
    static SetOfCategories categorie = new SetOfCategories("FirstSet");
    static Map<String, ArrayList<String>> mapXml = new HashMap<>();
    Accordion acordeon = new Accordion();
    

    
    Button messageButton;
    Button categoryButton;
    Button emailButton;

    Scene mainPageScene;
    Stage mainStage;
    StackPane forText = new StackPane();
    

    public void refreshCategoryList(){

        categorii = new ArrayList<>();
        XML xml = new XML();
        mapXml = xml.getMessagesForCategory();
        
        Set<String> keys = mapXml.keySet();
        for(String s : keys){
            SetOfCategories categories = new SetOfCategories(s);
            ArrayList<String> texte = new ArrayList<>();
            texte = mapXml.get(s);
            
            for(int i = 0; i < texte.size(); i++){
                categories.addText(texte.get(i));
            }            
            categorii.add(categories);     
        }
           // setsTitledPane = new TitledPane[categorii.size()];
            }

    @Override
    public void start(Stage primaryStage) {
        try {
            DetectorFactory.loadProfile("profiles");
        } catch (LangDetectException ex) {
            Logger.getLogger(EmailPage.class.getName()).log(Level.SEVERE, null, ex);
        }

        mainStage = primaryStage;
        //categorie.addText("Sport");
        //categorii.add(categorie);
        
        Accordion acordeon = new Accordion();
    TextFlow continut = new TextFlow();    
    HBox total = new HBox();
    VBox stanga = new VBox();
    VBox dreapta = new VBox();
        
        refreshCategoryList();
        forText.setPrefSize(300, 300);
        forText.setMaxSize(300, 300);
        forText.setAlignment(Pos.CENTER);
        continut = new TextFlow();
        continut.setPadding(new Insets(10, 10, 10, 10));
        continut.getChildren().add(text);
        forText.getChildren().add(continut);
    

        BorderPane mainPageBorder = new BorderPane();
        BorderPane outsidePageBorder = new BorderPane();
        mainPageBorder.setStyle("-fx-background-color: #B8EDFF;");

        //Label pentru logo
        Label imageLabel = new Label();
        Image image = new Image(getClass().getResourceAsStream("/resources/gui/labelme_logo.png"));
        imageLabel.setGraphic(new ImageView(image));
        imageLabel.setPrefSize(200, 200);
        imageLabel.setPadding(new Insets( 0, 100, 60, 100));
        HBox imageBox = new HBox();
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(20, 20, 20, 20));
        imageBox.getChildren().add(imageLabel);

        //HBox pentru titled pane cu seturi.
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(30, 30, 30, 30));
        vbox.setSpacing(70);
        vbox.setAlignment(Pos.CENTER);
  
          TitledPane[] setsTitledPane = new TitledPane[categorii.size()];
        for (int i = 0; i < categorii.size(); i++) {
            setsTitledPane[i] = new TitledPane();
            setsTitledPane[i].setText(categorii.get(i).getName());
            setsTitledPane[i].setContent(categorii.get(i).categorii);
            setsTitledPane[i].getContent().setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    if(event.getButton().equals(MouseButton.PRIMARY)){
                        if(event.getClickCount() == 2){
                            System.out.println("Double clicked");
                     }
                   }
               }    
           });
       }
        acordeon.getPanes().addAll(setsTitledPane);
                    
        //HBox pentru butoane;
        VBox box = new VBox();
        box.setPadding(new Insets(20, 20, 20, 20));
        box.setSpacing(40);
        box.setAlignment(Pos.CENTER);
        messageButton = new Button("Add Texts");
        categoryButton = new Button("Add Category");
        emailButton = new Button("Email");
        box.setPadding(new Insets(20, 0, 0, 0));

        messageButton.setPrefSize(200, 35);
        categoryButton.setPrefSize(200, 35);
        emailButton.setPrefSize(200, 35);
        messageButton.setStyle("-fx-background-color: #FF6200;"
                + "-fx-text-fill: white;");
        categoryButton.setStyle("-fx-background-color: #FF6200;"
                + "-fx-text-fill: white;");
        emailButton.setStyle("-fx-background-color: #FF6200;"
                + "-fx-text-fill: white;");
        forText.setStyle("-fx-border-color: #C2AFFF;"
                + "-fx-border-width: 2px;"
                + "-fx-background-color: #FFFFFF;");
        box.getChildren().addAll(messageButton, categoryButton, emailButton);
        
        
        messageButton.setOnAction(e -> {
            //primaryStage.setScene(addMessagesScene);
            AddMessageUploadFile addMessage = new AddMessageUploadFile(this);
            mainStage.setScene(addMessage.getScene());
        });
        
        emailButton.setOnAction(e -> {
            EmailPage emailPageee = new EmailPage(this);
        });
        
        
        categoryButton.setOnAction(e -> {
            CreateCategoryFromGUI newGUI = new CreateCategoryFromGUI(this);
        });
        
        
        outsidePageBorder.setLeft(acordeon);
        outsidePageBorder.setCenter(total);
        total.getChildren().addAll(stanga, dreapta);
        stanga.getChildren().add(imageLabel);
        stanga.getChildren().add(forText);
        stanga.setAlignment(Pos.CENTER);
        dreapta.getChildren().add(box);
        
        
        
        total.setStyle("-fx-background-color: #B8EDFF;");
        stanga.setStyle("-fx-background-color: #B8EDFF;");
        dreapta.setStyle("-fx-background-color: #B8EDFF;");
        
        
        mainPageScene = new Scene(outsidePageBorder, 900, 600);
        mainStage.setTitle("MainPage!");

        mainStage.setTitle("LabelMe");
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
