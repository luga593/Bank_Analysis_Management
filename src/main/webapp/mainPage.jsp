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

<h1 style = "color: white; text-align: center; margin-top: 20px">Welcome Back To Topicus Transactions</h1>

<div class="main">
    <div class="row">
        <div class="column">
            <h2 style = "text-decoration: underline solid #F9E805">Processes & Transactions</h2>
            <p>Our intelligent web application allows you to view all transactions related to your company in a table,
            Bank statements can be downloaded in whatever format you choose, we also allow sorting on individual
            attributes based on your needs. Additionally, there are also different language settings to choose from.
                Fraudulent transactions are flagged and you are notified of it in this section. </p>
        </div>

        <div class="column">
            <h2 style = "text-decoration: underline solid #F9E805">Bank Statement Upload</h2>
            <p>Topicus now introduces a new feature to permit multiple uploads of your bank statements for parsing,
            this parsed result will be available in the "Processes & Transactions" section in addition with all previously
            uploaded files, but the individually uploaded file can be accessed in the "Table View" section.</p>
        </div>

        <div class="column2">
            <h2 style = "text-decoration: underline solid #F9E805">Chart View And Graph</h2>
            <p>Andrea,simon, luis change this part,  line 43 of mainPae.jsp </p>
        </div>

        <div class="column2">
            <h2 style = "text-decoration: underline solid #F9E805"> Table View</h2>
            <p>This section allows you to view your most recently uploaded file, this section has all features of the table
            in "Processes & Transactions".</p>
        </div>

    </div>


</div>
<style>
    <jsp:include page="WEB-INF/CSS/baseStyle.css"/>

    .main{
        border-radius: 10px;
        justify-content: space-evenly;
        margin-top: 200px;
        background-color: var(--bkg-color);
        margin: auto;
        width: 50%;
        border: 3px solid #F9E805;
        padding: 10px;
    }
    /* Create three equal columns that floats next to each other */
    .column {
        justify-content: space-evenly;
        text-align: center;
        color: white;
        float: none;
        width: 100%;
        padding: 5px;
        border: black;
    }

    .column2 {
        text-align: center;
        color: white;
        float: none;
        width: 100%;
        padding: 5px;
        border: black;
    }



    /* Clear floats after the columns */
    .row:after {
        content: "";
        display: table;
        clear: both;
    }

    /* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
    @media screen and (max-width:600px) {
        .column {
            width: 100%;
        }
    }

</style>

<script>
    <jsp:include page="WEB-INF/JS/darkTheme.js"/>
    <jsp:include page="WEB-INF/JS/sideNav.js"/>
</script>

</body>
</html>