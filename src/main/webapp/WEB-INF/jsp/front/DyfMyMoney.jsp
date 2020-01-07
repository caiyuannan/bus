<%--
  Created by IntelliJ IDEA.
  User: 40651
  Date: 2020/1/5
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getContextPath();
	String cssPath=request.getContextPath()+"/css/";
	String jsPath=request.getContextPath()+"/js/";
	String imgPath=request.getContextPath()+"/img/";
	String laPath = application.getContextPath()+"/layui/";
%>
<html>
<head>
	<title>我的钱包</title>
	<script src=<%=jsPath + "jquery.js"%>></script>
	<link rel="stylesheet" href="<%=laPath%>css/layui.css" media="all">
	<script src="<%=laPath%>layui.js"></script>
</head>
<body>
<form class="layui-form" action="<%=path%>/aliPay" method="post">
	<h1 style="margin-left:15%;">*欢迎来到个人钱包界面!</h1>
<div align="center" style="margin-left:29%;margin-top:20%">
<div class="layui-form-item">
	<label class="layui-form-label">用户名称</label>
	<div class="layui-input-inline" style="width: 200px">
		<input type="text" name="userName" placeholder="请输入" autocomplete="off" readonly="readonly" class="layui-input" value="${requestScope.userBean.userName}">
	</div>
</div>
<div class="layui-form-item">
	<label class="layui-form-label" >账户余额</label>
	<div class="layui-input-inline" style="width: 200px">
		<input type="text" id="userMoney" name="userMoney" placeholder="请输入" readonly="readonly"  autocomplete="off" class="layui-input" value="${requestScope.userBean.userBalance}">
	</div>
	<div class="layui-btn layui-btn-warm" style="float: left" id="pointMoney">充值</div>
</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</div>
	<input type="hidden" name="userPointMoney" id="userPointMoney">
	<input type="hidden" name="userType" id="userType" value="1">
</form>
<script>
	$("#pointMoney").click(function () {
		layui.use('layer', function(){
			var layer = layui.layer;
			//页面层
			layer.open({
				type: 1,
				title: '金额添加',
				closeBtn: 1,
				area: ['400px','150px'],
//		  skin: 'layui-layer-nobg', //没有背景色
				shadeClose: true,
				btn:['确认','取消'],
				content: $('#pointConfig')
				,yes: function(index, layero){
					var gotoMoney = $("#gotoMoney").val();
					$("#userPointMoney").val(gotoMoney);
					$("#userMoney").val(parseInt($("#userMoney").val())+parseInt(gotoMoney));
					layer.close(index); //如果设定了yes回调，需进行手工关闭
					layero.find('form').find('button[lay-submit]').click();
					return false;
				}
				,btn2:function (index, layero) {
					layer.close(index); //如果设定了yes回调，需进行手工关闭
				}
			});
		});
	})

	//表单提交
	layui.use('form', function(){
		var form = layui.form;

		//监听提交
		form.on('submit(formDemo)', function(data){
			/*layer.msg(JSON.stringify(data.field));
			return false;*/
		});
	});
</script>
<div id="pointConfig" style="display: none;" align="center">
	<div class="layui-form-item">
	<label class="layui-form-label" style="width:150px">请输入需要充值金额</label>
	<div class="layui-input-inline" style="width: 100px">
		<input type="text" name="userName" id="gotoMoney" placeholder="请输入" autocomplete="off" class="layui-input"
		       oninput="if(this.value=='00'){this.value='0';}else{this.value=this.value.replace(/[^0-9]/g,'')}"/>
	</div>
</div>
</div>
</body>
</html>
