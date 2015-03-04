<%-- 
    Document   : index
    Created on : 04-Mar-2015, 13:20:55
    Author     : xmw13bzu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>

<%
    User user = (User) session.getAttribute("user"); 
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
        
        <% 
            if (loggedIn) {
        %>
        
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
                <h3>LOG IN</h3>
                 <h2>
            Enter email and password:
              </h2>
                <form name="login" action="LoginController" method="get">
                 <p>Email:<input type="text" name="email" class="textbox"/></p>
                 <p>Password:<input type="password" name="password" id="textbox"/></p>
                 <p><input type="button" value="Login"/>
                <input type="reset" value="Reset"/></p>
         </form>

            </article>
                <aside id="sidebar">
                <h3>SIGN UP</h3>
                <form name="signup" action="signup" method="get">
                 <p>Email:<input type="text" name="email" class="textbox"/></p>
                 <p>Password:<input type="password" name="password" id="textbox"/></p>
                 <p>Confirm password:<input type="password" name="passwordConfirm" id="textbox"/></p>
                 <p><input type="button" value="Sign up"/>
                <input type="reset" value="Reset"/></p>
                </form>
             
                     
            </aside>
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                <a href="admin.html" id="admin">Admin</a></p>
            </footer>        </div>
        
        <% 
            }
            else {
        %>
        
        <p>herro</p>
        
        <%
            }
        %>
    </body>
</html>
