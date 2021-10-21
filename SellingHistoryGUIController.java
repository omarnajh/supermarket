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
import marktingprograme.DataBase.GetDataForTable.GetDataSellingHistory;
import marktingprograme.DataBase.ImoprtDataFromExel;
import marktingprograme.DataBase.OpOnData.OpOnSellingHistory;
import marktingprograme.DataBase.SellingHistory;
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
public class SellingHistoryGUIController implements Initializable,GetDatainterface {

    private int id;
    private int idInvoice;
    private int idMaterial;
    private String nameemploy;
    private String materialName;
    private String sellingPrice;
    private String countOfSelling;
    private String sellingTotalPrice;
    private String note;
    private String winTotalPrice;
    private String dateOfSelling;
    @FXML
    private TableView<SellingHistory> SellingHistoryTable;

    @FXML
    private TableColumn<SellingHistory, Integer> idcel;

    @FXML
    private TableColumn<SellingHistory, Integer> idInvoicecel;

    @FXML
    private TableColumn<SellingHistory, Integer> idMaterialcel;
    @FXML
    private TableColumn<SellingHistory, String> materialNamecel;

    @FXML
    private TableColumn<SellingHistory, String> sellingPricecel;

    @FXML
    private TableColumn<SellingHistory, String> countOfSellingcel;

    @FXML
    private TableColumn<SellingHistory, String> sellingTotalPricecel;

    @FXML
    private TableColumn<SellingHistory, String> notecel;

    @FXML
    private TableColumn<SellingHistory, String> winTotalPricecel;

    @FXML
    private TableColumn<SellingHistory, String> dateOfSellingcel;

    @FXML
    private TextField idInvoicetext;

    @FXML
    private TextField idMaterialtext;
    @FXML
    private TextField materialNametext;

    @FXML
    private TextField sellingPricetext;

    @FXML
    private TextField countOfSellingtext;

    @FXML
    private TextField sellingTotalPricetext;

    @FXML
    private TextField notetext;

    @FXML
    private TextField winTotalPricetext;

    @FXML
    private TextField dateOfSellingtext;

//    @FXML
//    private Button inputbut;
//
//    @FXML
//    private Button updateBut;
//
//    @FXML
//    private Button DeleteBut;

    @FXML
    private Button serchBut;
    @FXML
    private Label TotalLabel;

    @FXML
    private Label winTotallebal;
    @FXML
    private Button exportdatabut;

    @FXML
    private Button imoprtdatabut;
    
    @FXML
    void exportdatabutEvent(ActionEvent event) {
        ExportDataToExcelFile();
    }

    @FXML
    void imoprtdatabutEvent(ActionEvent event) {
        ImoprtDataFromExel dataFromExel = new ImoprtDataFromExel();
                dataFromExel.SellingHistory();
    }

//    @FXML
//    void DeleteButEvent(ActionEvent event) {
//    if(id>0){
//        getDataFromeText();
//        OpOnSellingHistory op = new OpOnSellingHistory(idInvoice, idMaterial, materialName, sellingPrice, countOfSelling, sellingTotalPrice, note, winTotalPrice, dateOfSelling);
//        op.DeleteData(id);
//        clearDataFromText();
//        refrshSpdata();
//        }
//       else
//        {
//           Alert a =new Alert(Alert.AlertType.ERROR,"اختر الحقل المطلوب", ButtonType.OK);
//             a.showAndWait();
//           }
//    }

//    @FXML
//    void UpdateButEvent(ActionEvent event) {
//     if(id>0){
//        getDataFromeText();
//        OpOnSellingHistory op = new OpOnSellingHistory(idInvoice, idMaterial, materialName, sellingPrice, countOfSelling, sellingTotalPrice, note, winTotalPrice, dateOfSelling);
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

//    @FXML
//    void inputButEvent(ActionEvent event) {
//     if(materialName.length()>0){
//        getDataFromeText();
//        OpOnSellingHistory op = new OpOnSellingHistory(idInvoice, idMaterial,materialName, sellingPrice, countOfSelling, sellingTotalPrice, note, winTotalPrice, dateOfSelling);
//        op.InsertData();
//        clearDataFromText();
//        refrshSpdata();}
//          else
//        {
//           Alert a =new Alert(Alert.AlertType.ERROR,"لاتترك الاسم فارغ", ButtonType.OK);
//             a.showAndWait();
//           }
//    }

    @FXML
    void serchButEvent(ActionEvent event) {
        getDataFromeText();
        OpOnSellingHistory op = new OpOnSellingHistory(idInvoice, idMaterial,materialName, sellingPrice, countOfSelling, sellingTotalPrice, note, winTotalPrice, dateOfSelling);
        SellingHistoryTable.setItems(op.getDataSearch());
        clearDataFromText();
        TotalLabel.setText(""+op.getSumtotal());
       winTotallebal.setText(""+op.getSumwintotal());
      
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intializedata();
    }    
    
     private void intializedata(){
        GetDataSellingHistory data =new GetDataSellingHistory(); 
        idcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,Integer>("id"));
        idInvoicecel.setCellValueFactory( new PropertyValueFactory<SellingHistory,Integer>("idInvoice"));
        idMaterialcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,Integer>("idMaterial"));
        materialNamecel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("materialName"));
        sellingPricecel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("sellingPrice"));
        countOfSellingcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("countOfSelling"));
        sellingTotalPricecel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("sellingTotalPrice"));
        notecel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("note"));
        winTotalPricecel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("winTotalPrice"));
        dateOfSellingcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("dateOfSelling"));
        SellingHistoryTable.setItems(data.getDataTable()); 
         TotalLabel.setText(""+data.getSumtotal());
         winTotallebal.setText(""+data.getSumwintotal());
        SellingHistoryTable.setRowFactory(tv -> {
        TableRow<SellingHistory> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            SellingHistory raw = clickedRow.getItem();
            id=raw.getId();
            idInvoicetext.setText(""+raw.getIdInvoice());
            idMaterialtext.setText(""+raw.getIdMaterial());
            materialNametext.setText(raw.getMaterialName());
            sellingPricetext.setText(raw.getSellingPrice());
            countOfSellingtext.setText(raw.getCountOfSelling());
            sellingTotalPricetext.setText(raw.getSellingTotalPrice());
            notetext.setText(raw.getNote());
            winTotalPricetext.setText(raw.getWinTotalPrice());
            dateOfSellingtext.setText(raw.getDateOfSelling());
           }
        });
        return clickedRow ;
        });
    }

    @Override
    public void getDataFromeText() {
    try{
    idInvoice=Integer.parseInt(idInvoicetext.getText());
    }
    catch(Exception ex)
    {
    idInvoice=0;
    }
    try{
    idMaterial=Integer.parseInt(idMaterialtext.getText());
    
    }
    catch(Exception ex){
    
    idMaterial=0;
    }
    materialName=materialNametext.getText();
    sellingPrice =sellingPricetext.getText();
    countOfSelling=countOfSellingtext.getText();
    sellingTotalPrice=sellingTotalPricetext.getText();
    note=notetext.getText();
    winTotalPrice=winTotalPricetext.getText();
    dateOfSelling=dateOfSellingtext.getText();
    }

    @Override
    public void clearDataFromText() {
    idInvoicetext.setText("");
    idMaterialtext.setText("");
    materialNametext.setText("");
    sellingPricetext.setText("");
    countOfSellingtext.setText("");
    sellingTotalPricetext.setText("");
    notetext.setText("");
    winTotalPricetext.setText("");
    dateOfSellingtext.setText("");
    }

    @Override
    public void refrshSpdata() {
    GetDataSellingHistory data =new GetDataSellingHistory();
    SellingHistoryTable.setItems(data.getDataTable());
    TotalLabel.setText(""+data.getSumtotal());
    winTotallebal.setText(""+data.getSumwintotal());
    }
    
     private void ExportDataToExcelFile(){
    
    Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = (Sheet) workbook.createSheet("تاريخ المبيعات");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < SellingHistoryTable.getColumns().size(); j++) {
            row.createCell(j).setCellValue(SellingHistoryTable.getColumns().get(j).getText());
        }

        for (int i = 0; i < SellingHistoryTable.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < SellingHistoryTable.getColumns().size(); j++) {
                if(SellingHistoryTable.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(SellingHistoryTable.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }
      
        try (FileOutputStream fileOut = new FileOutputStream("E:\\Data\\بيانات تاريخ المبيعات.xls")) {
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
