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

        <div id="logo-container">
            <a href="testLogin.jsp"><img src="images/topicus-logo.png" alt="logo" class="logo"></a>
        </div>

        <button class="btn-toggle">TOGGLE DARK-MODE</button>

        <nav>
            <ul>
                <li><a href="https://topicus.nl/ons-verhaal">About</a></li>
                <li><a href="https://topicus.nl/contact">Contact</a></li>
            </ul>
        </nav>

    </div>
</header>

<script>
    <jsp:include page="WEB-INF/JS/darkTheme.js"/>
</script>

<style>
    <jsp:include page="WEB-INF/CSS/baseStyle.css"/>

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

</style>
</body>
</html>