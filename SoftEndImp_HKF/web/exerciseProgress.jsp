<%-- 
    Document   : exerciseProgress
    Created on : 12-Apr-2015, 14:24:45
    Author     : xmw13bzu
--%>

<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import ="Controllers.*"%>
<%@page import ="Model.*"%>
<%@page import ="Model.PhysicalHealth.*"%>

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
                        <li><a href="home.jsp" class="thispage">Home</a></li>
                        <li><a href="accountManagement.jsp" class="thispage">Account</a></li> 
                        <li><a href="LogoutController" class="thispage">Log Out</a></li>   
                    </ul>
                </nav>
            </header>
            <article id="main">

                <%
                    //get all necessary data
    
                    //get error message (if there is one)
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    //get exercise log
                    ExerciseLogger exLog = (ExerciseLogger) session.getAttribute("exerciseLog");
                    //get list of all available exercises
                    ArrayList<Exercise> exercises = Exercise.findAll();
                    //get list of all exercises from the chosen date to be displayed in advanced graphs (if there is one)
                    ArrayList<ExerciseProgress> specificProgresses = (ArrayList<ExerciseProgress>) session.getAttribute("specificExercises");
                    
                    
                    
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
                    Your Overall Exercise Log
                </h3>

                <!-- for each exercise progress -->
                <!-- print date : exercisename, cals burned --> 
                <table>
                    <tr>
                        <th>Start Date</th> <th>Exercise</th> <th>Calories Burned</th> <th>End Date</th> <th>Duration</th>
                    </tr>

                    <%
                        

                        for (ExerciseProgress exProg : exLog.getExerciseLog()) {
                            HKFDate startDate = exProg.getDate();
                            HKFDate endDate = startDate.getEndDate(exProg.getDuration());
                    %>
                    <tr>
                        <td><%=startDate.forGraphWithTime()%></td>   
                        <td><%=exProg.getExercise().getExerciseName()%></td>
                        <td><%=exProg.calculateCals()%></td>
                        <td><%=endDate.forGraphWithTime()%></td>
                        <td><%=exProg.getDuration()%></td>

                    </tr>
                    <%
                        }
                    %>
                </table>   
                
                <!--Load the AJAX API-->
                <script type="text/javascript" src="https://www.google.com/jsapi"></script>
                <script type="text/javascript">
                    google.load('visualization', '1', {packages: ['timeline']});
                    google.load("visualization", "1", {packages: ["bar"]});
                    google.load('visualization', '1.0', {'packages': ['corechart']});
                    google.setOnLoadCallback(startChart);

                    //boolean to specify whether we load more detailed graphs (timeline and calorie breakdown)
                    var loadMoreGraphs = <%=!specificProgresses.isEmpty()%>;



                    function drawCalBurnedOverview() {

                        var data = new google.visualization.DataTable();
                        data.addColumn('string', 'Date');



                        // index in array corresponds to (exercise.Id)
                        // i.e. Running.id = 2, 
                    <%for (Exercise ex : exercises) {%>
                        data.addColumn('number', '<%=ex.getExerciseName()%>');

                    <%}%>



                    <%
                      //******************JSP***************//

                          ArrayList<Object> row = new ArrayList<Object>();

                        //create a temporary row containg 
                          //date and calories per all possible meals initialised to 0                        
                          row.add(new HKFDate());
                          int exSize = 0;
                          for (Exercise ex : exercises) {
                              exSize++;
                              row.add(Integer.valueOf(0));
                          }

                          int counter = 0;
                          for (ExerciseProgress exProg : specificProgresses) {

                              HKFDate date = exProg.getDate();

                              row.set(0, date);

                              int exIndex = exProg.getExercise().getID();

                              //add to whatever is already there (might be 0, might be a value)                                                           
                              Integer valueToStore = Integer.valueOf(exProg.calculateCals());
                              Integer valueStored = (Integer) row.get(exIndex);

                              row.set(exProg.getExercise().getID(), valueStored + valueToStore);

                              if (counter == specificProgresses.size() - 1) {

                                     //output row to GOOGLE GRAPHS
%>
                                    data.addRow([new Date(<%=date.getYear()%>,<%=date.getMonthForGraph()%>,<%=date.getDay()%>).toDateString()
                                <%
                                         String exCell;
                                         for (int i = 1; i <= exSize; i++) {
                                             exCell = ", " + ((Integer) row.get(i)).intValue();
                                %>
                                    <%=exCell%>

                                <%
                                         }
                                %>
                                    ]);
                                <%
                                         //reload row (clean it up)
                                         for (int j = 1; j <= exSize; j++) {
                                             row.set(j, Integer.valueOf(0));
                                         }
                                         //start a new one
                                         row.set(exIndex, Integer.valueOf(exProg.calculateCals()));

                                     }

                                     counter++;
                                 }%>


                        var options = {
                            title: 'Calories burned per exercise',
                            width: 900,
                            height: 500,
                            legend: 'none',
                            isStacked: 'true'


                        };
                        
                         var formatter = new google.visualization.NumberFormat(
                          {pattern: '# kCal'});
                  
                                 <% for (int i = 1; i < exercises.size(); i++){%>
                          formatter.format(data, <%=i%>); // Apply formatter to all number columns
                                                  
                        <%}%>
                        
                        var chart = new google.visualization.BarChart(document.getElementById('calBurned'));
                            
                        
                        chart.draw(data, options);
                    }
                    function prepareDataForTimeline() {

                        data_TL = new google.visualization.DataTable();
                        data_TL.addColumn('string', 'Activity');
                        data_TL.addColumn('date', 'startDate');
                        data_TL.addColumn('date', 'endDate');


                    <%for (ExerciseProgress exProg : specificProgresses) {
                            HKFDate startDate = exProg.getDate();
                            HKFDate endDate = startDate.getEndDate(exProg.getDuration());
                            String exerciseName = exProg.getExercise().getExerciseName();


                    %>
                        data_TL.addRow(['<%=exerciseName%>', new Date(<%=startDate.forGraphWithTime()%>), new Date(<%=endDate.forGraphWithTime()%>)]);
                    <%}%>

                    }

                    function prepareTimeline() {
                        options_TL = {
                            title: 'Your Weight Over Time [kg]',
                            width: 900,
                            height: 500,
                        };



                        chart_TL = new google.visualization.Timeline(document.getElementById('timeline'));
                    }


                    function drawTimeline() {
                        chart_TL.draw(data_TL, options_TL);
                    }

                    function prepareDataForGraph() {

                        data_GR = new google.visualization.DataTable();
                        data_GR.addColumn('string', 'Date');
                        data_GR.addColumn('number', 'Calories Burned');
                        data_GR.addColumn('number', 'Activity Time');


                    <%

                        TreeMap<String, Integer> calBurnedPerDay = exLog.findCalsBurnedPerDay();
                        TreeMap<String, Integer> activityTimePerDay = exLog.findActivityTimePerDay();
                        Set<String> dates = calBurnedPerDay.keySet();

                        Iterator<String> datesIterator = dates.iterator();

                        while (datesIterator.hasNext()) {
                            String date = datesIterator.next();
                            int calBurned = (int) calBurnedPerDay.get(date);
                            int activityTime = (int) activityTimePerDay.get(date);


                    %>
                        data_GR.addRow(['<%=date%>', <%=calBurned%>, <%=activityTime%>]);
                    <%}%>

                    }


                    function prepareGraph() {
                        options_GR = {
                            chart: {
                                title: 'Your Activity Log',
                                subtitle: 'calories burned on the left, activity time on the right'
                            },
                            width: 900,
                            height: 500,
                            series: {
                                0: {axis: 'calBurned'},
                                1: {axis: 'actTime'}
                            },
                            axes: {
                                y: {
                                    calBurned: {side: 'left', label: 'kCal'}, // Left y-axis.
                                    actTime: {side: 'right', label: 'minutes'} // Right y-axis.
                                }
                            }

                        };





                        chart_GR = new google.charts.Bar(document.getElementById('graph'));
                    }


                    function drawGraph() {
                        chart_GR.draw(data_GR, options_GR);
                    }



                    function startChart() {
                        if (loadMoreGraphs) {
                            drawCalBurnedOverview()
                            prepareDataForTimeline();
                            prepareTimeline();
                            drawTimeline();
                        }

                        prepareDataForGraph();
                        prepareGraph();
                        drawGraph();

                    }


                </script>
                <div id="calBurned"></div>
                <div id="timeline"></div>
                <div id="graph"></div>

                
                <h3>
                    Select a specific date to look at in detail
               </h3>                            
                <%
                        TreeSet<String> dateSet = new TreeSet<String>();
                        for (ExerciseProgress exProg : exLog.sortDate()) {
                            dateSet.add(exProg.getDate().toString());
                        }
                %>
                <form name="chooseDate" action="ExerciseLogController" method="get">
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
                    Log new exercise 
                </h3>



                <form name="login" action="LogExerciseController" method="get">
                    <p>Start Date<input type="date" name="date" class="textbox"/></p>
                    <p>Start Time<input type="time" name="time" class="textbox"/></p>


                    <!--<p>Exercise:<input type="text" name="exercise" class="textbox"/></p>-->
                    <p>Exercise: 
                        <select name="exercise">
                            <%
                                for (Exercise exercise : exercises) {
                            %>
                            <option value="<%=exercise.getID()%>"><%=exercise.getExerciseName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </p>


                    <p>Duration(if applicable):<input type="number" name="duration" class="textbox"/></p>
                    <p>Amount(if applicable):<input type="number" name="amount" class="textbox"/></p>
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
