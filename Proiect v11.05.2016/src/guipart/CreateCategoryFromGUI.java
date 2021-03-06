package guipart;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import apipart.Category;
import apipart.XML;

public class CreateCategoryFromGUI {
    
    MainPage mainPage = new MainPage();
    
    public CreateCategoryFromGUI(MainPage _mainPage){  
        this.mainPage = _mainPage;
        this.start(this.mainPage.mainStage);
    }

    Button create;
    Button back;
    Scene createCategory;
    TextField pathField = new TextField();

    ArrayList<File> fileNames;

    public ArrayList<File> getFileNames() {
        return fileNames;
    }

    public void start(Stage window) {

        BorderPane mainPageBorder = new BorderPane();
        mainPageBorder.setStyle("-fx-background-color: #B8EDFF;");

        createCategory = new Scene(mainPageBorder, 900, 600);
        fileNames = new ArrayList<File>();
        //Label pentru logo

        Label imageLabel = new Label();
        Image image = new Image(getClass().getResourceAsStream("/resources/gui/labelme_logo.png"));
        imageLabel.setGraphic(new ImageView(image));
        imageLabel.setPrefSize(200, 200);
        HBox imageBox = new HBox();
        imageBox.setAlignment(Pos.TOP_CENTER);
        imageBox.setPadding(new Insets(20, 20, 20, 20));
        imageBox.getChildren().add(imageLabel);

        //TEXT box category name,
        VBox boxText = new VBox();
        boxText.setPadding(new Insets(20, 20, 20, 20));
        boxText.setSpacing(10);
        boxText.setAlignment(Pos.TOP_CENTER);

        TextField textField = new TextField();
        textField.setPromptText("CategoryName");
        textField.setMaxWidth(200);
        textField.setMaxHeight(25);
        textField.setAlignment(Pos.TOP_CENTER);
        textField.setLayoutX(300);
        textField.setLayoutY(300);
        textField.setPrefWidth(300);
        textField.setPrefHeight(300);
        textField.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background,-30%);");
        
         TextField textField2 = new TextField ();
         textField2.setPromptText("KeyWords, sep by comma");
         textField2.setMaxWidth(200);
         textField2.setMaxHeight(25);
         textField2.setAlignment(Pos.TOP_CENTER);
         textField2.setLayoutX(300);
         textField2.setLayoutY(300);
         textField2.setPrefWidth(300);
         textField2.setPrefHeight(300);
         textField2.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background,-30%);");
         
  
        //  Label addMessagesLabel = new Label("Add messages");
        //   addMessagesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        //   addMessagesLabel.setStyle("-fx-text-fill: #FF6200;");
        //   Label CategoriesCreated = new Label("Categories created:");
        boxText.getChildren().addAll(textField,textField2, pathField );

        //Vbox2
        /*VBox boxText1 = new VBox();
         boxText1.setPadding(new Insets(20,20, 20, 20));
         boxText1.setSpacing(10);
         boxText1.setAlignment(Pos.CENTER);
         Label CategoriesCreated = new Label("Categories created:");
         boxText1.getChildren().addAll(CategoriesCreated);
         
         */
        //HBox pentru butoane;
        HBox boxButtons = new HBox();
        boxButtons.setPadding(new Insets(20, 20, 20, 20));
        boxButtons.setSpacing(40);
        boxButtons.setAlignment(Pos.CENTER);
        create = new Button("Create");
        back = new Button("Back");

        create.setPrefSize(200, 35);
        back.setPrefSize(200, 35);

        create.setStyle("-fx-background-color: #FF6200;"
                + "-fx-text-fill: white;");
        back.setStyle("-fx-background-color: #FF6200;"
                + "-fx-text-fill: white;");
        boxButtons.getChildren().addAll(back, create);
        
        create.setOnAction(e -> {
            
            String categoryName = textField.getText();
            String keyWordsPref = new String();
            keyWordsPref = textField2.getText();
            ArrayList<String> keyWords = new ArrayList<String>(Arrays.asList(keyWordsPref.split(",[ ]*")));
            Category cat = new Category();
              
            if(!categoryName.isEmpty()){
                if(!keyWordsPref.isEmpty())
                    cat.define(categoryName,keyWords);
            
                if(!fileNames.isEmpty())
                    cat.define(categoryName, fileNames.get(0).getAbsolutePath());
                mainPage.refreshCategoryList();
                mainPage.start(mainPage.getStage());
              }
            else{
                  textField.setPromptText("Set a category name!");
                  textField.setStyle("-fx-prompt-text-fill: red;");
            }
        });
        
        back.setOnAction(e -> {
            mainPage.refreshCategoryList();
            mainPage.start(mainPage.getStage());
        });
        
        mainPageBorder.setTop(imageBox);
        mainPageBorder.setCenter(boxText);
        mainPageBorder.setBottom(boxButtons);

        window.setTitle("Create Category");
        window.setScene(createCategory);
        window.show();

    }
}
