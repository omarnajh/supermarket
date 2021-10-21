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
public class TableTotalPremiums {
    private final String TotalPremiums  = "CREATE TABLE IF NOT EXISTS TotalPremiums ("+
              "id int(11) NOT NULL AUTO_INCREMENT,"+
              "idIvoices     int(11) NOT NULL,"+
              "costmerName     varchar(100) NOT NULL,"+
              "premiumsDate     varchar(100) NOT NULL,"+
              "premiumsposh     varchar(100) NOT NULL,"+
              "PRIMARY KEY (id)"+
              ")";
    private final String create ="ALTER TABLE TotalPremiums ADD COLUMN IF NOT EXISTS settingsystem VARCHAR(100)";
    
    private final  Connection con;
    
    public TableTotalPremiums(Connection con){
    this.con=con;
        create_Tables(TotalPremiums);
         create_Tables(create);
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
