
package guipart;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmailPage {
    MainPage mainPage = new MainPage();

    
    public EmailPage(MainPage _mainPage){  
        this.mainPage = _mainPage;
        this.start(this.mainPage.mainStage);
    }
    
    Scene emailScene;

    public void start(Stage window) {
        BorderPane emailBorderPane = new BorderPane();
        emailBorderPane.setStyle("-fx-background-color: #B8EDFF;");
        VBox boxForButton = new VBox();
        boxForButton.setPadding(new Insets(0, 0, 100, 0));
        emailScene = new Scene(emailBorderPane, 900, 600);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        
        Label lblUserName = new Label("Username");
        final TextField usernameField = new TextField();
        Label lblPassword = new Label("Password");
        final PasswordField passwordField = new PasswordField();
        Button btnLogin = new Button("Accept");
        Button btnBack = new Button("Back");
        final Label lblMessage = new Label();
        
        
        Label imageLabel = new Label();
        Image image = new Image(getClass().getResourceAsStream("/resources/gui/labelme_logo.png"));
        imageLabel.setGraphic(new ImageView(image));
        imageLabel.setPrefSize(200, 200);
        HBox imageBox = new HBox();
        imageBox.setAlignment(Pos.TOP_CENTER);
        imageBox.setPadding(new Insets(20, 20, 20, 20));
        imageBox.getChildren().add(imageLabel);
         
        //Adding Nodes to GridPane layout
        gridPane.add(lblUserName, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(btnLogin, 1, 2);
         
        btnBack.setOnAction(e -> {
            mainPage.refreshCategoryList();
            window.setScene(mainPage.mainPageScene);
        });
        boxForButton.getChildren().add(btnBack);
        boxForButton.setAlignment(Pos.CENTER);
        btnBack.setStyle("-fx-background-color: #FF6200;"
                + "-fx-text-fill: white;");
        btnBack.setPrefSize(200, 35);
        imageBox.setAlignment(Pos.CENTER);
        emailBorderPane.setTop(imageBox);
        
        gridPane.setPadding(new Insets(0, 0, 200, 0));
        gridPane.setAlignment(Pos.CENTER);
        emailBorderPane.setCenter(gridPane);
        emailBorderPane.setBottom(boxForButton);
        window.setTitle("Label Me");
        window.setScene(emailScene);
        window.show();
    }
}
