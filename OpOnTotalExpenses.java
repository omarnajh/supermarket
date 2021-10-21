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
import marktingprograme.DataBase.TotalExpenses;

/**
 *
 * @author omar
 */
public class OpOnTotalExpenses {
   private final Connection_To_Server contoserver =new Connection_To_Server();
    private final Connection con ;
    private final  String dateExpenses;
    private final String subjectExpenses;
    private final String amountTotal;
    private final String note;
    
    private final ObservableList<TotalExpenses> data = FXCollections.observableArrayList();
    
    private final String insertSQLCommond="INSERT INTO TotalExpenses (dateExpenses,subjectExpenses,amountTotal,note) VALUES (?,?,?,?)";
    private final String updateSQLCommond="UPDATE  TotalExpenses SET  dateExpenses=?,subjectExpenses=?,amountTotal=?,note=? WHERE id=";
    private final String deleteSQLCommond="DELETE FROM TotalExpenses WHERE id=";
    private String sqlcommond="SELECT *FROM TotalExpenses where subjectExpenses like '%";

    public OpOnTotalExpenses( String dateExpenses, String subjectExpenses, String amountTotal, String note) {
        this.dateExpenses =  (dateExpenses);
        this.subjectExpenses =  (subjectExpenses);
        this.amountTotal =  (amountTotal);
        this.note =  (note);
        con=contoserver.getConnection_To_DataBase();
    }





    public boolean  InsertData() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setString(1, dateExpenses);
                    statment.setString(2, subjectExpenses);
                    statment.setString(3, amountTotal);
                    statment.setString(4, note);
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
                    statment.setString(1, dateExpenses);
                    statment.setString(2, subjectExpenses);
                    statment.setString(3, amountTotal);
                    statment.setString(4, note);
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
    
   public ObservableList<TotalExpenses> getDataSearch(){
     sqlcommond+=subjectExpenses+"%'";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommond);
            while(rs.next()){  
                 TotalExpenses table= new TotalExpenses(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                  data.add(table);
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }   
}
