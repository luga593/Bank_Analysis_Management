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
                    <div></div>
                </span>

            <a href="home.html"><img src="images/topicus-logo.png" alt="logo" class="logo"></a>

            <button class="btn-toggle">TOGGLE DARK-MODE</button>


            <nav>
                <ul>
                    <li><a href="logInPage.jsp">Log out</a></li>
                </ul>
            </nav>

        </div>
    </header>

    <div id="mySidenav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a href="#">Processes And Transactions</a>
        <a href="upload.jsp">Bank Statement Upload</a>
        <a href="Chart.jsp">Chart-view And Graph</a>
    </div>

    <div id="main">

    </div>
</body>
</html>