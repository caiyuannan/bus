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
	<meta http-equiv="Content-Type" content="text/h tml; charset=UTF-8"/>
	<title>卡片管理模块</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="<%=path+"/layui/css/layui.css"%>" media="all">
	<script src=<%=path + "/layui/layui.js"%>></script>
	<script type="text/javascript" src=<%=jspath + "jquery-3.4.1.js"%>></script>
</head>
<body>
<div style="width: 80%;margin: auto;padding-top: 50px">
	<div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">卡状态</label>
				<div class="layui-input-inline">
					<select id="cardSelectState" lay-verify="required" name="PaymentModeID" class="SelectPaymentMode">
						<option value="">请选择</option>
						<option value="未使用">未使用</option>
						<option value="已使用">已使用</option>
						<option value="已停用">已停用</option>
						<option value="已删除">已删除</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="site-demo-button" id="layerDemo" style="margin-left: 10px;">
		<button class="layui-btn layui-btn-radius layui-btn-normal" id="searchId" onclick="searchBtn()">搜索</button>
		<button data-method="notice" class="layui-btn layui-btn-radius layui-btn-normal" onclick="onAddBtn()">新建
		</button>
	</div>
	<table class="layui-hide" id="test" lay-filter="test"></table>
</div>

<%--<script type="text/html" id="toolbarDemo">--%>
<%--	<div class="layui-btn-container">--%>
<%--		<button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>--%>
<%--		<button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>--%>
<%--		<button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>--%>
<%--	</div>--%>
<%--</script>--%>
<input type="hidden" id="oldId" name="old-Id">
<input type="hidden" id="oldCardNum" name="old-Card-Num">
<input type="hidden" id="oldState" name="old-State">
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
			, url: '/bus/mangecardshow'
			, toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
			, defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				title: '提示'
				, layEvent: 'LAYTABLE_TIPS'
				, icon: 'layui-icon-tips'
			}]
			, title: '卡片数据表'
			, cols: [[
				{checkbox: true, fixed: true}
				, {field: 'cardId', title: 'ID', align: 'center'}
				, {field: 'cardNum', title: '卡号', align: 'center'}
				, {field: 'stateName', title: '状态', align: 'center'}
				, {field: 'userName', title: '使用人', align: 'center'}
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
			}
			;
		});

		//监听行工具事件
		table.on('tool(test)', function (obj) {
			var data = obj.data;
			if (obj.event === 'del') {
				layer.confirm('确定删除吗？', function (index) {
					var cardId = data['cardId'];
					$.ajax({
						type: "POST",
						url: "/bus/delCard?id=" + cardId,
						dataType: "text",//希望返回的数据类型
						async: true,//异步操作
						success: function (msg) {//成功的方法  msg为返回数据
							alert(msg);
							if (msg === "删除成功！") {
								obj.del();
								layer.close(layer.index);
								tableReload();
							}
						},
						error: function () {//错误的方法
						}
					});
				});
			} else if (obj.event === 'edit') {
				var id = data['cardId'];
				var cardNum = data['cardNum'];
				var stateName = data['stateName'];
				var userName = data['userName'];
				//页面层-自定义
				layer.open({
					type: 1,
					title: "新建界面",
					closeBtn: false,
					shift: 2,
					area: ['400px', '300px'],
					shadeClose: true,
					content: $("#update-main"),
					success: function (layero, index) {
					},
					yes: function () {
					}
				});
				$("#oldId").val(id);
				$("#updateId").html(id);

				$("#oldCardNum").val(cardNum);
				$("#updateCardNum").html(cardNum);

				$("#oldState").val(stateName);
				$('#selectState').val(stateName);

				$("#oldName").val(userName);
				$("#usernameUp").html(userName);
			}
		});
	});

	//增加按钮
	function onAddBtn() {
		//页面层-自定义
		layer.open({
			type: 1,
			title: "新建界面",
			closeBtn: false,
			shift: 2,
			area: ['400px', '300px'],
			shadeClose: true,
			content: $("#add-main"),
			success: function (layero, index) {
			},
			yes: function () {
			}
		});
	}

	//增加方法
	function addMethod() {
		var num = $("#usernameInp").val();
		$.ajax({
			type: "POST",
			url: "/bus/addCard?num=" + num,
			dataType: "text",//希望返回的数据类型
			async: true,//异步操作
			success: function (msg) {//成功的方法  msg为返回数据
				alert(msg);
				layer.closeAll();
				tableReload();
			},
			error: function () {//错误的方法
			}
		});

	}

	//搜索方法
	function searchBtn() {
		tableReload();
	}

	//表格重载
	function tableReload() {
		var stateName = $('#cardSelectState option:selected').val();
		var table = layui.table;
		table.reload('test', {
			url: '/bus/mangecardshow'
			, where: {
				'stateName': stateName
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
		var oldStateName = $("#oldState").val();
		var newStateName = $("#selectState").val();
		var id = $("#oldId").val();
		// if(oldStateName===newStateName){
		// 	alert("两次状态一致，请重新选择噢！");
		// }else{}
		$.ajax({
			type: "POST",
			url: "/bus/upCard?newStateName=" + newStateName + "&id=" + id,
			dataType: "text",//希望返回的数据类型
			async: true,//异步操作
			success: function (msg) {//成功的方法  msg为返回数据
				alert(msg);
				if (msg === "success") {
					layer.closeAll();
					tableReload();
				}
			},
			error: function () {//错误的方法
			}
		});

	}
</script>
<div id="add-main" style="display: none;">
	<div style="background-color: #ffffff;height: 30px"></div>
	<div class="layui-form-item center">
		<label class="layui-form-label" style="width: 100px">数量:</label>
		<div class="layui-input-block">
			<input type="text" name="userName" required value="" style="width: 240px" lay-verify="required"
			       placeholder="请输入增加数量" autocomplete="off" class="layui-input"
			       id="usernameInp">
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="save"
			        onclick="addMethod()">立即提交
			</button>
			<button type="reset" class="layui-btn layui-btn-primary"
			        onclick="closeMethod()">取消
			</button>
		</div>
	</div>
</div>
<div id="update-main" style="display: none;">
	<div style="background-color: #ffffff;height: 30px"></div>
	<div class="layui-form-item center">
		<label class="layui-form-label" style="width: 100px">ID: </label>
		<label class="layui-form-label" style="width: 100px" id="updateId"></label>
	</div>
	<div class="layui-form-item center">
		<label class="layui-form-label" style="width: 100px">卡号: </label>
		<label class="layui-form-label" style="width: 100px" id="updateCardNum"></label>
	</div>
	<div class="layui-form-item center">
		<label class="layui-form-label" style="margin-left: 15px;">卡状态: </label>
		<select id="selectState" lay-verify="required" name="PaymentModeID" class="SelectPaymentMode"
		        style="margin-left: 20px;">
			<option value="未使用">未使用</option>
			<option value="已使用">已使用</option>
			<option value="已停用">已停用</option>
			<option value="已删除">已删除</option>
		</select>

	</div>
	<div class="layui-form-item center">
		<label class="layui-form-label" style="width: 100px">使用人: </label>
		<label class="layui-form-label" style="width: 100px" id="usernameUp"></label>
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
