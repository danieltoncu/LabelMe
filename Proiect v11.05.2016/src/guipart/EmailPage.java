
package guipart;

import apipart.Categorize;
import apipart.MailReader;
import static apipart.MailReader.fetch;
import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import org.apache.commons.io.FileUtils;

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
        final TextField usernameField = new TextField("labelmetest@gmail.com");
        Label lblPassword = new Label("Password");
        final PasswordField passwordField = new PasswordField();
        passwordField.setText("labelmetest2");
        Button btnLogin = new Button("Accept");
        Button btnBack = new Button("Back");
        final Label lblMessage1 = new Label("STATUS:");
        final Label lblMessage2 = new Label("Waiting...");
        
        
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
        gridPane.add(btnLogin, 1, 3);
        gridPane.add(lblMessage1, 0, 2);
        gridPane.add(lblMessage2, 1, 2);

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
        
        gridPane.setPadding(new Insets(0, 0, 180, 0));
        gridPane.setAlignment(Pos.CENTER);
        emailBorderPane.setCenter(gridPane);
        emailBorderPane.setBottom(boxForButton);
        window.setTitle("Label Me");
        window.setScene(emailScene);
        window.show();
        
        btnLogin.setOnAction(e -> {
            int flag = 0;
            gridPane.requestLayout();
            if(usernameField.getText().length() != 0 && passwordField.getText().length() != 0){
                Detector detector;
                MailReader mailReader = new MailReader();
                ArrayList<String> messages = null;
                try {
                    messages = fetch(usernameField.getText(), passwordField.getText());
                    flag = 1;
                } catch (Exception ex) {
                    Logger.getLogger(EmailPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(flag == 1){
                    if (messages == null) {
                        lblMessage2.setText("You have no new messages.");
                    }else{
                        
                        File dir = new File("mails/"+ usernameField.getText() +"/");
                        dir.mkdir();

                        
                        for (int i = 0; i < messages.size(); ++i) {
                            try {

                                detector = DetectorFactory.create();
                                detector.append(messages.get(i));
                                String lang = detector.detect();
                                
                                if(lang.equals("ro") || lang.equals("en")){
                                    
                                    Categorize categorize = new Categorize();
                                    
                                    File file = new File("mails/"+ usernameField.getText() + "/" +  MailReader.subjects.get(i) + ".txt");
                                    file.createNewFile();
                                
                                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                                    BufferedWriter bw = new BufferedWriter(fw);

                                    bw.write(messages.get(i));
                                    bw.close();
                                    
                                    ArrayList<String> categories = categorize.getCategory(messages.get(i),file.getName());
                                }
                                
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(EmailPage.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnsupportedEncodingException ex) {
                                Logger.getLogger(EmailPage.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(EmailPage.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (LangDetectException ex) {
                                Logger.getLogger(EmailPage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        lblMessage2.setText("Messages succesfully exported!");
                    }
                }
            }else{
                lblMessage2.setText("Invalid credentials.");
            }
        });
    }
}
