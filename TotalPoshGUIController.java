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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import marktingprograme.DataBase.GetDataForTable.GetDataTotalPosh;
import marktingprograme.DataBase.OpOnData.OpOnTotalPosh;
import marktingprograme.DataBase.TotalPosh;
import marktingprograme.InterFaceFolder.GetDatainterface;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class TotalPoshGUIController implements Initializable, GetDatainterface {

    private int id;
    private String name;
    private String total;
    private String idinvoi;
    private String date;
    private String posh;
    private String res;
    @FXML
    private TableView<TotalPosh> TotalPoshTabel;

    @FXML
    private TableColumn<TotalPosh, Integer> idcel;

    @FXML
    private TableColumn<TotalPosh, String> namecel;

    @FXML
    private TableColumn<TotalPosh, String> totalcel;

    @FXML
    private TableColumn<TotalPosh, String> idinvoicel;

    @FXML
    private TableColumn<TotalPosh, String> datecel;
    @FXML
    private TableColumn<TotalPosh, String> poshcel;

    @FXML
    private TableColumn<TotalPosh, String> rescel;

    @FXML
    private TextField idtext;

    @FXML
    private TextField nametext;

    @FXML
    private TextField totaltext;

    @FXML
    private TextField idinvoitext;

    @FXML
    private TextField datetext;
    @FXML
    private TextField poshtext;

    @FXML
    private TextField restext;

    @FXML
    private Button inputbut;

    @FXML
    private Button updateBut;

    @FXML
    private Button DeleteBut;

    @FXML
    private Button serchBut;
    @FXML
    private Label sumall;
    @FXML
    private Label sumsub;

    @FXML
    void DeleteButEvent(ActionEvent event) {
        if (id > 0) {
            getDataFromeText();
            OpOnTotalPosh op = new OpOnTotalPosh(name, total, idinvoi, date, posh, res);
            op.DeleteData(id);
            clearDataFromText();
            refrshSpdata();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "اختر الحقل المطلوب", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    void UpdateButEvent(ActionEvent event) {
        getDataFromeText();
        if (id > 0) {
            OpOnTotalPosh op = new OpOnTotalPosh(name, total, idinvoi, date, posh, res);
            op.UpDateData(id);
            clearDataFromText();
            refrshSpdata();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "اختر الحقل المطلوب", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    void inputButEvent(ActionEvent event) {
        if (nametext.getText().length() > 0) {
            getDataFromeText();
            OpOnTotalPosh op = new OpOnTotalPosh(name, total, idinvoi, date, posh, res);
            op.InsertData();
            clearDataFromText();
            refrshSpdata();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "لاتترك الاسم فارغ", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    void serchButEvent(ActionEvent event) {
        getDataFromeText();
        OpOnTotalPosh op = new OpOnTotalPosh(name, total, idinvoi, date, posh, res);
        TotalPoshTabel.setItems(op.getDataSearch());
        sumall.setText(op.getSumall()+"");
        sumsub.setText(op.getSumsub()+"");
        clearDataFromText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intializedata();
    }
    
      @FXML
    void onchengenent(KeyEvent event) {
       int x=0;
       int y =0;
       try{
           x=Integer.parseInt(totaltext.getText());
       }catch(Exception ex)
       {
       x=0;
       }
       try{
           y=Integer.parseInt(poshtext.getText());
       }catch(Exception ex)
       {
       y=0;
       }
       if(x>=y){
       int sum = x-y;
       restext.setText(sum+"");}
       else{
           Alert a = new Alert(Alert.AlertType.ERROR, "لايمكن ان يكون ملغ التسديد اكبر من مبلغ الفاتورة", ButtonType.OK);
            a.showAndWait();
            poshtext.setText("");
       }
    }

    private void intializedata() {
        GetDataTotalPosh data = new GetDataTotalPosh();
        idcel.setCellValueFactory(new PropertyValueFactory<TotalPosh, Integer>("id"));
        namecel.setCellValueFactory(new PropertyValueFactory<TotalPosh, String>("name"));
        totalcel.setCellValueFactory(new PropertyValueFactory<TotalPosh, String>("total"));
        idinvoicel.setCellValueFactory(new PropertyValueFactory<TotalPosh, String>("idinvoi"));
        datecel.setCellValueFactory(new PropertyValueFactory<TotalPosh, String>("date"));
        poshcel.setCellValueFactory(new PropertyValueFactory<TotalPosh, String>("posh"));
        rescel.setCellValueFactory(new PropertyValueFactory<TotalPosh, String>("res"));
        sumall.setText(data.getSumall()+"");
        sumsub.setText(data.getSumsub()+"");
        TotalPoshTabel.setItems(data.getDataTable());
        TotalPoshTabel.setRowFactory(tv -> {
            TableRow<TotalPosh> clickedRow = new TableRow<>();
            clickedRow.setOnMouseClicked(event -> {
                if (!clickedRow.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    TotalPosh raw = clickedRow.getItem();
                    id = raw.getId();
                    idtext.setText("" + raw.getId());
                    nametext.setText(raw.getName());
                    totaltext.setText(raw.getTotal());
                    idinvoitext.setText(raw.getIdinvoi());
                    datetext.setText(raw.getDate());
                    poshtext.setText(raw.getPosh());
                    restext.setText(raw.getRes());
                }
            });
            return clickedRow;
        });
    }

    @Override
    public void getDataFromeText() {
        name = nametext.getText();
        total = totaltext.getText();
        idinvoi = idinvoitext.getText();
        date = datetext.getText();
        posh = poshtext.getText();
        res = restext.getText();
    }

    @Override
    public void clearDataFromText() {
        nametext.setText("");
        totaltext.setText("");
        idinvoitext.setText("");
        datetext.setText("");
        poshtext.setText("");
        restext.setText("");

    }

    @Override
    public void refrshSpdata() {
        GetDataTotalPosh gdte = new GetDataTotalPosh();
        TotalPoshTabel.setItems(gdte.getDataTable());
        sumall.setText(gdte.getSumall()+"");
        sumsub.setText(gdte.getSumsub()+"");
    }
}
