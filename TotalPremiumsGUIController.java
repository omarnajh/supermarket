/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import marktingprograme.DataBase.GetDataForTable.GetDataTotalPremiums;
import marktingprograme.DataBase.ImoprtDataFromExel;
import marktingprograme.DataBase.OpOnData.OpOnTotalCostmer;
import marktingprograme.DataBase.OpOnData.OpOnTotalPremiums;
import marktingprograme.DataBase.TotalPremiums;
import marktingprograme.InterFaceFolder.GetDatainterface;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class TotalPremiumsGUIController implements Initializable ,GetDatainterface{

    private int id ;
    private int  idIvoices; 
    private String costmerName;
    private String premiumsDate;
    private String premiumsposh;
    private String settingsystem;
    
    @FXML
    private TableView<TotalPremiums> TotalPremiumsTabel;

    @FXML
    private TableColumn<TotalPremiums, Integer> idcel;

    @FXML
    private TableColumn<TotalPremiums, Integer> idIvoicescel;

    @FXML
    private TableColumn<TotalPremiums, String> costmerNamecel;

    @FXML
    private TableColumn<TotalPremiums, String> premiumsDatecel;

    @FXML
    private TableColumn<TotalPremiums, String> premiumsposhcel;
    @FXML
    private TableColumn<TotalPremiums, String> settingsystemcel;

    @FXML
    private TextField idtext;

    @FXML
    private TextField idIvoicestext;

    @FXML
    private TextField costmerNametext;

    @FXML
    private TextField premiumsDatetext;

    @FXML
    private TextField premiumsposhtext;
    @FXML
    private TextField settingsystemtext;

    @FXML
    private Button inputbut;

//    @FXML
//    private Button updateBut;


    @FXML
    private Button serchBut;
    @FXML 
    private Button getDatetotextBut;
    @FXML
    private Button exportdatabut;

    @FXML
    private Button importdatabut;

    @FXML
    void exportdatabutEvent(ActionEvent event) {
      ExportDataToExcelFile();
    }

    @FXML
    void importdatabutEvent(ActionEvent event) {
        ImoprtDataFromExel dataFromExel =new ImoprtDataFromExel();
        dataFromExel.TotalPremiums();
    }
  
//
//    @FXML
//    void UpdateButEvent(ActionEvent event) {
//   getDataFromeText();
//      if(id>0){
//        OpOnTotalPremiums op =new OpOnTotalPremiums(idIvoices, costmerName, premiumsDate, premiumsposh,settingsystem);
//        op.UpDateData(id);
//        clearDataFromText();
//        refrshSpdata();
//        }
//       else
//        {
//           Alert a =new Alert(Alert.AlertType.ERROR,"اختر الحقل المطلوب", ButtonType.OK);
//             a.showAndWait();
//           }
//    }

    @FXML
    void inputButEvent(ActionEvent event) {
    if(costmerNametext.getText().length()>0){
        getDataFromeText();
        if(getTotalMonyafter()){
        OpOnTotalPremiums op =new OpOnTotalPremiums(idIvoices, costmerName, premiumsDate, premiumsposh,settingsystem);
        op.InsertData();
        OpOnTotalCostmer costmer =new OpOnTotalCostmer(costmerName, "", "", "", idIvoices+"", "","");
        costmer.UpDateDataprem(costmerName);
        clearDataFromText();
        refrshSpdata();}}
          else
        {
           Alert a =new Alert(Alert.AlertType.ERROR,"لاتترك الاسم فارغ", ButtonType.OK);
             a.showAndWait();
           }
    }
     @FXML
    void getDateToText(ActionEvent event) {
         String timeStamp = new SimpleDateFormat("yyyy/MM/dd:HH_mm_ss").format(Calendar.getInstance().getTime());
        premiumsDatetext.setText(timeStamp);
    }
 
    
    @FXML
    void serchButEvent(ActionEvent event) {
       getDataFromeText();
        OpOnTotalPremiums op =new OpOnTotalPremiums(idIvoices, costmerName, premiumsDate, premiumsposh,settingsystem);
       TotalPremiumsTabel.setItems(op.getDataSearch());
        clearDataFromText();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        
//        OpOnTotalPremiums op = new OpOnTotalPremiums(0, "", "", "","");
//        Platform.runLater( 
//                 new Runnable() {
//            public void run() {
//             op.getCleant(); 
//             intializedata();
//            }
//        });
//          
//        
        
     intializedata();
    }    

      @FXML
    void SerchDataCostmerEvent(KeyEvent event) {
        OpOnTotalPremiums opOnTotalCostmer =new OpOnTotalPremiums(0,costmerNametext.getText(), null, null,"");
        TotalPremiumsTabel.setItems(opOnTotalCostmer.getDataSearch());
    }
    
    private void intializedata(){
         GetDataTotalPremiums data =new GetDataTotalPremiums(); 
        idcel.setCellValueFactory( new PropertyValueFactory<TotalPremiums,Integer>("id"));
        idIvoicescel.setCellValueFactory( new PropertyValueFactory<TotalPremiums,Integer>("idIvoices"));
        costmerNamecel.setCellValueFactory( new PropertyValueFactory<TotalPremiums,String>("costmerName"));
        premiumsDatecel.setCellValueFactory( new PropertyValueFactory<TotalPremiums,String>("premiumsDate"));
        premiumsposhcel.setCellValueFactory( new PropertyValueFactory<TotalPremiums,String>("premiumsposh"));
        settingsystemcel.setCellValueFactory( new PropertyValueFactory<TotalPremiums,String>("settingsystem"));
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd:HH_mm_ss").format(Calendar.getInstance().getTime());
        premiumsDatetext.setText(timeStamp);
        TotalPremiumsTabel.setItems(data.getDataTable()); 
        settingsystemtext.setText(LoginFxmlController.nameusermark);
        TotalPremiumsTabel.setRowFactory(tv -> {
        TableRow<TotalPremiums> clickedRow = new TableRow<>();
        
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            TotalPremiums raw = clickedRow.getItem();
            id=raw.getId();
            idtext.setText(""+raw.getId());
            idIvoicestext.setText(""+raw.getIdIvoices());
            costmerNametext.setText(raw.getCostmerName());
            premiumsDatetext.setText(raw.getPremiumsDate());
            premiumsposhtext.setText("");
            settingsystemtext.setText(LoginFxmlController.nameusermark);
            }
        });
        return clickedRow ;
        });
    }
    
    @Override
    public void getDataFromeText() {
              try{
                idIvoices=Integer.parseInt(idIvoicestext.getText());
              }catch(Exception ex){
              idIvoices=0;
              }
            costmerName=costmerNametext.getText();
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd:HH_mm_ss").format(Calendar.getInstance().getTime());
            premiumsDate=timeStamp;
            premiumsposh=premiumsposhtext.getText();
            settingsystem=LoginFxmlController.nameusermark;
            }

    @Override
    public void clearDataFromText() {
            idIvoicestext.setText("");
            costmerNametext.setText("");
            premiumsDatetext.setText("");
            premiumsposhtext.setText("");
            settingsystemtext.setText("");
             }

    
    
    private boolean getTotalMonyafter(){
    try{
      if(idIvoices>=(Integer.parseInt(premiumsposh))){
      idIvoices =idIvoices-Integer.parseInt(premiumsposh);
      return true;
      }
      else{
       Alert a =new Alert(Alert.AlertType.WARNING,"لا يمكن ان يكون سعر التسديد اعلى من سعر الدين", ButtonType.OK);
             a.showAndWait();
             return false;
      }
    }
    catch(Exception ex){
         Alert a =new Alert(Alert.AlertType.WARNING," حقل التسديد يجب ان يحتوي الارقام فقط", ButtonType.OK);
             a.showAndWait();
    return false;
     }
    }
    
    @Override
    public void refrshSpdata() {
    GetDataTotalPremiums data =new GetDataTotalPremiums();
    TotalPremiumsTabel.setItems(data.getDataTable());
    }
    
     private void ExportDataToExcelFile(){
    
    Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = (Sheet) workbook.createSheet("الفواتير");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < TotalPremiumsTabel.getColumns().size(); j++) {
            row.createCell(j).setCellValue(TotalPremiumsTabel.getColumns().get(j).getText());
        }

        for (int i = 0; i < TotalPremiumsTabel.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < TotalPremiumsTabel.getColumns().size(); j++) {
                if(TotalPremiumsTabel.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(TotalPremiumsTabel.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }
      
        try (FileOutputStream fileOut = new FileOutputStream("E:\\Data\\بيانات الفواتير.xls")) {
            workbook.write(fileOut);
            workbook.close();
              Alert a =new Alert(Alert.AlertType.INFORMATION,"تم الحفظ", ButtonType.OK);
             a.showAndWait(); 
           }catch(IOException ex){
                   Alert a =new Alert(Alert.AlertType.ERROR,ex.toString().trim(), ButtonType.OK);
             a.showAndWait(); 
      }
    } 
}
