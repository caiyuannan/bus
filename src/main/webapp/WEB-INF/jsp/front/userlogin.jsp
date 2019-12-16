<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/16
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = application.getContextPath();
%>
<html>
<head>
	<title>乘客用户</title>
	<link rel="stylesheet"  href=<%=path+"/css/jquery-ui.min.css" %>>
	<link rel="stylesheet"  href=<%=path+"/css/jquery-ui.structure.min.css" %>>
	<link rel="stylesheet"  href=<%=path+"/css/jquery-ui.theme.min.css" %>>
	<script  src=<%=path+"/js/jquery.js" %>></script>
	<script  src=<%=path+"/js/demo.js" %>></script>
	<script  src=<%=path+"/js/jquery-3.4.1.js" %>></script>
	<script  src=<%=path+"/js/jquery-ui.min.js" %>></script>
</head>
<body>
<form action="./userlogin"  method="post" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8">
	帐号：<input type="text" name="userName" id="LAY-user-login-userName" lay-verify="required"
	          placeholder="请输入用户名" class="layui-input">
	<br/>
	密码：<input type="password" name="userPassword" id="LAY-user-login-userPassword" lay-verify="required"
	          placeholder="请输入密码" class="layui-input">
	<br/>
	<button type="submit">登录</button>
</form>

</body>
</html>
