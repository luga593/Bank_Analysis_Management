function getData() {
				console.log("sunt aici");
				var xmlhttp = new XMLHttpRequest();
				xmlhttp.onreadystatechange = function() {
					if (this.readyState === 4 && this.status === 200) {
						showGraph(this.responseText);
					}
				};
				xmlhttp.open("GET", "http://localhost:8080/Topicus/TestSimon", true);
				//xmlhttp.open("GET", "http://topicus-bank1.paas.hosted-by-previder.com/Topicus/TestSimon", true);
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