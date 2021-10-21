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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import marktingprograme.DataBase.GetDataForTable.GetDataInvoices;
import marktingprograme.DataBase.ImoprtDataFromExel;
import marktingprograme.DataBase.Invoices;
import marktingprograme.DataBase.OpOnData.OpOnInvoices;
import marktingprograme.Gmail.Gmailsender;
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
public class RiportSellingFXMLController implements Initializable,GetDatainterface {
 private int id;
    private String dateInvoices;
    private String amontInvoices;
    private String finalTotal;
    private String winTotal;

    @FXML
    private TableView<Invoices> InvoicesTable;

    @FXML
    private TableColumn<Invoices, Integer> idcel;

    @FXML
    private TableColumn<Invoices, String> dateInvoicescel;

    @FXML
    private TableColumn<Invoices, String> amontInvoicescel;
 
    @FXML
    private TableColumn<Invoices, String> finalTotalcel;

    @FXML
    private TableColumn<Invoices, String> winTotalcel;

    @FXML
    private DatePicker dateInvoicestext;

   @FXML
    private DatePicker dateInvoicestextend;

    @FXML
    private Label Totallebal;

    @FXML
    private Label winTotallebal;

 
    @FXML
    private Button inputbut;

    @FXML
    private Button updateBut;

    @FXML
    private Button DeleteBut;

    @FXML
    private Button serchBut;
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
        dataFromExel.Invoices();
    }
    
  

   
    
 
    
   private  boolean  genRulNumber(){
    int number = (int)(Math.random()*10000-1000);
        Gmailsender gmailsender =new Gmailsender();
        gmailsender.sendEmailnumber(""+number);
        TextInputDialog dialog =new TextInputDialog();
        dialog.setTitle("التحقق من رمز التعديل للفانورة :");
        dialog.setHeaderText("ادخل رمز التحقق من اميل الالكتروني هنا:");
        String text =dialog.showAndWait().get();
        return String.valueOf(number).equals(text);
    }

 

    @FXML
    void serchButEvent(ActionEvent event) {
         getDataFromeText();
         OpOnInvoices op =new OpOnInvoices(dateInvoices.replaceAll("-", "/"), amontInvoices, "", "", "", "", "", "", finalTotal, winTotal);
         InvoicesTable.setItems(op.getDateSearch(dateInvoicestextend.getValue().toString().replaceAll("-", "/")));
         clearDataFromText();
        Totallebal.setText(""+op.getSumtotal());
       winTotallebal.setText(""+op.getSumwintotal());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intializedata();
    }    

      private void intializedata(){
        GetDataInvoices data =new GetDataInvoices(); 
        idcel.setCellValueFactory( new PropertyValueFactory<Invoices,Integer>("id"));
        dateInvoicescel.setCellValueFactory( new PropertyValueFactory<Invoices,String>("dateInvoices"));
        amontInvoicescel.setCellValueFactory( new PropertyValueFactory<Invoices,String>("amontInvoices"));
        finalTotalcel.setCellValueFactory( new PropertyValueFactory<Invoices,String>("finalTotal"));
        winTotalcel.setCellValueFactory( new PropertyValueFactory<Invoices,String>("winTotal"));
        InvoicesTable.setItems(data.getDataTable()); 
         Totallebal.setText(""+data.getSumtotal());
         winTotallebal.setText(""+data.getSumwintotal());
        InvoicesTable.setRowFactory(tv -> {
        TableRow<Invoices> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            Invoices raw = clickedRow.getItem();
            }
        });
        return clickedRow ;
        });
    }
    
    @Override
    public void getDataFromeText() {
            dateInvoices=dateInvoicestext.getValue().toString();
    }

    @Override
    public void clearDataFromText() {
        id=0;
   }

    @Override
    public void refrshSpdata() {
        GetDataInvoices data =new GetDataInvoices();
        InvoicesTable.setItems(data.getDataTable());
    }
    
    
   private void ExportDataToExcelFile(){
    
    Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = (Sheet) workbook.createSheet("الفواتير");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < InvoicesTable.getColumns().size(); j++) {
            row.createCell(j).setCellValue(InvoicesTable.getColumns().get(j).getText());
        }

        for (int i = 0; i < InvoicesTable.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < InvoicesTable.getColumns().size(); j++) {
                if(InvoicesTable.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(InvoicesTable.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }
      
        try (FileOutputStream fileOut = new FileOutputStream("E:\\Data\\بيانات تقارير الفواتير.xls")) {
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
