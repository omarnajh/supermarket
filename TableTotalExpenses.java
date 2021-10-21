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
public class TableTotalExpenses {
    private final String TotalExpenses  = "CREATE TABLE IF NOT EXISTS TotalExpenses ("+
              "id int(11) NOT NULL AUTO_INCREMENT,"+
              "dateExpenses     varchar(100) NOT NULL,"+
              "subjectExpenses     varchar(100) NOT NULL,"+
              "amountTotal     varchar(100) NOT NULL,"+
              "note     varchar(100) NOT NULL,"+
              "PRIMARY KEY (id)"+
              ")";
    private final  Connection con;
    
    public TableTotalExpenses(Connection con){
    this.con=con;
        create_Tables(TotalExpenses);
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
