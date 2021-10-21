/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marktingprograme.DataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

public class ImoprtDataFromExel {
    private final Connection_To_Server contoserver =new Connection_To_Server();
    private Connection con ;
   private File file;
   private final String nameimageuser="";
    private final String typeimage="";
    private final byte []datauserimage={0,0};
    private final String namefile="";
    private final String typefile="";
    private final byte []datafile={0,0};
    private final String insertSQLCommond="INSERT INTO Materials (materialName,countAvailable,priceGet,priceSelling,expDate,mosName) VALUES (?,?,?,?,?,?)";
    private final String insertSQLCommond2="INSERT INTO SellingHistory (idInvoice,idMaterial,materialName,sellingPrice,countOfSelling,sellingTotalPrice,note,winTotalPrice,dateOfSelling) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String insertSQLCommond3="INSERT INTO Invoices (dateInvoices,amontInvoices,numberBerlter,employName,typePay,stateIn,dar,dis,finalTotal,winTotal) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String insertSQLCommond4="INSERT INTO TotalCostmer (costmerName,costmerPhone,costmerId,costmerDate,costmerTotalMony,payType,payNote) VALUES (?,?,?,?,?,?,?)";
    private final String insertSQLCommond5="INSERT INTO TotalPremiums (idIvoices,costmerName,premiumsDate,premiumsposh) VALUES (?,?,?,?)";
    private final String insertSQLCommond6="INSERT INTO TotalePurchases (idMaterial,materialName,dateProdect,dateExp,materialCount,companyName,senderName,senderPhone,datePurchases,idInvoices,invoicesAmont,notePurcheses) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    
    public ImoprtDataFromExel( ) {
           OpenFileimage();
    }
        private void OpenFileimage(){
                   try {
                    FileChooser fileChooser = new FileChooser();
                   file = fileChooser.showOpenDialog(null);
                    FileInputStream fis = new FileInputStream(file);
            
                    } catch (IOException ex) {
                        System.out.println(ex.toString()+" error");
                    }
      }
        
    public void Materials(){
               try {
                  con =contoserver.getConnection_To_DataBase();
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond);
                    PreparedStatement statment2= con.prepareStatement(insertSQLCommond6);
                       System.out.println(file.length());
                       POIFSFileSystem fs = new POIFSFileSystem( file );
                      
                             HSSFWorkbook wb = new HSSFWorkbook(fs);
                         
                            HSSFSheet  seet =wb.getSheetAt(0);
                            
                            Row row;
                          

                            for(int i=1;i<=seet.getLastRowNum();i++)
                            {
                                row=seet.getRow(i);
                                statment2.setString (1, "0");
                                statment2.setString(3, "0");
                                statment2.setString(4, "0");
                                statment2.setString(6, "0");
                                statment2.setString(7, "0");
                                statment2.setString(8, "0");
                                statment2.setString(9, "0");
                                statment2.setString(10, "0");
                                 statment2.setString(11, "0");
                                statment2.setString(12, "0");
                                try{
                                statment.setString(1, row.getCell(1).toString());
                                statment2.setString(2, row.getCell(1).toString());
                                }catch(Exception ex){
                                   statment.setString(1, "0");  
                                   statment2.setString(2, "0");
                                }
                                try{
                                 int conut =(int)Math.round( Float.parseFloat(row.getCell(2).toString()));
                                  
                                statment.setString(2,conut+"" );
                                statment2.setString(5, conut+"");
                                }
                                catch(Exception ex){
                                    statment.setString(2, "0");
                                    statment2.setString(5, "0");
                                }
                                try{
                                int conut =(int)Math.round( Float.parseFloat(row.getCell(3).toString()));
                                statment.setString(3,conut+"");
                                }catch(Exception ex){
                                statment.setString(3,"0");
                                }
                                try{
                                int conut =(int)Math.round( Float.parseFloat(row.getCell(4).toString()));
                                statment.setString(4,conut+"");
                                }
                                catch(Exception ex){
                                statment.setString(4, "0");
                                }
                                try{
                                statment.setString(5, row.getCell(5).toString());
                                }catch(Exception ex)
                                {
                                statment.setString(5, "0");
                                }
                                try{
                                statment.setString(6, row.getCell(6).toString());
                                }
                                catch(Exception ex)
                                {
                                statment.setString(6, "0");
                                
                                }
                                statment.executeUpdate();
                         statment2.executeUpdate();
                            }
                    
               con.close();
                   
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("حفظ");
                        alert.setHeaderText(" المواد");
                        alert.setContentText("تم الحفظ بنــــــــــــــــجاح");
                        alert.showAndWait();

        } catch (SQLException |IOException ex) {
            
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("خطأ");
        alert.setHeaderText("خطاء حفظ معلومات");
        alert.setContentText(ex.toString());
        alert.showAndWait();
        
        }
        }
        
    public void SellingHistory(){
               try {
                   con =contoserver.getConnection_To_DataBase();
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond2);
                       System.out.println(file.length());
                       POIFSFileSystem fs = new POIFSFileSystem( file );
                      
                             HSSFWorkbook wb = new HSSFWorkbook(fs);
                         
                            HSSFSheet  seet =wb.getSheetAt(0);
                            
                            Row row;
                          

                            for(int i=1;i<=seet.getLastRowNum();i++)
                            {
                                row=seet.getRow(i);
                                statment.setString(1, row.getCell(1).toString());
                                statment.setString(2,row.getCell(2).toString());
                                statment.setString(3, row.getCell(3).toString());
                                statment.setString(4, row.getCell(4).toString());
                                statment.setString(5, row.getCell(5).toString());
                                statment.setString(6, row.getCell(6).toString());
                                statment.setString(7,row.getCell(7).toString());
                                statment.setString(8, row.getCell(8).toString());
                                statment.setString(9, row.getCell(9).toString());
                                statment.executeUpdate();
                             
                            }
                    
               
                   con.close();
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("حفظ");
                        alert.setHeaderText("تاريخ المبيعات");
                        alert.setContentText("تم الحفظ بنــــــــــــــــجاح");
                        alert.showAndWait();

        } catch (SQLException |IOException ex) {
            
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("خطأ");
        alert.setHeaderText("خطاء حفظ معلومات");
        alert.setContentText(ex.toString());
        alert.showAndWait();
        }
        }
                
    public void Invoices(){
               try {
                   con =contoserver.getConnection_To_DataBase();
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond3);
                       System.out.println(file.length());
                       POIFSFileSystem fs = new POIFSFileSystem( file );
                      
                             HSSFWorkbook wb = new HSSFWorkbook(fs);
                         
                            HSSFSheet  seet =wb.getSheetAt(0);
                            
                            Row row;
                          

                            for(int i=1;i<=seet.getLastRowNum();i++)
                            {
                                row=seet.getRow(i);
                                statment.setString(1, row.getCell(1).toString());
                                statment.setString(2,row.getCell(2).toString());
                                statment.setString(3, row.getCell(3).toString());
                                statment.setString(4, row.getCell(4).toString());
                                statment.setString(5, row.getCell(5).toString());
                                statment.setString(6, row.getCell(6).toString());
                                statment.setString(7,row.getCell(7).toString());
                                statment.setString(8, row.getCell(8).toString());
                                statment.setString(9, row.getCell(9).toString());
                                statment.setString(10, row.getCell(10).toString());
                                statment.executeUpdate();
                     
                            }
                    
               con.close();
                   
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("حفظ");
                        alert.setHeaderText("الفواتير");
                        alert.setContentText("تم الحفظ بنــــــــــــــــجاح");
                        alert.showAndWait();

        } catch (SQLException |IOException ex) {
            
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("خطأ");
        alert.setHeaderText("خطاء حفظ معلومات");
        alert.setContentText(ex.toString());
        alert.showAndWait();
        }
        }
    
      public void TotalCostmer(){
               try {
                   con =contoserver.getConnection_To_DataBase();
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond4);
                       System.out.println(file.length());
                       POIFSFileSystem fs = new POIFSFileSystem( file );
                      
                             HSSFWorkbook wb = new HSSFWorkbook(fs);
                         
                            HSSFSheet  seet =wb.getSheetAt(0);
                            
                            Row row;
                          

                            for(int i=1;i<=seet.getLastRowNum();i++)
                            {
                                row=seet.getRow(i);
                                statment.setString(1, row.getCell(1).toString());
                                statment.setString(2, row.getCell(2).toString());
                                statment.setString(3,row.getCell(3).toString());
                                statment.setString(4, row.getCell(4).toString());
                                statment.setString(5, row.getCell(5).toString());
                                statment.setString(6, row.getCell(6).toString());
                                statment.setString(7, row.getCell(7).toString());
                                statment.executeUpdate();
                             
                            }
                    
               
                   con.close();
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("حفظ");
                        alert.setHeaderText("الزبائن");
                        alert.setContentText("تم الحفظ بنــــــــــــــــجاح");
                        alert.showAndWait();

        } catch (SQLException |IOException ex) {
            
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("خطأ");
        alert.setHeaderText("خطاء حفظ معلومات");
        alert.setContentText(ex.toString());
        alert.showAndWait();
        }
        }
        
        public  void TotalPremiums(){
               try {
                   con =contoserver.getConnection_To_DataBase();
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond5);
                       System.out.println(file.length());
                       POIFSFileSystem fs = new POIFSFileSystem( file );
                      
                             HSSFWorkbook wb = new HSSFWorkbook(fs);
                         
                            HSSFSheet  seet =wb.getSheetAt(0);
                            
                            Row row;
                          

                            for(int i=1;i<=seet.getLastRowNum();i++)
                            {
                                row=seet.getRow(i);
                                statment.setString(1, row.getCell(1).toString());
                                statment.setString(2, row.getCell(2).toString());
                                statment.setString(3,row.getCell(3).toString());
                                statment.setString(4, row.getCell(4).toString());
                            }
                    
               
                   con.close();
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("حفظ");
                        alert.setHeaderText("اقساط الزبائن ");
                        alert.setContentText("تم الحفظ بنــــــــــــــــجاح");
                        alert.showAndWait();
        } catch (SQLException |IOException ex) {
            
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("خطأ");
        alert.setHeaderText("خطاء حفظ معلومات");
        alert.setContentText(ex.toString());
        alert.showAndWait();
        }
        }
                
  public  void GetData115(){
               try {
                   con =contoserver.getConnection_To_DataBase();
                    PreparedStatement statment= con.prepareStatement(insertSQLCommond6);
                       System.out.println(file.length());
                       POIFSFileSystem fs = new POIFSFileSystem( file );
                      
                             HSSFWorkbook wb = new HSSFWorkbook(fs);
                         
                            HSSFSheet  seet =wb.getSheetAt(5);
                            
                            Row row;
                          

                            for(int i=0;i<=seet.getLastRowNum();i++)
                            {
                                row=seet.getRow(i);
                                statment.setString(1,  row.getCell(0).toString());
                                statment.setString(2, row.getCell(1).toString());
                                statment.setString(3, row.getCell(2).toString());
                                statment.setString(4,  row.getCell(3).toString());
                                statment.setString(5,  row.getCell(4).toString());
                                statment.setString(6,  row.getCell(5).toString());
                                statment.setString(7,  row.getCell(6).toString());
                                statment.setString(8, row.getCell(7).toString());
                                statment.setString(9,  row.getCell(8).toString());
                                statment.setString(10, nameimageuser);
                                statment.setString(11, typeimage);
                                statment.setBytes(12, datauserimage);
                                statment.setString(13, namefile);
                                statment.setString(14, typefile);
                                statment.setBytes(15, datafile);
                                statment.executeUpdate();
                                
                          
                            }
                    
               con.close();
                   
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("حفظ");
                        alert.setHeaderText("قانون 220");
                        alert.setContentText("تم الحفظ بنــــــــــــــــجاح");
                        alert.showAndWait();

        } catch (SQLException |IOException ex) {
            
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("خطأ");
        alert.setHeaderText("خطاء حفظ معلومات");
        alert.setContentText(ex.toString());
        alert.showAndWait();
        }
        }
}
