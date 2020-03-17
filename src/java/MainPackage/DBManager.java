/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fiblabs
 */
public class DBManager {
    
    Connection connection = null;
    
    DBManager()
    {
        //constructor
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }
        catch (ClassNotFoundException ex)
        {   
            System.out.println("Error al registrar el driver");
        }
    }
    
    boolean userExist(String user)
    {
        boolean exist = false;
        try 
        {
            String query = "select usuario from usuarios where usuario="+user;
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                exist = true;
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }
    
    boolean userExist(String user, String password)
    {
        boolean exist = false;
        try 
        {
            String query = "select usuario from usuarios where usuario="+user+" AND pass="+password;
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next())
            {
                exist = true;
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }
    
    void conectar(String url, String user, String pass)
    {       
        try 
        {
            this.connection = DriverManager.getConnection(url,user,pass);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Me he conectado");
    }
    
    void executeSQLcode(String query)
    {
        PreparedStatement ps;
        try 
        {
            ps = this.connection.prepareStatement(query);
            ps.execute();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void cerrarConexion()
    {
        try 
        {
            this.connection.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void main()
    {
                
    }
}
