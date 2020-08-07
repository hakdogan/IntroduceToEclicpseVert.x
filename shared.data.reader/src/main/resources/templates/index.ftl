<!doctype html>
<html>
<head>
    <title>${title}</title>
    <style>
        canvas{
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
        }
    </style>
</head>

<body style="padding: 5px;">
<h1>${headText}</h1>
<div style="width: 100%; display: table;">
    <div style="display: table-row">
        <div style="display: table-cell; vertical-align: top; width: 300px;">
            <br/><br/><br/>
            ${information}
            </div>
        <div style="width: 650px;">
            <canvas id="canvas"></canvas>
        </div>
    </div>
</div>
<script type="text/javascript">
        <#include "js/Chart.bundle.js">
        <#include "js/utils.js">
        <#include "js/jquery-3.3.1.min.js">

    $(document).ready(function() {

        var config = {
            type: 'line',
            data: {
                labels: [],
                datasets: [{
                    label: '',
                    backgroundColor: window.chartColors.green,
                    borderColor: window.chartColors.green,
                    data: [],
                    fill: false,
                },{
                    label: '',
                    backgroundColor: window.chartColors.blue,
                    borderColor: window.chartColors.blue,
                    data: [],
                    fill: false,
                }
                ]
            },
            options: {
                responsive: true,
                title: {
                    display: true,
                    text: 'Istanbul Stock Exchange'
                },
                tooltips: {
                    mode: 'index',
                    intersect: false,
                },
                hover: {
                    mode: 'nearest',
                    intersect: true
                },
                scales: {
                    xAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: 'Time'
                        }
                    }],
                    yAxes: [{
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: 'Value'
                        }
                    }]
                }
            }
        };

        var ctx = document.getElementById("canvas").getContext("2d");
        var myLineChart = new Chart(ctx, config);
        var url = "http://" + location.host + "/refresh";
        setInterval(function() {
            $.ajax({
                type:"get",
                url: url,
                success: function(result){
                    if(null != result){
                        if(myLineChart.data.labels.length > 9) {
                            myLineChart.data.datasets[0].data.shift();
                            myLineChart.data.datasets[1].data.shift();
                            myLineChart.data.labels.shift();
                        }
                        var data1 = result.dataList[0];
                        var data2 = result.dataList[1];

                        myLineChart.data.labels.push(result.time);
                        myLineChart.data.datasets[0].label = data1.name;
                        myLineChart.data.datasets[1].label = data2.name;
                        myLineChart.data.datasets[0].data.push(data1.rate);
                        myLineChart.data.datasets[1].data.push(data2.rate);
                    }
                    myLineChart.update();
                }
            });
        }, 1000);
    });
</script>
</body>

</html>