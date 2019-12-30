<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String csspath = request.getContextPath() + "/css/";
	String jspath = request.getContextPath() + "/js/";
	String jsppath = request.getContextPath() + "/jsp/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/h tml; charset=UTF-8"/>
	<title>权限管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="<%=path+"/layui/css/layui.css"%>" media="all">
	<script src=<%=path + "/layui/layui.js"%>></script>
	<script type="text/javascript" src=<%=jspath + "jquery-3.4.1.js"%>></script>
</head>
<body>
<%--<div class="layui-btn-container">--%>
<%--	<button type="button" class="layui-btn layui-btn-sm" lay-demo="getChecked">获取选中节点数据</button>--%>
<%--	<button type="button" class="layui-btn layui-btn-sm" lay-demo="setChecked">勾选指定节点</button>--%>
<%--	<button type="button" class="layui-btn layui-btn-sm" lay-demo="reload">重载实例</button>--%>
<%--</div>--%>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
	<legend></legend>
</fieldset>


<div class="layui-inline">
	<label class="layui-form-label">请选择角色</label>
	<div class="layui-input-inline">
		<select id="selectRoleID">
			<option value="0">|--请选择角色--|</option>
			<c:forEach items="${requestScope.listRoles}" begin="0" step="1" var="x">
				<option value="${x.roleId}">${x.roleName}</option>
			</c:forEach>
		</select>
	</div>
</div>
<div style="margin-left: 20px;">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" type="button" lay-demo="submitDate">重新分配</button>
	</div>
</div>

<div id="test5" class="demo-tree" style="margin-left: 20px;" ></div>
<script>
	layui.use(['tree', 'util'], function () {
		var tree = layui.tree
			, layer = layui.layer
			, util = layui.util;
		var $ = layui.$;
		$.ajax({
			type: "POST",
			url: "/bus/mangemenu",
			dataType: "json",
			async: true,
			success: function (msg) {
				//开启复选框
				tree.render({
					elem: '#test5'
					, data: msg
					, id: 'demoId1'
					, showCheckbox: true
				});
			}
		});
		//按钮事件
		util.event('lay-demo', {
			"submitDate": function(){
				var roleid=$("#selectRoleID").val();
				if(parseInt(roleid) > 1){
					//获得选中的节点
					var checkedData = tree.getChecked('demoId1');
					// layer.alert(JSON.stringify(checkedData));

					//把获取到的数据传到服务器
					$.ajax({
						type:"POST",
						data:{"roleId":roleid,"checkedData":JSON.stringify(checkedData)},
						url:"/bus/modify",
						dataType: "json",
						async: true,
						success:function (date) {
							alert(date.msg);
						}
					})
				}else if(parseInt(roleid) === 0){
					alert("请选择角色,才能重新分配权限！");
				}else if(parseInt(roleid) === 1){
					alert("超级管理员的权限无法修改噢！");
				}
			}
		});
	});

	$("#selectRoleID").change(function () {
		$.ajax({
			type: "POST",
			data:{"roleId":$("#selectRoleID").val()},
			url: "/bus/changedemo",
			dataType: "json",
			async: true,
			success: function (msg) {
				layui.use(['tree', 'util'], function() {
					var tree = layui.tree
						, layer = layui.layer
						, util = layui.util
						, data = msg;
					tree.render({
						elem: '#test5'
						, data: msg
						, id: 'demoId1'
						, showCheckbox: true
					});
				});
			}
		});
	})
</script>
</body>
</html>
