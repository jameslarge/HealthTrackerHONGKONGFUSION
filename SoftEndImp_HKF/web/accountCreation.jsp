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
                        <li><a href="index.jsp">Index</a></li>
                        <li><a href="accountCreation.jsp" class="thispage">Sign Up</a></li>                 
                    </ul>
                </nav>
            </header>
            <article id="main">
                
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                        <p class="error"><%=errorMessage%></p>
                <%
                    }
                %>
                
                <h3>
                    Enter details 
                </h3>
                <form name="login" action="NewUserController" method="get">
                    <p>Username:<input type="text" name="username" class="textbox"/></p>
                    <p>Email:<input type="text" name="email" class="textbox"/></p>
                    <p>Password:<input type="password" name="password" id="textbox"/></p>
                    <p>Forename:<input type="text" name="forename" class="textbox"/></p>
                    <p>Surname:<input type="text" name="surname" class="textbox"/></p>
                    <p>Current Weight:<input type="number" name="weight" class="textbox"/></p>
                    <p>Current Height:<input type="number" name="height" class="textbox"/></p>
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

