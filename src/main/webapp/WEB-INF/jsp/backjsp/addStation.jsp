<%--
  Created by IntelliJ IDEA.
  User: 70716
  Date: 2019/12/17
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" charset="UTF-8">
	<title>增加站点</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<%--<link rel="stylesheet" href=<%=path+"/css/backAddAdmin.css"%>>--%>
	<script src=<%=path+"/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path+"/layui/layui.js"%>></script>
</head>
<body>
<div id="myDiv">

	<div class="layui-form-item">
		<label class="left-first layui-form-label">城市:</label>
		<label id="cityName" class="left-first layui-form-label">${requestScope.cityName}</label>
	</div>

	<div class="layui-form-item">
		<label class="left-first layui-form-label" for="stationName">站点名称:</label>
		<div class="layui-input-inline">
			<input type="text" name="stationName" id="stationName" required  lay-verify="required" placeholder="stationName" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="left-first layui-form-label" for="stationLon">x坐标:</label>
		<div class="layui-input-inline">
			<input type="text" name="stationLon" id="stationLon" required  lay-verify="required" placeholder="stationLon" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="left-first layui-form-label" for="stationLat">y坐标:</label>
		<div class="layui-input-inline">
			<input type="text" name="stationLat" id="stationLat" required  lay-verify="required" placeholder="stationLat" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-filter="formDemo" id="add-btn">添加</button>
			<button type="reset" class="layui-btn layui-btn-primary" id="close-btn">关闭</button>
		</div>
	</div>

</div>
</body>
<script>
	$("#add-btn").click(function () {
		var cityName = $("#cityName").text();
		var stationName = $("#stationName").val();
		var stationLon = $("#stationLon").val();
		var stationLat = $("#stationLat").val();

		alert(cityName+","+stationName+","+stationLon+","+stationLat);
		$.ajax({
			url:"/bus/station/addStation",
			type:"POST",
			data:{"cityName":cityName,"stationName":stationName,"stationLon":stationLon,"stationLat":stationLat},
			dataType:"text",
			async:true,
			success:function (data) {
				alert(data);
				if(data === '添加成功!'){
					layui.use('layer', function(){
						var layer = layui.layer;
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
						parent.reload();
					});
				}
			},
			error:function () {
				alert("出错啦...");
			}
		})
	})
	$("#close-btn").click(
		function () {
			layui.use('layer', function(){
				var layer = layui.layer;
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			});
		}
	)
</script>
</html>
