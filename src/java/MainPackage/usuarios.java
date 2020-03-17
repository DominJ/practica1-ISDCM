/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

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
    }
    usuarios(String nombre, String apellido, String user, String email, String pass)
    {
        this.login = false;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.user = user;
        this.pass = pass;
    }
    
    void registrar()
    {
        if(manager.userExist(this.user))
        {
            //ERROR
        }
        else
        {
            //REGISTRAMOS
        }
    }
    
    void login()
    {
        if(!(manager.userExist(this.user)))
        {
            //ERROR
        }
        else
        {
            //LOGIN
            if(manager.userExist(this.user, this.pass))
            {
                //USER Y PASS CORRECTOS
            }
            else
            {
                //PASS INCORRECTA
            }
        }
    }
    //verificar que no exista el usuario
    //insertar el usuario si existe
    //dar login
    /*DBManager manager = new DBManager();
        manager.conectar("c:derby://localhost:1527/videoClub", "root", "root");
        manager.executeSQLcode("INSERT INTO");
        manager.cerrarConexion();*/
}
