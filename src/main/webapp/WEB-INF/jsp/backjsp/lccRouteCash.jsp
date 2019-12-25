<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/24
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String layuiPath = request.getContextPath()+"/layui/";
	String jsPath = request.getContextPath()+"/js/";
	String imgPath = request.getContextPath()+"/img/";
%>
<html>
<head>
	<title>收银统计</title>
	<link rel="stylesheet" href="/bus/layui/css/layui.css">
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src=<%=jsPath + "echarts.js"%>></script>
	<script src="/bus/layui/layui.js"></script>
</head>
<body>
<div style="margin-top: 50px;"></div>
<form action=" " method="post">
	<lable>开始日期:</lable><input type="date" id="startTime" name="startTime">
	<lable>结束日期:</lable><input type="date" id="endTime" name="endTime">
	<button class="layui-btn" type="button" onclick="updateTable()">查询</button><br/>

	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="width: 600px;height:400px;"></div>
</form>
</div>

<script type="text/javascript">

	//var obArr = [];

	var nameArr = [];
	var valueArr = [];
	$(function() {

		$.ajax({
			method : "POST",
			url : "/bus/toRouteCash",
			dataType : "text",
			//dataType:"html",
			success : function(msg) {
				var arr = JSON.parse(msg);
				alert(msg);
				for (var i = 0; i < arr.length; i++) {
					// 普通柱状图使用的数据
					nameArr.push(arr[i].routeName);
					valueArr.push(arr[i].incomes);
				}
				createEchars();// 创建普通柱状图

			},
			error : function() {
				alert("服务器正忙");
			}
		});
	});



	function updateTable(){
		nameArr.splice(0);
		valueArr.splice(0);
		var ob = $("#startTime").val()+","+$("#endTime").val();
		//alert(ob);
		var str = JSON.stringify(ob);
		$.ajax({
			method : "POST",
			url : "/bus/toRouteCash",
			data: "msg=" + str,
			dataType : "text",
			//dataType:"html",
			success : function(msg) {
				var arr = JSON.parse(msg);
				alert(msg);
				for (var i = 0; i < arr.length; i++) {
					// 普通柱状图使用的数据
					nameArr.push(arr[i].routeName);
					valueArr.push(arr[i].incomes);
				}
				createEchars();// 创建普通柱状图
				//rose();// 创建南丁格尔玫瑰圆饼图
				//location.reload();
			},
			error : function() {
				alert("服务器正忙");
			}
		});
	}

	//普通柱状图
function createEchars() {
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));

	// 指定图表的配置项和数据
	var option = {
		title: {
			text: '线路收银图'
		},
		color: ['#009688'],
		tooltip: {
			trigger: 'axis',
			axisPointer: {            // 坐标轴指示器，坐标轴触发有效
				type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend: {
			data: ['线路']
		},
		xAxis: {
			data: nameArr
		},

		yAxis: {},
		series : [ {
			name : '线路',
			type : 'bar',
			data : valueArr
		} ]

	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option, true);

	//barChart.setOption(option, true);

}


</script>
</body>
</html>
