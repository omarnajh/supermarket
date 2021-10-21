/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import marktingprograme.DataBase.GetDataForTable.GetDataMaterials;
import marktingprograme.DataBase.GetDataForTable.GetDataMaterialsFreq;
import marktingprograme.DataBase.Materialsfreq;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class RiportMaterialsellingFXMLController implements Initializable {
   
    XYChart.Series series = new XYChart.Series<>();
   private   ObservableList<Materialsfreq> datapaysum = FXCollections.observableArrayList();
    
   @FXML
    private BarChart<?,?> barchart;
    @FXML
    private DatePicker datestart;

    @FXML
    private TextField materialnamecompo;

    @FXML
    private DatePicker dataend;

    @FXML   
    private CategoryAxis xcatgory;

    @FXML
    private NumberAxis ynumber;

   @FXML
   private void show_Data(ActionEvent event){
       series.getData().removeAll(series.getData());
       barchart.getData().removeAll(barchart.getData());
       if(checkDatatime()){
       GetDataMaterialsFreq getdata= new GetDataMaterialsFreq(datestart.getValue().toString().replaceAll("-", "/"),dataend.getValue().toString().replaceAll("-", "/"),materialnamecompo.getText());
       if(cheackname()){
           
        }  
       else
       {
       series.setName("بيانات المواد");
       }
        if(cheackname())
           {
              datapaysum= getdata.getDataTable();
               System.out.println(datapaysum.size());
               for(int i=0;i<datapaysum.size();i++){
                    XYChart.Series seriesx= new XYChart.Series<>();
                    seriesx.setName(materialnamecompo.getText());
                    seriesx.getData().add(new XYChart.Data(datapaysum.get(i).getMaterialName(), Integer.parseInt(datapaysum.get(i).getFrequnt())));
                    barchart.getData().add(seriesx);
               }
           }
        else
       getdata.getDataTable().forEach((datapay1) -> {
             XYChart.Series seriesx= new XYChart.Series<>();
             seriesx.setName(datapay1.getMaterialName());
             seriesx.getData().add(new XYChart.Data(datapay1.getMaterialName(), Integer.parseInt(datapay1.getFrequnt())));
             barchart.getData().addAll(seriesx);
        });
    
            }
     
       
   }
   
   private Boolean cheackname(){
       try{
           if(materialnamecompo.getText().length()>0)
           {
           return true;
           }
         }
       catch(Exception ex){
       return false;
       }
        return false;
   }
   
   private Boolean checkDatatime(){
       try{
   if(datestart.getValue().toString().isEmpty()||datestart.getValue().toString().equals("")||dataend.getValue().toString().isEmpty()||dataend.getValue().toString().equals(""))
   {
         Alert a =new Alert(Alert.AlertType.ERROR,"حدد المدة ", ButtonType.OK);
                  a.showAndWait();
                  return false;
        }
       }catch(Exception ex){
          Alert a =new Alert(Alert.AlertType.ERROR,"حدد المدة ", ButtonType.OK);
                  a.showAndWait();
                  return false;   
       }
       return true;
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDataOncompo();
    }    
    
      private void setDataOncompo(){
    
       GetDataMaterials data =new GetDataMaterials();
    
      TextFields.bindAutoCompletion(materialnamecompo, data.getDataArray());
    }
}
