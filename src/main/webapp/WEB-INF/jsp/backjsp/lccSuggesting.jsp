<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2019/12/23
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String layuiPath = request.getContextPath()+"/layui/";
	String jsPath = request.getContextPath()+"/js/";
	String imgPath = request.getContextPath()+"/img/";
%>
<html>
<head>
	<title>投诉建议</title>
	<link rel="stylesheet" href="/bus/layui/css/layui.css">
	<script src=<%=jsPath + "jquery.js"%>></script>
	<script src="/bus/layui/layui.js"></script>
	<style>
		.layui-upload-img {
			width: 600px;
			height: 300px;
			margin: 0 10px 10px 0;
		}
		.layui-form-item {
			width: 600px;
		}
		.layui-form-label {
			text-align: left;
		}
		h1{
			width: 600px;
			text-align: center;
			margin: 20px;
			box-sizing:border-box;
		}
		.layui-body {
			position: absolute;
			left: 346px;
			right: 0;
			top: 0;
			bottom: 0;
			z-index: 998;
			width: auto;
			overflow-y: auto;
			box-sizing: border-box;
		}
	</style>
</head>
<body class="layui-layout-body">
<div  class="layui-body">
	<h1>投诉建议</h1>
<form class="layui-form" action="">
	<div class="layui-form-item" style="width: 600px;">
		<label class="layui-form-label">标题</label>
		<div class="layui-input-block">
			<input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-upload layui-form-item">
		<button type="button" class="layui-btn" id="test1" style="display: inline-block;">选择图片</button>
		<div class="layui-upload-list">
			<img class="layui-upload-img" id="demo1">
			<p id="demoText"></p>
		</div>
	</div>

	<div class="layui-form-item layui-form-text">
		<label class="layui-form-label">内容</label>
		<div class="layui-input-block">
			<textarea name="desc" placeholder="请输入内容" class="layui-textarea" id="desc"></textarea>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="formDemo" id="formDemo">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>

<script>

	//Demo
	layui.use(['form','upload'], function(){
		var form = layui.form
			,$ = layui.jquery
			,upload = layui.upload;


		//选完文件后不自动上传
		upload.render({
			elem: '#test1'
			,url: '/bus/toUpload'
			,before: function(obj){
				this.data={'desc':$('#desc').val()};//关键代码
			}
			,choose: function(obj){

				//预读本地文件示例，不支持ie8
				obj.preview(function(index, file, result){
					$('#demo1').attr('src', result); //图片链接（base64）
				});
			}
			, auto: false
			, bindAction: '#formDemo'
			, done: function (res) {
				//alert(res);
				if (res == 200) {

					layer.msg("提交成功", {icon: 6});
					setTimeout(location.reload(),10000);

				} else {

					layer.msg("提交失败", {icon: 5});
				}
			}
		});

		//监听提交
		form.on('submit(formDemo)', function(data){
			//layer.msg(JSON.stringify(data.field));
			return false;
		});
	});
</script>
</div>
</body>
</html>
