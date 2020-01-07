<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2020/1/5
  Time: 23:39
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
	<title>用户消费明细</title>
	<link rel="stylesheet" href="<%=laPath+"css/layui.css"%>">
	<script src="<%=laPath+"layui.js"%>"></script>
	<script src=<%=jspath + "jquery-3.4.1.js"%>></script>
</head>
<body>
<table align="center" style="margin-top:70px">
	<tr>
		<td>
			<%--			车牌号码input--%>
			<div class="layui-form-item">
				<div class="layui-input-inline">
					<label class="layui-form-label">*操作时间:</label>
					<div class="layui-input-block" style="width: 120px">
						<input type="text" class="layui-input" id="moneyDate">
					</div>
				</div>
			</div>
		</td>

	</tr>
</table>
<table id="demo" lay-filter="test"></table>

<script type="text/html" id="indexTpl">
	{{d.LAY_TABLE_INDEX+1}}
</script>

<script>
	layui.use('table', function () {
		var table = layui.table;
//第一个实例
		table.render({
			elem: '#demo'
			, height: 312
			, url: 'http://localhost:8080/bus/findSaveMoney' //数据接口
			, page: true
			, cols: [[ //表头
				{  title: '序号', width: 180 ,toolbar: '#indexTpl'}
				,{field: 'nowDate', title: '操作时间', align: "center"}
				, {field: 'orderMoney', title: '金额数值', align: "center"}
				, {field: 'orderType', title: '操作类型/1(充值)/2(乘车消费)', align: "center"}

			]]
			, skin: 'row' //表格风格
			, even: true
			, limits: [1, 5, 10]
			, limit: 1 //每页默认显示的数量

		});
		layui.use('laydate', function(){
			var laydate = layui.laydate;
			//执行一个laydate实例
			laydate.render({
				elem: '#moneyDate' //指定元素
				,done: function(value, date, endDate){
					//表格重载
					table.reload('demo', {
						url: 'http://localhost:8080/bus/findSaveMoney'
						,where: {
							value:value
						} //设定异步数据接口的额外参数
						//,height: 300
					});
				}
			});
		});


	});



</script>
</body>
</html>
