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
            
            <footer>
                <article id="disclaimer">
                    <span>Disclaimer:</span> This application is not a commercial application and does not provide
                    insurance. This is a study project that is part of a Computing Science module taught at the
                    University of East Anglia, Norwich, UK. If you have any questions, please contact the
                    module coordinator, Joost Noppen, at j.noppen@uea.ac.uk
                </article>
            </footer>
            
            
            <header id="top">
                <h1>Hong Kong Fusion Health Tracker</h1>
                <nav id="mainnav">
                    <ul>
                        <li><a href="home.jsp" class="thispage">Home</a></li>
                        <li><a href="GoalsController">Goals</a></li>
                        <li><a href="PhysicalHealthLogController">Weight</a></li>
                        <li><a href="ExerciseLogController">Exercise</a></li>
                        <li><a href="DietLogController">Diet</a></li>
                        <li><a href="accountManagement.jsp">Account</a></li> 
                        <li><a href="LogoutController">Log Out</a></li>       
                    </ul>
                </nav>
            </header>
            
            
            <!--<article id="main">-->
            <div class="divTable">
              <div class="divRow">
                  <article class="contentArea" id="leftHalf">
                       <article class="basicArea">
                            <%
                                String errorMessage = (String) request.getAttribute("errorMessage");
                                if (errorMessage != null) {
                            %>
                                    <p class="error">Error: <%=errorMessage%></p>
                            <%
                                }
                            %>

                            <h3>
                                Your Info
                            </h3>

                            <p>Username: <%=member.getUsername()%></p>   
                            <p>Email: <%=member.getEmail()%></p>
                            <p>Forename:  <%=member.getForename()%></p>
                            <p>Surname:  <%=member.getSurname()%></p>              
                
                        </article>
                    </article>

                    <article id="centre"></article>
                    
                    <article class="contentArea" id="rightHalf">
                         <article class="basicArea">
                
                            <% 
                                double bmiHealth = member.calcHealthinessBMI();
                                double activityHealth = member.calcHealthinessActivity();
                                double dietHealth = member.calcHealthinessDiet();

                                ExerciseLogger exLog = ExerciseLogger.find(member.getUserID());
                                int avgActivityPerDay = exLog.findAverageDailyActivityTime();

                                DietLogger dietLog = DietLogger.find(member.getUserID());
                                int avgCalsPerDay = dietLog.findAverageDailyCalsConsumed();
                            %>

                            <h3>
                                Your Health Summary: <%=member.calculateHealthiness(bmiHealth, activityHealth, dietHealth)%>
                            </h3>
                            <p>Body Mass Index: <%=member.calculateBMI()%></p>
                            <p>Average Activity per day: <%=avgActivityPerDay%>mins</p>
                            <p>Average Calories Consumed per day: <%=avgCalsPerDay%></p>
                        </article>
                    </article>
                </div>
           </div>
                
            <br><br>
            <footer>
                <p>&copy; Copyright 2015 
                <a href="admin.html" id="admin">Admin</a></p>
            </footer>        </div>
    </body>
</html>
