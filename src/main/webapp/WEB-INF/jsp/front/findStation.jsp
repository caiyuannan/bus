<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/30
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String cssPath = application.getContextPath()+"/css/" ;
	String jsPath =application.getContextPath()+"/js/";
	String imgPath=request.getContextPath()+"/img/";

	String uicssPath = request.getContextPath()+"/layui/" ;
	String uiPath = request.getContextPath()+"/layui/";
%>
<html>
<head>
	<title>智慧公交系统_站点查询</title>
	<meta name="keywords" content="智慧公交系统_站点查询" />
	<meta name="description" content="智慧公交系统_站点查询" />
	<link rel="stylesheet" href=<%=path + "/bootstrap/css/bootstrap.min.css"%>>
	<link rel="stylesheet" href=<%=cssPath + "login.css"%>>
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src=<%=path + "/bootstrap/js/bootstrap.min.js"%>></script>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=E06eb9d756d0eafc722effb355657b4c"></script>
	<link rel="icon" href="./img/favicon.ico">
	<link rel="stylesheet" href=<%=uicssPath +"css/layui.css"%> media="all">
	<script src="<%=jsPath+"jquery-3.4.1.js"%>"></script>
	<script src=<%=uiPath+"layui.js"%>></script>

	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{height:300px;width:100%;}
		#r-result,#r-result table{width:100%;}
	</style>

</head>
<body>

<div class="container"style="margin-top: 20px;">
	<h3 class="panel-title" style="font-size: 45px;">
		站点查询
	</h3>
	<div class="row clearfix" style="margin-top: 20px;">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">

				<div class="collapse navbar-collapse row" id="bs-example-navbar-collapse-1">

					<form class="navbar-form  col-md-9" role="search" style="width: 75%;">
						<div class="form-group">
<%--							<label for="start">开始站点:</label>--%>
<%--							<input type="text" class="form-control" id="start"/>&nbsp;-&nbsp;--%>
<%--							<label for="end">结束站点:</label>--%>
<%--							<input type="text" class="form-control" id="end"/>--%>
<%--								输入开始站点--%>
	                             <label for="startStations">开始站点:</label>
	                            <input type="text" list="itemlist" id="startStations" />
	                              <datalist id="itemlist">
		                           <c:if test="${null != requestScope.stationList}">
			                         <c:forEach items="${requestScope.stationList}" begin="0" step="1" var="i">
			                        	<option  value="${i.stationName}">${i.stationName}</option>
			                         </c:forEach>
		                           </c:if>
	                              </datalist>
<%--	                                   输入终点站点--%>
	                            <label for="endStations">结束站点:</label>
	                            <input type="text" list="endlist" id="endStations" />
	                              <datalist id="endlist">
		                            <c:if test="${null != requestScope.stationList}">
			                          <c:forEach items="${requestScope.stationList}" begin="0" step="1" var="i">
				                        <option  value="${i.stationName}">${i.stationName}</option>
			                          </c:forEach>
		                            </c:if>
	                              </datalist>

	<button type="button" class="btn btn-default btn-sm" onclick="inquireMethod()"/>
		<span class="glyphicon glyphicon-search"></span> Search
						</div>

<%--						<button type="submit" class="btn btn-default btn-sm">--%>
<%--						<span class="glyphicon glyphicon-search"></span> Search--%>
<%--					</button>--%>
					</form>

					<div class="col-md-3" style="text-align: right;padding-top: 20px;">
						<span>admin</span>/注销
					</div>
				</div>


			</nav>
			<div class="jumbotron" style="height: 350px;box-sizing:border-box;padding:1%;">
				<div class="map" style="width:100%;height:100%;background-color: #eea236;color: #ffef8f">
<%--					当前位置-附近站点--%>
	<div id="allmap"></div>
<%--	<input type="button" id="result" value="查询" onclick="as()"/>--%>
	<p id="pp"> </p>
	<p id="ppp"></p>
	<div id="r-result">

	</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript">
	var driving;
	var items =[];
	var output;
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	// map.centerAndZoom(new BMap.Point(113.962196, 22.545144), 13);
	var point = new BMap.Point(113.962196, 22.545144);
	map.centerAndZoom(point,12);
	map.enableScrollWheelZoom(); // 允许滚轮缩放

	output= "查询结果：";
	var searchComplete = function (results){
		if (driving.getStatus() != BMAP_STATUS_SUCCESS){
			return ;
		}
		var plan = results.getPlan(0);
		output += plan.getDuration(true) + "\n";  //获取时间
		output += plan.getDistance(true) + "\n";  //获取距离
		texts+=output;
		$("#pp").text(texts);
		$("#ppp").text("下面是步行到乘坐站点的终点路线和时间距离告知");
		// alert(texts);
	};


	var driving2 = new BMap.DrivingRoute(map,
		{
			renderOptions: { //结果呈现设置
				map: map,
				autoViewport: true,  //检索结束后自动调整地图视野
				enableDragging: true   //起终点可进行拖拽
			},
			policy: BMAP_DRIVING_POLICY_LEAST_DISTANCE //驾车方案的策略配置,最少步行


		});

	function run(ar){
		var arg = ar;
		// map.clearOverlays();                        //清除地图上所有的覆盖物
		var driving = new BMap.DrivingRoute(map,
			{
				renderOptions: { //结果呈现设置
					map: map,
					autoViewport: true //检索结束后自动调整地图视野
					// enableDragging: true   //起终点可进行拖拽
				},
				policy: BMAP_DRIVING_POLICY_LEAST_DISTANCE //驾车方案的策略配置,最少步行


			});    //创建驾车实例
		for (var h = 0; h < arg.length; h++) {
			driving.search(arg[h], arg[h+1]);
		}
		driving.setSearchCompleteCallback(function(){
			var pts = driving.getResults().getPlan(0).getRoute(0).getPath();    //通过驾车实例，获得一系列点的数组

			var polyline = new BMap.Polyline(pts);
			map.addOverlay(polyline);

		});
	}






var texts;
	var point2;
	var point3;
	function inquireMethod() {
		// alert($("#startStations").val());
		var startStations=$("#startStations").val();
		var endStations=$("#endStations").val();
		output= "从"+$("#startStations").val()+"到"+$("#endStations").val()+"坐公交需要";
		$.ajax({
			url:"/bus/findQueryStationByRouteOrderName?startStationName="+startStations +"&endStationName="+endStations,
			// data: ob,
			type:"Post",
			dataType:"json",
			async: true,
			success: function (date) {
				map.clearOverlays(); //清除地图上所有的覆盖物
				items.length = 0;
				if (date.msg==="查无此路"){
					alert(date.msg);
				}else if(date.msg==="success"){

					// alert(date.sum);
					//
					// alert((date.list).length);

					// for (var i = 0; i < (date.list).length; i ++) {
					// 	alert((date.list)[i].stationName);
					// }
					texts="乘做"+date.routeName+"线共需要"+date.sum+"站才到达,经过：";
				}


				for (var i = 0; i < (date.listStation).length; i ++) {
					var point1 = new BMap.Point((date.listStation)[i].stationLon,(date.listStation)[i].stationLat);
					items.push(point1);
					// alert((date.listStation)[i].stationName);
					//标注站点位置和名字
					var mk = new BMap.Marker(point1);
					map.addOverlay(mk);
					// addMarker(point);
					var label = new BMap.Label((date.listStation)[i].stationName,{offset:new BMap.Size(20,-10)});//覆盖物上的标签
					mk.setLabel(label);

					texts+=(date.listStation)[i].stationName+"，";
				}
				//游览器定位当前位子
				var geolocation = new BMap.Geolocation();
				geolocation.getCurrentPosition(function(r){
					if(this.getStatus() == BMAP_STATUS_SUCCESS){
						var mk = new BMap.Marker(r.point);
						map.addOverlay(mk);
						map.panTo(r.point);
						alert('您的位置：'+r.point.lng+','+r.point.lat);
						 point2 = new BMap.Point(r.point.lng,r.point.lat);
						items.push(point2);
						//标注当前位置和名字
						var mk2 = new BMap.Marker(point2);
						map.addOverlay(mk2);
						// addMarker(point);
						var label2 = new BMap.Label("我的当前位置",{offset:new BMap.Size(20,-10)});//覆盖物上的标签
						mk2.setLabel(label2);


						//其实站点位置
						point3= new BMap.Point(date.startStopY,date.startStopX);
						//步行到站点路线
						var walking = new BMap.WalkingRoute(map, {renderOptions:{map: map, panel: "r-result", autoViewport: true}  });
						walking.search(point2,point3);

						//计算从站点开始全部用时和时间
						var point4 = new BMap.Point((date.listStation)[(date.listStation).length-1].stationLon,(date.listStation)[(date.listStation).length-1].stationLat);

						driving = new BMap.DrivingRoute(map, {renderOptions: {map: map},
							onSearchComplete: searchComplete
							,policy: BMAP_DRIVING_POLICY_LEAST_DISTANCE
						});
						driving.search(point3, point4);
					}
					else {
						alert('failed'+this.getStatus());
					}
				},{enableHighAccuracy: true});


				// var start=new BMap.Point(116.404844,39.911836);
				// var end=new BMap.Point(116.308102,40.056057);
				// transit.search(start, end);


				run(items);
			},
			error:function(date){
				alert("服务器正忙");
			}
		});
	}
	// function as() {
	// 	alert(1);
	// 	alert(output);
	// }


</script>
