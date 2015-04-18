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
                
                  <%
                    ArrayList<Meal> meals = Meal.findAll();
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
                </table> 
                          <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);


                    
                    function drawChart() {
                      
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
                          HKFDate prevDate = null;
                          ArrayList<MealProgress> mealProgresses =  dietLog.sortDate();
                      for (MealProgress mealProg :mealProgresses){
                          
                         
                          
                          HKFDate date = mealProg.getDate();
                          
                          if(counter==0){
                              prevDate = date;
                          }
                          
                          row.set(0, date);
                          
                          int mealIndex = mealProg.getMeal().getID();
                          
                          
                          boolean sameDay = date.compareTo(prevDate)==0;
                          //if the date is the same modify the values by adding to appropriate fields
                          if (sameDay){
                              
                              //add to whatever is already there (might be 0, might be a value)                                                           
                              Integer valueToStore = Integer.valueOf(mealProg.calcCalories());
                              Integer valueStored = (Integer) row.get(mealIndex);
                                      
                              row.set(mealProg.getMeal().getID(), valueStored + valueToStore);
                          }
                          if (!sameDay || counter == mealProgresses.size() - 1){
                             
                             //output row to GOOGLE GRAPHS
                             %>   
                                 data.addRow([new Date(<%=prevDate.getYear()%>,<%=prevDate.getMonthForGraph()%>,<%=prevDate.getDay()%>).toDateString()
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
                          if(counter!=0){
                            prevDate = date;
                          }
                         counter++;
                        }%>  
                                
                        
                                                  
                          
                                     

                      var options = {
                        title: 'Blabla',                                              
                        width: 900,
                        height: 500,
                        legend: 'none',
                        isStacked: 'true',
                        
                       
                      };
                      
                       var chart = new google.visualization.BarChart(document.getElementById('linechart'));
                       
                       //getting the total amount of calories consumed per date
                       rowValues;
                       for(r = 0; r< data.getNumberOfRows(); r++){
                           var value = 0;
                           for(c = 1; c<data.getNumberOfColumns();c++){
                              value = value + data.getValue(r,c);
                           }
                           rowValues[rowValues.length] = value;
                       }
                      chart.draw(data, options);
                    }
                                       
                </script>
                
                 <div id="linechart"></div>
                
                             
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
