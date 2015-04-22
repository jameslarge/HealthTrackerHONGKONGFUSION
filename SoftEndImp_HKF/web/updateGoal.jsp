<%-- 
    Document   : updateGoal
    Created on : 19-Apr-2015, 18:44:57
    Author     : xmw13bzu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>
<%@page import ="Goals.*"%>

<%
    
    Member member = (Member) session.getAttribute("member"); 
    int memberID = member.getUserID();
    if (member == null) {
        //something or other 
    }
    
    Goal goal = null;
    String goalIDstr = request.getParameter("goalID");

    if (goalIDstr != null)
        goal = Goal.find(Integer.parseInt(goalIDstr));
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
                        <li><a href="home.jsp">Home</a></li>
                        <li><a href="GoalsController" class="thispage">Goals</a></li>
                        <li><a href="PhysicalHealthLogController">Weight</a></li>
                        <li><a href="ExerciseLogController">Exercise</a></li>
                        <li><a href="DietLogController">Diet</a></li>
                        <li><a href="accountManagement.jsp">Account</a></li> 
                        <li><a href="LogoutController">Log Out</a></li>       
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
               
                <form name="update" action="UpdateGoalController" method="get">
                    <input type="hidden" name="goalID" value="<%=goal.getGoalID()%>"/>
                    <p>End Date:<input type="date" name="goalDeadline" value="<%=goal.getEndDate()%>" class="textbox"/></p>     
                    <p>Target:<input type="number" name="target" value="<%=goal.getTarget()%>" class="textbox"/></p>
                    <p><input type="submit" name="submit" value="Update"/></p>
                </form>

            </article>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                </p>
            </footer>        
        </div>
    </body>
</html>
