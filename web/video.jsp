<%-- 
    Document   : video
    Created on : 26/03/2020, 17:11:37
    Author     : vroig
--%>


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
                <form action="servletUsuarios" method="get">
                    <div class="subcabecera" id="FontTitle">Listado de videos existentes con sus propiedades</div>
                    <div class="subpie">
                        <a href="registroVideo.jsp" class="myButton">Registrar un nuevo video</a>
                    </div>
                </form>
            </div>
        </section>
        <footer>
            <div>
                Coded by Domin & Vicent
            </div>
        </footer>
    </body>
</html>
