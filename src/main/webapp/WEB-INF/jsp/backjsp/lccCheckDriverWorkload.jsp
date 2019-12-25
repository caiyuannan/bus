<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/11/25
  Time: 15:25
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
	<title>Title</title>
	<link rel="stylesheet" href="/bus/layui/css/layui.css">
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src="/bus/layui/layui.js"></script>

</head>
<body>
<div class="demoTable">
<%--		<input class="layui-input" name="id" id="demoReload" autocomplete="off">--%>
	<div class="layui-inline">
		<label class="layui-form-label">日期范围</label>
		<div class="layui-input-inline">
			<input type="text" class="layui-input" name="date1" id="demoReload" placeholder=" - ">
		</div>
	</div>

	<button class="layui-btn" data-type="reload">搜索</button>
</div>

<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>
<input type="hidden" name="driverID" value="${driverId}" id="dvid"/>



<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
	layui.use('table', function(){
		var table = layui.table;

		//方法级渲染
		table.render({
			elem: '#LAY_table_user'
			,url: '/bus/toDriverWorkload?driverID='+${driverId}
			,limits:[5,10,20,30,40,50]
			,limit: 5
			,cols: [[
				//{checkbox: true, fixed: true}
			// {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
			,{field: 'workloadId', title: '序号', width:100}
			,{field: 'busLicense', title: '车牌', width:80}
			,{field: 'routeName', title: '线路', width:80}
			,{field: 'workDate', title: '日期', width:80}
			,{field: 'officeHours', title: '时间', width:80}
			//,{field: 'driverId', title: '司机ID', hide:true}
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
					//alert(demoReload.val()+"7777");
				//执行重载
				table.reload('testReload', {
					page: {
						curr: 1 //重新从第 1 页开始
					}
					,where:{
						//'token': token,
						workDate: demoReload.val()
					}
				}, 'data');
			}
		};

		$('.demoTable .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});

	//日期范围
	layui.use('laydate', function(){
		var laydate = layui.laydate;
	laydate.render({
		elem: '#demoReload'
		,range: true
	});
});
</script>

</body>
</html>
