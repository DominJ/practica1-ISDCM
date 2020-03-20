/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
    usuarios usu;
    
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
            throws ServletException, IOException 
    {
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String correo = request.getParameter("correo");
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String repass = request.getParameter("repass");
            String userlogin = request.getParameter("userlogin");
            String passlogin = request.getParameter("passlogin");

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
                    usu = new usuarios(nombre, apellidos, correo, user, pass);
                    usu.registrar();
                }
            }
            else
            {
                //Login
                out.println("Venimos del login bruh");
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
                    out.println("bbbbbbbbbbbb");
                    usu = new usuarios(userlogin, passlogin);
                    int a = usu.login();
                    out.println("aaaaaaaaaaaaa"+a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(servletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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

}
