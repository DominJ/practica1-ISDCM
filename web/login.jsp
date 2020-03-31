<%-- 
    Document   : login
    Created on : 11-mar-2020, 16:51:30
    Author     : fiblabs
--%>

<%@page import="MainPackage.usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    usuarios user = (usuarios)session.getAttribute("usuario");
    if(user != null && user.getLogin())
    {
        //El user ya se ha logeado
        response.sendRedirect("registroVideo.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Login ISDCM</title>
    </head>
    <body>
        <header>
            <div>
                Página de registro del servidor de ficheros
            </div>
        </header>
        <section>
            <div class="principal">
                <form action="servletUsuarios" method="get">
                    <div class="subcabecera" id="FontTitle">Login de usuarios</div>
                    <div class="subprincipal">
                        <div class="columnaIzquierda">
                            <div class="casilla">Nombre de usuario:</div>
                            <div class="casilla">Contraseña:</div>
                        </div>
                        <div class="columnaDerecha">
                            <div class="casilla"><input name="userlogin" type="text" /></div>
                            <div class="casilla"><input name="passlogin" type="password" /></div>
                        </div>
                    </div>
                    <div class="subpie">
                        <input type="submit" class="myButton" value="Login" />
                        <a href="registroUsu.jsp" class="myButton">Registrar</a>
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