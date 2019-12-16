<%--
  Created by IntelliJ IDEA.
  User: 谢海安
  Date: 2019/12/16
  Time: 8:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" charset="UTF-8">
	<title>用户查询</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<script src=<%=path+"/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path+"/layui/layui.js"%>></script>
</head>
<body>

<div class="layui-row" id="editUser" style="display:none;">
	<div class="layui-inline">
		<label class="layui-form-label">用户名:</label>
		<label class="layui-form-label" id="userLabel"></label>
		<label class="layui-form-label">修改密码:</label>
		<input class="layui-input" id="newPassword">
		<button class="layui-btn" id="update" >修改</button>
	</div>
</div>

<div class="demoTable site-demo-button" id="layerDemo">
	省份：
	<div class="layui-inline">
		<input name="provinceName" class="layui-input" id="provinceName" autocomplete="off">
	</div>
	城市：
	<div class="layui-inline">
		<input name="cityName" class="layui-input" id="cityName" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload" id="search-btn">搜索</button>
	<button data-method="addAdmin" data-type="auto" class="layui-btn layui-btn-normal">新增人员</button>
</div>

<table class="layui-hide" id="test" lay-filter="test"></table>
<script id="barDemo" type="text/html">
	<a class="layui-btn layui-btn-xs" lay-event="modify">修改</a>
	<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="changeState">禁用/启用</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
	var reload = null;
	layui.use('table', function(){
		var table = layui.table;
		table.render({
			elem: '#test'
			,url: '/bus/city/findCity'
			,cols: [[
				{checkbox: true, fixed: true}
				,{field:'num', width:80, title: '序号', sort: true}
				,{field:'provinceName', width:80, title: '省份'}
				,{field:'cityName', width:80, title: '城市', sort: true}
				,{field:'stationCounts', width:120, title: '站点数', sort: true}
				,{field:'routeCounts', width:120, title: '线路数', sort: true}
				,{field: 'operate', title:'操作', toolbar: '#barDemo', width:200}
			]]
			,initSort: {
				field: 'num' //排序字段，对应 cols 设定的各字段名
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
						url:"/admin/deleteAdmin",
						type:"POST",
						data:{"adId":data.adId},
						dataType:"text",
						async:true,
						success:function (data) {
							alert(data);
							if(data === '删除成功!'){
								layui.use('layer', function(){
									reload();
								});
							}
						},
						error:function () {
							alert("出错啦...");
						}
					})
					obj.del();
					layer.close(index);
				});
			} else if(obj.event === 'changeState'){
				layer.confirm('确定修改状态？', function(index){
					$.ajax({
						url:"/admin/changeState",
						type:"POST",
						data:{"adId":data.adId,"stId":data.stId},
						dataType:"text",
						async:true,
						success:function (data) {
							alert(data);
							var str = data.replace("\"", "").replace("\"", "");
							if(str === '更改成功!'){
								layui.use('layer', function(){
									reload();
								});
							}
						},
						error:function () {
							alert("出错啦...");
						}
					})
					layer.close(index);
				});
			} else if(obj.event === 'modify') {
				$("#userLabel").text(data.adId);
				layer.open({
					type: 1
					,title:"修改个人信息"
					,content: $("#editUser")
					,area: ['400px', '350px']
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
								/*layui.use('layer', function(){
									reload();
									密码没有显示在界面上，更新不了
								});*/
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
					"id":$("#demoReload").val()
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
			addAdmin: function(othis){
				var type = othis.data('type')
					,text = othis.text();

				layer.open({
					type: 2
					,title:['新增人员','font-size:20px']
					,offset: type
					,id: 'MODIFY'+type //防止重复弹出
					,content: ['/admin/addAdminView', 'no']
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
</body>
</html>
