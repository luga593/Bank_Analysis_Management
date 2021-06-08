<%--
  Created by IntelliJ IDEA.
  User: Mohamed Waleed
  Date: 6/8/2021
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Welcome <%=session.getAttribute("name") %>></title>
</head>
<body>
<h3>Login successful!!</h3>
<h4>
    Hello,
    <%=session.getAttribute("name") %>
</h4>
</body>
</html>
