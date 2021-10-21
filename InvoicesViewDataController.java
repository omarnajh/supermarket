/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import marktingprograme.DataBase.GetDataForTable.GetDataInvoices;
import marktingprograme.DataBase.GetDataForTable.GetDataSettingSystem;
import marktingprograme.DataBase.Invoices;
import marktingprograme.DataBase.OpOnData.OpOnInvoices;
import marktingprograme.DataBase.OpOnData.OpOnMaterial;
import marktingprograme.DataBase.OpOnData.OpOnSellingHistory;
import marktingprograme.DataBase.OpOnData.OpOnTotalCostmer;
import marktingprograme.DataBase.SellingHistory;
import marktingprograme.DataBase.SettingSystem;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class InvoicesViewDataController implements Initializable {
    private String employName;
    private int idinvoc =0;
    
    
    @FXML
    private AnchorPane ancherpane;
  
    private final  ObservableList<SellingHistory> datasecndflter = FXCollections.observableArrayList();
  
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
    private Label sumofinvoic;

    @FXML
    private Label numberofinvoic;

    @FXML
    private Label nameofemploy;
    @FXML
    private Label diskashtext;

    @FXML
    private Label arivedkash;
   
    @FXML
    private Label settingInvo;
 


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
    private TableColumn<SellingHistory, Integer> idcel;

    @FXML
    private TableView<Invoices> tabelserch;

    @FXML
    private TableColumn<Invoices,String > datainvoiccel;

    @FXML
    private TableColumn<Invoices, String> nameemployicel;

    @FXML
    private TableColumn<Invoices, Integer> idinvoiccel;

    @FXML
    private TextField serachinvoiecbut;

    @FXML
    private Button printBut;

    @FXML
    private Button exitbut;
    @FXML
    private Button clearBut;
    
    @FXML
    void clearButEvent(ActionEvent event) {
      serachinvoiecbut.setText("");
      getDataFromeText();
       serchData();
    }
    
    @FXML
    void exitbutEvent(ActionEvent event) {
       ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void printButevent(ActionEvent event) {
//               WritableImage image=null;
//              image = ancherpane.snapshot(new SnapshotParameters(), null);
//              BufferedImage imageb = SwingFXUtils.fromFXImage(image, null);
              printImage(null);
        
    }

    @FXML
    void serachinvoiecbutEvent(KeyEvent event) {
      serchData();
    }

       @Override
    public void initialize(URL url, ResourceBundle rb) {
        intializedata();
           getTital();
    }    

      private void intializedata(){
        GetDataInvoices data =new GetDataInvoices(); 
        idinvoiccel.setCellValueFactory( new PropertyValueFactory<Invoices,Integer>("id"));
        datainvoiccel.setCellValueFactory( new PropertyValueFactory<Invoices,String>("dateInvoices"));
        nameemployicel.setCellValueFactory( new PropertyValueFactory<Invoices,String>("employName"));
        tabelserch.setItems(data.getDataTable());
        tabelserch.setRowFactory(tv -> {
        TableRow<Invoices> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            Invoices raw = clickedRow.getItem();
                OpOnSellingHistory ob =new OpOnSellingHistory(0, 0, "", "", "", "", "", "", raw.getDateInvoices());
                frestFelter(ob.getDataSearch());
                dateofin.setText(raw.getDateInvoices());
                nameofemploy.setText(raw.getEmployName());
                sumofinvoic.setText(raw.getAmontInvoices());
                numberofinvoic.setText(String.valueOf(raw.getId()));
                serachinvoiecbut.setText(raw.getEmployName());
                idinvoc=Integer.parseInt(numberofinvoic.getText());
                diskashtext.setText(raw.getFinalTotal());
                settingInvo.setText(raw.getDar());
                try{
                int sumnw  =Integer.parseInt(raw.getAmontInvoices())-Integer.parseInt(diskashtext.getText());
                arivedkash.setText(String.valueOf(sumnw));
                        }
                catch(Exception ex){
                    arivedkash.setText(raw.getAmontInvoices());
                }
                
                serchData();
            }
        });
        return clickedRow ;
        }); 
              
        totalpricecel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("sellingTotalPrice"));
        pricesellingcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("sellingPrice"));
        countcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("countOfSelling"));
        namematerialcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,String>("materialName"));
        idcel.setCellValueFactory( new PropertyValueFactory<SellingHistory,Integer>("note"));
        tableinvoice.setRowFactory(tv -> {
        TableRow<SellingHistory> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            SellingHistory raw = clickedRow.getItem();
            TextInputDialog td = new TextInputDialog();
            td.setTitle("استرجاع ماده");
            td.setHeaderText("ادخل الكمية المسترجعة");
            td.showAndWait();
          
             
            try{
                /////////////start selling
                String numstr =td.getEditor().getText();
                int number =Integer.parseInt(numstr);
                int id=raw.getId();
                int rel = Integer.parseInt(raw.getCountOfSelling());
                if(number<=rel&&number>0){
                    
                String pricetotal =String.valueOf(Integer.parseInt( raw.getSellingPrice())*(rel-number));
                int totalwinint = Integer.parseInt(raw.getWinTotalPrice())/Integer.parseInt(raw.getCountOfSelling());
                String totalwin =String.valueOf(totalwinint*(rel-number));
                OpOnSellingHistory ob =new OpOnSellingHistory(raw.getIdInvoice(), raw.getIdMaterial(), raw.getMaterialName(),raw.getSellingPrice(), raw.getCountOfSelling(),pricetotal , "", totalwin, raw.getDateOfSelling()); 
                ob.UpDateDataid(id,number);
                OpOnSellingHistory ob2 =new OpOnSellingHistory(0, 0, "", "", "", "", "", "", raw.getDateOfSelling());
                frestFelter(ob2.getDataSearch());
                serchData();
                   
                //////////////end selling   strat material
                
                OpOnMaterial opm = new OpOnMaterial("","", 0, 0, 0, "", "",0);
                opm.UpDateDataid(raw.getIdMaterial(),number);
                
                //////////////////end material strat invoices
                if(idinvoc>0){
                OpOnInvoices opin = new OpOnInvoices("", String.valueOf(getTotalPrice()), "", "", "", "", "", "", String.valueOf(getTotalPrice()),String.valueOf(getwinTotalPrice()) );
                opin.UpDateDatasum(idinvoc);
                sumofinvoic.setText(String.valueOf(getTotalPrice()));
                 }
                
                ///////////////////end invoices start total costmer
                    int submony =number*Integer.parseInt(raw.getSellingPrice());
                    OpOnTotalCostmer opcos =new OpOnTotalCostmer(employName, "", "", "",submony+"" , "", "");
                    opcos.UpDateData(employName);
                
                //////////////////end total costmer 
                }
                else{
                Alert a =new Alert(Alert.AlertType.WARNING,"العدد المرجع اكبر من الموجود", ButtonType.OK);
                a.setHeaderText("العدد المرجع");
                a.setTitle("تحذير");
                  a.showAndWait();
                }
            }catch(Exception ex){
                System.out.println(ex.toString());
            }
            }
        });
        return clickedRow ;
        });
     
    }   
    
      
      private int getTotalPrice(){
      int sum =0;
      for(SellingHistory sel:datasecndflter)
      {
      sum+=Integer.parseInt(sel.getSellingTotalPrice());
      }
      return sum;
      }
      
      
      private int getwinTotalPrice(){
      int sum =0;
      for(SellingHistory sel:datasecndflter)
      {
      sum+=Integer.parseInt(sel.getWinTotalPrice());
      }
      return sum;
      }
      
      private void frestFelter(ObservableList<SellingHistory> dataferstflter){
          datasecndflter.clear();
          int index=1;
          for(SellingHistory dataf:dataferstflter){
            String price=  String.valueOf(Integer.parseInt(dataf.getSellingPrice())*Integer.parseInt(dataf.getCountOfSelling()));
            SellingHistory sel = new SellingHistory(dataf.getId(), dataf.getIdInvoice(), dataf.getIdMaterial(), dataf.getMaterialName(),dataf.getSellingPrice() , dataf.getCountOfSelling(), price, String.valueOf(index), dataf.getWinTotalPrice(), dataf.getDateOfSelling());
            datasecndflter.add(sel);
            index++;
          }
          tableinvoice.setItems(datasecndflter);
      }
      
      private void getTital(){
        GetDataSettingSystem gdss = new GetDataSettingSystem();
        for(int i=0;i<gdss.getDataTable().size();i++)
        {
          SettingSystem system =gdss.getDataTable().get(i);
          taital.setText(system.getCenterName());
          typeofshope.setText(system.getNatureMaterial());
          numberphone.setText(system.getCenterPhone());
          addressmark.setText(system.getAdsress());
        }       
    }

    private void getDataFromeText() {
        employName=serachinvoiecbut.getText();
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
//        Image newImage = image.getScaledInstance(500, 750, Image.SCALE_DEFAULT);
//
//        graphics.drawImage(newImage, 100-x, 100-y, 570, 806, null);
//        return PAGE_EXISTS;
//      }
//    });
//    try {
//      printJob.print();
//    } catch (PrinterException e1) {
//      e1.printStackTrace();
//    }
       
            Printer printer =Printer.getDefaultPrinter();
            Paper paper = Paper.A4;
            PageLayout pagelayout =printer.createPageLayout(paper, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);
            PrinterJob job =PrinterJob.createPrinterJob();
            double x= pagelayout.getPrintableWidth()/(ancherpane.getBoundsInParent().getHeight()+(155*1.41));
            double y= pagelayout.getPrintableHeight()/(ancherpane.getBoundsInParent().getWidth()+80);
            ancherpane.getTransforms().add(new Scale(x, y));
    
        if (job != null) {
         boolean success = job.printPage(ancherpane);
         if (success) {
           job.endJob();
      
             Stage s = (Stage) ancherpane.getScene().getWindow();
             s.close();
          }
        }
   
  }
    
    
    private void serchData(){
      getDataFromeText();
        OpOnInvoices op =new OpOnInvoices("", "", "", employName, "", "", "", "", "", "");
         tabelserch.setItems(op.getDataSearch());}
}
