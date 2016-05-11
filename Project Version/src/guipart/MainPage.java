/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guipart;

import apipart.XML;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainPage extends Application {

    ArrayList<String> categories = new ArrayList<>();
    ArrayList<SetOfCategories> seturi = new ArrayList<>();
    SetOfCategories set1 = new SetOfCategories("FirstSet");

    Button messageButton;
    Button categoryButton;
    Button setButton;

    Scene mainPageScene;
    Stage mainStage;
    
    

    @Override
    public void start(Stage primaryStage) {

        mainStage = primaryStage;
        set1.addCategory("Sport");
        seturi.add(set1);

        TitledPane[] setsTitledPane = new TitledPane[seturi.size()];

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

        for (int i = 0; i < seturi.size(); i++) {
            setsTitledPane[i] = new TitledPane();
            setsTitledPane[i].setText(seturi.get(i).getName());
            setsTitledPane[i].setContent(seturi.get(i).categorii);
        }
        tbox.getChildren().addAll(setsTitledPane);

        //HBox pentru butoane;
        HBox box = new HBox();
        box.setPadding(new Insets(20, 20, 20, 20));
        box.setSpacing(40);
        box.setAlignment(Pos.CENTER);
        messageButton = new Button("Add Texts");
        categoryButton = new Button("Add Category");
        setButton = new Button("Seturi");

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
        mainPageBorder.setCenter(tbox);
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
        XML xml = new XML();
        xml.createDocument();
        launch(args);
    }

}
