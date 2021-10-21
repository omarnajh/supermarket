/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author omar2_2
 */
public class Connection_To_Server {
     private final String URLServer ="jdbc:mysql://127.0.01:3306/";
     private final String User="root";
     private final String Password="1989";
     private final String LoadClassString="com.mysql.jdbc.Driver";
     private final String DataBaseName="SuperMarktting";
     private final String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
     private Connection conn;

    
    public Connection_To_Server(){
        LoadClass();
    }
    
    private void LoadClass(){
         try {
                 Class.forName(LoadClassString);
             } 
             catch (ClassNotFoundException ex) {
                   Alert a =new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK);
                  a.showAndWait();
             }
    }
     
    public Connection getConnection_To_DataBase(){
     try {
         conn = DriverManager.getConnection(URLServer+DataBaseName+unicode, User, Password);
     } 
     catch (SQLException ex) {
          Alert a =new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK);
                  a.showAndWait();
     }
     return conn;
    }
    
 
    public Connection getConnection_To_Server(){
     try {
         conn = DriverManager.getConnection(URLServer+unicode, User, Password);
     } 
     catch (SQLException ex) {
          Alert a =new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK);
                  a.showAndWait();
     }
     return conn;
    }

}
