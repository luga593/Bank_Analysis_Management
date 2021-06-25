<%@ page import="java.time.*" %><%--
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
        <h2>CHART VIEW AND GRAPH</h2>

    <div id="query-container">
<%--        <form>--%>
            <p>Select a query and enter the data to load a graph.</p>

            <select id="query1" name="query" onchange="showElements(this.value)">
                <option value="1">query1</option>
                <option value="2">query2</option>
                <option value="3">query3</option>
                <option value="4">query4</option>
                <option value="5">query5</option>
            </select>

            <input type="number" id="input1" placeholder="Minimum amount of credits." name="credit">

            <input type="month" id="input235" placeholder="Please enter the month and date." name="month-year">

            <input type="text" id="input4" placeholder="Please enter the IBAN." name="IBAN">

            <button type="submit" onclick="loadGraph()">Load graph</button>
<%--        </form>--%>
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

        #query-container form {
            display: inline-block;
            width: 45%;
            text-align: center;
            background-color: var(--header-color);
            border: transparent;
            border-radius: 15px;
        }
    </style>
=======
<%--        chart goes here     --%>

<form action="getGraph" method="post" onchange="javascript:this.form.submit()">
    <label for="month">"minimum amount of money in one month"</label>
    <input type="month" id="month" name="month" max="">
</form>

<form action="getGraph" method="post" onchange="javascript:this.form.submit()">
    <label for="balance">"the amount the credit the user wants to take"</label>
    <input type="number" id="balance" name="balance">
</form>

<form action="getGraph" method="post" onchange="javascript:this.form.submit()">
    <label for="day-month">"selector for the day and the month for the balance salad for a specific day"</label>
    <input type="date" id="day-month" name="day-month">
</form>

</div>

<style>
    <jsp:include page="WEB-INF/CSS/baseStyle.css"/>

    input, label {
        display: block;
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
        var datavalues;
        // iban is NL34RABO0327101691

        function myFunction(){
        let ibanVal = document.getElementById('IBAN').value;
        var request = new XMLHttpRequest();

        request.onreadystatechange = function(){
        if(request.readyState == 4 && request.status == 200){
        datavalues = request.responseText; //this is a string
        updateChart(masspopChart);

    }
    }
        request.open("GET","http://localhost:8080/Topicus_war/ChartTest" + "?" +
        "iban="+ ibanVal,true);                                                      //CHANGE PARAM TO IBAN
        request.send();
    }

        //after taking all values updates the chart <waiting until all vars are assigned a value,the this func>
        function updateChart(chart) {
        let dataArray = datavalues.split(',');
        console.log(dataArray[1]);

        chart.data.datasets[0].data = dataArray;

        chart.update();

    }

        //Chart
        let myChart= document.getElementById('myChart').getContext('2d');
        let masspopChart = new Chart('myChart', {
        type: 'pie',
        data:{
        labels: ['transactions with no description','transactions with no recipient nor description',
        'transactions with no recipient', 'good Transactions'],
        datasets: [{
        label: 'population',
        data:[
        0,
        0,
        0,
        1,
        //1000 <<old value
        ],
        backgroundColor:[
        'rgba(249, 105, 14, 1)',
        'rgba(240, 52, 52, 1)',
        'rgba(245, 171, 53, 1)',
        'rgba(34, 167, 240, 1)'

        ]

    }]
    },
        options: {}
    });


    </script>

</body>
</html>