<%@ page import="java.time.*" %><%--
Created by IntelliJ IDEA.
User: User
Date: 14/06/2021
Time: 12:08
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chart View</title>
</head>
<body>
    <jsp:include page="base.jsp"/>
    <div id="main">

        <form action="getGraph" method="post" onchange="javascript:this.form.submit()">
            <label for="month">"minimum amount of money in one month"</label>
            <input type="month" id="month" name="month" max="">
        </form>

        <form action="getGraph" method="post" onchange="javascript:this.form.submit()">
            <label for="balance">"the amount the credit the user wants to take"</label>
            <input type="number" id="balance" name="balance">
        </form>

        <form action="getGraph" method="post" onchange="javascript:this.form.submit()">
            <label for="day-month">"selector for the day and the month for the balance salad for a specific day"</label>
            <input type="date" id="day-month" name="day-month">
        </form>

    </div>

    <style>
        <jsp:include page="WEB-INF/CSS/baseStyle.css"/>

        input, label {
            display: block;
        }
    </style>

    <script>
        <jsp:include page="WEB-INF/JS/darkTheme.js"/>
        <jsp:include page="WEB-INF/JS/sideNav.js"/>
    </script>

</body>
</html>