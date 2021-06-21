<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14/06/2021
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="MyStyle.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
    <meta charset="utf-8">
</head>
<body>


<header>
    <div class="nav-bar">

        <!-- Use any element to open the sidenav -->
        <span class="sidenav-button" onclick="openNav()">
                    <div><img src="images/menu.png" alt="menu"></div>
            </span>

        <div id="logo-container">
            <a href="mainPage.jsp"><img src="images/topicus-logo.png" alt="logo" class="logo"></a>
        </div>

        <button class="btn-toggle">TOGGLE DARK-MODE</button>

        <nav>
            <ul>
                <%
                    String username = "";

                    if (servlets.logInServlet.getUser() != null) {
                        username = servlets.logInServlet.getUser().getUsername();
                    }
                %>

                Hello <%= username%>!

                <li>
                    <form action="logout" method="get">
                        <button type="submit" id="log-out">Log out</button>
                    </form>
                </li>
                <li><a href ="https://topicus.nl/contact"> Contact </a></li>
            </ul>
        </nav>

    </div>
</header>

<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="Table.jsp">Processes And Transactions</a>
    <a href="upload.jsp">Bank Statement Upload</a>
    <a href="Chart.jsp">Chart view And Graph</a>
    <a href="TableSingular.jsp">Table view</a>
</div>

</body>
</html>