<%-- 
    Document   : officeLogin
    Created on : 01-Dec-2015, 19:39:06
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Office Login</title>
    </head>
    <body>
        <h1>Enter your username and password</h1>
        <form method="POST" action="DriverValidate">
        <p />
            View a table <br />
           Username: <input type="dname" name="name" value=""><br />
           Password:<input type="password" name="password" value=""><br />
             <input type=submit value='Log in'> <br />
        </form> 
    </body>
</html>
