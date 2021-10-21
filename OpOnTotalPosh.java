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
import marktingprograme.DataBase.TotalPosh;

/**
 *
 * @author omar
 */
public class OpOnTotalPosh {
private final Connection_To_Server contoserver =new Connection_To_Server();
    private final Connection con ;
    private final  String name;
    private final String total;
    private final String idinvoi;
    private final String date;
    private final String posh;
    private final String res;
    private int sumall=0;
    private int sumsub=0;
    private final ObservableList<TotalPosh> data = FXCollections.observableArrayList();
    
    private final String insertSQLCommond="INSERT INTO TotalPosh (name,total,idinvoi,date,posh,res) VALUES (?,?,?,?,?,?)";
    private final String updateSQLCommond="UPDATE  TotalPosh SET  name=?,total=?,idinvoi=?,date=?,posh=?,res=? WHERE id=";
    private final String deleteSQLCommond="DELETE FROM TotalPosh WHERE id=";
    private String sqlcommond="SELECT *FROM TotalPosh where name like '%";

    public OpOnTotalPosh( String name, String total, String idinvoi, String date,String posh,String res) {
        this.name =  (name);
        this.total =  (total);
        this.idinvoi =  (idinvoi);
        this.date =  (date);
         this.posh =  (posh);
        this.res =  (res);
        con=contoserver.getConnection_To_DataBase();
    }





    public boolean  InsertData() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setString(1, name);
                    statment.setString(2, total);
                    statment.setString(3, idinvoi);
                    statment.setString(4, date);
                    statment.setString(5, posh);
                    statment.setString(6, res);
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
                    statment.setString(1, name);
                    statment.setString(2, total);
                    statment.setString(3, idinvoi);
                    statment.setString(4, date);
                    statment.setString(5, posh);
                    statment.setString(6, res);
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
    
   public ObservableList<TotalPosh> getDataSearch(){
     sqlcommond+=name+"%'";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommond);
            while(rs.next()){  
                 TotalPosh table= new TotalPosh(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                  data.add(table);
                      try{
                         sumall+=Integer.parseInt(rs.getString(3));
                         }catch(Exception ex){
                         sumall+=0;
                         }
                         try{
                         sumsub+=Integer.parseInt(rs.getString(7));
                          }catch(Exception ex){
                         sumsub+=0;
                         }
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }  

    public int getSumall() {
        return sumall;
    }

    public int getSumsub() {
        return sumsub;
    }
   
}
