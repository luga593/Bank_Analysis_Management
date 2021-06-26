<%@ page import="java.time.*" %>
<%@ page import="servlets.logInServlet" %><%--
Created by IntelliJ IDEA.
User: User
Date: 14/06/2021
Time: 12:08
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chart View</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.3.2/chart.min.js"></script>
</head>
<body>
<jsp:include page="base.jsp"/>
<div id="main">
    <h2>CHART AND GRAPH VIEW</h2>

    <div id="query-container">
<form method = "get" action = "/Topicus/TestSimon">
            <p>Select a query and enter the data to load a graph.</p>

            <select id="query1" name="query" onchange="showElements(this.value)">
                <option value="1">query1</option>
                <option value="2">query2</option>
                <option value="3">query3</option>
                <option value="4">query4</option>
                <option value="5">query5</option>
            </select>

            <input type="number" id="input1" placeholder="Minimum amount of credits." name="credit">

            <input type="month" id="input235" placeholder="Please enter the month and date." name="month-year" >

            <input type="text" id="input4" placeholder="Please enter the IBAN." name="IBAN" >

            <button type="submit" onclick="loadGraph()">Load graph</button>
</form>
    </div>

    <div class="chart-container" id="chart-container" style="height: 40vh; width:40vw">
        <canvas id="myChart" ></canvas>

    </div>

</div>

<style>
    <jsp:include page="WEB-INF/CSS/baseStyle.css"/>

    body {
        height: auto;
    }

    #chart-container {
        display: none;
        position: absolute;
        right: 0;
    }

    input, label {
        display: block;
        margin: 0 auto;
    }

    #input1 {
        display: block;
    }

    #input4 {
        display: none;
    }

    #input235 {
        display: none;
    }

    #query-container {
        float: left;
        display: inline-block;
        margin-left: 10px;
        margin-top: 10px;
        width: 25%;
        height: auto;
        text-align: center;
        background-color: var(--header-color);
        border: solid 3px;
        border-color: var(--button-color);
        border-radius: 15px;
        padding: 10px;

        /*mb*/
        justify-content: space-around;
    }

    #query-container input {
        margin-top: 10px;
    }

    #query-container select {
        border: inherit;
        border-radius: inherit;
    }

    #query-container button {
        margin-top: 10px;
        border: inherit;
        border-radius: inherit;
        background-color: var(--header-a-color);
    }

    #query-container button:hover {
        background-color: var(--header-a-color-hover);
    }

</style>

<script>
    <jsp:include page="WEB-INF/JS/darkTheme.js"/>
    <jsp:include page="WEB-INF/JS/sideNav.js"/>

    var userid = <%= logInServlet.getUser().getUserID()%>

    function loadGraph() {
	
        var query = document.getElementById("query1").value;
		console.log("aici");
        if(masspopChart != null){
            ClearRiskChart();
        }
        if (query == "4") {
            buildPieChart();
        }
        console.log("Am ajuns");
        if (query == "5"){
        	getData();
        }
        

    }
    // chart luis
	<jsp:include page="WEB-INF/JS/SimonsChart.js"/>
		 <jsp:include page="WEB-INF/JS/pieChart.js"/>



			    // takes an id and sets display to none or block
			    function setDisplay(id, display) {
			        document.getElementById(id).style.display = display;
			    }

			    // reads selector value to determine which elements should be visible
			    function showElements(query) {
			        var inputs1 = ["2","3","5"];

			        if (inputs1.includes(query)) {
			            setDisplay("input235", "block");
			            setDisplay("input4", "none");
			            setDisplay("input1", "none");
			        } else if (query == "4") {
			            setDisplay("input235", "none");
			            setDisplay("input4", "none");
			            setDisplay("input1", "none");
			        } else {
			            setDisplay("input235", "none");
			            setDisplay("input4", "none");
			            setDisplay("input1", "block");
			        }
			    }
			    </script>


</body>
</html>
