/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fiblabs
 */
@WebServlet(name = "servletUsuarios", urlPatterns = {"/servletUsuarios"})
public class servletUsuarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection connection = null;
    String nombre;
    String apellidos;
    String correo;
    String user;
    String pass;
    String repass;
    String userlogin;
    String passlogin;
    
    
    boolean checkMail(String correo)
    {
        boolean checked = false;
        for(int i=0; i<=correo.length(); i++)
        {
            if(correo.charAt(i)== '@')
            {
                checked = true;
            }
        }
        return checked;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException 
    {
        
        response.setContentType("text/html;charset=UTF-8");
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        //conectar();
        //Statement statement = connection.createStatement();
        //statement.setQueryTimeout(30);
        //connection.close();
        
        try (PrintWriter out = response.getWriter()) 
        {
            
            nombre = request.getParameter("nombre");
            apellidos = request.getParameter("apellidos");
            correo = request.getParameter("correo");
            user = request.getParameter("user");
            pass = request.getParameter("pass");
            repass = request.getParameter("repass");
            userlogin = request.getParameter("userlogin");
            passlogin = request.getParameter("passlogin");

            if(userlogin == null && passlogin == null)
            {
                //Registro nuevo
                if(!(pass.equals(repass)))
                {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet servletUsuarios</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<p> La contraseña y su repetición son diferentes, por favor comprueba </p>");
                    out.println("<a href=\"registroUsu.jsp\">Volver</a>");
                    out.println("</body>");
                    out.println("</html>");
                }
                else if(checkMail(correo) == false)
                {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet servletUsuarios</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<p> El email es icorrecto </p>");
                    out.println("<a href=\"registroUsu.jsp\">Volver</a>");
                    out.println("</body>");
                    out.println("</html>");
                }
                else
                {
                    //usu = new usuarios(nombre, apellidos, correo, user, pass);
                    //usu.registrar();
                }
            }
            else
            {
                //Login
                if(userlogin == null || passlogin == null)
                {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet servletUsuarios</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("alguno de los 2 campos está en blanco, por favor, rellenalo de nuevo.");
                    out.println("<a href=\"login.jsp\">Volver</a>");
                    out.println("</body>");
                    out.println("</html>");
                }
                else
                {
                    out.println(userlogin);
                    conectar();
                    out.println(login());
                    desconectar();
                    //usu = new usuarios(userlogin, passlogin);
                    //int a = usu.login();
                    //out.println("aaaaaaaaaaaaa"+a);
                }
            }
        }    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void conectar() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/videoClub;user=root;password=root");
    }
    
    private void desconectar() throws SQLException
    {
        connection.close();
    }
    
    private int login() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        int errorCode = 0;
        if(userExist(this.userlogin))
        {
            //LOGIN
            if(userExist(this.userlogin, this.passlogin))
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
        else
        {
            //ERROR
            errorCode = -3;
            System.out.println("que no existe el puto user");
        }
        return errorCode;
    }
    
    private void registrar() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        if(userExist(user))
        {
            //ERROR
        }
        else
        {
            //REGISTRAMOS
        }
    }
    
    private boolean userExist(String user) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        boolean exist = false;
        
        String query = "select usuario from usuarios where usuario='"+user+"'";
        System.out.println("query contruida");
        Statement st = connection.createStatement();
        System.out.println("statement construido");
        ResultSet rs = st.executeQuery(query);
        System.out.println("query ejecutada");

        while(rs.next())
        {
            System.out.println(rs.getString("usuario"));
            exist = true;
        }
        return exist;
    }
    
    public boolean userExist(String user, String password) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        boolean exist = false;
        String query = "select usuario from usuarios where usuario='"+user+"' AND pass='"+password+"'";
        System.out.println("query contruida");
        Statement st = connection.createStatement();
        System.out.println("statement construido");
        ResultSet rs = st.executeQuery(query);
        System.out.println("query ejecutada");

        while(rs.next())
        {
            exist = true;
        }
        return exist;
    }
}
