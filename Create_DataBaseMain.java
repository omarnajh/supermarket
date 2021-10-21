/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import marktingprograme.DataBase.TableCreation.*;
/**
 *
 * @author omar2_2
 */
public class Create_DataBaseMain {
    private final String SQLCreatDataBase="CREATE DATABASE IF NOT EXISTS SuperMarktting CHARACTER SET utf8 COLLATE utf8_general_ci";
    private final Connection_To_Server conect ;
    private final Connection con;

    
    public Create_DataBaseMain(){
        conect =new Connection_To_Server();
        con=conect.getConnection_To_Server();
        Create_Data_Base();
        TableEmployes t1= new TableEmployes(conect.getConnection_To_DataBase());
        TableInvoices t2 =new TableInvoices(conect.getConnection_To_DataBase());
        TableMaterials t3 = new TableMaterials(conect.getConnection_To_DataBase());
        TableSellingHistory t4 = new TableSellingHistory(conect.getConnection_To_DataBase());
        TableSettingSystem t5 = new TableSettingSystem(conect.getConnection_To_DataBase());
        TableTotalCostmer t6 = new TableTotalCostmer(conect.getConnection_To_DataBase());
        TableTotalExpenses t7 = new TableTotalExpenses(conect.getConnection_To_DataBase());
        TableTotalMost t8 = new TableTotalMost(conect.getConnection_To_DataBase());
        TableTotalPremiums t9 = new TableTotalPremiums(conect.getConnection_To_DataBase());
        TableTotalePurchases t10 =new TableTotalePurchases(conect.getConnection_To_DataBase());
        TableTotalPosh t11 =new TableTotalPosh(conect.getConnection_To_DataBase());
       closecon();
    }
    
    private void Create_Data_Base(){
         try {
                Statement s = con.createStatement();
                int Result = s.executeUpdate(SQLCreatDataBase); 
                
                } 
         catch (SQLException ex) 
              {
                Alert a =new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK);
                  a.showAndWait();
              }

    }
    
    private void closecon(){
        try{
           con.close();
        }
        catch(SQLException ex){
                  Alert a =new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK);
                  a.showAndWait();
        }
    }
}
