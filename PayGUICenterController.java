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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import marktingprograme.DataBase.GetDataForTable.GetDataMaterials;
import marktingprograme.DataBase.GetDataForTable.GetDataTotalMost;
import marktingprograme.DataBase.Materials;
import marktingprograme.DataBase.OpOnData.OpOnMaterial;
import marktingprograme.DataBase.OpOnData.OpOnTotalePurchases;
import marktingprograme.DataBase.cashInfo;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class PayGUICenterController implements Initializable {
    private int id=0;
     private String barcode;
    private  String  materialName; 
    private  int countAvailable;
    private  int priceGet;
    private  int priceSelling;
    private  String expDate;
    private  String mosName;
    
    private ObservableList<Materials> datapay = FXCollections.observableArrayList();
    
    private  ObservableList<Materials> datapaysum = FXCollections.observableArrayList();
    private  ObservableList<cashInfo> datacash = FXCollections.observableArrayList();
    private String TotalPrice="";
    private String TotalPriceWin="";
    private int countbeforPay=0;
    @FXML
    private TableView<Materials> PayTabel;
    private int index=1;
    private int sumwintotal =0;
    private int sumtotal =0;

    @FXML
    private TableColumn<Materials, Integer> idcel;

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
    private Button PayBut;

    @FXML
    private TableView<Materials> materialTabel;

    @FXML
    private TableColumn<Materials, Integer> midcel;

    @FXML
    private TableColumn<Materials, String> mmaterialNamecel;

    @FXML
    private TableColumn<Materials, Integer> mcountAvailablecel;

    @FXML
    private TableColumn<Materials, Integer> mpriceGetcel;

    @FXML
    private TableColumn<Materials, Integer> mpriceSellingcel;

    @FXML
    private TableColumn<Materials, String> mexpDatecel;

    @FXML
    private TableColumn<Materials, String> mmosNamecel;
    
    @FXML
    private TableColumn<Materials, String> barcodecel;

    @FXML
    private TextField namematerialtext;

    @FXML
    private TextField countAvailabletext;

    @FXML
    private TextField priceGettext;

    @FXML
    private TextField priceSellingtext;

    @FXML
    private TextField expDatetext;

    @FXML
    private ComboBox mosNametext;
    
    @FXML
    private TextField TotalText;
    
    @FXML
    private TextField barcodeText;

    @FXML
    private TextField wintotalText;


    @FXML
    private Button AddBut;
    
    @FXML
    private Button Addmaterial;
    
    @FXML
    private Button exitbut;
    
    @FXML
    void exitbutEvent(ActionEvent event) {
        datapay.clear();
        datacash.clear();
        PayTabel.getItems().removeAll();
               getSumofall();
    }
    
    @FXML
    void AddButMaterialEvent(ActionEvent event) {

        if(namematerialtext.getText().length()>0){
                getDataFromeText();
                OpOnMaterial op = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDate, mosName,0);
                op.InsertData();
                OpOnMaterial opn = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDate, mosName,0);
                opn.getDataSearchname(materialName);
                refrshSpdata();
           }
          else
        {
           Alert a =new Alert(Alert.AlertType.ERROR,"لا تترك اسم المادة فارغ ", ButtonType.OK);
             a.showAndWait();
           }
    
    }

    @FXML
    void AddButEvent(ActionEvent event) {    
      getDataFromeText();
      if(priceGet<(priceSelling)){
      if(countAvailable>0){
       Materials table= new Materials(id,materialName, countAvailable, priceGet, priceSelling,(countAvailable*(priceSelling)-(priceGet*countAvailable))+"" ,((countAvailable)*(priceSelling))+"",0,barcode);                      
       datapay.add( table); 
       PayTabel.setItems(datapay);
        getSumofall();
        clearDataFromText();
       }
      else{
             Alert a =new Alert(Alert.AlertType.ERROR,"حدد الكمية المباعة", ButtonType.OK);
             a.showAndWait();
       }
      }
      else{
             Alert a =new Alert(Alert.AlertType.WARNING,"لايمكن ان يكون سعر البيع اقل من سعر الشراء", ButtonType.OK);
             a.setTitle("تحذير غلط في السعر");
             a.setHeaderText("تحذير");
             a.showAndWait();
      }
        requestFocus();
    }

    @FXML
    void PayButEvent(ActionEvent event) {
        try{
     upDataMaterialDatainPay();
     getContentDataPay();
        }catch(Exception ex){
         getContentDataPay();
        }
     showStage("BayTypeGUI.fxml","واجهة البيع والزبائن");    
    }
     @FXML
    void namematerialEvent(InputMethodEvent event) { 
        getDataFromeText();
        OpOnMaterial op = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDate, mosName,0);
        materialTabel.setItems(op.getDataSearch());  
    }
    
     
    
     @FXML
    void namematerialEventkey(KeyEvent event) { 
        getDataFromeText();
        OpOnMaterial op = new OpOnMaterial(barcode,materialName, countAvailable, priceGet, priceSelling, expDate, mosName,0);
        materialTabel.setItems(op.getDataSearch());  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     setDataOncompo();
     intializedata();
     intializedatapay();
        requestFocus();
    }  
    
   void  requestFocus(){
          Platform.runLater(new Runnable() {
        @Override
        public void run() {
            barcodeText.requestFocus();
        }
    });
    }
      void  requestFocuscount(){
          Platform.runLater(new Runnable() {
        @Override
        public void run() {
            countAvailabletext.requestFocus();
        }
    });
    }
       private void intializedatapay(){
        idcel.setCellValueFactory( new PropertyValueFactory<Materials,Integer>("id"));
        materialNamecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("materialName"));
        countAvailablecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("countAvailable"));
        priceGetcel.setCellValueFactory( new PropertyValueFactory<Materials,String>("priceGet"));
        priceSellingcel.setCellValueFactory( new PropertyValueFactory<Materials,String>("priceSelling"));
        expDatecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("expDate"));
        mosNamecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("mosName")); 
        PayTabel.setRowFactory(tv -> {
        TableRow<Materials> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            Materials raw = clickedRow.getItem(); 
           
           PayTabel.getItems().removeAll(PayTabel.getSelectionModel().getSelectedItems());
            getSumofall();
           }
        });
        return clickedRow ;
        });
    }
    
    @FXML
    void BarcodeEvent(KeyEvent event) {
       if(event.getCode()==KeyCode.ENTER){
        OpOnMaterial op = new OpOnMaterial(barcodeText.getText(),"1nom", countAvailable, priceGet, priceSelling, expDate, mosName,0);
        materialTabel.setItems(op.getDataSearch());
        clearDataFromText();
           requestFocuscount();
           ObservableList<Materials> raw  = op.getDataSearch();
          id=raw.get(0).getId();
            namematerialtext.setText(raw.get(0).getMaterialName());
            countAvailabletext.setText("");
            barcodeText.setText(raw.get(0).getBarcode());
            countbeforPay=Integer.parseInt(raw.get(0).getCountAvailable()+"");
            priceGettext.setText(raw.get(0).getPriceGet()+"");
            priceSellingtext.setText(raw.get(0).getPriceSelling()+"");
            expDatetext.setText(raw.get(0).getExpDate());
            mosNametext.setValue(raw.get(0).getMosName());     
       }
    }
    
       private void getSumofall(){
           datapaysum =PayTabel.getItems();
           sumtotal=0;
           sumwintotal=0;
                  
        for (Materials datapaysum1 : datapaysum) {
            
            Materials m = new Materials(datapaysum1.getId(),datapaysum1.getMaterialName(), datapaysum1.getCountAvailable(), datapaysum1.getPriceGet(), datapaysum1.getPriceSelling(), datapaysum1.getExpDate(), datapaysum1.getMosName(),0,datapaysum1.getBarcode());
            sumtotal+=(m.getPriceSelling())*(m.getCountAvailable());
           sumwintotal+=(m.getPriceSelling()*(m.getCountAvailable()))-(m.getPriceGet())*(m.getCountAvailable());
           
        }
        TotalText.setText(String.valueOf(sumtotal));
           wintotalText.setText(String.valueOf(sumwintotal));
       }
       
     private void intializedata(){
        GetDataMaterials data =new GetDataMaterials(); 
        midcel.setCellValueFactory( new PropertyValueFactory<Materials,Integer>("id"));
        mmaterialNamecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("materialName"));
        mcountAvailablecel.setCellValueFactory( new PropertyValueFactory<Materials,Integer>("countAvailable"));
        mpriceGetcel.setCellValueFactory( new PropertyValueFactory<Materials,Integer>("priceGet"));
        mpriceSellingcel.setCellValueFactory( new PropertyValueFactory<Materials,Integer>("priceSelling"));
        mexpDatecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("expDate"));
        mmosNamecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("mosName"));
        barcodecel.setCellValueFactory( new PropertyValueFactory<Materials,String>("barcode"));
        materialTabel.setItems(data.getDataTable()); 
        materialTabel.setRowFactory(tv -> {
        TableRow<Materials> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            Materials raw = clickedRow.getItem();
            if(raw.getCountAvailable()>0){
            id=raw.getId();
            namematerialtext.setText(raw.getMaterialName());
            countAvailabletext.setText("");
            barcodeText.setText(raw.getBarcode());
            countbeforPay=Integer.parseInt(raw.getCountAvailable()+"");
            priceGettext.setText(raw.getPriceGet()+"");
            priceSellingtext.setText(raw.getPriceSelling()+"");
            expDatetext.setText(raw.getExpDate());
            mosNametext.setValue(raw.getMosName());}
            else{
              Alert a =new Alert(Alert.AlertType.WARNING,"لا  توجد كمية للبيع ", ButtonType.OK);
             a.setTitle("تحذير ");
             a.setHeaderText("الكمية غير متوفره");
             a.showAndWait();
            }
           }
        });
        return clickedRow ;
        });
    }
    
   public void getDataFromeText() {
       materialName= namematerialtext.getText();
         try{
       countAvailable= Integer.parseInt(countAvailabletext.getText());
       priceGet=Integer.parseInt(priceGettext.getText());
       priceSelling =Integer.parseInt(priceSellingtext.getText());
       expDate=expDatetext.getText();
     
      if( mosNametext.getSelectionModel().getSelectedItem().toString().length()>0)
       mosName= mosNametext.getSelectionModel().getSelectedItem().toString();
      else
          mosName="";
       }catch(Exception e){
       mosName="";
       }
         barcode = barcodeText.getText();
    }
    
    public void refrshSpdata(){
    try{
     GetDataMaterials data =new GetDataMaterials();
     materialTabel.setItems(data.getDataTable());
    }catch(Exception ex){
    System.out.println(ex.toString());
    }
    }
    
    public void clearDataFromText() {
        id=0;
        namematerialtext.setText("");
        countAvailabletext.setText("");
        priceGettext.setText("");
        priceSellingtext.setText("");
        expDatetext.setText("");
        mosNametext.setValue("");
        barcodeText.setText("");
    }
    
  private void setDataOncompo(){
       GetDataTotalMost data =new GetDataTotalMost();
       mosNametext.getItems().removeAll(mosNametext.getItems());
       mosNametext.setItems(FXCollections.observableArrayList(data.getDataArray()));

    }
  

  
  private void upDataMaterialDatainPay(){
      try{
        for (Materials materials : datapay) {
            ObservableList<Materials> datacount = FXCollections.observableArrayList();
            OpOnMaterial onMaterial =new OpOnMaterial(materials.getBarcode(),materials.getMaterialName(), materials.getCountAvailable(),materials.getPriceGet(),materials.getPriceSelling(),materials.getExpDate(),materials.getMosName(),0);
            datacount=onMaterial.getDataSearchname(materials.getId());
            Materials materialscount=datacount.get(datacount.size()-1);
            OpOnMaterial opOnMaterial =new OpOnMaterial(materials.getBarcode(),materials.getMaterialName(), materials.getCountAvailable(),materials.getPriceGet(),materials.getPriceSelling(),materials.getExpDate(),materials.getMosName(),0);
            opOnMaterial.UpDateDataname(materials.getId(), (materialscount.getCountAvailable())-(materials.getCountAvailable()));
           refrshSpdata();
        }
      }catch (Exception ex)
      {
          System.out.println(ex.toString());
      }
  }
  
  private void getContentDataPay(){
      TotalPrice=TotalText.getText();
        TotalPriceWin= wintotalText.getText();
        for (Materials datapay1 : datapay) {
           cashInfo cash =new cashInfo(index,datapay1.getMaterialName(), datapay1.getCountAvailable()+"", datapay1.getMosName(), String.valueOf(sumtotal));
           datacash.add(cash);
      
            index++;
        }
        index=0;
  }
  
  private void clearPay(){
       PayTabel.setItems(datapay);
       PayTabel.refresh();
       sumtotal=0;
       sumwintotal=0;
       index=1;
       TotalText.setText("");
       wintotalText.setText("");
  }
  
   public void showStage(String filepath,String Titel)  {
        
        try {
                Stage stage =new Stage();
                getSecreenSize();
                FXMLLoader loder = new FXMLLoader();
                loder.setLocation(getClass().getResource(filepath));
                loder.load();
                BayTypeGUIController control = loder.getController();
                control.setDatapay(datapay);
                control.setTotalPrice(TotalPrice);
                control.setTotalPriceWin(TotalPriceWin);
                clearAfterbay();
                 clearPay(); 
                Parent root =loder.getRoot();
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
   
   ///////clear aftre bay
   
  private void clearAfterbay(){
  datacash.clear();
  datapay.clear();
  datapaysum.clear();
  TotalPrice="";
  TotalPriceWin="";
  
  }
          
          
          
          
  private void getSecreenSize(){
    Toolkit tool =Toolkit.getDefaultToolkit();
    width = tool.getScreenSize().width;
    height=tool.getScreenSize().height;
    }
  
  private int width;
  private int height;
}
