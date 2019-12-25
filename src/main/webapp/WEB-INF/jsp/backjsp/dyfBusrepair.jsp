<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2019/12/18
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page  isELIgnored = "false" %>
<%
	String path = application.getContextPath();
	String laPath = application.getContextPath()+"/layui/";
	String csspath = application.getContextPath()+"/css/";
	String viewPath = application.getContextPath()+"/jsp/";
	String jspath = application.getContextPath()+"/js/";
%>
<html>
<head>
	<title>车辆维修查看</title>
	<link rel="stylesheet" href="<%=laPath+"css/layui.css"%>">
	<script src="<%=laPath+"layui.js"%>"></script>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<script src=<%=jspath+"jquery-3.4.1.js"%>></script>
	<script src=<%=jspath+"dyfChangeProvince.js"%>></script>
</head>
<body class="layui-layout-body close-footer"  >
<h1 style="float: left;margin-top: 8px;margin-left: 40px;background-color: #8d8c76">欢迎来到公交车车辆信息配置</h1>
<div class="layui-layout layui-layout-admin">
	<div class="layui-side" >
		<div class="layui-side-scroll" >
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree"  lay-filter="test" style="background-color:#d2d2d2">
				<c:if test="${requestScope.provincesList!=null}">
					<c:forEach items="${requestScope.provincesList}" begin="0" step="1" var="entry">
						<li class="layui-nav-item " id="goto1" >
							<a class="">${entry.provinceName}</a>
							<dl class="layui-nav-child">

								<c:forEach items="${entry.city}"  begin="0" step="1" var="j">
									<dd><a title="${j.cityName}" href="javascript:void(0)" onclick="changgeIframe(this)">${j.cityName}</a></dd>
								</c:forEach>
							</dl>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>

	<div class="layui-body">
		<!-- 内容主体区域 -->
		<iframe src="" frameborder="0" name="select-view" id="myiframe" style="width: 100%;height: 100%"></iframe>
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
