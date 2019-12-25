<%--
  Created by IntelliJ IDEA.
  User: 70716
  Date: 2019/12/22
  Time: 15:25
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
	<title>城市路线配置表</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<script src=<%=path+"/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path+"/layui/layui.js"%>></script>
</head>
<body>
<%--弹窗内容--%>
<div class="layui-row" id="queryRoute" style="display:none;">
	<button class="layui-btn" data-type="reload" id="forwardRoute">正向路线</button>
	<button class="layui-btn" data-type="reload" id="reverseRoute">反向路线</button>
	<table class="layui-hide" id="routeTable" lay-filter="routeTable"></table>
	<input type="hidden" id="routeNameHidden" value="">
	<input type="hidden" id="stationNameHidden" value="">
	<input type="hidden" id="isReverse" value="1">
</div>
<%--弹窗内容--%>
<div class="layui-row" id="addStationWindow" style="display:none;">
	<div class="layui-form" style="width: 200px">
		<select name="stationSelect" id="stationSelect" lay-filter="myselect">
			<c:forEach items="${requestScope.stationList}" var="i" begin="0" step="1">
				<option value="${i.stationName}">${i.stationName}</option>
			</c:forEach>
		</select>
		<button class="layui-btn" data-type="reload" id="mapStation">地图选站</button>
	</div>
	<button class="layui-btn" data-type="reload" id="forwardAdd">在前增加</button>
	<button class="layui-btn" data-type="reload" id="afterAdd">在后增加</button>
</div>

<div class="demoTable site-demo-button" id="layerDemo">
	站点名称：
	<div class="layui-inline">
		<input name="stationName" class="layui-input" id="stationName" autocomplete="off">
	</div>
	路线名称：
	<div class="layui-inline">
		<input name="routeName" class="layui-input" id="routeName" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload" id="search-btn">搜索</button>
	<button data-method="addRoute" data-type="auto" class="layui-btn layui-btn-normal">新增路线</button>
</div>

<table class="layui-hide" id="test" lay-filter="test"></table>
<script id="barDemo" type="text/html">
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	<a class="layui-btn layui-btn-xs" lay-event="queryRoute">查看线路</a>
	<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="stationMapView">查看站点</a>
</script>

<script id="selectRouteBar" type="text/html">
	<a class="layui-btn layui-btn-xs" lay-event="addStation">添加站点</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="deleteStation">删除</a>
</script>

<script>
	var reload = null;
	layui.use('table', function(){
		var table = layui.table;
		table.render({
			elem: '#test'
			,url: '/bus/route/queryRoute?cityName=${requestScope.cityName}'
			,cols: [[
				{checkbox: true, fixed: true}
				,{field:'routeName', width:120, title: '路线名称'}
				,{field:'stationCount', width:100, title: '站点数', sort: true}
				,{field:'shiftCount', width:100, title: '班次', sort: true}
				,{field:'busCount', width:100, title: '车辆', sort: true}
				,{field: 'operate', title:'操作', toolbar: '#barDemo', width:300}
			]]
			,initSort: {
				field: 'stationId' //排序字段，对应 cols 设定的各字段名
				,type: 'asc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
			}
			,id: 'testReload'
			,page: true
			,height: 400
			,width:950
			,limit:10
			,even:true
			,loading:true
		});

		//监听行工具事件
		table.on('tool(test)', function(obj){
			var data = obj.data;
			if(obj.event === 'del'){
				layer.confirm('确认删除？', function(index){
					$.ajax({
						url:"/bus/station/deleteStation",
						type:"POST",
						data:{"stationId":data.stationId,"cityName":${requestScope.cityName}},
						dataType:"text",
						async:true,
						success:function (data) {
							alert(data);
						},
						error:function () {
							alert("出错啦...");
						}
					})
					obj.del();
					layer.close(index);
				});
			}else if(obj.event === 'stationMapView'){
				window.location = '/bus/route/stationMapView?cityName=${requestScope.cityName}&routeName='+data.routeName
			}else if(obj.event === 'queryRoute') {
				$("#routeNameHidden").val(data.routeName);
				var reload = null;

				//路线明细表格

				layui.use('table', function(){
					var table = layui.table;
					table.render({
						elem: '#routeTable'
						,url: '/bus/route/queryStationByRouteName?routeName='+$("#routeNameHidden").val()
						,cols: [[
							{checkbox: true, fixed: true}
							,{field:'routeOrderNumber', width:120, title: '路线顺序'}
							,{field:'stationName', width:140, title: '站点名', sort: true}
							,{field: 'operate', title:'操作', toolbar: '#selectRouteBar', width:180}
						]]
						,initSort: {
							field: 'stationId' //排序字段，对应 cols 设定的各字段名
							,type: 'asc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
						}
						,id: 'routeTableReload'
						,page: false
						,height: 350
						,width:450
						// ,limit:10
						,even:true
						,loading:true
					});

					//监听行工具事件
					table.on('tool(routeTable)', function(obj){
						var data = obj.data;
						if(obj.event === 'deleteStation'){
							layer.confirm('确认删除？', function(index){
								$.ajax({
									url:"/bus/route/deleteStation",
									type:"POST",
									data:{"routeName":$("#routeNameHidden").val(),"stationName":data.stationName,"routeOrderNumber":data.routeOrderNumber},
									dataType:"text",
									async:true,
									success:function (data) {
										alert(data);
										layui.use('layer', function(){
											routeReload();
										});
									},
									error:function () {
										alert("出错啦...");
									}
								})
								obj.del();
								layer.close(index);
							});
						} else if(obj.event === 'addStation') {
							$("#stationNameHidden").val(data.stationName);
							layer.open({
								type: 1
								,title:"添加站点"
								,content: $("#addStationWindow")
								,area: ['400px', '300px']
								,zIndex: layer.zIndex //重点1
								,success: function(layero){
									layer.setTop(layero); //重点2
								}
							});
						}
					});

					routeReload = function(){
						table.reload('routeTableReload', {
							where: {
								"isReverse":$("#isReverse").val()
							}
						}, 'data');
					}

					$("#reverseRoute").click(function () {
						$("#isReverse").val(2);
						//执行重载
						routeReload();
					})
					$("#forwardRoute").click(function () {
						$("#isReverse").val(1);
						//执行重载
						routeReload();
					})
					$("#forwardAdd").click(function () {
						alert("路线名:"+$("#routeNameHidden").val());
						alert($("#stationNameHidden").val());
						alert($("#stationSelect").val());
						$.ajax({
							url:"/bus/route/insertStation",
							type:"POST",
							data:{"routeName":$("#routeNameHidden").val(),"stationName":$("#stationNameHidden").val(),"addStationName":$("#stationSelect").val()},
							dataType:"text",
							async:true,
							success:function (data) {
								alert(data);
								layui.use('layer', function(){
									routeReload();
								});
							},
							error:function () {
								alert("出错啦...");
							}
						})
					})
					$("#afterAdd").click(function () {

					})
				});
				layui.use('layer', function(){ //独立版的layer无需执行这一句
					var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
					//触发事件
					var active = {
						addStation: function(othis){
							var type = othis.data('type')
								,text = othis.text();
							layer.open({
								type: 2
								,title:['新增城市','font-size:20px']
								,offset: type
								,id: 'MODIFY'+type //防止重复弹出
								,content: ['/bus/station/addStationView?cityName=${requestScope.cityName}', 'no']
								,area: ['400px', '350px']
								,shade: 0 //不显示遮罩
							});
						},
					};

					$('#layerDemo .layui-btn').on('click', function(){
						var othis = $(this), method = othis.data('method');
						active[method] ? active[method].call(this, othis) : '';
					});
				});

				layer.open({
					type: 1
					,title:"查看线路"
					,content: $("#queryRoute")
					,area: ['500px', '400px']
				});
			}

			$("#update").on('click',function () {
				var adId = $("#userLabel").text();
				var adPw = $("#newPassword").val();
				layer.confirm('确定修改信息？', function(index){
					$.ajax({
						url:"/admin/modifyAdmin",
						type:"POST",
						data:{"adId":adId,"adPassword":adPw},
						dataType:"text",
						async:true,
						success:function (data) {
							alert(data);
							var str = data.replace("\"", "").replace("\"", "");
							if(str === '更改成功!'){
								layui.use('layer', function(){
									reload();
									// 密码没有显示在界面上，更新不了
								});
								layer.closeAll();
							}
						},
						error:function () {
							alert("出错啦...");
						}
					})
					layer.close(index);
				});
			});

		});

		reload = function(){
			table.reload('testReload', {
				page: {
					curr: 1 //重新从第 1 页开始
				}
				,where: {
					"stationName":$("#stationName").val(),
				}
			}, 'data');
		}

		$("#search-btn").click(function () {
			//执行重载
			reload();
		})
	});
	layui.use('layer', function(){ //独立版的layer无需执行这一句
		var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
		//触发事件
		var active = {
			addStation: function(othis){
				var type = othis.data('type')
					,text = othis.text();
				layer.open({
					type: 2
					,title:['新增城市','font-size:20px']
					,offset: type
					,id: 'MODIFY'+type //防止重复弹出
					,content: ['/bus/station/addStationView?cityName=${requestScope.cityName}', 'no']
					,area: ['400px', '350px']
					,shade: 0 //不显示遮罩
				});
			},
		};

		$('#layerDemo .layui-btn').on('click', function(){
			var othis = $(this), method = othis.data('method');
			active[method] ? active[method].call(this, othis) : '';
		});
	});
</script>
<script>

</script>
</body>
</html>

