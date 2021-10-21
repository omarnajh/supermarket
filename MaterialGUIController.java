/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme;

import com.jfoenix.controls.JFXDatePicker;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import marktingprograme.DataBase.GetDataForTable.GetDataMaterials;
import marktingprograme.DataBase.GetDataForTable.GetDataTotalMost;
import marktingprograme.DataBase.ImoprtDataFromExel;
import marktingprograme.DataBase.Materials;
import marktingprograme.DataBase.OpOnData.OpOnMaterial;
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
public class MaterialGUIController implements Initializable,GetDatainterface {
    private String barcode;
    private  String  materialName; 
    private  int countAvailable;
    private  int priceGet;
    private  int priceSelling;
    private  String expDate;
    private  String mosName;
    private int colgetprice;
    private String AcountAvailable="0";
    private int id=0;
    
    @FXML
    private Button updateBut;

    @FXML
    private Button DeleteBut;

    @FXML
    private Button serchBut;


    @FXML
    private TextField namematerialtext;

    @FXML
    private TextField countAvailabletext;

    @FXML
    private TextField priceGettext;

    @FXML
    private TextField priceSellingtext;

    @FXML
    private  JFXDatePicker expDatetext;
    
    @FXML
    private TextField colgetPrice;
    
    @FXML
    private TextField barcodeText;
     
    @FXML
    private ComboBox mosNametext;
    
    @FXML
    private TableView<Materials> materialTabel;

    @FXML
    private TableColumn<Materials, Integer> idcel;
    @FXML
    private TableColumn<Materials, String>  barcodecel;
    @FXML
    private TableColumn<Materials, String> materialNamecel;
    @FXML
    private TableColumn<Materials, String> countAvailablecel;
    @FXML
    private TableColumn<Materials, String> priceGetcel;
    @FXML
    private TableColumn<Materials, String> priceSellingcel;
    @FXML
    private TableColumn<Materials, String> expDatecel;
    @FXML
    private TableColumn<Materials, String> mosNamecel;
     @FXML
    private TableColumn<Materials, String> colgetPricecel;
     @FXML
    private Button importdatabut;

    @FXML
    private Button inputbut;
    @FXML
    private Button exportdatabut;
   @FXML
   private Label totalPricecolget;

    @FXML
    void exportdatabutEvent(ActionEvent event) {
      ExportDataToExcelFile();
    }
 

    @FXML
    void DeleteButEvent(ActionEvent event) {
        getDataFromeText();
        OpOnMaterial op = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDate, mosName,colgetprice);       
        materialTabel.setItems(op.DeleteData());
        clearDataFromText();
     
    }
    
      @FXML
    void searchfelidEvent(KeyEvent event) {
     getDataFromeText();
        OpOnMaterial op = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDate, mosName,colgetprice);
        materialTabel.setItems(op.getDataSearch());
     
    }
    @FXML
    void importdatabutEvent(ActionEvent event) {
        ImoprtDataFromExel imdx =new ImoprtDataFromExel();
        imdx.Materials();
    }
    @FXML
       void find_expier_butEvent(ActionEvent event) {
        getDataFromeText();
        OpOnMaterial op = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDate, mosName,colgetprice);
        materialTabel.setItems(op.getDataSearchdate(expDatetext.getValue().toString().replaceAll("-", "/")));
      
    }
    
    
    @FXML
    void UpdateButEvent(ActionEvent event) {
        if(id>0){
        getDataFromeText();
        if(getCheak()){
        try{
      
        OpOnMaterial op = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDate, mosName,colgetprice);
        op.UpDateData(id);
        clearDataFromText();
        refrshSpdata();}
          catch(Exception ex){
        Alert a =new Alert(Alert.AlertType.ERROR,"لاتضع اي حرف في حقل الكمية : سعر الشراء:سعر البيع  ", ButtonType.OK);
             a.showAndWait();
        }
        }}
       else
        {
           Alert a =new Alert(Alert.AlertType.ERROR,"اختر الحقل المطلوب", ButtonType.OK);
             a.showAndWait();
           }
    }

    @FXML
    void serchButEvent(ActionEvent event) {
        getDataFromeText();
       
        OpOnMaterial op = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDatetext.getValue().toString().replaceAll("-", "/"), mosName, colgetprice);
        materialTabel.setItems(op.getDataSearch());
        clearDataFromText();
      
    }
    
    @FXML
    void inputButEvent(ActionEvent event) {
        getDataFromeText();
        if(materialName.length()>0){
        if(getCheak()){
            
        try{

        OpOnMaterial op = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDatetext.getValue().toString().replaceAll("-", "/"), mosName,colgetprice);
        op.InsertData();
        clearDataFromText();
        refrshSpdata();}
         catch(Exception ex){
        Alert a =new Alert(Alert.AlertType.ERROR,"لاتضع اي حرف في حقل الكمية : سعر الشراء:سعر البيع  ", ButtonType.OK);
             a.showAndWait();
        }}
        }
          else
        {
           Alert a =new Alert(Alert.AlertType.ERROR," ", ButtonType.OK);
             a.showAndWait();
           }
    }
    
    @FXML
    void BarcodeEvent(KeyEvent event) {
       if(event.getCode()==KeyCode.ENTER){
        OpOnMaterial op = new OpOnMaterial(barcodeText.getText(),"1nom", countAvailable, priceGet, priceSelling, expDate, mosName,colgetprice);
        materialTabel.setItems(op.getDataSearch());
        clearDataFromText();
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OpOnMaterial op = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDate, mosName,colgetprice);
        op.refreshData();
        intializedata();
        setDataOncompo();
       
    }    
    
        private void intializedata(){
        GetDataMaterials data =new GetDataMaterials(); 
        idcel.setCellValueFactory( new PropertyValueFactory<Materials,Integer>("id"));
        barcodecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("barcode"));
        materialNamecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("materialName"));
        countAvailablecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("countAvailable"));
        priceGetcel.setCellValueFactory( new PropertyValueFactory<Materials,String>("priceGet"));
        priceSellingcel.setCellValueFactory( new PropertyValueFactory<Materials,String>("priceSelling"));
        expDatecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("expDate"));
        mosNamecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("mosName"));        
        colgetPricecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("colgetprice"));
        totalPricecolget.setText(data.getSum()+"");
        materialTabel.setItems(data.getDataTable()); 
        materialTabel.setRowFactory(tv -> {
        TableRow<Materials> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            Materials raw = clickedRow.getItem();
            id=raw.getId();
            barcodeText.setText(raw.getBarcode());
            namematerialtext.setText(raw.getMaterialName());
            priceGettext.setText(raw.getPriceGet()+"");
            priceSellingtext.setText(raw.getPriceSelling()+"");
            AcountAvailable="0";
            AcountAvailable=""+(raw.getCountAvailable());
            expDatetext.setValue(LOCAL_DATE(raw.getExpDate()));
            mosNametext.setValue(raw.getMosName());
            colgetPrice.setText(raw.getColgetprice()+"");
           }
        });
        return clickedRow ;
        });
        totalPricecolget.setText(data.getSum()+"");
        countAvailabletext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               if (!newValue.matches("\\d*")) {
               countAvailabletext.setText(newValue.replaceAll("[^\\d]", ""));
        }
            }
        });
       priceGettext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              if (!newValue.matches("\\d*")) {
               priceGettext.setText(newValue.replaceAll("[^\\d]", ""));
              }
            }
        });
       priceSellingtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              if (!newValue.matches("\\d*")) {
               priceSellingtext.setText(newValue.replaceAll("[^\\d]", ""));
               }
            }
        });
       colgetPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              if (!newValue.matches("\\d*")) {
               colgetPrice.setText(newValue.replaceAll("[^\\d]", ""));
                       }
            }
        });
    }

    @Override
    public void getDataFromeText() {
       barcode =barcodeText.getText();
       materialName= namematerialtext.getText();
       try{
       countAvailable=Integer.parseInt(countAvailabletext.getText());
       priceGet=Integer.parseInt(priceGettext.getText());
       priceSelling =Integer.parseInt(priceSellingtext.getText());
       colgetprice=Integer.parseInt(colgetPrice.getText());
        expDate=expDatetext.getValue().toString().replaceAll("-", "/");
       }catch(Exception ex)
       {
       expDate="";
       }

       try{
      if( mosNametext.getSelectionModel().getSelectedItem().toString().length()>0)
       mosName= mosNametext.getSelectionModel().getSelectedItem().toString();
      else
          mosName="";
       }catch(Exception e){
       mosName="";
       }
    }
    
         @FXML
    void onchengenent(KeyEvent event) {
       int x=0;
       int y =0;
       try{
           x=Integer.parseInt(countAvailabletext.getText());
       }catch(Exception ex)
       {
       x=0;
       }
       try{
           y=Integer.parseInt(priceGettext.getText());
       }catch(Exception ex)
       {
       y=0;
       }
       
       int sum = x*y;
       colgetPrice.setText(sum+"");
     
    }

 
    @Override
    public void refrshSpdata(){
    try{
        AcountAvailable="0";
     GetDataMaterials data =new GetDataMaterials();
     materialTabel.setItems(data.getDataTable());
     totalPricecolget.setText(data.getSum()+"");
    }catch(Exception ex){
    System.out.println(ex.toString());
    }
    }
    
    @Override
    public void clearDataFromText() {
        id=0;
        barcodeText.setText("");
        namematerialtext.setText("");
        countAvailabletext.setText("");
        priceGettext.setText("");
        priceSellingtext.setText("");
        expDatetext.setValue(LOCAL_DATE(""));
        colgetPrice.setText("");
    }
    
   public  LocalDate LOCAL_DATE (String dateString){
       try{
     if(dateString.length()>1){
    LocalDate localDate = LocalDate.parse(dateString);
    return localDate; 
     }
     else{
         return LocalDate.now(); 
     }}catch(Exception ex){
     return LocalDate.now();
     }
}
    private void setDataOncompo(){
    GetDataTotalMost data =new GetDataTotalMost();
   mosNametext.getItems().removeAll(mosNametext.getItems());
   mosNametext.setItems(FXCollections.observableArrayList(data.getDataArray()));
   
    }
    
    private boolean getCheak(){
    try{
        
        
    if((Integer.parseInt(priceGettext.getText())>Integer.parseInt(priceSellingtext.getText()))||countAvailable<0){
              Alert a =new Alert(Alert.AlertType.WARNING,"لايمكن ان يكون سعر البيع اقل من سعر الشراء", ButtonType.OK);
             a.setTitle("تحذير غلط في السعر");
             a.setHeaderText("تحذير");
             a.showAndWait();
    return false;}
    }
    catch(Exception e){
       
    }
    return true;
    }
    
   private void ExportDataToExcelFile(){
    
    Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = (Sheet) workbook.createSheet("المواد");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < materialTabel.getColumns().size(); j++) {
            row.createCell(j).setCellValue(materialTabel.getColumns().get(j).getText());
        }

        for (int i = 0; i < materialTabel.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < materialTabel.getColumns().size(); j++) {
                if(materialTabel.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(materialTabel.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }
      
        try (FileOutputStream fileOut = new FileOutputStream("E:\\Data\\بيانات المواد.xls")) {
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
