<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="WEB-INF/MyStyle.css">
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
<%--            <button onclick = "login()" class= "loginButton">Log in</button>--%>
<%--            <button onclick = "signup()" class="loginButton"> Sign up</button>--%>
            <button type="submit" class = "loginButton">Log In</button>
            <button type="submit" class = "signUpButton">Sign Up</button>

            <br>
        </div>
    </div>
</div>




<script>
    //  light and dark theme
    const btn = document.querySelector(".btn-toggle");
    // checks wich theme the user usually prefers
    const prefersDarkScheme = window.matchMedia("(prefers-color-scheme: dark)");

    // checks which theme the users used last
    const currentTheme = localStorage.getItem("theme");
    if (currentTheme == "dark") {
        document.body.classList.toggle("dark-theme");
    } else if (currentTheme == "light") {
        document.body.classList.toggle("light-theme");
    }

    btn.addEventListener("click", function () {
        if (prefersDarkScheme.matches) {
            document.body.classList.toggle("light-theme");
            var theme = document.body.classList.contains("light-theme")
                ? "light"
                : "dark";
        } else {
            document.body.classList.toggle("dark-theme");
            var theme = document.body.classList.contains("dark-theme")
                ? "dark"
                : "light";
        }
        // saves preference
        localStorage.setItem("theme", theme);
    });

    // side nav

    /* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
    function openNav() {
        document.getElementById("mySidenav").style.width = "400px";
        document.getElementById("main").style.marginLeft = "400px";
        document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
    }

    /* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
        document.body.style.backgroundColor = "white";
    }

    /* Allows a user to log in and navigate to the main page*/
    function login() {
        let xhr = new XMLHttpRequest();
        xhr.open("GET", "http://localhost:8080/Topicus_war_exploded/logInPage.jsp/login", true);
        xhr.send();
    }


</script>









<style>
    /*dark theme*/
    body {
        --text-color: #222;
        --bkg-color: #fff;
        --header-color: #F9E805;
        --header-a-color-hover: #fff;
        --header-a-color: #5c5c5c;
        --button-color: #fff;
    }
    body.dark-theme {
        --text-color: #eee;
        --bkg-color: #242424;
        --header-color: #000000;
        --header-a-color-hover: #dae327;
        --header-a-color: #fff;
        --button-color: #fff;
    }

    @media (prefers-color-scheme: dark) {
        /* defaults to dark theme */
        body {
            --text-color: #eee;
            --bkg-color: #242424;
            --header-color: #000000;
            --header-a-color-hover: #dae327;
            --header-a-color: #fff;
            --button-color: #fff;
        }
        body.light-theme {
            --text-color: #222;
            --bkg-color: #fff;
            --header-color: #F9E805;
            --header-a-color-hover: #fff;
            --header-a-color: #5c5c5c;
            --button-color: #fff;
        }
    }

    * {
        font-family: "Poppins", sans-serif;
    }

    body {
        background: var(--bkg-color);
        margin: 0;
    }

    /*header*/
    header {
        background: var(--header-color);
    }

    header::after {
        content: '';
        display: table;
        clear: both;
    }

    nav {
        float: right;
    }

    nav ul {
        margin: 0;
        padding: 0;
        list-style: none;
    }

    nav li {
        display: inline-block;
        margin-left: 50px;
        padding-top: 19px;
    }

    nav a {
        color: var(--header-a-color);
        text-decoration: none;
        text-transform: uppercase;
        transition: 0.3s;
    }

    nav a:hover {
        color: var(--header-a-color-hover);

    }

    .nav-bar {
        width: 90%;
        height: 60px;
        margin: 0 auto;
    }

    .logo {
        float: left;
        padding: 10px 0;
        width: auto;
        height: 40px;
    }

    .btn-toggle {
        float: right;
        margin-top: 20px;
        margin-left: 50px;
        height: 20px;
        border: none;
        border-radius: 5px;
    }

    .btn-toggle:hover {
        background-color: var(--header-a-color-hover);
    }

    /* The side navigation menu */
    .sidenav {
        height: 100%; /* 100% Full-height */
        width: 0; /* 0 width - change this with JavaScript */
        position: fixed; /* Stay in place */
        z-index: 1; /* Stay on top */
        top: 0;
        left: 0;
        background-color: var(--header-color); /* Black*/
        overflow-x: hidden; /* Disable horizontal scroll */
        transition: 0.5s; /* 0.5 second transition effect to slide in the sidenav */
        box-shadow: 10px 0px 5px grey;
    }

    /* The navigation menu links */
    .sidenav a {
        /*background-color: black;*/
        padding: 8px 8px 8px 32px;
        text-decoration: none;
        font-size: 25px;
        color: var(--header-a-color);
        display: inline-block;
        transition: 0.3s;
        width: 100%;
    }

    /* When you mouse over the navigation links, change their color */
    .sidenav a:hover {
        color: var(--header-a-color-hover);
    }

    .sidenav a:not(.closebtn) {
        border-bottom-style: solid;
        border-bottom-color: var(--header-a-color);
    }

    .sidenav a:not(.closebtn):hover {
        border-bottom-color: var(--header-a-color-hover);
    }

    /* Position and style the menu button top left corner */

    .sidenav-button div  {
        width: 20px;
        height: 2px;
        background-color: var(--header-a-color);
        margin: 6px 0 ;
    }

    /* Position and style the close button (top right corner) */
    .sidenav .closebtn {
        top: 0;
        width: 100%;
    }

    /* Style page content - use this if you want to push the page content to the right when you open the side navigation */
    #main {
        transition: margin-left .5s;
        background: var(--bkg-color);
        margin: 0;
        position: absolute;
        top: 60px; /* Header Height */
        width: 100%;
    }



    /* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
    @media screen and (max-height: 450px) {
        .sidenav {padding-top: 15px;}
        .sidenav a {font-size: 18px;}
    }

    /*login*/

    .login {
        display: flex;
        flex-direction: column;
        background-color: var(--header-color);
        border: none;
        height: 400px;
        width: 450px;
        border-radius: 10px;
        justify-content: space-evenly;
        align-items: center;
        position: relative;
        left: 305px;
        z-index: 3;
        font-family: Calibri light;
        color: var(--header-a-color);
        right: 165px;
        margin: 0 auto;
        margin-top: 50px;
    }
    .loginButton{
        border: none;
        padding: 0;
        background-color: var(--button-color);
        height: 40px;
        width: 350px;
        border-radius: 15px;
        font-family: "Calibri";
        font-size: 18px;
    }

    input {
        width: 50%;
        margin: 0;
        border: none;
        border-radius: 10px;
    }

    /*links*/
    body a {
        text-decoration: none;
    }

    /*tables*/
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }

    th, td {
        padding: 15px;
    }

    /*processes*/
    #processes {
        margin-left: 50px;
    }
</style>
</body>
</html>