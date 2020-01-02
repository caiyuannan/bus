<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2019/12/24
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath();
	String laPath = application.getContextPath() + "/layui/";
	String csspath = application.getContextPath() + "/css/";
	String viewPath = application.getContextPath() + "/jsp/";
	String jspath = application.getContextPath() + "/js/";
%>
<html>
<head>
	<link rel="stylesheet" href="<%=laPath+"css/layui.css"%>">
	<script src="<%=laPath+"layui.js"%>"></script>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<script src=<%=jspath + "jquery-3.4.1.js"%>></script>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Hello, World</title>
	<style type="text/css">
		html {
			height: 100%
		}

		body {
			height: 100%;
			margin: 0px;
			padding: 0px
		}

		#container {
			height: 100%
		}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=Cyk0nfKrPb8pL456eO0OHXTGGiptZEbm">
		//v3.0版本的引用方式：src="http://api.map.baidu.com/api?v=3.0&ak=您的密钥"
	</script>
</head>

<body>
<div class="layui-form">
	<label class="layui-form-label" style="width: 100px"><i class="asterisk">*</i>请选择线路:</label>
	<div class="layui-inline" style="margin-right: 50px;width: 200px">
		<select name="driverState" id="beforeBusState1" lay-filter="require1d">
			<option value="0">请选择线路</option>
			<c:if test="${requestScope.allRouteBeans != null}">
				<c:forEach var="item" items="${requestScope.allRouteBeans}" begin="0" step="1">
					<option value="${item.routeName}">${item.routeName}</option>
				</c:forEach>
			</c:if>
		</select>
	</div>
</div>
<div id="container"></div>
<script type="text/javascript">
	var time = 1;


	var map = new BMap.Map("container");
	map.centerAndZoom(new BMap.Point(118.194034, 24.489054), 13);
	map.enableScrollWheelZoom(true);
	map.setCurrentCity("厦门");          // 设置地图显示的城市 此项是必须设置的
	var res = null;
	var arrayList = [];
	var markers = [];
	var marker;
	var mapList = [];
	var point;
	var paths;
	var pts;
	var myIcon = new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(37, 25), {imageOffset: new BMap.Size(0, 0)});
	var time1 = 0;
	var interval;
	function showPoly(pointList) {
		//循环显示点对象
		//http://lbsyun.baidu.com/jsdemo/img/car.png
		for (c = 0; c < pointList.length; c++) {
			marker = new BMap.Marker(pointList[c]);
			//将标记点显示在地图上
			map.addOverlay(marker);
			//获得所有的marker 在后面刷新时将之前的点全部清除
			markers.push(marker);
			//将途经点按顺序添加到地图上
			// var label = new BMap.Label(c+1,{offset:new BMap.Size(20,-10)});
			//设置标注
			if (c === 0) {
				var bum = new BMap.Label('公交始/终总站点' + '    -v-   ' + res[0].stationName, {offset: new BMap.Size(20, -10)});
				marker.setLabel(bum)
			} else if (c === pointList.length - 1) {
				var bum = new BMap.Label('公交始/终总站点' + '    -v-   ' + res[res.length - 1].stationName, {offset: new BMap.Size(20, -10)});
				marker.setLabel(bum)
			} else {
				var bum = new BMap.Label('途径站点' + '    -v-   ' + res[c].stationName, {offset: new BMap.Size(20, -10)});
				marker.setLabel(bum);
			}
		}

		var driving = new BMap.DrivingRoute(map);
		var locIndex = 1;

		driving.search(arrayList[0], arrayList[1]);

		driving.setSearchCompleteCallback(function () {
			if(!driving.getResults()) return ;
			pts = driving.getResults().getPlan(0).getRoute(0).getPath();    //通过驾车实例，获得一系列点的数组
			var polyline = new BMap.Polyline(pts);
			map.addOverlay(polyline);
			paths = pts.length;    //获得有几个点

			for (var i = 0; i < paths; i++) {
				var bp= new BMap.Point(pts[i].lng, pts[i].lat);
				mapList.push(bp);
			}

			if(locIndex < arrayList.length - 1 ){
				driving.search(arrayList[locIndex], arrayList[locIndex + 1]);
				locIndex ++;
			}
		})


	}





	layui.use('form', function () {
		var form = layui.form;
		form.on('select(require1d)', function (data) {
			var shfitBusLine = $("#beforeBusState1").val();
			if (mapList.length > 0) {
				clearInterval(interval);
				if (mapList.length > 0) {
					paths = 0;
					mapList = [];
				}
				time1 = 0;
			}
			$.ajax({
				url: 'http://localhost:8080/bus/selectAllStationServlet',
				type: "post",
				data: {shfitBusLine: shfitBusLine},
				datatype: "json",
				async: true,
				success: function (date) {
					if (date != null && date.length > 0) {
						if (mapList.length > 0) {
							mapList = [];
							paths = 0;
						}
						res = date;
						var allOverlay = map.getOverlays();
						for (var j = 0; j < allOverlay.length; j++) {
							map.removeOverlay(allOverlay[j]);
						}
						arrayList = [];
						for (var i = 0; i < res.length; i++) {
							console.log(date[i].lon, date[i].lat);
							arrayList.push(new BMap.Point(date[i].lon, date[i].lat))
						}
						showPoly(arrayList);
						//自适应查看所有点
						map.setViewport(arrayList);
						//刷新成功后等待几秒

						setTimeout("showBus()", 3000);
					} else {
						layer.msg("该线路还未开放哦")
					}
				}
			})
		});
	})

	function showBus(){
		point = new BMap.Marker(mapList[time1], {icon: myIcon});
		map.addOverlay(point);

		setTimeout("showBusPosition()", 500);
	}
	function showBusPosition() {
		if (time1 < mapList.length) {
			point.setPosition(mapList[time1]);
			console.log(mapList[time1].lng, mapList[time1].lat);
			// 创建点坐标
			time1++;
		} else {
			map.removeOverlay(point);
		}
		setTimeout("showBusPosition()", 100);
	}

</script>
</body>
</html>
