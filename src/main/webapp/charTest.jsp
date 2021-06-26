<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17/06/2021
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Chart test</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.3.2/chart.min.js">
  </script>
</head>

<body>
<div class="w3-container">
  <label for="IBAN"> Enter the fileID</label> <br>
  <input type="text" id="IBAN"> <br>
  <input type="submit" onclick="myFunction()">   <br>
</div>

<div class="chart-container" style="position: relative; height: 40vh; width:40vw">
  <canvas id="myChart" ></canvas>

</div>
<script>


  //check for if the data got correctly inserted into the chart
</script>
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
    request.open("GET","http://localhost:8080/Topicus/ChartTest" + "?" +
            "iban="+ ibanVal,true);  
    // request.open("GET","http://topicus-bank1.paas.hosted-by-previder.com/Topicus/ChartTest" + "?" +
    //        "iban="+ ibanVal,true);                                                      
    //CHANGE PARAM TO IBAN
    request.send();
  }
  //after taking all values updates the chart <waiting until all vars are assigned a value,the this func>
  function updateChart(chart) {
    //updating the datavalues
    let dataArray = datavalues.split(',',4);
    console.log(dataArray[1]);
    chart.data.datasets[0].data = dataArray;

    //calculating percentage
    let total = parseInt(datavalues.split(',')[4]);
    console.log(total);
    let fraudulent = total - parseInt(dataArray[3]);
    console.log(fraudulent);
    let fraudulence = (fraudulent/total) * 100;
    console.log(fraudulence);

    //updating the title
    chart.options = {
      plugins: {
        title: {
          display: true,
          text: 'The percentage of fraudulence is ' + fraudulence.toFixed(0)+ '%',
          fontsize: 40
        }
      }
    };

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
    options: {},
  });


</script>


</body>
</html>
