var datavalues;
var masspopChart;

function buildPieChart() {

    if(masspopChart == null){
        // initialize();
    }

    console.log("type");
    console.log(typeof masspopChart);
    console.log(masspopChart);
    console.log(masspopChart == null);
    console.log (masspopChart != null);
    initialize();


    setDisplay("chart-container", "block");

    var ibanVal = "";
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            datavalues = request.responseText; //this is a string
            updateChart(masspopChart);
            console.log("function 2");

        }
    }
    request.open("GET", "http://localhost:8080/Topicus/ChartTest" + "?" +
        "iban=" + ibanVal, true);                                                      //CHANGE PARAM TO IBAN
    request.send();
    console.log("function 3");
}

function updateChart(chart) {
    //updating the datavalues
    console.log("update 0");
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
    console.log("update 1");
    chart.update();
    console.log("update 2");
}

function initialize() {
    console.log("initialize 0");
    myChart= document.getElementById('myChart').getContext('2d');
    masspopChart = new Chart('myChart', {
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
                    1
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
    console.log("initialize 1");
}

function ClearRiskChart(){
    masspopChart.destroy();
    cleared = true;
}