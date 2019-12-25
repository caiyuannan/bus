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
	String laPath = application.getContextPath()+"/layui/";
	String csspath = application.getContextPath()+"/css/";
	String viewPath = application.getContextPath()+"/jsp/";
	String jspath = application.getContextPath()+"/js/";
%>
<html>
<head>
	<link rel="stylesheet" href="<%=laPath+"css/layui.css"%>">
	<script src="<%=laPath+"layui.js"%>"></script>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<script src=<%=jspath+"jquery-3.4.1.js"%>></script>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Hello, World</title>
	<style type="text/css">
		html{height:100%}
		body{height:100%;margin:0px;padding:0px}
		#container{height:100%}
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
					<option  value="${item.routeName}">${item.routeName}</option>
				</c:forEach>
			</c:if>
		</select>
	</div>
</div>
<div id="container"></div>
<script type="text/javascript">
	var map = new BMap.Map("container");
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 13);
	map.enableScrollWheelZoom(true);
	var res = null;
	var arrayList = [] ;
	function showPoly(pointList){
		//循环显示点对象
		for(c=0;c<pointList.length;c++){
			var marker = new BMap.Marker(pointList[c]);
			map.addOverlay(marker);
			//将途经点按顺序添加到地图上
			var label = new BMap.Label(c+1,{offset:new BMap.Size(20,-10)});
			if (c===0) {
				var bum = new BMap.Label('开始站点',{offset:new BMap.Size(20,-10)});
				marker.setLabel(bum)
			}else if (c===pointList.length-1) {
				var bum = new BMap.Label('结束站点',{offset:new BMap.Size(20,-10)});
				marker.setLabel(bum)
			}else {
				marker.setLabel(label);
			}

		}

		var  group = Math.floor( pointList.length /11 ) ;
		var mode = pointList.length %11 ;

		var driving = new BMap.DrivingRoute( map, {onSearchComplete: function(results){
				if (driving.getStatus() == BMAP_STATUS_SUCCESS){
					var plan = driving.getResults().getPlan(0);
					var  num = plan.getNumRoutes();
					for(var j =0;j<num ;j++){
						var pts= plan.getRoute(j).getPath();    //通过驾车实例，获得一系列点的数组
						var polyline = new BMap.Polyline(pts);
						map.addOverlay(polyline);
					}
				}
			}});
		for(var i =0;i<group;i++){
			var waypoints = pointList.slice(i*11+1,(i+1)*11);
			//注意这里的终点如果是11的倍数的时候，数组可是取不到最后一位的，所以要注意终点-1喔。感谢song141的提醒，怪我太粗心大意了~
			driving.search(pointList[i*11], pointList[(i+1)*11-1],{waypoints:waypoints});//waypoints表示途经点
		}
		if( mode != 0){
			var waypoints = pointList.slice(group*11,pointList.length-1);//多出的一段单独进行search
			driving.search(pointList[group*11],pointList[pointList.length-1],{waypoints:waypoints});
		}

	}


	layui.use('form', function() {
		var form = layui.form;
		form.on('select(require1d)', function (data) {
			var shfitBusLine = $("#beforeBusState1").val();
			console.log(shfitBusLine);
			$.ajax({
				url :'http://localhost:8080/bus/selectAllStationServlet',
				type: "post",
				data: {shfitBusLine:shfitBusLine},
				datatype: "json",
				async: true,
				success:function (date) {

					if (date!=null && date.length>0){
						res = date;
						for (var i = 0; i < date.length; i++) {
							arrayList.push(new BMap.Point(date[i].lon,date[i].lat))
						}
						showPoly(arrayList);
						setTimeout(function () {
							map.setViewport(arrayList);          //调整到最佳视野
						}, 1000);
					}

				}
			})

			// var p1 = new BMap.Point(118.194034,24.489054);
			// var p2 = new BMap.Point(118.196367,24.509667);
			// var p3 = new BMap.Point(118.165376,24.543141);
			// var p4 = new BMap.Point(118.114464,24.591745);
			// var p5 = new BMap.Point(118.079396,24.640207);
			// arrayList.push(p1,p2,p3,p4,p5);


		});
	})

	//定位
	// var geolocation = new BMap.Geolocation();
	// geolocation.getCurrentPosition(function(r){   //定位结果对象会传递给r变量
	// 	if(this.getStatus() == BMAP_STATUS_SUCCESS){  //通过Geolocation类的getStatus()可以判断是否成功定位。
	// 		var mk = new BMap.Marker(r.point);    //基于定位的这个点的点位创建marker
	// 		map.addOverlay(mk);    //将marker作为覆盖物添加到map地图上
	// 		map.panTo(r.point);   //将地图中心点移动到定位的这个点位置。注意是r.point而不是r对象。
	// 		alert('您的位置：'+r.point.lng+','+r.point.lat);  //r对象的point属性也是一个对象，这个对象的lng属性表示经度，lat属性表示纬度。
	// 	} else {
	// 		alert('failed'+this.getStatus());
	// 	}
	// },{enableHighAccuracy: true})
	// // 百度地图API功能




</script>
</body>
</html>
