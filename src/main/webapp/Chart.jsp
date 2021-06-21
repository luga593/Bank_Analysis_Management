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

        <div id="query-container">
            <form action="#" method="post">
                <label for="query1">Query</label>
                <select id="query1" name="query1">
                    <option value="#1">query1</option>
                    <option value="#2">query2</option>
                    <option value="#3">query3</option>
                </select>

                <label for="date1">From</label>
                <input type="date" id="date1" name="date1">

                <label for="date2">To</label>
                <input type="date" id="date2" name="date2">
            </form>

            <form action="#" method="post">
                <label for="query2">Query</label>
                <select id="query2" name="query2">
                    <option value="#1">query1</option>
                    <option value="#2">query2</option>
                    <option value="#3">query3</option>
                </select>

                <label for="date3">From</label>
                <input type="date" id="date3" name="date3">

                <label for="date4">To</label>
                <input type="date" id="date4" name="date4">
            </form>
        </div>

    </div>

    <style>
        <jsp:include page="WEB-INF/CSS/baseStyle.css"/>

        input, label {
            display: block;
            margin: 0 auto;
        }

        #query-container {
            display: flex;
            margin-left: 10px;
            margin-top: 10px;
            justify-content: space-around;
        }

        #query-container form {
            display: inline-block;
            width: 45%;
            text-align: center;
            background-color: var(--header-color);
            border: transparent;
            border-radius: 15px;
        }
    </style>

    <script>
        <jsp:include page="WEB-INF/JS/darkTheme.js"/>
        <jsp:include page="WEB-INF/JS/sideNav.js"/>
    </script>

</body>
</html>