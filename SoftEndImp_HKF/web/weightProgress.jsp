<%-- 
    Document   : weightProgress.jsp
    Created on : 08-April-2015, 14:51:04
    Author     : xmw13bzu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>
<%@page import ="Model.PhysicalHealth.*"%>

<%
    Member member = (Member) session.getAttribute("member"); 
    if (member == null) {
        //something or other 
    }
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
                    Your General Info
                </h3>
                
                <%
                    PhysicalHealth physHealth = (PhysicalHealth) session.getAttribute("physHealth"); 
                %>
                
                <p>Username: <%=member.getUsername()%></p>   
                <p>Email: <%=member.getEmail()%></p>
                <p>Height: <%=physHealth.getHeight()%></p>
                
                <h3>
                    Your Weight Log
                </h3>
                
                <!-- for each weight progress -->
                    <!-- print date : weight --> 
                <table>
                    <tr>
                         <th>Date</th> <th>Weight</th>
                    </tr>
                   
                <%
                    for (WeightProgress weightProg : physHealth.getPhysicalHealthLog()) {
                %>
                    <tr>
                        <td><%=weightProg.getDate()%></td>   <td><%=weightProg.getWeight()%></td>
                    </tr>
                <%                        
                    }
                %>
                </table>    
              
                <h3>
                    Log new weight 
                </h3>
                
                 <form name="login" action="LogWeightController" method="get">
                    <p>Date:<input type="date" name="date" class="textbox"/></p>
                    <p>Weight:<input type="text" name="weight" class="textbox"/></p>
                    <p><input type="submit" value="Enter"/>
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
