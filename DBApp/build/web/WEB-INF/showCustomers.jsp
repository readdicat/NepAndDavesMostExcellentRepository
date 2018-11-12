<%-- 
    Document   : showCustomers
    Created on : 03-Dec-2015, 15:54:10
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
       <h1>Customer List :</h1>
        <jsp:useBean id="dbbean" class="db.DBBean" scope="request"/>
        <jsp:getProperty name="dbbean" property="customers"/>
       
        <%--
            DBBean dbBean = (DBBean)request.getAttribute("dbbean");
        --%>
       
        <%--= dbBean.getCustomers() --%>
         <jsp:include page="foot.jsp"/>
    </body>
</html>
