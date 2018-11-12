<%-- 
    Document   : driverJobs
    Created on : 30-Oct-2015, 10:02:53
    Author     : me-aydin
--%>

<%--@page import="db.DBBean"--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DB Results</title>
    </head>
    <body>
        <h1>Your Jobs :</h1>
        <jsp:useBean id="dbbean" class="db.DBBean" scope="request"/>
        <jsp:getProperty name="dbbean" property="driverJobs"/>
       
        <%--
            DBBean dbBean = (DBBean)request.getAttribute("dbbean");
        --%>
       
        <%--= dbBean.getDriverJobs() --%>
         <jsp:include page="foot.jsp"/>
    </body>
</html>
