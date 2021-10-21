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
public class TableMaterials {
    private final String Materials  = "CREATE TABLE IF NOT EXISTS Materials ("+
              "id int(11) NOT NULL AUTO_INCREMENT,"+
              "materialName     varchar(100) NOT NULL,"+
              "countAvailable     INT(11) NOT NULL,"+
              "priceGet         INT(11) NOT NULL,"+
              "priceSelling     INT(11) NOT NULL,"+
              "expDate     varchar(100) NOT NULL,"+
              "mosName     varchar(100) NOT NULL,"+
              "colgetprice INT(11) NOT NULL,"+
              "barcode     varchar(100) NOT NULL,"+
              "PRIMARY KEY (id), UNIQUE(materialName,barcode))";
    private final String create ="ALTER TABLE Materials ADD COLUMN IF NOT EXISTS barcode varchar(100) NOT NULL";
    private final  Connection con;
    
    public TableMaterials(Connection con){
    this.con=con;
        create_Tables(Materials);
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
