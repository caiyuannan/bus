<%--
  Created by IntelliJ IDEA.
  User: 70716
  Date: 2019/12/18
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>城市站点配置</title>
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<script src=<%=path+"/js/jquery-3.4.1.js"%>></script>
	<script src=<%=path+"/layui/layui.js"%>></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">城市站点配置管理</div>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree"  lay-filter="test">
				<c:forEach items="${requestScope.menu}" var="i" step="1" begin="0">
					<li class="layui-nav-item">
						<a class="" href="javascript:;">${i.provinceName}</a>
						<dl class="layui-nav-child">
							<c:forEach begin="0" step="1" var="y" items="${i.citys}">
								<dd><a href="/bus/station/stationTable?cityName=${y.cityName}" target="page-view">${y.cityName}</a></dd>
							</c:forEach>
						</dl>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="layui-body">
		<iframe src="" frameborder="0" name="page-view" id="myiframe" style="width: 100%;height: 100%"></iframe>
	</div>
</div>
<script>
	//JavaScript代码区域
	layui.use('element', function(){
		var element = layui.element;
	});
</script>
</body>
</html>
