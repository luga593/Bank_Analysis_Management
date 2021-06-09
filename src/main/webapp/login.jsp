<%--
  Created by IntelliJ IDEA.
  User: Mohamed Waleed
  Date: 6/7/2021
  Time: 10:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<%--<%@ include file = "logInPage.jsp"  %>--%>
<head>
    <title>Title</title>
</head>
<body>
    <div style="text-align: center">
        <h1>Admin Login</h1>
        <form action="login" method="post">
            <%--@declare id="uname"--%><label for="uname">Username:</label>
            <input name="uname" size="30" />
            <br><br>
                <%--@declare id="password"--%><label for="password">Password:</label>
            <input type="password" name="password" size="30" />
            <br><br>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
--/>
</html>
