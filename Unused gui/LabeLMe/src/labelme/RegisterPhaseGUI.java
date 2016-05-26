/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labelme;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import myfxstyle.CoolStyle;

/**
 *
 * @author Cristian
 */
public class RegisterPhaseGUI {
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private Button registerButton;
    
    public RegisterPhaseGUI(){
        this.window  = new Stage();
        this.layout = new BorderPane();
        this.scene   = new Scene(layout, 300, 400);
        this.registerButton = new Button();
        this.drawIt();
    }
    
    private void drawIt(){
        this.window.initModality(Modality.APPLICATION_MODAL);
        
        this.window.setScene(scene);
        
        CoolStyle.setCoolWindowStyle(scene, layout);
        
        this.window.show();
    }
}
