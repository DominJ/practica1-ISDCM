/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletRegistroVid;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vroig
 */
@WebServlet(name = "servletRegistroVid", urlPatterns = {"/servletRegistroVid"})
public class servletRegistroVid extends HttpServlet {

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
    //String fecha_creacion;
    //String duracion;
    //String reproducciones;
    String duracion;
    String descripcion;
    String formato;
    
    long millis=System.currentTimeMillis();  
    java.sql.Date fecha_creacion=new java.sql.Date(millis);
    
    
    
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
            
            titulo = request.getParameter("titulo");
            autor = request.getParameter("autor");
            //fecha_creacion = request.getParameter("fecha_creacion");
            //duracion = request.getParameter("duracion");
            //reproducciones = request.getParameter("reproducciones");
            descripcion = request.getParameter("descripcion");
            formato = request.getParameter("formato");
            out.println("aaaassssssssssssssss");
            if(!(titulo == null || autor == null || descripcion == null || formato == null))
            {
                //Registro nuevo
                out.println("aaaassssssssssssssss");
                if(true)
                {
                    out.println("aaaassssssssssssssss");
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
                        out.println("aaaasssssssssssssssstit");
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
            }
        }    
    }


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
            String query = "INSERT INTO VIDEOS (TITULO, AUTOR, FECHA_CREACION, DURACION, DESCRIPCION, FORMATO) " +
                            "VALUES ('"+titulo+"', '"+autor+"', '"+fecha_creacion+"', '"+duracion+"', '"+descripcion+"', '"+formato+"')";
            System.out.println("query contruida");
            Statement st = connection.createStatement();
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
        Statement st = connection.createStatement();
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
