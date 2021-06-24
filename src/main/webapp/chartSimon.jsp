!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Graph of dynamics of amount</title>
</head>
<body onload="getData()">
	<div class="chart-container" style="float: left; position: relative; height: 50vh; width:50vw">
	<canvas id="myChart" width="200" height="100" aria-label="Hello ARIA World" role="img">
		<p>Hello Fallback World</p>
	</canvas>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.3.2/chart.js" integrity="sha512-CAv0l04Voko2LIdaPmkvGjH3jLsH+pmTXKFoyh5TIimAME93KjejeP9j7wSeSRXqXForv73KUZGJMn8/P98Ifg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script>
		function getData() {
				var xmlhttp = new XMLHttpRequest();
				xmlhttp.onreadystatechange = function() {
					if (this.readyState === 4 && this.status === 200) {
						showGraph(this.responseText);
					}
				};
				xmlhttp.open("GET", "http://localhost:8080/Topicus/TestSimon", true);
				xmlhttp.send();
			}
		function showGraph(data) {
			var ctx = document.getElementById('myChart').getContext('2d');
			var arr = data.split("/");
			dates = arr[0].split(",");
			amounts = arr[1].split(",");
			var jsArray = [];
			var jsArray1 = []
			for(var i = 0 ;i < dates.length-1;i++) {
				jsArray.push(dates[i]);
				jsArray1.push(amounts[i])
			}
			var myChart = new Chart(ctx, {
				type : 'line',
				data : {
					labels : jsArray,
					datasets : [ {
						label : 'Euros in the bank account',
						data : jsArray1,
						borderColor : 'rgb(255, 0, 0)',
						backgroundColor : 'rgb(255, 0, 0)',
						tension : 0.1,
						pointBackgroundColor : 'rgb(255, 0, 0)',
						pointBorderColor : 'rgb(255, 0, 0)'
					} ]
				},
				options : {
					scales : {
						x : {
							display : true,
							title : {
								display : true,
								text : 'Date'
							}
						},
						y : {
							beginAtZero : false,
							title : {
								display : true,
								text : 'Balance'
							}
						}
					}
				}
			});
		}
	</script>
	
 
<div class="chart-container" style="float: middle; position: relative; height: 20vh; width:20vw">
	<canvas id="myChart2" width="200" height="100" aria-label="Hello ARIA World" role="img">
		<p>Hello Fallback World</p>
	</canvas>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.3.2/chart.js" integrity="sha512-CAv0l04Voko2LIdaPmkvGjH3jLsH+pmTXKFoyh5TIimAME93KjejeP9j7wSeSRXqXForv73KUZGJMn8/P98Ifg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>
        <!-- const dataValues = ['empty'] // store your values here. -->

        let myChart2= document.getElementById('myChart2').getContext('2d');
        let masspopChart = new Chart('myChart2', {
            type: 'pie',
            data:{
                labels: ['transactions with no description','transactions with non matching IBAN', 'transactions with no recipient', 'good Transactions'],
                datasets: [{
                    label: 'population',
                    data:[
                        1000,
                        20000,
                        10997,
                        20021
                    ],
                    backgroundColor:[
                        'rgba(0, 255, 0, 1)',
                        'rgba(240, 52, 52, 1)',
                        'rgb(249, 105, 14)',
                        'rgba(34, 167, 240, 1)'

                    ],
					borderColor:[
                        'rgb(0, 0, 0)',
                        'rgb(0, 0, 0)',
                        'rgb(0, 0, 0)',
                        'rgb(0, 0, 0)'

                    ],
					hoverOffset: 15

                }]
            },
            options: {}
        });
    </script>
</body>
</html>