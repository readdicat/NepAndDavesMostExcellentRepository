<%-- 
    Document   : showTurnOver
    Created on : 03-Dec-2015, 11:44:08
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Daily Turnover :</h1>
        <jsp:useBean id="dbbean" class="db.DBBean" scope="request"/>
        <jsp:getProperty name="dbbean" property="turnover"/>
       
        <%--
            DBBean dbBean = (DBBean)request.getAttribute("dbbean");
        --%>
       
        <%--= dbBean.getTurnover() --%>
         <jsp:include page="foot.jsp"/>
    </body>
</html>
