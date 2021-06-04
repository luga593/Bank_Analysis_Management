<%--
  Created by IntelliJ IDEA.
  User: Mohamed Waleed
  Date: 5/26/2021
  Time: 10:51 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href = "<c:url value="WEB-INF/MyStyle.css" />" rel = "stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
    <meta charset="utf-8">
</head>
<body>

<header>
    <div class="nav-bar">

        <a href="home.html"><img src="images/topicus-logo.png" alt="logo" class="logo"></a>

        <button class="btn-toggle">TOGGLE DARK-MODE</button>

        <nav>
            <ul>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </nav>

    </div>
</header>

<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="personal.html">Personal Information Page </a>
    <a href="processes.html">Processes And Transactions</a>
    <a href="upload.html">Bank Statement Upload</a>
    <a href="chart.html">Chart-view And Graph</a>
    <a href="feedback.html">Feedback</a>
</div>

<!-- login -->
<div class ="backImage" style="background-image: url('images/typ.jfif');">
    <div class = "topicusLogo"> </div>
    <div class = "containerLogin">
        <div class="login">

            <h2>Login here</h2>
            <br>
            <!--<label for="login">username</label> -->
            <input type="text" placeholder="Enter Username" name="uname" required style="height: 25px; width: 350px">

            <!-- <label for="psw">Password</label> -->
            <input type="password" placeholder="Enter Password" name="psw" required style="height: 25px;width: 350px">
            <br>
            <br>
            <button class= "loginButton"><a href="home.html">Log in</a></button>
            <button class="loginButton"> Sign up</button>
            <br>
        </div>
    </div>
</div>





<script src='MyScript.js'></script>
</body>
</html>