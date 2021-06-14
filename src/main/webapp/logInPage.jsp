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
                <li><a href="https://topicus.nl/ons-verhaal">About</a></li>
                <li><a href="https://topicus.nl/contact">Contact</a></li>
            </ul>
        </nav>

    </div>
</header>

<!-- login -->
<%--<div class ="backImage" style="background-image: url('images/typ.jfif');">--%>
<%--    <div class = "topicusLogo"> </div>--%>
<%--    <div class = "containerLogin">--%>
<%--        <div class="login" style="padding-left: 20px">--%>

<%--            <h2 style="text-align: center"> Login here </h2>--%>
<%--            <br>--%>
<%--            <form action="login" method="post">--%>
<%--                <!--<label for="login">username</label> -->--%>
<%--                <input type="text" placeholder="Enter Username" name="uname" required style="height: 25px; width: 350px">--%>
<%--                <br>--%>
<%--                <!-- <label for="psw">Password</label> -->--%>
<%--                <input type="password" placeholder="Enter Password" name="password" required style="height: 25px;width: 350px">--%>
<%--                <br>--%>
<%--                <br>--%>
<%--    &lt;%&ndash;            <button onclick = "login()" class= "loginButton">Log in</button>&ndash;%&gt;--%>
<%--    &lt;%&ndash;            <button onclick = "signup()" class="loginButton"> Sign up</button>&ndash;%&gt;--%>
<%--                <button type="submit" class = "loginButton">Log In</button>--%>

<%--            </form>--%>
<%--                <button type="submit" class = "loginButton"> <a href="signIn.jsp">Sign Up </a> </button>--%>
<%--            <br>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>




<script>
    <jsp:include page="WEB-INF/JS/darkTheme.js"/>
</script>

<style>
    <jsp:include page="WEB-INF/CSS/baseStyle.css"/>

    .containerLogin{
        display: flex;
        flex-direction: column;
        background-color: var(--header-color);
        border: transparent;
        height: 400px;
        width: 450px;
        border-radius: 10px;
        margin: 0 auto;
        margin-top: 10px;

        position: relative;
        right: 165px;
        z-index: 2;
        font-family: Calibri light;
    }

    .login {

        display: flex;
        flex-direction: column;
        background-color: var(--bkg-color);
        border: none;
        height: 400px;
        width: 450px;
        border-radius: 10px;
        border-bottom-left-radius: 0px;
        border-top-left-radius: 0px;
        justify-content: space-evenly;
        align-items: center;
        position: relative;
        left: 305px;
        z-index: 3;
        font-family: Calibri light;
        color: var(--header-a-color);
        right: 165px;
        margin: 0 auto;
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

    .loginButton{
        border: solid;
        border-width: 2px;
        border-radius: 15px;
        padding: 0;
        background-color: var(--button-color);
        height: 40px;
        width: 350px;
        font-family: "Calibri";
        font-size: 18px;
    }
</style>
</body>
</html>