/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme.DataBase.OpOnData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import marktingprograme.DataBase.Connection_To_Server;
import marktingprograme.DataBase.Invoices;

/**
 *
 * @author omar
 */
public class OpOnInvoices {
    private final Connection_To_Server contoserver =new Connection_To_Server();
    private int Idin =0;
    private final Connection con ;
    private final String dateInvoices;
    private final String amontInvoices;
    private final String  numberBerlter;
    private final String  employName;
    private final  String typePay;
    private final  String stateIn;
    private final  String dar;
    private final  String dis;
    private final  String finalTotal;
    private final  String winTotal;
     private int sumtotal=0;
    private int sumwintotal=0;
    
    private final ObservableList<Invoices> data = FXCollections.observableArrayList();
    
    private final String insertSQLCommond="INSERT INTO Invoices (dateInvoices,amontInvoices,numberBerlter,employName,typePay,stateIn,dar,dis,finalTotal,winTotal) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String updateSQLCommond="UPDATE  Invoices SET  dateInvoices=?,amontInvoices=?,numberBerlter=?,employName=? ,typePay=?,stateIn=?,dar=?,dis=?,finalTotal=?,winTotal=? WHERE id=";
    private final String deleteSQLCommond="DELETE FROM Invoices WHERE id=";
    private String sqlcommond="SELECT *FROM Invoices where employName like '%";
    private String sqlcommondtime="SELECT *FROM Invoices where dateInvoices like '%";
    private final String updateSQLCommondsum="UPDATE  Invoices SET  amontInvoices=?,finalTotal=?,winTotal=? WHERE id=";
    private String sqldatebetween= " select * from Invoices where dateInvoices between ";
    
     public OpOnInvoices( String dateInvoices, String amontInvoices, String numberBerlter, String employName, String typePay, String stateIn, String dar, String dis, String finalTotal, String winTotal) {
        this.dateInvoices = dateInvoices;
        this.amontInvoices = amontInvoices;
        this.numberBerlter = numberBerlter;
        this.employName =employName;
        this.typePay = typePay;
        this.stateIn = stateIn;
        this.dar = dar;
        this.dis = dis;
        this.finalTotal = finalTotal;
        this.winTotal = winTotal;
        con=contoserver.getConnection_To_DataBase();
    }






    public boolean  InsertData() {
              try {
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setString(1, dateInvoices);
                    statment.setString(2, amontInvoices);
                    statment.setString(3, numberBerlter);
                    statment.setString(4, employName);
                    statment.setString(5, typePay);
                    statment.setString(6, stateIn);
                    statment.setString(7, dar);
                    statment.setString(8, dis);
                    statment.setString(9, finalTotal);
                    statment.setString(10, winTotal);
                    statment.executeUpdate();
                    con.close();
                    Alert a =new Alert(Alert.AlertType.INFORMATION,"تمت العملية بنجاح", ButtonType.OK);
                    a.showAndWait();
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,ex.toString(), ButtonType.OK);
                  a.showAndWait();
                  return false;
        }   
              return true;
    }

   
    public boolean UpDateData(int ID) {
               try {
               
            PreparedStatement statment= con.prepareStatement(updateSQLCommond+ID);
                    statment.setString(1, dateInvoices);
                    statment.setString(2, amontInvoices);
                    statment.setString(3, numberBerlter);
                    statment.setString(4, employName);
                    statment.setString(5, typePay);
                    statment.setString(6, stateIn);
                    statment.setString(7, dar);
                    statment.setString(8, dis);
                    statment.setString(9, finalTotal);
                    statment.setString(10, winTotal);
                    statment.executeUpdate();
                    con.close();
                    Alert a =new Alert(Alert.AlertType.INFORMATION,"تمت التعديل بنجاح", ButtonType.OK);
                    a.showAndWait();
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,"خطــــــا", ButtonType.OK);
                  a.showAndWait();
                  return false;
        } 
               return true;
    }

        public boolean UpDateDatasum(int ID) {
               try {
               
            PreparedStatement statment= con.prepareStatement(updateSQLCommondsum+ID);
                    statment.setString(1, amontInvoices);                  
                    statment.setString(2, finalTotal);
                    statment.setString(3, winTotal);
                    statment.executeUpdate();
                    con.close();
                   
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,ex.toString(), ButtonType.OK);
                  a.showAndWait();
                  return false;
        } 
               return true;
    }
        
    public boolean DeleteData(int ID) {
            try {    
            PreparedStatement statment= con.prepareStatement(deleteSQLCommond+ID);
            statment.executeUpdate();
            con.close();
           Alert a =new Alert(Alert.AlertType.INFORMATION,"تمت الحذف بنجاح", ButtonType.OK);
                    a.showAndWait();
        } catch (SQLException ex) {
                 Alert a =new Alert(Alert.AlertType.ERROR,"خطــــــا", ButtonType.OK);
                  a.showAndWait();
                  return false;
        } 
        return true;
     }
    
   public ObservableList<Invoices> getDataSearch(){
     sqlcommond+=employName+"%'";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommond);
            while(rs.next()){  
                 Invoices table= new Invoices(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));                      
                 data.add( table);
              try{
                     sumtotal+=Integer.parseInt(rs.getString("amontInvoices"));
                     }catch(Exception ex){
                     sumtotal+=0;
                     }
                     try{
                    sumwintotal+=Integer.parseInt(rs.getString("winTotal"));}
                     catch(Exception ex){
                       sumwintotal+=0;
                     } }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }
   public ObservableList<Invoices> getDateSearch(String endDate){//\"2021/04/21\" and \"2021/04/23:13_23_30\"";
     sqldatebetween+="\""+dateInvoices+"\""+" and  "+"\""+ endDate+"\"";
       try{
           System.out.println(sqldatebetween);
             ResultSet rs = con.createStatement().executeQuery(sqldatebetween);
            while(rs.next()){  
                 Invoices table= new Invoices(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));                      
                 data.add( table);
              try{
                     sumtotal+=Integer.parseInt(rs.getString("amontInvoices"));
                     }catch(Exception ex){
                     sumtotal+=0;
                     }
                     try{
                    sumwintotal+=Integer.parseInt(rs.getString("winTotal"));}
                     catch(Exception ex){
                       sumwintotal+=0;
                     } }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }
   
   
           
     public ObservableList<Invoices> getDataSearchtime(){
     sqlcommondtime+=dateInvoices+"%'";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommondtime);
            while(rs.next()){  
                 Invoices table= new Invoices(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));                      
                Idin=rs.getInt(1);
                 data.add( table);
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }

    public int getIdin() {
        return Idin;
    }

    public int getSumtotal() {
        return sumtotal;
    }

    public int getSumwintotal() {
        return sumwintotal;
    }
     
    
}
