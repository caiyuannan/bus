<%--
  Created by IntelliJ IDEA.
  User: 70716
  Date: 2019/12/7
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=0c0Nx611CCv4c4kzt5ZBzumpRGLBktZ6"></script>
	<script src=<%=path+"/js/jquery-3.4.1.js"%>></script>
	<title>站点地图</title>
</head>
<body>
<div id="allmap"></div>
<input type="hidden" id="cityNameHidden" value="${requestScope.routeName}">
</body>

<script type="text/javascript">
	// 百度地图API功能
	var softPoint = new BMap.Point(118.194034,24.489054);// 创建点坐标,软二
	//地图初始化
	var map = new BMap.Map("allmap"); // 创建地图实例
	map.centerAndZoom(softPoint, 11); // 初始化地图，设置中心点坐标和地图级别(越大越详细)
	map.addControl(new BMap.NavigationControl());//控制控件位置
	map.enableScrollWheelZoom(true);//开启鼠标滚轮缩放
	map.enableKeyboard(true);//启用键盘上下左右键移动地图

	// 编写自定义函数,创建标注
	function addMarker(point){
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
	}
	$.ajax({
		url:"/bus/route/stationMapViewAjax",
		type:"POST",
		data:{"routeName":$("#cityNameHidden").val()},
		dataType:"json",
		async:true,
		success:function (data) {
			for (var i = 0; i < data.length; i ++) {
				var point = new BMap.Point(data[i].stationLon,data[i].stationLat);
				var mk = new BMap.Marker(point);
				map.addOverlay(mk);
				// addMarker(point);
				var label = new BMap.Label(data[i].stationName,{offset:new BMap.Size(20,-10)});//覆盖物上的标签
				mk.setLabel(label);
			}
		},
		error:function () {
			alert("出错啦...");
		}
	})
</script>
</html>