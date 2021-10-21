/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme.DataBase.TableCreation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *   
 * @author omar
 */
public class TableSellingHistory {
    private final String SellingHistory = "CREATE TABLE IF NOT EXISTS SellingHistory("+
              "id int(11) NOT NULL AUTO_INCREMENT,"+
              "idInvoice     int(11) NOT NULL,"+
              "idMaterial    int(11) NOT NULL,"+
              "materialName     varchar(100) NOT NULL,"+
              "sellingPrice     varchar(100) NOT NULL,"+
              "countOfSelling     varchar(100) NOT NULL,"+
              "sellingTotalPrice     varchar(100) NOT NULL,"+
              "note     varchar(100) NOT NULL,"+
              "winTotalPrice     varchar(100) NOT NULL,"+
              "dateOfSelling     varchar(100) NOT NULL,"+
              "PRIMARY KEY (id)"+
              ")";
    private final  Connection con;
    
    public TableSellingHistory(Connection con){
    this.con=con;
        create_Tables(SellingHistory);
          closeCon();
    }
    
    private void create_Tables(String SQL){
             try {
                Statement s = con.createStatement();
                int Result = s.executeUpdate(SQL); 
              
                } 
         catch (SQLException ex) 
              {
                  Alert a =new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK);
                  a.showAndWait();
              }

    }
    
    private void closeCon(){
    try{
    con.close();
    }
    catch(SQLException ex){
      Alert a =new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK);
                  a.showAndWait();}
    }

}
