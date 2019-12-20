<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2019/12/20
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
	String path = application.getContextPath();
	String laPath = application.getContextPath()+"/layui/";
	String csspath = application.getContextPath()+"/css/";
	String viewPath = application.getContextPath()+"/jsp/";
	String jspath = application.getContextPath()+"/js/";
%>
<html>
<head>
	<title>车辆排班表格</title>
	<link rel="stylesheet" href="<%=laPath+"css/layui.css"%>">
	<script src="<%=laPath+"layui.js"%>"></script>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<script src=<%=jspath+"jquery-3.4.1.js"%>></script>
</head>
<body>
<form class="layui-form" action="">
<div class="layui-form-item">
	<label class="layui-form-label">行车线路:</label>
	<div class="layui-input-block" style="width: 120px">
		<select name="driverState" id="beforeBusState" lay-filter="required">
			<option value="0">请选择线路：</option>
			<c:if test="${requestScope.tbRoute !=null}">
				<c:forEach items="${requestScope.tbRoute}" begin="0" step="1" var="item">
					<option value="${item.routeName}" name="${item.routeId}">${item.routeName}</option>
				</c:forEach>
			</c:if>
		</select>
	</div>
</div>

	<table id="demo" lay-filter="test" style="margin-top: 10px"></table>
	<script id="barDemo" type="text/html">
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="check1">排班</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="check1">替换排班</a>
	</script>

</form>
<script>

	layui.use('table', function () {
		var table = layui.table;
//第一个实例
		table.render({
			elem: '#demo'
			, url: 'http://localhost:8080/bus/busShfitAllInforMation' //数据接口
			, page: true
			, cols: [[ //表头
				{field: 'shfitId', title: '序号', align: "center"}
				, {field: 'busLicense', title: '时间', align: "center"}
				, {field: 'busDutyDriver', title: '发车', align: "center"}
				, {fixed: 'right', title: '操作', minWidth: "20px", toolbar: '#barDemo', align: "center"}

			]]
			, skin: 'sm' //表格风格
			, even: true
			, limits: [1, 5, 10]
			, limit: 1 //每页默认显示的数量

		})
	});

	//Demo
	layui.use('form', function(){
		var form = layui.form;

		//监听提交
		form.on('submit(formDemo)', function(data){
			layer.msg(JSON.stringify(data.field));
			return false;
		});
	});
</script>

</body>
</html>
