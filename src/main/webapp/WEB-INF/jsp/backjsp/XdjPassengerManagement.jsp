<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/17
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String csspath = request.getContextPath() + "/css/";
	String jspath = request.getContextPath() + "/js/";
	String jsppath = request.getContextPath() + "/jsp/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>乘客账号管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet"  href="<%=path+"/layui/css/layui.css"%>" media="all">
	<script  src="<%=path + "/layui/layui.js"%>"></script>
	<script  type="<%=jspath+"jquery-3.4.1.js"%>"></script>
</head>
<body>
<table align="center"  class="layui-hide">
	<div class="layui-input-inline">
		用户名：
		<div class="layui-inline">
			<input class="layui-input" name="userName" id="userName" autocomplete="off">
		</div>
		手机号：
		<div class="layui-inline">
			<input class="layui-input" name="userPhonenumber" id="demoReload" autocomplete="off">
		</div>
		<%--		<button class="layui-btn layui-btn-sm" data-type="reload">搜索</button>--%>
		<button data-method="setTop" class="layui-btn">搜索</button>
	</div>
</table>
<%--<table class="layui-hide" id="test" lay-filter="test"></table>--%>
<%--<input type="hidden" id="oldName" name="old-Name">--%>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">查看</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del1">增加</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del3">修改</a>
</script>
<table id="text" lay-filter="text"  class="layui-hide"></table>
<%--<script src="<%=path + "/layui/layui.js"%>" charset="utf-8"></script>--%>
<script>
	layui.use('table', function() {
		var table = layui.table;
		table.render({
			elem: '#text'
			,url:'http://localhost:8080/bus/XdjPassengerManagementServlet'
			,defaultToolbar: ['filter', 'exports', 'print', {
				title: '提示'
				,layEvent: 'LAYTABLE_TIPS'
				,icon: 'layui-icon-tips'
			}]
			,title: '乘客用户表'
			,cols: [[
				{field:'userId', title:'ID',width:100}
				,{field:'userPhonenumber', title:'手机号',width:150, templet:
						function(res){return '<em>'+ res.userPhonenumber +'</em>'}}
				,{field:'userName', title:'昵称', width:100}
				,{field:'userHome', title:'家',width:150}
				,{field:'userCompany', title:'公司',width:150}
				,{field:'userBalance', title:'账户余额',width:100}
				,{fixed: 'right', title:'操作', toolbar: '#barDemo',width:250}
			]]
			,page: true
			,parseData: function (res) { //res 即为原始返回的数据
				return {
					"code": res.code, //解析接口状态
					"msg": res.msg, //解析提示文本
					"count": res.count, //解析数据长度
					"data": res.data //解析数据列表
				};
			}
		});
		table.on('tool(test)', function(obj){
			var data = obj.data;
			if(obj.event === 'del'){
				layer.confirm('是否删除', function(index){
					var account=data['account'];
					$.ajax({
						type: "POST",
						url: "/firstdemo/delUser?name=" + account,
						dataType: "text",//希望返回的数据类型
						async: true,
						success: function (msg) {// msg为返回数据
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
			} else if (obj.event === 'edit') {
				var oldAccount=data['account'];
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
				$("#usernameUp").val(oldAccount);
				$("#oldName").val(oldAccount);
			}
		});
	});

	function tableReload() {
		var name = $('#userName').val();
		var table = layui.table;
		table.reload('test', {
			url: 'http://localhost:8080/bus/XdjPassengerManagementServlet'
			, where: {
				'userName': name
			}
			, page: {
				curr: 1
			}
		});
	}

	function onAddBtn(){
		if ($("#userNull").val()!=null &&$("#userNull").val().length>0){
			layer.open({
				type: 1,
				title:"新建配置",
				closeBtn: false,
				shift: 2,
				shadeClose: true,
				content: $("#add1-main"),
				success: function(layero, index){},
				yes:function(){
				}
			});
		} else {
			alert("请先添加用户")
		}
	}

	$('#layerDemo .layui-btn').on('click', function(){
		var othis = $(this), method = othis.data('method');
		active[method] ? active[method].call(this, othis) : '';
	});

</script>

</body>
</html>
