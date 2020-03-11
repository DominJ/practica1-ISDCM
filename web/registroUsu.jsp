<%-- 
    Document   : login
    Created on : 11-mar-2020, 15:30:54
    Author     : Domin y Vicent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Registro User ISDCM</title>
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
                    <div class="subcabecera" id="FontTitle">Registro de usuarios</div>
                    <div class="subprincipal">
                        <div class="columnaIzquierda">
                            <div class="casilla">Nombre:</div>
                            <div class="casilla">Apellidos:</div>
                            <div class="casilla">Correo electrónico:</div>
                            <div class="casilla">Nombre de usuario:</div>
                            <div class="casilla">Contraseña:</div>
                            <div class="casilla">Repetir contraseña:</div>
                        </div>
                        <div class="columnaDerecha">
                            <div class="casilla"><input name="nombre" type="text" /></div>
                            <div class="casilla"><input name="apellidos" type="text" /></div>
                            <div class="casilla"><input name="correo" type="text" /></div>
                            <div class="casilla"><input name="user" type="text" /></div>
                            <div class="casilla"><input name="pass" type="password" /></div>
                            <div class="casilla"><input name="repass" type="password" /></div>
                        </div>
                    </div>
                    <div class="subpie">
                        <input type="submit" class="myButton" value="Registrar usuario" />
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
