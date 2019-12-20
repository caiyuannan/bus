<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2019/12/18
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath();
	String laPath = application.getContextPath() + "/layui/";
	String csspath = application.getContextPath() + "/css/";
	String viewPath = application.getContextPath() + "/jsp/";
	String jspath = application.getContextPath() + "/js/";
%>
<html>
<head>
	<title>车辆维修记录</title>
	<link rel="stylesheet" href="<%=laPath%>css/layui.css" media="all">
	<script src="<%=laPath%>layui.js"></script>
	<script src=<%=jspath + "jquery-3.4.1.js"%>></script>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<style type="text/css">
		.layui-table-page {
			text-align: center;
		}
	</style>
</head>
<body>
<table align="center" style="margin-left: 57px;margin-top:100px">
	<tr align="center">
		<td>
			<div class="layui-form">
			<label class="layui-form-label" style="width: 140px"><i class="asterisk">*</i>车辆新状态:</label>
			<div class="layui-input-inline">
				<input type="text" id="licenseId" name="driverName" required lay-verify="required" placeholder="车牌号"
				       autocomplete="off" class="layui-input">
			</div>
				<div class="layui-input-inline">
					<button class="layui-btn" onclick="selectProvince()">*搜索</button>
				</div>
			</div>

		</td>
	</tr>
</table>
<table id="demo" lay-filter="test" style="margin-top: 10px"></table>
<script>
	layui.use('table', function () {
		var table = layui.table;
//第一个实例
		table.render({
			elem: '#demo'
			, height: 312
			, url: 'http://localhost:8080/bus/ajaxRepairBusTable' //数据接口
			, page: true
			, cols: [[ //表头
				{field: 'repairId', title: '序号', align: "center"}
				, {field: 'busLicense', title: '车牌', align: "center"}
				, {field: 'busDutyDriver', title: '维护司机', align: "center"}
				, {field: 'repairStart', title: '送修日期', align: "center"}
				, {field: 'repairStartTime', title: '送修时间', align: "center"}
				, {field: 'repairFinish', title: '返修日期', align: "center"}
				, {field: 'repairFinishTime', title: '返修时间', align: "center"}
				, {field: 'stateName', title: '公交车状态', align: "center"}
			]]
			, skin: 'row' //表格风格
			, even: true
			, limits: [1, 5, 10]
			, limit: 1 //每页默认显示的数量

		})
	});
	
	function selectProvince() {
		var table = layui.table;
		var license = $("#licenseId").val();
		var recodePage = $(".layui-laypage-skip .layui-input").val();
		console.log(license,recodePage)
		if (license!=null&&license.length>0){
			table.reload('demo', {
				url: '/bus/ajaxRepairBusTable'
				,where: {
					license :license,
					page:recodePage
				} //设定异步数据接口的额外参数
				//,height: 300
			});
		}else {
			table.reload('demo', {
				url: '/bus/ajaxRepairBusTable'
				,where: {
				} //设定异步数据接口的额外参数
				//,height: 300
			});
		}
		
	}
</script>
</body>
</html>
