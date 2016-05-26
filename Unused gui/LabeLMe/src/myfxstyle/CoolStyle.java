/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfxstyle;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Cristian
 */
public class CoolStyle {
    
    public static String cssPath  = CoolStyle.class.getResource("/resources/styles/cool-style.css").toExternalForm();
    //public static String fontPathLight = CoolStyle.class.getResource("resources/fonts/Brandon_light.otf").toExternalForm();
    
    static double xPos = 0;
    static double yPos = 0;
    
    public static void setCoolButtonStyle(Button button){
        String coolStyle = ""
                + "-fx-background-color: #BC2A2A;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 0px;"
                + "-fx-padding: 8px 20px 8px 20px;"
                + "-fx-font-size: 16px;"
                + "-fx-cursor: pointer;";
        
        button.getStyleClass().add("buttons");
    }
    
    public static void setCoolWindowStyle(Scene scene, BorderPane layout){
        //We use our personalized titlebar and hide the default one
        Stage window = (Stage)scene.getWindow();
        HBox titleBar = createTitleBar(scene);

        titleBar.setOnMousePressed((MouseEvent e) -> {
            xPos = window.getX() - e.getScreenX();
            yPos = window.getY() - e.getScreenY();
        });
        titleBar.setOnMouseDragged(e -> {
            window.setX(e.getScreenX() + xPos);
            window.setY(e.getScreenY() + yPos);
        });
        window.initStyle(StageStyle.TRANSPARENT); // hide the default title bar
        
        
        
        layout.setTop(titleBar);
        
    }
    
    private static HBox createTitleBar(Scene scene){
        int prefWidth  = 60;
        int prefHeight = 40;
        
        int barsWidth  = 25;
        int barsHeight = 4;
        
        HBox titleBar      = new HBox();
        
        StackPane closeButton = new StackPane();
        StackPane minimizeButton = new StackPane();
        
        Rectangle minimizeShape = new Rectangle();
        minimizeShape.setWidth(prefWidth);
        minimizeShape.setHeight(prefHeight);
        
        Rectangle minimizeBar = new Rectangle();
        minimizeBar.setWidth(barsWidth);
        minimizeBar.setHeight(barsHeight);
        minimizeBar.setFill(Color.WHITE);
        
        Rectangle closeShape = new Rectangle();
        closeShape.setWidth(prefWidth);
        closeShape.setHeight(prefHeight);
        
        Rectangle closeBar1 = new Rectangle();
        closeBar1.setWidth(barsWidth);
        closeBar1.setHeight(barsHeight);
        closeBar1.setFill(Color.WHITE);
        closeBar1.setRotate(45);
        
        Rectangle closeBar2 = new Rectangle();
        closeBar2.setWidth(barsWidth);
        closeBar2.setHeight(barsHeight);
        closeBar2.setFill(Color.WHITE);
        closeBar2.setRotate(-45);
        
        titleBar.setStyle("-fx-background-color: #66ccff;");
        closeShape.setStyle("-fx-fill: #66ccff");
        minimizeShape.setStyle("-fx-fill: #66ccff");
        
        minimizeButton.getChildren().addAll(minimizeShape, minimizeBar);
        closeButton.getChildren().addAll(closeShape, closeBar1, closeBar2);
        
        
        
        closeButton.setTranslateX(scene.getWidth() - 2 * prefWidth);
        minimizeButton.setTranslateX(scene.getWidth() - 2 * prefWidth);
        
        closeButton.setOnMouseReleased(e -> {
            Stage window = (Stage)(((StackPane)e.getSource()).getScene()).getWindow();
            window.close();
        });
        
        minimizeButton.setOnMouseReleased(e -> {
            Stage window = (Stage)(((StackPane)e.getSource()).getScene()).getWindow();
            window.setIconified(true);
        });
        
        closeButton.setOnMouseEntered(e ->{
            closeShape.setStyle("-fx-fill:  #cc0000");
        });
        
        closeButton.setOnMouseExited(e ->{
            closeShape.setStyle("-fx-fill:  #66ccff");
        });
        
        minimizeButton.setOnMouseEntered(e -> {
            minimizeShape.setStyle("-fx-fill:  #3366ff");
        });
        
        minimizeButton.setOnMouseExited(e -> {
            minimizeShape.setStyle("-fx-fill:  #66ccff");
        });

        titleBar.getChildren().addAll(minimizeButton,closeButton);
        
        return titleBar;
    }
    
    public static void setCoolSceneStyle(Scene scene){
        scene.getStylesheets().add(cssPath);
    }
    
    public static void setCoolTextFormat(VBox centerContent){
        Label helloText    = (Label)centerContent.getChildren().get(0);
        Label questionText = (Label)centerContent.getChildren().get(4); 
        Label registerText = (Label)centerContent.getChildren().get(5);
        
        helloText.setStyle("-fx-font-size: 40px;"
                + "-fx-font-family: \"Brandon Grotesque Light\";");
        helloText.setTranslateY(-120);
        
        questionText.setStyle("-fx-font-size: 20px;"
                + "-fx-font-family: \"Brandon Grotesque Light\";");
        //questionText.setTranslateY(30);
        registerText.setStyle("-fx-font-size: 20px;"
                + "-fx-font-family: \"Brandon Grotesque Light\";"
                + "-fx-text-fill: blue;"
                + "-fx-cursor: hand;");
        registerText.setTranslateY(-28);
        //questionText.setTranslateX(-( centerContent.getScene().getWidth() / 3));
        
    }
}
