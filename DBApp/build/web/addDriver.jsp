<%-- 
    Document   : addDriver
    Created on : 02-Dec-2015, 21:19:19
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a Driver</title>
    </head>
    <body>
        <h1>Add a Driver</h1>
        <form method="POST" action="driverAdd">
        <p />
            Please enter new driver details <br />
           Driver name: <input type="dname" name="name" value=""><br />
           Driver password: <input type="password" name="password" value=""><br />
           Registration: <input type="dregno" name="regno" value=""><br />
            <input type=submit value='Add Driver'> <br />
        </form>
    </body>
</html>
