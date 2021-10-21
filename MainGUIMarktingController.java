/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import marktingprograme.DataBase.GetDataForTable.GetDataSettingSystem;
import marktingprograme.DataBase.SettingSystem;
import marktingprograme.Gmail.BackUpDataandsending;
import marktingprograme.Gmail.Gmailsender;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class MainGUIMarktingController implements Initializable {
     @FXML 
     private Label LabelTital;
     @FXML 
     private Button Materialbut;
     @FXML 
     private Button Mostbut;
     @FXML 
     private Button Invoicesbut;
//     @FXML 
//     private Button Employesbut;
     @FXML 
     private Button SellingHistorybut;
     @FXML 
     private Button SettingSystembut;
     @FXML 
     private Button TotalCostmerbut;
     @FXML 
     private Button TotalExpensesbut;
     @FXML 
     private Button TotalPremiumsbut;
     @FXML 
     private Button TotalePurchasesbut;
     @FXML 
     private Button TotalPoshbut;
     @FXML 
     private Button PayBut;
     @FXML 
     private Button cobyof;
     @FXML 
     private Button pastof;
     @FXML
    private Button showinvoicesbut;
     @FXML
    void showinvoicesbutEvent(ActionEvent event) {
     showStage("InvoicesViewData.fxml","واجهة عرض الفواتير ");
    }
    
    @FXML
    void EmployesbutEvent(ActionEvent event) {
     showStage("EmployesGUI.fxml","واجهة ادارة المستخدمين");
    }
    @FXML
    void InvoicesbutEvent(ActionEvent event) {
     showStage("InvoicesGUI.fxml","واجهة الفواتير ");
    }

    @FXML
    void MaterialbutEvent(ActionEvent event) {
     showStage("MaterialGUI.fxml","واجهة ادخال المواد للمخزن");
    }

    @FXML
    void PayButEvent(ActionEvent event) {
        
     showStage("PayGUICenter.fxml","واجهة المبيعات");
    }

    @FXML
    void SellingHistorybutEvent(ActionEvent event) {
     showStage("SellingHistoryGUI.fxml","وادجهة تاريخ المبيعات  ");
    }

    @FXML
    void SettingSystembutEvent(ActionEvent event) {
      showStage("SettingSystemGUI.fxml","وادجهة اعدادات النظام  ");
    }

    @FXML
    void TotalCostmerbutEvent(ActionEvent event) {
          showStage("TotalCostmerGUI.fxml","وادجهة الزبائن ");
    }
    @FXML
    void MostbutEvent(ActionEvent event) {
      showStage("MostGui.fxml","واجهة ادخال المواد للمخزن");
    }

    @FXML
    void TotalExpensesbutEvent(ActionEvent event) {
        showStage("TotalExpensesGUI.fxml","واجهة المصروفات");
    }

    @FXML
    void TotalPremiumsbutEvent(ActionEvent event) {

        showStage("TotalPremiumsGUI.fxml","واجهة الاقساط");
    }

    @FXML
    void TotalePurchasesbutEvent(ActionEvent event) {
     showStage("TotalePurchasesGUI.fxml","واجهة الموردين");
    }
     @FXML
    void TotalePoshbutEvent(ActionEvent event) {
     showStage("TotalPoshGUI.fxml","واجهة  الديون على محل");
    }
    
    @FXML
    void TotaleRiportSellingEvent(ActionEvent event) {
     showStage("RiportSellingFXML.fxml","واجهة تقارير الارباح");
    }
    
    
     @FXML
    void RiportMaterialsellingFXMLEvent(ActionEvent event) {
     showStage("RiportMaterialsellingFXML.fxml","واجهة تقارير حركة المواد ونشاطها");
    }
    @FXML
    void copyOfEvent(ActionEvent event) {
    getBackup();
    }
    
    @FXML
    void pastOfEvent(ActionEvent event) {
    setBackup();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTital();
    }    
    
    private void getTital(){
        GetDataSettingSystem gdss = new GetDataSettingSystem();
        for(int i=0;i<gdss.getDataTable().size();i++)
        {
          SettingSystem system =gdss.getDataTable().get(i);
          LabelTital.setText(system.getCenterName());
        }       
    }
    
    
    public void showStage(String filepath,String Titel)  {
        Parent root;
        try {
                Stage stage =new Stage();
                getSecreenSize();
                root = FXMLLoader.load(getClass().getResource(filepath));
                Scene scene = new Scene(root);
                stage.setResizable(true);
                stage.setScene(scene);
                stage.setHeight(height);
                stage.setWidth(width);
                stage.centerOnScreen();
                stage.setTitle(Titel);
                stage.show();
                
        } catch (IOException ex) {
        }
   
    }
  
  private void getBackup(){
     BackUpDataandsending backUpDataandsending = new BackUpDataandsending();
     backUpDataandsending.Backupdbtosql();
    }
    private void setBackup(){
     if( genRulNumber()){
     BackUpDataandsending backUpDataandsending = new BackUpDataandsending();
     backUpDataandsending.Restoredbfromsql();}
     else
        {
           Alert a =new Alert(Alert.AlertType.ERROR,"رمز التحقق خطأ", ButtonType.OK);
             a.showAndWait();
           }
    }
    
   private  boolean  genRulNumber(){
    int number = (int)(Math.random()*10000-1000);
        Gmailsender gmailsender =new Gmailsender();
        gmailsender.sendEmailnumber(""+number);
        TextInputDialog dialog =new TextInputDialog();
        dialog.setTitle("تحقق :");
        dialog.setHeaderText("ادخل رمز التحقق من اميل الالكتروني هنا:");
        String text =dialog.showAndWait().get();
        if( String.valueOf(number).equals(text)){
        return true;
        }
        else
            return false;
    }
  private void getSecreenSize(){
    Toolkit tool =Toolkit.getDefaultToolkit();
    width = tool.getScreenSize().width;
    height=tool.getScreenSize().height;
    }
  
  private int width;
  private int height;
  
}
