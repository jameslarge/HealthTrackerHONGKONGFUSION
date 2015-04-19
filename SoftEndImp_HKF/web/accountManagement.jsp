<%-- 
    Document   : accountManagement
    Created on : 19-Apr-2015, 16:05:01
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
                        <li><a href="home.jsp" class="thispage">Home</a></li>
                        <li><a href="accountManagement.jsp" class="thispage">Account</a></li> 
                        <li><a href="LogoutController" class="thispage">Log Out</a></li>                    
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
                    Enter details in fields you wish to change
                </h3>
                <form name="update" action="UpdateUserController" method="get">
                    <p>Email:<input type="text" name="email" class="textbox"/></p>
                    <p>Password:<input type="password" name="password" id="textbox"/></p>
                    <p><input type="submit" name="submit" value="Update"/>
                    <input type="reset" name="submit" value="Reset"/></p>
                </form>
                
                <h3>Delete Account</h3>
                    
                <form name="accountDelete" action="UpdateUserController" method="get">
                    <input type="submit" name="submit" value="Delete Account">
                </form>

            </article>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                <a href="admin.html" id="admin">Admin</a></p>
            </footer>        </div>
    </body>
</html>
