<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Graph of dynamics of amount </title>
</head>
<body>
<script>
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: labels,
        datasets: [{
            label: 'Euros in the bank account',
            data: data1,
            borderColor: 'rgb(255, 0, 0)',
            backgroundColor: 'rgb(255, 0, 0)',
            tension: 0.1,
            pointBackgroundColor: 'rgb(255, 0, 0)',
            pointBorderColor: 'rgb(255, 0, 0)'
        },
        {
            label: 'Euros in the bank account',
            data: data2,
            borderColor: 'rgb(0, 255, 0)',
            backgroundColor: 'rgb(0, 255, 0)',
            tension: 0.1,
            pointBackgroundColor: 'rgb(0, 255, 0)',
            pointBorderColor: 'rgb(0, 255, 0)'
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