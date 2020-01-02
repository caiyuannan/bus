<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String csspath = request.getContextPath() + "/css/";
	String jspath = request.getContextPath() + "/js/";
	String jsppath = request.getContextPath() + "/jsp/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/h tml; charset=UTF-8" />
	<title>日志管理界面</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="<%=path+"/layui/css/layui.css"%>" media="all">
	<script src=<%=path + "/layui/layui.js"%>></script>
	<script type="text/javascript" src=<%=jspath + "jquery-3.4.1.js"%>></script>
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<div style="width: 80%;margin: auto;padding-top: 50px">
	<div>
		<label>操作人&nbsp;&nbsp;</label>
		<input type="text" id="name" name="username">

		<div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
			<button class="layui-btn layui-btn-radius layui-btn-normal" id="searchId" onclick="searchBtn()">搜索</button>
<%--			<button data-method="notice" class="layui-btn layui-btn-radius layui-btn-normal" onclick="onAddBtn()">新建</button>--%>
		</div>
	</div>
	<table class="layui-hide" id="test" lay-filter="test"></table>
</div>

<input type="hidden" id="oldName" name="old-Name">
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
	layui.use('table', function () {
		var table = layui.table;

		table.render({
			elem: '#test'
			, url: '/bus/mangelog'
			, toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
			, defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				title: '提示'
				, layEvent: 'LAYTABLE_TIPS'
				, icon: 'layui-icon-tips'
			}]
			, title: '日志数据表'
			, cols: [[
				{checkbox: true, fixed: true}
				, {field: 'account', title: '用户名', align: 'center'}
				, {field: 'nowfen', title: '积分', align: 'center'}
				, {field: 'upnum', title: '上传文档数', align: 'center'}
				, {field: 'downnum', title: '下载文档数', align: 'center'}
				, {field: 'statename', title: '状态', align: 'center'}
				, {fixed: 'right', width: 200, align: 'center', toolbar: '#barDemo'}
			]]
			, page: true
			, parseData: function (res) { //res 即为原始返回的数据
				return {
					"code": res.code, //解析接口状态
					"msg": res.msg, //解析提示文本
					"count": res.count, //解析数据长度
					"data": res.date //解析数据列表
				};
			}
		});
		//头工具栏事件
		table.on('toolbar(test)', function (obj) {
			var checkStatus = table.checkStatus(obj.config.id);
			switch (obj.event) {
				case 'getCheckData':
					var data = checkStatus.data;
					layer.alert(JSON.stringify(data));
					break;
				case 'getCheckLength':
					var data = checkStatus.data;
					layer.msg('选中了：' + data.length + ' 个');
					break;
				case 'isAll':
					layer.msg(checkStatus.isAll ? '全选' : '未全选');
					break;

				//自定义头工具栏右侧图标 - 提示
				case 'LAYTABLE_TIPS':
					layer.alert('这是工具栏右侧自定义的一个图标按钮');
					break;
			};
		});

		//监听行工具事件
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			if (obj.event === 'del') {
				layer.confirm('确定删除吗？', function (index) {
					var account=data['account'];
					$.ajax({
						type: "POST",
						url: "/firstdemo/delUser?name=" + account,
						dataType: "text",//希望返回的数据类型
						async: true,//异步操作
						success: function (msg) {//成功的方法  msg为返回数据
							alert(msg);
							if(msg==="删除成功！"){
								obj.del();
								layer.close(layer.index);
								tableReload();
							}
						},
						error: function () {//错误的方法
						}
					});
				});
			}
		});
	});

	//搜索方法
	function searchBtn(){
		tableReload();
	}
	//表格重载
	function tableReload() {
		var name = $('#name').val();
		var table = layui.table;
		table.reload('test', {
			url: '/bus/mangeuser'
			, where: {
				'username': name
			}
			, page: {
				curr: 1
			}
		});
	}
	//取消方法
	function closeMethod() {
		layer.close(layer.index);
		tableReload();
	}
	//修改的方法
	function updateMethod() {
		var oldName=$("#oldName").val();
		var upName=$("#usernameUp").val();
		if(oldName===upName){
			alert("两次用户名一致，请重新输入！");
		}else{
			$.ajax({
				type: "POST",
				url: "/firstdemo/upUser?oldName=" + oldName + "&updateName=" + upName,
				dataType: "text",//希望返回的数据类型
				async: true,//异步操作
				success: function (msg) {//成功的方法  msg为返回数据
					alert(msg);
					if(msg==="修改成功！"){
						layer.close(layer.index);
						tableReload();
					}
				},
				error: function () {//错误的方法
				}
			});
		}
	}
</script>
<div id="update-main" style="display: none;">
	<div style="background-color: #ffffff;height: 50px"></div>
	<div class="layui-form-item center">
		<label class="layui-form-label" style="width: 100px">用户名 :</label>
		<div class="layui-input-block">
			<input type="text" name="userName" required value="" style="width:240px" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input"
			       id="usernameUp">
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="save"
			        onclick="updateMethod()">确定修改
			</button>
			<button type="reset" class="layui-btn layui-btn-primary"
			        onclick="closeMethod()">取消
			</button>
		</div>
	</div>
</div>
</body>
</html>