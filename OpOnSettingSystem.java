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
import marktingprograme.DataBase.SettingSystem;

/**
 *
 * @author omar
 */
public class OpOnSettingSystem {
    private final Connection_To_Server contoserver =new Connection_To_Server();
    private final Connection con ;
    private final  String centerName;
    private final String mangerName;
    private final String centerPhone;
    private final String systemPassword;
    private final String natureMaterial;
    private final String currenyName;
    private final String adsress;
    private final ObservableList<SettingSystem> data = FXCollections.observableArrayList();
    
    private final String insertSQLCommond="INSERT INTO SettingSystem (centerName,mangerName,centerPhone,systemPassword,natureMaterial,currenyName,adsress) VALUES (?,?,?,?,?,?,?)";
    private final String updateSQLCommond="UPDATE  SettingSystem SET  centerName=?,mangerName=?,centerPhone=?,systemPassword=? ,natureMaterial=?,currenyName=?,adsress=? WHERE id=";
    private final String deleteSQLCommond="DELETE FROM SettingSystem WHERE id=";
    private String sqlcommond="SELECT *FROM SettingSystem where mangerName like '%";

    public OpOnSettingSystem( String centerName, String mangerName, String centerPhone, String systemPassword, String natureMaterial, String currenyName,String adsress) {
        this.centerName = (centerName);
        this.mangerName =  (mangerName);
        this.centerPhone =  (centerPhone);
        this.systemPassword =  (systemPassword);
        this.natureMaterial =  (natureMaterial);
        this.currenyName =  (currenyName);
        this.adsress = adsress;
        con=contoserver.getConnection_To_DataBase();
    }





    public boolean  InsertData() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setString(1, centerName);
                    statment.setString(2, mangerName);
                    statment.setString(3, centerPhone);
                    statment.setString(4, systemPassword);
                    statment.setString(5, natureMaterial);
                    statment.setString(6, currenyName);
                    statment.setString(7, adsress);
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
                   statment.setString(1, centerName);
                    statment.setString(2, mangerName);
                    statment.setString(3, centerPhone);
                    statment.setString(4, systemPassword);
                    statment.setString(5, natureMaterial);
                    statment.setString(6, currenyName);
                    statment.setString(7, adsress);
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
    
   public ObservableList<SettingSystem> getDataSearch(){
     sqlcommond+=mangerName+"%'";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommond);
            while(rs.next()){  
                 SettingSystem table= new SettingSystem(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));                      
                 data.add( table);
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }
}
