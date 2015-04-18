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
    int memberID = member.getUserID();
    if (member == null) {
        //something or other 
    }
%>

                                                
<%
    GoalLogger goalLog = (GoalLogger) session.getAttribute("goalLog"); 

    ArrayList<Goal> finishedGoals = goalLog.findFinishedGoals();
    ArrayList<Goal> dueGoals = goalLog.findDueGoals();
    ArrayList<Goal> upcomingGoals = goalLog.findUpcomingGoals();

    ArrayList<Integer> finishedGoalsProgress = GoalLogger.checkProgress(finishedGoals, memberID);
    ArrayList<Integer> dueGoalsProgress = GoalLogger.checkProgress(dueGoals, memberID);
    ArrayList<Integer> upcomingGoalsProgress = GoalLogger.checkProgress(upcomingGoals, memberID);
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
                
                <p>Username: <%=member.getUsername()%></p>   
                <p>Email: <%=member.getEmail()%></p>
                
                <h3>
                    Goals due today
                </h3>
                
                <!-- for each exercise progress -->
                    <!-- print date : exercisename, cals burned --> 
                <table>
                    <tr>
                         <th>Goal</th> 
                         <th>Target</th> 
                         <th>Current Progress</th>
                    </tr>
                   
                    <%
                        for (int i = 0; i < dueGoals.size(); ++i) {
                    %>
                            <tr>
                                <td><%=dueGoals.get(i).getGoalType().toString()%></td>   
                                <td><%=dueGoals.get(i).getTarget()%></td>
                                <td><%=dueGoalsProgress.get(i)%></td>
                            </tr>
                    <%                        
                        }
                    %>
                </table>
                
                <h3>
                    Goals due soon
                </h3>
                
                <!-- for each exercise progress -->
                    <!-- print date : exercisename, cals burned --> 
                <table>
                    <tr>
                         <th>Goal</th> 
                         <th>Target</th> 
                         <th>Current Progress</th>
                    </tr>
                   
                    <%
                        for (int i = 0; i < upcomingGoals.size(); ++i) {
                    %>
                            <tr>
                                <td><%=upcomingGoals.get(i).getGoalType().toString()%></td>   
                                <td><%=upcomingGoals.get(i).getTarget()%></td>
                                <td><%=upcomingGoalsProgress.get(i)%></td>
                            </tr>
                    <%                        
                        }
                    %>
                </table>
                
                <h3>
                    ALL Your Goals
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
                    for (Goal goal : goalLog.getGoalList()) {
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
                    <p>Target:<input type="number" name="target" class="textbox"/></p>
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
