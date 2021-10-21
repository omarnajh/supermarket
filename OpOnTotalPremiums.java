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
import marktingprograme.DataBase.TotalPremiums;

/**
 *
 * @author omar
 */
public class OpOnTotalPremiums {
    private final Connection_To_Server contoserver =new Connection_To_Server();
    private final Connection con ;
    private  int  idIvoices;
    private  String costmerName;
    private  String premiumsDate;
    private  String premiumsposh;
    private  String settingsystem;
    private final ObservableList<TotalPremiums> data = FXCollections.observableArrayList();
    
    private final String insertSQLCommond="INSERT INTO TotalPremiums (idIvoices,costmerName,premiumsDate,premiumsposh,settingsystem) VALUES (?,?,?,?,?)";
    private final String updateSQLCommond="UPDATE  TotalPremiums SET  idIvoices=?,costmerName=?,premiumsDate=?,premiumsposh=?,settingsystem=? WHERE id=";
    private final String deleteSQLCommond="DELETE FROM TotalPremiums WHERE id=";
    private String sqlcommond="SELECT *FROM TotalPremiums where costmerName like '%";
    private final String sqlcommondid="SELECT *FROM TotalPremiums WHERE costmerName ='";
    
   
    private final String sqlinsertifnotexist="SELECT *FROM TotalPremiums ";

    public OpOnTotalPremiums( int idIvoices, String costmerName, String premiumsDate, String premiumsposh,String settingsystem) {
       this.idIvoices = (idIvoices);
        this.costmerName =  (costmerName);
        this.premiumsDate =  (premiumsDate);
        this.premiumsposh = (premiumsposh);
        this.settingsystem=settingsystem;
        con=contoserver.getConnection_To_DataBase();
    }

    

public void getCleant(){
    OpOnTotalCostmer op =new OpOnTotalCostmer("", "", "", "", "", "", "");
    
    op.getDataSearch().stream().forEach((tat) -> {
        try{
           
            if(getData(tat.getCostmerName(),sqlinsertifnotexist)){
                idIvoices=Integer.parseInt(tat.getCostmerTotalMony());
                costmerName=tat.getCostmerName();
                premiumsDate="";
                premiumsposh="";
                InsertData2();
                
            }
            else{
                idIvoices=Integer.parseInt(tat.getCostmerTotalMony());
                costmerName=tat.getCostmerName();
                getLastIDforCostemer();
                
            }
        }
        catch(Exception ex){
            Alert a =new Alert(Alert.AlertType.ERROR,ex.toString(), ButtonType.OK);
            a.showAndWait();
        }
    });
    try {

       con.close(); 
    } catch (Exception e) {
    }
    
    }

private void getLastIDforCostemer(){
    String  sql = "UPDATE TotalPremiums set idIvoices=? WHERE id ="+getDatalastid(costmerName);
    upDate2(sql);
}

   public boolean getData(String employname,String SearchSQL){
        try{
                     ResultSet rs = con.createStatement().executeQuery(SearchSQL);
                    while(rs.next()){
                            if(employname.equals(rs.getString("costmerName")))
                               return false; 
                    }
               } catch (SQLException ex) {
                   
               }
   return  true;
    }
   
     public int getDatalastid(String employname){
     int id =0;
      try{
             ResultSet rs = con.createStatement().executeQuery( sqlcommondid+employname+"'");
            while(rs.next()){  
               id=rs.getInt("id");
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return id;
    }
public boolean InsertDatapay() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setInt(1, idIvoices);
                    statment.setString(2, costmerName);
                    statment.setString(3, premiumsDate);
                    statment.setString(4, premiumsposh);
                    statment.setString(5, settingsystem);
                    statment.executeUpdate();
                    con.close();
                  
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,ex.toString(), ButtonType.OK);
                  a.showAndWait();
                  return false;
        }   
              return true;
    }

  public boolean  InsertData2() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setInt(1, idIvoices);
                    statment.setString(2, costmerName);
                    statment.setString(3, premiumsDate);
                    statment.setString(4, premiumsposh);
                    statment.setString(5, settingsystem);
                    statment.executeUpdate();

        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,ex.toString(), ButtonType.OK);
                  a.showAndWait();
                  return false;
        }   
              return true;
    }
    public boolean  InsertData() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setInt(1, idIvoices);
                    statment.setString(2, costmerName);
                    statment.setString(3, premiumsDate);
                    statment.setString(4, premiumsposh);
                    statment.setString(5, settingsystem);
                    statment.executeUpdate();
                    statment.setInt(1, idIvoices);
                    statment.setString(2, costmerName);
                    statment.setString(3, "");
                    statment.setString(4, "");
                    statment.setString(5, "");
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
                    statment.setInt(1, idIvoices);
                    statment.setString(2, costmerName);
                    statment.setString(3, premiumsDate);
                    statment.setString(4, premiumsposh);
                    statment.setString(5, settingsystem);
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
    
    private void upDate2(String sql){
                  try {
               
                     PreparedStatement statment= con.prepareStatement(sql);
                    statment.setInt(1, idIvoices);
                    statment.executeUpdate();
                   
                   
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,"خطا في جلب المجموع الكلي للزبون", ButtonType.OK);
                  a.showAndWait();
                 
        } 
    
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
    
   public ObservableList<TotalPremiums> getDataSearch(){
     sqlcommond+=costmerName+"%'";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommond);
            while(rs.next()){  
                 TotalPremiums table= new TotalPremiums(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                 data.add(table);
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }  

  
   
}
