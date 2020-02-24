/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GoldOne
 */
public class DBOperation {
    Connection con =null;
    
    public Connection getCon() throws ClassNotFoundException, SQLException{
        
        if(con==null){
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc","root","");
        }
        return con;
    }
    
    public boolean checkUser(String username, String pw) {
        try{
            getCon();
            Statement st = con.createStatement();
            Boolean res = false;
            ResultSet rs = st.executeQuery("select * from users");
            while(rs.next()){
                if (rs.getString("username").equals(username) && rs.getString("pw").equals(pw)) {
                    res = true;
                }
            }
            return res;
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
    }
    public boolean register(String username, String pw) {
        try{
            getCon();
            PreparedStatement ps = con.prepareStatement("insert into users values(?,?)");
            ps.setString(1, username);
            ps.setString(2, pw); 
            ps.execute();
            return true;
        }
        catch(SQLException | ClassNotFoundException e){
          return false;
        }
    }
     public boolean insertRecord(String id, String name){        
        try{
            getCon();
            PreparedStatement ps = con.prepareStatement("insert into KK values(?,?)");
            
            ps.setString(1, id);
            ps.setString(2, name); 
            ps.execute();
            
            return true;
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println("Insert record error");
        }
        return false;
    }
     
       public boolean DeleteRecord (KK s){
        String id = s.getId();
       
        try{
            getCon();
            Statement st = con.createStatement();
            
            st.executeUpdate("delete from KK where id ='"+id+"'");
           
        
        return true;
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println("Insert record error");
        }
        return false;
    }
    
        public boolean UpdateRecord (KK s){
        String id = s.getId();
        String name = s.getName();
       
        try{
            getCon();
            Statement st = con.createStatement();
            
            st.execute(" update KK set name='"+name+"' where id ='"+id+"' ");
           
        
        return true;
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println("Insert record error");
        }
        return false;
    }
        
        
         public ResultSet ViewAll (){
   
            try{
                getCon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from KK");
                return rs;
            }
            catch(SQLException | ClassNotFoundException e){
                return null;
            }
         }
        public ResultSet viewRecord(String id){
            try{
                getCon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from KK where id =" + id);
                return rs;
            }
            catch(SQLException | ClassNotFoundException e){
                return null;
            }
    }
    
}
