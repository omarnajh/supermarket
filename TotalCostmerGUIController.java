/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import marktingprograme.DataBase.GetDataForTable.GetDataTotalCostmer;
import marktingprograme.DataBase.ImoprtDataFromExel;
import marktingprograme.DataBase.OpOnData.OpOnTotalCostmer;
import marktingprograme.DataBase.TotalCostmer;
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
public class TotalCostmerGUIController implements Initializable ,GetDatainterface{

    private int id ;
    private String  costmerName;
    private String costmerPhone;
    private String costmerId;
    private String costmerDate;
    private String costmerTotalMony;
    private String payType;
    private String payNote;
    
    @FXML
    private TableView<TotalCostmer> TotalCostmerTabel;

    @FXML
    private TableColumn<TotalCostmer, Integer> idcel;

    @FXML
    private TableColumn<TotalCostmer, String> costmerNamecel;

    @FXML
    private TableColumn<TotalCostmer, String> costmerPhonecel;

    @FXML
    private TableColumn<TotalCostmer, String> costmerIdcel;

    @FXML
    private TableColumn<TotalCostmer, String> costmerDatecel;

    @FXML
    private TableColumn<TotalCostmer, String> costmerTotalMonycel;

    @FXML
    private TableColumn<TotalCostmer, String> payTypecel;

    @FXML
    private TableColumn<TotalCostmer, String> payNotecel;

    @FXML
    private TextField idtext;

    @FXML
    private TextField costmerNametext;

    @FXML
    private TextField costmerPhonetext;

    @FXML
    private TextField costmerIdtext;

    @FXML
    private TextField costmerDatetext;

    @FXML
    private TextField costmerTotalMonytext;

    @FXML
    private TextField payTypetext;

    @FXML
    private TextField payNotetext;

    @FXML
    private Button inputbut;

    @FXML
    private Button updateBut;

  
    @FXML
    private Button serchBut;
     @FXML
    private Button expotdatabut;
     
    @FXML
    private Label totalmony;

    @FXML
    private Button imoprtdatabut;
        @FXML
    void expotdatabutEvent(ActionEvent event) {
         ExportDataToExcelFile();
    }

    @FXML
    void imoprtdatabutEvent(ActionEvent event) {
        ImoprtDataFromExel  dataFromExel =new ImoprtDataFromExel();
        dataFromExel.TotalCostmer();
    }


 

    @FXML
    void UpdateButEvent(ActionEvent event) {
      getDataFromeText();
      if(id>0){
         OpOnTotalCostmer op= new OpOnTotalCostmer(costmerName, costmerPhone, costmerId, costmerDate, costmerTotalMony, payType, payNote);
        op.UpDateData(id);
        clearDataFromText();
        refrshSpdata();
        }
       else
        {
           Alert a =new Alert(Alert.AlertType.ERROR,"اختر الحقل المطلوب", ButtonType.OK);
             a.showAndWait();
           }
    }

    @FXML
    void inputButEvent(ActionEvent event) {
      if(costmerNametext.getText().length()>0){
        getDataFromeText();
        OpOnTotalCostmer op= new OpOnTotalCostmer(costmerName, costmerPhone, costmerId, costmerDate, costmerTotalMony, payType, payNote);
        op.InsertData();
        clearDataFromText();
        refrshSpdata();}
          else
        {
           Alert a =new Alert(Alert.AlertType.ERROR,"لاتترك الاسم فارغ", ButtonType.OK);
             a.showAndWait();
           }
    }

    @FXML
    void serchButEvent(ActionEvent event) {
       getDataFromeText();
       OpOnTotalCostmer op= new OpOnTotalCostmer(costmerName, costmerPhone, costmerId, costmerDate, costmerTotalMony, payType, payNote);
        TotalCostmerTabel.setItems(op.getDataSearch());
        clearDataFromText();
        totalmony.setText(op.getSum()+"");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intializedata();
    }    

    private void intializedata(){
         GetDataTotalCostmer data =new GetDataTotalCostmer(); 
         totalmony.setText(data.getSum()+"");
        idcel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,Integer>("id"));
        costmerNamecel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("costmerName"));
        costmerPhonecel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("costmerPhone"));
        costmerIdcel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("costmerId"));
        costmerDatecel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("costmerDate"));
        costmerTotalMonycel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("costmerTotalMony"));
        payTypecel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("payType"));
        payNotecel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("payNote"));
        TotalCostmerTabel.setItems(data.getDataTable()); 
        TotalCostmerTabel.setRowFactory(tv -> {
        TableRow<TotalCostmer> clickedRow = new TableRow<>();
        
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            TotalCostmer raw = clickedRow.getItem();
            id=raw.getId();
            idtext.setText(""+raw.getId());
            costmerNametext.setText(""+raw.getCostmerName());
            costmerPhonetext.setText(raw.getCostmerPhone());
            costmerIdtext.setText(raw.getCostmerId());
            costmerDatetext.setText(raw.getCostmerDate());
            costmerTotalMonytext.setText(raw.getCostmerTotalMony());
            payTypetext.setText(raw.getPayType());
            payNotetext.setText(raw.getPayNote());
            }
        });
        return clickedRow ;
        });
    }
    @Override
    public void getDataFromeText() {
           costmerName= costmerNametext.getText();
            costmerPhone=costmerPhonetext.getText();
            costmerId=costmerIdtext.getText();
            costmerDate=costmerDatetext.getText();
            costmerTotalMony=costmerTotalMonytext.getText();
            payType=payTypetext.getText();
            payNote=payNotetext.getText();}

    @Override
    public void clearDataFromText() {
            idtext.setText("");
            costmerNametext.setText("");
            costmerPhonetext.setText("");
            costmerIdtext.setText("");
            costmerDatetext.setText("");
            costmerTotalMonytext.setText("");
            payTypetext.setText("");
            payNotetext.setText("");}

    @Override
    public void refrshSpdata() {
    GetDataTotalCostmer data =new  GetDataTotalCostmer();
    totalmony.setText(data.getSum()+"");
    TotalCostmerTabel.setItems(data.getDataTable());
    }
     private void ExportDataToExcelFile(){
    
    Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = (Sheet) workbook.createSheet("الزبائن");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < TotalCostmerTabel.getColumns().size(); j++) {
            row.createCell(j).setCellValue(TotalCostmerTabel.getColumns().get(j).getText());
        }

        for (int i = 0; i < TotalCostmerTabel.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < TotalCostmerTabel.getColumns().size(); j++) {
                if(TotalCostmerTabel.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(TotalCostmerTabel.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }
      
        try (FileOutputStream fileOut = new FileOutputStream("E:\\Data\\بيانات الزبائن.xls")) {
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
