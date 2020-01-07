<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2020/1/2
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getContextPath();
	String cssPath=request.getContextPath()+"/css/";
	String jsPath=request.getContextPath()+"/js/";
	String imgPath=request.getContextPath()+"/img/";
	String laPath = application.getContextPath()+"/layui/";
%>
<html>
<head>
	<title>智慧公交系统_账户中心->个人中心</title>
	<script src=<%=jsPath + "jquery.js"%>></script>
	<link rel="stylesheet" href="<%=laPath%>css/layui.css" media="all">
	<script src="<%=laPath%>layui.js"></script>
</head>
<body>
<form class="layui-form" target="_parent">
	<div class="layui-form-item" style="margin-top: 20px;">
		<label class="layui-form-label">编号</label>
		<div class="layui-input-block">
			<input type="text" name="userId" required  lay-verify="required" value="${requestScope.userBean.userId}" autocomplete="off" readonly="readonly" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item" style="margin-top: 20px;">
		<label class="layui-form-label">登录名</label>
		<div class="layui-input-block">
			<input type="text" name="userName" required  lay-verify="required" value="${requestScope.userBean.userName}" autocomplete="off" readonly="readonly" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item" style="margin-top: 20px;">
		<label class="layui-form-label">密码</label>
		<div class="layui-input-block">
			<input type="text" name="userPwd" required  lay-verify="required" value="${requestScope.userBean.userPassWord}" autocomplete="off" readonly="readonly" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item" style="margin-top: 20px;">
		<label class="layui-form-label">家庭住址</label>
		<div class="layui-input-block">
			<input type="text" id="userHome" name="userHome" required  lay-verify="required" value="${requestScope.userBean.userHome}" autocomplete="off" class="layui-input">
		</div>
		<input type="hidden" name="hid1" id="hid1" value="0">
		<input type="hidden" name="hid2"  id="hid2" value="1">
	</div>
	<div class="layui-form-item" style="margin-top: 20px;">
		<label class="layui-form-label">公司地址</label>
		<div class="layui-input-block">
			<input type="text" id="userCompany" name="userCompany" required  lay-verify="required" value="${requestScope.userBean.userCompany}" autocomplete="off" class="layui-input">
		</div>
		<input type="hidden" name="hid3"  id="hid3" value="0">
		<input type="hidden" name="hid4"  id="hid4" value="1">
	</div>
	<div class="layui-form-item" style="margin-top: 20px;">
		<label class="layui-form-label">电话</label>
		<div class="layui-input-block">
			<input type="text" name="userPhone"  id="userPhone" required  lay-verify="required" value="${requestScope.userBean.userPhoneNumber}" autocomplete="off" readonly="readonly" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item" style="margin-top: 20px;">
		<label class="layui-form-label">性别</label>
		<div class="layui-input-block">
			<input name="sex" title="男" type="radio" checked="" value="男" id="sex1">
			<input name="sex" title="女" type="radio" value="女" id="sex2">
			<input type="hidden" value="${requestScope.userBean.userSex}" id="userSex">
		</div>
	</div>
	<div class="layui-upload">
		<label class="layui-form-label">头像:</label>
		<div class="layui-upload layui-input-block">
			<img src="<%=imgPath%>${requestScope.userBean.userHeadPortrait}" id="userImg" style="width: 50px;height: 50px">
			<button class="layui-btn layui-btn-danger" id="test7" type="button"><i class="layui-icon"></i>更换头像</button>
		</div>
	</div>
	<div class="layui-form-item" style="margin-top: 20px;">
		<div class="layui-input-block">
			<button class="layui-btn" id="btn2" lay-submit lay-filter="formDemo">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<input type="hidden" value="${requestScope.userInformation}" id="successOrFail">
<input type="hidden" value=<%=path+"/img/"%> id="imgPathUrl">
<script>

	layui.use('form', function() {
		var form = layui.form;
		form.on('submit(formDemo)', function () {
			var userHome = $("#userHome").val();
			var hid1 = $("#hid1").val();
			var hid2 = $("#hid2").val();
			var userPhone = $("#userPhone").val();
			var userCompany = $("#userCompany").val();
			var hid3 = $("#hid3").val();
			var hid4 = $("#hid4").val();
			var sex = $("#sex").val();
			$.ajax({
				url: 'http://localhost:8080/bus/setUserInforMation',
				type: "post",
				data: {
					userHome: userHome,
					hid1: hid1,
					hid2: hid2,
					hid3: hid3,
					hid4: hid4,
					userPhone: userPhone,
					userCompany: userCompany,
					sex: sex
				},
				datatype: "json",
				async: true,
				success: function (res) {
					if (res === 'success') {
						alert('修改成功')
					} else {
						alert('修改失败')
					}
				},
				error: function () {
					alert('数据异常')
				}
			});
			return false;
		});
	})

	$("#userHome").click(function () {
		layer.open({
			type: 2
			,id: 'layerDemo1' //防止重复弹出
			,content: '/bus/front/DyfMapHomeOrCompany'
			,title: '家/公司地点选中'
			,btn: ['确定','取消']
			 ,area: ['100%', '100%']
			,success: function(layero, index){
				var iframe = window['layui-layer-iframe'+index];
				iframe.child("1");
			}
			,yes: function(){
				layer.closeAll();
			},
			btn2: function(){
				layer.closeAll();
			}

		});
	});
	$("#userCompany").click(function () {
		layer.open({
			type: 2
			,id: 'layerDemo1' //防止重复弹出
			,content: '/bus/front/DyfMapHomeOrCompany'
			,title: '家/公司地点选中'
			,btn: ['确定','取消']
			,area: ['100%', '100%']
			,success: function(layero, index){
				var iframe = window['layui-layer-iframe'+index];
				iframe.child("2");
			}
			,yes: function(){
				layer.closeAll();
			},
			btn2: function(){
				layer.closeAll();
			}

		});
	});
	$(function () {
		//单选框选中性别问题
		layui.use('form', function() {
			var form = layui.form;
			var userSex = $("#userSex").val();
		if (userSex.length>0) {
			if (userSex==='男') {
				$("#sex1").attr("checked","true");
			}else if (userSex==='女'){
				$("#sex2").attr("checked","true");
			}
		}
		//渲染 重要、1！！！！必须
			form.render();
		})


	});
	var imgUrl = $("#imgPathUrl").val();
	layui.use('upload', function() {
		var $ = layui.jquery,
			upload = layui.upload;
		//设定文件大小限制
		upload.render({
			elem: '#test7'
			, url: 'http://localhost:8080/bus/uploadFile'
			// , size: 100 //限制文件大小，单位 KB
			, done: function (res) {
				if (res.msg==='success') {
					alert('修改头像成功');
					 $("#userImg").attr("src",imgUrl+res.url);
				}else {
					alert('修改失败')
				}
			}
		});
	})
</script>
</body>
</html>
