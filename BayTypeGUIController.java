/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import marktingprograme.DataBase.GetDataForTable.GetDataSettingSystem;
import marktingprograme.DataBase.GetDataForTable.GetDataTotalCostmer;
import marktingprograme.DataBase.Materials;
import marktingprograme.DataBase.OpOnData.OpOnInvoices;
import marktingprograme.DataBase.OpOnData.OpOnSellingHistory;
import marktingprograme.DataBase.OpOnData.OpOnTotalCostmer;
import marktingprograme.DataBase.OpOnData.OpOnTotalPremiums;
import marktingprograme.DataBase.SellingHistory;
import marktingprograme.DataBase.SettingSystem;
import marktingprograme.DataBase.TotalCostmer;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class BayTypeGUIController implements Initializable {
    private int id=0;
    private Boolean payOk =false;
    private Boolean do_check=true;
    private int dis=0;

    private ObservableList<Materials> datapay = FXCollections.observableArrayList();
    private final  ObservableList<Materials> datapaysum = FXCollections.observableArrayList();
    private String TotalPrice="";
    private String TotalPriceWin="";

    public void setDatapay(ObservableList<Materials> datapay) {
        this.datapay = datapay;
    }

  

  

    public void setTotalPrice(String TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public void setTotalPriceWin(String TotalPriceWin) {
        this.TotalPriceWin = TotalPriceWin;
        getDataForHbox();
        getTital();
    }
   
    @FXML
    private AnchorPane vboxPrint;
    @FXML
    private CheckBox checkPay;
    @FXML
    private Label allrecost;
   
    @FXML
    private TextField kashinvoisub;

    @FXML
    private Button buttonkashinvoisub;
    @FXML
    private TableView<TotalCostmer> tabelCostmer;

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
    
    ////////////////////////////////////////////////////
    @FXML
    private TableView<SellingHistory> tableinvoice;
    @FXML
    private TableColumn<SellingHistory, String> totalpricecel;

    @FXML
    private TableColumn<SellingHistory, String> pricesellingcel;

    @FXML
    private TableColumn<SellingHistory, String> countcel;

    @FXML
    private TableColumn<SellingHistory, String> namematerialcel;
       @FXML
    private TableColumn<SellingHistory, Integer> idcelselling;
    @FXML
    private AnchorPane ancherpane;

    @FXML
    private Label taital;

    @FXML
    private Label typeofshope;

    @FXML
    private Label addressmark;

    @FXML
    private Label numberphone;
    @FXML
    private Label dateofin;
    @FXML
    private Label nameofsorce;

    @FXML
    private Label sumofinvoic;

    @FXML
    private Label numberofinvoic;

    @FXML
    private Label nameofemploy;
    
    @FXML
    private Label thePrim;
    //////////////////////////////
     @FXML
    private Label diskashtext;
     @FXML
    private Label settingInvo;

    @FXML
    private Label arivedkash;
    @FXML
    private Button PrintandSaveBut;
    @FXML
    private TextField CostmerNametext;

    @FXML
    private TextField costmerPhoneText;

    @FXML
    private TextField costmerDateText;

    @FXML
    private TextField costmerTotalMonyText;

    @FXML
    private ComboBox payTypeText;

    @FXML
    private TextField paynoteText;

    @FXML
    private Button AddButton;
    @FXML
    private Button UpdateButton;
    
    @FXML
    private Button endbut;
    @FXML
    void endbutEvent(ActionEvent event) {
   
    if(CostmerNametext.getText().length()>0){
         if(do_check){
         insertToInvoices();    
        
    

        ((Node)(event.getSource())).getScene().getWindow().hide();
         }
         else{

        ((Node)(event.getSource())).getScene().getWindow().hide();
         }
         }
      else{
                Alert a =new Alert(Alert.AlertType.ERROR,"لاتترك الاسم فارغ   ", ButtonType.OK);
                 a.showAndWait();
              }
    
    }


    @FXML
    void AddButtonEvent(ActionEvent event) {
      if(CostmerNametext.getText().length()>0){
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd:HH_mm_ss").format(Calendar.getInstance().getTime());
        OpOnTotalCostmer opOnTotalCostmer =new OpOnTotalCostmer(CostmerNametext.getText(), costmerPhoneText.getText(), "", timeStamp, costmerTotalMonyText.getText(), getDataCombo(),paynoteText.getText());
        opOnTotalCostmer.InsertData();
        int totalmony=0;
        try{
           totalmony=Integer.parseInt( costmerTotalMonyText.getText());
        }catch(Exception ex){
        }
        OpOnTotalPremiums opOnTotalPremiums= new OpOnTotalPremiums(totalmony, CostmerNametext.getText(), "", "",LoginFxmlController.nameusermark);
        opOnTotalPremiums.InsertDatapay();
       nameofemploy.setText(CostmerNametext.getText());
        payOk =true;
        do_check=false;
          insertToInvoices();
        refreshData();
        AddButton.setDisable(true);
        UpdateButton.setDisable(true);
        thePrim.setText("0.0");
      }
      else{
             Alert a =new Alert(Alert.AlertType.ERROR,"لاتترك الاسم فارغ", ButtonType.OK);
             a.showAndWait();
       }
    
    }


    @FXML
    void buttonkashinvoisubEvent(ActionEvent event) {
        if(Integer.parseInt(kashinvoisub.getText())<=Integer.parseInt(TotalPrice)){
      try{
       dis=Integer.parseInt(kashinvoisub.getText());
       diskashtext.setText(String.valueOf(dis));
        int subnew =Integer.parseInt(TotalPrice)-dis;
        arivedkash.setText(String.valueOf(subnew));
        int sim =Integer.parseInt(costmerTotalMonyText.getText())-dis;
        costmerTotalMonyText.setText(String.valueOf(sim));
         allrecost.setText(costmerTotalMonyText.getText());
      }catch(Exception ex){
      dis=0;
      }}
        else{
               Alert a =new Alert(Alert.AlertType.WARNING,"الايمكن ان يكون سعر التسديد اكبر من سعر الفاتورة  ", ButtonType.OK);
                 a.showAndWait();
        }
    }

    
    
    @FXML
    void UpdateButtonEvent(ActionEvent event) {
       if(CostmerNametext.getText().length()>0&&id>0){
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd:HH_mm_ss").format(Calendar.getInstance().getTime());
        OpOnTotalCostmer opOnTotalCostmer =new OpOnTotalCostmer(CostmerNametext.getText(), costmerPhoneText.getText(), "", timeStamp, costmerTotalMonyText.getText(), getDataCombo(),paynoteText.getText());
        opOnTotalCostmer.UpDateData(id);
        
        payOk =true; 
        do_check=false;
         id=0;
        insertToInvoices();
        refreshData();
          AddButton.setDisable(true);
        UpdateButton.setDisable(true);
         endbut.setDisable(false);
     
        allrecost.setText(costmerTotalMonyText.getText());
      }
      else{
             Alert a =new Alert(Alert.AlertType.ERROR,"لاتترك الاسم فارغ واختر الحقل المطلوب  ", ButtonType.OK);
             a.showAndWait();
       }
        
       
    }
    
    @FXML
    void SerchDataCostmerEvent(KeyEvent event) {
        OpOnTotalCostmer opOnTotalCostmer =new OpOnTotalCostmer(CostmerNametext.getText(),null, null, null, null, null, null);
        tabelCostmer.setItems(opOnTotalCostmer.getDataSearch());
    }
    
    @FXML
    void PrintandSaveButEvent(ActionEvent event) {
              PrintandSaveBut.setDisable(true);
              if(CostmerNametext.getText().length()>0){
                  if(do_check){

                  insertToInvoices(); 
                  printImage(null);

                  ((Node)(event.getSource())).getScene().getWindow().hide();}
                  else{
                 
                  printImage(null);

                  ((Node)(event.getSource())).getScene().getWindow().hide(); 
                  }}
              else{
                Alert a =new Alert(Alert.AlertType.ERROR,"لاتترك الاسم فارغ   ", ButtonType.OK);
                 a.showAndWait();
              }
    }
    
    private void printImage(BufferedImage image) {
//    PrinterJob printJob = PrinterJob.getPrinterJob();
//    printJob.setPrintable(new Printable() {
//      @Override
//      public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
//        // Get the upper left corner that it printable
//        int x = (int) Math.ceil(pageFormat.getImageableX());
//        int y = (int) Math.ceil(pageFormat.getImageableY());
//        if (pageIndex != 0) {
//          return NO_SUCH_PAGE;
//        }
//        graphics.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
//        return PAGE_EXISTS;
//      }
//    });
//    try {
//      printJob.print();
//    } catch (PrinterException e1) {
//    }
            Printer printer =Printer.getDefaultPrinter();
            Paper paper = Paper.A4;
            PageLayout pagelayout =printer.createPageLayout(paper, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);
            PrinterJob job =PrinterJob.createPrinterJob();
            double x= pagelayout.getPrintableWidth()/(vboxPrint.getBoundsInParent().getHeight()+(155*1.41));
            double y= pagelayout.getPrintableHeight()/(vboxPrint.getBoundsInParent().getWidth()+80);
            vboxPrint.getTransforms().add(new Scale(x, y));
    
        if (job != null) {
         boolean success = job.printPage(vboxPrint);
         if (success) {
           job.endJob();
      
          }
        }
        PrintandSaveBut.setDisable(true);
  }
    
   private void insertToInvoices(){
       
       
      if(payOk) {
      String timeStamp = new SimpleDateFormat("yyyy/MM/dd:HH_mm_ss").format(Calendar.getInstance().getTime());
      if(getCheckpricees()){
      OpOnInvoices opOnInvoices =new OpOnInvoices(timeStamp, sumofinvoic.getText(), " ", CostmerNametext.getText(), getDataCombo(), "تمت", LoginFxmlController.nameusermark,"",dis+"", TotalPriceWin);
      opOnInvoices.InsertData();
        OpOnInvoices opOnInvoiceses =new OpOnInvoices(timeStamp,sumofinvoic.getText(), " ", CostmerNametext.getText(), getDataCombo(), "تمت", LoginFxmlController.nameusermark, "", dis+"", TotalPriceWin);
        opOnInvoiceses.getDataSearchtime();
      for (Materials datapay1 :datapaysum) {   
          String priceofmaterial = String.valueOf((datapay1.getCountAvailable())*datapay1.getPriceSelling());
          String priceofwintotal = String.valueOf((datapay1.getCountAvailable())*(datapay1.getPriceSelling())-(datapay1.getPriceGet()));
            
          OpOnSellingHistory sellingHistory =new OpOnSellingHistory(opOnInvoiceses.getIdin(), datapay1.getId() ,datapay1.getMaterialName(), datapay1.getPriceSelling()+"", datapay1.getCountAvailable()+"", priceofmaterial, "", priceofwintotal, timeStamp);
            sellingHistory.InsertData(1);            
        }
         numberofinvoic.setText(""+opOnInvoiceses.getIdin());
      }
      payOk=false;
      
      }
      else{
      String timeStamp = new SimpleDateFormat("yyyy/MM/dd:HH_mm_ss").format(Calendar.getInstance().getTime());
       if(getCheckpricees()){
      OpOnInvoices opOnInvoices =new OpOnInvoices(timeStamp, sumofinvoic.getText(), " ", CostmerNametext.getText(), getDataCombo(), "تمت",  LoginFxmlController.nameusermark, "",sumofinvoic.getText(),TotalPriceWin);
      opOnInvoices.InsertData();
       for (Materials datapay1 :datapaysum) {
            String priceofmaterial = String.valueOf(datapay1.getCountAvailable()*(datapay1.getPriceSelling()));
            String priceofwintotal = String.valueOf((datapay1.getCountAvailable())*((datapay1.getPriceSelling())-(datapay1.getPriceGet())));
             OpOnInvoices opOnInvoiceses =new OpOnInvoices(timeStamp, sumofinvoic.getText(), " ", CostmerNametext.getText(), getDataCombo(), "تمت",  LoginFxmlController.nameusermark, "", sumofinvoic.getText(), TotalPriceWin);
            opOnInvoiceses.getDataSearchtime();
             OpOnSellingHistory sellingHistory =new OpOnSellingHistory(opOnInvoiceses.getIdin(), datapay1.getId(), datapay1.getMaterialName(),datapay1.getPriceSelling()+"", datapay1.getCountAvailable()+"", priceofmaterial, "",priceofwintotal, timeStamp);
            sellingHistory.InsertData(1);           
        }
       numberofinvoic.setText(""+opOnInvoices.getIdin());
       }
      }
       do_check=false;
  }
    
   private boolean getCheckpricees(){
    try{
         Integer.parseInt(costmerTotalMonyText.getText());
         return true;
    }
    catch(Exception exx){
        Alert a =new Alert(Alert.AlertType.ERROR,"لايوجد مجموع للفاتورةيجب الغاء عملية البيع  ", ButtonType.OK);
        a.showAndWait();
        
    return false;
    }
   
   }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intializedatacostmer();
        setDataOncompo();
        inititsellingHestory();
        kashinvoisub.setText(TotalPrice);
    }    

          @FXML
    void onChois(ActionEvent event) {
     if( getDataCombo().equals("نقد")){
       kashinvoisub.setText(TotalPrice);
       diskashtext.setText(TotalPrice);
       arivedkash.setText("");
     }
     else{
         kashinvoisub.setText("");
          diskashtext.setText("");
     }
    }
       
        private void intializedatacostmer(){
        GetDataTotalCostmer data =new GetDataTotalCostmer(); 
        idcel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,Integer>("id"));
        costmerNamecel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("costmerName"));
        costmerPhonecel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("costmerPhone"));
        costmerIdcel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("costmerId"));
        costmerDatecel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("costmerDate"));
        costmerTotalMonycel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("costmerTotalMony"));
        payTypecel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("payType"));
        payNotecel.setCellValueFactory( new PropertyValueFactory<TotalCostmer,String>("payNote"));
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd:HH_mm_ss").format(Calendar.getInstance().getTime());
        settingInvo.setText(LoginFxmlController.nameusermark);
          costmerDateText.setText(timeStamp);
        tabelCostmer.setItems(data.getDataTable()); 
        tabelCostmer.setRowFactory(tv -> {
        TableRow<TotalCostmer> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            TotalCostmer raw = clickedRow.getItem();
            id=raw.getId();
            CostmerNametext.setText(raw.getCostmerName());
            nameofemploy.setText(raw.getCostmerName());
            costmerPhoneText.setText(raw.getCostmerPhone());
            costmerDateText.setText(raw.getCostmerDate());
            thePrim.setText(raw.getCostmerTotalMony());
            costmerTotalMonyText.setText(String.valueOf(Integer.parseInt(raw.getCostmerTotalMony())+Integer.parseInt(costmerTotalMonyText.getText())));
            payTypeText.setValue(raw.getPayType());
            paynoteText.setText(raw.getPayNote());
            sumofinvoic.setText(TotalPrice);
            tabelCostmer.setDisable(true);
            AddButton.setDisable(true);
            endbut.setDisable(true);
            allrecost.setText(costmerTotalMonyText.getText()+"");
           }
        });
        return clickedRow ;
        });
           
        arivedkash.setText("");
        diskashtext.setText("");
    }
        
        private void inititsellingHestory(){
        totalpricecel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("sellingTotalPrice"));
        pricesellingcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("sellingPrice"));
        countcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("countOfSelling"));
        namematerialcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("materialName"));
        idcelselling.setCellValueFactory( new PropertyValueFactory<SellingHistory,Integer>("id"));
        }
        
        
     private void getDataForHbox(){
       ObservableList<SellingHistory> datasecndflter = FXCollections.observableArrayList();
       int index=1;
     for(Materials m: datapay){
           String price=  String.valueOf((m.getCountAvailable())*(m.getPriceSelling()));
            SellingHistory sel = new SellingHistory(index, 0, 0, m.getMaterialName(), m.getPriceSelling()+"", m.getCountAvailable()+"",price , null, null, null);
            datasecndflter.add(sel);
            datapaysum.add(m);
            index++;
//     m.getPriceSelling()));
//     vBoxcountav.getChildren().add(new Label(m.getCountAvailable()));
//     vBoxnamematerial.getChildren().add(new Label(m.getMaterialName()));
//     vBoxpriceall.getChildren().add(new Label(String.valueOf((Integer.parseInt(m.getCountAvailable())*Integer.parseInt(m.getPriceSelling())))));
//     }
     }
     
     tableinvoice.setItems(datasecndflter);
    sumofinvoic.setText(TotalPrice);
    }
        
        
        private void refreshData(){
            GetDataTotalCostmer data =new GetDataTotalCostmer();
            tabelCostmer.setItems(data.getDataTable());
        }
        
        private void getTital(){
          costmerTotalMonyText.setText(TotalPrice);
          kashinvoisub.setText(TotalPrice);
           diskashtext.setText(TotalPrice);
         GetDataSettingSystem gdss = new GetDataSettingSystem();
 
        
          SettingSystem system =gdss.getDataTable().get(0);
          taital.setText(system.getCenterName());
          nameofsorce.setText(system.getMangerName());
          numberphone.setText(system.getCenterPhone());
          typeofshope.setText(system.getNatureMaterial());
         addressmark.setText(system.getAdsress());
         
         String timeStamp = new SimpleDateFormat("yyyy/MM/dd:HH_mm_ss").format(Calendar.getInstance().getTime());
          dateofin.setText(timeStamp);
         
    }
    private void setDataOncompo(){
        String []items = {"نقد","فقد"};
        payTypeText.getItems().removeAll(payTypeText.getItems());
        payTypeText.setItems(FXCollections.observableArrayList(items));
        payTypeText.setValue("نقد");

    }

     String getDataCombo(){
        try{
         if( payTypeText.getSelectionModel().getSelectedItem().toString().length()>0)
           return payTypeText.getSelectionModel().getSelectedItem().toString();
          else
         return "";
           }catch(Exception e){
        return "";
        }
     }
}
