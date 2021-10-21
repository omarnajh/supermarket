/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme;

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
import marktingprograme.DataBase.GetDataForTable.GetDataTotalePurchases;
import marktingprograme.DataBase.OpOnData.OpOnTotalePurchases;
import marktingprograme.DataBase.TotalePurchases;
import marktingprograme.InterFaceFolder.GetDatainterface;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class TotalePurchasesGUIController implements Initializable ,GetDatainterface{

    private int id ;
    private int  idMaterial;
    private String  materialName;
    private String dateProdect;
    private String dateExp;
    private String materialCount;
    private String companyName;
    private String senderName;
    private String senderPhone;
    private String datePurchases;
    private int  idInvoices;
    private String invoicesAmont;
    private String notePurcheses;
    
    
    @FXML
    private TableView<TotalePurchases> TotalePurchasesTable;

    @FXML
    private TableColumn<TotalePurchases, Integer> idcel;

    @FXML
    private TableColumn<TotalePurchases, Integer> idMaterialcel;

    @FXML
    private TableColumn<TotalePurchases, String> materialNamecel;

    @FXML
    private TableColumn<TotalePurchases, String> dateProdectcel;

    @FXML
    private TableColumn<TotalePurchases, String> dateExpcel;

    @FXML
    private TableColumn<TotalePurchases, String> materialCountcel;

    @FXML
    private TableColumn<TotalePurchases, String> companyNamecel;

    @FXML
    private TableColumn<TotalePurchases, String> senderNamecel;

    @FXML
    private TableColumn<TotalePurchases, String> senderPhonecel;

    @FXML
    private TableColumn<TotalePurchases, String> datePurchasescel;

    @FXML
    private TableColumn<TotalePurchases, Integer> idInvoicescel;

    @FXML
    private TableColumn<TotalePurchases, String> invoicesAmontcel;

    @FXML
    private TableColumn<TotalePurchases, String> notePurchesescel;

    @FXML
    private TextField idMaterialtext;

    @FXML
    private TextField materialNametext;

    @FXML
    private TextField dateProdecttext;

    @FXML
    private TextField dateExptext;

    @FXML
    private TextField materialCounttext;

    @FXML
    private TextField companyNametext;

    @FXML
    private TextField senderNametext;

    @FXML
    private TextField senderPhonetext;

    @FXML
    private TextField datePurchasestext;

    @FXML
    private Label TotalLabel;

    @FXML
    private Label winTotallebal;

    @FXML
    private TextField idInvoicestext;

    @FXML
    private TextField invoicesAmonttext;

    @FXML
    private TextField notePurchesestext;

    @FXML
    private Button inputbut;

    @FXML
    private Button updateBut;

    @FXML
    private Button DeleteBut;

    @FXML
    private Button serchBut;

    @FXML
    void DeleteButEvent(ActionEvent event) {
        if(id>0){
        getDataFromeText();
        OpOnTotalePurchases op =new OpOnTotalePurchases(idMaterial, materialName, dateProdect, dateExp, materialCount, companyName, senderName, senderPhone, datePurchases, idInvoices, invoicesAmont, notePurcheses);
         op.DeleteData(id);
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
    void UpdateButEvent(ActionEvent event) {
     if(id>0){
        getDataFromeText();
        OpOnTotalePurchases op =new OpOnTotalePurchases(idMaterial, materialName, dateProdect, dateExp, materialCount, companyName, senderName, senderPhone, datePurchases, idInvoices, invoicesAmont, notePurcheses);
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
      if(materialName.length()>0){
        getDataFromeText();
         OpOnTotalePurchases op =new OpOnTotalePurchases(idMaterial, materialName, dateProdect, dateExp, materialCount, companyName, senderName, senderPhone, datePurchases, idInvoices, invoicesAmont, notePurcheses);
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
        OpOnTotalePurchases op =new OpOnTotalePurchases(idMaterial, materialName, dateProdect, dateExp, materialCount, companyName, senderName, senderPhone, datePurchases, idInvoices, invoicesAmont, notePurcheses);
       TotalePurchasesTable.setItems(op.getDataSearch());
        clearDataFromText();
        TotalLabel.setText(""+op.getSumtotal());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intializedata();
    }    

     private void intializedata(){
         GetDataTotalePurchases data =new GetDataTotalePurchases(); 
        idcel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,Integer>("id"));
        idMaterialcel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,Integer>("idMaterial"));
        materialNamecel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,String>("materialName"));
        dateProdectcel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,String>("dateProdect"));
        dateExpcel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,String>("dateExp"));
        materialCountcel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,String>("materialCount"));
        companyNamecel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,String>("companyName"));
        senderNamecel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,String>("senderName"));
        senderPhonecel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,String>("senderPhone"));
        datePurchasescel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,String>("datePurchases"));
        idInvoicescel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,Integer>("idInvoices"));
        invoicesAmontcel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,String>("invoicesAmont"));
        notePurchesescel.setCellValueFactory( new PropertyValueFactory<TotalePurchases,String>("notePurcheses"));
        TotalePurchasesTable.setItems(data.getDataTable()); 
        TotalLabel.setText(""+data.getSumtotal());
        TotalePurchasesTable.setRowFactory(tv -> {
        TableRow<TotalePurchases> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            TotalePurchases raw = clickedRow.getItem();
            id=raw.getId();
            idMaterialtext.setText(""+raw.getIdMaterial());
            materialNametext.setText(raw.getMaterialName());
            dateProdecttext.setText(raw.getDateProdect());
            dateExptext.setText(raw.getDateExp());
            materialCounttext.setText(raw.getMaterialCount());
            companyNametext.setText(raw.getCompanyName());
            senderNametext.setText(raw.getSenderName());
            senderPhonetext.setText(raw.getSenderPhone());
            datePurchasestext.setText(raw.getDatePurchases());
           idInvoicestext.setText(""+raw.getIdInvoices());
            invoicesAmonttext.setText(raw.getInvoicesAmont());
            notePurchesestext.setText(raw.getNotePurcheses());
            }
        });
        return clickedRow ;
        });
    }
    
    @Override
    public void getDataFromeText() {
           idMaterial= Integer.parseInt(idMaterialtext.getText());
           materialName= materialNametext.getText();
            dateProdect=dateProdecttext.getText();
            dateExp=dateExptext.getText();
            materialCount=materialCounttext.getText();
            companyName=companyNametext.getText();
            senderName=senderNametext.getText();
            senderPhone=senderPhonetext.getText();
            datePurchases=datePurchasestext.getText();
           idInvoices=Integer.parseInt(idInvoicestext.getText());
           invoicesAmont= invoicesAmonttext.getText();
            notePurcheses=notePurchesestext.getText();
    }

    @Override
    public void clearDataFromText() {
           idMaterialtext.setText("");
            materialNametext.setText("");
            dateProdecttext.setText("");
            dateExptext.setText("");
            materialCounttext.setText("");
            companyNametext.setText("");
            senderNametext.setText("");
            senderPhonetext.setText("");
            datePurchasestext.setText("");
           idInvoicestext.setText("");
            invoicesAmonttext.setText("");
            notePurchesestext.setText("");}

    @Override
    public void refrshSpdata() {
    GetDataTotalePurchases data =new GetDataTotalePurchases();
    TotalePurchasesTable.setItems(data.getDataTable());
    TotalLabel.setText(""+data.getSumtotal());
    }
    
}
