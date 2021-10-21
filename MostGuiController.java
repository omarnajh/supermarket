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
import marktingprograme.DataBase.GetDataForTable.GetDataTotalMost;
import marktingprograme.DataBase.OpOnData.OpOnTotalMost;
import marktingprograme.DataBase.TotalMost;
import marktingprograme.InterFaceFolder.GetDatainterface;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class MostGuiController implements Initializable,GetDatainterface {
    private int  id ;
    private String   mostName;
    @FXML
    private TableView<TotalMost> mostTabel;

    @FXML
    private TableColumn<TotalMost, Integer> idcel;

    @FXML
    private TableColumn<TotalMost, String> mostNamecel;

    @FXML
    private TextField mostNametext;

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
        OpOnTotalMost op = new OpOnTotalMost(mostName);
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
        OpOnTotalMost op = new OpOnTotalMost(mostName);
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
      if(mostNametext.getText().length()>0){
        getDataFromeText();
         OpOnTotalMost op = new OpOnTotalMost(mostName);
        op.InsertData();
        clearDataFromText();
        refrshSpdata();}
          else
        {
           Alert a =new Alert(Alert.AlertType.ERROR,"اختر الحقل المطلوب", ButtonType.OK);
             a.showAndWait();
           }
    }

    @FXML
    void serchButEvent(ActionEvent event) {
        getDataFromeText();
      if(mostName.length()>0){
        OpOnTotalMost op = new OpOnTotalMost(mostName);
        mostTabel.setItems(op.getDataSearch());
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

    @Override
    public void getDataFromeText() {
        mostName=mostNametext.getText();
    }

    @Override
    public void clearDataFromText() {
        mostNametext.setText("");
        id=0;
    }

    @Override
    public void refrshSpdata() {
        GetDataTotalMost data =new GetDataTotalMost();
        mostTabel.setItems(data.getDataTable());
    }
    
       private void intializedata(){
        GetDataTotalMost data =new GetDataTotalMost(); 
        idcel.setCellValueFactory( new PropertyValueFactory<TotalMost,Integer>("id"));
        mostNamecel.setCellValueFactory( new PropertyValueFactory<TotalMost,String>("mostName"));
        mostTabel.setItems(data.getDataTable()); 
        mostTabel.setRowFactory(tv -> {
        TableRow<TotalMost> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            TotalMost raw = clickedRow.getItem();
            id=raw.getId();
            mostNametext.setText(raw.getMostName());
     
           }
        });
        return clickedRow ;
        });
    }
    
}
