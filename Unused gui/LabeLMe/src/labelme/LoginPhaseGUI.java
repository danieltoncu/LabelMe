/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labelme;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import myfxstyle.CoolStyle;

/**
 *
 * @author Cristian
 */
public class LoginPhaseGUI extends Application {
    
    private Button loginButton;
    private Scene scene;
    private BorderPane layout;
    private VBox centerContent;
    private TextField usernameArea;
    private PasswordField passwordArea;
    
    private Label helloText;
    private Label questionText;
    private Label registerText;
    
    private final int appHeight = 550;
    private final int appWidth  = 400;
    
    @Override
    public void start(Stage window) {
        
        this.loginButton   = new Button("LOGIN");
        this.layout        = new BorderPane();
        this.scene         = new Scene(this.layout, this.appWidth, this.appHeight);   
        this.centerContent = new VBox();
        this.usernameArea  = new TextField ();
        this.passwordArea  = new PasswordField();
        this.helloText     = new Label("Hello, please login");
        this.questionText  = new Label("You don't have an account yet?");
        this.registerText  = new Label("Register here.");
        
        window.setScene(scene);
        CoolStyle.setCoolWindowStyle(this.scene, this.layout);
        CoolStyle.setCoolButtonStyle(loginButton);
        CoolStyle.setCoolSceneStyle(scene);
        
        this.centerContent.setSpacing(25);
        this.usernameArea.setPromptText("username");
        this.usernameArea.setMaxWidth(250);
        this.passwordArea.setPromptText("password");
        this.passwordArea.setMaxWidth(250);

        this.centerContent.getChildren().addAll(this.helloText, this.usernameArea, this.passwordArea, this.loginButton, this.questionText, this.registerText);
        this.centerContent.setAlignment(Pos.CENTER);
        this.centerContent.setTranslateY(80);
        this.layout.setCenter(this.centerContent);
        
        this.registerText.setOnMouseReleased(e -> {
            RegisterPhaseGUI reg = new RegisterPhaseGUI();
        });
        CoolStyle.setCoolTextFormat(centerContent);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("prism.lcdtext", "false"); // this is for antialiasing
        launch(args);
    }
    
}
