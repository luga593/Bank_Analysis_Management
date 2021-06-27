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
            <h2> <a href = "Table.jsp">Processes & Transactions</a></h2>
            <p>Our intelligent web application allows you to view all transactions related to your company in a table,
            Bank statements can be downloaded in whatever format you choose, we also allow sorting on individual
            attributes based on your needs. Additionally, there are also different language settings to choose from.
                Fraudulent transactions are flagged and you are notified of it in this section. </p>
        </div>

        <div class = "space"> </div>

        <div  class="column"  >
            <h2> <a href="upload.jsp">Bank Statement Upload</a> </h2>
            <p>Topicus now introduces a new feature to permit multiple uploads of your bank statements for parsing,
            this parsed result will be available in the "Processes & Transactions" section in addition with all previously
            uploaded files, but the individually uploaded file can be accessed in the "Table View" section.</p>
        </div>

        <div class = "space"> </div>


        <div class="column2">
            <h2> <a href="Chart.jsp">Chart View And Graph</a> </h2>
            <p>Andrea,simon, luis change this part,  line 43 of mainPae.jsp </p>
        </div>

        <div class = "space"> </div>


        <div class="column2">
            <h2> <a href="TableSingular.jsp">Table View</a> </h2>
            <p>This section allows you to view your most recently uploaded file, this section has all features of the table
            in "Processes & Transactions".</p>
        </div>

    </div>


</div>
<style>
    <jsp:include page="baseStyle.css"/>

    .main{
        border-radius: 10px;
        justify-content: space-evenly;
        margin-top: 200px;
        background-color: var(--bkg-color);
        margin: auto;
        width: 50%;
        /*border: 3px solid var(--header-color);*/
        padding: 10px;
    }
    /* Create three equal columns that floats next to each other */
    .column {
        border: 3px solid #F9E805;
        border-radius: 10px;
        justify-content: space-evenly;
        text-align: center;
        color: white;
        float: none;
        width: 100%;
        padding: 5px;
        /*border: black;*/
    }

    .column:hover{
        transform: scale(1.2);
        transition: 0.4s;
    }

    .column2:hover{
        transform: scale(1.2);
        transition: 0.4s;
    }

    .column h2 a{
        text-decoration: none;
        color: black;
        border: black;
        padding: 5px;
        border-radius: 10px;
        background-color: #F9E805;
        transition: 0.2s;
    }

    /*.column h2:hover {*/
    /*    transform: scale(1.2);*/
    /*}*/

    .column2 {
        border: 3px solid #F9E805;
        border-radius: 10px;
        text-align: center;
        color: white;
        float: none;
        width: 100%;
        padding: 5px;
        /*border: black;*/
    }

    .column2 h2 a{
        text-decoration: none;
        color: black;
        border: black;
        padding: 5px;
        border-radius: 10px;
        background-color: #F9E805;
        transition: 0.2s;
    }

    /*.column2 h2:hover {*/
    /*    transform: scale(1.2);*/
    /*}*/


    /* Clear floats after the columns */
    .row:after {
        content: "";
        display: table;
        clear: both;
    }

    .space{
        height : 20px;
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