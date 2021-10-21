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
public class TableTotalPosh {
        private final String TotalPosh  = "CREATE TABLE IF NOT EXISTS TotalPosh ("+
              "id int(11) NOT NULL AUTO_INCREMENT,"+
              "name     varchar(100) NOT NULL,"+
              "total     varchar(100) NOT NULL,"+
              "idinvoi     varchar(100) NOT NULL,"+
              "date     varchar(100) NOT NULL,"+
              "posh     varchar(100) NOT NULL,"+
              "res     varchar(100) NOT NULL,"+
              "PRIMARY KEY (id)"+
              ")";
    private final  Connection con;
    
    public TableTotalPosh(Connection con){
    this.con=con;
        create_Tables(TotalPosh);
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
