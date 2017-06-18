<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function () {
        //创建图表
        var myChart = echarts.init($("#aaaa")[0]);//把一个$对象转换为dom对象,取下标0
        $.post("${pageContext.request.contextPath}/user/countRegistUser",function(data){
            var option = {
                title: {
                    text: '统计用户注册数'
                },
                tooltip: {},
                legend: {
                    data:['用户数量']
                },
                xAxis: {
                    data: data.dates,
                },
                yAxis: {},
                series: [{
                    name: '用户数量',
                    type: 'bar',
                    data: data.counts,
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },"JSON");
    });
</script>
<div id="aaaa" style="width: 600px;height:400px;"></div>


