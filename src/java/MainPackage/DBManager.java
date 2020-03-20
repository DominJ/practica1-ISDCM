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
    
    private static String DBURL = "jbdc:derby://localhost:1527/videoClub";
    private static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String USER_DB = "root";
    private static String PASS_DB = "root";
    
    //private static Connection connection = null;
    //private static Statement st = null;
    
   
    public static boolean userExist(String user) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        Connection con = conectar();
        boolean exist = false;
        
        String query = "select usuario from usuarios where usuario="+user;
        System.out.println("query contruida");
        Statement st = con.createStatement();
        System.out.println("statement construido");
        ResultSet rs = st.executeQuery(query);
        System.out.println("query ejecutada");

        while(rs.next())
        {
            exist = true;
        }
        con.close();
        return exist;
    }
    
    public static boolean userExist(String user, String password) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        Connection con = conectar();
        boolean exist = false;
            String query = "select usuario from usuarios where usuario="+user+" AND pass="+password;
            System.out.println("query contruida");
            Statement st = con.createStatement();
            System.out.println("statement construido");
            ResultSet rs = st.executeQuery(query);
            System.out.println("query ejecutada");
            
            while(rs.next())
            {
                exist = true;
            }
            con.close();
        return exist;
    }
    
    private static Connection conectar() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {       
        Class.forName(DRIVER).newInstance();
        return DriverManager.getConnection(DBManager.DBURL, DBManager.USER_DB, DBManager.PASS_DB);
    }
    
    public static void executeSQLcode(String query)
    {
        /*PreparedStatement ps;
        try 
        {
            //ps = connection.prepareStatement(query);
            //ps.execute();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    /*public static void cerrarConexion(Connection connection)
    {
        try 
        {
            connection.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    /*void main()
    {
                
    }*/
}
