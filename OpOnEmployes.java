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
import marktingprograme.DataBase.Employes;

/**
 *
 * @author omar
 */
public class OpOnEmployes {
    private final Connection_To_Server contoserver =new Connection_To_Server();
    private final Connection con ;
    private final  String employesName;
    private final  String employesPassword;
    private final  String employesLevel;
    
    private final ObservableList<Employes> data = FXCollections.observableArrayList();
    
    private final String insertSQLCommond="INSERT INTO Employes (employesName,employesPassword,employesLevel) VALUES (?,?,?)";
    private final String updateSQLCommond="UPDATE  Employes SET  employesName=?,employesPassword=?,employesLevel=? WHERE id=";
    private final String deleteSQLCommond="DELETE FROM Employes WHERE id=";
    private String sqlcommond="SELECT *FROM Employes where employesName like '%";

     public OpOnEmployes( String employesName, String employesPassword, String employesLevel) {
         this.employesName = employesName;
        this.employesPassword = employesPassword;
        this.employesLevel = employesLevel;
        con=contoserver.getConnection_To_DataBase();
    }





    public boolean  InsertData() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setString(1, employesName);
                    statment.setString(2, employesPassword);
                    statment.setString(3, employesLevel);
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
                    statment.setString(1, employesName);
                    statment.setString(2, employesPassword);
                    statment.setString(3, employesLevel);
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
    
   public ObservableList<Employes> getDataSearch(){
     sqlcommond+=employesName+"%'";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommond);
            while(rs.next()){  
                 Employes table= new Employes(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));                      
                 data.add( table);
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }
}
