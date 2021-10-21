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
public class TableTotalePurchases {
  private final String TotalePurchases = "CREATE TABLE IF NOT EXISTS TotalePurchases("+
              "id int(11) NOT NULL AUTO_INCREMENT,"+
              "idMaterial     int(11) NOT NULL,"+
              "materialName     varchar(100) NOT NULL,"+
              "dateProdect     varchar(100) NOT NULL,"+
              "dateExp     varchar(100) NOT NULL,"+
              "materialCount     varchar(100) NOT NULL,"+
              "companyName     varchar(100) NOT NULL,"+
              "senderName     varchar(100) NOT NULL,"+
              "senderPhone     varchar(100) NOT NULL,"+
              "datePurchases     varchar(100) NOT NULL,"+
              "idInvoices     int(11) NOT NULL,"+
              "invoicesAmont     varchar(100) NOT NULL,"+
              "notePurcheses     varchar(100) NOT NULL,"+
              "PRIMARY KEY (id)"+
              ")";
    private final  Connection con;
    
    public TableTotalePurchases(Connection con){
    this.con=con;
        create_Tables(TotalePurchases);
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
