/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme.DataBase.OpOnData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import marktingprograme.DataBase.Connection_To_Server;
import marktingprograme.DataBase.TotalCostmer;

/**
 *
 * @author omar
 */
public class OpOnTotalCostmer {
    private final Connection_To_Server contoserver =new Connection_To_Server();
    private final Connection con ;
     private final String  costmerName;
    private final String costmerPhone;
    private final String costmerId;
    private final String costmerDate;
    private final String costmerTotalMony;
    private final String payType;
    private final String payNote;
    private int sum=0;
    
    private final ObservableList<TotalCostmer> data = FXCollections.observableArrayList();
    
    private final String insertSQLCommond="INSERT INTO TotalCostmer (costmerName,costmerPhone,costmerId,costmerDate,costmerTotalMony,payType,payNote) VALUES (?,?,?,?,?,?,?)";
    private final String updateSQLCommond="UPDATE  TotalCostmer SET  costmerName=?,costmerPhone=?,costmerId=?,costmerDate=? ,costmerTotalMony=?,payType=?,payNote=? WHERE id=";
    private final String deleteSQLCommond="DELETE FROM TotalCostmer WHERE id=";
    private String sqlcommond="SELECT *FROM TotalCostmer where costmerName like '%";
    private final String  updateSQLCommondname="UPDATE  TotalCostmer SET  costmerTotalMony=? WHERE costmerName = '";
    private final String sqlcommondname="SELECT *FROM TotalCostmer where costmerName ='";
    
      public OpOnTotalCostmer( String costmerName, String costmerPhone, String costmerId, String costmerDate, String costmerTotalMony, String payType, String payNote) {
        this.costmerName = costmerName;
        this.costmerPhone = costmerPhone;
        this.costmerId = costmerId;
        this.costmerDate =costmerDate;
        this.costmerTotalMony = costmerTotalMony;
        this.payType = payType;
        this.payNote = payNote;
        con=contoserver.getConnection_To_DataBase();
    }





    public boolean  InsertData() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setString(1, costmerName);
                    statment.setString(2, costmerPhone);
                    statment.setString(3, costmerId);
                    statment.setString(4, costmerDate);
                    statment.setString(5, costmerTotalMony);
                    statment.setString(6, payType);
                    statment.setString(7, payNote);
                    statment.executeUpdate();
                    con.close();
                    Alert a =new Alert(Alert.AlertType.INFORMATION,"تمت العملية بنجاح", ButtonType.OK);
                    a.showAndWait();
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,ex.toString(), ButtonType.OK);
                  a.showAndWait();
                  return false;
        }   
              return true;
    }

   
    public boolean UpDateData(int ID) {
               try {
               
            PreparedStatement statment= con.prepareStatement(updateSQLCommond+ID);
                    statment.setString(1, costmerName);
                    statment.setString(2, costmerPhone);
                    statment.setString(3, costmerId);
                    statment.setString(4, costmerDate);
                    statment.setString(5, costmerTotalMony);
                    statment.setString(6, payType);
                    statment.setString(7, payNote);
                    statment.executeUpdate();
                    con.close();
                    Alert a =new Alert(Alert.AlertType.INFORMATION,"تمت التعديل بنجاح", ButtonType.OK);
                    a.showAndWait();
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,"خطــــــا", ButtonType.OK);
                  a.showAndWait();
                  return false;
        } 
               return true;
    }
    
  public boolean UpDateDataprem(String name) {
               try {
               
                    PreparedStatement statment= con.prepareStatement(updateSQLCommondname+name+"'");
                    statment.setString(1, costmerTotalMony);
                    statment.executeUpdate();
                    con.close();
                   
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,ex.toString(), ButtonType.OK);
                     
                  a.showAndWait();
                  return false;
        } 
         
        
               return true;
    }
    
     public boolean UpDateData(String name) {
         if(getMony(name)>0){
               try {
               
                    PreparedStatement statment= con.prepareStatement(updateSQLCommondname+name+"'");
                    int totalmany = getMony(name)-Integer.parseInt(costmerTotalMony);
                    statment.setString(1, String.valueOf(totalmany));
                    statment.executeUpdate();
                    con.close();
                    Alert a =new Alert(Alert.AlertType.INFORMATION,"تمت التعديل بنجاح", ButtonType.OK);
                    a.showAndWait();
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,ex.toString(), ButtonType.OK);
                     
                  a.showAndWait();
                  return false;
        } 
         }
         else
         {
               Alert a =new Alert(Alert.AlertType.WARNING,"غلط في تعديل الدين على الزبون او  ان الزبون غير موجود في قائمة الديون.", ButtonType.OK);
                     
                  a.showAndWait();
         }
               return true;
    }
   
     private int getMony(String Name){
      try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommondname+Name+"'");
            while(rs.next()){  
                 TotalCostmer table= new TotalCostmer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));                      
                 data.add( table);
                 System.out.println("Mony rel ="+Integer.parseInt(rs.getString("costmerTotalMony")));
                       return Integer.parseInt(rs.getString("costmerTotalMony"));}

       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
      return 0;
     }
     
    public boolean DeleteData(int ID) {
            try {    
            PreparedStatement statment= con.prepareStatement(deleteSQLCommond+ID);
            statment.executeUpdate();
            con.close();
           Alert a =new Alert(Alert.AlertType.INFORMATION,"تمت الحذف بنجاح", ButtonType.OK);
                    a.showAndWait();
        } catch (SQLException ex) {
                 Alert a =new Alert(Alert.AlertType.ERROR,"خطــــــا", ButtonType.OK);
                  a.showAndWait();
                  return false;
        } 
        return true;
     }
    
   public ObservableList<TotalCostmer> getDataSearch(){
     sqlcommond+=costmerName+"%'";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommond);
           
            while(rs.next()){  
                 TotalCostmer table= new TotalCostmer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));                      
                 data.add( table);
                  try{
                       sum+=Integer.parseInt(rs.getString("costmerTotalMony"));}
                       catch(Exception ex){
                       sum+=0;
                       }
                  
            }
             
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }
   
     public int getSum() {
        return sum;
    }

 
     
}
