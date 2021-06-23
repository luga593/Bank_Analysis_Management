<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

  <title>Home</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
    .w3-bar,h1,button {font-family: "Montserrat", sans-serif}
    .fa-anchor,.fa-coffee {font-size:200px}
  </style>
</head>
<body>
<%--<%--%>

<%--    if (servlets.logInServlet.getUser() == null) {--%>

<%--        String redirectURL = "testLogin.jsp";--%>
<%--        response.sendRedirect(redirectURL);--%>
<%--    }--%>
<%--%>--%>

<%--<jsp:include page="base.jsp"/>--%>

<div id="main">

</div>

<style>
  <%--    <jsp:include page="WEB-INF/CSS/baseStyle.css"/>--%>
</style>

<script>
  <jsp:include page="WEB-INF/JS/darkTheme.js"/>
  <jsp:include page="WEB-INF/JS/sideNav.js"/>
</script>

<!-- Header -->
<header class="w3-container w3-yellow w3-center" style="padding:128px 16px">
  <h1 class="w3-margin w3-jumbo">Topicus, your smart leading bank manager.</h1>
  <p class="w3-xlarge">More organization, more success.</p>
  <button onclick ="location.href = 'testLogin.jsp' ; "class = "w3-button w3-black w3-padding-large w3-large w3-margin-top"> Get Started</button>
</header>

<!-- First Grid -->
<div class="w3-row-padding w3-padding-64 w3-container">
  <div class="w3-content">
    <div class="w3-twothird">
      <h1>Our objective</h1>
      <h5> Topicus wants people, IT systems and data together to connect in digital platforms. We want to make your life easier, and your day simpler, with Topicus, all your
        worries will be gone. We make the impossible possible with IT. With innovative software platforms, digital ecosystems, in which people are central. Platforms in which we
        connect people, data and systems. In this way we ensure that people
        receive the financing for their dream home within one day.</h5>

      <p class="w3-text-grey">With our idiosyncratic, social and
        connecting character, we actively change the markets
        ourselves. We want to do business, renew and innovate
        together with customers. We question traditional and
        outdated (work) processes and take a critical look at
        existing chains. How do we connect those IT systems so
        that everyone knows what is going on? No IT islands, but
        one platform in which everyone can participate without
        unnecessary intermediaries.</p>


    </div>

    <div class="w3-third w3-center">
      <i class="fa fa-anchor w3-padding-64 w3-text-black"></i>
    </div>
  </div>
</div>

<!-- Second Grid -->
<div class="w3-row-padding w3-light-grey w3-padding-64 w3-container">
  <div class="w3-content">
    <div class="w3-third w3-center">
      <i class="fa fa-coffee w3-padding-64 w3-text-black w3-margin-right"></i>
    </div>

    <div class="w3-twothird">
      <h1>Book with us, have a drink</h1>
      <h5 class="w3-padding-32"> We at Topicus gain the trust of our clients, and we
        would like to invite you for a free demo in which you can measure
        the success of your company with sample bank statements.
        Be bold, meet Topicus.</h5>

    </div>
  </div>
</div>

<%--<div class="w3-container w3-black w3-center w3-opacity w3-padding-64">--%>
<%--    <h1 class="w3-margin w3-xlarge">Quote of the day: live life</h1>--%>
<%--</div>--%>

<!-- Footer -->
<footer class="w3-container w3-padding-64 w3-center w3-opacity">
  <div class="w3-xlarge w3-padding-32">
    <i class="fa fa-facebook-official w3-hover-opacity"></i>
    <i class="fa fa-instagram w3-hover-opacity"></i>
    <i class="fa fa-snapchat w3-hover-opacity"></i>
    <i class="fa fa-pinterest-p w3-hover-opacity"></i>
    <i class="fa fa-twitter w3-hover-opacity"></i>
    <i class="fa fa-linkedin w3-hover-opacity"></i>
  </div>
  <p>Powered by <a href="https://topicus.nl/" target="_blank">topicus.nl</a></p>
</footer>



<script>
  // Used to toggle the menu on small screens when clicking on the menu button
  function myFunction() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
      x.className += " w3-show";
    } else {
      x.className = x.className.replace(" w3-show", "");
    }
  }
</script>
</body>
</html>




