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
	<title>站点地图</title>
</head>
<body>
<div id="allmap"></div>
</body>

<script type="text/javascript">
	alert("${requestScope.station.stationName}")
	// 百度地图API功能
	var softPoint = new BMap.Point(${requestScope.station.stationLon},${requestScope.station.stationLat});// 创建点坐标,软二
	//地图初始化
	var map = new BMap.Map("allmap"); // 创建地图实例
	map.centerAndZoom(softPoint, 15); // 初始化地图，设置中心点坐标和地图级别(越大越详细)
	map.addControl(new BMap.NavigationControl());//控制控件位置
	map.enableScrollWheelZoom(true);//开启鼠标滚轮缩放
	map.enableKeyboard(true);//启用键盘上下左右键移动地图
	var mk = new BMap.Marker(softPoint);
	map.addOverlay(mk);//将marker作为覆盖物添加到map地图上
	mk.setAnimation(BMAP_ANIMATION_BOUNCE);//跳动的动画
	var label = new BMap.Label("${requestScope.station.stationName}",{offset:new BMap.Size(20,-10)});//覆盖物上的标签
	mk.setLabel(label);
	// map.panTo(point);//将地图中心点移动到定位的这个点位置。
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		alert(e.point.lng + "," + e.point.lat);
	});
</script>
</html>