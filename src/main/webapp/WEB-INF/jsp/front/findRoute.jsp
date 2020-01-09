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
	<title>线路查询</title>
	<meta name="keywords" content="智慧公交系统_线路查询" />
	<meta name="description" content="智慧公交系统_线路查询" />
	<link rel="stylesheet" href=<%=path + "/bootstrap/css/bootstrap.min.css"%>>
	<link rel="stylesheet" href=<%=cssPath + "login.css"%>>
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src=<%=path + "/bootstrap/js/bootstrap.min.js"%>></script>



	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
	<link rel="icon" href="./img/favicon.ico">
	<link rel="stylesheet" href=<%=uicssPath +"css/layui.css"%> media="all">
	<script src="<%=jsPath+"jquery-3.4.1.js"%>"></script>
	<script src=<%=uiPath+"layui.js"%>></script>
	<script src=<%=jsPath+"advertisement.js"%>></script>

</head>
<body>

<div class="container"style="margin-top: 20px;">
	<h3 class="panel-title" style="font-size: 45px;">
		线路查询
	</h3>
	<div class="row clearfix" style="margin-top: 20px;">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">

				<div class="collapse navbar-collapse row" id="bs-example-navbar-collapse-1">

					<form class="navbar-form  col-md-9" role="search" style="width: 75%;">
						<div class="form-group">
<%--							<input type="text" class="form-control" />--%>
<%--							输入开始站点--%>
		                  <input type="text" list="itemlist" id="startStations" />
		                     <datalist id="itemlist">
			                   <c:if test="${null != requestScope.stationList}">
				                 <c:forEach items="${requestScope.stationList}" begin="0" step="1" var="i">
					               <option  value="${i.stationName}">${i.stationName}</option>
				                 </c:forEach>
			                   </c:if>
		                     </datalist>
						</div>

						<button type="button" class="btn btn-default btn-sm" onclick="inquireMethod()">
						<span class="glyphicon glyphicon-search"></span> Search
					</button>
						<div class="layui-input-inline" >
							<select id="routeListSelect" name="routeListSelect"  style="margin-left: 3%;" onchange="change()">
<%--								<option  value=""></option>--%>
<%--								<c:if test="${null != requestScope.routesNamelis}">--%>
<%--									<c:forEach items="${requestScope.routesNamelis}" begin="0" step="1" var="i">--%>
<%--										<option  value="${i.routeName}">${i.routeName}</option>--%>
<%--									</c:forEach>--%>
<%--								</c:if>--%>
							</select>
						</div>
					</form>

					<div class="col-md-3" style="text-align: right;padding-top: 20px;">
						<span>admin</span>/注销
					</div>
				</div>


			</nav>
			<div class="jumbotron" style="height: 400px;box-sizing:border-box;padding:1%;">
				<div class="map" style="width:100%;height:100%;background-color: #eea236;color: #ffef8f">
<%--					地图内容--%>
	              <div id="allmap"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	var items =[];
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	// map.centerAndZoom(new BMap.Point(113.962196, 22.545144), 13);
	var point = new BMap.Point(113.962196, 22.545144);
	map.centerAndZoom(point,12);
	map.enableScrollWheelZoom(); // 允许滚轮缩放

	// var myP1 = new BMap.Point(113.962196, 22.545144);
	// var myP2 = new BMap.Point(113.987268, 22.54884);
	// var myP3 = new BMap.Point(113.976281, 22.53543);
	// var myP4 = new BMap.Point(113.957956, 22.539636);

	//终点
	// var myIcon = new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/Mario.png", new BMap.Size(32, 70), {    //小车图片
	// 	//offset: new BMap.Size(0, -5),    //相当于CSS精灵
	// 	imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。
	// });
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
		// driving.search(myP1, myP2);                 //第一个驾车搜索
		// driving.search(myP2, myP3);                 //第二个驾车搜索
		driving.setSearchCompleteCallback(function(){
			var pts = driving.getResults().getPlan(0).getRoute(0).getPath();    //通过驾车实例，获得一系列点的数组

			var polyline = new BMap.Polyline(pts);
			map.addOverlay(polyline);

			// for (var h = 0; h < arg.length; h++) {
			//
			// 	var hh = new BMap.Marker(arg[h]);
			// 	map.addOverlay(hh);
			// 	var hhh = new BMap.Label("站点"+h,{position:arg[h]});
			// 	map.addOverlay(hhh);
			// }
			// var m1 = new BMap.Marker(myP1);         //创建3个marker
			// var m2 = new BMap.Marker(myP2);
			// var m3 = new BMap.Marker(myP3);
			// map.addOverlay(m1);
			// map.addOverlay(m2);
			// map.addOverlay(m3);

			// var lab1 = new BMap.Label("起点",{position:myP1});        //创建3个label
			// var lab2 = new BMap.Label("途径点",{position:myP2});
			// var lab3 = new BMap.Label("终点",{position:myP3});
			// map.addOverlay(lab1);
			// map.addOverlay(lab2);
			// map.addOverlay(lab3);

			// setTimeout(function(){
			// 	map.setViewport(arg);          //调整到最佳视野
			// },1000);

		});
	}



	// run([myP1,myP2,myP3,myP4]);
	var x;
	var y;
	function inquireMethod() {
		alert($("#startStations").val());
		var startStations=$("#startStations").val();
		$.ajax({
			url:"/bus/findRouteStationName?stationName="+startStations,
			// data: ob,
			type:"Post",
			dataType:"json",
			async: true,
			success: function (data) {

				if (data.length > 0) {
					// project = data.rows;
					addOptions(data);
				}
				inquireMethod2();

				<%--alert(msg.stationLon);--%>
				<%--alert("inquireMethod");--%>
				<%--<c:if test="${null != requestScope.StationLat}">--%>
				<%--x=${requestScope.StationLat};--%>
				<%--y=${requestScope.StationLon};--%>
				<%--</c:if>--%>
				<%--alert(x+y);--%>
			},
			error:function(data){
				alert("服务器正忙");
			}
		});
	}
	function addOptions(project) {

		var pro = $('#routeListSelect');

		//清空原先的值
		var area= document.getElementById("routeListSelect");
		area.innerHTML = "";
		for(var i=0;i<area.childNodes.length;i++){
			area.removeChild(area.options[i]);
			area.remove(i);
			area.options[i] = null;
		}

        //重新赋值
		var options = '<option value ="0">请选择</option>';
		$(project).each(function() {
			options += '<option value="' + this.routeName + '" >' + this.routeName + '</option>';

		});

		pro.append(options);
	}
	function inquireMethod2() {

		var startStations=$("#startStations").val();
		$.ajax({
			url:"/bus/findRouteStationName2?stationName="+startStations,
			// data: ob,
			type:"Post",
			dataType:"json",
			async: true,
			success: function (data) {
				// var jsonData = JSON.parse(data);
				y=data.stationLat;
				x=data.stationLon;
			},
			error:function(data){
				alert("服务器正忙");
			}
		});
	}
	function change() {
		var routeNameSelect=$("#routeListSelect").val();
		items.length = 0;
		map.clearOverlays();
		// var interval = setInterval(run, 1);
		// clearInterval(interval);
		$.ajax({
			url:"/bus/StationByRouteName?routeName="+routeNameSelect,
			// data: ob,
			type:"Post",
			dataType:"json",
			async: true,
			success: function (data) {

				for (var i = 0; i < data.length; i ++) {
					var point1 = new BMap.Point(data[i].stationLon,data[i].stationLat);
					items.push(point1);

					//标注站点位置和名字
					var mk = new BMap.Marker(point1);
					map.addOverlay(mk);
					// addMarker(point);
					var label = new BMap.Label(data[i].stationName,{offset:new BMap.Size(20,-10)});//覆盖物上的标签
					mk.setLabel(label);

				}
				//游览器定位当前位子
				var geolocation = new BMap.Geolocation();
				geolocation.getCurrentPosition(function(r){
					if(this.getStatus() == BMAP_STATUS_SUCCESS){
						var mk = new BMap.Marker(r.point);
						map.addOverlay(mk);
						map.panTo(r.point);
						// alert('您的位置：'+r.point.lng+','+r.point.lat);
						var point2 = new BMap.Point(r.point.lng,r.point.lat);
						items.push(point2);
						//标注当前位置和名字
						var mk2 = new BMap.Marker(point2);
						map.addOverlay(mk2);
						// addMarker(point);
						var label2 = new BMap.Label("我的当前位置",{offset:new BMap.Size(20,-10)});//覆盖物上的标签
						mk2.setLabel(label2);
						//其实站点位置
						var point3= new BMap.Point(x,y);
						//步行到站点路线
						var walking = new BMap.WalkingRoute(map, {renderOptions:{map: map, autoViewport: true}});
						walking.search(point2,point3);
					}
					else {
						alert('failed'+this.getStatus());
					}
				},{enableHighAccuracy: true});


				run(items);

			},
			error:function(msg){
				alert("服务器正忙");
			}
		});

	}



</script>
</html>
