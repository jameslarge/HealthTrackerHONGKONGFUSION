<%-- 
    Document   : home.jsp
    Created on : 04-Mar-2015, 14:51:04
    Author     : xmw13bzu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>

<%
    Member member = (Member) session.getAttribute("member"); 
    final boolean loggedIn = (member != null);
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
                        <li><a href="LogoutController" class="thispage">Log Out</a></li>                        
                    </ul>
                </nav>
            </header>
            <article id="main">
                <h3>
                    Your Info
                </h3>
                
                <p>Username: <%=member.getUsername()%></p>   
                <p>Email: <%=member.getEmail()%></p>
                <p>Forename:  <%=member.getForename()%></p>
                <p>Surname:  <%=member.getSurname()%></p>              
                
                <h3>
                    View Physical Details/Weight Progress
                </h3>
                <form name="physical" action="PhysicalHealthLogController" method="get">
                    <p><input type="submit" value="GoGoPhysical"/>
                </form>
                
                <h3>
                    View Exercise Details/Progress
                </h3>
                <form name="exercise" action="ExerciseLogController" method="get">
                    <p><input type="submit" value="GoGoExercise"/>
                </form>
                
                <h3>
                    View Diet Details/Progress
                </h3>
                <form name="diet" action="DietLogController" method="get">
                    <p><input type="submit" value="GoGoDiet"/>
                </form>
                
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
