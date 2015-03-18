<%-- 
    Document   : accountCreation
    Created on : 11-Mar-2015, 14:54:32
    Author     : xmw13bzu
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>HONG KONG FUSIOOOOON</title>
        <link href="styles/main.css" rel="stylesheet" type="text/css">

    </head>

    <body>
        <div id="wrapper">
            <header id="top">
                <h1>HONG KONG FUSIOOOOON</h1>
                <nav id="mainnav">
                    <ul>
                        <li><a href="index.html" class="thispage">Home</a></li>
                        <li><a href="sthelse.html" class="thispage">SOMETHING ELSE</a></li>                        
                    </ul>
                </nav>
            </header>
            <article id="main">
                <h3>
                    Enter personal details 
                </h3>
                <form name="login" action="NewUserPersonalController" method="get">
                    <p>Forename:<input type="text" name="forename" class="textbox"/></p>
                    <p>Surname:<input type="text" name="surname" class="textbox"/></p>
                    <p>Current Weight:<input type="text" name="weight" class="textbox"/></p>
                    <p>Current Height:<input type="text" name="height" class="textbox"/></p>
                    <p><input type="submit" value="Update"/>
                    <input type="reset" value="Reset"/></p>
                </form>

            </article>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                <a href="admin.html" id="admin">Admin</a></p>
            </footer>        </div>
    </body>
</html>

