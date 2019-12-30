<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String csspath = request.getContextPath() + "/css/";
	String jspath = request.getContextPath() + "/js/";
	String jsppath = request.getContextPath() + "/jsp/";
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>管理员登录界面</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<link rel="stylesheet" href=<%=csspath+"mangeuserlogin.css"%>>
	<script src=<%=path + "/layui/layui.js"%>></script>
	<script type="text/javascript" src=<%=jspath + "jquery-3.4.1.js"%>></script>
	<script src=<%=jspath + "mangeuserlogin.js"%>></script>
</head>
<body>

<div class="wrap">
	<img src=<%=path+"/img/backage.jpg"%> class="imgStyle">
	<div class="loginForm">
		<form id="form-date" action=<%=path+"/mangeUserLogin"%> method="POST">
			<div class="logoHead">
				<h2 style="margin-top: 15px">智能公交车平台管理系统</h2>
			</div>
			<div class="usernameWrapDiv">
				<div class="usernameLabel">
					<label>用户名:</label>
				</div>
				<div class="usernameDiv">
					<i class="layui-icon layui-icon-username adminIcon"></i>
					<input id="loginUsername" class="layui-input adminInput" type="text" name="username" placeholder="输入用户名" value="admin">
				</div>
			</div>
			<div class="usernameWrapDiv">
				<div class="usernameLabel">
					<label>密码:</label>
				</div>
				<div class="passwordDiv">
					<i class="layui-icon layui-icon-password adminIcon"></i>
					<input id="loginPassword" class="layui-input adminInput" type="password" name="password" placeholder="输入密码" value="123456">
				</div>
			</div>
			<div class="usernameWrapDiv">
				<div class="usernameLabel">
					<label>验证码:</label>
				</div>
				<div class="cardDiv">
					<input id="loginCard" class="layui-input cardInput" type="text" name="card" placeholder="输入验证码">
				</div>
				<div class="codeDiv">
					<input id="loginCode" class="layui-input codeInput"  type="button">
				</div>
			</div>
			<div class="usernameWrapDiv">
				<div class="submitLabel">
					<label>没有账号？<a href="#" id="loginRegister">点击注册</a></label>
				</div>
				<div class="submitDiv">
					<input id="loginBtn" type="button" class="submit layui-btn layui-btn-primary" value="登录" onclick="subMethod()"></input>
				</div>
			</div>
		</form>
	</div>
</div>
<c:choose>
	<c:when test="${requestScope.msg!=null}">
		<script>alert("${requestScope.msg}")</script>
	</c:when>
</c:choose>
</body>
</html>
