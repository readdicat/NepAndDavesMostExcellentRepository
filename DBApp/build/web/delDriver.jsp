<%-- 
    Document   : delDriver
    Created on : 02-Dec-2015, 22:32:27
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Driver</title>
    </head>
    <body>
        <h1>Delete a Driver</h1>
        <form method="POST" action="DriverDel">
            <p />
            Please enter new driver details <br />
            Driver name: <input type="dname" name="name" value=""><br />
            <input type=submit value='Delete Driver'> <br />
        </form>
    </body>
</html>
