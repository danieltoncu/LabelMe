/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guipart;

import apipart.Categorize;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

public class AddMessageUploadFile {

    private Scene scene;

    Button acceptButton;
    Button browseButton;
    Button finishButton;
    Button previousButton;
    Button cancelButton;
    Button backButton;
    ListView<String> fileList;
    ObservableList<String> files;
    
    ArrayList<File> fileNames;
    
    FileChooser fileChooser = new FileChooser();

    TextField pathField = new TextField();
    MainPage mainPage = new MainPage();
    Label textCategory;

    public AddMessageUploadFile(MainPage _mainPage) {
        mainPage = _mainPage;
        setScene(mainPage.getStage());
    }

    private void setScene(Stage window) {
        fileNames = new ArrayList<File>();
        fileList = new ListView<String>();
        files = FXCollections.observableArrayList();
        fileList.setItems(files);
        
        fileList.setTranslateY(-50);
        fileList.setMaxWidth(300);
        fileList.setMinHeight(150);
        
        Label addMessagesLabel = new Label("Add messages");
        Label loadedMessagesLabel = new Label("Loaded messages:");

        Label imageLabel2 = new Label();
        Image image2 = new Image(getClass().getResourceAsStream("/resources/gui/labelme_logo.png"));
        imageLabel2.setGraphic(new ImageView(image2));
        imageLabel2.setPrefSize(200, 200);
        HBox imageBox2 = new HBox();
        imageBox2.setAlignment(Pos.CENTER);
        imageBox2.setPadding(new Insets(20, 20, 20, 20));
        imageBox2.getChildren().add(imageLabel2);

        acceptButton = new Button("Accept All");
        browseButton = new Button("Browse");
        finishButton = new Button("Finish");
        previousButton = new Button("Previous");
        cancelButton = new Button("X");
        backButton = new Button("Back");
        pathField.setMinWidth(200);
        textCategory = new Label();

        addMessagesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(browseButton, pathField, acceptButton);
        hBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.setSpacing(30);

        VBox filesPlaceholder = new VBox();
        filesPlaceholder.setSpacing(10);
        
        backButton.setTranslateY(-50);
        
        browseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                //TODO: add more extensionFilters
                File currDir = new File(".");
                fileChooser.setInitialDirectory(currDir);
                fileChooser.getExtensionFilters().addAll((new FileChooser.ExtensionFilter("Text Files", "*.txt")));
                File file = fileChooser.showOpenDialog(window);

                if (file != null) {
                    pathField.setText(file.getName());
                    fileNames.add(file);
                    files.add(file.getName());
                }
                
                System.out.println("Fisierul "+file.toString());
                    System.out.println("Calea destinatiei "+currDir.getAbsoluteFile().getParent());
                    File pathDirectory= new File( currDir.getAbsoluteFile().getParent()+"\\Messages\\"+file.getName());
                    System.out.println(pathDirectory.toString());
                try {
                    new apipart.Categorize().copyFile(file,pathDirectory);
                } catch (IOException ex) {
                    Logger.getLogger(AddMessageUploadFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        
        acceptButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                if (!fileNames.isEmpty()) {
                    Categorize categorize = new Categorize();
                    
                    int size = files.size();
                    
                    for(int i = 0; i < size; i ++){
                        String fileName = files.get(i);
                        for(File file : fileNames){
                            if(file.getName().equals(fileName)){
                                try {
                                    ArrayList<String> categories = categorize.getCategory(FileUtils.readFileToString(file, "UTF-8"),file.getName());
                                    String rezultat="";
                                    for(int index=0;index < categories.size() ; index++){
                                        rezultat=rezultat+" "+categories.get(index);
                                    }
                                    
                                    files.add(fileName + "      " + rezultat);
                                } catch (IOException ex) {
                                    Logger.getLogger(AddMessageUploadFile.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                    
                    int i = 0;
                    while(i < size){
                        files.remove(0);
                        i++;
                    }
                    
                    /*
                    try {
                        textCategory.setText("The text category is: " + categorize.getCategory(FileUtils.readFileToString(fileNames.get(fileNames.size() - 1), "UTF-8")));
                    } catch (IOException ex) {
                        Logger.getLogger(AddMessageUploadFile.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                }
                /*
                if (pathField.getText() != null) {
                    int index = pathField.getText().lastIndexOf("\\");

                    Label messageLabel = new Label(pathField.getText().substring(index + 1));
                    messageLabel.setMinWidth(200);
                    messageLabel.setMinHeight(25);
                    messageLabel.setStyle("-fx-fill: orange;\n"
                            + "-fx-border-color: blue;\n"
                            + "-fx-border-width: 3;\n");
                    if (filesPlaceholder.getChildren().size() != 0) {
                        layout.getChildren().remove(layout.getChildren().size() - 1);
                    }

                    filesPlaceholder.getChildren().addAll(messageLabel);
                    filesPlaceholder.setAlignment(Pos.CENTER);
                    filesPlaceholder.setPadding(new Insets(0, 0, 0, 50));

                    layout.getChildren().add(filesPlaceholder);
                }*/
            }
        });

        backButton.setOnAction(e -> {
            mainPage.refreshCategoryList();
            mainPage.start(mainPage.getStage());
        });

        layout.getChildren().addAll(imageLabel2, addMessagesLabel, hBox, textCategory, fileList , backButton);
        layout.setStyle("-fx-background-color: white");
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #B8EDFF;");
        scene = new Scene(layout, 900, 600);
    }

    public Scene getScene() {
        return scene;
    }
}
