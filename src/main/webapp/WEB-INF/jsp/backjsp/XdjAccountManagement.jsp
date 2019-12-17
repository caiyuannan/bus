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
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>乘客账号管理</title>
	<link rel="stylesheet"  href=<%=path+"/layui/css/layui.css"%>>
	<link rel="stylesheet"  href=<%=csspath+"jquery-ui.min.css" %>>
	<link rel="stylesheet"  href=<%=csspath+"jquery-ui.structure.min.css" %>>
	<link rel="stylesheet"  href=<%=csspath+"jquery-ui.theme.min.css" %>>

	<script  src=<%=path + "/layui/layui.js"%>></script>
	<script  src=<%=jspath+"jquery.js" %>></script>
	<script  src=<%=jspath+"jquery-3.4.1.js" %>></script>
	<script  src=<%=jspath+"jquery-ui.min.js" %>></script>
</head>
<body>
<table class="search_panel" align="center">
	<tr class="search_panel_info">
		用户名：
		<div class="layui-inline">
			<input class="layui-input" name="userName" id="demoReload" autocomplete="off">
		</div>
<%--		<td width="10%">用户名:</td>--%>
<%--		<td width="15%">--%>
<%--			<input class="field" type="text" placeholder="请输入用户名" name="account"/>--%>
<%--		</td>--%>
		手机号：
		<div class="layui-inline">
			<input class="layui-input" name="userPhonenumber" id="demoReload1" autocomplete="off">
		</div>
		<button class="layui-btn" data-type="reload">搜索</button>
<%--		<td width="10%">手机号:</td>--%>
<%--		<td width="15%">--%>
<%--			<input class="field" type="text" placeholder="请输入手机号" name="account"/>--%>
<%--		</td>--%>
<%--        <td width="10%" colspan="2">--%>
<%--	        <input type="button" class="btn" value="搜索" onclick=" myaction(this)"/>--%>
<%--        </td>--%>
	</tr>
</table>

<table class="user_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0" cellspacing="0">
	<tr class="user_panel_title" align="center">
		<td>序号</td>
		<td>手机号</td>
		<td>昵称</td>
		<td>家</td>
		<td>公司</td>
		<td>账户余额</td>
		<td>操作</td>
	</tr>
    <c:forEach items="${requestScope.table}" begin="0" step="1" var="i">
	<tr>
	    <td>${i.user_id}</td>
	    <td>${i.user_phonenumber}</td>
		<td>${i.user_name}</td>
	    <td>${i.user_home}</td>
	    <td>${i.user_company}</td>
	    <td>${i.user_balance}</td>
	    <td>
	        <span><a>查看</a></span>
	    </td>
	</tr>
	</c:forEach>
</table>

<script>
	layui.use('table', function() {
		var table = layui.table;

		table.render({
			elem: '#demo'
			,url:'http://localhost:8080/bus/XdjAccountManagementServlet'
			,title: '乘客用户表'
			,cols: [[
				{field:'user_id', title:'ID', width:100,  unresize: true, sort: true}
				,{field:'user_phonenumber', title:'手机号', width:150, edit: 'text'}
				,{field:'user_name', title:'昵称', width:100, edit: 'text'}
				,{field:'user_home', title:'家', width:100, edit: 'text', sort: true}
				,{field:'user_company', title:'公司', width:100}
				,{field:'user_balance', title:'账户余额'}
				,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
			]]
			,page: true
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
							id: demoReload.val()
						}
					}
				}, 'data');
			}
		};
		$('.demoTable .layui-btn').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
</script>

<%--<table class="function_panel" align="center">--%>
<%--	<tr>--%>
<%--		<td>--%>
<%--			<input class="btn" type="button" value="上一页" onclick="myaction(this)"/>--%>
<%--			<input type="hidden" name="startPage" value="${requestScope.pagestart}" id="staid">--%>
<%--			<span class="font" >${requestScope.pagestart}</span>--%>
<%--			<span class="font">/</span>--%>
<%--			<span class="font" >${requestScope.pagecount}</span>--%>
<%--			<input type="hidden" name="countPage" value="${requestScope.pagecount}" id="conid">--%>
<%--			<input class="btn" type="button" value="下一页" onclick="myaction(this)"/>--%>
<%--		</td>--%>
<%--	</tr>--%>
<%--</table>--%>

</body>
</html>
