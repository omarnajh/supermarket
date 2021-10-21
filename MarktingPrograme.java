/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import marktingprograme.Gmail.BackUpDataandsending;

/**
 *
 * @author omar
 */
public class MarktingPrograme extends Application {
   public static Stage mainStage ; 
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginFxml.fxml"));
        Scene scene = new Scene(root);
        mainStage=stage;
        mainStage.setScene(scene);
        mainStage.show();
        mainStage.centerOnScreen();
        mainStage.setTitle("شاشة الدخول");
        mainStage.setResizable(false);
        mainStage.getIcons().add(new Image("file:icon.png"));
      mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
     public void handle(WindowEvent e) {
       getBackup();
     Platform.exit();
     
     System.exit(0);
    }
  });
    }
  private void getBackup(){
     BackUpDataandsending backUpDataandsending = new BackUpDataandsending();
     backUpDataandsending.Backupdbtosql();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        //System.setProperty("prism.text", "t2k");
      System.setProperty("prism.lcdtext", "false");
        launch(args);
    }
  
}
