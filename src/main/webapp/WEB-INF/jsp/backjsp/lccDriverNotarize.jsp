<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/19
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = application.getContextPath();
	String cssPath = request.getContextPath()+"/css/";
	String jsPath = request.getContextPath()+"/js/";
	String imgPath = request.getContextPath()+"/img/";
%>
<html>
<head>
	<meta charset="utf-8">
	<title>出站确认</title>
	<link rel="stylesheet" href="/bus/layui/css/layui.css">
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src="/bus/layui/layui.js"></script>
	<style>
		h1{
			text-align: center;
			padding: 30px;
		}
		.slt{
			background-color: pink;
			width: 100%;
			height: 25px;
		}
	</style>
</head>
<body>
<div class="layui-card">
	<div class="layui-card-header"></div>
	<div class="layui-card-body">
		<div class="layui-row">
			<div class="layui-col-md3">
				&nbsp;&nbsp;&nbsp;
			</div>
			<div class="layui-col-md6">
				<h1>出站确认</h1>
				<form action="/bus/toDriverNotarize" method="post">
					<div>
						时间:<input type="text" name="start" required  lay-verify="required" value="${requestScope.start}" placeholder="请输入标题" autocomplete="off" class="layui-input">
					</div>
					<div>
						车辆: <select name="busnum" id="busnum" class="slt">
						<option value=""> 请选择</option>
						<c:forEach items="${olist}" var="i" begin="0" step="1">
							<option value="${i.busLicense}" <c:if test="${i.busLicense eq requestScope.busnum}">selected="selected"</c:if>>${i.busLicense}</option>
						</c:forEach>
						<input type="hidden" value="${requestScope.busnum}" id="oid">
					</select>
					</div>

					<div>
						线路: <select name="route" id="route" class="slt">
						<option value=""> 请选择</option>
						<c:forEach items="${rlist}" var="i" begin="0" step="1">
							<option value="${i.routeName}" <c:if test="${i.routeName eq requestScope.route}">selected="selected"</c:if>>${i.routeName}</option>
						</c:forEach>
						<input type="hidden" value="${requestScope.route}" id="rid">
					</select>
					</div>

					<div>
						司机名称: <select name="driver" id="driver" class="slt">
						<option value=""> 请选择</option>
						<c:forEach items="${dlist}" var="i" begin="0" step="1">
							<option value="${i.driverName}" <c:if test="${i.driverName eq requestScope.driver}">selected="selected"</c:if>>${i.driverName}</option>
						</c:forEach>
						<input type="hidden" value="${requestScope.driver}" id="sid">
					</select>
					</div>

					<div>
						<div class="layui-input-block" style="text-align: center;padding: 15px;">
							<button class="layui-btn" type="submit">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</div>
			<div class="layui-col-md3">
				&nbsp;&nbsp;&nbsp;
			</div>
		</div>
	</div>
</div>
<%
	String mg = (String)request.getAttribute("msg1");
	if (mg!=null)
	{
					out.write("<script>alert('"+mg+"')</script>");

	}
%>
<script>

	$(function () {

		$("#busnum option").each(function () {

			if($(this).val() === $("#oid").val()){
				$(this).attr("selected",true);
			}
		});

		$("#route option").each(function () {

			if($(this).val() === $("#rid").val()){
				$(this).attr("selected",true);
			}
		});

		$("#driver option").each(function () {

			if($(this).val() === $("#sid").val()){
				$(this).attr("selected",true);
			}
		})


	});

</script>
</body>
</html>
