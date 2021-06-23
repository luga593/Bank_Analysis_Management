<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Graph of dynamics of amount</title>
</head>
<body onload="script();">
<div class="chart-container" style="position: relative; height: 40vh; width:40vw">
  <canvas id="myChart" ></canvas>
</div>
	<script>
	SimonsGraph newgraph = new SimonsGraph();
	List<List<String>> result1 = new ArrayList<List<String>>();
	result1 = newgraph.getqueryResult();
	var jsArray = <%= newgraphtoJavascriptArray(result1) %>;
	var jsArray1 = <%= newgraphtoJavascriptArray1(result1) %>;
	var ctx = document.getElementById('myChart').getContext('2d');
	var myChart = new Chart(ctx, {
	    type: 'line',
	    data: {
	        labels: jsArray,
	        datasets: [{
	            label: 'Euros in the bank account',
	            data: jsArray1,
	            borderColor: 'rgb(255, 0, 0)',
	            backgroundColor: 'rgb(255, 0, 0)',
	            tension: 0.1,
	            pointBackgroundColor: 'rgb(255, 0, 0)',
	            pointBorderColor: 'rgb(255, 0, 0)'
	        }]
	    },
	    options: {
	        scales: {
	            x: {
	                display: true,
	                title: {
	                display: true,
	                text: 'Date'
	                }
	            },
	            y: {
	                beginAtZero: false,
	                title: {
	                display: true,
	                text: 'Balance'
	                }
	            }
	        }
	    }
	});

</script>
</body>
</html>