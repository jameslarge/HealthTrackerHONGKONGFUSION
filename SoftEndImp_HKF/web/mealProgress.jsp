<%-- 
    Document   : mealProgress
    Created on : 14-Apr-2015, 12:10:25
    Author     : xmw13bzu
--%>

<%@page import="java.util.TreeSet"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>
<%@page import ="Model.Meal.*"%>

<%
    Member member = (Member) session.getAttribute("member");
    //no one is logged in
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
                        <li><a href="home.jsp">Home</a></li>
                        <li><a href="GoalsController">Goals</a></li>
                        <li><a href="PhysicalHealthLogController">Weight</a></li>
                        <li><a href="ExerciseLogController">Exercise</a></li>
                        <li><a href="DietLogController" class="thispage">Diet</a></li>
                        <li><a href="accountManagement.jsp">Account</a></li> 
                        <li><a href="LogoutController">Log Out</a></li>       
                    </ul>
                </nav>
            </header>
            <article id="main">
                
                <%
                    //get error message (if there is one)
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    //get diet log
                    DietLogger dietLog = (DietLogger) session.getAttribute("dietLog"); 
                    //get list of all available meals
                    ArrayList<Meal> meals = Meal.findAll();
                    //get list of all exercises from the chosen date to be displayed in advanced graphs (if there is one)
                    ArrayList<MealProgress> specificProgresses = (ArrayList<MealProgress>) session.getAttribute("specificMeals");
                    
                    
                     
                    //display error message if there is one
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
                </table> 
                          <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(startChart);
      var loadMoreGraphs = <%=!specificProgresses.isEmpty()%>;


                    
                    function drawCalBreakDown() {
                      
                      var data = new google.visualization.DataTable();
                      data.addColumn('string', 'Date');
                     // data.addColumn('string', 'Meal name');
                      
                      
                      // index in array corresponds to (meal.Id)
                      // i.e. Pizza.id = 2, 
                      <%for (Meal meal: meals){%>
                        data.addColumn('number', '<%=meal.getMealName()%>');
                         
                      <%}%>
                      
                      
                      
                      <%
                      //******************JSP***************//
                        
                        ArrayList<Object> row = new ArrayList<Object>();
                      
                      
                        //create a temporary row containg 
                        //date and calories per all possible meals initialised to 0                        
                        row.add(new HKFDate());
                        int mealSize = 0;
                        for (Meal meal : meals){
                              mealSize++;
                              row.add(Integer.valueOf(0));
                          }
                        
                        
                          int counter = 0;
                          
                      for (MealProgress mealProg : specificProgresses){
                          
                         
                          
                          HKFDate date = mealProg.getDate();                                                                             
                          row.set(0, date);
                          int mealIndex = mealProg.getMeal().getID();
                          
                          
                          
                                                     
                              //add to whatever is already there (might be 0, might be a value)                                                           
                              Integer valueToStore = Integer.valueOf(mealProg.calcCalories());
                              Integer valueStored = (Integer) row.get(mealIndex);
                                      
                              row.set(mealProg.getMeal().getID(), valueStored + valueToStore);
                          
                          if (counter == specificProgresses.size() - 1){
                             
                             //output row to GOOGLE GRAPHS
                             %>   
                                 data.addRow([new Date(<%=date.getYear()%>,<%=date.getMonthForGraph()%>,<%=date.getDay()%>).toDateString()
                                     <%
                                     String mealCell;
                                     for(int i = 1; i<=mealSize; i++){
                                         mealCell = ", " + ((Integer)row.get(i)).intValue();
                                         %>
                                                     <%=mealCell%>
                                                         
                                     <%
                                     }
                                     %>                               
                                ]);     
                             <% 
                              //reload row (clean it up)
                              for (int j = 1; j<=mealSize; j++){
                                   row.set(j,Integer.valueOf(0));
                               }
                              //start a new one
                             row.set(mealIndex, Integer.valueOf(mealProg.calcCalories()));
                             
                             
                          }                         
                         counter++;
                        }%>  
                                
                        
                                                  
                          
                                     

                      var options = {
                        title: 'Calories consumed per meal',                                              
                        width: 900,
                        height: 500,
                        legend: 'none',
                        isStacked: 'true'
                        
                       
                      };
                       var formatter = new google.visualization.NumberFormat(
                          {pattern: '# kCal'});
                  
                                 <% for (int i = 1; i < meals.size(); i++){%>
                          formatter.format(data, <%=i%>); // Apply formatter to all number columns
                                                  
                        <%}%>
                       var chart = new google.visualization.BarChart(document.getElementById('calBreakDown'));
                       
                       //getting the total amount of calories consumed per date
                       
                      chart.draw(data, options);
                    }
                    
                   
                    
                    
                    function prepareData() {
                      
                      data = new google.visualization.DataTable();
                      data.addColumn('date', 'Date');
                      data.addColumn('number', 'Calories')
                      
                      
                      <%
                          TreeMap<HKFDate, Integer> calEatenPerDay = dietLog.findCalsConsumedPerDay();
                          
                          for (Map.Entry<HKFDate,Integer> entry : calEatenPerDay.entrySet()){
                                HKFDate date = entry.getKey();
                                int calEaten = (int) entry.getValue();
                          
                      %>                      
                        data.addRow([new Date(<%=date.getYear()%>,<%=date.getMonthForGraph()%>,<%=date.getDay()%>), <%=calEaten%>]);
                       <%}%>  

                    }
                    
                    function prepareChart(){
                         options = {
                             title: 'Calories Eaten Over Time',                                              
                            width: 900,
                            height: 500,
                            pointSize: 20,
                            legend: 'none',
                             hAxis: {title: 'Day'},
                             vAxis: {title: 'Calories [kCal]'},
                             crosshair: { 
                                          trigger: 'both',
                                          orientation: 'horizontal',
                                          color: 'red'                                                                                   
                                        }
                           
                            
                          };

                          var formatter = new google.visualization.NumberFormat(
                          {pattern: '# kCal'});
                          formatter.format(data, 1); // Apply formatter to second column
                          
                       
                          chart = new google.visualization.LineChart(document.getElementById('linechart'));
                    }
                    
                    
                    function drawChart(){
                      chart.draw(data, options);
                    }
                    
                    function startChart(){
                      if(loadMoreGraphs){
                          drawCalBreakDown();
                      }
                      prepareData();
                      prepareChart();
                      drawChart();
                      
                    }
                                                           
                </script>
                                       
               
                
                 <div id="linechart"></div>
                 <div id="calBreakDown"></div>
                 
                <h3>
                    Select a specific date to look at in detail
               </h3>                            
                <%
                        TreeSet<String> dateSet = new TreeSet<String>();
                        for (MealProgress mealProg : dietLog.sortDate()) {
                            dateSet.add(mealProg.getDate().toString());
                        }
                %>
                <form name="chooseDate" action="DietLogController" method="get">
                    <p>
                        <select name="specificDate">
                            <%
                                for (String date : dateSet) {
                            %>
                            <option value="<%=date%>"><%=date%></option>
                            <%
                                }
                            %>
                        </select>
                        <input type="submit" value="View"/>
                    </p>
                </form>
                 
                 
                             
                <h3>
                    Log new meal 
                </h3>
                
              
                
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
                            
                    <p>Amount:<input type="number" name="amount" class="textbox"/></p>
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
