<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/26
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String csspath = request.getContextPath() + "/css/";
	String jspath = request.getContextPath() + "/js/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>系统帐号管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet"  href="<%=path+"/layui/css/layui.css"%>" media="all">
	<script  src="<%=path + "/layui/layui.js"%>"></script>
	<script  type="<%=jspath+"jquery-3.4.1.js"%>"></script>
</head>
<body>

<div>
	<form class="layui-form layui-col-md12 x-so" id="mangeuser_name" style=" margin-top: 6%;">
		<label class="layui-form-label" style="margin-left: 8%">用户名：</label>
		<div class="layui-input-inline">
			<input id="mangeuserName" type="text" name="mangeuserName" class="layui-input" id="test1" >
		</div>
		<label>角色：</label>
		<div class="layui-input-inline" >
			<select id="rolename" name="rolename"  style="margin-left: 3%;">
				<option value="">搜索角色</option>
				<option value="1">调度员</option>
				<option value="2">经理</option>
				<option value="3">司机</option>
			</select>
		</div>
		<button class="layui-btn layui-btn-sm" data-type="reload">搜索</button>
		<button class="layui-btn layui-btn-sm" data-type="reload1">新增</button>
	</form>
</div>

<div style=" padding-top: 10%">
	<table id="demo" lay-filter="text" class="layui-hide" style=" padding-top: 10%"></table>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">重置密码</a>
	<a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
	<a class="layui-btn  layui-btn-xs" id="state" lay-event="state">启用</a>
	<a class="layui-btn  layui-btn-xs" id="state2" lay-event="state2">禁用</a>
</script>
<script>
	layui.use(['table','layer','form','laypage','element'], function(){
		var table = layui.table,
			layer = layui.layer,
			form = layui.form,
			laypage = layui.laypage;
		table.render({
			elem: '#demo'
			, url: 'http://localhost:8080/bus/XdjSystemManagementServlet'
			, defaultToolbar: ['filter', 'exports', 'print', {
				title: '提示'
				, layEvent: 'LAYTABLE_TIPS'
				, icon: 'layui-icon-tips'
			}]
			,page: true //开启分页
			,limit: 3 //开启分页
			,limits: [1,5,10] //开启分页
			,id: 'idTest'
			, title: '系统帐号管理表'
			, cols: [[
				{field: 'mangeuserId', title: '序号', width: 100}
				, {field: 'mangeuserName', title: '用户名', width: 150}
				, {field: 'password', title: '密码', width: 100}
				, {field: 'mangeuserPhonenumber', title: '电话', width: 150}
				, {field: 'roleName', title: '角色', width: 150}
				, {field: 'mangeuserCity', title: '所属地区', width: 100}
				, {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 250}
			]]
			, parseData: function (res) { //res 即为原始返回的数据
				return {
					"code": res.code,//解析接口状态
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
					var ob={mangeuserId:data.mangeuserId};
					$.ajax({
						type: "post",
						url: '/bus/deleteXdjSystemManagementServlet',
						data: ob,
						dataType: "text",//希望返回的数据类型
						async: true,
						success: function (msg) {// msg为返回数据
							alert(msg);
							if(msg==="删除成功！"){
								obj.del();
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
			}
			else if (obj.event === 'edit') {
				var mangeuserId=data['mangeuserId'];
				layer.open({
					type: 1,
					title:"新建界面",
					closeBtn: false,
					shift: 2,
					shadeClose: true,
					content: $("#update-main"),
					success: function(layero, index){},
					yes:function(){
					}
				});
				$("#uppassword").val(password);
			}
			else if(obj.event === 'state'){
				if (data['right']==="启用") {
					$("#state").disabled();
				}else{
					layer.confirm('真的要启用吗', function(index){
						$.ajax({
							url:"/bus/updateXdjSystemManagementServlet?stateName=启用"+"&mangeuserId="+data['mangeuserId'],
							type:"Post",
							dataType:"text",
							async: true,
							success: function (msg) {
								if(msg==="修改成功1"){
									obj.del();
									alert("修改成功");
									location.reload(); //删除成功后再刷新
								}else{
									alert("修改失败");
								}
							},
							error:function(msg){
								alert("服务器正忙");
							}
						});
						layer.close(index);
					});
				}
			}

			else if(obj.event === 'state2') {

				if (data['right'] === "禁用") {
					$("#state").disabled();
				} else {

					layer.confirm('真的要禁用吗', function (index) {
						$.ajax({
							url: "/bus/updateXdjSystemManagementServlet?stateName=禁用" + "&mangeuserId="+data['mangeuserId'],
							type: "Post",
							dataType: "text",
							async: true,

							success: function (msg) {
								// alert(msg);
								if (msg === "修改成功1") {
									obj.del();
									alert("修改成功");
									location.reload();
								} else {
									alert("修改失败");
								}
							},
							error: function (msg) {
								alert("服务器正忙");
							}
						});
						layer.close(index);
					});
				}
			}

		});
		form.on('select', function(data){
			console.log(data.elem); //得到select原始DOM对象
			console.log(data.value); //得到被选中的值]
			$("#rolename").val(data.value);
			console.log(data.othis); //得到美化后的DOM对象
		});
		form.on('submit(provinceSearch)', function(data){
			var formData = data.field;
			console.debug(formData);
			var rolename=formData.rolename,
				rolename = $("#rolename").val(),
				mangeuserName=formData.mangeuserName;
			table.reload('idTest', {
				page: {
					curr: 1
				},
				where: {
					rolename:rolename,
					mangeuserName:mangeuserName,
				},
			});
			return false;
		});
	});
</script>
</div>

<script>
	layui.use('layer', function(){ //独立版的layer无需执行这一句
		var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
		var active = {
			setTop: function(){
				var that = this;
			}
			,notice: function(){
				//示范一个公告层
				layer.open({
					type: 1
					,title: false //不显示标题栏
					,closeBtn: false
					,area: ['370px', '250px']
					,shade: 0.8
					,id: 'LAY_layuipro' //设定一个id，防止重复弹出
					,btn1:function addUser() {
						// alert(1);
						var addpassword = $("#addpassword").val();
						if (addpassword != null && addpassword.length > 0 ) {
							var ob={addpassword:addpassword};
							$.ajax({
								url: 'bus/addXdjSystemManagementServlet',
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
					,btnAlign: 'c'
					,moveType: 1 //拖拽模式，0或者1
					,content: '<div style="padding: 50px; line-height: 33px; background-color: #393D49; color: #fff; font-weight:800;">' +
						'密码：<input type="text" name="addpassword" id="addpassword"><br>'+
						'</div>'
					,success: function(layero){
					}
				});
			}

		};

		$('#layerDemo .layui-btn').on('click', function(){
			var othis = $(this), method = othis.data('method');
			active[method] ? active[method].call(this, othis) : '';
		});
	});


	//关闭模态框
	function closeMethod() {
		layer.close(layer.index);
		tableReload();
	}
	//修改的方法
	function updateMethod() {
		var addpassword = $("#addpassworde").val();

		var ob={addpassword:addpassword};

		$.ajax({
			type: "POST",
			url: "/bus/updateXdjSystemManagementServlet",//提交的地址
			data: ob,
			dataType: "text",//希望返回的数据类型
			async: true,//异步操作
			success: function (msg) {//成功的方法  msg为返回数据
				if(msg==="修改成功"){
					layer.close(layer.index);
					location.reload(); //删除成功后再刷新
				}else{
					alert("修改失败");
				}
			},
			error: function () {//错误的方法
			}
		});
	}
</script>

<div id="update" style="display: none;">
	<div style="background-color: #ffffff;height: 50px"></div>
	<div class="layui-form-item center">

		<div class="layui-input-block">
			<input type="text" name="upmangeuserId" id="upmangeuserId" hidden="true" ><br>
			密码：<input type="text" name="upaddpassword" id="upaddpassword"><br>
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
