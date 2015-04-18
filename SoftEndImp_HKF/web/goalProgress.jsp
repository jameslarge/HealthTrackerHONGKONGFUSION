<%-- 
    Document   : goalProgress
    Created on : 16-Apr-2015, 18:28:41
    Author     : xmw13bzu
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>
<%@page import ="Goals.*"%>

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
                
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                        <p class="error"><%=errorMessage%></p>
                <%
                    }
                %>
                
                <h3>
                    Your General Info
                </h3>
                
                <%
                    ArrayList<Goal> goalList = (ArrayList<Goal>) session.getAttribute("goalList"); 
                %>
                
                <p>Username: <%=member.getUsername()%></p>   
                <p>Email: <%=member.getEmail()%></p>
                
                <h3>
                    Your Goals
                </h3>
                
                <!-- for each exercise progress -->
                    <!-- print date : exercisename, cals burned --> 
                <table>
                    <tr>
                         <th>Goal</th> 
                         <th>Target</th> 
                         <th>Start Date</th>
                         <th>End Date</th>
                    </tr>
                   
                <%
                    for (Goal goal : goalList) {
                %>
                        <tr>
                            <td><%=goal.getGoalType().toString()%></td>   
                            <td><%=goal.getTarget()%></td>
                            <td><%=goal.getStartDate()%></td>
                            <td><%=goal.getEndDate()%></td>
                        </tr>
                <%                        
                    }
                %>
                </table>    
              
                <h3>
                    Set new goal
                </h3>
                
                <form name="login" action="CreateGoalController" method="get">
                    <p>Goal Type: 
                        <select name="goalType">
                            <%
                                for (Goal.GoalType gt : Goal.GoalType.values()) {
                            %>
                                    <option value="<%=gt.ordinal()%>"><%=gt.toString()%></option>
                            <%                        
                                }
                            %>
                        </select>
                    </p>

                    <p>Start Date:<input type="date" name="startDate" value="<%=new HKFDate().toString()%>" class="textbox"/></p>     
                    <p>End Date:<input type="date" name="endDate" value="<%=new HKFDate().toString()%>" class="textbox"/></p>     
                    <p>Target:<input type="text" name="target" class="textbox"/></p>
                    <p><input type="submit" value="Create Goal"/>
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
