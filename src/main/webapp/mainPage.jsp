<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
    <meta charset="utf-8">
</head>

<body>
<%

    if (servlets.logInServlet.getUser() == null) {

        String redirectURL = "testLogin.jsp";
        response.sendRedirect(redirectURL);
    }
%>

<jsp:include page="base.jsp"/>

<div id="main">

</div>

<style>
    <jsp:include page="WEB-INF/CSS/baseStyle.css"/>
</style>

<script>
    <jsp:include page="WEB-INF/JS/darkTheme.js"/>
    <jsp:include page="WEB-INF/JS/sideNav.js"/>
</script>

</body>
</html>