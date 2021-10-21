/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import marktingprograme.DataBase.Create_DataBaseMain;
import marktingprograme.DataBase.OpOnData.CheckData;
import marktingprograme.Gmail.Gmailsender;
import static marktingprograme.MarktingPrograme.mainStage;


/**
 *
 * @author omar
 */
public class LoginFxmlController implements Initializable {
    private int  i =0;
    private int  randomnumber =0;
public static String nameusermark="";


 @FXML
    private JFXTextField _usernameText;

    @FXML
    private JFXPasswordField _passowrdText;

    @FXML
    private JFXCheckBox _checkBox;

    @FXML
    private JFXTextField _emailText;

    @FXML
    private JFXButton _enterBut;

    @FXML
    private JFXButton _exitBut;
      @FXML
    private FontAwesomeIconView _iconemail;
    
    @FXML
    void exitButevent(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void loginButevent(ActionEvent event) {
        CheckData cd =new CheckData();
        if(cd.getData(_usernameText.getText(), _passowrdText.getText())&& i>0){
            showwindow();
        nameusermark=cd.getName();
        }
        else if(i==0&& randomnumber == 0)
        {
         randomnumber =genRulNumber(_emailText.getText());
         _emailText.setVisible(false);
        }
        else if(i==0 && randomnumber>0){
        try{
        int nametextno = Integer.parseInt(_usernameText.getText());
        int passwordno = Integer.parseInt(_passowrdText.getText());
        if(randomnumber==nametextno && randomnumber==passwordno)
        showwindow();
        }catch(Exception ex){
        
        }
        }
        
    }
    
        @FXML
    void onchecked(ActionEvent event) {
      if(_checkBox.isSelected()){
      _emailText.setVisible(true);
      _iconemail.setVisible(true);
      }
      else{
      _emailText.setVisible(false);
      _iconemail.setVisible(false);
      }
    }
    
    private void showwindow(){
     showStage("MainGUIMarkting.fxml","واجهة اختيار النوافذ ");   
       
    }
 
      private  int  genRulNumber(String email){
       int number = (int)(Math.random()*10000-1000);
        Gmailsender gmailsender =new Gmailsender(email,"");
        gmailsender.sendEmailnumber(""+number);
        return number;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CreateData();
       // setNameArbic();
        getsizeofdata();
    }    
    
    private void getsizeofdata(){
    CheckData cd =new CheckData();
    cd.getData("", "");
    i=cd.getSizeof();
    if(i>0)
    {
     // emaillabel.setVisible(false);
      _emailText.setVisible(false);
    }
    }
    private void CreateData(){
        Create_DataBaseMain data= new Create_DataBaseMain();
    }
    
    public void showStage(String filepath,String Titel)  {
        Parent root;
        try {
                root = FXMLLoader.load(getClass().getResource(filepath));
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.setTitle(Titel);
                mainStage.show();
                mainStage.setResizable(true);
                getSecreenSize();
                mainStage.setHeight(height);
                mainStage.setWidth(width);
                mainStage.centerOnScreen();
                
        } catch (IOException ex) {
        }
   
    }
  
    
   
      private void getSecreenSize(){
    Toolkit tool =Toolkit.getDefaultToolkit();
    width = tool.getScreenSize().width;
    height=tool.getScreenSize().height;
    }
  
  private int width;
  private int height;
}
