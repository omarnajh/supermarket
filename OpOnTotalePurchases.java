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
import marktingprograme.DataBase.TotalePurchases;

/**
 *
 * @author omar
 */
public class OpOnTotalePurchases {
    private final Connection_To_Server contoserver =new Connection_To_Server();
    private final Connection con ;
    private final int idMaterial;
    private final String  materialName;
    private final String dateProdect;
    private final String dateExp;
    private final String materialCount;
    private final String companyName;
    private final String senderName;
    private final String senderPhone;
    private final String datePurchases;
    private final int idInvoices;
    private final String invoicesAmont;
    private final String notePurcheses;
    private int Sumtotal=0;
    private final ObservableList<TotalePurchases> data = FXCollections.observableArrayList();
    
    private final String insertSQLCommond="INSERT INTO TotalePurchases (idMaterial,materialName,dateProdect,dateExp,materialCount,companyName,senderName,senderPhone,datePurchases,idInvoices,invoicesAmont,notePurcheses) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String updateSQLCommond="UPDATE  TotalePurchases SET  idMaterial=?,materialName=?,dateProdect=?,dateExp=? ,materialCount=?,companyName=?,senderName=?,senderPhone=?,datePurchases=?,idInvoices=?,invoicesAmont=?,notePurcheses=? WHERE id=";
    private final String deleteSQLCommond="DELETE FROM TotalePurchases WHERE id=";
    private String sqlcommond="SELECT *FROM TotalePurchases where materialName like '%";

     public OpOnTotalePurchases( int idMaterial, String materialName, String dateProdect, String dateExp, String materialCount, String companyName, String senderName, String senderPhone, String datePurchases, int idInvoices, String invoicesAmont, String notePurcheses) {
       this.idMaterial =  (idMaterial);
        this.materialName = (materialName);
        this.dateProdect = ( dateProdect);
        this.dateExp =  (dateExp);
        this.materialCount = ( materialCount);
        this.companyName =  (companyName);
        this.senderName =  (senderName);
        this.senderPhone = ( senderPhone);
        this.datePurchases = ( datePurchases);
        this.idInvoices = ( idInvoices);
        this.invoicesAmont =  (invoicesAmont);
        this.notePurcheses = ( notePurcheses);
        con=contoserver.getConnection_To_DataBase();
    }





    public boolean  InsertData() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setInt(1, idMaterial);
                    statment.setString(2, materialName);
                    statment.setString(3, dateProdect);
                    statment.setString(4, dateExp);
                    statment.setString(5, materialCount);
                    statment.setString(6, companyName);
                    statment.setString(7, senderName);
                    statment.setString(8, senderPhone);
                    statment.setString(9, datePurchases);
                    statment.setInt(10, idInvoices);
                    statment.setString(11, invoicesAmont);
                    statment.setString(12, notePurcheses);
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
                    statment.setInt(1, idMaterial);
                    statment.setString(2, materialName);
                    statment.setString(3, dateProdect);
                    statment.setString(4, dateExp);
                    statment.setString(5, materialCount);
                    statment.setString(6, companyName);
                    statment.setString(7, senderName);
                    statment.setString(8, senderPhone);
                    statment.setString(9, datePurchases);
                    statment.setInt(10, idInvoices);
                    statment.setString(11, invoicesAmont);
                    statment.setString(12, notePurcheses);
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
    
   public ObservableList<TotalePurchases> getDataSearch(){
     sqlcommond+=materialName+"%'";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommond);
            while(rs.next()){  
                 TotalePurchases table= new TotalePurchases(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11),rs.getString(12),rs.getString(13));                      
                 data.add( table);
                 Sumtotal+=Integer.parseInt(rs.getString("invoicesAmont"));
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }

    public int getSumtotal() {
        return Sumtotal;
    }
   
}
