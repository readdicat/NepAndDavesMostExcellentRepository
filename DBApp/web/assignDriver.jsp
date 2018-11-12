<%-- 
    Document   : assignDriver
    Created on : 03-Dec-2015, 12:17:47
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assign a Driver</title>
    </head>
    <body>
        <h1>Add a Driver</h1>
        <form method="POST" action="driverAssign">
        <p />
            Please enter new driver details <br />
           Driver Registration: <input type="dregno" name="dregno" value=""><br />
           Demand Id: <input type="did" name="did" value=""><br />
           <input type=submit value='Assign Driver'> <br />
        </form>
    </body>
</html>
