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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import marktingprograme.DataBase.Connection_To_Server;
import marktingprograme.DataBase.Materials;

/**
 *
 * @author omar
 */
public class OpOnMaterial {
   private final Connection_To_Server contoserver =new Connection_To_Server();
    private final Connection con ;
    private final String barcode;
    private  String materialName;
    private int idn =0;
    private final int countAvailable;
    private final int priceGet;
    private final int priceSelling;
    private final String expDate;
    private final String mosName;
    private final int colgetprice;
    private int sum=0;
    
    private final ObservableList<Materials> data = FXCollections.observableArrayList();
    
    private final String insertSQLCommond="INSERT INTO Materials (barcode,materialName,countAvailable,priceGet,priceSelling,expDate,mosName,colgetprice) VALUES (?,?,?,?,?,?,?,?)";
    private final String updateSQLCommond="UPDATE  Materials SET barcode=?, materialName=?,countAvailable=?,priceGet=?,priceSelling=?,expDate=? ,mosName=? ,colgetprice=? WHERE id=";
    private final String updateSQLCommondname="UPDATE  Materials SET  countAvailable=? WHERE id=";
    private final  String deleteSQLCommond="SELECT *FROM Materials WHERE countAvailable < ";
    private String sqlcommond="SELECT *FROM Materials where ";
    private String sqlcommondname="SELECT * FROM Materials where materialName ='";
    private String sqlcommondid ="SELECT * FROM Materials where id =";
    private String sqlcommonddate ="select * from Materials  WHERE expDate BETWEEN ";
    private final String updateSQLCommondid="UPDATE  Materials SET  countAvailable=? WHERE id=";
    private final String refrsh ="UPDATE Materials SET colgetprice = countAvailable * priceGet WHERE id>0";
   
    public OpOnMaterial(String barcode,String materialName, int countAvailable,int priceGet ,int priceSelling, String expDate, String mosName, int colgetprice) {
        this.barcode=barcode;
        this.materialName = materialName;
        this.countAvailable = countAvailable;
        this.priceGet=priceGet;
        this.priceSelling = priceSelling;
        this.expDate = expDate;
        this.mosName = mosName;
        this.colgetprice=colgetprice;
        con=contoserver.getConnection_To_DataBase();
    }



public void refreshData(){
  try {
                  System.out.println(refrsh);
                    PreparedStatement statment= con.prepareStatement(refrsh);
                    statment.executeUpdate();
                    con.close();
                    
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,ex.toString(), ButtonType.OK);
                  a.showAndWait();
                  
        } 
}

    public boolean  InsertData() {
              try {
                  System.out.println(insertSQLCommond);
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    statment.setString(1, barcode);
                    statment.setString(2, materialName);
                    statment.setInt(3, countAvailable);
                    statment.setInt(4, priceGet);
                    statment.setInt(5, priceSelling);
                    statment.setString(6, expDate);
                    statment.setString(7, mosName);
                    statment.setInt(8, colgetprice);
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
                   System.out.println(updateSQLCommond+ID+"    2");
                    PreparedStatement statment= con.prepareStatement(updateSQLCommond+ID);
                    statment.setString(1, barcode);
                    statment.setString(2, materialName);
                    statment.setInt(3, getCountAvailable(ID));
                    statment.setInt(4, priceGet);
                    statment.setInt(5, priceSelling);           
                    statment.setString(6, expDate);
                    statment.setString(7, mosName);
                    statment.setInt(8, colgetprice);
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
    
    private int getCountAvailable(int id){
        String sqlcommondcount="SELECT * FROM Materials where id ="+id;
         try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommondcount);
            while(rs.next()){  
              
                return rs.getInt("countAvailable")+countAvailable;
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
    return 0;
    }
    
    private int getCount(int id){
         sqlcommondid+=id+"";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommondid);
            while(rs.next()){  
               return Integer.parseInt(rs.getString("countAvailable"));
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }   
        
    return  0;
    }
    
  public boolean UpDateDataid(int ID,int number) {
               try {
                    System.out.println(updateSQLCommondid+ID+"    3");
                    PreparedStatement statment= con.prepareStatement(updateSQLCommondid+ID);
                    statment.setString(1, String.valueOf(number+getCount(ID)));
                 
                    statment.executeUpdate();
                    con.close();
                   
        } catch (SQLException ex) {
                      Alert a =new Alert(Alert.AlertType.ERROR,"خطــــــا", ButtonType.OK);
                  a.showAndWait();
                  return false;
        } 
               return true;
    }
        public boolean UpDateDataname(int id,int countAvailablego) {
         try {          
               PreparedStatement statment= con.prepareStatement(updateSQLCommondname+id+"");
                    statment.setString(1, String.valueOf(countAvailablego));
                    statment.executeUpdate();
                    con.close();
        } catch (SQLException ex) {
                  Alert a =new Alert(Alert.AlertType.ERROR,ex.toString()+"خطــــــا", ButtonType.OK);
                  a.showAndWait();
                  return false;
        } 
               return true;
    }
        
    public ObservableList<Materials> DeleteData() {
       try{
             ResultSet rs = con.createStatement().executeQuery(deleteSQLCommond+countAvailable);
            while(rs.next()){  
               Materials table= new Materials(rs.getInt("id"),rs.getString("materialName"),rs.getInt("countAvailable"),rs.getInt("priceGet"),rs.getInt("priceSelling"),rs.getString("expDate"),rs.getString("mosName"),rs.getInt("colgetprice"),rs.getString("barcode"));  data.add( table);
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
     }
    
  public ObservableList<Materials> getDataSearchname(String name){
     sqlcommondname+=name+"'";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommondname);
            while(rs.next()){  
              Materials table= new Materials(rs.getInt("id"),rs.getString("materialName"),rs.getInt("countAvailable"),rs.getInt("priceGet"),rs.getInt("priceSelling"),rs.getString("expDate"),rs.getString("mosName"),rs.getInt("colgetprice"),rs.getString("barcode"));   data.add( table);
                 idn=rs.getInt(1);
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }
  
   public ObservableList<Materials> getDataSearchdate(String date){
       System.out.println("date 1"+date);
      
       int first =Integer.parseInt(date.substring(5, 7))+  1;
       String datet="";
       if(first<10)
          datet = "0"+first;
       else
           datet=first+"";
       String date2= date.substring(0, 5)+datet+"/"+date.substring(8, 10);
       
     sqlcommonddate+="\""+date+"\" AND \""+date2+"\" ";
     try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommonddate);
            while(rs.next()){  
               Materials table= new Materials(rs.getInt("id"),rs.getString("materialName"),rs.getInt("countAvailable"),rs.getInt("priceGet"),rs.getInt("priceSelling"),rs.getString("expDate"),rs.getString("mosName"),rs.getInt("colgetprice"),rs.getString("barcode"));  data.add( table);
                 idn=rs.getInt(1);
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }
      public ObservableList<Materials> getDataSearchname(int id){
     sqlcommondid+=id+"";
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommondid);
            while(rs.next()){  
                Materials table= new Materials(rs.getInt("id"),rs.getString("materialName"),rs.getInt("countAvailable"),rs.getInt("priceGet"),rs.getInt("priceSelling"),rs.getString("expDate"),rs.getString("mosName"),rs.getInt("colgetprice"),rs.getString("barcode"));  data.add( table);
                 idn=rs.getInt(1);
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }
      
      public void closecon(){
       try {
           con.close();
       } catch (SQLException ex) {
           Logger.getLogger(OpOnMaterial.class.getName()).log(Level.SEVERE, null, ex);
       }
      }
      
      
   public ObservableList<Materials> getDataSearch(){
      if(materialName=="1nom"){
          sqlcommond+="barcode like '%";
       sqlcommond+=barcode+"%'";
       materialName="";
       }
      else{
         sqlcommond+=" materialName like '%";
       sqlcommond+=materialName+"%'";
      }
       try{
             ResultSet rs = con.createStatement().executeQuery(sqlcommond);
            while(rs.next()){  
               Materials table= new Materials(rs.getInt("id"),rs.getString("materialName"),rs.getInt("countAvailable"),rs.getInt("priceGet"),rs.getInt("priceSelling"),rs.getString("expDate"),rs.getString("mosName"),rs.getInt("colgetprice"),rs.getString("barcode"));  
               data.add( table);
                 try{
                     sum+=Integer.parseInt(rs.getString("colgetprice"));
                     }catch(Exception exx){
                         sum+=0;
                     }
            }
       } catch (SQLException ex) {
           System.out.println("fild load data"+ex.toString());
       }
       return data;
   }

    public int getIdn() {
        return idn;
    }
   
    public int getSum() {
        return sum;
    }
}
