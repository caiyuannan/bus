<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/30
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String cssPath=request.getContextPath()+"/css/";
	String jsPath=request.getContextPath()+"/js/";
	String imgPath=request.getContextPath()+"/img/";
	String laPath = application.getContextPath()+"/layui/";
%>
<html>
<head>
	<title>智慧公交系统_账户中心</title>
	<meta name="keywords" content="智慧公交系统_账户中心" />
	<meta name="description" content="智慧公交系统_账户中心" />
	<link rel="stylesheet" href=<%=path + "/bootstrap/css/bootstrap.min.css"%>>
	<link rel="stylesheet" href=<%=cssPath + "login.css"%>>
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src=<%=path + "/bootstrap/js/bootstrap.min.js"%>></script>
	<script src=<%=jsPath+"accountCenter.js"%>></script>
	<link rel="stylesheet" href="<%=laPath%>css/layui.css" media="all">
	<script src="<%=laPath%>layui.js"></script>
	<style>
		.panel-body a{text-decoration: none;color: #5e5e5e;}
		.panel-body:hover{background-color:#9EEA6A;opacity: 0.3;font-size: 20px;font-weight: bold;cursor: pointer;}
	</style>
</head>
<body>
<div class="container">

	<div class="row clearfix" style="margin: 20px;border: 1px solid #40AFFE;background-color: #4cae4c;border-radius: 5px;">
		<div class="col-md-12 column">
			<h2 style="color: #f9ffed;width:89%;float: left;">
				账户中心
			</h2>
			<span style="display: inline-block;margin-top: 25px;font-size: 20px;color:#fff9ec;"><a style="color: #f9ffed;cursor: pointer;" id="userName">${requestScope.userBean.userName}</a></span>
			<div class="row clearfix">
				<div class="col-md-2 column" style="background-color: #4cae4c;height: 550px;">
					<div class="panel-group" id="panel-709019">
						<div class="panel panel-default" style="text-align: center;">
							<div class="panel-heading">
								<a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-709019" href="#panel-element-328542" style="text-decoration:none;"><h4 style="color: #0C0C0C;">账户</h4></a>
							</div>
							<div id="panel-element-328542" class="panel-collapse collapse">
								<div class="panel-body">
									<a href="javascript:void(0)" title=<%=path+"/HtmlUserInforMation"%> onclick="changgeURL(this)">个人信息</a>
								</div>
								<div class="panel-body" >
									<a href="javascript:void(0)" id="successTitle" title=<%=path+"/myMoney"%> onclick="changgeURL(this)">我的钱包</a>
								</div>
								<div class="panel-body">
									<a href="javascript:void(0)" title=<%=path+"/twoDisMensional"%> onclick="changgeURL(this)">乘车二维码</a>
								</div>
								<div class="panel-body">
									<a href="javascript:void(0)" title=<%=path+"/front/DyfUserStream"%> onclick="changgeURL(this)">消费明细</a>
								</div>
							</div>
						</div>

					</div>

				</div>
				<div class="col-md-10 column" style="background-color: #deedf7;height: 550px;">
				<iframe src="" style="height: 100%;width: 100%" id="myiframe"></iframe>


				</div>
			</div>
		</div>
	</div>
</div>
<%--判断是否充值金额成功问题--%>
<input type="hidden" id="successMoney" value="${requestScope.success}">
<script>
	$(function () {
		$(".collapse").collapse();
		if ($("#successMoney").val()==='success') {
			var successTitle = $("#successTitle");
			var urlTitle = successTitle.attr('title');
			$("#myiframe").attr("src",urlTitle);
			console.log($("#myiframe").attr('src'));
		}
	})
</script>
</body>
</html>
