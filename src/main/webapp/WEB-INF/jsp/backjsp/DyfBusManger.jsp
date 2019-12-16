<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2019/12/16
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page  isELIgnored = "false" %>
<%
	String path = application.getContextPath();
	String laPath = application.getContextPath()+"/layui/";
	String csspath = application.getContextPath()+"/css/";
	String viewPath = application.getContextPath()+"/jsp/";
	String jspath = application.getContextPath()+"/js/";
%>
<html>
<head>
	<title>车辆信息模块</title>
	<link rel="stylesheet" href="<%=laPath%>css/layui.css" media="all">
	<script src="<%=laPath%>layui.js"></script>
	<script src=<%=jspath+"jquery-3.4.1.js"%>></script>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<style type="text/css" >
		.layui-table-page{text-align: center;}

	</style>
</head>
<body>



<table align="center" style="margin-left: 300px;margin-top:20px">
	<tr align="center">
		<td>
			<div class="layui-input-inline">
				<input type="text" id="driverName" name="driverName" required lay-verify="required" placeholder="维护人" autocomplete="off" class="layui-input">
			</div>
		</td>
		<td>
			<div class="layui-form">
				<select  name="driverState" id="driverState" lay-filter="myselect">
					<option value="">请选择车辆状态</option>
					<option value="在用">在用</option>
					<option value="故障待救援">故障待救援</option>
					<option value="故障救援">故障救援</option>
					<option value="维修">维修</option>
					<option value="报废停用">报废停用</option>
				</select>
			</div>
		</td>
		<td>
<%--			使用年数input--%>
			<div class="layui-input-inline">
				<input type="text" id="sendYear" name="sendYear" required lay-verify="required" placeholder="使用年数" autocomplete="off" class="layui-input">
			</div>
		</td>
		<td>
		</td>
	</tr>
	<tr>
		<td>
			<%--			车牌号码input--%>
			<div class="layui-input-inline">
				<input type="text" id="carCard" name="carCard" required lay-verify="required" placeholder="车牌号码" autocomplete="off" class="layui-input">
			</div>
		</td>
		<td>
			<%--			使用年限input--%>
			<div class="layui-input-inline">
				<input type="text" id="driverAge" name="driverAge" required lay-verify="required" placeholder="使用年限" autocomplete="off" class="layui-input">
			</div>
		</td>
		<td style="margin-left: 50px;padding-left: 0px">
			<button type="button" class="layui-btn" onclick="onAddBtn()">
				<i class="layui-icon">&#xe608;</i> 添加
			</button>
			<input  class="layui-btn  layui-btn-primary layui-btn-lg" type="button" value="搜索" onclick="gotoDb()">
		</td>
	</tr>
</table>

<table id="demo" lay-filter="test" style="margin-top: 10px"></table>

<script id="barDemo" type="text/html">
	<a class="layui-btn layui-btn-xs" lay-event="edit" onclick="updateUser()" >排班</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" >修改</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" >删除</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs">报废停用</a>
</script>

<script>
	layui.use('table', function(){
		var table = layui.table;
		//第一个实例
		table.render({
			elem: '#demo'
			,height: 312
			,url: 'http://localhost:8080/bus/DyfBusMangerServlet' //数据接口
			,page: true
			,cols: [[ //表头

				{field: 'busId', title: '序号', align:"center"}
				,{field: 'busLicense', title: '车牌', align:"center"}
				,{field: 'busDutyDriver', title: '维护人', align:"center"}
				,{field: 'busMin', title: '使用时间(年)', align:"center",minWidth:"40px"}
				,{field: 'busAge', title: '使用年限(年)', align:"center"}
				,{field: 'stateName', title: '公交车状态', align:"center"}
				,{fixed: 'right', title:'操作',minWidth:"120px", toolbar: '#barDemo',align:"center"}
			]]
			,skin: 'row' //表格风格
			,even: true
			,limits: [1,5,10]
			,limit: 1 //每页默认显示的数量

		});

	});
	//条件查询ajax刷新
	function gotoDb(){
		var driverState  =$("#driverState").val();
		var sendYear = $("#sendYear").val();
		var carCard = $("#carCard").val();
		var driverAge = $("#driverAge").val();
		var driverName = $("#driverName").val();
		var table = layui.table;
		table.reload('demo', {
			url: 'http://localhost:8080/bus/DyfBusMangerServlet'
			,where: {
				"driverSta":driverState,
				"sendYear":sendYear,
				"carCard":carCard,
				"driverAge":driverAge,
				"driverName":driverName
			}
			,page:{
				curr:1
			}//设定异步数据接口的额外参数
			//,height: 300
		});

	}
	//add-main 添加弹窗显示
	function onAddBtn(){
		//页面层-自定义
		layer.open({
			type: 1,
			title:"新建配置",
			closeBtn: false,
			shift: 2,
			area: ['500px', '600px'],
			shadeClose: true,
			// btn: ['新增', '取消'],
			// btnAlign: 'c',
			content: $("#add1-main"),
			success: function(layero, index){},
			yes:function(){
			}
		});
	}
</script>

<%--添加按钮弹窗--%>
<div id="add1-main" style="display: none;" align="center">
	<h2 style="color: #2D93CA;margin-top: 50px">添加公交车数据</h2>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="text" name="value" id="cardProvince" required style="width: 240px;margin-top: 30px;margin-left: -60px" lay-verify="required" placeholder="公交车车牌" autocomplete="off" class="layui-input">
			<input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="text" name="value" id="driverNam" required style="width: 240px;margin-left: -60px" lay-verify="required" placeholder="维护人" autocomplete="off" class="layui-input">
			<input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="text" name="busAge" id="busAge" required style="width: 240px;margin-left: -60px" lay-verify="required" placeholder="使用时间" autocomplete="off" class="layui-input">
			<input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<input type="text" name="busYear" id="busYear" required style="width: 240px;margin-left: -60px" lay-verify="required" placeholder="使用年限" autocomplete="off" class="layui-input">
			<input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form" >
		<div class="layui-input-block">
		<select  name="driverState" id="busState" lay-filter="required" style="width: 100px;margin-left: -60px">
			<option value="">请选择车辆类型</option>
			<option value="新能源">新能源</option>
			<option value="燃油">燃油</option>
		</select>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block" style="margin-left: -40px;margin-top: 70px">
			<button class="layui-btn" lay-submit lay-filter="save" >立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary" id="closeSome" >重置</button>
		</div>
	</div>
</div>
</body>
</html>

