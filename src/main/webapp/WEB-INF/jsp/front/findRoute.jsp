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
%>
<html>
<head>
	<title>线路查询</title>
	<meta name="keywords" content="智慧公交系统_线路查询" />
	<meta name="description" content="智慧公交系统_线路查询" />
	<link rel="stylesheet" href=<%=path + "/bootstrap/css/bootstrap.min.css"%>>
	<link rel="stylesheet" href=<%=cssPath + "login.css"%>>
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src=<%=path + "/bootstrap/js/bootstrap.min.js"%>></script>
</head>
<body>

<div class="container"style="margin-top: 20px;">
	<h3 class="panel-title" style="font-size: 45px;">
		线路查询
	</h3>
	<div class="row clearfix" style="margin-top: 20px;">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">

				<div class="collapse navbar-collapse row" id="bs-example-navbar-collapse-1">

					<form class="navbar-form  col-md-9" role="search" style="width: 75%;">
						<div class="form-group">
							<input type="text" class="form-control" />
						</div>         <button type="submit" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-search"></span> Search
					</button>
					</form>

					<div class="col-md-3" style="text-align: right;padding-top: 20px;">
						<span>admin</span>/注销
					</div>
				</div>


			</nav>
			<div class="jumbotron" style="height: 350px;box-sizing:border-box;padding:1%;">
				<div class="map" style="width:100%;height:100%;background-color: #eea236;color: #ffef8f">
					地图内容
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
