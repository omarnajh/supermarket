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
import marktingprograme.DataBase.Employes;
import marktingprograme.DataBase.GetDataForTable.GetDataEmployes;
import marktingprograme.DataBase.OpOnData.OpOnEmployes;
import marktingprograme.InterFaceFolder.GetDatainterface;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class EmployesGUIController implements Initializable ,GetDatainterface{
    private  int id=0;
    private String employesName;
    private  String employesPassword;
    private  String employesLevel;

    @FXML
    private TableView<Employes> EmployTabel;

    @FXML
    private TableColumn<Employes, Integer> idcel;

    @FXML
    private TableColumn<Employes, String> employesNamecel;

    @FXML
    private TableColumn<Employes, String> employesPasswordcel;

    @FXML
    private TableColumn<Employes, String> employesLevelcel;

    @FXML
    private TextField idtext;

    @FXML
    private TextField employesNametext;

    @FXML
    private TextField employesPasswordtext;

    @FXML
    private TextField employesLeveltext;

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
        OpOnEmployes op = new OpOnEmployes(employesName, employesPassword, employesLevel);
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
        OpOnEmployes op = new OpOnEmployes(employesName, employesPassword, employesLevel);
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
      if(employesNametext.getText().length()>0){
        getDataFromeText();
        OpOnEmployes op = new OpOnEmployes(employesName, employesPassword, employesLevel);
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
        OpOnEmployes op = new OpOnEmployes(employesName, employesPassword, employesLevel);
        EmployTabel.setItems(op.getDataSearch());
        clearDataFromText();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intializedata();
    }    
    
      private void intializedata(){
        GetDataEmployes data =new GetDataEmployes(); 
        idcel.setCellValueFactory( new PropertyValueFactory<Employes,Integer>("id"));
        employesNamecel.setCellValueFactory( new PropertyValueFactory<Employes,String>("employesName"));
        employesPasswordcel.setCellValueFactory( new PropertyValueFactory<Employes,String>("employesPassword"));
        employesLevelcel.setCellValueFactory( new PropertyValueFactory<Employes,String>("employesLevel"));
        EmployTabel.setItems(data.getDataTable()); 
        EmployTabel.setRowFactory(tv -> {
        TableRow<Employes> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            Employes raw = clickedRow.getItem();
            id=raw.getId();
            idtext.setText(""+raw.getId());
            employesNametext.setText(raw.getEmployesName());
            employesPasswordtext.setText(raw.getEmployesPassword());
            employesLeveltext.setText(raw.getEmployesLevel());
           }
        });
        return clickedRow ;
        });
    }
    
      
         @Override
    public void getDataFromeText() {
        employesLevel=employesLeveltext.getText();
        employesName=employesNametext.getText();
        employesPassword=employesPasswordtext.getText();
        try{
        id=Integer.parseInt(idtext.getText());}
        catch(Exception ex)
        {
        id=0;
        }
    }

    @Override
    public void clearDataFromText() {
        employesLeveltext.setText("");
        employesNametext.setText("");
        employesPasswordtext.setText("");
        idtext.setText("");
    }

    @Override
    public void refrshSpdata() {
        GetDataEmployes data =new GetDataEmployes();
        EmployTabel.setItems(data.getDataTable());
    }
    
}
