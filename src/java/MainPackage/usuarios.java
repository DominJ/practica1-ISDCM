/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.sql.SQLException;

/**
 *
 * @author fiblabs
 */
public class usuarios 
{
    boolean login;
    String nombre, apellido, email, user, pass;
    DBManager manager = null;
    
    usuarios(String user, String pass)
    {
        this.login = true;
        this.user = user;
        this.pass = pass;
        //this.manager = new DBManager();
    }
    usuarios(String nombre, String apellido, String user, String email, String pass)
    {
        this.login = false;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.user = user;
        this.pass = pass;
        //this.manager = new DBManager();
    }
    
    void registrar() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        //manager.conectar("jdbc:derby://localhost:1527/videoClub", "root", "root");
        //manager.conectar();
        if(DBManager.userExist(this.user))
        {
            //ERROR
        }
        else
        {
            //REGISTRAMOS
        }
    }
    
    int login() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        int errorCode = 0;
        //manager.conectar("jbdc:derby://localhost:1527/videoClub", "root", "root");
        //manager.conectar();
        if(!(DBManager.userExist(this.user)))
        {
            //ERROR
            errorCode = -3;
        }
        else
        {
            //LOGIN
            if(DBManager.userExist(this.user, this.pass))
            {
                //USER Y PASS CORRECTOS
                errorCode = 0;
            }
            else
            {
                //PASS INCORRECTA
                errorCode = -2;
            }
        }
        //manager.cerrarConexion();
        return errorCode;
    }
    //verificar que no exista el usuario
    //insertar el usuario si existe
    //dar login
    /*DBManager manager = new DBManager();
        manager.conectar("c:derby://localhost:1527/videoClub", "root", "root");
        manager.executeSQLcode("INSERT INTO");
        manager.cerrarConexion();*/
}
