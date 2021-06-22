<%@ page import="java.time.*" %><%--
Created by IntelliJ IDEA.
User: User
Date: 14/06/2021
<body>
    <jsp:include page="base.jsp"/>
    <div id="main">
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