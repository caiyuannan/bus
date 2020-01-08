<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/23
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String layuiPath = request.getContextPath()+"/layui/";
	String jsPath = request.getContextPath()+"/js/";
	String imgPath = request.getContextPath()+"/img/";
%>
<html>
<head>
	<title>意见处理</title>
	<link rel="stylesheet" href="/bus/layui/css/layui.css">
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src="/bus/layui/layui.js"></script>
</head>
<body>

<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
	layui.use('table', function(){
		var table = layui.table;

		//方法级渲染
		table.render({
			elem: '#LAY_table_user'
			,url: '/bus/toDriverWage'
			,limits:[5,10,20,30,40,50]
			,limit: 5
			,cols: [[
				//{checkbox: true, fixed: true}
				{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
				// ,{field: 'cityName', title: '', width:100}
				,{field: 'driverName', title: '用户名', width:80}
				,{field: 'driverWage', title: '投诉内容', width:80}
				,{field: 'driverCellphone', title: '电话', width:80}
				,{field: 'driverId', title: '处理状态', hide:true}
			]]
			,id: 'testReload'
			,page: true
			,height: 310
			// ,where:{
			// 	driverID:$("#dvid").val()
			// }
		});

		var $ = layui.$, active = {
			reload: function(){
				var demoReload = $('#demoReload');

				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					,where: {
						key: {
							date1: demoReload.val()
							//driverId: dvid.val()
						}
					}
				}, 'data');
			}
		};

	});

</script>

</body>
</html>
