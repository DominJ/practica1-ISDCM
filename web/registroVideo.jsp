<%-- 
    Document   : registroVideo
    Created on : 26/03/2020, 17:24:55
    Author     : vroig
--%>
<%@page import="MainPackage.usuarios"%>
<% 
    usuarios user = (usuarios)session.getAttribute("usuario");
    if(user == null || user.getLogin() != true)
    {
        //El user NO se ha logeado
        response.sendRedirect("login.jsp");
    }
    else
    {
        System.out.println("Ya existe el user y es: "+user.getLogin());
        System.out.println("Ya existe el user y es: "+user.getUser());
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Registro Video ISDCM</title>
    </head>
    <body>
        <header>
            <div>
                Página de registro del servidor de ficheros
            </div>
        </header>
        <section>
            <div class="principal">
                <% out.println("Usuario actual: "+ user.getUser()); %>
                <form action="servletRegistroVideo" method="get">
                    <div class="subcabecera" id="FontTitle">Registro de videos</div>
                    <div class="subprincipal">
                        <div class="columnaIzquierda">
                            <div class="casilla">Titulo:</div>
                            <div class="casilla">Autor:</div>
                            <div class="casilla">Duracion:</div>
                            <div class="casilla">Descripción:</div>
                            <div class="casilla">Formato:</div>
                        </div>
                        <div class="columnaDerecha">
                            <div class="casilla"><input name="titulo" type="text" /></div>
                            <div class="casilla"><input name="autor" type="text" /></div>
                            <div class="casilla"><input name="duracion" type="text" /></div>
                            <div class="casilla"><input name="descripcion" type="text" /></div>
                            <div class="casilla"><input name="formato" type="text" /></div>
                        </div>
                    </div>
                    <div class="subpie">
                        <a href="video.jsp" class="myButton">Volver a lista de videos</a>
                        <input type="submit" class="myButton" value="Registrar video" />
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
