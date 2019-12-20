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
				<form class="layui-form" action="">
					<div class="layui-form-item">
						<label class="layui-form-label">时间:</label>
						<div class="layui-input-block">
							<input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">车辆:</label>
						<div class="layui-input-block">
							<input type="text" name="car" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">线路:</label>
						<div class="layui-input-block">
							<input type="text" name="role" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">司机名称:</label>
						<div class="layui-input-block">
							<input type="text" name="driver" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block" style="text-align: center;padding: 15px;">
							<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
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

<script>
	//Demo
	layui.use('form', function(){
		var form = layui.form;

		//监听提交
		form.on('submit(formDemo)', function(data){
			layer.msg(JSON.stringify(data.field));
			return false;
		});
	});
</script>
</body>
</html>
