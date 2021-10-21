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
import marktingprograme.DataBase.SellingHistory;

/**
 *
 * @author omar
 */
public class OpOnSellingHistory {
    private final Connection_To_Server contoserver =new Connection_To_Server();
    private final Connection con ;
    private final  int idInvoice;
    private final  int idMaterial;
    private final  String materialName;
    private final  String sellingPrice;
    private final  String countOfSelling;
    private final  String sellingTotalPrice;
    private final  String note;
    private final  String winTotalPrice;
    private final  String dateOfSelling;
    private int sumtotal=0;
    private int sumwintotal=0;
    private final ObservableList<SellingHistory> data = FXCollections.observableArrayList();
    
    private final String insertSQLCommond="INSERT INTO SellingHistory (idInvoice,idMaterial,materialName,sellingPrice,countOfSelling,sellingTotalPrice,note,winTotalPrice,dateOfSelling) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String updateSQLCommond="UPDATE  SellingHistory SET  idInvoice=?,idMaterial=?,materialName=?,sellingPrice=? ,countOfSelling=?,sellingTotalPrice=?,note=?,winTotalPrice=?,dateOfSelling=? WHERE id=";
    private final String deleteSQLCommond="DELETE FROM SellingHistory WHERE id=";
    private String sqlcommond="SELECT *FROM SellingHistory where ";
    private  String updateSQLCommondid="UPDATE  SellingHistory SET  sellingPrice=? ,countOfSelling=?,sellingTotalPrice=?,winTotalPrice=? WHERE id=";
    

     public OpOnSellingHistory(int idInvoice, int idMaterial, String materialName, String sellingPrice, String countOfSelling, String sellingTotalPrice, String note, String winTotalPrice, String dateOfSelling) {
        this.idInvoice = idInvoice;
        this.idMaterial = idMaterial;
        this.materialName =  materialName;
        this.sellingPrice =  sellingPrice;
        this.countOfSelling =  countOfSelling;
        this.sellingTotalPrice =  sellingTotalPrice;
        this.note =  note;
        this.winTotalPrice =  winTotalPrice;
        this.dateOfSelling =  dateOfSelling;
        con=contoserver.getConnection_To_DataBase();
    }





    public boolean  InsertData() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setInt(1, idInvoice);
                    statment.setInt(2, idMaterial);
                    statment.setString(3, materialName);
                    statment.setString(4, sellingPrice);
                    statment.setString(5, countOfSelling);
                    statment.setString(6, sellingTotalPrice);
                    statment.setString(7, note);
                    statment.setString(8, winTotalPrice);
                    statment.setString(9, dateOfSelling);
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
    
     public boolean  InsertData(int i) {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                     statment.setInt(1, idInvoice);
                    statment.setInt(2, idMaterial);
                    statment.setString(3, materialName);
                    statment.setString(4, sellingPrice);
                    statment.setString(5, countOfSelling);
                    statment.setString(6, sellingTotalPrice);
                    statment.setString(7, note);
                    statment.setString(8, winTotalPrice);
                    statment.setString(9, dateOfSelling);
                    statment.executeUpdate();
                    con.close();
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
                    statment.setInt(1, idInvoice);
                    statment.setInt(2, idMaterial);
                    statment.setString(3, materialName);
                    statment.setString(4, sellingPrice);
                    statment.setString(5, countOfSelling);
                    statment.setString(6, sellingTotalPrice);
                    statment.setString(7, note);
                    statment.setString(8, winTotalPrice);
                    statment.setString(9, dateOfSelling);
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
    
  public boolean UpDateDataid(int ID,int count) {
               try {
               
                   PreparedStatement statment= con.prepareStatement(updateSQLCommondid+ID);
                   String str =String.valueOf(Integer.parseInt(countOfSelling)-count);
                    statment.setString(1, sellingPrice);
                    statment.setString(2, str);
                    statment.setString(3, sellingTotalPrice);
                    statment.setString(4, winTotalPrice);
                    statment.executeUpdate();
                    con.close();
                   
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
    
    

    
   public ObservableList<SellingHistory> getDataSearch(){
  
     sqlcommond+=getStringSql();
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommond);
            while(rs.next()){  
                 SellingHistory table= new SellingHistory(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));                      
                 data.add( table);       
                 try{
                  sumwintotal+=Integer.parseInt(rs.getString("winTotalPrice"));
                 }catch(Exception ex ){
                 sumwintotal+=0;
                 }
                 try{
                  sumtotal+=Integer.parseInt(rs.getString("sellingPrice"))*Integer.parseInt(rs.getString("countOfSelling"));
                 }catch(Exception ex){
                 sumtotal+=0;
                 }
                 }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }

    public int getSumtotal() {
        return sumtotal;
    }

    public int getSumwintotal() {
        return sumwintotal;
    }
     private String getStringSql(){
    String path="";
    if(materialName.length()>0){
         path+=" materialName like '%"+materialName+"%'";
    }
    if(idInvoice>0){
            String idf= String.valueOf(idInvoice);
         if(materialName.length()>0)
             path+=" and ";
             path+=" idInvoice like '%"+idf+"%'";
    }
    if(dateOfSelling.length()>0){
         if(materialName.length()>0&&idInvoice>0)
             path+=" and ";
          path+=" dateOfSelling like '"+dateOfSelling+"'";
    }
   
   if(path.length()==0){
   path+=" materialName like '%"+materialName+"%'";
   }
    return path;
    }
}
