<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String csspath=request.getContextPath()+"/css/";
	String jspath=request.getContextPath()+"/js/";
	String jsppath=request.getContextPath()+"/jsp/";
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>智能公交车管理系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href=<%=path+"/layui/css/layui.css"%>>
	<script src="${ctx}/webjars/jquery/3.3.1-2/jquery.min.js"></script>
	<script src=<%=path + "/layui/layui.js"%>></script>
	<script language="JavaScript">
		history.pushState(null,null,document.URL);
		window.addEventListener('popstate',function () {
			history.pushState(null,null,document.URL);
		})
	</script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">后台管理界面</div>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					<c:choose>
						<c:when test="${sessionScope.user!=null}">
							<span id="login">欢迎你,${sessionScope.user.mangeUserName}</span>
						</c:when>
						<c:otherwise>
							<span id="login"><a href="/bus/web/mangeuserlogin"%>">登录</a></span>
						</c:otherwise>
					</c:choose>
				</a>
			</li>
			<li class="layui-nav-item"><a href="/bus/web/mangeuserlogin">退出</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<c:if test="${requestScope.menus !=null}">
				<ul class="layui-nav layui-nav-tree"  lay-filter="test">
					<c:forEach items="${requestScope.menus}" begin="0" step="1" var="x">
						<li class="layui-nav-item">
							<a class="" href="javascript:;">${x.key}</a>
							<dl class="layui-nav-child">
								<c:forEach items="${x.value}" begin="0" step="1" var="y">
									<dd><a href="${y.menuUrl}" target="right-iframe">${y.childrenName}</a></dd>
								</c:forEach>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
	</div>
	<div class="layui-body">
		<iframe name="right-iframe" id="right-frame" style="width: 100%;height: 100%"></iframe>
	</div>
</div>
<script>
	//JavaScript代码区域
	layui.use('element', function(){
		var element = layui.element;
	});

</script>
<c:choose>
	<c:when test="${requestScope.msg!=null}">
		<script>alert("${requestScope.msg}")</script>
	</c:when>
</c:choose>
</body>
</html>
