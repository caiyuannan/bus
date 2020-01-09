<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/2
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String jspath = request.getContextPath() + "/js/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>角色配置</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet"  href="<%=path+"/layui/css/layui.css"%>" media="all">
	<script  src="<%=path + "/layui/layui.js"%>"></script>
	<script  type="<%=jspath+"jquery-3.4.1.js"%>"></script>
</head>
<body>
	<div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
		<button data-method="notice" class="layui-btn" onclick="addDiv()">新增</button>
	</div>
	<div style=" padding-top: 10%">
	<table id="demo" lay-filter="test" style="margin: auto"></table>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del1">配置权限</a>
</script>
<table id="text" lay-filter="text"  class="layui-hide"></table>
<script>
	layui.use('table', function() {
		var table = layui.table;
		table.render({
			elem: '#text'
			,url:'http://localhost:8080/bus/XdjRoleServlet'
			,title: '角色配置'
			,page: true
			,limit: 5 //开启分页
			,limits: [5,10,20] //开启分页
			,id: 'idTest'
			,cols: [[
				{field:'roleId', title:'ID',width:100}
				,{field:'roleName', title:'角色名称', width:100}
				,{fixed: 'right', title:'操作', toolbar: '#barDemo',width:250}
			]]
			,parseData: function (res) { //res 即为原始返回的数据
				return {
					"code": res.code, //解析接口状态
					"msg": res.msg, //解析提示文本
					"count": res.count, //解析数据长度
					"data": res.data //解析数据列表
				};
			}
		});
		var $ = layui.jquery;
		table.on('tool(test)', function(obj){
			var data = obj.data;
			if(obj.event === 'del'){
				layer.confirm('是否删除', function(index){
					var accounts={RoleId:data.roleId};
					$.ajax({
						type: "post",
						data: accounts,
						url: "/firstdemo/delrole?name=" + accounts,
						dataType: "text",//希望返回的数据类型
						async: true,
						success: function (msg) {// msg为返回数据
							alert(msg);
							if(msg==="删除成功！"){
								alert("删除成功");
								location.reload(); //删除成功后再刷新
							}else{
								alert("删除失败");
							}
						},
						error:function(msg){
							alert("服务器正忙");
						}
					});
					layer.close(index);
				});
			} else if (obj.event === 'edit') {
				var Roled=data['roleId'];
				layer.open({
					type: 1,
					title:"新建界面",
					closeBtn: false,
					shift: 2,
					area: ['400px', '300px'],
					shadeClose: true,
					content: $("#update"),
					success: function(layero, index){
						$("#uproleName").val(data['roleName'])
					},
					yes:function(){
					}
				});
				$("#uproleId").val(Roled);
			}
		});
		form.on('select', function(data){
			console.log(data.elem); //得到select原始DOM对象
			console.log(data.value); //得到被选中的值]
			$("#RoleSelect").val(data.value);
			$("#addRoleSelect").val(data.value);
			$("#upRoleSelect").val(data.value);
			console.log(data.othis); //得到美化后的DOM对象
		});

	});
	//
	// function onAddBtn(){
	// 	if ($("#userNull").val()!=null &&$("#userNull").val().length>0){
	// 		layer.open({
	// 			type: 1,
	// 			title:"新建配置",
	// 			closeBtn: false,
	// 			shift: 2,
	// 			shadeClose: true,
	// 			content: $("#add1-main"),
	// 			success: function(layero, index){},
	// 			yes:function(){
	// 			}
	// 		});
	// 	} else {
	// 		alert("请先添加用户")
	// 	}
	// }
	//
	// $('#layerDemo .layui-btn').on('click', function(){
	// 	var othis = $(this), method = othis.data('method');
	// 	active[method] ? active[method].call(this, othis) : '';
	// });

</script>
	</div>

<script>
	function addDiv() {
		//打开模态框
		layer.open({
			type: 1,
			title:"增加界面",
			closeBtn: false,
			shift: 2,
			area: ['400px', '250px'],
			shadeClose: true,
			content: $("#add"),
			success: function(layero, index){
				$("#addroleName").val("");
				$("#addRoleSelect").val("");
			},
			yes:function(){
			}
		});
	}

	function addMethod() {
		var addroleName = $("#addroleName").val();
		if (addroleName != null && addroleName.length > 0) {
			var ob={roleName:addroleName};
			$.ajax({
				url: "/bus/addRole",
				data: ob,
				type: "Post",
				dataType: "text",
				async: true,
				success: function (msg) {
					if (msg === "添加成功") {
						alert("添加成功");
						location.reload(); //删除成功后再刷新
					} else {
						alert("添加失败");
					}
				},
				error: function (msg) {
					alert("服务器正忙");
				}
			});
		} else  {
			alert("输入的信息不能为空");
		}
	}

	function closeMethod() {
		layer.close(layer.index);
	}

	function updateMethod() {
		var uproleName = $("#uproleName").val();
		var uproleId = $("#uproleId").val();
		if (uproleName != null && uproleName.length > 0 ) {

			var ob={roleName:uproleName};
			$.ajax({
				type: "POST",
				url: "/bus/updateRole",//提交的地址
				data: ob,
				dataType: "text",//希望返回的数据类型
				async: true,//异步操作
				success: function (msg) {//成功的方法  msg为返回数据
					if(msg==="修改成功"){
						alert("修改成功");
						layer.close(layer.index);
						location.reload(); //删除成功后再刷新
					}else{
						alert("修改失败");
					}
				},
				error: function () {//错误的方法
				}
			});
		} else  {
			alert("输入的信息不能为空");
		}
	}
</script>

	<div id="add" style="display: none;">
		<div style="background-color: #ffffff;height: 25px"></div>
		<div class="layui-form-item center">

			<div style="padding: 50px; line-height: 33px; background-color: #393D49; color: #fff; font-weight:800;">
				角色名称:<input type="text" name="addroleName" id="addroleName"><br>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button id="add-btn" class="layui-btn" lay-submit lay-filter="save"
					        onclick="addMethod()">新增
					</button>
					<button type="reset" class="layui-btn layui-btn-primary"
					        onclick="closeMethod()">取消
					</button>
				</div>
			</div>
		</div>
	</div>


	<div id="update" style="display: none;">
		<div style="background-color: #ffffff;height: 50px"></div>
		<div class="layui-form-item center">

			<div class="layui-input-block">
				<input type="text" name="uproleId" id="uproleId" hidden="true" ><br>
				角色名称:<input type="text" name="uproleName" id="uproleName"><br>
			</div>
		</div>

		<div class="layui-form-item">

			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="save" onclick="updateMethod()">确定修改
				</button>
				<button type="reset" class="layui-btn layui-btn-primary" onclick="closeMethod()">取消
				</button>
			</div>

		</div>
	</div>
</body>
</html>
