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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import marktingprograme.DataBase.GetDataForTable.GetDataSettingSystem;
import marktingprograme.DataBase.OpOnData.OpOnSettingSystem;
import marktingprograme.DataBase.SettingSystem;
import marktingprograme.InterFaceFolder.GetDatainterface;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class SettingSystemGUIController implements Initializable,GetDatainterface {

    private int id ;
    private String  centerName;
    private String mangerName;
    private String centerPhone;
    private String systemPassword;
    private String natureMaterial;
    private String currenyName;
    private String  adsress;
    @FXML
    private TableView<SettingSystem> SettingSystemTabel;

    @FXML
    private TableColumn<SettingSystem, Integer> idcel;

    @FXML
    private TableColumn<SettingSystem, String> centerNamecel;

    @FXML
    private TableColumn<SettingSystem, String> mangerNamecel;

    @FXML
    private TableColumn<SettingSystem, String> centerPhonecel;

    @FXML
    private TableColumn<SettingSystem, String> systemPasswordcel;

    @FXML
    private TableColumn<SettingSystem, String> natureMaterialcel;

    @FXML
    private TableColumn<SettingSystem, String> currenyNamecel;
    @FXML
    private TableColumn<SettingSystem, String> adsresscel;

    @FXML
    private TextField idtext;

    @FXML
    private TextField centerNametext;

    @FXML
    private TextField mangerNametext;

    @FXML
    private TextField centerPhonetext;

    @FXML
    private TextField systemPasswordtext;

    @FXML
    private TextField natureMaterialtext;

    @FXML
    private TextField currenyNametext;
    @FXML
    private TextField adsresstext;

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
        OpOnSettingSystem op = new OpOnSettingSystem(centerName, mangerName, centerPhone, systemPassword, natureMaterial, currenyName,adsress);
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
       getDataFromeText();
      if(id>0){
        OpOnSettingSystem op = new OpOnSettingSystem(centerName, mangerName, centerPhone, systemPassword, natureMaterial, currenyName,adsress);
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
      if(mangerNametext.getText().length()>0){
        getDataFromeText();
        OpOnSettingSystem op = new OpOnSettingSystem(centerName, mangerName, centerPhone, systemPassword, natureMaterial, currenyName,adsress);
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
      if(mangerNametext.getText().length()>0){
        OpOnSettingSystem op = new OpOnSettingSystem(centerName, mangerName, centerPhone, systemPassword, natureMaterial, currenyName,adsress);
         SettingSystemTabel.setItems(op.getDataSearch());
        clearDataFromText();
       }
       else{
             Alert a =new Alert(Alert.AlertType.ERROR,"لا تترك حقل الاسم فارغ", ButtonType.OK);
             a.showAndWait();
       }
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intializedata();
    }    

   private void intializedata(){
        GetDataSettingSystem data =new GetDataSettingSystem(); 
        idcel.setCellValueFactory( new PropertyValueFactory<SettingSystem,Integer>("id"));
        centerNamecel.setCellValueFactory( new PropertyValueFactory<SettingSystem,String>("centerName"));
        mangerNamecel.setCellValueFactory( new PropertyValueFactory<SettingSystem,String>("mangerName"));
        centerPhonecel.setCellValueFactory( new PropertyValueFactory<SettingSystem,String>("centerPhone"));
        systemPasswordcel.setCellValueFactory( new PropertyValueFactory<SettingSystem,String>("systemPassword"));
        natureMaterialcel.setCellValueFactory( new PropertyValueFactory<SettingSystem,String>("natureMaterial"));
        currenyNamecel.setCellValueFactory( new PropertyValueFactory<SettingSystem,String>("currenyName"));
        adsresscel.setCellValueFactory( new PropertyValueFactory<SettingSystem,String>("adsress"));
        SettingSystemTabel.setItems(data.getDataTable()); 
        SettingSystemTabel.setRowFactory(tv -> {
        TableRow<SettingSystem> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            SettingSystem raw = clickedRow.getItem();
            id=raw.getId();
            idtext.setText(""+raw.getId());
            centerNametext.setText(raw.getCenterName());
            mangerNametext.setText(raw.getMangerName());
            centerPhonetext.setText(raw.getCenterPhone());
            systemPasswordtext.setText(raw.getSystemPassword());
            natureMaterialtext.setText(raw.getNatureMaterial());
            currenyNametext.setText(raw.getCurrenyName());
            adsresstext.setText(raw.getAdsress());
            }
        });
        return clickedRow ;
        });
    }
    
    @Override
    public void getDataFromeText() {
            centerName=centerNametext.getText();
            mangerName=mangerNametext.getText();
            centerPhone=centerPhonetext.getText();
            systemPassword=systemPasswordtext.getText();
            natureMaterial=natureMaterialtext.getText();
            currenyName=currenyNametext.getText();
            adsress=adsresstext.getText();
    }

    @Override
    public void clearDataFromText() {
            id=0;
            idtext.setText("");
            centerNametext.setText("");
            mangerNametext.setText("");
            centerPhonetext.setText("");
            systemPasswordtext.setText("");
            natureMaterialtext.setText("");
            currenyNametext.setText("");
            adsresstext.setText("");
    }

    @Override
    public void refrshSpdata() {
        GetDataSettingSystem data =new GetDataSettingSystem();
        SettingSystemTabel.setItems(data.getDataTable());
    }
    
}
