<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/16
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String csspath = request.getContextPath() + "/css/";
	String jspath = request.getContextPath() + "/js/";
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>乘客用户登录</title>
	<link rel="stylesheet"  href=<%=path+"/layui/css/layui.css"%>>
	<link rel="stylesheet"  href=<%=csspath+"jquery-ui.min.css" %>>
	<link rel="stylesheet"  href=<%=csspath+"jquery-ui.structure.min.css" %>>
	<link rel="stylesheet"  href=<%=csspath+"jquery-ui.theme.min.css" %>>
	<link rel="stylesheet"  href=<%=csspath+"userlogin.css" %>>

	<script  src=<%=path + "/layui/layui.js"%>></script>
	<script  src=<%=jspath+"jquery.js" %>></script>
	<script  src=<%=jspath+"jquery-3.4.1.js" %>></script>
	<script  src=<%=jspath+"jquery-ui.min.js" %>></script>
	<script  src=<%=jspath+"userlogin.js"%>></script>
</head>
<body>
<form action="./userlogin"  method="post" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8">
	帐号：<input type="text" name="userName" id="LAY-user-login-userName" lay-verify="required"
	          placeholder="请输入用户名" class="layui-input">
	<br/>
	密码：<input type="password" name="userPassword" id="LAY-user-login-userPassword" lay-verify="required"
	          placeholder="请输入密码" class="layui-input">
	<br/>
	<button type="submit">登录/注册</button>
</form>

</body>
</html>
