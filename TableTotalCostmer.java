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
public class TableTotalCostmer {
     private final String TotalCostmer  = "CREATE TABLE IF NOT EXISTS TotalCostmer ("+
              "id int(11) NOT NULL AUTO_INCREMENT,"+
              "costmerName     varchar(100) NOT NULL,"+
              "costmerPhone     varchar(100) NOT NULL,"+
              "costmerId     varchar(100) NOT NULL,"+
              "costmerDate     varchar(100) NOT NULL,"+
              "costmerTotalMony     varchar(100) NOT NULL,"+
              "payType     varchar(100) NOT NULL,"+
              "payNote     varchar(100) NOT NULL,"+
              "PRIMARY KEY (id)"+
              ")";
    private final  Connection con;
    
    public TableTotalCostmer(Connection con){
    this.con=con;
        create_Tables(TotalCostmer);
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
