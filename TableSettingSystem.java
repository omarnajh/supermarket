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
 *    private final SimpleIntegerProperty id ;
    private final SimpleStringProperty  centerName;
    private final SimpleStringProperty mangerName;
    private final SimpleStringProperty centerPhone;
    private final SimpleStringProperty systemPassword;
    private final SimpleStringProperty natureMaterial;
    private final SimpleStringProperty currenyName;
 * @author omar
 */
public class TableSettingSystem {
     private final String SettingSystem  = "CREATE TABLE IF NOT EXISTS SettingSystem ("+
              "id int(11) NOT NULL AUTO_INCREMENT,"+
              "centerName     varchar(100) NOT NULL,"+
              "mangerName     varchar(100) NOT NULL,"+
              "centerPhone     varchar(100) NOT NULL,"+
              "systemPassword     varchar(100) NOT NULL,"+
              "natureMaterial     varchar(100) NOT NULL,"+
              "currenyName     varchar(100) NOT NULL,"+
             "adsress     varchar(100) NOT NULL,"+
              "PRIMARY KEY (id)"+
              ")";
    private final  Connection con;
    
    public TableSettingSystem(Connection con){
    this.con=con;
        create_Tables(SettingSystem);
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
