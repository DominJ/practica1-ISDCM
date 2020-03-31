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
    String user;
    int userID;
    
    usuarios()
    {
        this.login = false;
        this.user = null;
        this.userID = -1;
    }
    
    usuarios(boolean login, String user, int userID)
    {
        this.login = login;
        this.user = user;
        this.userID = userID;
    }
    
    public boolean getLogin()
    {
        return login;
    }
    
    public void setLogin(boolean login)
    {
        this.login = login;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public void setUser(String user)
    {
        this.user = user;
    }
    
    public int getID()
    {
        return this.userID;
    }
}
