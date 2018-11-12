<%-- 
    Document   : index
    Created on : 27-Oct-2015, 14:16:08
    Author     : me-aydin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gateway into DB Project</title>
    </head>
    <body>
        <h1>Head Office Admin Tasks</h1>
        <form method="POST" action="DBService.do">
        <p />
            View a table <br />
            <input type="radio" name="tbl" value="dailycust">Today's Customers<br />
            <input type="radio" name="tbl" value="turnover">Daily Turnover<br />
            <input type="radio" name="tbl" value="journey">Get Journeys<br />
            <input type="radio" name="tbl" value="demands">Get Jobs<br />
            <input type="radio" name="tbl" value="customer">List customers<br />
            <input type="radio" checked name="tbl" value="drivers">List drivers<br />
            <input type=submit value='Reports'> <br />
        </form>
        <a href="addDriver.jsp">Add a Driver</a><br>
        <a href="delDriver.jsp">Delete a Driver</a><br>
        <a href="assignDriver.jsp">Assign a Driver</a><br>
    </body>
</html>
