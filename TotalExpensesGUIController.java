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
import marktingprograme.DataBase.GetDataForTable.GetDataTotalExpenses;
import marktingprograme.DataBase.OpOnData.OpOnTotalExpenses;
import marktingprograme.DataBase.TotalExpenses;
import marktingprograme.InterFaceFolder.GetDatainterface;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class TotalExpensesGUIController implements Initializable,GetDatainterface {

    private int id ;
    private String  dateExpenses;
    private String subjectExpenses;
    private String amountTotal;
    private String note;
    @FXML
    private TableView<TotalExpenses> TotalExpensesTabel;

    @FXML
    private TableColumn<TotalExpenses, Integer> idcel;

    @FXML
    private TableColumn<TotalExpenses, String> dateExpensescel;

    @FXML
    private TableColumn<TotalExpenses, String> subjectExpensescel;

    @FXML
    private TableColumn<TotalExpenses, String> amountTotalcel;

    @FXML
    private TableColumn<TotalExpenses, String> notecel;

    @FXML
    private TextField idtext;

    @FXML
    private TextField dateExpensestext;

    @FXML
    private TextField subjectExpensestext;

    @FXML
    private TextField amountTotaltext;

    @FXML
    private TextField notetext;

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
        OpOnTotalExpenses op =new OpOnTotalExpenses(dateExpenses, subjectExpenses, amountTotal, note);
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
        OpOnTotalExpenses op =new OpOnTotalExpenses(dateExpenses, subjectExpenses, amountTotal, note);
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
     if(subjectExpensestext.getText().length()>0){
        getDataFromeText();
        OpOnTotalExpenses op =new OpOnTotalExpenses(dateExpenses, subjectExpenses, amountTotal, note);
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
        OpOnTotalExpenses op =new OpOnTotalExpenses(dateExpenses, subjectExpenses, amountTotal, note);
        TotalExpensesTabel.setItems(op.getDataSearch());
        clearDataFromText();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intializedata();
    }    

     private void intializedata(){
         GetDataTotalExpenses data =new GetDataTotalExpenses(); 
        idcel.setCellValueFactory( new PropertyValueFactory<TotalExpenses,Integer>("id"));
        dateExpensescel.setCellValueFactory( new PropertyValueFactory<TotalExpenses,String>("dateExpenses"));
        subjectExpensescel.setCellValueFactory( new PropertyValueFactory<TotalExpenses,String>("subjectExpenses"));
        amountTotalcel.setCellValueFactory( new PropertyValueFactory<TotalExpenses,String>("amountTotal"));
        notecel.setCellValueFactory( new PropertyValueFactory<TotalExpenses,String>("note"));
        TotalExpensesTabel.setItems(data.getDataTable()); 
        TotalExpensesTabel.setRowFactory(tv -> {
        TableRow<TotalExpenses> clickedRow = new TableRow<>();
        clickedRow.setOnMouseClicked(event -> {
            if (! clickedRow.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                 && event.getClickCount() == 2) {
            TotalExpenses raw = clickedRow.getItem();
            id=raw.getId();
            idtext.setText(""+raw.getId());
            dateExpensestext.setText(raw.getDateExpenses());
            subjectExpensestext.setText(raw.getSubjectExpenses());
            amountTotaltext.setText(raw.getAmountTotal());
            notetext.setText(raw.getNote());
            }
        });
        return clickedRow ;
        });
    }
    @Override
    public void getDataFromeText() {
           dateExpenses= dateExpensestext.getText();
           subjectExpenses= subjectExpensestext.getText();
           amountTotal= amountTotaltext.getText();
           note= notetext.getText();
    }

    @Override
    public void clearDataFromText() {
          dateExpensestext.setText("");
           subjectExpensestext.setText("");
           amountTotaltext.setText("");
          notetext.setText("");}

    @Override
    public void refrshSpdata() {
    GetDataTotalExpenses gdte =new GetDataTotalExpenses();
    TotalExpensesTabel.setItems(gdte.getDataTable());
    }
    
}
