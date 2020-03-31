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
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "servletRegistroVideo", urlPatterns = {"/servletRegistroVideo"})
public class servletRegistroVideo extends HttpServlet {

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
    String titulo;
    String autor;
    int duracion;
    String descripcion;
    String formato;
    
    long millis=System.currentTimeMillis();  
    java.sql.Date fecha_creacion=new java.sql.Date(millis);
    HttpSession misession;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        response.setContentType("text/html;charset=UTF-8");
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
       
        try (PrintWriter out = response.getWriter()) 
        {
            
            titulo = request.getParameter("titulo");
            autor = request.getParameter("autor");
            duracion = Integer.parseInt(request.getParameter("duracion"));
            descripcion = request.getParameter("descripcion");
            formato = request.getParameter("formato");
            misession = request.getSession();
            
            if(titulo != null && autor != null && descripcion != null && formato != null)
            {
                //Registro nuevo
                conectar();
                if(tituloExist(titulo))
                {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet servletUsuarios</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("Ya existe un video con este titulo");
                    out.println("<a href=\"registroVideo.jsp\">Volver</a>");
                    out.println("</body>");
                    out.println("</html>");
                }
                else
                {
                    int code = registrarvideo();
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet servletUsuarios</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("El video ha sido registrado con exito.");
                    out.println("<a href=\"video.jsp\">Ir a lista de videos</a>");
                    out.println("</body>");
                    out.println("</html>");
                }
                desconectar();
            }
            else
            {
                
                out.println("Alguno de los campos no se ha rellenado correctamente");
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
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(servletRegistroVideo.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(servletRegistroVideo.class.getName()).log(Level.SEVERE, null, ex);
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
    
/*DATABASE FUNCTIONS-------------------------------------------------*/
    private void conectar() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/videoClub;user=root;password=root");
    }
    
    private void desconectar() throws SQLException
    {
        connection.close();
    }
    

    
    private int registrarvideo() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        int errorCode = 0;
        if(tituloExist(titulo))
        {
            //ERROR
            errorCode = -3;
        }
        else
        {
            
            //REGISTRAMOS
            usuarios usu = (usuarios)misession.getAttribute("usuario");
            String query = "INSERT INTO VIDEOS (USERID, TITULO, AUTOR, FECHA_CREACION, DURACION, DESCRIPCION, FORMATO, NUMERO_REPRODUCCIONES) " +
                            "VALUES ("+usu.getID()+", '"+titulo+"', '"+autor+"', '"+fecha_creacion+"', "+duracion+", '"+descripcion+"', '"+formato+"',0)";
            System.out.println("query contruida");
            java.sql.Statement st = connection.createStatement();
            System.out.println("statement construido");
            st.executeUpdate(query);
            System.out.println("update ejecutada");
        }
        return errorCode;
    }
   
    
    private boolean tituloExist(String titulo) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        boolean exist = false;
        
        String query = "select titulo from videos where titulo='"+titulo+"'";
        System.out.println("query contruida");
        java.sql.Statement st = connection.createStatement();
        System.out.println("statement construido");
        ResultSet rs = st.executeQuery(query);
        System.out.println("query ejecutada");

        while(rs.next())
        {
            System.out.println(rs.getString("titulo"));
            exist = true;
        }
        return exist;
    }
}
