<%-- 
    Document   : home.jsp
    Created on : 04-Mar-2015, 14:51:04
    Author     : xmw13bzu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>

<%
    Member user = (Member) session.getAttribute("user"); 
    final boolean loggedIn = (user != null);
%>

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
                        <li><a href="home.jsp" class="thispage">Home</a></li>
                        <li><a href="sthelse.jsp" class="thispage">SOMETHING ELSE</a></li>                        
                    </ul>
                </nav>
            </header>
            <article id="main">
                <h3>
                    Your Info
                </h3>
                
                <p>Username: <%=user.getUsername()%></p>   
                <p>Email: <%=user.getEmail()%></p>
                <p>Forename:  <%=user.getForename()%></p>
                <p>Surname:  <%=user.getSurname()%></p>
                <p>Current Weight:  </p>
                <p>Current Height:  <%=user.getHeight()%></p>
                
                
                <h3>
                    LOG OUT
                </h3>
                <form name="logout" action="LogoutController" method="get">
                        <p><input type="submit" value="Logout"/>
                </form>
              
            </article>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                <a href="admin.html" id="admin">Admin</a></p>
            </footer>        </div>
    </body>
</html>
