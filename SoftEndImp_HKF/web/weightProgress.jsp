<%-- 
    Document   : weightProgress.jsp
    Created on : 08-April-2015, 14:51:04
    Author     : xmw13bzu
--%>

<%@page import="java.util.ArrayList"%>
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
                                  
                           
              <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);


                    
                    function drawChart() {
                      
                      var data = new google.visualization.DataTable();
                      data.addColumn('date', 'Date');
                      data.addColumn('number', 'Your Weight')
                      
                      
                      <%for (WeightProgress weightProg : physHealth.sortDate()){
                          HKFDate date = weightProg.getDate();
                          Weight weight = weightProg.getWeight();
                          
                      %>                      
                        data.addRow([new Date(<%=date.getYear()%>,<%=date.getMonthForGraph()%>,<%=date.getDay()%>), <%=weight.toString()%>]);
                       <%}%>  

                      var options = {
                        'title': 'Your Weight Over Time [kg]',                                              
                        'width': 900,
                        'height': 500,
                        'pointSize': 20,
                        'legend': 'none',
                        
                      };
                      
                      var formatter = new google.visualization.NumberFormat(
                      {suffix: 'kg', fractionDigits: 1});
                      formatter.format(data, 1); // Apply formatter to second column

                      var chart = new google.visualization.LineChart(document.getElementById('linechart'));

                      chart.draw(data, options);
                    }
                                       
                </script>
  
                <div id="linechart"></div>
              
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
