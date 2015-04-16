<%-- 
    Document   : mealProgress
    Created on : 14-Apr-2015, 12:10:25
    Author     : xmw13bzu
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>
<%@page import ="Model.Meal.*"%>

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
                    DietLogger dietLog = (DietLogger) session.getAttribute("dietLog"); 
                %>
                
                <p>Username: <%=member.getUsername()%></p>   
                <p>Email: <%=member.getEmail()%></p>
                
                <h3>
                    Your Meal Log
                </h3>
                
                <!-- for each exercise progress -->
                    <!-- print date : exercisename, cals burned --> 
                <table>
                    <tr>
                         <th>Date</th> <th>Meal</th> <th>Calories Consumed</th>
                    </tr>
                   
                <%
                    for (MealProgress mealProg : dietLog.sortDate()) {
                %>
                    <tr>
                        <td><%=mealProg.getDate()%></td>   
                        <td><%=mealProg.getMeal().getMealName()%></td>
                        <td><%=mealProg.calcCalories()%></td>
                    </tr>
                <%                        
                    }
                %>
                
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
                     // data.addColumn('string', 'Meal name');
                      data.addColumn('number', 'Calories')
                      
                      
                      <%for (MealProgress mealProg : dietLog.sortDate()){
                          HKFDate date = mealProg.getDate();
                          String mealName = mealProg.getMeal().getMealName();
                          int calories = mealProg.calcCalories();
                          
                          
                      %>                      
                        data.addRow([new Date(<%=date.getYear()%>,<%=date.getMonthForGraph()%>,<%=date.getDay()%>),<%=calories%>]);
                       <%}%>  

                      var options = {
                        title: 'Blabla',                                              
                        width: 900,
                        height: 500,
                        legend: 'none',
                        
                        
                       
                      };
                      
                                    var chart = new google.visualization.LineChart(document.getElementById('linechart'));

                      chart.draw(data, options);
                    }
                                       
                </script>
                
                 <div id="linechart"></div>
                
                </table>    
              
                <h3>
                    Log new meal 
                </h3>
                
                <%
                    ArrayList<Meal> meals = Meal.findAll();
                %>
                
                 <form name="login" action="LogMealController" method="get">
                    <p>Date:<input type="date" name="date" class="textbox"/></p>
                    <!--<p>Exercise:<input type="text" name="exercise" class="textbox"/></p>-->
                    <p>Meal: 
                        <select name="meal">
                            <%
                                for (Meal meal : meals) {
                            %>
                                    <option value="<%=meal.getID()%>"><%=meal.getMealName()%></option>
                            <%                        
                                }
                            %>
                        </select>
                    </p>
                    

                    
                    <p>Meal Type:
                        <select name="mealType">
                            <%
                                for (MealProgress.MealTime type : MealProgress.MealTime.values()) {
                            %>
                                    <option value="<%=type.getValue()%>"><%=type.toString()%></option>
                            <%                        
                                }
                            %>
                        </select>
                    </p>
                            
                    <p>Amount:<input type="text" name="amount" class="textbox"/></p>
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
