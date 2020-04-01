<%-- 
    Document   : video
    Created on : 26/03/2020, 17:11:37
    Author     : vroig
--%>


<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="MainPackage.usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    usuarios user = (usuarios)session.getAttribute("usuario");
    if(user == null || user.getLogin() != true)
    {
        //El user ya se ha logeado
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Registro/Listado ISDCM</title>
    </head>
    <body>
        <header>
            <div>
                Listado de videos existententes
            </div>
        </header>
        <section>
            <div class="principal">
                <div class="subcabecera" id="FontTitle">Listado de videos existentes con sus propiedades</div>
                <div>
                <table class="mytable">
                    <tr> <th>Título</th> <th>Autor</th> <th>Fecha</th> <th>Duración</th> <th>Numero de reproducciones</th> <th>Descripción</th> <th>Formato</th> </tr>
                    <% 
                        Connection connection = null;
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/videoClub;user=root;password=root");
                        String query = "select * from VIDEOS";
                        //System.out.println("query contruida");
                        Statement st = connection.createStatement();
                        //System.out.println("statement construido");
                        ResultSet rs = st.executeQuery(query);
                        //System.out.println("query ejecutada");

                        while(rs.next())
                        {
                            out.println("<tr><td>"+rs.getString("titulo")+"</td> <td>"+rs.getString("autor")+"</td> <td>"+rs.getString("fecha_creacion")+"</td> <td>"+rs.getString("duracion")+"</td> <td>"+rs.getString("numero_reproducciones")+"</td> <td>"+rs.getString("descripcion")+"</td> <td>"+rs.getString("formato")+"</td></tr>");
                        }
                    %>
                </table>
                </div>
                <div class="subpie">
                    <a href="registroVideo.jsp" class="myButton">Registrar un nuevo video</a>
                </div>
            </div>
        </section>
        <footer>
            <div>
                Coded by Domin & Vicent
            </div>
        </footer>
    </body>
</html>
