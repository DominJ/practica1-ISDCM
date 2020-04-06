/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    usuarios usu = null;
    String nombre;
    String apellidos;
    String correo;
    String user;
    String pass;
    String repass;
    String userlogin;
    String passlogin;
    int userID;
    HttpSession misession;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        //conectar();
        //Statement statement = connection.createStatement();
        //statement.setQueryTimeout(30);
        //connection.close();
        
        try (PrintWriter out = response.getWriter()) 
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            nombre = request.getParameter("nombre");
            apellidos = request.getParameter("apellidos");
            correo = request.getParameter("correo");
            user = request.getParameter("user");
            pass = request.getParameter("pass");
            repass = request.getParameter("repass");
            userlogin = request.getParameter("userlogin");
            passlogin = request.getParameter("passlogin");
            misession = request.getSession();
            

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
                    //out.println("aaaassssssssssssssss");
                    conectar();
                    if(userExist(user))
                    {
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet servletUsuarios</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("El usuario ya existe");
                        out.println("<a href=\"registroUsu.jsp\">Volver</a>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    else
                    {
                        //out.println("aaaasssssssssssssssstit");
                        int code = registrar();
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet servletUsuarios</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("Registro correcto. Porfavor inicie sesión.");
                        out.println("<a href=\"login.jsp\">Ir a loggin</a>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    desconectar();
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
                    //out.println(userlogin);
                    conectar();
                    int code = login();
                    desconectar();
                    out.println(code);
                    switch(code)
                    {
                        case 0  :   
                                    
                                    usu = new usuarios(true, userlogin, userID);
                                    out.println("<html>");  //LOGIN CORRECTO
                                    out.println("<head>");
                                    out.println("<title>Servlet servletUsuarios</title>");            
                                    out.println("</head>");
                                    out.println("<body>");
                                    out.println("Login correcto");
                                    out.println("<META HTTP-EQUIV=REFRESH CONTENT=1;URL=video.jsp>" );
                                    out.println("</body>");
                                    out.println("</html>");
                                    break;
                        case -2 :   
                                    usu = new usuarios();
                                    out.println("<html>");
                                    out.println("<head>");
                                    out.println("<title>Servlet servletUsuarios</title>");            
                                    out.println("</head>");
                                    out.println("<body>");
                                    out.println("pass incorrecta");
                                    out.println("<a href=\"login.jsp\">Volver</a>");
                                    out.println("</body>");
                                    out.println("</html>");
                                    break;
                        case -3 :   
                                    usu = new usuarios();
                                    out.println("<html>");
                                    out.println("<head>");
                                    out.println("<title>Servlet servletUsuarios</title>");            
                                    out.println("</head>");
                                    out.println("<body>");
                                    out.println("user incorrecto");
                                    out.println("<a href=\"login.jsp\">Volver</a>");
                                    out.println("</body>");
                                    out.println("</html>");
                    }
                    misession.setAttribute("usuario", usu);
                    response.sendRedirect("video.jsp");
                }
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Excepción de input/output.");
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            out.println("No se ha encontrado alguna de las clases que se utiliza.");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Se ha producido un problema en la ejecución de código SQL.");
        } 
        catch (IllegalAccessException ex) 
        {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Se ha producido un acceso no permitido a la base de datos.");
        } 
        catch (InstantiationException ex) 
        {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Se ha producido un problemas en la instanciación de la conexión");
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    /*DATABASE FUNCTIONS-------------------------------------------------*/
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
        }
        return errorCode;
    }
    
    private int registrar() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        int errorCode = 0;
        if(userExist(user))
        {
            //ERROR
            errorCode = -3;
        }
        else
        {
            //REGISTRAMOS
            String query = "INSERT INTO USUARIOS (NOMBRE, APELLIDO, EMAIL, PASS, USUARIO) " +
                    "VALUES ('"+nombre+"', '"+apellidos+"', '"+correo+"', '"+pass+"', '"+user+"')";
            System.out.println("query contruida");
            Statement st = connection.createStatement();
            System.out.println("statement construido");
            st.executeUpdate(query);
            System.out.println("update ejecutada");
        }
        return errorCode;
    }
    
    private boolean checkMail(String correo)
    {
        boolean checked = false;
        for(int i=0; i<correo.length(); i++)
        {
            if(correo.charAt(i)== '@')
            {
                checked = true;
            }
        }
        return checked;
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
        String query = "select ID from usuarios where usuario='"+user+"' AND pass='"+password+"'";
        System.out.println("query contruida");
        Statement st = connection.createStatement();
        System.out.println("statement construido");
        ResultSet rs = st.executeQuery(query);
        System.out.println("query ejecutada");

        while(rs.next())
        {
            userID = rs.getInt("ID");
            exist = true;
        }
        return exist;
    }
}
