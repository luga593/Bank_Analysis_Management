<%--
  Created by IntelliJ IDEA.
  User: Mohamed Waleed
  Date: 6/9/2021
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09/06/2021
  Time: 09:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <link rel="stylesheet" type="text/css" href="WEB-INF/MyStyle.css">
</head>
<body>


<header>
  <div class="nav-bar">

    <a href="home.html"><img src="images/topicus-logo.png" alt="logo" class="logo"></a>


    <nav>
      <ul>
        <li><a href="#">About</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
    </nav>

  </div>
</header>


<!-- sign up -->
<div class ="backImage" style="background-image: url('images/typ.jfif');">co
  <div class = "topicusLogo"> </div>
  <div class = "containerLogin">
    <div class="login">

      <h2>Sign in</h2>

      <!--<label for="login">username</label> -->
      <h3> Please Enter your credentials</h3>

      <form action="signup" method="POST" style="padding: 15px;">
        <input type="text" placeholder="Enter Username" id="uname" required style="height: 25px; width: 350px">


        <input type="email" placeholder="Enter your E-mail" id="EMail" required style="height: 25px; width: 350px">


        <input type="text" placeholder="Enter your first name" id="fName" required style="height: 25px; width: 350px">


        <input type="text" placeholder="Enter your last name" id="lName" required style="height: 25px; width: 350px">
        <!-- <label for="psw">Password</label> -->

        <input type="password" placeholder="Enter Password" id="psw1" required style="height: 25px;width: 350px">


        <input type="password" placeholder="Re-enter Password" id="psw2" required style="height: 25px;width: 350px">

        <button type="submit" class = "loginButton">Submit </button>
      </form>
      <br>
      <br>
      <%--            <button onclick = "login()" class= "loginButton">Log in</button>--%>
      <%--            <button onclick = "signup()" class="loginButton"> Sign up</button>--%>
      <button type="submit" class = "loginButton"> <a href="logInPage.jsp">login </a> </button>


      <br>
    </div>
  </div>
</div>
</body>
<script>

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
    --box-shadow: 10px 0px 5px grey;
    --title-color: black;
    --text-color: black;
  }
  body.dark-theme {
    --text-color: #eee;
    --bkg-color: #202124;
    --header-color: #292a2d;
    --header-a-color-hover: #dae327;
    --header-a-color: #fff;
    --button-color: #fff;
    --box-shadow: 0px 0px 0px grey;
    --title-color: white;
    --text-color: #959595;

  }

  @media (prefers-color-scheme: dark) {
    /* defaults to dark theme */
    body {
      --text-color: #eee;
      --bkg-color: #202124;
      --header-color: #292a2d;
      --header-a-color-hover: #dae327;
      --header-a-color: #fff;
      --button-color: #fff;
      --box-shadow: 0px 0px 0px grey;
      --title-color: white;
      --text-color: #959595;
    }
    body.light-theme {
      --text-color: #222;
      --bkg-color: #fff;
      --header-color: #F9E805;
      --header-a-color-hover: #fff;
      --header-a-color: #5c5c5c;
      --button-color: #fff;
      --box-shadow: 10px 0px 5px grey;
      --title-color: black;
      --text-color: black;
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
    width: 99%;
    height: 60px;
    margin: 0 auto;
  }

  .logo {
    float: left;
    padding: 10px 0;
    width: auto;
    height: 40px;
    margin-left: 10px;
  }

  .btn-toggle {
    float: right;
    margin-top: 20px;
    margin-left: 50px;
    height: 20px;
    border: none;
    border-radius: 5px;
    transition: 0.3s;
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
    box-shadow: var(--box-shadow);
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
    background-image: url("images/menu.png");
    width: 40px;
    height: 40px;
    background-size: 40px;
    float: left;
    margin-top: 10px;
    background-color: var(--button-color);
    border-radius: 10px;
    transition: 0.3s;
  }

  .sidenav-button div:hover {
    background-color: var(--header-a-color-hover);
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
    height: 100%;
    color: var(--text-color);
  }



  /* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
  @media screen and (max-height: 450px) {
    .sidenav {padding-top: 15px;}
    .sidenav a {font-size: 18px;}
  }

  /*login*/
  .backImage{
    position: absolute;
    width: auto;
    height: 100%;
    left: 700px;
    right: 0px;
    z-index: 1;
    background-repeat: no-repeat;
    background-size: cover;
  }
  .topicusLogo{
    background-image: url("images/topicusBl.png");
    height: 110px;
    width: 300px;
    position: relative;
    z-index: 2;
    left: 370px;
    margin-top: 10px;
  }


  .containerLogin{
    display: flex;
    flex-direction: column;
    background-color: var(--header-color);
    border: transparent;
    height: 500px;
    width: 450px;
    border-radius: 10px;
    margin: 0 auto;
    margin-top: 10px;
    padding: 0px;
    position: relative;

    z-index: 2;
    font-family: Calibri light;
  }


  .login {
    display: flex;
    flex-direction: column;
    background-color: var(--header-color);
    border: none;
    height: 500px;
    width: 450px;
    border-radius: 10px;
    justify-content: space-evenly;
    align-items: center;
    position: relative;
    left: 0px;
    z-index: 3;
    font-family: Calibri light;
    color: var(--header-a-color);
    right: 0px;
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
  .login form{

    display: flex;
    flex-direction: column;
    margin: 0px;
    padding: 0px;
    align-items: center;
  }
  .login form input{
    margin-top: 5px;
  }
  .login form button{
    margin-top: 20px;

  }


  input {
    width: 50%;
    margin: 0;
    border: solid;
    border-radius: 10px;
    border-width: 2px;
  }

  /*links*/
  body a {
    text-decoration: none;
  }

  /*tables*/
  table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    border-color: var(--text-color);
  }

  th, td {
    padding: 15px;
  }

  th {
    color: var(--title-color);
  }

  td {
    color: var(--text-color);
  }

  /*processes*/
  #processes {
    margin-left: 50px;
  }
</style>
</html>

