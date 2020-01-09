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
				, {field: 'logInfId', title: 'ID', align: 'center'}
				, {field: 'actionName', title: '操作人', align: 'center'}
				, {field: 'actionTime', title: '操作事件', align: 'center'}
				, {field: 'actionInf', title: '操作事件', align: 'center'}
				, {field: 'money', title: '金额', align: 'center'}
				, {fixed: 'right', width: 200, align: 'center', toolbar: '#barDemo'}
			]]
			, page: true
			, parseData: function (res) { //res 即为原始返回的数据
				return {
					"code": res.code, //解析接口状态
					"msg": res.msg, //解析提示文本
					"count": res.count, //解析数据长度
					"data": res.data //解析数据列表
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
					var logId=data['logInfId'];
					$.ajax({
						type: "POST",
						url: "/bus/delLog?logId=" + logId,
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
			url: '/bus/mangelog'
			, where: {
				'username': name
			}
			, page: {
				curr: 1
			}
		});
	}
</script>
</body>
</html>