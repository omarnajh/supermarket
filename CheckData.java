package marktingprograme.DataBase.OpOnData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import marktingprograme.DataBase.Connection_To_Server;

/**
 *
 * @author omapc
 */
public class CheckData {
     private final Connection_To_Server contoserver =new Connection_To_Server();
     private final Connection con ;
     private final String SearchSQL="SELECT * FROM SettingSystem";
     private String name="";
     private int i =0;
  
     
     public CheckData(){
        con=contoserver.getConnection_To_DataBase();
        
     }
    
     
    public boolean getData(String username,String password){
       
        try{
                     ResultSet rs = con.createStatement().executeQuery(SearchSQL);
                    while(rs.next()){
                        i++;
                            if(username.equals(rs.getString("currenyName"))&&password.equals(rs.getString("systemPassword")))
                            {
                               
                                name=rs.getString("mangerName");
                                return true;
                            
                            }
                        
                    }
               } catch (SQLException ex) {
                   
               }
   return  false;
    }

    public String getName() {
        return name;
    }
     public int getSizeof(){
     return i;
     }
    
}
